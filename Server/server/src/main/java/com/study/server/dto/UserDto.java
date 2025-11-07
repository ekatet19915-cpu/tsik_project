package com.study.server.dto;

import com.study.server.model.Role;
import jakarta.validation.constraints.*;
import lombok.*;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class UserDto {
  private Long id;
  @NotBlank private String username;
  @NotBlank(groups = Create.class) private String password; // на create обязательно
  private Role role;

  public interface Create {}
  public interface Update {}
}
