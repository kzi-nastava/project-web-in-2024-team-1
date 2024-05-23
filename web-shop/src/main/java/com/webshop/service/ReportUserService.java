package com.webshop.service;

import com.webshop.dto.RejectReportDto;
import com.webshop.dto.ReportActionDto;
import com.webshop.dto.ReportUserDto;
import com.webshop.exception.AccountRoleException;
import com.webshop.model.Account;
import com.webshop.model.ReportStatus;
import com.webshop.model.ReportUser;
import com.webshop.model.Role;
import com.webshop.repository.AccountRepository;
import com.webshop.repository.ReportUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReportUserService {

    @Autowired
    private ReportUserRepository reportUserRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ProductService productService;


    public ReportUserDto addReport(ReportUserDto reportUserDto,Account currentAccount) {

        try {
            ReportUser reportUser = new ReportUser();
            reportUser.setReason(reportUserDto.getReason());
            reportUser.setReportDate(new Date());
            Account reportedAccount = currentAccount;
            Account reportingAccount = accountRepository.findById(reportUserDto.getReportingUserId()).orElseThrow(() -> new RuntimeException("Reporting user not found"));
            //Account reportedAccount = accountRepository.findById(reportUserDto.getReportedUserId()).orElseThrow(() -> new RuntimeException("Reported user not found"));
            if(reportingAccount == reportedAccount){
                throw new AccountRoleException("Reported user and reporting user cannot be the same");
            }
            reportUser.setReportingUser(reportingAccount);
            reportUser.setReportedUser(reportedAccount);
            reportUser.setStatus(ReportStatus.valueOf(reportUserDto.getStatus()));

            ReportUser savedReport = reportUserRepository.save(reportUser);

            return new ReportUserDto(savedReport.getId(),savedReport.getReason(),savedReport.getReportDate(),savedReport.getReportingUser().getId(),savedReport.getReportedUser().getId(),savedReport.getStatus().toString());

        } catch (Exception e) {
            throw new RuntimeException("Error saving report",e);
        }

    }

    public List<ReportUserDto> getAllReportUsers() {
        List<ReportUser> reportUsers = reportUserRepository.findAll();
        List<ReportUserDto> reportUserDtos = new ArrayList<>();
        for (ReportUser reportUser : reportUsers) {
            String status =  "UNKNOWN";
            if(reportUser.getStatus() != null){
                status = reportUser.getStatus().toString();
            }
            ReportUserDto reportUserDto = new ReportUserDto(reportUser.getId(), reportUser.getReason(),
                    reportUser.getReportDate(), reportUser.getReportingUser().getId(),
                    reportUser.getReportedUser().getId(), status);

            reportUserDtos.add(reportUserDto);
        }
        return reportUserDtos;
    }

    public void acceptReport(Long reportId,Account currentAccount) {
        if(!isAdmin(currentAccount)){
            throw new AccountRoleException("You do not have permission to accept this request");
        }
        ReportUser reportUser = reportUserRepository.findById(reportId).orElseThrow(() -> new RuntimeException("Report not found"));
        reportUser.setStatus(ReportStatus.ACCEPTED);
        reportUserRepository.save(reportUser);

        Account reportedUser = reportUser.getReportingUser();
        reportedUser.setBlocked(true);
        accountRepository.save(reportedUser);
        //reportUser.getReportedUser().setBlocked(true);

    }

    public void rejectReport(Long reportId,RejectReportDto rejectReportDto,Account currentAccount) {
        if(!isAdmin(currentAccount)){
            throw new AccountRoleException("You do not have permission to reject this request");
        }
        ReportUser reportUser = reportUserRepository.findById(reportId).orElseThrow(() -> new RuntimeException("Report not found"));
        reportUser.setStatus(ReportStatus.REJECTED);
        reportUser.setReason(rejectReportDto.getRejectReason());
        reportUserRepository.save(reportUser);
    }

    public boolean isAdmin(Account account) {
        return account != null && account.getUserRole() == Role.ADMINISTRATOR;
    }

    public List<ReportActionDto> getUserReportActions(Account currentAccount){

        List<ReportUser> userReports = reportUserRepository.findByReportedUser_Id(currentAccount.getId());
        List<ReportActionDto> reportActionDtos = new ArrayList<>();

        for (ReportUser reportUser : userReports) {
            ReportActionDto actionDto  = new ReportActionDto();
            actionDto.setReportId(reportUser.getId());

            if(reportUser.getStatus() == ReportStatus.ACCEPTED){
                actionDto.setMessage("Your report has been accepted");
            } else if (reportUser.getStatus() == ReportStatus.REJECTED){
                String message = "Your report has been rejected: " + reportUser.getReason();
                actionDto.setMessage(message);
            }
            reportActionDtos.add(actionDto);
        }
        return reportActionDtos;
    }
}
