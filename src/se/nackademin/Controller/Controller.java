package se.nackademin.Controller;

import se.nackademin.Model.Model;
import se.nackademin.View.View;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Controller implements Runnable, KeyListener, PropertyChangeListener {
    private Model model;
    private View view;
    private String moveDirection = "up";

    public Controller (Model model, View view) {
        this.model = model;
        this.view = view;
        this.view.setPlayingField(model.getPlayArea());
        this.view.addKeyListener(this);
    }

    @Override
    public void run() {

        while (true) {

            if(moveDirection.equals("up"))
                model.moveUp();
            if(moveDirection.equals("down"))
                model.moveDown();
            if(moveDirection.equals("left"))
                model.moveLeft();
            if(moveDirection.equals("right"))
                model.moveRight();

            view.setPlayingField(model.getPlayArea());
            view.setPlayerScore(model.getPlayerScore());

            if(model.getCollision()) {
                view.showGameOver("Game Over, Score: " + model.getPlayerScore());
                break;
            }

            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyValue = e.getKeyCode();

        switch (keyValue) {
            case KeyEvent.VK_UP:
                if(!moveDirection.equals("down"))
                    moveDirection = "up";
                break;
            case KeyEvent.VK_DOWN:
                if(!moveDirection.equals("up"))
                    moveDirection = "down";
                break;
            case KeyEvent.VK_LEFT:
                if(!moveDirection.equals("right"))
                    moveDirection = "left";
                break;
            case KeyEvent.VK_RIGHT:
                if(!moveDirection.equals("left"))
                    moveDirection = "right";
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }


}

