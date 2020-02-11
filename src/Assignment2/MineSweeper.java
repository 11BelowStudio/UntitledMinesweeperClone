package Assignment2;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;

public class MineSweeper { //this basically powers the background stuff

    private int gridX;
    private int gridY;
    private float prob;

    //ArrayList<ArrayList<Boolean>> mines;

    ArrayList<ArrayList<Character>> mineLocations;

    ArrayList<ArrayList<GameObject>> mineBoard;

    JPanel minePanel;

    Game game;

    private int mineCount;

    private Random rng;

    public MineSweeper(){
        rng = new Random();
        gridX = 0;
        gridY = 0;
        prob = 0f;
        game = null;
        makeMines();
    }

    public MineSweeper(Game game){
        rng = new Random();
        gridX = 0;
        gridY = 0;
        prob = 0f;
        this.game = game;
        makeMines();
    }

    public MineSweeper(int gridX, int gridY, float chance){
        this.gridX = gridX;
        this.gridY = gridY;
        prob = chance;

        rng = new Random();

        makeMines();

        /*
        for (int y = 0; y < gridY+2; y++) {
            System.out.println(mines.get(y));
        }*/

        for (int y = 0; y < gridY; y++) {
            System.out.println(mineLocations.get(y));
        }
    }

    public ArrayList<ArrayList<Character>> generateMines(int gridX, int gridY, float chance){

        this.gridX = gridX;
        this.gridY = gridY;
        prob = chance;
        makeMines();
        return mineLocations;
    }

    public ArrayList<ArrayList<GameObject>> generateBoard(int gridX, int gridY, float chance){

        this.gridX = gridX;
        this.gridY = gridY;
        prob = chance;
        makeMines();
        return mineBoard;
    }

    public ArrayList<ArrayList<Character>> getMines(){
        return mineLocations;
    }

    public ArrayList<ArrayList<GameObject>> getBoard(){
        return mineBoard;
    }

    public int getMineCount(){
        return mineCount;
    }

    private void makeMines(){

        mineCount = 0;

        ArrayList<ArrayList<Boolean>> mines = new ArrayList<ArrayList<Boolean>>();

        for (int y = 0; y < gridY+2; y++){
            mines.add(new ArrayList<>()); //creates a new ArrayList for this row of the board
            for (int x = 0; x < gridY+2; x++) {

                if (y == 0 || y == gridY+1 || x == 0 || x == gridX+1){
                    //if its on the outer edge of this larger array, no mine
                    mines.get(y).add(false);
                } else {mines.get(y).add(rng.nextFloat() < prob);}
                //if the float value returned by RNG is below prob (the chance of a mine appearing), there's a mine
                //otherwise no mine

            }
        }

        mineLocations = new ArrayList<ArrayList<Character>>();

        mineBoard = new ArrayList<>();

        int nearMines; //used to store count of surrounding mines

        for (int y = 0; y < gridY; y++) {
            mineLocations.add(new ArrayList<>());
            mineBoard.add(new ArrayList<>()) ;
            for (int x = 0; x < gridX; x++) {

                if (mines.get(y+1).get(x+1)){
                    //if this is a mine ('true' value in corresponding 'mines' index)
                    mineLocations.get(y).add('*');
                    mineBoard.get(y).add(new Mine(x,y,game)); //Mine object added to MineBoard
                    mineCount++; //mineCount increased
                    //mine represented by an asterisk
                } else{
                    //if this is not a mine
                    nearMines = 0;
                    //will count the mines in the 8 surrounding spaces
                    for (int a = -1; a < 2; a++){
                        for (int b = -1; b < 2; b++){
                            //each loop has values -1, 0, and 1
                            if (mines.get(y+1+a).get(x+1+b)){
                                nearMines++;
                                //increases nearMines if there's a mine in the current surrounding space
                            }
                        }
                    }
                    mineLocations.get(y).add((char)(nearMines + '0'));
                    //adds the value of nearMines as a character (keeping the numeric value) to mineLocations
                    mineBoard.get(y).add(new NotAMine(x,y,nearMines,game));
                    //adds a new NotAMine object to mineBoard
                }
                mineBoard.get(y).get(x).addMouseListener(new MouseClickListener(game));
            }
        }

    }

    public static void main(String args[]){
        MineSweeper m1 = new MineSweeper();

        ArrayList<ArrayList<GameObject>> board = m1.generateBoard(10,10,0.25f);

        System.out.println(m1.getMineCount());

        for (int i = 0; i < board.size(); i++) {
            System.out.println(board.get(i));
        }

        System.out.println("");

        board = m1.generateBoard(10,10,0.05f);
        System.out.println(m1.getMineCount());

        for (int i = 0; i < board.size(); i++) {
            System.out.println(board.get(i));
        }

        //MineSweeper m2 = new MineSweeper(10,10, (float) 0.5);
    }
}
