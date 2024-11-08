package com.example.music_player.command;

import com.example.music_player.service.PlaylistService;

public class AddSongCommand implements Command{
    private final PlaylistService playlistService;
    private final Long playlistId;
    private final Long songId;

    public AddSongCommand(PlaylistService playlistService, Long playlistId, Long songId) {
        this.playlistService = playlistService;
        this.playlistId = playlistId;
        this.songId = songId;
    }

    @Override
    public void execute() {
        playlistService.addSongToPlaylist(playlistId, songId);
    }

}
