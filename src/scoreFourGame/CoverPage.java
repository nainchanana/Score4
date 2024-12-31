/**
 * The CoverPage class represents the introductory screen of the Score Four game, allowing users to enter their name and start or quit the game.
 * It features a background image, input field for the player's name, and buttons for starting the game and quitting.
 * Additionally, it handles user interactions such as clicking the start button or pressing Enter to initiate the game.
 *This class is created by nainpreet kaur, krishi patel, mayank chawda, raj vimal talaviya and samprat upadhyay
 */
package scoreFourGame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class CoverPage extends JPanel {
    private JTextField nameField;
    private JButton startButton;

    public CoverPage(ActionListener startButtonListener) {
        // Load the background image
        BufferedImage backgroundImage = null;
        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\kaurn\\OneDrive\\Desktop\\Score4 Game\\src\\scoreFourGame\\background for score 4 cover page[110].png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        setLayout(null); // We'll use absolute positioning

        // Create components
        JLabel playerNameLabel = createLabel("Player Name:", 100, 20, 200, 50, 30, Color.WHITE);
        nameField = createTextField(320, 20, 380, 30, 20);
        startButton = createButton("Start Game", 50, 700, 200, 50, 20, startButtonListener);
        JButton quitButton = createButton("Quit", 550, 700, 200, 50, 20, e -> System.exit(0));

        // Add ActionListener to nameField
        nameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Trigger startButton ActionListener when Enter key is pressed
                startButton.doClick();
            }
        });

        // Add components to panel
        add(playerNameLabel);
        add(nameField);
        add(startButton);
        add(quitButton);
    }

    // Override the paintComponent method to draw the background image
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage backgroundImage = null;
        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\kaurn\\OneDrive\\Desktop\\Score4 Game\\src\\scoreFourGame\\background for score 4 cover page[110].png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    public String getUserName() {
        return nameField.getText();
    }

    private JLabel createLabel(String text, int x, int y, int width, int height, int fontSize, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Garamond", Font.BOLD, fontSize)); // Using Garamond font
        label.setForeground(color);
        label.setBounds(x, y, width, height);
        return label;
    }

    private JTextField createTextField(int x, int y, int width, int height, int fontSize) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, width, height);
        textField.setFont(new Font("Garamond", Font.PLAIN, fontSize)); // Using Garamond font
        return textField;
    }

    private JButton createButton(String text, int x, int y, int width, int height, int fontSize, ActionListener listener) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setFont(new Font("Garamond", Font.PLAIN, fontSize)); // Using Garamond font
        button.addActionListener(listener);
        return button;
    }
}
