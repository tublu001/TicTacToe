package org.development.strategies.winningStrategies;

import org.development.models.Board;
import org.development.models.Move;

public class rowWinningStrategy implements WinningStrategy {

    @Override
    public boolean checkWinner(Board board, Move move) {
        return false;
    }
}
