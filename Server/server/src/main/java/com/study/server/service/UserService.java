package com.study.server.service;

import com.study.server.dto.UserDto;
import com.study.server.model.Role;
import com.study.server.model.User;
import com.study.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @RequiredArgsConstructor @Transactional
public class UserService {
  private final UserRepository userRepository;
  private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  public UserDto create(UserDto dto) {
    if (userRepository.existsByUsername(dto.getUsername()))
      throw new IllegalArgumentException("Username already taken");

    User user = User.builder()
        .username(dto.getUsername())
        .password(passwordEncoder.encode(dto.getPassword()))
        .role(dto.getRole() != null ? dto.getRole() : Role.USER)
        .build();
    user = userRepository.save(user);
    return toDto(user);
  }

  @Transactional(readOnly = true)
  public java.util.List<UserDto> findAll() {
    return userRepository.findAll().stream().map(this::toDto).toList();
  }

  @Transactional(readOnly = true)
  public User getEntity(Long id) {
    return userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
  }

  public UserDto update(Long id, UserDto dto) {
    User user = getEntity(id);
    if (dto.getUsername() != null) user.setUsername(dto.getUsername());
    if (dto.getPassword() != null && !dto.getPassword().isBlank())
      user.setPassword(passwordEncoder.encode(dto.getPassword()));
    if (dto.getRole() != null) user.setRole(dto.getRole());
    return toDto(user);
  }

  public void delete(Long id) { userRepository.deleteById(id); }

  private UserDto toDto(User u) {
    return UserDto.builder()
        .id(u.getId()).username(u.getUsername()).role(u.getRole())
        .build();
  }
}
