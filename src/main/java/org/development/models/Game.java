package org.development.models;

import lombok.Data;
import org.development.strategies.winningStrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

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

    public void displayBoard() {
        gameBoard.displayBoard();
    }

    public void makeMove() {
        Player currentPlayer = players.get(currentMovePlayerIndex);
        Cell cellToMove = currentPlayer.makeMove(gameBoard);

        if(isInvalidCell(cellToMove) || !getGameBoardCell(cellToMove.row, cellToMove.col).cellState.equals(CellState.FREE)) {
            System.out.println("Invalid Move");
            return;
        }

        cellToMove.player = currentPlayer;
        cellToMove.cellState = CellState.PLACED;

        gameBoard.board.get(cellToMove.row).set(cellToMove.col, cellToMove);
        moves.add(new Move(currentPlayer, cellToMove));
        currentMovePlayerIndex += 1;
        currentMovePlayerIndex %= players.size();
    }

    private Cell getGameBoardCell(int row, int col) {
        return gameBoard.board.get(row).get(col);
    }

    public Player getCurrentPlayerTurn() {
        return players.get(currentMovePlayerIndex);
    }

    public void checkGameStatus() {
        if(moves.isEmpty()) {
            return;
        }
        AtomicBoolean isWon = new AtomicBoolean(false);
        winningStrategies.forEach(winningStrategy -> {
            if(winningStrategy.checkWinner(gameBoard, moves.get(moves.size()-1))) {
                isWon.set(true);
                return;
            }
        });
        if(isWon.get()) {
            gameStatus = GameStatus.ENDED;
            winner = moves.get(moves.size()-1).getPlayer();
        }
        if(moves.size() == gameBoard.size * gameBoard.size) {
            gameStatus = GameStatus.DRAW;
        }
    }

    public boolean isInvalidCell(Cell cell) {
        return cell.row < 0 || cell.col < 0 || cell.row > gameBoard.size - 1 || cell.col > gameBoard.size - 1;
    }

    public static Builder getBuilder() {
        return new Builder();
    }

    public static class Builder {
        private List<Player> players;
        int dimension;
        private List<WinningStrategy> winningStrategies;

        private Builder() {}

        public Builder addPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder addWinningStrategies(List<WinningStrategy> winningStrategies) {
            this.winningStrategies = winningStrategies;
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
            if(players.isEmpty() || (players.size() + 1) != dimension) {
                throw new Exception("Invalid number of players");
            }
            return new Game(players, dimension, winningStrategies);
        }

        public void validatePlayer(Player player) throws Exception {
            if(players.isEmpty()) {
                throw new Exception("No players assigned");
            }
            if(player.getName() == null || player.getName().isEmpty()) {
                throw new Exception("Invalid player name");
            }
        }
    }
}
