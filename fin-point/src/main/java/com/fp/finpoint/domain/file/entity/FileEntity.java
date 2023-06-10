package com.fp.finpoint.domain.file.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
@Table(name = "file")
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="file_id")
    private Long id;

    @NotNull
    private String email;

    private String originName;
    private String savedName;
    private String savedPath;

    @Builder
    public FileEntity(Long id, String originName, String savedName, String savedPath, String email) {
        this.id = id;
        this.originName = originName;
        this.savedName = savedName;
        this.savedPath = savedPath;
        this.email = email;
    }

}

