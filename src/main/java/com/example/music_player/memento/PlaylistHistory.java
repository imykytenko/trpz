package com.example.music_player.memento;

import java.util.ArrayList;
import java.util.List;

public class PlaylistHistory {
    private final List<PlaylistMemento> mementos;

    public PlaylistHistory() {
        this.mementos = new ArrayList<>();
    }

    public void addMemento(PlaylistMemento memento) {
        mementos.add(memento);
    }

    public PlaylistMemento getMemento(int index) {
        return this.mementos.get(index);
    }
}
