import javax.swing.JPanel;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.awt.Rectangle;
import java.awt.Point;

/*
 * This is the actual game screen what is put on here will show up within the game
 */

public class GamePanel extends JPanel implements ActionListener{

    ArrayList<String> lifeCounter;  // The life counter at top left of streen

    MessageBox livesLabelThree; // top left label when char has 3 hearts
    MessageBox livesLabelTwo;  // top left label when char has 2 hearts
    MessageBox livesLabelOne;  // top left label when char has 1 heart

    int numOfDeaths;  // this is used to determine whether to restart and what # of hearts shows up on screen

    int cameraX;  // controls the camera THE MAIN CAMERA

    int cameraIntro=150;  // camera for animation

    Player player;  // this is the player object see Player.java for more info

    ArrayList<Wall> walls; //= new ArrayList<>();  // a list to keep all wall cordinates
    ArrayList<Wall> introWalls = new ArrayList<>();  // a list for all the intro wall animation cordinates

    Timer gameTimer;

    Rectangle menuButton; //Pause button top right

    //Intro screen
    IntroScreen introScreen;  // This is the background of the intro screen (not walls, titles, or start button)
    Rectangle startButton;
    MessageBox title;
    boolean start=true;  // controls what to do when start screen is up

    // Pause screen function
    Rectangle pauseScreen;
    Rectangle resetButton;
    Rectangle newGame;
    Rectangle continueButton;
    boolean pause=false;  // controls what to do when pause is pressed

    //Coins
    ArrayList<Coin> coins = new ArrayList<>();
    
    //Score Panel
    Rectangle score;

    //Reset boolean
    boolean reset = false;

    Wall wall;




    public GamePanel(){
        //walls
        wall = new Wall(10, 10, 100, 100);
        wall.populateWalls();
        walls = wall.returnList();


        //BEGINING OF GAME (BEFORE PLAYING)
        introScreen = new IntroScreen(300, 600);
        startButton = new Rectangle(245, 475, 200, 100);
        title = new MessageBox("DODGE A BLOCK", 0, 100);


        //PAUSE SCREEN
        //pauseScreen = new Rectangle(210,100,270,400);
        resetButton = new Rectangle(240, 335, 200, 50);
        newGame = new Rectangle(240, 410, 200, 50);
        continueButton = new Rectangle(240, 260, 200, 50);


        //WHILE PLAYING
        player = new Player(300,300,this);  // Set up the player character on the screen.
        numOfDeaths = -1;

        //Score panel
        score = new Rectangle(10, 600, 100, 100);


        // Sets up the health box in the top left corner
        String livesThree = "\u2764 \u2764 \u2764";
        livesLabelThree= new MessageBox(livesThree, 20, 50);
        String livesTwo = "\u2764 \u2764";
        livesLabelTwo= new MessageBox(livesTwo, 20, 50);
        String livesOne = "\u2764";
        livesLabelOne= new MessageBox(livesOne, 20, 50);


        // Sets the restart botton in top right
        menuButton = new Rectangle(600, 20, 75, 40);

    
        // RESET CHAR WHILE PLAYING
        reset();
        // Delays the character 3 seconds before moving
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        gameTimer = new Timer();
        gameTimer.schedule(new TimerTask(){
            

            public void run(){
                System.out.println(player.getPoints());
                if (start == true){
                    for(Wall introWall: introWalls) introWall.set(cameraIntro);
                    repaint();
                    cameraIntro+=1; // incrementing the intro screen
                }

                if (start == false){
                    player.set();
                    for(Wall wall: walls) wall.set(cameraX);
                    for(Coin coin: coins) coin.set(cameraX);
                    repaint();
                }
                if(pause == true){
                    player.xspeed=0;
                    player.yspeed=0;
                    cameraX = getCameraX()-1;
                    repaint();
                }
                //System.out.println(getCameraX());
            }

        }, 0, 17);

    }


    public void reset(){

        player.x = 100;
        player.y = 500;
        player.xspeed = 0;
        player.yspeed = 0;

        cameraX = 150;

        //makeWalls();
        //for(Coin coin: coins) coin.hitFalse();
        makeCoins();
        animatedWalls();


        numOfDeaths +=1;

        //  if the number of deaths equals for it will return to the start screen
        if (numOfDeaths == 4){
            start = true;
            player.setPoints(0);
            numOfDeaths = 0;
        }
    }



    public void paint(Graphics g){
        super.paint(g);
        Graphics2D gtd = (Graphics2D) g;

        //SETTING UP CHARACTER
        if(start == false){
            for(Coin coin: coins) coin.drawCircle(gtd);

            player.drawRight(gtd);



            //SETTING UP WALLSa
            for(Wall wall: walls)wall.draw(gtd);

            //Setting up MENU button 
            gtd.setColor(Color.BLACK);
            gtd.fillRect(600, 20, 75, 40);
            gtd.setColor(Color.white);
            gtd.fillRect(601, 21, 70, 35);
            gtd.setColor(Color.BLACK);
            Font font = new Font("Dialog", Font.PLAIN, 20);
            gtd.setFont(font);
            gtd.drawString("MENU", 607, 47);


                        //SETING UP LIVES LABEL
            if (numOfDeaths == 0)livesLabelThree.paint(gtd);
            if (numOfDeaths == 1)livesLabelTwo.paint(gtd);
            if (numOfDeaths == 2)livesLabelOne.paint(gtd);

            //Score panel
            gtd.setColor(Color.black);
            gtd.fillRect(440, 20, 150, 40);
            gtd.setColor(Color.WHITE);
            gtd.fillRect(441, 21, 148, 35);
            gtd.setColor(Color.BLACK);
            font = new Font("Dialog", Font.PLAIN, 20);
            gtd.setFont(font);
            String currentPoints = Integer.toString(player.getPoints());
            gtd.drawString("SCORE: "+currentPoints, 447, 47);
        }


        //INTO SCREEN
        if(start == true){
            for(Wall introWall: introWalls)introWall.draw(gtd);
            introScreen.paint(gtd);

            //START BUTTON(UNCOMMENT LINES TO TEST)
            //gtd.setColor(Color.BLACK);
            //gtd.drawRect(245, 475, 200, 100);

            gtd.setColor(Color.RED);
            Font font = new Font("Dialog", Font.PLAIN, 60);
            gtd.setFont(font);
            gtd.drawString("START", 255, 545);

            //TITLE BOX
            title.paintTitle(gtd);
        }

        if (pause == true && start == false){
            gtd.setColor(Color.DARK_GRAY);
            gtd.fillRect(210,100,260,390);
            gtd.setColor(Color.WHITE);
            gtd.fillRect(225, 115, 230, 360);

            gtd.setColor(Color.BLACK);
            Font font = new Font("Dialog", Font.PLAIN, 55);
            gtd.setFont(font);
            gtd.drawString("PAUSED", 240, 175);


            // CONTINUE BUTTON
            gtd.setColor(Color.LIGHT_GRAY);
            gtd.fillRect(240, 260, 200, 50);
            gtd.setColor(Color.BLACK);
            font = new Font("Dialog", Font.PLAIN, 40);
            gtd.setFont(font);
            gtd.drawString("Continue", 250, 300);

            // RESTART BUTTON
            gtd.setColor(Color.LIGHT_GRAY);
            gtd.fillRect(240, 335, 200, 50);
            gtd.setColor(Color.RED);
            font = new Font("Dialog", Font.PLAIN, 40);
            gtd.setFont(font);
            gtd.drawString("Restart", 270, 375);

            //NEW GAME BUTTON
            gtd.setColor(Color.LIGHT_GRAY);
            gtd.fillRect(240, 410, 200, 50);
            gtd.setColor(Color.RED);
            font = new Font("Dialog", Font.PLAIN, 35);
            gtd.setFont(font);
            gtd.drawString("New Game", 250, 450);



        }


    }


    public int getCameraX(){
        return this.cameraX;
    }


    void keyPressed(KeyEvent e){
        if(e.getKeyChar() == ' '&& start == false) player.keyUp = true;
    }

    void keyReleased(KeyEvent e){
        if(e.getKeyChar() == ' '&& start == false) player.keyUp = false;
    }



    @Override
    public void actionPerformed(ActionEvent ae){
    }

    void mouseClicked(MouseEvent e){

        if(menuButton.contains(new Point(e.getPoint().x,e.getPoint().y-29)) && start == false){
            //reset();
            if (pause == true){
                pause = false;
            }
            else{
                pause = true;
            }
            repaint();
            System.out.println("CLICKED");
        }
        if(startButton.contains(new Point(e.getPoint().x,e.getPoint().y-29)) && start == true){
            start = false;
            System.out.println("CLICKED START");
        }
        if(resetButton.contains(new Point(e.getPoint().x,e.getPoint().y-29)) && pause == true){
            reset();
            pause = false;
            System.out.println("CLICKED RESTART");
        }
        if(newGame.contains(new Point(e.getPoint().x,e.getPoint().y-29)) && pause == true){
            start = true;
            player.setPoints(0);
            reset();
            pause = false;
            numOfDeaths=0;
            System.out.println("CLICKED New Game");
        }
        if(continueButton.contains(new Point(e.getPoint().x,e.getPoint().y-29)) && pause == true){
            pause = false;
            System.out.println("CLICKED continue");
        }
    }

    public void makeCoins(){  // This Method is for the opening screen animation
        for(int i = 100; i<50000; i+=500){
            coins.add(new Coin(i,500,25,Color.YELLOW));
        }
    }

    public void animatedWalls(){  // This Method is for the opening screen animation
        for(int i = 100; i<50000; i+=100){
            introWalls.add(new Wall(i, 600,100,100));
        }
        for(int i = 100; i<50000; i+=100){
            introWalls.add(new Wall(i, 0,100,100));
        }
    }
    /* 
    // This method is level One walls
    public void makeWalls(){
        // This is the floor
        for(int i = 100; i<2200; i+=100){
            walls.add(new Wall(i, 600,100,100));
        } 
        //for(int i = 6000; i<10000; i+=100){
        //    walls.add(new Wall(i, 600,100,100));
        //} 

        walls.add(new Wall(1400,450,100,50));
        walls.add(new Wall(1800,400,100,50));
        walls.add(new Wall(2200,350,100,50));
        walls.add(new Wall(2600,300,100,50));

        walls.add(new Wall(2300, 600,100,100));
        walls.add(new Wall(2400, 600,100,100));
        walls.add(new Wall(2600, 600,100,100));
        walls.add(new Wall(2800, 600,100,100));
        walls.add(new Wall(3100, 600,100,100));
        walls.add(new Wall(3300, 600,100,100));
        walls.add(new Wall(3600, 600,100,100));
        walls.add(new Wall(3800, 600,100,100));
        walls.add(new Wall(4100, 600,100,100));
        walls.add(new Wall(4400, 600,100,100));
        walls.add(new Wall(4800, 600,100,100));
        walls.add(new Wall(5200, 600,100,100));
        walls.add(new Wall(5600, 600,100,100));

        walls.add(new Wall(5800, 400,100,50));



        walls.add(new Wall(3000,250,100,50));
        walls.add(new Wall(3400,200,1000,50));
        walls.add(new Wall(4600,200,1000,50));

        walls.add(new Wall(6000,600,100,100));
        walls.add(new Wall(6000,500,100,100));
        walls.add(new Wall(6000,400,100,100));
        walls.add(new Wall(6000,300,100,100));

        walls.add(new Wall(6300,600,100,100));
        walls.add(new Wall(6300,500,100,100));
        walls.add(new Wall(6300,400,100,100));
        walls.add(new Wall(6300,300,100,100));
        walls.add(new Wall(6400,600,100,100));
        walls.add(new Wall(6400,500,100,100));
        walls.add(new Wall(6400,400,100,100));
        walls.add(new Wall(6400,300,100,100));

        walls.add(new Wall(6700,600,100,100));
        walls.add(new Wall(6700,500,100,100));
        walls.add(new Wall(6700,400,100,100));
        walls.add(new Wall(6700,300,100,100));
        walls.add(new Wall(6800,600,100,100));
        walls.add(new Wall(6800,500,100,100));
        walls.add(new Wall(6800,400,100,100));
        walls.add(new Wall(6800,300,100,100));


        walls.add(new Wall(7100,600,100,100));
        walls.add(new Wall(7100,500,100,100));
        walls.add(new Wall(7100,400,100,100));
        walls.add(new Wall(7100,300,100,100));
        walls.add(new Wall(7200,600,100,100));
        walls.add(new Wall(7200,500,100,100));
        walls.add(new Wall(7200,400,100,100));
        walls.add(new Wall(7200,300,100,100));

        walls.add(new Wall(7500,600,100,100));
        walls.add(new Wall(7500,500,100,100));
        walls.add(new Wall(7500,400,100,100));
        walls.add(new Wall(7500,300,100,100));
        walls.add(new Wall(7600,600,100,100));
        walls.add(new Wall(7600,500,100,100));
        walls.add(new Wall(7600,400,100,100));
        walls.add(new Wall(7600,300,100,100));

        walls.add(new Wall(7900,600,100,100));
        walls.add(new Wall(7900,500,100,100));
        walls.add(new Wall(7900,400,100,100));
        walls.add(new Wall(7900,300,100,100));
        walls.add(new Wall(8000,600,100,100));
        walls.add(new Wall(8000,500,100,100));
        walls.add(new Wall(8000,400,100,100));
        walls.add(new Wall(8000,300,100,100));



    }
     */
}