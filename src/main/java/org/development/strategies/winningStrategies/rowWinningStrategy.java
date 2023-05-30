package org.development.strategies.winningStrategies;

import org.development.models.Board;
import org.development.models.Move;
import org.development.models.Player;

public class rowWinningStrategy implements WinningStrategy {

    @Override
    public boolean checkWinner(Board board, Move move) {
        int row = move.getCell().getRow();
        Player player = move.getPlayer();

        for(int colIdx = 0; colIdx < board.getSize(); colIdx++) {
            if(!player.equals(board.getBoard().get(row).get(colIdx).getPlayer())) {
                return false;
            }
        }
        return true;
    }
}
