package com.fp.finpoint.domain.invest.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvestDto {

    public String subject;
    public String content;
    public Long id;

    // 여기서 Invest는 return 타입
    public Invest toEntity() {
        return new Invest(this.subject, this.content, this.id);
    }
}
