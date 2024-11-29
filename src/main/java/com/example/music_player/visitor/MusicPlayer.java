package com.example.music_player.visitor;
import java.util.List;

public class MusicPlayer {
    private final List<Element> elements;

    public MusicPlayer(List<Element> elements) {
        this.elements = elements;
    }

    public void acceptVisitor(Visitor visitor) {
        for (Element element : elements) {
            element.accept(visitor);
        }
    }
}