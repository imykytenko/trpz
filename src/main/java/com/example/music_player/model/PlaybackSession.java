package com.example.music_player.model;

import jakarta.persistence.*;

@Entity
public class PlaybackSession{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "currentTrackId", nullable = false)
    private Song currentTrack;

    @ManyToOne
    @JoinColumn(name = "playlistId", nullable = false)
    private Playlist playlist;

    private boolean isShuffling;

    private boolean isRepeating;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Song getCurrentTrack() {
        return currentTrack;
    }

    public void setCurrentTrack(Song currentTrack) {
        this.currentTrack = currentTrack;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }

    public boolean isShuffling() {
        return isShuffling;
    }

    public void setShuffling(boolean isShuffling) {
        this.isShuffling = isShuffling;
    }

    public boolean isRepeating() {
        return isRepeating;
    }

    public void setRepeating(boolean isRepeating) {
        this.isRepeating = isRepeating;
    }
}
