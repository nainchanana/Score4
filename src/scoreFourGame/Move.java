/**
 * The Move class handles the logic for calculating the next best move for the computer player.
 * It looks through the possibility of which is the most suitable cell to place the bead into for ai and where the human player
 * can place their bead
 * Calculates the next best move for the computer player.
 * This class is created by nainpreet kaur, krishi patel, mayank chawda, raj vimal talaviya and samprat upadhyay
 */

package scoreFourGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Move {

    /**
     *
     * @param cells       The current state of the game board.
     * @param humanPlayer The human player.
     * @return The position of the next best move.
     */
    public static Position calculateTheNextBestMove(Cell[][][] cells, Player humanPlayer) {
        // Get the list of available moves on the board
        List<Cell> availableMoves = getAvailableMoves(cells);

        // Check if there are any winning moves for the human player
        for (Cell move : availableMoves) {
            int layer = move.getLayer();
            int row = move.getRow();
            int column = move.getColumn();

            // Simulate placing a bead at the current move
            cells[layer][row][column].setBeadInCell(humanPlayer);

            // Check if the human player wins after placing the bead
            if (WinningConditions.checkWinner(cells) != null) {
                // Reset the cell state and return the winning move
                cells[layer][row][column].setBeadInCell(null);
                return new Position(layer, row, column);
            }

            // Reset the cell state
            cells[layer][row][column].setBeadInCell(null);
        }

        // If no winning moves, randomly select a move
        if (!availableMoves.isEmpty()) {
            Random random = new Random();
            Cell randomMove = availableMoves.get(random.nextInt(availableMoves.size()));
            return new Position(randomMove.getLayer(), randomMove.getRow(), randomMove.getColumn());
        }

        // No available moves
        return null;
    }

    /**
     * Retrieves all available moves on the board where a bead can be placed.
     *
     * @param cells The current state of the game board.
     * @return A list of available moves.
     */
    private static List<Cell> getAvailableMoves(Cell[][][] cells) {
        List<Cell> availableMoves = new ArrayList<>();
        for (int column = 0; column < BoardPanel.TOTAL_COLUMNS; column++) {
            for (int layer = BoardPanel.TOTAL_LAYERS - 1; layer >= 0; layer--) {
                for (int row = 0; row < BoardPanel.TOTAL_ROWS; row++) {
                    if (cells[layer][row][column].getState() == GameState.Blank) {
                        availableMoves.add(cells[layer][row][column]);
                        break; // Move to the next column
                    }
                }
            }
        }
        return availableMoves;
    }
}
