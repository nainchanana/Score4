Score4 is a Java-based adaptation of the classic “Connect Four” board game, reimagined with a 3D twist, an intuitive Swing GUI, and a built-in AI opponent. Through careful architecture and modular design, it offers both casual fun and a clean foundation for further development.

Contents
Overview
Built With
Setup & Installation
Usage
Roadmap
Contributing
License
Contact
Acknowledgments
Overview
Score4 introduces a layered grid where players drop tokens, attempting to align four in a row (horizontally, vertically, or diagonally). One user plays as Player 1 (red), while Player 2 can be controlled by an AI that determines “best moves” to keep the game challenging.

Key highlights:

3D Board Layout: Multiple layers, rows, and columns.
Mouse Interaction: Hover effects and click detection for selecting valid cells.
AI Integration: Basic algorithm to block or threaten moves, offering single-player excitement.
Score Tracking: Real-time scoring and a winner overlay.
Modularity: Classes like BoardPanel, Cell, Controller, and Player are each responsible for distinct parts of the game.
Built With
Java (JDK 8 or later)
Core language for all game logic and GUI components.
Swing
Used to build the interactive UI, including windows, buttons, and mouse detection.
(Feel free to list any additional frameworks or libraries if applicable.)

Setup & Installation

Clone the Repository https://github.com/nainchanana/Score4

bash
Copy code
git clone https://github.com/nainchanana/Score4
Open in IntelliJ (or Your Preferred IDE)

Select File → Open and locate the Score4 folder.
Make sure your Project SDK is set to a valid Java version (e.g., Java 8+).
Compile & Run

If using IntelliJ, locate the Main class inside src/scoreFourGame and run it directly.
From the command line (if you prefer):
bash
Copy code
cd Score4/src
javac scoreFourGame/*.java
java scoreFourGame.Main
Optional: Update the Remote
If you forked this code, point the repository to your own GitHub path:

bash
Copy code
git remote set-url origin https://github.com/nainchanana/Score4

Usage
Launch the Game: After running Main, a cover page appears, prompting for a player name.
Start Game: Player 1 is human (red tokens). Player 2 is an AI (blue tokens).
Objective: Drop tokens to form a line of four. Use strategy to block your opponent and create multiple potential wins.
Reset or Exit: The bottom panel features buttons to reset the current board or close the application.
Tip: Modify BoardPanel constants (like TOTAL_LAYERS, TOTAL_ROWS, TOTAL_COLUMNS) to experiment with different board dimensions.

Roadmap
AI Difficulty Levels: Introduce multiple AI strategies (e.g., easy, medium, hard).
Online Multiplayer: Explore networking for player vs. player matches across the internet.
Visual Themes: Offer custom backgrounds or token designs.
Replay Mode: Track moves and allow players to review an entire match.
Check the Issues section to see what’s in progress or propose new ideas.

Contributing
Contributions are always welcome! Here’s how you can help:

Fork the Repo
Create your own copy on GitHub.
Create a New Branch

Contact
Nainpreet Kaur
Email: kaurnainpreet22@gmail.com
GitHub: @nainchanana
Thank you for checking out Score4! Enjoy the game, and don’t hesitate to submit a pull request or open an issue if you have suggestions or run into any problems.







