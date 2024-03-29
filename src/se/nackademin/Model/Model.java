package se.nackademin.Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

public class Model {
    private int gameSize;
    private int playerLoc;
    private int playerScore = 0;
    private List<Integer> playerMoveHistory = new ArrayList<>();
    private String player = "O";
    private String filler = "  ";
    private String tail = "o";
    private String goal = "x";
    private boolean collision = false;
    private Random r = new Random();
    private int pointLoc;
    private PropertyChangeSupport subjects;
    private String[] playArea;

    public Model(int gameSize) {
        this.gameSize = gameSize;
        playArea = new String[gameSize * gameSize];
        Arrays.fill(playArea, filler);
        playerLoc = (gameSize * gameSize) / 2 + 5;
        playArea[playerLoc] = player;
        addRandomGoalPoint();
        subjects = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener (PropertyChangeListener pcl) {
        subjects.addPropertyChangeListener(pcl);
    }

    public String getPlayArea() {
        StringBuilder playFieldString = new StringBuilder();

        for (int i = 0; i < playArea.length; i++) {
            if (i % gameSize == 0) {
                playFieldString.append("\n");
            }
            playFieldString.append(playArea[i] + "   ");
        }

        return playFieldString.toString();
    }


    public void moveUp() {

        playerMoveHistory.add(playerLoc);

        if (playerLoc - gameSize < 0)
            playerLoc = (gameSize * gameSize) - (gameSize - playerLoc);
        else
            playerLoc = playerLoc - gameSize;

        makeMove();
    }

    public void moveDown() {

        playerMoveHistory.add(playerLoc);

        if (playerLoc + gameSize + 1  > gameSize * gameSize)
            playerLoc = playerLoc % gameSize;
        else
            playerLoc = playerLoc + gameSize;

        makeMove();
    }

    public void moveLeft() {

        playerMoveHistory.add(playerLoc);

        if ((playerLoc) % gameSize == 0)
            playerLoc = playerLoc + gameSize - 1;
        else
            playerLoc = playerLoc - 1;

        makeMove();
    }

    public void moveRight() {

        playerMoveHistory.add(playerLoc);

        if ((playerLoc) % gameSize == gameSize - 1)
            playerLoc = playerLoc - gameSize + 1;
        else
            playerLoc = playerLoc + 1;

        makeMove();
    }

    private void makeMove() {
        collisionCheck();
        playArea[playerLoc] = player;
        checkIfPoint();
        paintGame();
    }

    private void addRandomGoalPoint() {

        do {
            pointLoc = r.nextInt(gameSize * gameSize);
        } while (playArea[pointLoc].equals(tail) || playArea[pointLoc].equals(player));

        playArea[pointLoc] = goal;

    }

    private void checkIfPoint() {
        if (playerLoc == pointLoc) {
            playerScore++;
            addRandomGoalPoint();
            subjects.firePropertyChange("playerScore", null, playerScore);
        }
    }

    private void collisionCheck() {
        if(playArea[playerLoc].equals(tail)) {
            subjects.firePropertyChange("collision", this.collision, true);
            collision = playArea[playerLoc].equals(tail);
        }
    }

    private void paintGame() {

        for (int i = 0; i < playArea.length; i++) {
            if (i == playerLoc)
                playArea[i] = player;
            else if (i == pointLoc)
                playArea[i] = goal;
            else {
                playArea[i] = filler;
            }

            for (int e = 0; e < playerScore + 1; e++) {
                playArea[playerMoveHistory.get(playerMoveHistory.size() - 1 - e)] = tail;
            }
        }

        subjects.firePropertyChange("paintGame", null, getPlayArea());

    }
}
