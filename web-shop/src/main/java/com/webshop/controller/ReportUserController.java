package com.webshop.controller;

import com.webshop.dto.PuchaseActionDto;
import com.webshop.dto.RejectReportDto;
import com.webshop.dto.ReportActionDto;
import com.webshop.model.Account;
import com.webshop.dto.ReportUserDto;
import com.webshop.service.ReportUserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/reports/")
public class ReportUserController {

    @Autowired
    private ReportUserService reportUserService;

    @PostMapping("add")
    public ResponseEntity<ReportUserDto> addReport(@RequestBody ReportUserDto reportUserDto,HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        ReportUserDto result = reportUserService.addReport(reportUserDto,account);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("all")
    public List<ReportUserDto> getAllReports() {
        return reportUserService.getAllReportUsers();
    }

    @PostMapping("accept/{reportId}")
    public ResponseEntity<String> acceptReport(@PathVariable Long reportId, HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            return new ResponseEntity<>("Not logged in", HttpStatus.UNAUTHORIZED);
        }
        try{
            reportUserService.acceptReport(reportId,account);
            return ResponseEntity.ok("Report accepted");
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("reject/{reportId}")
    public ResponseEntity<String> rejectReport(@PathVariable Long reportId, @RequestBody RejectReportDto rejectReportDto, HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            return new ResponseEntity<>("Not logged in", HttpStatus.UNAUTHORIZED);
        }
        try{
            reportUserService.rejectReport(reportId,rejectReportDto,account);
            return ResponseEntity.ok("Report rejected");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("{userId}/actions")
    public ResponseEntity<List<ReportActionDto>> getReportActions(HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        List<ReportActionDto> reportActionDtos = reportUserService.getUserReportActions(account);
        return ResponseEntity.ok(reportActionDtos);
    }

    @GetMapping("{userId}/acceptAction")
    public ResponseEntity<List<ReportActionDto>> getUserReports(HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        List<ReportActionDto> reportActionDtos = reportUserService.getUserReports(account);
        return ResponseEntity.ok(reportActionDtos);
    }


}
