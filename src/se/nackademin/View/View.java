package se.nackademin.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class View extends JFrame {

    private int gameSize;

    private JPanel gamePanel = new JPanel();
    private JLabel playerScore = new JLabel();
    private JPanel scorePanel = new JPanel();
    JTextArea playingField = new JTextArea(gameSize,gameSize);

    public View (int gameSize) {
        this.gameSize = gameSize;
        this.setSize(500,400);
        this.add(gamePanel);
        setVisible(true);
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

    public void setPlayingField (String s) {
        playingField.setText(s);
    }


    public void setPlayerScore (int score) {
        playerScore.setText("Score: " + score);
    }


}
