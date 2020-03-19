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
    private JPanel outerPanel = new JPanel();
    private JPanel topPanel = new JPanel();
    private JPanel rightPanel = new JPanel();
    private JButton reset = new JButton("reset");
    private JButton chooseLevel = new JButton("choose level");
    private JComboBox<String> levelSelect;

    private GridLayout GLayout = new GridLayout(5,5);
    private BorderLayout BLayout = new BorderLayout();
    private BorderLayout topBLayout = new BorderLayout();
    private BorderLayout rightBLayout = new BorderLayout();

    private boolean lily = true;

    private File file;
    private BufferedReader br;

    private Square[] squares = new Square[25];

    private int[] selected = {-1,-1};

    private int currentLevel = 0;

    private String[] levels = new String[41];



    public Board(int w, int h, String title){
        width = w;
        height = h;
        frameTitle = title;
    }

    public void createScreen(){
        for (int i=0; i < 41; i++){
            levels[i] = i+"";
        }
        levels[0] = "Select Level";
        levelSelect = new JComboBox<String>(levels);
        levelSelect.setSelectedIndex(0);

        levelSelect.addActionListener(this);
        reset.addActionListener(this);
        chooseLevel.addActionListener(this);

        frame.setSize(width,height);
        frame.setTitle(frameTitle);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
        frame.setContentPane(outerPanel);

        outerPanel.setLayout(BLayout);
        topPanel.setLayout(topBLayout);
        rightPanel.setLayout(rightBLayout);
        panel.setLayout(GLayout);

        outerPanel.add(panel, BorderLayout.CENTER);
        outerPanel.add(topPanel, BorderLayout.NORTH);
        topPanel.add(rightPanel, BorderLayout.EAST);
        topPanel.add(reset, BorderLayout.WEST);
        rightPanel.add(chooseLevel, BorderLayout.CENTER);
        rightPanel.add(levelSelect, BorderLayout.EAST);

        createLevel(1);
        frame.setVisible(true);
    }

    public void resetLevel(int level){
    System.out.println("reset LEVELELLELELL");
        currentLevel = level;
        selected[0] = -1;
        selected[1] = -1;
        String sqr = "";
        try{
            file = new File("levels\\finishedLevel"+level+".txt"); 
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

                //System.out.println(sqr+"\n");
                //System.out.println(level);

                switch(sqr){
                    case "r":
                        squares[(i*5)+j].setPiece("RedFrog");
                        squares[(i*5)+j].setImage("RedFrog");
                        break;
                    case "g":
                        squares[(i*5)+j].setPiece("GreenFrog");
                        squares[(i*5)+j].setImage("GreenFrog");
                        break;
                    case "w":
                        squares[(i*5)+j].setPiece("Water");
                        squares[(i*5)+j].setImage("Water");
                        break;
                    case "l":
                        squares[(i*5)+j].setPiece("LilyPad");
                        squares[(i*5)+j].setImage("LilyPad");
                        break;
                }

                //panel.add(squares[(i*5)+j].getButton(), GLayout);
                //squares[(i*5)+j].getButton().addActionListener(this);
            }
        }
    }

    public void createLevel(int level){
        System.out.println("CREATE LEVELELLELELL");
        currentLevel = level;
        selected[0] = -1;
        selected[1] = -1;
        String sqr = "";
        try{
            file = new File("levels\\finishedLevel"+level+".txt"); 
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

                //System.out.println(sqr+"\n");
                System.out.println(level);

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

                panel.add(squares[(i*5)+j].getButton(), GLayout);
                squares[(i*5)+j].getButton().addActionListener(this);
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
            if (e.getSource() == squares[i].getButton()){
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

        if(e.getSource() == reset){
            resetLevel(currentLevel);
        }

        if(e.getSource() == chooseLevel){
            if (levelSelect.getSelectedIndex() != 0){
                resetLevel(Integer.parseInt(levelSelect.getSelectedItem()+""));
            }
        }

        if (winner()){
            JOptionPane.showMessageDialog(null, "CATASTROPHIC ERROR:\nyou win :)");
            resetLevel(currentLevel+1);
        }
    }
}