package com.source.lunina.main.controller;

import com.source.lunina.common.dto.pagination.PaginationMetadata;
import com.source.lunina.common.dto.pagination.PaginationSearchDTO;
import com.source.lunina.common.dto.response.PaginationResponse;
import com.source.lunina.main.dto.LoginDTO;
import com.source.lunina.main.dto.UserDTO;
import com.source.lunina.main.service.AuthService;
import com.source.lunina.main.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody UserDTO userDTO, @RequestParam String password) {
        return ResponseEntity.ok(authService.register(userDTO, password));
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@RequestBody LoginDTO loginDTO) {
        // Trả về thông tin User nếu đăng nhập đúng
        return ResponseEntity.ok(authService.login(loginDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getProfile(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping
    public ResponseEntity<PaginationResponse<List<UserDTO>>> findAll(@Valid @ParameterObject PaginationSearchDTO paginationDTO) {
        var data = userService.list(paginationDTO);
        return ResponseEntity
                .ok(PaginationResponse.<List<UserDTO>>builder()
                        .metadata(
                                new PaginationMetadata(
                                        paginationDTO.getPage(),
                                        paginationDTO.getSize(),
                                        data.getTotalElements(),
                                        data.getTotalPages()
                                )
                        )
                        .success(true)
                        .data(data.toList())
                        .build());
    }
}
