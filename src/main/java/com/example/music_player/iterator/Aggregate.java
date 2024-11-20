package com.example.music_player.iterator;

public interface Aggregate<T> {
    Iterator<T> createIterator();
}
