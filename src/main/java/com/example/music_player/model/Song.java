package com.example.music_player.model;

import com.example.music_player.visitor.Element;
import com.example.music_player.visitor.Visitor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Song implements Element {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String artist;

    private double duration;

    @Lob
    private byte[] fileData;

    private String format;


    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
