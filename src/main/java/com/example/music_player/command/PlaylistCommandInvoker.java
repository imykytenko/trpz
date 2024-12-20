package com.example.music_player.command;


import lombok.Setter;

@Setter
public class PlaylistCommandInvoker {

    public void executeCommand(Command command) {
        command.execute();
    }
}
