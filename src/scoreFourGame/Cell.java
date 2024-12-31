/**
 * The Cell class models individual cells on the Score Four game board, tracking their position, occupancy, and state.
 * It offers methods for drawing cells and checking coordinate containment within a cell.
 * This class is created by nainpreet kaur, krishi patel, mayank chawda, raj vimal talaviya and samprat upadhyay
 */

package scoreFourGame;

import java.awt.Color;
import java.awt.Graphics;

// Represents a single cell in the game board
public class Cell {
    private final int layer; // The layer of the cell in the 3D grid
    private final int row; // The row of the cell
    private final int column; // The column of the cell
    private int cornerX[]; // X coordinates of the cell corners
    private int cornerY[]; // Y coordinates of the cell corners
    private Player beadInCell; // Player occupying the cell
    private GameState state = GameState.Blank; // State of the cell (Blank, Occupied, etc.)

    // Constructor to initialize a cell without a bead
    public Cell(int layer, int row, int column) {
        this.layer = layer;
        this.row = row;
        this.column = column;

        // Calculate the coordinates of the cell corners
        int cellOriginX = BoardPanel.ORIGIN_X + (int) BoardPanel.OFFSETX_3D * (BoardPanel.TOTAL_ROWS - row) + column * BoardPanel.CELL_WIDTH;
        int cellOriginY = BoardPanel.ORIGIN_Y + BoardPanel.CELL_HEIGHT * row + (BoardPanel.LAYER_GAP + BoardPanel.TOTAL_ROWS * BoardPanel.CELL_HEIGHT) * layer;
        cornerX = new int[]{cellOriginX, cellOriginX + BoardPanel.CELL_WIDTH, cellOriginX + BoardPanel.CELL_WIDTH - (int) BoardPanel.OFFSETX_3D, cellOriginX - (int) BoardPanel.OFFSETX_3D};
        cornerY = new int[]{cellOriginY, cellOriginY, cellOriginY + BoardPanel.CELL_HEIGHT, cellOriginY + BoardPanel.CELL_HEIGHT};
    }

    // Constructor to initialize a cell with a bead and state
    public Cell(int layer, int row, int column, Player beadInCell, GameState state) {
        this(layer, row, column);
        this.beadInCell = beadInCell;
        this.state = state;
    }

    // Method to clone the cell
    protected Cell clone() {
        Player clonedBeadInCell = null;
        if (this.beadInCell != null) {
            clonedBeadInCell = this.beadInCell.clone();
        }
        return new Cell(this.layer, this.row, this.column, clonedBeadInCell, this.state);
    }

    // Getter method for the player occupying the cell
    public Player getBeadInCell() {
        return beadInCell;
    }

    // Setter method for the player occupying the cell
    public void setBeadInCell(Player beadInCell) {
        this.beadInCell = beadInCell;
    }

    // Getter method for the state of the cell
    public GameState getState() {
        return state;
    }

    // Setter method for the state of the cell
    public void setState(GameState state) {
        this.state = state;
    }

    // Getter method for the layer of the cell
    public int getLayer() {
        return layer;
    }

    // Getter method for the row of the cell
    public int getRow() {
        return row;
    }

    // Getter method for the column of the cell
    public int getColumn() {
        return column;
    }

    // Getter method for the X coordinates of the cell corners
    public int[] getCornerX() {
        return cornerX;
    }

    // Getter method for the Y coordinates of the cell corners
    public int[] getCornerY() {
        return cornerY;
    }

    // Method to draw the cell on the graphics object
    public void drawCell(Graphics g, Player player) {
        // Draw the cell outline
        g.setColor(Color.WHITE);
        g.drawPolygon(cornerX, cornerY, 4);

        // Draw bead if the cell is occupied
        if (state == GameState.Occupied) {
            // Set color based on the player
            Color beadColor = (beadInCell.getPlayerNo() == 1) ? Color.RED : Color.BLUE;

            // Calculate center coordinates of the cell
            int centerX = cornerX[0] + (cornerX[1] - cornerX[0]) / 2;
            int centerY = cornerY[0] + (cornerY[2] - cornerY[0]) / 2;

            // Draw the bead (circle)
            int radius = Math.min(BoardPanel.CELL_WIDTH, BoardPanel.CELL_HEIGHT) / 3;
            g.setColor(beadColor);
            g.fillOval(centerX - radius, centerY - radius, 2 * radius, 2 * radius);
        }
    }

    // Method to check if a given coordinate is inside the cell
    public boolean isCoordinateInsideCell(int x, int y) {
        // Check if the point is within the y-range of the cell
        if (y < Math.min(cornerY[0], cornerY[3]) || y > Math.max(cornerY[0], cornerY[3])) {
            return false;
        }

        // Calculate the x-coordinate of the cell edge at the given y-coordinate
        double xEdge = cornerX[0] + (double) (y - cornerY[0]) * (cornerX[3] - cornerX[0]) / (cornerY[3] - cornerY[0]);

        // Check if the point is to the right of the left edge and to the left of the right edge
        return x >= xEdge && x <= xEdge + (cornerX[1] - cornerX[0]);
    }

}
