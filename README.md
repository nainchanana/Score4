Score4
A modernized implementation of the classic “Connect Four” board game, Score4 combines timeless gameplay with clean, modular code. Players take turns dropping tokens into a vertically suspended grid, aiming to align four tokens before their opponent does. With a user-friendly graphical interface built using Java Swing and robust in-game logic, Score4 balances straightforward rules with strategic depth.

Table of Contents
Features
Project Architecture
Getting Started
Running the Game
How to Play
Contributing
License
Credits
Features
Clean, Modular Code
The project follows best practices by separating concerns into logical components such as the board, controller, player classes, and UI elements.

Graphical Interface (Java Swing)
A user-friendly interface built with Swing, featuring a cover page, a dynamic board panel, and an overlay that announces the winner.

AI Opponent
A built-in AI (Player 2 by default) that calculates the “next best move,” offering a challenge to human players. You can tweak or replace this logic as needed.

Scalable Board Size & Rules
The code is written to handle different grid sizes and winning conditions with minimal refactoring.

Scoring & Replay Features
Real-time scoring for Player 1 and Player 2, with easy extension points for future replay implementations.

Well-Documented Classes
Clear JavaDocs and comments throughout the code ensure readability and maintainability.

Project Architecture
BoardPanel

Handles the graphical representation of the 3D board layers, rows, and columns.
Detects mouse clicks/movements to place or highlight tokens.
Manages score updates and winner overlays.
Cell

Represents each playable cell on the 3D board, storing state (Blank, Occupied, Focused, Won) and which player’s token occupies it.
Controller

Manages the primary game window (JFrame) and transitions between cover page and main board.
Handles core logic (start game, reset, and exit).
CoverPage

The intro screen, prompting the user’s name.
Allows players to start or quit the game.
GameState

An enum defining states of each cell: Blank, Occupied, Focused, or Won.
Move

Contains logic for determining the AI’s optimal move.
Checks if the human player is about to win, allowing the AI to block them, or chooses a random valid spot otherwise.
Player

Represents either a Human or an AI player, tracking move counts, player numbers, and the logic to decide moves.
Position

Simple class holding layer, row, and column to identify a cell in the 3D grid.
WinningConditions

Examines lines of tokens to detect a winning set of four consecutive pieces in any direction.
Getting Started
Clone This Repository

bash
Copy code
git clone https://github.com/YourUsername/score4.git
Open in IntelliJ (or any Java IDE)

Select File → Open and navigate to the project folder.
Ensure your Java SDK is properly configured.
Project Structure

The primary source code resides in src/scoreFourGame/.
Each class is documented, making it easy to locate relevant functionality.
Running the Game
Compile and Run
If using IntelliJ: Click the green “Run” button next to Main or use the run configuration.
If using the command line:
bash
Copy code
cd src
javac scoreFourGame/*.java
java scoreFourGame.Main
Game Window
A cover page will appear, prompting for your name.
Click Start Game to launch the main Score4 board.
Use the Reset button to clear the board and begin a new match, or Exit to quit.
How to Play
Object of the Game

Drop tokens (red for Player 1, blue for Player 2) into columns, aiming to connect four in any direction (horizontal, vertical, or diagonal).
Turns

Players alternate placing a token each turn.
Player 1 is controlled by the user; Player 2 is an AI by default.
Winning

If four consecutive tokens of the same color align, the overlay announces the winner, and scoring updates accordingly.
Tips

Block your opponent’s attempts to connect four.
Look for ways to create multiple threats at once.
Contributing
Contributions are welcome! Here’s how you can help:

Fork the repository and clone it locally.
Create a Feature Branch for your changes (e.g., feature/improve-ai).
Commit and Push your work to your branch.
Open a Pull Request against the main branch.
Provide a clear description of your proposed changes and any relevant context.
License
This project is licensed under an open license (e.g., MIT License). Feel free to modify, distribute, or use this code in your own projects. Refer to the LICENSE file (if provided) for more details.

Credits
Team Grizzly Claw:

Nainpreet Kaur
Krishi Patel
Mayank Chawda
Raj Vimal Talaviya
Samprat Upadhyay
Their collective effort in conceptualizing and implementing Score4 is reflected in this source code.

Thank you for checking out Score4. We hope this game provides both an entertaining experience and a hands-on guide to clean, maintainable Java software design! If you have any questions, issues, or suggestions, feel free to open an Issue or reach out through a Pull Request. Enjoy the game!
