package org.development.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
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

    public void displayBoard() {
        System.out.print("     ");
        for(int i = 0; i < board.size(); i++) {
            System.out.print(i + "     ");
        }
        System.out.println();
        int rowNum = 0;
        for(List<Cell> cells: board) {
            System.out.print(rowNum++ + ": ");
            for(Cell cell : cells) {
                cell.displayCell();
            }
            System.out.println();
        }
    }
}
