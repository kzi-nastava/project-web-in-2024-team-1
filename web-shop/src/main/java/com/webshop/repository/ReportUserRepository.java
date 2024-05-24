package com.webshop.repository;

import com.webshop.model.ReportUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportUserRepository extends JpaRepository<ReportUser, Long> {
    List<ReportUser> findByReportedUser_Id(Long userId);
    List<ReportUser> findByReportingUser_Id(Long reportUserId);
}
