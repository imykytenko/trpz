package com.example.music_player.visitor;

import com.example.music_player.model.Playlist;
import com.example.music_player.model.Song;
import lombok.Getter;

@Getter
public class StatisticsVisitor implements Visitor {
    private int totalSongs = 0;
    private long totalDuration = 0;

    @Override
    public void visit(Song song) {
        totalSongs++;
        totalDuration += (long) song.getDuration();
    }

    @Override
    public void visit(Playlist playlist) {
        for (Song song : playlist.getSongs()) {
            visit(song);
        }
    }
}