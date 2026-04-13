package com.source.lunina.main.entity;

import com.source.lunina.common.entity.BaseEntity;
import com.source.lunina.main.constants.RankEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Users")
@EqualsAndHashCode(callSuper = true)
public class Users extends BaseEntity implements UserDetails {
    private String username;
    private String passwordHash;
    private String fullName;
    private String email;
    private String address; // Địa chỉ mặc định

    private Double totalSpending = 0.0; // Tổng tiền đã mua để xét hạng
    private Integer points = 0; // Điểm tích lũy (nếu cần)

    @Enumerated(EnumType.STRING)
    private RankEnum rank = RankEnum.NORMAL; // Mặc định là khách mới

    private String role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }
}
