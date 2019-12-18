package se.nackademin.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Model {
    int gameSize;
    int playerLoc;

    public int getPlayerScore() {
        return playerScore;
    }

    public int playerScore = 0;
    List<Integer> playerMoveHistory = new ArrayList<>();
    String player = "O";
    String filler = "  ";
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

    public boolean getCollision() {
        return collision;
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

    void makeMove () {
        collisionCheck();
        playArea[playerLoc] = player;

        checkIfPoint();
        paintGame();
    }

    private void addRandomGoalPoint() {

        while(true) {
            pointLoc = r.nextInt(gameSize * gameSize);
            if(!playArea[pointLoc].equals(tail) && !playArea[pointLoc].equals(player) )
                break;
        }

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

    }
}
