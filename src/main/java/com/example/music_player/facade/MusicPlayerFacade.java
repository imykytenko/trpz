package com.example.music_player.facade;

import com.example.music_player.model.Playlist;
import com.example.music_player.model.Song;
import com.example.music_player.service.PlaylistService;
import com.example.music_player.service.SongService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
@Component
public class MusicPlayerFacade {
    private final PlaylistService playlistService;
    private final SongService songService;

    public MusicPlayerFacade(PlaylistService playlistService, SongService songService) {
        this.playlistService = playlistService;
        this.songService = songService;
    }

    // Методи для роботи з піснями
    public Song addSong(Song song) {
        return songService.add(song);
    }

    public Optional<Song> findSongById(Long id) {
        return songService.findById(id);
    }

    public List<Song> getAllSongs() {
        return songService.findAll();
    }

    public void deleteSongById(Long id) {
        songService.deleteById(id);
    }

    // Методи для роботи з плейлистами
    public Playlist createPlaylist(Playlist playlist) {
        return playlistService.createPlaylist(playlist);
    }

    public Playlist getPlaylistById(Long id) {
        return playlistService.getPlaylistById(id);
    }

    public List<Playlist> getAllPlaylists() {
        return playlistService.getAllPlaylists();
    }

    public Playlist updatePlaylist(Long id, Playlist playlist) {
        return playlistService.updatePlaylist(id, playlist);
    }

    public void deletePlaylist(Long id) {
        playlistService.deletePlaylist(id);
    }

    public List<Song> getSongsInPlaylist(Long playlistId) {
        return playlistService.getSongsInPlaylist(playlistId);
    }

    public void addSongToPlaylist(Long playlistId, Long songId) {
        playlistService.addSongToPlaylist(playlistId, songId);
    }

    public void removeSongFromPlaylist(Long playlistId, Long songId) {
        playlistService.removeSongFromPlaylist(playlistId, songId);
    }

    public Song getNextSong(Long playlistId) {
        return playlistService.getNextSong(playlistId);
    }

    public Map<String, Object> calculateStatistics(Long playlistId) {
        return playlistService.calculateStatistics(playlistId);
    }

}
