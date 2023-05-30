package org.development.models;

import lombok.Data;

import java.util.Scanner;

@Data
public class Player {
    private String name;
    private Symbol symbol;
    private PlayerType playerType;

    Scanner sc = new Scanner(System.in);

    public Player(String name, Symbol symbol, PlayerType playerType) {
        this.name = name;
        this.symbol = symbol;
        this.playerType = playerType;
    }

    public Cell makeMove(Board gameBoard) {
        System.out.print("Enter Row: ");
        int row = sc.nextInt();
        System.out.print("Enter Col: ");
        int col = sc.nextInt();
        return new Cell(row, col);
    }
}
