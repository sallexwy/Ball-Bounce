////////IMPORTS NECESSARY STUFF////////
import javax.swing.*;
import java.awt.Graphics;
import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.io.*;
import java.util.Random;
///////THIS CLASS DOES THE GAMEBOARD////////////
public class Moving_Ball extends JFrame //implements KeyListener
{

    public int RADIUS = 50;
    public int x = 250, y = 210;
    public int speedX = 1;
    public int speedY = 1;

    public boolean start = false;

    public int bx = 350, by = 550;
    public int l = 120, w = 20;
    public static double velX = 0;

    public CA_SallyXiao ()
    {
	setTitle ("Game Board");
	setSize (1000, 600);
	setBackground (Color.PINK);   // refer to awt colors - you can change the panel color
	setVisible (true);

	setForeground (Color.black);
	//add the key listener class
	addKeyListener (new myKeyListener ());

	//focuses the screen, otherwise keylistener won't work
	setFocusable (true);
	getContentPane ().setLayout (null);

    }


    ////////THE BOOLEAN METHODS FOR WHETHER THE BALL HITS THE WALL/////////
    //if the ball hits the two walls on the side
    public boolean hitsHorizWall ()
    {
	if (x == getWidth () - RADIUS || (x + RADIUS) == 0)
	{
	    return true;
	}
	else
	{
	    return false;
	}

    }


    //if the ball hits the top wall
    public boolean hitsVertWall ()
    {
	if (y == 200)
	{
	    return true;
	}
	else
	{
	    return false;
	}
    }


    //if the platform catches the ball
    public boolean hitsPlatform ()
    {

	if ((bx) <= (x + RADIUS) && (x - RADIUS) <= (bx + w) && (y + RADIUS) == by)
	{
	    return true;
	}
	else
	{
	    return false;
	}
    }


    //changing the speed or direction of the ball to whatever parameter given
    public void setSpeedX (int a)
    {
	speedX = a;
    }


    public void setSpeedY (int a)
    {

	speedY = a;
    }


    //this checks if the ball has reached the bottom
    public boolean hitBottom ()
    {
	if (y > 600)
	{
	    return true;
	}
	else
	{
	    return false;
	}
    }


    ///////THE METHOD THAT MOVES THE BALL//////////
    public void moveStuff ()
    {
	//moving the ball//
	x += speedX;
	y += speedY;

	Random random = new Random ();
	if (hitsHorizWall ())
	{
	    if (speedX > 0)
	    {

		// New speed between 10 and 15 (you can choose other)
		setSpeedX (-(random.nextInt (speedX) + 1));
	    }
	    else
	    {
		setSpeedX (random.nextInt (-speedX) + 1);
	    }
	}
	if (hitsVertWall ())
	{
	    if (speedY > 0)
	    {
		setSpeedY (-(random.nextInt (speedY) + 1));
	    }

	    else
	    {
		setSpeedY (random.nextInt (-speedY) + 1);
	    }

	}

	if (hitsPlatform ())
	{
	    setSpeedY (-(random.nextInt (speedY) + 1));
	}
	//moving the platform
	bx += velX;
    }


    //controls where the platform moves according to the key pressed
    public static void leftPlatform ()
    {
	velX = -2;
    }


    public static void rightPlatform ()
    {
	velX = 2;
    }


    //this method draws the two things
    public void paint (Graphics g)
    {

	super.paint (g);
	//paints the ball
	g.setColor (Color.RED);
	g.fillOval (x, y, RADIUS, RADIUS);

	//paints the platform
	g.setColor (Color.BLACK);
	g.fillRect (bx, by, l, w);

    }


    //delays for another 500 miliseconds
    public static void delayMe ()
    {

	try
	{
	    Thread.sleep (500);
	}
	catch (Exception e)
	{

	}
    }

/////////////////////THE METHOD THAT LAUNCHES EVERYTHING//////////////////
    public static void launch (String decide) throws IOException
    {
	CA_SallyXiao ball = new CA_SallyXiao ();
	//this resets everything and make sure you restart
	ball.RADIUS = 50;
	ball.x = 250;
	ball.y = 210;
	ball.speedX = 1;
	ball.speedY = 1;

	ball.bx = 350;
	ball.by = 550;
	ball.l = 120;
	ball.w = 20;
	ball.velX = 0;
	//the panel containing all elements on the menu
	JPanel menuPanel = new JPanel (new FlowLayout ()); //the panel for the menu
	GameMenu.panelDeclare (menuPanel, 1000, 150); //call the method that declares a panel

	JLabel introMessage = new JLabel ("Catch the Ball, don't let it fall!"); //the introduction message for the user is displayed on this panel
	introMessage.setFont (new Font ("Arial", Font.BOLD, 20));
	introMessage.setBounds (350, 10, 400, 25);
	//////ASKS THER USER TO GET READY////////
	JLabel readyMessage1 = new JLabel ("READY");

	readyMessage1.setFont (new Font ("Arial", Font.BOLD, 20));
	readyMessage1.setBounds (430, 10, 400, 25);

	JLabel readyMessage2 = new JLabel ("SET");
	readyMessage2.setFont (new Font ("Arial", Font.BOLD, 20));
	readyMessage2.setBounds (450, 10, 400, 25);

	JLabel readyMessage3 = new JLabel ("GO");
	readyMessage3.setFont (new Font ("Arial", Font.BOLD, 20));
	readyMessage3.setBounds (460, 10, 400, 25);

	//this label shows the time elapsed in miliseconds
	JLabel timer;

	//Button to view rules
	JButton rules = new JButton ("View Rules");
	rules.setFont (new Font ("Times New Roman", Font.PLAIN, 12));
	rules.setBounds (400, 50, 150, 30);
	//Button to start the game
	/*JButton start = new JButton ("Play Game");
	start.setFont (new Font ("Times New Roman", Font.PLAIN, 12));
	start.setBounds (400, 70, 150, 30);*/
	//Button to exit the game
	JButton exit = new JButton ("Exit");
	exit.setFont (new Font ("Times New Roman", Font.PLAIN, 12));
	exit.setBounds (400, 100, 150, 30);
	//adding everything to the frame
	ball.getContentPane ().add (introMessage);
	ball.getContentPane ().add (readyMessage1);
	ball.getContentPane ().add (readyMessage2);
	ball.getContentPane ().add (readyMessage3);
	ball.getContentPane ().add (rules);
	/*ball.getContentPane ().add (start);*/
	ball.getContentPane ().add (exit);
	ball.getContentPane ().add (menuPanel); //adding the menu panel to the menu frame
	ball.setResizable (false);  //prevent the user from resizing the panel
	ball.setVisible (true);   //make the panel and its related elements visible

	introMessage.setVisible (false);
	readyMessage1.setVisible (true);
	readyMessage2.setVisible (false);
	readyMessage3.setVisible (false);
	//what happens when you press the rules button
	rules.addActionListener (new ActionListener ()  //what you do when u click the button
	{
	    public void actionPerformed (ActionEvent arg0)
	    {
		GameMenu.showRules (); //call the method to display the rules for the user when the rules button is pressed
	    }
	}
	);

	//what happens when you press the exit button
	exit.addActionListener (new ActionListener ()  //when you click the exit button
	{
	    public void actionPerformed (ActionEvent arg0)
	    {
		System.exit (0); //the whole program exits
	    }
	}
	);


	/////////////// BEFORE GAME STARTS///////////////////
	//delay at the start of animation so user can prepare
	delayMe ();
	//First message for the user disappears
	readyMessage1.setVisible (false);
	//delays for another 500 miliseconds
	readyMessage2.setVisible (true);
	delayMe ();
	//second ready message disappears
	readyMessage2.setVisible (false);
	//delay
	readyMessage3.setVisible (true);
	delayMe ();
	//last message disappears
	readyMessage3.setVisible (false);
	introMessage.setVisible (true);
	/////////////////////DURING GAME////////////////////
	long startTime = System.currentTimeMillis ();
	long stopTime = System.currentTimeMillis ();

	// Run some code;
	while (true && !ball.hitBottom ())
	{
	    ball.moveStuff ();
	    ball.repaint ();
	    try
	    {
		Thread.sleep (3);
	    }

	    catch (Exception e)
	    {
		Thread.currentThread ().interrupt ();
	    }
	}
	//once the user loses the game
	stopTime = System.currentTimeMillis ();

	System.out.println ("You have lasted " + (stopTime - startTime) + " seconds, restart? Press R or r; to exit, press any other key.");

    }


    ////////////////////MAIN METHOD THAT LAUNCHES EVERYTHING///////////////////
    public static void main (String[] args) throws IOException
    {
	//this is initializing the variable that user will use to decide to continue the game or exit
	String decide = "";
	//it will launch the game first
	do
	{
	    BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
	    launch (decide);
	    decide = br.readLine ();
	}
	while (decide.equalsIgnoreCase ("R"));
	//otherwise the loop will break, exiting the program
	System.exit (0);
	//Button to view rules
    }
}

/////////////THIS IS FOR THE GAME MENU/////////////
class GameMenu
{
    public static void panelDeclare (JPanel p, int length, int width)  //declares and initializes all the necessary elements for a panel
    {
	p.setLayout (null);
	p.setBounds (0, 0, length, width);
	p.setBackground (Color.PINK);   // refer to awt colors - you can change the panel color
	p.setVisible (true);
    }


    /////////////////////////THE FOLLOWING METHODS ARE CONCERNED WITH THE RULES OF THE GAME//////////////////////////////////////////
    public static void writeRules ()  //this method creates a file named "rules.txt" and writes the rules of the game into it
    {
	try
	{
	    FileWriter dw = new FileWriter ("rules.txt"); //creating the file
	    PrintWriter pw = new PrintWriter (dw);
	    pw.println ("WELCOME!");
	    pw.println ("Please CAREFULLY read the following:");
	    pw.println ("The objective of the game is to keep the ball from falling to the bottom wall");
	    pw.println ("1. The game automatically starts after the message 'ready, set, go' disappears.");
	    pw.println ("2. Once the game starts, the ball will start to bounce and a timer will automatically start.");
	    pw.println ("3. You can only control the movement of the platform (the black rectangular structure in the figure above)");
	    pw.println ("4. The ball can bounce off of any of the three walls (top, left, right)");
	    pw.println ("5. When the ball reaches the bottom wall, you will lose the game.");
	    pw.println ("6. Your score is the time you have lasted");
	    pw.println ("7. The game can be terminated at any point when \"Exit\" is pressed");
	    pw.close ();
	}
	catch (IOException e)
	{
	}
    }


    //////////READS EACH LINE OF RULES AND CHANGE THEM INTO A JLabel
    public static void addRules (JPanel p)
    {
	writeRules ();
	try
	{

	    FileReader dr = new FileReader ("rules.txt");
	    BufferedReader br = new BufferedReader (dr);
	    String line;
	    line = br.readLine (); //line is the variable for each line read
	    int lineSpace = 0; //declared for the line spacing of each line of rule
	    while (line != null) //read until the end of the file
	    {

		JLabel eachRule = new JLabel (line);
		if (lineSpace == 0)
		{ //the first line of the rules board is formatted differently than the rest
		    eachRule.setFont (new Font ("Arial", Font.BOLD, 20)); //making each line a JLabel
		    eachRule.setBounds (200, 50 + lineSpace * 50, 700, 25);
		}
		else if (lineSpace == 1)
		{ //the second line of the rules board is also formatted differently than the rest
		    eachRule.setFont (new Font ("Arial", Font.ITALIC, 16)); //making each line a JLabel
		    eachRule.setBounds (20, 50 + lineSpace * 50, 700, 25);
		}
		else
		{
		    eachRule.setFont (new Font ("Arial", Font.PLAIN, 14));  //making each line a JLabel
		    eachRule.setBounds (50, 50 + lineSpace * 50, 700, 25);
		}
		p.add (eachRule); //adding each line of rule to the rule panel
		line = br.readLine ();
		lineSpace = lineSpace + 1;

	    }
	    br.close ();
	}

	catch (IOException e)
	{
	}
    }


    //////////this is the method that shows rules///////////
    public static void showRules ()  //this method shows a new frame with the rules when called
    {

	final JFrame RULES;
	RULES = new JFrame ("Rules");
	RULES.setBounds (100, 100, 750, 700);
	RULES.dispatchEvent (new WindowEvent (RULES, WindowEvent.WINDOW_CLOSING));
	RULES.getContentPane ().setLayout (null);
	//this is the panel on which the message will be displayed
	JPanel rulePanel = new JPanel (new FlowLayout ());
	panelDeclare (rulePanel, 750, 700);
	addRules (rulePanel);
	//making the frame visible
	RULES.getContentPane ().add (rulePanel);
	RULES.setResizable (false);
	RULES.setVisible (true);
    }
}

//listener
class myKeyListener extends JFrame implements KeyListener
{
    //implement all the possible actions on keys
    public void keyPressed (KeyEvent keyEvent)
    {
	switch (keyEvent.getKeyCode ())
	{
	    case KeyEvent.VK_RIGHT:
		{
		    CA_SallyXiao.rightPlatform ();
		}
		break;
	    case KeyEvent.VK_LEFT:
		{
		    CA_SallyXiao.leftPlatform ();
		}
		break;

	}
	repaint ();  //this is here to ensure that the screen updates
    }


    public void keyReleased (KeyEvent keyEvent)
    {
	CA_SallyXiao.velX = 0;
    }


    public void keyTyped (KeyEvent keyEvent)
    {

    }
}


