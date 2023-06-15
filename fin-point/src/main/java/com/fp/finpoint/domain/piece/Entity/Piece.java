package com.fp.finpoint.domain.piece.Entity;

import com.fp.finpoint.domain.invest.entity.Invest;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @Column(columnDefinition = "text")
    private String uuid;

    @OneToOne(mappedBy = "piece")
    private Invest invest;

    @OneToMany(mappedBy = "piece")
    private List<PieceMember> pieceMembers = new ArrayList<>();

    public Piece(String name, Long price, Long count) {
        this.name = name;
        this.price = price;
        this.count = count;
    }

    public Piece(String name, Long price, Long count, String uuid) {
        this.name = name;
        this.price = price;
        this.count = count;
        this.uuid = uuid;
    }

    public void updateCount(Long count) {
        this.count -= count;
    }

    public void plusCount(Long count) {
        this.count += count;
    }
}
