package org.development.models;

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
}
