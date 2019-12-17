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

    JTextArea playingField = new JTextArea(gameSize,gameSize);

    public View (int gameSize) {
        this.gameSize = gameSize;

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        this.setSize(500,500);
        this.add(gamePanel);

        playerScore.setText("Score: ");
        playingField.setEditable(false);
        gamePanel.add(playingField);
        gamePanel.add(playerScore);

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
                setMoveDirection("up");
                System.out.println(moveDirection);
                break;
            case KeyEvent.VK_DOWN:
                setMoveDirection("down");
                System.out.println(moveDirection);
                break;
            case KeyEvent.VK_LEFT:
                setMoveDirection("left");
                System.out.println(moveDirection);
                break;
            case KeyEvent.VK_RIGHT:
                setMoveDirection("right");
                System.out.println(moveDirection);
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
