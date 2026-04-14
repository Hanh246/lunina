package com.source.lunina.main.service;

import com.source.lunina.main.constants.RankEnum;
import com.source.lunina.main.constants.RoleEnum;
import com.source.lunina.main.dto.LoginDTO;
import com.source.lunina.main.dto.UserDTO;
import com.source.lunina.main.entity.Users;
import com.source.lunina.main.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    public UserDTO register(UserDTO userDTO, String password) {
        if (userRepository.findByEmail(userDTO.getEmail()).isPresent()) {
            throw new RuntimeException("Email này đã được đăng ký!");
        }

        Users user = modelMapper.map(userDTO, Users.class);
        // Mã hóa mật khẩu trước khi lưu vào DB
        user.setPasswordHash(passwordEncoder.encode(password));
        user.setRole(RoleEnum.CUSTOMER);
        user.setRank(RankEnum.NORMAL);

        Users savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDTO.class);
    }

    public UserDTO login(LoginDTO loginDTO) {
        // Tìm user theo email
        Users user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("Email hoặc mật khẩu không chính xác"));

        // So sánh mật khẩu thô với mật khẩu đã mã hóa trong DB
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPasswordHash())) {
            throw new RuntimeException("Email hoặc mật khẩu không chính xác");
        }

        return modelMapper.map(user, UserDTO.class);
    }
}
