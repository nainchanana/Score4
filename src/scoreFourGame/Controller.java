/**
 * The Controller class manages the game's user interface and controls the game flow.
 * This class is created by nainpreet kaur, krishi patel, mayank chawda, raj vimal talaviya and samprat upadhyay
 */

package scoreFourGame;

import javax.swing.*;
import java.awt.*;

public class Controller extends JFrame {
    private BoardPanel gameMainPanel;
    private JLabel infoLabel;
    private CoverPage coverPage;

    private Player player1;
    private Player player2;

    /**
     * Constructor to initialize the UI and players.
     */
    public Controller() {
        initializeUI();
        initializePlayers();
    }

    /**
     * Initializes the user interface by setting up the frame, cover page, control panel, and buttons.
     */
    private void initializeUI() {
        setTitle("SCORE FOUR GAME BY TEAM GRIZZLY CLAW");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Create the cover page
        coverPage = new CoverPage(e -> {
            String userName = coverPage.getUserName();
            remove(coverPage); // Remove the cover page once the user clicks on the start game button
            startGame(userName);
        });
        add(coverPage, BorderLayout.CENTER);

        // Create the control panel for buttons and information label
        JPanel controlPanel = new JPanel(new BorderLayout());
        controlPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        infoLabel = new JLabel("Welcome to Score Four Game!");
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        controlPanel.add(infoLabel, BorderLayout.NORTH);

        // Create buttons panel with Reset and Exit buttons
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));

        JButton resetButton = createButton("Reset");
        resetButton.addActionListener(e -> resetGame()); // Add action listener to the reset button
        buttonsPanel.add(resetButton);

        JButton exitButton = createButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        buttonsPanel.add(exitButton);

        controlPanel.add(buttonsPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        pack(); // Adjust frame size
        setLocationRelativeTo(null); // Center the frame on the screen
    }

    /**
     * Creates a button with customized appearance and behavior.
     * @param text The text to display on the button.
     * @return The created JButton object.
     */
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(120, 40));
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Button customization for background, border, and hover effect
        button.setBackground(new Color(255, 102, 102)); // Light red background color
        button.setForeground(Color.WHITE); // White text color
        button.setBorder(BorderFactory.createLineBorder(new Color(255, 51, 51), 2)); // Red border with 2px thickness
        button.setOpaque(true);
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(255, 51, 51)); // Darker red on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(255, 102, 102)); // Restore original color on exit
            }
        });

        return button;
    }

    /**
     * Initializes player objects for Player 1 and Player 2.
     */
    private void initializePlayers() {
        player1 = new Player(1, Player.PlayerType.HumanPlayer);
        player2 = new Player(2, Player.PlayerType.AIPlayer);
        player1.setOpponent(player2); // Set opponents
    }

    /**
     * Starts the game with the specified user name.
     * @param userName The user name entered by the player.
     */
    public void startGame(String userName) {
        gameMainPanel = new BoardPanel(this);
        add(gameMainPanel, BorderLayout.CENTER);
        pack();

        startNewGame(player1); // Start the game with Player 1
    }

    /**
     * Starts a new game with the specified player.
     * @param player The player to start the game with.
     */
    public void startNewGame(Player player) {
        gameMainPanel.initCells();
        gameMainPanel.setCurrentPlayer(player);
        gameMainPanel.setGameStarted(true);
        if (player == player1) {
            setInfo("Player 1's turn");
        } else {
            setInfo("Player 2's turn");
        }
    }

    /**
     * Resets the game by initializing cells and displaying a welcome message.
     */
    public void resetGame() {
        gameMainPanel.initCells();
        gameMainPanel.setGameStarted(true);
        setInfo("Welcome to Score Four Game!");
    }

    /**
     * Sets the information message on the UI.
     * @param message The message to display.
     */
    public void setInfo(String message) {
        infoLabel.setText(message);
    }
}
