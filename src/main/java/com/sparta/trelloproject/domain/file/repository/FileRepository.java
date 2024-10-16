package com.sparta.trelloproject.domain.file.repository;

import com.sparta.trelloproject.domain.file.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}
