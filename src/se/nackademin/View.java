package se.nackademin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class View extends JFrame implements KeyListener{

    int gameSize;
    String moveDirection = "up";

    JPanel gamePanel = new JPanel();
    JLabel playerScore = new JLabel();
    JPanel scorePanel = new JPanel();

    JTextArea playingField = new JTextArea(gameSize,gameSize);

    public View (int gameSize) {
        this.gameSize = gameSize;

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        this.setSize(500,400);
        this.add(gamePanel);


        playerScore.setText("Score: ");
        playingField.setEditable(false);
        gamePanel.add(playingField);
        scorePanel.add(playerScore);
        gamePanel.add(scorePanel);
        this.setVisible(true);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

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


    @Override
    public void keyPressed(KeyEvent e) {
        int keyValue = e.getKeyCode();

        switch (keyValue) {
            case KeyEvent.VK_UP:
                if(!moveDirection.equals("down"))
                    setMoveDirection("up");
                break;
            case KeyEvent.VK_DOWN:
                if(!moveDirection.equals("up"))
                    setMoveDirection("down");
                break;
            case KeyEvent.VK_LEFT:
                if(!moveDirection.equals("right"))
                    setMoveDirection("left");
                break;
            case KeyEvent.VK_RIGHT:
                if(!moveDirection.equals("left"))
                    setMoveDirection("right");
                break;
        }
    }

    public void setMoveDirection (String direction) {
        moveDirection = direction;
    }

    public String getDirection () {
        return moveDirection;
    }


    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
