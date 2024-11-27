package com.example.music_player.service.impl;

import com.example.music_player.command.AddSongCommand;
import com.example.music_player.command.Command;
import com.example.music_player.command.PlaylistCommandInvoker;
import com.example.music_player.command.RemoveSongCommand;
import com.example.music_player.iterator.Iterator;
import com.example.music_player.memento.PlaylistHistory;
import com.example.music_player.memento.PlaylistMemento;
import com.example.music_player.model.Playlist;
import com.example.music_player.model.Song;
import com.example.music_player.repository.PlaylistRepository;
import com.example.music_player.repository.SongRepository;
import com.example.music_player.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PlaylistServiceImpl implements PlaylistService {
    @Autowired
    private final PlaylistRepository playlistRepository;
    @Autowired
    private final SongRepository songRepository;
    private final PlaylistCommandInvoker commandInvoker = new PlaylistCommandInvoker();
    private final Map<Long, PlaylistHistory> playlistHistories = new HashMap<>();

    @Autowired
    public PlaylistServiceImpl(PlaylistRepository playlistRepository, SongRepository songRepository) {
        this.playlistRepository = playlistRepository;
        this.songRepository = songRepository;
    }

    @Override
    public Playlist createPlaylist(Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    @Override
    public Playlist getPlaylistById(Long id) {
        return playlistRepository.findById(id).orElse(null);
    }

    @Override
    public List<Playlist> getAllPlaylists() {
        return playlistRepository.findAll();
    }

    @Override
    public Playlist updatePlaylist(Long id, Playlist playlist) {
        if (playlistRepository.existsById(id)) {
            playlist.setId(id);
            return playlistRepository.save(playlist);
        }
        return null;
    }

    @Override
    public void deletePlaylist(Long id) {
        playlistRepository.deleteById(id);
    }

    @Override
    public void addSongToPlaylist(Long playlistId, Long songId) {
        Playlist playlist = playlistRepository.findById(playlistId).orElse(null);
        Song song = songRepository.findById(songId).orElse(null);
        if (playlist != null && song != null) {
            Command addSongCommand = new AddSongCommand(this, playlistId, songId);
            commandInvoker.setCommand(addSongCommand);
            commandInvoker.executeCommand();
        }
    }

    @Override
    public void removeSongFromPlaylist(Long playlistId, Long songId) {
        Playlist playlist = playlistRepository.findById(playlistId).orElse(null);
        Song song = songRepository.findById(songId).orElse(null);
        if (playlist != null && song != null) {
            Command removeSongCommand = new RemoveSongCommand(this, playlistId, songId);
            commandInvoker.setCommand(removeSongCommand);
            commandInvoker.executeCommand();
        }
    }

    @Override
    public List<Song> getSongsInPlaylist(Long playlistId) {
        Playlist playlist = getPlaylistById(playlistId);

        if (playlist == null) {
            return null;
        }

        List<Song> songsInPlaylist = new ArrayList<>();
        Iterator<Song> songIterator = playlist.createIterator();

        while (songIterator.hasNext()) {
            songsInPlaylist.add(songIterator.next());
        }

        return songsInPlaylist;
    }

    @Override
    public void savePlaylistState(Long playlistId) {
        Playlist playlist = getPlaylistById(playlistId);

        if (playlist != null) {
            PlaylistMemento memento = playlist.createMemento();
            playlistHistories.putIfAbsent(playlistId, new PlaylistHistory());
            playlistHistories.get(playlistId).addMemento(memento);
        }
    }

    @Override
    public void restorePlaylistState(Long playlistId, int mementoIndex) {
        Playlist playlist = getPlaylistById(playlistId);

        if (playlist != null) {
            PlaylistHistory history = playlistHistories.get(playlistId);
            PlaylistMemento memento = history.getMemento(mementoIndex);
            playlist.restoreFromMemento(memento);
            playlistRepository.save(playlist);
        }
    }
}
