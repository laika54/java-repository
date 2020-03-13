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
    Square testSquare = new Square(0,0);
    

    
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
                squares[(i*5)+j] = new Square(i,j);
                
                try{
                    sqr = br.readLine();
                }catch(Exception e){
                    System.out.println(e);
                }

                System.out.println(sqr+"\n");

                switch(sqr){
                    case "r":
                        squares[(i*5)+j].setImage("RedFrog");
                        break;
                    case "g":
                        squares[(i*5)+j].setImage("GreenFrog");
                        break;
                    case "w":
                        squares[(i*5)+j].setImage("Water");
                        break;
                    case "l":
                        squares[(i*5)+j].setImage("LilyPad");
                        break;
                }

                panel.add(squares[(i*5)+j].button, GLayout);
            }
        }
    }

    public void actionPerformed(ActionEvent e){

    }
}