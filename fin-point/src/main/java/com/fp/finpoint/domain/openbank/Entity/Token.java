package com.fp.finpoint.domain.openbank.Entity;

import com.fp.finpoint.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "text")
    private String access_token;
    private String token_type;
    private String expires_in;
    @Column(columnDefinition = "text")
    private String refresh_token;
    private String scope;
    private String userSeqNo;
    @OneToOne(mappedBy = "token")
    private Member member;

    public Token(String access_token, String token_type, String expires_in, String refresh_token, String scope, String user_seq_no) {
        this.access_token = access_token;
        this.token_type = token_type;
        this.expires_in = expires_in;
        this.refresh_token = refresh_token;
        this.scope = scope;
        this.userSeqNo = user_seq_no;
    }
}
