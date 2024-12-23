package com.example.music_player.iterator;

import com.example.music_player.model.Song;
import java.util.List;
import java.util.NoSuchElementException;

public class SongIterator implements Iterator<Song>{
    private final List<Song> songs;
    private int position;

    public SongIterator(List<Song> songs) {
        this.songs = songs;
    }

    @Override
    public boolean hasNext() {
        return position < songs.size();
    }

    @Override
    public Song next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more songs in the playlist.");
        }
        return songs.get(position++);
    }
}
