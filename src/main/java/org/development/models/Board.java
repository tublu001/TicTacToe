package org.development.models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    int size;
    List<List<Cell>> board;

    public Board(int size) {
        this.size = size;
        this.board = new ArrayList<>();
        for(int row = 0; row < size; ++row) {
            board.add(new ArrayList<>());
            for(int col = 0; col < size; ++col) {
                board.get(row).add(new Cell(row, col));
            }
        }
    }
}
