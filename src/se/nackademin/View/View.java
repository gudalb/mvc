package se.nackademin.View;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Observable;
import java.util.Observer;

public class View extends JFrame implements PropertyChangeListener {

    private int gameSize;

    private JPanel gamePanel = new JPanel();
    private JLabel playerScore = new JLabel();
    private JPanel scorePanel = new JPanel();
    JTextArea playingField = new JTextArea(gameSize,gameSize);

    public View (int gameSize) {
        this.gameSize = gameSize;
        this.setSize(500,400);
        this.add(gamePanel);
        this.setVisible(true);
        playerScore.setText("Score: ");
        playingField.setEditable(false);
        gamePanel.add(playingField);
        scorePanel.add(playerScore);
        gamePanel.add(scorePanel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

    }

    public void showGameOver (String message) {
        JOptionPane.showMessageDialog(this, message);
    }
    public void setPlayerScore (int score) {
        playerScore.setText("Score: " + score);
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("paintGame")) {
            playingField.setText((String) evt.getNewValue());
        }

        if(evt.getPropertyName().equals("collision")) {
            showGameOver("Game over");
        }

        if(evt.getPropertyName().equals("playerScore")) {
            setPlayerScore((Integer) evt.getNewValue());
        }

    }
}
