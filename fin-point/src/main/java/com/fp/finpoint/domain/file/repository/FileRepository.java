package com.fp.finpoint.domain.file.repository;

import com.fp.finpoint.domain.file.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
}
