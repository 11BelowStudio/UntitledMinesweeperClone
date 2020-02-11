package Assignment2;

import javax.swing.border.Border;
import java.awt.*;

public class Mine extends GameObject {

    MineCircle theMine;

    public Mine(int x, int y, Game game){

        super(x, y, game);
        this.setLayout(new BorderLayout());
        //this.add(text, BorderLayout.NORTH);
    }

    public void flag(){
        super.flag();
    }

    public void resized(){
        super.resized();
    }

    public void showContents(){
        super.showContents();
        theMine = new MineCircle(this.getSize());
        this.add(theMine, BorderLayout.CENTER);
        this.setBackground(Color.RED); //will set the background colour of this to red
        //text.setText("MINE");
        //this.add(text);
    }


    public void showContentsIngame(){
        this.showContents();
        game.gamePanel.kaboom();
    }

    public int getContents(){
        return 9;
    }

    public String toString(){
        return "Mine";
    }

    public Mine(Mine m){
        super(m.posX,m.posY, m.game);
    }
}
