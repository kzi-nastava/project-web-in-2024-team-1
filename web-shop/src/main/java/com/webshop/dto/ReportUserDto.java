package com.webshop.dto;

import com.webshop.model.ReportUser;

import java.util.Date;

public class ReportUserDto {
    private Long id;
    private String reason;
    private Date reportDate;
    private Long reportingUserId;
    private Long reportedUserId;
    private String status;

    public ReportUserDto() {}

    public ReportUserDto(Long id, String reason, Date reportDate, Long reportingUserId, Long reportedUserId, String status) {
        this.id = id;
        this.reason = reason;
        this.reportDate = reportDate;
        this.reportingUserId = reportingUserId;
        this.reportedUserId = reportedUserId;
        this.status = status;
    }

    public ReportUserDto(ReportUser reportUser) {
        this.id = reportUser.getId();
        this.reason = reportUser.getReason();
        this.reportDate = reportUser.getReportDate();
        this.reportingUserId = reportUser.getReportingUser().getId();
        this.reportedUserId = reportUser.getReportedUser().getId();
        this.status = reportUser.getStatus().toString();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public Long getReportingUserId() {
        return reportingUserId;
    }

    public void setReportingUserId(Long reportingUserId) {
        this.reportingUserId = reportingUserId;
    }

    public Long getReportedUserId() {
        return reportedUserId;
    }

    public void setReportedUserId(Long reportedUserId) {
        this.reportedUserId = reportedUserId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
