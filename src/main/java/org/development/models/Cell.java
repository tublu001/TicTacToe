package org.development.models;

import lombok.Data;

@Data
public class Cell {
    int row;
    int col;
    Player player;
    CellState cellState;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
        cellState = CellState.FREE;
    }

    public void displayCell() {
        System.out.print("| " + ((cellState.equals(CellState.FREE)? "-": player.getSymbol().symbol) + " | "));
    }
}
