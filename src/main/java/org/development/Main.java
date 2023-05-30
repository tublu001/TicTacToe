package org.development;

import org.development.controllers.GameController;
import org.development.models.*;
import org.development.strategies.winningStrategies.WinningStrategy;
import org.development.strategies.winningStrategies.colWinningStrategy;
import org.development.strategies.winningStrategies.diagonalWinningStrategy;
import org.development.strategies.winningStrategies.rowWinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        List<Player> players = new ArrayList<>();
        players.add(new Player("Manash", new Symbol('X'), PlayerType.HUMAN));
        players.add(new Bot("GPT1", new Symbol('O'), BotDifficultyLevel.EASY));
        players.add(new Bot("GPT2", new Symbol('1'), BotDifficultyLevel.EASY));
        players.add(new Bot("GPT3", new Symbol('2'), BotDifficultyLevel.EASY));

        List<WinningStrategy> winningStrategies = new ArrayList<>();
        winningStrategies.add(new rowWinningStrategy());
        winningStrategies.add(new colWinningStrategy());
        winningStrategies.add(new diagonalWinningStrategy());

        GameController gameController = new GameController();
        Game game = gameController.createGame(players, 5, winningStrategies);
        gameController.showBoard(game);

        while(gameController.gameStatus(game).equals(GameStatus.IN_PROGRESS)) {
            System.out.println("\n" + gameController.getCurrentPlayerTurn(game).getName() + "'s turn ->");
            gameController.makeMove(game);
            gameController.checkGameStatus(game);
            gameController.showBoard(game);
            Thread.sleep(1000);
        }

        if (gameController.gameStatus(game) == GameStatus.ENDED) {
            System.out.println("\nGame Ended!!! " + gameController.getWinner(game).getName() + " [" + gameController.getWinner(game).getPlayerType() + "] is the Winner...");
        } else if (gameController.gameStatus(game) == GameStatus.DRAW) {
            System.out.println("\nGame Drawn...");
        }
    }
}