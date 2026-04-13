package com.source.lunina.main.service;

import com.source.lunina.common.dto.pagination.PaginationSearchDTO;
import com.source.lunina.main.dto.UserDTO;
import com.source.lunina.main.plugin.mapper.UserMapperPlugin;
import com.source.lunina.main.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
