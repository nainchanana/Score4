/**
 * This class is created by nainpreet kaur, krishi patel, mayank chawda, raj vimal talaviya and samprat upadhyay
 */

package scoreFourGame;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Controller game = new Controller();
            game.setVisible(true);
        });
    }
}

