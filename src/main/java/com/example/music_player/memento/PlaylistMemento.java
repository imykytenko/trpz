package com.example.music_player.memento;

import com.example.music_player.model.Song;

import java.util.List;

public class PlaylistMemento {
    private final List<Song> songs;

    public PlaylistMemento(List<Song> songs) {
        this.songs = songs;
    }

    public List<Song> getSavedSongs() {
        return this.songs;
    }
}
