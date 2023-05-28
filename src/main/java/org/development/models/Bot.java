package org.development.models;

import org.development.strategies.botPlayingStrategies.BotPlayingStrategy;
import org.development.strategies.botPlayingStrategies.BotPlayingStrategyFactory;

public class Bot extends Player{
    BotDifficultyLevel botDifficultyLevel;
    BotPlayingStrategy botPlayingStrategy;

    public Bot(String name, Symbol symbol, BotDifficultyLevel difficultyLevel) {
        super(name, symbol, PlayerType.BOT);
        this.botDifficultyLevel = difficultyLevel;
        this.botPlayingStrategy = BotPlayingStrategyFactory.getBotPlayingStrategy(difficultyLevel);
    }

    @Override
    public Cell makeMove() {
        return botPlayingStrategy.makeMove(new Board(3));
    }
}
