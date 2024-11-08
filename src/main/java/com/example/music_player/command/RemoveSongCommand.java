package com.example.music_player.command;

import com.example.music_player.service.PlaylistService;

public class RemoveSongCommand implements Command {

    private final PlaylistService playlistService;
    private final Long playlistId;
    private final Long songId;

    public RemoveSongCommand(PlaylistService playlistService, Long playlistId, Long songId) {
        this.playlistService = playlistService;
        this.playlistId = playlistId;
        this.songId = songId;
    }

    @Override
    public void execute() {
        playlistService.removeSongFromPlaylist(playlistId, songId);
    }
}
