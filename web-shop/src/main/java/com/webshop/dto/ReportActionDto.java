package com.webshop.dto;

public class ReportActionDto {
    private Long reportId;
    private String message;

    public ReportActionDto() {}

    public ReportActionDto(Long reportId, String message) {
        this.reportId = reportId;
        this.message = message;
    }

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
