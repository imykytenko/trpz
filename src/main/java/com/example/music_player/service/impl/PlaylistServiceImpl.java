package com.example.music_player.service.impl;

import com.example.music_player.command.AddSongToPlaylistCommand;
import com.example.music_player.command.Command;
import com.example.music_player.command.PlaylistCommandInvoker;
import com.example.music_player.command.RemoveSongFromPlaylistCommand;
import com.example.music_player.iterator.Iterator;
import com.example.music_player.model.Playlist;
import com.example.music_player.model.Song;
import com.example.music_player.repository.PlaylistRepository;
import com.example.music_player.repository.SongRepository;
import com.example.music_player.service.PlaylistService;
import com.example.music_player.visitor.Element;
import com.example.music_player.visitor.MusicPlayer;
import com.example.music_player.visitor.StatisticsVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlaylistServiceImpl implements PlaylistService{
    @Autowired
    private final PlaylistRepository playlistRepository;
    @Autowired
    private final SongRepository songRepository;
    private final Map<Long, Iterator<Song>> iterators = new HashMap<>();
    private final PlaylistCommandInvoker commandInvoker = new PlaylistCommandInvoker();

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
        Command addSongCommand = new AddSongToPlaylistCommand(playlistRepository, songRepository, playlistId, songId);
        commandInvoker.executeCommand(addSongCommand);
    }

    @Override
    public void removeSongFromPlaylist(Long playlistId, Long songId) {
        Command removeSongCommand = new RemoveSongFromPlaylistCommand(playlistRepository, songRepository, playlistId, songId);
        commandInvoker.executeCommand(removeSongCommand);
    }

    @Override
    public List<Song> getSongsInPlaylist(Long playlistId) {
        Playlist playlist = getPlaylistById(playlistId);

        if (playlist == null) {
            return null;
        }

        Set<Song> uniqueSongs = new HashSet<>(playlist.getSongs());
        List<Song> songsInPlaylist = new ArrayList<>(uniqueSongs);

        return songsInPlaylist;
    }

    @Override
    public Song getNextSong(Long playlistId) {
        Playlist playlist = playlistRepository.findById(playlistId)
                .orElseThrow(() -> new NoSuchElementException("Playlist not found"));

        iterators.putIfAbsent(playlistId, playlist.createIterator());
        Iterator<Song> iterator = iterators.get(playlistId);

        if (!iterator.hasNext()) {
            iterator.reset();
        }

        return iterator.next();
    }

    @Override
    public Map<String, Object> calculateStatistics(Long playlistId) {
        Playlist playlist = getPlaylistById(playlistId);

        if (playlist == null) {
            throw new NoSuchElementException("Playlist not found");
        }

        List<Element> elements = new ArrayList<>();
        elements.add(playlist);
        MusicPlayer musicPlayer = new MusicPlayer(elements);

        StatisticsVisitor visitor = new StatisticsVisitor();
        musicPlayer.acceptVisitor(visitor);

        Map<String, Object> statistics = new HashMap<>();
        statistics.put("Кількість пісень", visitor.getTotalSongs());
        statistics.put("Загальна тривалість", visitor.getTotalDuration());
        return statistics;
    }
}