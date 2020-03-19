import javax.swing.*;
import java.awt.event.*;
import java.lang.*;

public class Square{

    public JButton button = new JButton();
    private int x;
    private int y;
    private Icon i;
    private String piece;

    public Square(int xcord, int ycord, String p){
        x = xcord;
        y = ycord;
        piece = p;
        
    }

    public void setImage(String img){
        i = new ImageIcon(img+".png");
        //button.JButton(i);
        button.setIcon(i);
    }

    public int getx(){
        return x;
    }

    public int gety(){
        return y;
    }

    public JButton getButton(){
        return button;
    }
    
    public void setx(int num){
        x = num;
    }

    public void sety(int num){
        y = num;
    }

    public void setPiece(String p){
        piece = p;
    }

    public String getPiece(){
        return piece;
    }

    public boolean checkLegal(Square squareMoveTo, Square centreSquare, int[] centre){
        //check if move is legal
            //check is second piece is a lily
        int changey = y - squareMoveTo.gety();
        int changex = x - squareMoveTo.getx();
        if (squareMoveTo.getPiece().equals("LilyPad") == false){
            System.out.println("Piece to land on was not a lilypad");
            return false;

        }
            //check if lily is close enough ----- total change x and y must be 4
        else if (Math.abs(changex)+Math.abs(changey) != 4){
            System.out.println("Distance not valid");
            System.out.println(changex + "piss");
            System.out.println((x+y) + "\t" + squareMoveTo.getx() + " " + squareMoveTo.gety() + "piss");
            return false;
        }
            
            
            //check if jumping over a frog -- _check if square between two squares is a frog_
        else if (centreSquare.getPiece().equals("GreenFrog") == false){
            System.out.println("Centre was not a frog");
            return false;
        }else if (centre[0] == -1){
            System.out.println("Centre invalid");
            return false;
        }else{
            return true;
        }
    }

    public void moveSquare(Square square){
        //getIndex(x,y);
        square.setImage(piece);
        square.setPiece(piece);

        setImage("LilyPad");
        setPiece("LilyPad");

        //setx(square.getx());
        //sety(square.gety());
    }

    public void testMethod(Square square){
        System.out.println(square.getx() + square.getPiece());
    }
}