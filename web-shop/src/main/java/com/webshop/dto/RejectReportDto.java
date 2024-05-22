package com.webshop.dto;

public class RejectReportDto {
    private Long id;
    private String rejectReason;

    public RejectReportDto() {}
    public RejectReportDto(Long id, String rejectReason) {
        this.id = id;
        this.rejectReason = rejectReason;
    }

    public String getRejectReason() {
        return rejectReason;
    }
    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
