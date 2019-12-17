package se.nackademin;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.SQLOutput;

public class Controller implements Runnable {
    Model model;
    View view;

    public Controller (Model model, View view) {
        this.model = model;
        this.view = view;
        view.setPlayingField(model.getPlayArea());
    }

    @Override
    public void run() {

        while (true) {

            String moveDirection = view.getDirection();

            if(moveDirection.equals("up"))
                model.moveUp();
            if(moveDirection.equals("down"))
                model.moveDown();
            if(moveDirection.equals("left"))
                model.moveLeft();
            if(moveDirection.equals("right"))
                model.moveRight();

            view.setPlayingField(model.getPlayArea());
            view.setPlayerScore(model.playerScore);

            if(model.getCollision()) {
                view.showGameOver("Game Over, Score: " + model.getPlayerScore());
                break;
            }

            try {
                Thread.sleep(350);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
