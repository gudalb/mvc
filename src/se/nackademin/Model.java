package se.nackademin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Model {
    int gameSize;
    int playerLoc;

    public int getPlayerScore() {
        return playerScore;
    }

    int playerScore = 0;
    List<Integer> playerMoveHistory = new ArrayList<>();
    String player = "0";
    String filler = " ";
    String tail = "o";
    String goal = "x";
    boolean collision = false;
    Random r = new Random();
    int pointLoc;

    private String[] playArea;

    public Model(int gameSize) {
        this.gameSize = gameSize;
        playArea = new String[gameSize * gameSize];

        for (int i = 0; i < playArea.length; i++) {
            playArea[i] = filler;
        }


        playerLoc = (gameSize * gameSize) / 2 + 5;
        playArea[playerLoc] = player;
        addRandomGoalPoint();


    }

    String getPlayArea() {
        StringBuilder playFieldString = new StringBuilder();

        for (int i = 0; i < playArea.length; i++) {
            if (i % gameSize == 0) {
                playFieldString.append("\n");
            }
            playFieldString.append(playArea[i]);
        }

        return playFieldString.toString();
    }

    public void setGameSize(int i) {
        this.gameSize = i;
    }

    boolean getCollision() {
        return collision;
    }

    void moveUp() {

        int tempLoc = playerLoc;
        playerMoveHistory.add(playerLoc);

        if (playerLoc - gameSize < 0)
            playerLoc = (gameSize * gameSize) - playerLoc - 1;
        else
            playerLoc = playerLoc - gameSize;

        collisionCheck();
        playArea[playerLoc] = player;

        checkIfPoint();
        paintTail();
    }

    void moveDown() {

        int tempLoc = playerLoc;
        playerMoveHistory.add(playerLoc);

        if (playerLoc + gameSize > gameSize * gameSize)
            playerLoc = playerLoc % gameSize;
        else
            playerLoc = playerLoc + gameSize;

        collisionCheck();
        playArea[playerLoc] = player;

        checkIfPoint();
        paintTail();
    }

    void moveLeft() {

        int tempLoc = playerLoc;
        playerMoveHistory.add(playerLoc);

        if ((playerLoc) % gameSize == 0)
            playerLoc = playerLoc + gameSize - 1;
        else
            playerLoc = playerLoc - 1;

        collisionCheck();
        playArea[playerLoc] = player;

        checkIfPoint();
        paintTail();
    }

    void moveRight() {


        int tempLoc = playerLoc;
        playerMoveHistory.add(playerLoc);

        if ((playerLoc) % gameSize == gameSize - 1)
            playerLoc = playerLoc - gameSize + 1;

        else
            playerLoc = playerLoc + 1;

        collisionCheck();
        playArea[playerLoc] = player;

        checkIfPoint();
        paintTail();
    }

    private void addRandomGoalPoint() {
        pointLoc = r.nextInt(gameSize * gameSize);
        playArea[pointLoc] = goal;
    }

    private void checkIfPoint() {
        if (playerLoc == pointLoc) {
            playerScore++;
            addRandomGoalPoint();
        }
    }

    private void collisionCheck() {
        collision = playArea[playerLoc].equals(tail);
    }

    private void paintTail() {

        for (int i = 0; i < playArea.length - 1; i++) {
            if (i == playerLoc)
                playArea[i] = player;
            else if (i == pointLoc)
                playArea[i] = goal;
            else {
                playArea[i] = filler;
            }


            if (playerScore > 0) {


                for (int e = 1; e < playerScore + 1; e++) {
                    playArea[playerMoveHistory.get(playerMoveHistory.size() - 1 - e)] = tail;
                }
            }


        }

    }
}
