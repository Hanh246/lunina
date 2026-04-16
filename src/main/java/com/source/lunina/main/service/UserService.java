package com.source.lunina.main.service;

import com.source.lunina.common.dto.pagination.PaginationSearchDTO;
import com.source.lunina.main.dto.UserDTO;
import com.source.lunina.main.entity.Users;
import com.source.lunina.main.plugin.mapper.UserMapperPlugin;
import com.source.lunina.main.repository.IUserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final IUserRepository userRepository;
    private final UserMapperPlugin modelMapper;

    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(modelMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Người dùng không tồn tại"));
    }

    public UserDTO getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(modelMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Người dùng không tồn tại"));
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        Users existingModel = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entity with id " + id + " not found"));

        Users updatedModel = modelMapper.updateModel(existingModel, userDTO);
        Users savedModel = userRepository.save(updatedModel);
        return modelMapper.toDto(savedModel);
    }

    @Transactional(readOnly = true)
    public Page<UserDTO> list(PaginationSearchDTO paginationDTO) {
        Pageable pageable = paginationDTO.toPageRequest();

        Page<UserDTO> data;
        if (paginationDTO.getSearch() == null || paginationDTO.getSearch().isEmpty()) {
            data = userRepository.findAll(pageable).map(modelMapper::toDto);
        } else {
            data = userRepository
                    .findAll(userRepository.contains(modelMapper.getSearchableFieldNames(), paginationDTO.getSearch()), pageable)
                    .map(modelMapper::toDto);
        }

        return data;
    }

    // Trong UserService.java
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(modelMapper::toDto)
                .toList();
    }

    public Users toModel(UserDTO dto) {
        return modelMapper.toModel(dto);
    }
    public UserDTO toDto(Users model) {
        return modelMapper.toDto(model);
    }

}
