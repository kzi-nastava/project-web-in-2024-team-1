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
    private User reportingUser;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User reportedUser;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReportStatus status;

    public ReportUser() {
        this.id = 1L;
        this.Reason = "";
        this.ReportDate = new Date(0);
        this.reportingUser = new User();
        this.reportedUser = new User();
        this.status = ReportStatus.SUBMITTED;
    }

    public ReportUser(Long id, String reason, Date reportDate, User reportingUser, User reportedUser, ReportStatus status) {
        this.id = id;
        this.Reason = reason;
        this.ReportDate = reportDate;
        this.reportingUser = reportingUser;
        this.reportedUser = reportedUser;
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

    public User getReportingUser() {
        return reportingUser;
    }

    public void setReportingUser(User reportingUser) {
        this.reportingUser = reportingUser;
    }

    public User getReportedUser() {
        return reportedUser;
    }

    public void setReportedUser(User reportedUser) {
        this.reportedUser = reportedUser;
    }

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }
}
