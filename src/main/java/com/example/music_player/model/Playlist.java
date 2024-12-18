package com.example.music_player.model;

import com.example.music_player.iterator.Aggregate;
import com.example.music_player.iterator.Iterator;
import java.util.*;

import com.example.music_player.iterator.PlaylistSongIterator;
import com.example.music_player.visitor.Element;
import com.example.music_player.visitor.Visitor;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Playlist implements Aggregate<Song>, Element {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "playlist_song",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id")
    )
    private List<Song> songs = new ArrayList<>();

    @Override
    public Iterator<Song> createIterator() {
        return new PlaylistSongIterator(this.songs);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
        for (Song song : songs) {
            song.accept(visitor);
        }
    }
}
