package com.fp.finpoint.domain.member.entity;

import com.fp.finpoint.domain.openbank.Entity.Token;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String email;

    private String password;

    private String salt;

    private String code;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id")
    private Token token;

    private String fintech_use_num;
    public void assignCode(String code) {
        this.code = code;
    }

    public void setFintech_use_num(String fintech_use_num) {
        this.fintech_use_num = fintech_use_num;
    }
}
