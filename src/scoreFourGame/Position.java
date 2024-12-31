/**
 * The Position class represents a position on the game board.
 * It initializes the layer, row, and column of a cell.
 *This class is created by nainpreet kaur, krishi patel, mayank chawda, raj vimal talaviya and samprat upadhyay
 */

package scoreFourGame;
public class Position {

    public int layer;   // Represents the layer of the position
    public int row;     // Represents the row of the position
    public int column;  // Represents the column of the position

    /**
     * Constructs a Position object with the specified layer, row, and column.
     * @param layer the layer of the position
     * @param row the row of the position
     * @param column the column of the position
     */
    public Position(int layer, int row, int column) {
        this.layer = layer;
        this.row = row;
        this.column = column;
    }
}
