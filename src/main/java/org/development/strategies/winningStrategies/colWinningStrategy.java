package org.development.strategies.winningStrategies;

import org.development.models.Board;
import org.development.models.Move;
import org.development.models.Player;

public class colWinningStrategy implements WinningStrategy {

    @Override
    public boolean checkWinner(Board board, Move move) {
        int col = move.getCell().getCol();
        Player player = move.getPlayer();

        for(int rowIdx = 0; rowIdx < board.getSize(); rowIdx++) {
            if(!player.equals(board.getBoard().get(rowIdx).get(col).getPlayer())) {
                return false;
            }
        }
        return true;
    }
}
