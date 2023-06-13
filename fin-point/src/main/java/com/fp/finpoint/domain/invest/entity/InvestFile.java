package com.fp.finpoint.domain.invest.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table
public class InvestFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="invest_file_id")
    private Long id;
    private String originName;
    private String savedName;
    private String savedPath;

    @Builder
    public InvestFile(String originName, String savedName, String savedPath) {
        this.originName = originName;
        this.savedName = savedName;
        this.savedPath = savedPath;
    }

}