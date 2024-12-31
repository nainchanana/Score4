/**
 * Represents the game board panel where the game is played.
 * This class is created by nainpreet kaur, krishi patel, mayank chawda, raj vimal talaviya and samprat upadhyay
 */

package scoreFourGame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.*;


public class BoardPanel extends JPanel {

    // Player and cell-related variables
    protected static final int ORIGIN_X = 50;

    protected static final int ORIGIN_Y = 20;


    protected static int TOTAL_LAYERS = 4;


    protected static int TOTAL_ROWS = 4;


    protected static int TOTAL_COLUMNS = 4;

    protected static final int TOTAL_BEADS_TO_WIN = 4;

    protected static final int LAYER_GAP = 20;


    protected static final int CELL_WIDTH = 140;


    protected static final int CELL_HEIGHT = 30;


    protected static final double OFFSETX_3D = Math.tan(Math.toRadians(60)) * CELL_HEIGHT;


    private Player currentPlayer = null;

    private Cell cells[][][] = null;


    private boolean gameStarted = false;


    private Cell lastFocusedCell = null;


    // Scoreboard and overlay panel components
    private Controller game;
    private JLabel scoreboard;
    private int player1Score = 0; // Score variables with initialization
    private int player2Score = 0;
    private JLabel player1ScoreLabel; // Player 1 score label
    private JLabel player2ScoreLabel; // Player 2 score label
    private JPanel overlayPanel;

    /**
     * Constructs a new BoardPanel with the specified game controller.
     *
     * @param game The game controller.
     */
    public BoardPanel(Controller game) {

        this.game = game;
        // Set the background color
        setBackground(Color.BLACK);
        // Initialize player score labels
        player1ScoreLabel = new JLabel("Player 1: " + player1Score);
        player1ScoreLabel.setForeground(Color.WHITE); // Customize the appearance
        player1ScoreLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Customize the appearance

        player2ScoreLabel = new JLabel("Player 2: " + player2Score);
        player2ScoreLabel.setForeground(Color.WHITE); // Customize the appearance
        player2ScoreLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Customize the appearance

        // Create a panel to hold the player score labels with GridBagLayout
        JPanel scorePanel = new JPanel(new GridBagLayout());
        scorePanel.setOpaque(false); // Make the panel transparent

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.insets = new Insets(10, 10, 10, 10); // Padding

        gbc.gridx = 0;
        gbc.gridy = 0;
        scorePanel.add(player1ScoreLabel, gbc);

        gbc.gridy = 1;
        scorePanel.add(player2ScoreLabel, gbc);

        // Position the score panel at the top-right corner of the frame
        setLayout(new BorderLayout());
        add(scorePanel, BorderLayout.EAST);
        overlayPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Fill the panel with black color
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());
                // Display the winning message with animation effects
                // Implement animation effects here
            }
        };
        overlayPanel.setOpaque(false); // Make the overlay panel transparent
        add(overlayPanel); // Add the overlay panel to the board panel
        overlayPanel.setVisible(false); // Initially hide the overlay panel


        // Register a mouse click event handler
        setBorder(BorderFactory.createLineBorder(Color.black));
        addMouseListener(new MouseAdapter() {


            @Override
            public void mouseClicked(MouseEvent e) {
                if (gameStarted) {
                    for (int layer = 0; layer < TOTAL_LAYERS; layer++) {
                        for (int row = 0; row < TOTAL_ROWS; row++) {
                            for (int column = 0; column < TOTAL_COLUMNS; column++) {
                                if (cells[layer][row][column].isCoordinateInsideCell(e.getX(), e.getY())) {
                                    if (isCellAvailable(layer, row, column, true)) {
                                        // When the mouse cursor coordinate is within a cell, and the current cell is a valid
                                        // move (i.e., the current cell is BLANK, and a lower layer (same row and column) is not BLANK,
                                        // set the currentPlayer to the cell, and also set the state to "Occupied"
                                        cells[layer][row][column].setState(GameState.Occupied);
                                        cells[layer][row][column].setBeadInCell(currentPlayer);
                                        currentPlayer.play(cells);
                                        // Clear the lastFocusedCell if it is applicable.
                                        if (lastFocusedCell != null) {
                                            if (lastFocusedCell.getState() == GameState.Focused) {
                                                lastFocusedCell.setState(GameState.Blank);
                                            }
                                            lastFocusedCell = null;
                                        }
                                        repaint();

                                        // check if there is a winner after each move.
                                        if (checkWinner()) {
                                            return;
                                        }

                                        // Check if it is a tie.
                                        if (calculateNumOfBlankCells(cells) == 0) {
                                            game.setInfo("Draw game.");
                                            gameStarted = false;
                                            return;
                                        }

                                        // Switch players
                                        currentPlayer = currentPlayer.getTheOtherPlayer();
                                        if (currentPlayer.getPlayerType() == Player.PlayerType.AIPlayer) {
                                            // if the next player is a computer player
                                            game.setInfo("Player " + currentPlayer.getPlayerNo() + " (Computer player) is thinking . . .");
                                            game.repaint();
                                            Position coordinate = currentPlayer.play(cells);
                                            if (coordinate == null) {
                                                // This is a tie
                                                game.setInfo("Draw game.");
                                                gameStarted = false;
                                                return;
                                            } else {
                                                // Not a tie (maybe win, so need to check ...
                                                cells[coordinate.layer][coordinate.row][coordinate.column].setBeadInCell(currentPlayer);
                                                cells[coordinate.layer][coordinate.row][coordinate.column].setState(GameState.Occupied);
                                                // check if there is a winner after each move.
                                                if (checkWinner()) {
                                                    return;
                                                }
                                            }
                                            // switch player again (no need to check if the other player is a computer player (i.e., both players are computer player,
                                            // as it has been handled at the beginning of the game (in the "New Game" button click event handler).
                                            currentPlayer = currentPlayer.getTheOtherPlayer();
                                        }
                                        game.setInfo("Player " + currentPlayer.getPlayerNo() + " to place a bead . . .");
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });

        // Register a mouse move event handler
        addMouseMotionListener(new MouseMotionAdapter() {


            @Override
            public void mouseMoved(MouseEvent e) {
                if (gameStarted) {
                    // Check if a cell can be selected by passing the mouse cursor's coordinate
                    for (int layer = 0; layer < TOTAL_LAYERS; layer++) {
                        for (int row = 0; row < TOTAL_ROWS; row++) {
                            for (int column = 0; column < TOTAL_COLUMNS; column++) {
                                if (cells[layer][row][column].isCoordinateInsideCell(e.getX(), e.getY())) {
                                    if (isCellAvailable(layer, row, column, false)) {
                                        // When the mouse cursor coordinate is within a cell, and the current cell is a valid
                                        // move (i.e., the current cell is BLANK, and a lower layer (same row and column) is not BLANK,
                                        // mark state to "Focused".
                                        // No need to worry for frequently execute the logic for the same cell
                                        // as the isCellAvailable() method returns true only if the current cell is BLANK.
                                        cells[layer][row][column].setState(GameState.Focused);
                                        // Clear the lastFocusedCell (set the rest to BLANK).
                                        if (lastFocusedCell != null) {
                                            lastFocusedCell.setState(GameState.Blank);
                                        }
                                        lastFocusedCell = cells[layer][row][column];
                                        repaint();
                                    } else if ((lastFocusedCell != null) && (cells[layer][row][column] != lastFocusedCell)) {
                                        // Reset the lastFocusedCell's state to BLANK, ONLY IF it is not the same cell where the mouse cursor is now
                                        lastFocusedCell.setState(GameState.Blank);
                                        lastFocusedCell = null;
                                        repaint();
                                    }
                                    return;
                                }
                            }
                        }
                    }
                    // Reset the lastFocusedCell's state to BLANK. This is a catch-up run if the mouse is not in any available cell.
                    if (lastFocusedCell != null) {
                        lastFocusedCell.setState(GameState.Blank);
                        lastFocusedCell = null;
                        repaint();
                    }
                }
            }
        });
        initCells();
    }

    /**
     * Sets the size of the game board to the specified size.
     * This method updates the total number of layers, rows, and columns of the game board.
     *
     * @param size The size to set for the game board (number of layers, rows, and columns).
     */
    public static void setBoardSize(int size) {
        TOTAL_LAYERS = size;
        TOTAL_ROWS = size;
        TOTAL_COLUMNS = size;
    }

    /**
     * Sets the specified cell on the game board with the given player and state.
     *
     * @param layer The layer index of the cell.
     * @param row The row index of the cell.
     * @param column The column index of the cell.
     * @param player The player to set in the cell.
     * @param state The state to set for the cell.
     */
    public void setCell(int layer, int row, int column, Player player, GameState state) {
        this.cells[layer][row][column].setBeadInCell(player);
        cells[layer][row][column].setState(state);
    }

    /**
     * Sets the specified cell on the game board with the given player and state using a Position object.
     *
     * @param coordinate The Position object containing the layer, row, and column indices.
     * @param player The player to set in the cell.
     * @param state The state to set for the cell.
     */
    public void setCell(Position coordinate, Player player, GameState state) {
        setCell(coordinate.layer, coordinate.row, coordinate.column, player, state);
    }

    /**
     * Calculates the number of blank cells on the game board.
     *
     * @param cells The 3D array representing the game board cells.
     * @return The number of blank cells on the game board.
     */
    protected int calculateNumOfBlankCells(Cell[][][] cells) {
        int numOfBlankCells = 0;
        for (int layer = 0; layer < TOTAL_LAYERS; layer++) {
            for (int row = 0; row < TOTAL_ROWS; row++) {
                for (int column = 0; column < TOTAL_COLUMNS; column++) {
                    if (cells[layer][row][column].getState() == GameState.Blank) {
                        numOfBlankCells++;
                    }
                }
            }
        }
        return numOfBlankCells;
    }

    /**
     * Checks if the specified cell is available for placing a bead.
     *
     * @param layer The layer index of the cell.
     * @param row The row index of the cell.
     * @param column The column index of the cell.
     * @param isForPlacingBead Flag indicating if the cell is being checked for placing a bead.
     * @return True if the cell is available for placing a bead, false otherwise.
     */
    protected boolean isCellAvailable(int layer, int row, int column, boolean isForPlacingBead) {
        return ((cells[layer][row][column].getState() == GameState.Blank)
                || ((isForPlacingBead) && (cells[layer][row][column].getState() == GameState.Focused)))
                && ((layer == TOTAL_LAYERS - 1) || (cells[layer + 1][row][column].getState() != GameState.Blank));
    }

    /**
     * Checks if there is a winner on the game board.
     *
     * @return True if there is a winner, false otherwise.
     */
    public boolean checkWinner() {
        Cell[] winningCells = WinningConditions.checkWinner(cells);
        if (winningCells != null) {
            gameStarted = false;
            if (winningCells[0].getBeadInCell().getPlayerNo() == 1) {
                incrementPlayer1Score();
            } else {
                incrementPlayer2Score();
            }
            game.setInfo(winningCells[0].getBeadInCell().toString() + " has won in " + winningCells[0].getBeadInCell().getMoveCount() + " moves.");
            // Call showWinnerScreen method to display the winning message
            showWinnerScreen(winningCells[0].getBeadInCell());
            return true;
        } else {
            return false;
        }
    }



    public void initCells() {
        this.cells = new Cell[TOTAL_LAYERS][TOTAL_ROWS][TOTAL_COLUMNS];
        for (int layer = 0; layer < TOTAL_LAYERS; layer++) {
            for (int row = 0; row < TOTAL_ROWS; row++) {
                for (int column = 0; column < TOTAL_COLUMNS; column++) {
                    cells[layer][row][column] = new Cell(layer, row, column);
                }
            }
        }
        repaint();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(
                ORIGIN_X + (int) OFFSETX_3D * TOTAL_COLUMNS + TOTAL_COLUMNS * CELL_WIDTH,
                ORIGIN_Y + (LAYER_GAP + TOTAL_ROWS * CELL_HEIGHT) * TOTAL_LAYERS
        );
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw cells
        for (int layer = 0; layer < TOTAL_LAYERS; layer++) {
            for (int row = 0; row < TOTAL_ROWS; row++) {
                for (int column = 0; column < TOTAL_COLUMNS; column++) {
                    cells[layer][row][column].drawCell(g, currentPlayer);
                }
            }
        }

        // Draw layer borders
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE); // Set the color of the borders
        g2d.setStroke(new BasicStroke(3)); // Set the thickness of the borders

        // Draw borders for the layers
        for (int layer = 0; layer < TOTAL_LAYERS; layer++) {
            int x = ORIGIN_X;
            int y = ORIGIN_Y + (LAYER_GAP + CELL_HEIGHT * TOTAL_ROWS) * layer;
            int width = (int) OFFSETX_3D * TOTAL_COLUMNS + CELL_WIDTH * TOTAL_COLUMNS;
            int height = LAYER_GAP + CELL_HEIGHT * TOTAL_ROWS;
            g2d.drawRect(x, y, width, height);
        }

        // Draw last placed bead
        if (lastFocusedCell != null && lastFocusedCell.getState() == GameState.Focused) {
            lastFocusedCell.drawCell(g, currentPlayer);
        }
    }

    public void showWinnerScreen(Player winningPlayer) {
        overlayPanel.setBounds(0, 0, getWidth(), getHeight()); // Ensure overlay covers entire board
        overlayPanel.setVisible(true); // Show the overlay panel
        repaint(); // Repaint to ensure cells are drawn before the overlay panel
        overlayPanel.repaint(); // Repaint the overlay panel to trigger its paintComponent method


        // After 2 seconds, make the entire screen black and display "Player 1 has won"
        overlayPanel.setBackground(Color.BLACK);
        overlayPanel.setForeground(Color.WHITE);
        overlayPanel.setLayout(new BorderLayout());
        JLabel winnerLabel = new JLabel(winningPlayer.toString() + " has won");
        winnerLabel.setFont(new Font("Arial", Font.BOLD, 30));
        winnerLabel.setHorizontalAlignment(JLabel.CENTER);
        overlayPanel.removeAll();
        overlayPanel.add(winnerLabel, BorderLayout.CENTER);
        overlayPanel.revalidate();
    }

    /**
     * Resets the game by clearing scores, initializing cells, hiding the overlay panel, and repainting the panel.
     */
    private void resetGame() {
        // Reset scores
        player1Score = 0;
        player2Score = 0;
        updateScoreboard();

        // Initialize cells
        initCells();

        // Hide overlay panel
        overlayPanel.setVisible(false);

        // Repaint panel
        repaint();
    }

    /**
     * Updates the scoreboard to display the current scores of both players.
     */
    public void updateScoreboard() {
        scoreboard.setText("Player 1: " + player1Score + "   Player 2: " + player2Score);
    }

    /**
     * Increments Player 1's score by 1 and updates the corresponding score label.
     */
    public void incrementPlayer1Score() {
        player1Score++;
        player1ScoreLabel.setText("Player 1: " + player1Score);
    }

    /**
     * Increments Player 2's score by 1 and updates the corresponding score label.
     */
    public void incrementPlayer2Score() {
        player2Score++;
        player2ScoreLabel.setText("Player 2: " + player2Score);
    }

    /**
     * Returns the current player.
     *
     * @return The current player.
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Sets the current player.
     *
     * @param currentPlayer The player to set as the current player.
     */
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * Checks if the game has started.
     *
     * @return True if the game has started, false otherwise.
     */
    public boolean isGameStarted() {
        return gameStarted;
    }

    /**
     * Sets the status of the game (started or not started).
     *
     * @param gameStarted True to indicate that the game has started, false otherwise.
     */
    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    /**
     * Gets the 3D array of cells representing the game board.
     *
     * @return The 3D array of cells representing the game board.
     */
    public Cell[][][] getCells() {
        return cells;
    }
}