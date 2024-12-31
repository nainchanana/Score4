/**
 * The GameState enum represents the possible states of a cell on the game board.(whether it it blank, occupied, focussed and won)
 * This class is created by nainpreet kaur, krishi patel, mayank chawda, raj vimal talaviya and samprat upadhyay
 */

package scoreFourGame;
public enum GameState {
    // Represents an empty cell
    Blank,
    // Represents a cell occupied by a player's bead
    Occupied,
    // Represents a cell that is currently focused (e.g., by the mouse cursor)
    Focused,
    // Represents a cell that is part of a winning combination
    Won;
}
