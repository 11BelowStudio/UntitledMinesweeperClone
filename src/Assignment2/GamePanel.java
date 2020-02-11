package Assignment2;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GamePanel extends JPanel {

    Game game;

    int gridX;
    int gridY;
    float chance;

    ArrayList<ArrayList<GameObject>> gameArrayList;



    public GamePanel(int gX,int gY, float chance, Game game){

        gridX = gX;
        gridY = gY;
        this.chance = chance;
        this.game = game;

        this.setLayout(new GridLayout(gridX,gridY));

        gameArrayList = game.mineboard.getBoard();

        //spaceArray= new GameObject[boardBounds[0][level]][boardBounds[1][level]];

        for (int y = 0; y < gameArrayList.size(); y++) {
            for (int x = 0; x < gameArrayList.get(y).size(); x++) {
                /*
                if (gameArrayList.get(i).get(j) instanceof Mine){
                    spaceArray[i][j] = new Mine((Mine) gameArrayList.get(i).get(j));
                } else{
                    spaceArray[i][j] = new NotAMine((NotAMine) gameArrayList.get(i).get(j));
                }
                spaceArray[i][j].addMouseListener(new MouseClickListener(this));
                gamePanel.add(spaceArray[i][j]);*/
                this.add(gameArrayList.get(y).get(x));
            }
        }
    }

    public void safeRevealed(int revX, int revY, int count){
        if (game.panelsToFind != 0) {
            game.panelsToFind--; //decreases the panelsToFind count for 'game'
            if (count == 0) { //if this panel has a 0 in it
                //will proceed to check the spaces surrounding it

                int tempX; //storing an x and y value temporarily
                int tempY;

                //will search for spaces which are 1, 0, -1 in the x/y axis from current space
                for (int y = -1; y < 2; y++) {
                    tempY = revY + y; //tempY is effectively the Y co-ordinate of the current space being searched
                    if (tempY >= 0 && tempY < gridY) { //if tempY refers to a row which is within the bounds of the grid
                        for (int x = -1; x < 2; x++) {
                            tempX = revX + x; //tempX is effectively the X co-ordinate of the current space being searched
                            if (tempX >= 0 && tempX < gridX) { //if tempX refers to a an index which is within the bounds of the current row
                                if (!gameArrayList.get(tempY).get(tempX).getRevealed()) {
                                    gameArrayList.get(tempY).get(tempX).showContentsIngame();
                                }
                            }
                        }
                    }
                }
            }
            checkWin();
        }
    }

    //checks to see if you have won the game
    private void checkWin(){

        if (game.panelsToFind == 0){
            System.out.println("A winner is you");
            JOptionPane.showMessageDialog(game,
                    "congrats.",
                    "wow nice you actually managed to win",
                    JOptionPane.PLAIN_MESSAGE);

            game.score++;
            game.level++;
            game.initGame();
        }
    }


    //this is what happens when you press a mine
    public void kaboom(){
        System.out.println("oof");
        for (int y = 0; y < gameArrayList.size(); y++) {
            for (int x = 0; x < gameArrayList.get(y).size(); x++) {
                if (!gameArrayList.get(y).get(x).getRevealed()) {
                    gameArrayList.get(y).get(x).showContents();
                }
            }
        }
        JOptionPane.showMessageDialog(game,
                "you died lol.",
                "GAME OVER",
                JOptionPane.PLAIN_MESSAGE);

        game.score = 0;
        game.level = 0;
        game.initGame();
    }

}
