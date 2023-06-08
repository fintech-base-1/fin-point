package com.fp.finpoint.domain.member.entity;

//import com.fp.finpoint.domain.like.Like;
import com.fp.finpoint.domain.like.Like;
import com.fp.finpoint.domain.oauth.OauthClient;
import com.fp.finpoint.domain.openbank.Entity.Token;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(unique = true, nullable = false)
    @Email
    @NotBlank
    private String email;

    private String password;

    private String salt;

    private String code;

    @Enumerated(EnumType.STRING)
    private OauthClient oauthClient;

//    @ElementCollection(fetch = FetchType.EAGER)
    @ElementCollection()
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();
  
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "token_id")
    private Token token;

    private String fintech_use_num;
    public void assignCode(String code) {
        this.code = code;
    }

    public void setFintech_use_num(String fintech_use_num) {
        this.fintech_use_num = fintech_use_num;
    }

    public void setToken(Token token) {
        this.token = token;
    }


    @OneToMany(mappedBy = "member")
    private List<Like> likes = new ArrayList<>(); //좋아요

}
