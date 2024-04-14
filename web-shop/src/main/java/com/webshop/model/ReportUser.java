package com.webshop.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
public class ReportUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String Reason;

    @Column
    private Date ReportDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Account reportingUser;

    @ManyToOne
    @JoinColumn(name = "reporter_id")
    private Account reportedUser;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReportStatus reportingStatus;

    public ReportUser() {
        this.id = 1L;
        this.Reason = "";
        this.ReportDate = new Date(0);
        this.reportingUser = new Account();
        this.reportedUser = new Account();
        this.reportingStatus = ReportStatus.SUBMITTED;
    }

    public ReportUser(Long id, String reason, Date reportDate, Account reportingUser, Account reportedUser, ReportStatus status) {
        this.id = id;
        this.Reason = reason;
        this.ReportDate = reportDate;
        this.reportingUser = reportingUser;
        this.reportedUser = reportedUser;
        this.reportingStatus= status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        this.Reason = reason;
    }

    public Date getReportDate() {
        return ReportDate;
    }

    public void setReportDate(Date reportDate) {
        this.ReportDate = reportDate;
    }

    public Account getReportingUser() {
        return reportingUser;
    }

    public void setReportingUser(Account reportingUser) {
        this.reportingUser = reportingUser;
    }

    public Account getReportedUser() {
        return reportedUser;
    }

    public void setReportedUser(Account reportedUser) {
        this.reportedUser = reportedUser;
    }

    public ReportStatus getStatus() {
        return reportingStatus;
    }

    public void setStatus(ReportStatus status) {
        this.reportingStatus = status;
    }
}
