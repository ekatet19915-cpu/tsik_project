package com.study.client.model;

import com.study.server.model.ResourceType;
import org.antlr.v4.runtime.misc.NotNull;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Resource {
  private Long id;
  @NotBlank private String title;
  private String content;
  @NotNull
  private ResourceType type;
  @NotNull private Long uploaderId;
}
