package com.example.music_player.command;

import com.example.music_player.model.Playlist;
import com.example.music_player.model.Song;
import com.example.music_player.repository.PlaylistRepository;
import com.example.music_player.repository.SongRepository;

public class AddSongToPlaylistCommand implements Command {
    private final PlaylistRepository playlistRepository;
    private final SongRepository songRepository;
    private final Long playlistId;
    private final Long songId;

    public AddSongToPlaylistCommand(PlaylistRepository playlistRepository, SongRepository songRepository, Long playlistId, Long songId) {
        this.playlistRepository = playlistRepository;
        this.songRepository = songRepository;
        this.playlistId = playlistId;
        this.songId = songId;
    }

    @Override
    public void execute() {
        Playlist playlist = playlistRepository.findById(playlistId).orElseThrow(() ->
                new IllegalArgumentException("Playlist not found with ID: " + playlistId));
        Song song = songRepository.findById(songId).orElseThrow(() ->
                new IllegalArgumentException("Song not found with ID: " + songId));

        System.out.println("Додаємо пісню: " + song.getTitle() + " до плейлиста: " + playlist.getName());

        if (!playlist.getSongs().contains(song)) {
            playlist.getSongs().add(song);
            playlistRepository.save(playlist);
        }
    }
}
