package com.example.music_player.model;

import com.example.music_player.visitor.Element;
import com.example.music_player.visitor.Visitor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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

    private String fileUrl;

    private String format;
    @ManyToMany(mappedBy = "songs")
    @JsonIgnore
    private List<Playlist> playlists = new ArrayList<>();

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
