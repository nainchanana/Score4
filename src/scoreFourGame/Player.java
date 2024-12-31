/**
 * The Player class represents a player in the Score Four game.
 * It contains information about the player's type, number, move count,
 * and methods for managing player actions.
 *  This class is created by nainpreet kaur, krishi patel, mayank chawda, raj vimal talaviya and samprat upadhyay
 */


package scoreFourGame;

public class Player {

    // Enum defining player types
    public enum PlayerType {
        HumanPlayer, // Represents a human player
        AIPlayer;    // Represents an AI player
    }

    // Player variables
    private final PlayerType playerType; // Type of player (Human or AI)
    private final int playerNo; // Player number
    private int moveCount = 0; // Count of moves made by the player
    private Player theOtherPlayer = null; // Reference to the opponent player

    // Constructor for Human Player
    public Player(int playerNo) {
        this.playerNo = playerNo;
        this.playerType = PlayerType.HumanPlayer;
    }

    // Constructor for AI Player
    public Player(int playerNo, PlayerType playerType) {
        this.playerNo = playerNo;
        this.playerType = playerType;
    }

    // Getter for move count
    public int getMoveCount() {
        return moveCount;
    }

    // Setter for move count
    public void setMoveCount(int moveCount) {
        this.moveCount = moveCount;
    }

    // Getter for player number
    public int getPlayerNo() {
        return playerNo;
    }

    // Getter for player type
    public PlayerType getPlayerType() {
        return playerType;
    }

    // Getter for opponent player
    public Player getTheOtherPlayer() {
        return theOtherPlayer;
    }

    // Setter for opponent player
    public void setTheOtherPlayer(Player theOtherPlayer) {
        this.theOtherPlayer = theOtherPlayer;
    }

    // Method to set opponent player and establish mutual reference
    public void setOpponent(Player theOtherPlayer) {
        setTheOtherPlayer(theOtherPlayer);
        theOtherPlayer.setTheOtherPlayer(this);
    }

    // Method to increment move count
    public void incrementMoveCount() {
        this.moveCount++;
    }

    // Method to provide a string representation of the player
    @Override
    public String toString() {
        return (this.playerType == PlayerType.HumanPlayer ? "Human Player #" : "AI Player #") + Integer.toString(this.playerNo);
    }

    // Method to clone the player object
    @Override
    protected Player clone() {
        return new Player(this.playerNo, this.playerType);
    }

    // Method for player action, determining the next move
    public Position play(Cell[][][] cells) {
        this.incrementMoveCount();
        if (this.playerType == PlayerType.AIPlayer) {
            // Algorithm for determining the optimum move
            return Move.calculateTheNextBestMove(cells, this);
        } else {
            // For Human Player, logic is handled elsewhere
            return null;
        }
    }
}
