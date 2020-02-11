package Assignment2;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class Game extends JFrame {

    JPanel gameInfo;

    public GamePanel gamePanel;

    GameObject[][] spaceArray;

    ArrayList<ArrayList<GameObject>> gameArrayList;


    int score;
    JLabel scoreLabel;

    JLabel mineLabel;

    int level;
    JLabel levelLabel;

    MineSweeper mineboard;

    int panelsToFind;
    int mineCount;

    int[][] boardBounds;
    float[] boardChances;

    public void setupGameInfo(){
        //will now setup the GameInfo panel which goes above the game area

        //removes any existing gameInfo panel
        if (gameInfo != null){
            this.remove(gameInfo);
            gameInfo = null;
        }

        //actually sets up the gameInfo panel
        gameInfo = new JPanel(new GridLayout(1,3)); //1
        scoreLabel = new JLabel("Score: "+ score);
        mineLabel = new JLabel("");
        levelLabel = new JLabel("Level: "+level);
        gameInfo.add(levelLabel);
        gameInfo.add(mineLabel);
        gameInfo.add(scoreLabel);

        this.add(gameInfo, BorderLayout.NORTH);
    }

    public void initGame(){

        //removes the gamePanel panel if it already exists
        if (gamePanel != null){
            this.remove(gamePanel);
            gamePanel = null;
        }

        /*

        gamePanel = new JPanel(new GridLayout(boardBounds[0][level],boardBounds[1][level]));

        gameArrayList = mineboard.generateBoard(boardBounds[0][level],boardBounds[1][level],boardChances[level]);

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
                gamePanel.add(spaceArray[i][j]);
                    (end of that comment)

                gamePanel.add(gameArrayList.get(y).get(x));
            }
        }
         */
        mineCount = 0;
        while ((mineCount < 3) && (mineCount != boardBounds[0][level] * boardBounds[1][level])) {
            mineboard.generateBoard(boardBounds[0][level], boardBounds[1][level], boardChances[level]);
            mineCount = mineboard.getMineCount();
        }
        panelsToFind = (boardBounds[0][level] * boardBounds[1][level]) - mineCount;

        gamePanel = new GamePanel(boardBounds[0][level],boardBounds[1][level],boardChances[level], this);

        this.add(gamePanel, BorderLayout.CENTER);

        mineLabel.setText("Mines: " + mineCount);

        scoreLabel.setText("Score: "+ score);
        levelLabel.setText("Level: " + level);

        this.revalidate();
        this.repaint();
        //this.pack();
    }

    public Game(){

        this.setTitle("Untitled Minesweeper Game #1804170");

        this.setLayout(new BorderLayout());

        score = 0;
        level = 0;
        setupGameInfo();

        boardBounds = new int[][]{{5,5,5, 10,10,10, 15,15,15,20}, {5,5,5, 10,10,10, 15,15,15,20}};

        boardChances = new float[]{0.1f,0.15f,0.2f, 0.25f,0.3f,0.35f, 0.4f,0.425f,0.45f,0.475f};


        mineboard = new MineSweeper(this);

        initGame();

        addKeyListener(new KeyboardListener(this));

        setVisible(true);

        setSize(new Dimension(800,600));

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }


    public static void main(String[] args) {
        new Game();
    }

}
