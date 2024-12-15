package com.example.music_player.visitor;

import com.example.music_player.model.Playlist;
import com.example.music_player.model.Song;

public interface Visitor {
    void visit(Song song);
    void visit(Playlist playlist);
}
