package org.development.strategies.botPlayingStrategies;

import org.development.models.Board;
import org.development.models.Cell;
import org.development.models.CellState;

import java.util.HashMap;
import java.util.Map;

public class EasyBotPlayingStrategy implements BotPlayingStrategy{

    @Override
    public Cell makeMove(Board board) {
        Map<Integer, Integer[]> availableCells = new HashMap<>();
        int entry = 0;
        for(int row = 0; row < board.getSize(); row++) {
            for(int col = 0; col < board.getSize(); col++) {
                if(board.getBoard().get(row).get(col).getCellState() == CellState.FREE) {
                    availableCells.put(entry++, new Integer[]{row, col});
                }
            }
        }
        int getRandomSelection = (int) (Math.random() * (entry));
        Integer[] cellValues = availableCells.get(getRandomSelection);

        return new Cell(cellValues[0], cellValues[1]);
    }
}
