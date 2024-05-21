package com.webshop.controller;

import com.webshop.dto.ReportUserDto;
import com.webshop.service.ReportUserService;
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
    public ResponseEntity<ReportUserDto> addReport(@RequestBody ReportUserDto reportUserDto) {
        ReportUserDto result = reportUserService.addReport(reportUserDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @GetMapping("all")
    public List<ReportUserDto> getAllReports() {
        return reportUserService.getAllReportUsers();
    }
}
