package com.fp.finpoint.domain.file.repository;

import com.fp.finpoint.domain.file.entity.FileEntity;
import com.fp.finpoint.domain.member.entity.Member;
import org.apache.tomcat.jni.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<FileEntity, Long> {
}
