package com.source.lunina.main.controller;

import com.source.lunina.common.dto.pagination.PaginationMetadata;
import com.source.lunina.common.dto.pagination.PaginationSearchDTO;
import com.source.lunina.common.dto.response.BaseResponse;
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
    public ResponseEntity<BaseResponse<UserDTO>> register(@RequestBody UserDTO userDTO, @RequestParam String password) {
        UserDTO data = authService.register(userDTO, password);
        return ResponseEntity.ok(BaseResponse.<UserDTO>builder()
                .success(true)
                .data(data)
                .build());
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<UserDTO>> login(@RequestBody LoginDTO loginDTO) {
        UserDTO data = authService.login(loginDTO);
        return ResponseEntity.ok(BaseResponse.<UserDTO>builder()
                .success(true)
                .data(data)
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BaseResponse<UserDTO>> updateProfile(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        UserDTO data = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(BaseResponse.<UserDTO>builder()
                .success(true)
                .data(data)
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse<UserDTO>> getProfile(@PathVariable Long id) {
        UserDTO data = userService.getUserById(id);
        return ResponseEntity.ok(BaseResponse.<UserDTO>builder()
                .success(true)
                .data(data)
                .build());
    }

    @GetMapping
    public ResponseEntity<PaginationResponse<List<UserDTO>>> findAll(@Valid @ParameterObject PaginationSearchDTO paginationDTO) {
        var pageData = userService.list(paginationDTO);

        return ResponseEntity.ok(PaginationResponse.<List<UserDTO>>builder()
                .success(true)
                .data(pageData.getContent())
                .metadata(new PaginationMetadata(
                        paginationDTO.getPage(),
                        paginationDTO.getSize(),
                        pageData.getTotalElements(),
                        pageData.getTotalPages()
                ))
                .build());
    }
}