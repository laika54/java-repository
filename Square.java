import javax.swing.*;
import java.awt.event.*;

public class Square{

    public JButton button;
    private int x;
    private int y;
    private ImageIcon i;
    private String piece;

    public Square(int xcord, int ycord){
        x = xcord;
        y = ycord;
        
    }

    public void setImage(String img){
        i = new ImageIcon(img+".png");
        button = new JButton(i);
        button.setIcon(i);
    }

    public int getx(){
        return x;
    }

    public int gety(){
        return y;
    }

    public void setPiece(String p){
        piece = p;
    }

    public String getPiece(){
        return piece;
    }

    public boolean checkLegal(Square square){
        //check if move is legal
        //check if THIS SQUARE -- IE THE INSTANCE  THIS COMMENT IS IN RIGHT NOW is a frog
        //check is second piece is a lily
        //check if lily is close enough
        //check if jumping over a frog -- _check if square between two squares is a frog_
    }
}