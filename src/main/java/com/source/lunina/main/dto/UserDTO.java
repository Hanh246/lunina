package com.source.lunina.main.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.source.lunina.main.constants.RankEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String fullName;
    private String email;
    private String address; // Địa chỉ mặc định

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double totalSpending = 0.0; // Tổng tiền đã mua để xét hạng
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double points = 0.0; // Điểm tích lũy (nếu cần)

    @Enumerated(EnumType.STRING)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private RankEnum rank; // Mặc định là khách mới

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String role;
}
