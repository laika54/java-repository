import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;


public class Board implements ActionListener{

    private int width, height;
    private String frameTitle;
    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();
    private GridLayout GLayout = new GridLayout(5,5);
    //private String extention = "images\\";
    //private String image = "RedFrog";
    private boolean lily = true;
    private File file;
    private BufferedReader br;
    Square[] squares = new Square[25];
    private int[] selected = {-1,-1};
    //Square testSquare = new Square(0,0);
    

    
    public Board(int w, int h, String title){
        width = w;
        height = h;
        frameTitle = title;
    }

    public void createScreen(){
        frame.setSize(width,height);
        frame.setTitle(frameTitle);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);

        /*gotoEventBtn.setLocation(150, 150);
        gotoEventBtn.setSize(250, 50);
        gotoEventBtn.addActionListener(this);
        gotoEventBtn.setText("GO TO Events");
        mainMenuPanel.add(gotoEventBtn);*/
        //JOptionPane.showMessageDialog(null, "pee pee poo poo");

        //frame.add(panel);
        panel.setLayout(GLayout);
        //testSquare.setImage(image);
        //panel.add(testSquare.button, GLayout);
        
        createLevel(1);
        frame.setVisible(true);
    }

    public void createLevel(int level){
        String sqr = "";
        try{
            file = new File("level"+level+".txt"); 
            br = new BufferedReader(new FileReader(file));
        }catch(Exception e){
            System.out.println(e);
        }
        for (int i = 0; i<5; i++){
            for (int j = 0; j<5; j++){
                
                try{
                    sqr = br.readLine();
                }catch(Exception e){
                    System.out.println(e);
                }

                System.out.println(sqr+"\n");

                switch(sqr){
                    case "r":
                        squares[(i*5)+j] = new Square(j,i,"RedFrog");
                        squares[(i*5)+j].setImage("RedFrog");
                        break;
                    case "g":
                        squares[(i*5)+j] = new Square(j,i,"GreenFrog");
                        squares[(i*5)+j].setImage("GreenFrog");
                        break;
                    case "w":
                        squares[(i*5)+j] = new Square(j,i,"Water");
                        squares[(i*5)+j].setImage("Water");
                        break;
                    case "l":
                        squares[(i*5)+j] = new Square(j,i,"LilyPad");
                        squares[(i*5)+j].setImage("LilyPad");
                        break;
                }

                panel.add(squares[(i*5)+j].button, GLayout);
                squares[(i*5)+j].button.addActionListener(this);
            }
        }
    }

    public int[] findCentre(Square sq1, Square sq2){
        int[] coords = {-1,-1};
        try{
            coords[0] = (sq1.getx()+sq2.getx())/2;
            coords[1] = (sq1.gety()+sq2.gety())/2;
            return coords;
        }catch(Exception e){
            System.out.println("not valid centre");
        }
        return coords;
    }

    public int getIndex(int xcord, int ycord){
        return (ycord*5)+xcord;
    }

    public boolean winner(){
        for (int i = 0; i < 25; i++){
            if(squares[i].getPiece().equals("GreenFrog")){
                return false;
            }
        }

        return true;
    }

    public void actionPerformed(ActionEvent e){
        //System.out.println(e.getSource());
        
        for (int i = 0; i<25; i++){
            if (e.getSource() == squares[i].button){
                System.out.println(squares[i].getx() + " " + squares[i].gety());
                if(squares[i].getPiece().equals("Water") == false){
                    if (selected[0] == -1){
                        if(squares[i].getPiece().equals("LilyPad") == false){
                            selected[0] = i;
                            squares[i].setImage(squares[i].getPiece()+"2");
                        }
                    }else{
                        selected[1]=i;
                        int[] centre = {-1,-1};
                        centre = findCentre(squares[selected[0]], squares[selected[1]]);
                        //int centreIndex =
                        if (squares[selected[0]].checkLegal(squares[selected[1]], squares[getIndex(centre[0],centre[1])], centre)){
                            squares[selected[0]].moveSquare(squares[selected[1]]);

                            squares[getIndex(centre[0],centre[1])].setPiece("LilyPad");
                            squares[getIndex(centre[0],centre[1])].setImage("LilyPad");
                            
                            selected[0]=-1;
                            selected[1]=-1;
                        }else{
                            System.out.println("move illegal");
                            System.out.println(squares[selected[1]].getPiece());
                        }
                        //selected[0]=-1;
                        selected[1]=-1;
                    }
                }
            }
        }

        if (winner()){
            JOptionPane.showMessageDialog(null, "CATASTROPHIC ERROR:\nyou win :)");
        }
    }
}