package com.example.music_player.model;

import com.example.music_player.iterator.Aggregate;
import com.example.music_player.iterator.Iterator;
import java.util.*;

import com.example.music_player.iterator.SongIterator;
import com.example.music_player.memento.PlaylistMemento;
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

    @ManyToMany
    @JoinTable(
            name = "playlist_song",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id")
    )
    private List<Song> songs;

    @Override
    public Iterator<Song> createIterator() {
        return new SongIterator(songs);
    }

    public PlaylistMemento createMemento() {
        return new PlaylistMemento(this.songs);
    }

    public void restoreFromMemento(PlaylistMemento memento) {
        this.songs = memento.getSavedSongs();
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
        for (Song song : songs) {
            song.accept(visitor);
        }
    }
}
