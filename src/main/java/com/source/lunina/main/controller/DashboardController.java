package com.source.lunina.main.controller;

import com.source.lunina.common.dto.response.BaseResponse;
import com.source.lunina.main.dto.DashboardStatisticsDTO;
import com.source.lunina.main.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/summary")
    public ResponseEntity<BaseResponse<DashboardStatisticsDTO>> getSummary() {
        // Lấy dữ liệu từ service
        DashboardStatisticsDTO stats = dashboardService.getDashboardStats();

        // Đóng gói vào BaseResponse bằng SuperBuilder
        BaseResponse<DashboardStatisticsDTO> response = BaseResponse.<DashboardStatisticsDTO>builder()
                .success(true)
                .data(stats)
                .message("Lấy dữ liệu dashboard thành công")
                .build();

        return ResponseEntity.ok(response);
    }
}
