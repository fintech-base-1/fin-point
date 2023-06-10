package com.fp.finpoint.domain.piece.Entity;

import com.fp.finpoint.domain.invest.entity.Invest;
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
    private String name;
    private Long price;
    private Long count;
    @Version
    private Long version;

    @OneToOne(mappedBy = "piece")
    private Invest invest;

    public Piece(String name, Long price, Long count) {
        this.name = name;
        this.price = price;
        this.count = count;
    }

    public void updateCount(Long count) {
        this.count -= count;
    }
}
