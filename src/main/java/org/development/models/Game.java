package org.development.models;

import lombok.Data;
import org.development.strategies.winningStrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
@Data
public class Game {
    private List<Player> players;
    private Board gameBoard;
    private List<Move> moves;
    private GameStatus gameStatus;
    private Player winner;
    private int currentMovePlayerIndex;
    private List<WinningStrategy> winningStrategies;

    private Game(List<Player> players, int dimension, List<WinningStrategy> winningStrategies) {
        this.players = players;
        this.gameBoard = new Board(dimension);
        this.moves = new ArrayList<>();
        this.gameStatus = GameStatus.IN_PROGRESS;
        this.winningStrategies = winningStrategies;
        this.currentMovePlayerIndex = 0;
    }

    public static class Builder {
        private final List<Player> players = new ArrayList<>();
        int dimension;
        private final List<WinningStrategy> winningStrategies = new ArrayList<>();

        public Builder addPlayer(String name, Symbol symbol, PlayerType playerType) {
            this.players.add(new Player(name, symbol, playerType));
            return this;
        }

        public Builder addWinningStrategy(WinningStrategy winningStrategy) {
            this.winningStrategies.add(winningStrategy);
            return this;
        }

        public Builder withBoardSize(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Game build() throws Exception {
            if (dimension <= 0 || dimension >= 10) {
                throw new Exception("Invalid board size");
            }
            for (Player player : players) {
                validatePlayer(player);
            }
            return new Game(players, dimension, winningStrategies);
        }

        public void validatePlayer(Player player) throws Exception {
            if(players.isEmpty()) {
                throw new Exception("No players assigned");
            }
            if(player.name == null || player.name.isEmpty()) {
                throw new Exception("Invalid player name");
            }
        }
    }
}
