package org.development.models;

import lombok.Data;

@Data
public class Move {
    private Player player;
    private Cell cell;

    public Move(Player player, Cell cell) {
        this.player = player;
        this.cell = cell;
    }
}
