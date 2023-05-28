package org.development.strategies.winningStrategies;

import org.development.models.Board;
import org.development.models.Move;

public interface WinningStrategy {
    boolean checkWinner(Board board, Move move);
}
