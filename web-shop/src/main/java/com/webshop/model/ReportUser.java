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
    @Column
    private ReportStatus status;

    public ReportUser() {
        this.Reason = "";
        this.ReportDate = new Date(0);
        this.status = ReportStatus.SUBMITTED;
    }

    public ReportUser(String reason, Date reportDate, ReportStatus status) {
        this.Reason = reason;
        this.ReportDate = reportDate;
        this.status = status;
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
        return status;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }
}
