package com.study.server.controller;

import com.study.server.dto.TaskDto;
import com.study.server.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/api/tasks") @RequiredArgsConstructor
public class TaskController {
  private final TaskService taskService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public TaskDto create(@RequestBody @Valid TaskDto dto) { return taskService.create(dto); }

  @GetMapping public List<TaskDto> all() { return taskService.findAll(); }

  @GetMapping("/group/{groupId}")
  public List<TaskDto> byGroup(@PathVariable Long groupId) { return taskService.findByGroup(groupId); }

  @PutMapping("/{id}") public TaskDto update(@PathVariable Long id, @RequestBody TaskDto dto) {
    return taskService.update(id, dto);
  }

  @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) { taskService.delete(id); }
}
