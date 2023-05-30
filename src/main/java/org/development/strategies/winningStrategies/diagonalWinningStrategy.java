package org.development.strategies.winningStrategies;

import org.development.models.Board;
import org.development.models.Move;
import org.development.models.Player;

import java.util.HashMap;
import java.util.Map;

public class diagonalWinningStrategy implements WinningStrategy {

    private Map<Player, Integer> leftDiagCounts = new HashMap<>();
    private Map<Player, Integer> rightDiagCounts = new HashMap<>();
    @Override
    public boolean checkWinner(Board board, Move move) {
        Player player = move.getPlayer();

        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        // check for left diagonal
        if (row == col) {
            if (!leftDiagCounts.containsKey(player)) {
                leftDiagCounts.put(player, 0);
            }

            leftDiagCounts.put(
                    player,
                    leftDiagCounts.get(player) + 1);
        }

        // check for right diagonal
        if (row + col == board.getSize() - 1) {
            if (!rightDiagCounts.containsKey(player)) {
                rightDiagCounts.put(player, 0);
            }

            rightDiagCounts.put(
                    player,
                    rightDiagCounts.get(player) + 1
            );
        }

        if (row == col) {
            if (leftDiagCounts.get(player).equals(board.getSize())) {
                return true;
            }
        }

        if (row + col == board.getSize() - 1) {
            if (rightDiagCounts.get(player).equals(board.getSize())) {
                return true;
            }
        }


        return false;
    }
}
