package com.study.client.model;

import com.study.server.model.TaskStatus;

import java.time.Instant;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Task {
  private Long id;
  @NotBlank private String title;
  private String description;
  @NotNull private Long groupId;
  private Long assignedToId;
  private Instant dueDate;
  private TaskStatus status;
}
