/**
 * The WinningConditions class defines methods to check for winning conditions in a Score Four game by examining lines of beads in all directions from each cell.
 * If a winning condition is found, it returns the winning cells; otherwise, it returns null.
 * The class also includes a method to mark winning cells and repaint the game panel upon finding a winner.
 *This class is created by nainpreet kaur, krishi patel, mayank chawda, raj vimal talaviya and samprat upadhyay
 */

package scoreFourGame;

public class WinningConditions {

    // Method to check for a winning condition in the game
    public static Cell[] checkWinner(Cell[][][] cells) {
        // Iterate through each cell in the grid
        for (int layer = 0; layer < BoardPanel.TOTAL_LAYERS; layer++) {
            for (int row = 0; row < BoardPanel.TOTAL_ROWS; row++) {
                for (int column = 0; column < BoardPanel.TOTAL_COLUMNS; column++) {
                    // Check for winning lines starting from each cell
                    Cell[] winningCells = checkWinningLinesInAllDirections(cells, layer, row, column);
                    if (winningCells != null) {
                        return winningCells; // Return the winning cells if found
                    }
                }
            }
        }
        return null; // Return null if no winning condition is found
    }

    // Method to check for a winning condition and repaint the game panel if a winner is found
    public static void checkWinnerAndRepaint(BoardPanel gameMainPanel, Cell[][][] cells) {
        Cell[] winningCells = checkWinner(cells);
        if (winningCells != null) {
            // Handle winning condition by marking winning cells and repainting the panel
            for (Cell cell : winningCells) {
                cell.setState(GameState.Won);
            }
            gameMainPanel.repaint();
        }
    }

    // Method to check winning lines in all directions from a given cell
    private static Cell[] checkWinningLinesInAllDirections(Cell[][][] cells, int layer, int row, int column) {
        // Get the player who placed the first bead in the line from the current cell
        Player firstPlayerPlacedBeadInTheLine = cells[layer][row][column].getBeadInCell();
        // Iterate through all possible directions from the current cell
        for (int layerDirectionFactor = -1; layerDirectionFactor <= 1; layerDirectionFactor++) {
            for (int rowDirectionFactor = -1; rowDirectionFactor <= 1; rowDirectionFactor++) {
                for (int columnDirectionFactor = -1; columnDirectionFactor <= 1; columnDirectionFactor++) {
                    // Skip checking the current cell itself
                    if ((layerDirectionFactor != 0) || (rowDirectionFactor != 0) || (columnDirectionFactor != 0)) {
                        Cell[] winningCells = new Cell[BoardPanel.TOTAL_BEADS_TO_WIN];
                        int totalSameBeadsInTheLine = 1; // Initialize count of same beads in the line
                        winningCells[0] = cells[layer][row][column]; // Add the current cell to the winning cells array
                        if (firstPlayerPlacedBeadInTheLine != null) {
                            boolean keepLooking = true;
                            // Continue checking in the current direction until the line is complete or interrupted
                            for (int k = 1; k < BoardPanel.TOTAL_BEADS_TO_WIN && keepLooking; k++) {
                                int newLayer = layer + layerDirectionFactor * k;
                                int newRow = row + rowDirectionFactor * k;
                                int newColumn = column + columnDirectionFactor * k;
                                // Check if the new cell coordinates are within the grid boundaries
                                if ((newLayer < 0) || (newLayer >= BoardPanel.TOTAL_LAYERS) ||
                                        (newRow < 0) || (newRow >= BoardPanel.TOTAL_ROWS) ||
                                        (newColumn < 0) || (newColumn >= BoardPanel.TOTAL_COLUMNS)) {
                                    keepLooking = false; // Stop searching if out of bounds
                                } else {
                                    // Get the player who placed the bead in the new cell
                                    Player nextPlayerPlacedBeadInTheLine = cells[newLayer][newRow][newColumn].getBeadInCell();
                                    // Stop searching if the next cell is empty or belongs to a different player
                                    if ((nextPlayerPlacedBeadInTheLine == null) || (nextPlayerPlacedBeadInTheLine != firstPlayerPlacedBeadInTheLine)) {
                                        keepLooking = false;
                                    } else {
                                        // Add the next cell to the winning cells array and increment the count
                                        winningCells[totalSameBeadsInTheLine] = cells[layer + layerDirectionFactor * k][row + rowDirectionFactor * k][column + columnDirectionFactor * k];
                                        totalSameBeadsInTheLine++;
                                    }
                                }
                            }
                        }
                        // If the line contains enough beads to win, return the winning cells
                        if (totalSameBeadsInTheLine == BoardPanel.TOTAL_BEADS_TO_WIN) {
                            return winningCells;
                        }
                    }
                }
            }
        }
        return null; // Return null if no winning condition is found
    }
}
