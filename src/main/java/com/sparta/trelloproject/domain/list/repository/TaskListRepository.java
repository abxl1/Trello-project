package com.sparta.trelloproject.domain.list.repository;

import com.sparta.trelloproject.domain.list.entity.TaskList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskListRepository extends JpaRepository<TaskList, Long> {
}
