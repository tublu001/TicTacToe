package org.development.strategies.botPlayingStrategies;

import org.development.models.Board;
import org.development.models.Cell;

public interface BotPlayingStrategy {
    Cell makeMove(Board board);
}
