package com.fp.finpoint.domain.piece.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Piece {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long price;
    private Long count;
    //todo: member와 1:n, Invest(판매글)과 1:1
    //todo: 동시성 이슈가 생길 수 있음 낙관적 락을 걸고 일단 테스트 진행
    @Version
    private Long version;


    public Piece(Long price, Long count) {
        this.price = price;
        this.count = count;
    }

    public void updateCount(Long count) {
        this.count -= count;
    }
}
