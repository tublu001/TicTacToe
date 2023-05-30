package org.development.controllers;

import org.development.models.Game;
import org.development.models.GameStatus;
import org.development.models.Player;
import org.development.strategies.winningStrategies.WinningStrategy;

import java.util.List;

public class GameController {
    public Game createGame(List<Player> players, int dimension, List<WinningStrategy> winningStrategies) throws Exception {
        return Game.getBuilder().withBoardSize(dimension)
                .addPlayers(players)
                .addWinningStrategies(winningStrategies)
                .build();
    }

    public void showBoard(Game game) {
        game.displayBoard();
    }

    public GameStatus gameStatus(Game game) {
        return game.getGameStatus();
    }

    public void makeMove(Game game) {
        game.makeMove();
    }

    public void checkGameStatus(Game game) {
        game.checkGameStatus();
    }

    public Player getWinner(Game game) {
        return game.getWinner();
    }

    public Player getCurrentPlayerTurn(Game game) {
        return game.getCurrentPlayerTurn();
    }
}
