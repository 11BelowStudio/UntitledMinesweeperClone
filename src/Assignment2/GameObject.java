package Assignment2;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;

public abstract class GameObject extends JPanel {
    int posX;
    int posY;

    Boolean revealed;

    Boolean flagged;

    JLabel text;

    Color background;

    Dimension currentSize;

    Game game;

    public GameObject(int x, int y, Game game) {
        posX = x;
        posY = y;
        this.game = game;
        revealed = false;
        flagged = false;
        this.setBackground(Color.lightGray);
        this.setBorder(new EtchedBorder());
        text = new JLabel();
        currentSize = this.getSize();
    }

    public void flag(){
        if (flagged){
            this.setBackground(Color.lightGray);
            flagged = false;
        } else{
            this.setBackground(Color.orange);
            flagged = true;
        }
    }

    public void resized(){
        currentSize = this.getSize();
    }

    public Boolean getRevealed() {
        return revealed;
    }

    public int getPosX(){
        return posX;
    }
    public int getPosY(){
        return posY;
    }

    public void showContents(){
        if (flagged){
            this.flag();
        }
        this.revealed = true;
    }


    public abstract void showContentsIngame();

    public abstract int getContents();

    public abstract String toString();

}
