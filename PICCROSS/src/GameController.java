/* 
 * Declaration: We (Keshav Sandhu and AlayKumar Patel) declare that the following code is created by our own logical thinking and is not copied from any outer resources.
 * Assessment: Assignment 2
 * Prof: Paulo Sausa
 * Section number: CST8221_300
 * Name: Keshav Sandhu (040994718)
 * 		 Alaykumar Patel (041001515)
 * Purpose: Functionality of the "Piccross game".
 */

import javax.swing.*;
import java.awt.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class GameController extends JFrame {
    
    /**
     * Global variables
     */
	private static final long serialVersionUID = 1L;
	/*
	 * Declaration of several JPanels, JCheckBox, JLabel, JTextField, JButton, ImageIcon.
	 */
	private JFrame frame = new JFrame("SandhuK_PatelA");	
	private JPanel mainPanel, boardPanel, controlPanel, topPanel, leftPanel, markPanel;
	public JTextArea pntPanel;
    private JCheckBox chkBox;
    private ImageIcon icon;
    private JButton picBtn = new JButton();
    private JButton howToPlayTheGame, resetBtn;
    private JButton[][] btns = new JButton[8][8];
    private int i=0, j=0;
    private Controller con = null;
    private GameModel model = new GameModel();
    private JLabel pointsGained = null;
	private String score = "0"; 		
	protected String timer = "0";
	int pointGained = Integer.parseInt(score);
	boolean checkBoxMarkSelection = false;
	private String[][] buttonName = new String[model.getDim()][model.getDim()];
	private int count = 0;
	protected String timeElasped = "0";   
	protected JMenuBar bar;    
	protected JMenu Help, Game;    
	protected JMenuItem New, Solution, Exit, Colors, About;    
	protected JTextArea ta;
	protected JPanel J1, J2, J3, J11;
	protected JButton jb1, jb2, jb3;
	private Color correctColor = Color.GREEN;
	private Color markedColor  = Color.yellow;
	private Color incorrectColor = Color.RED;
	
	/**
	 * Creating an 2D array for the buttons labels.
	 */
    private String[][] buttonValues = {
			{"(1,1)", "(1,2)", "(1,3)", "(1,4)", "(1,5)", "(1,6)", "(1,7)", "(1,8)"},
			{"(2,1)", "(2,2)", "(2,3)", "(2,4)", "(2,5)", "(2,6)", "(2,7)", "(2,8)"},
			{"(3,1)", "(3,2)", "(3,3)", "(3,4)", "(3,5)", "(3,6)", "(3,7)", "(3,8)"},
			{"(4,1)", "(4,2)", "(4,3)", "(4,4)", "(4,5)", "(4,6)", "(4,7)", "(4,8)"},
			{"(5,1)", "(5,2)", "(5,3)", "(5,4)", "(5,5)", "(5,6)", "(5,7)", "(5,8)"},
			{"(6,1)", "(6,2)", "(6,3)", "(6,4)", "(6,5)", "(6,6)", "(6,7)", "(6,8)"},
			{"(7,1)", "(7,2)", "(7,3)", "(7,4)", "(7,5)", "(7,6)", "(7,7)", "(7,8)"},
			{"(8,1)", "(8,2)", "(8,3)", "(8,4)", "(8,5)", "(8,6)", "(8,7)", "(8,8)"}
	};
    
    /*
     * Instantiation object of Control class.
     */
    Controller control = new Controller();

    /*
     * Non-Parameterized constructor.
     */
    public GameController() {
    	JFrame frame = new JFrame("SandhuK_PatelA");											//Frame instantiation object.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creating the menu for the game.
        New = new JMenuItem("New", new ImageIcon("G:\\Sem_04\\piciconnew.gif"));    
        Solution = new JMenuItem("Solution", new ImageIcon("G:\\Sem_04\\piciconsol.gif"));    
        Exit = new JMenuItem("Exit", new ImageIcon("G:\\Sem_04\\piciconext.gif"));    
        Colors = new JMenuItem("Colors", new ImageIcon("G:\\Sem_04\\piciconcol.gif"));
        About = new JMenuItem("About", new ImageIcon("G:\\Sem_04\\piciconabt.gif"));
        Colors.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		colorChanger();																	//invoking the method for action.
        	}
        });
        
        bar=new JMenuBar();    
        Game=new JMenu("Game");       
        Help=new JMenu("Help");     
        Game.add(New);
        Game.add(Solution);
        Game.add(Exit);
        Help.add(Colors);    
        Help.add(About);  
        bar.add(Game);bar.add(Help);  
        ta=new JTextArea();        
        frame.add(bar);
        frame.add(ta);    
        frame.setJMenuBar(bar);  
        frame.setLayout(null);
        
        //ActionListener for Exit
    	ActionListener exit = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				frame.dispose();
				
			}
		};
    	Exit.addActionListener(exit);
    	
    	//ActionListener for about
    	ActionListener abt = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JLabel a = new JLabel(new ImageIcon(getClass().getResource("piccrossLogo.jpg")));
				JOptionPane.showMessageDialog(null, a);
				
			}
		};
    	About.addActionListener(abt);
    	
    	//ActionListener for new
    	ActionListener nw = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				GameController gameController = new GameController();
				
			}
		};
    	New.addActionListener(nw);
    	
    	//ActionListener for Solution.
    	Solution.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) { 
    			for(int i =0; i <model.getDim(); i++) {
    				System.out.println(model.getCheckEachStringLeft()[i]);
    				for(int s=0; s < model.getDim(); s++) {
    					if(model.getCheckEachStringLeft()[i].charAt(s) == '1') {
    						btns[i][s].setBackground(correctColor);
    					}
    					else if(model.getCheckEachStringLeft()[i].charAt(s) == '0') {
    						btns[i][s].setBackground(markedColor);
    					}
    				}
    			}	
    		}
    	});
    				
    			Border blackline = BorderFactory.createLineBorder(Color.black, 3);			//Border layout declaration.
    			mainPanel = new JPanel();													//Initialization of mainPanel.
                mainPanel.setLayout(new GridBagLayout());									//Setting up the layout manager.
                GridBagConstraints gidConstraint = new GridBagConstraints();				// GridBagConstraint object instantiation.
                
                markPanel = new JPanel();													//Initialization of markPanel.
                markPanel.setPreferredSize(new Dimension(10, 100));							//Setting up the dimension Size
                markPanel.setBackground(Color.green);										//Providing the background color to the Panel.
                markPanel.setBorder(BorderFactory.createLineBorder(Color.black, 3));		//Giving the border color.
                
                leftPanel = new JPanel(new GridLayout(8,0));													//Initialization of leftPanel.
                leftPanel.setMinimumSize(new Dimension(150, 100));							//Setting up the dimension Size
                leftPanel.setMaximumSize(new Dimension(150, 100));
                leftPanel.setPreferredSize(new Dimension(150, 100));
                leftPanel.setBackground(Color.lightGray);									//Providing the background color to the Panel.
                leftPanel.setBorder(BorderFactory.createLineBorder(Color.black, 3));		//Giving the border color.
                
                controlPanel = new JPanel();												//Initialization of controlPanel.
                controlPanel.setLayout(new GridLayout(4, 7));								//Setting up the layout manager.
                controlPanel.setMinimumSize(new Dimension(225, 100));						//Setting up the dimensions.
                controlPanel.setMaximumSize(new Dimension(225, 100));
                controlPanel.setPreferredSize(new Dimension(225, 100));		
                controlPanel.setBackground(Color.red);										//Providing the background color to the Panel.
                controlPanel.setBorder(BorderFactory.createLineBorder(Color.black, 3));		//Providing the border color to the Panel.
                
                boardPanel = new JPanel();													//Initialization of borderPanel.
                boardPanel.setLayout(new GridLayout(8, 8));									//Setting up the layout manager.
                boardPanel.setMinimumSize(new Dimension(700, 600));							//Fixing the dimension size.
                boardPanel.setMaximumSize(new Dimension(700, 600));
                boardPanel.setPreferredSize(new Dimension(700, 600));
                boardPanel.setBorder(blackline);	
                
                con = new Controller();
                
                //invoking the getPatternAt function to recognize the pattern of the game in boolean representation.
                for (int i = 0; i  < model.getDim(); i++) {
        			for(int j=0; j < model.getDim(); j++) {
        				
        				btns[i][j] = creationOfButtons(buttonValues[i][j], model.getPatternAt()[i][j], con);	//Invoking the CreationOfButtons() method.
        				btns[i][j].setBackground(new Color(6, 244, 214));
        				btns[i][j].setBorder(BorderFactory.createLineBorder(Color.white, 3));
        				btns[i][j].setBackground(Color.black);
        			}
        		}
        		// Adding the buttons to the board panel
        		for (int i = 0; i < model.getDim(); i++) {
        			for(int j = 0; j < model.getDim(); j++) {
        				boardPanel.add(btns[i][j]);
        			}
        		}
                //setting up the border.												
                
                topPanel = new JPanel(new GridLayout(0,8));									//Initializing the topPanel
                topPanel.setMinimumSize(new Dimension(100, 100));							//Setting the dimension size.
                topPanel.setMaximumSize(new Dimension(100, 100));
                topPanel.setPreferredSize(new Dimension(100, 100));
                topPanel.setBackground(Color.lightGray);									//Setting the background color.
                topPanel.setBorder(BorderFactory.createLineBorder(Color.black, 3));			//giving the border color.
                
                //This is for computing the numbers in terms of binary operation.
                String[] topNumbering = model.getTopPanelNumbering();
        		for(int i=0; i<model.getDim();i++) {
        			JPanel Tcol = new JPanel(new GridLayout(1,0));
        			JLabel TL = new JLabel(topNumbering[i], JLabel.CENTER);
        			TL.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
        			TL.setForeground(Color.BLUE);
        			Tcol.add(TL);
        			Tcol.setBorder(BorderFactory.createLineBorder(Color.black, 4));	//setting the border
        			Tcol.setBackground(Color.WHITE);
        			topPanel.add(Tcol);	
        		}
                
                gidConstraint.gridx = 0;													//Making changes with the help of gridBagContraint function.
                gidConstraint.gridy = 0;
                gidConstraint.gridheight = 1;
                gidConstraint.weighty = 0;
                gidConstraint.weightx = 0;
                gidConstraint.fill = GridBagConstraints.HORIZONTAL;							//Setting up the panel Horizontal fill.
                mainPanel.add(markPanel, gidConstraint);									//adding the panel to mainPanel
                
                gidConstraint.gridx = 0;
                gidConstraint.gridy = 1;
                gidConstraint.gridheight = 2;
                gidConstraint.weighty = 1;
                gidConstraint.weightx = 0;
                gidConstraint.fill = GridBagConstraints.VERTICAL;							//Setting up the panel Vertical fill.
                mainPanel.add(leftPanel, gidConstraint);									//adding the panel to mainPanel.
                
              //This is for computing the numbers in terms of binary operation.
	            String[] leftNumbering = model.getLeftPanelNumbering();
                for(int i=0; i<model.getDim();i++) {
        			JPanel Lcol = new JPanel(new GridLayout(0,1));
        			JLabel LL = new JLabel(leftNumbering[i], JLabel.CENTER);
        			LL.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
        			LL.setForeground(Color.BLUE);
        			Lcol.add(LL);
        			Lcol.setBorder(BorderFactory.createLineBorder(Color.black, 4));	//setting the border
        			Lcol.setBackground(Color.WHITE);
        			leftPanel.add(Lcol);	//adding to the main panel 
        		}
                
	            gidConstraint.gridx = 1;													//Making changes with the help of gridBagContraint function.
	            gidConstraint.gridy = 0;
	            gidConstraint.gridheight = 1;
	            gidConstraint.gridwidth = 2;
	            gidConstraint.weighty = 0;
	            gidConstraint.weightx = 1;
	            gidConstraint.fill = GridBagConstraints.HORIZONTAL;							//Setting up the panel Horizontal fill.
                mainPanel.add(topPanel, gidConstraint);										//adding the panel to mainPanel

                gidConstraint.gridx = 1;
                gidConstraint.gridy = 1;
                gidConstraint.fill = GridBagConstraints.BOTH;								//Setting up the panel Both fill.
                gidConstraint.weightx = 1;
                gidConstraint.weighty = 1;
                mainPanel.add(boardPanel,gidConstraint);									//adding the panel to mainPanel

                gidConstraint.gridx = 4;
                gidConstraint.gridy = 0;
                gidConstraint.gridheight = 2;
                gidConstraint.gridwidth = 2;
                gidConstraint.weighty = 1;
                gidConstraint.weightx = 0;
                gidConstraint.fill = GridBagConstraints.VERTICAL;							//Setting up the panel Vertical fill.
                mainPanel.add(controlPanel, gidConstraint);									//Adding to mianPanel.
                		
                markPanel.setLayout(new FlowLayout());										//Setting up the Layout manager to FlowLAyout for the checkBox in MarkPanel
                chkBox = new JCheckBox();
                chkBox.setText("Mark Panel");												//Adding the component to MArkPAnel
                		
                // ActionListener for the mark panel whether it is selected or not.
                ActionListener markAct = null;
        		chkBox.setText("Mark Panel");
        		markAct = new ActionListener() {
        				public void actionPerformed(ActionEvent e) {
        					if(checkBoxMarkSelection == true) {
        						pntPanel.append("Check Unselected\n");
        						checkBoxMarkSelection = false;
        						
        					}
        					else{
        						pntPanel.append("Check Selected\n" );
        						checkBoxMarkSelection = true;
        					}
        				}			
        			};
        		chkBox.addActionListener(markAct);
        		markPanel.add(chkBox);
        		
                controlPanel.setLayout(new FlowLayout());									//Setting up the Layout manager to FlowLAyout for the Image in ControlPanel
                icon = new ImageIcon("G:\\Sem_04\\piccrossNameMin.jpg");					//Initializing the Image function and setting up the image.
                picBtn.setIcon(icon);
                picBtn.setBorder(BorderFactory.createLineBorder(Color.black, 4));
                
                // ActionListener for the Piccross icon
                ActionListener piccrossAct = new ActionListener(){	
                    public void actionPerformed(ActionEvent e) {
                    	JFrame jf1 = new JFrame ("Piccross");
            	        jf1.setSize (500, 200);
            	        JLabel a = new JLabel(new ImageIcon(getClass().getResource("piccrossLogo.jpg")));
            	        jf1.add(a);
            	        jf1.pack();
                        jf1.setVisible(true);
                    }
                };
                picBtn.addActionListener(piccrossAct);
                controlPanel.add(picBtn);	
                
                //Adding to control Panel
                JPanel pointAndTimePanel = new JPanel(new GridLayout(2,2,5,5)); //making the grid*/
        		pointAndTimePanel.setPreferredSize(new Dimension(200, 50));
        		JLabel point = new JLabel("Points gained: ", JLabel.CENTER);

        		point.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 10));
        		point.setForeground(Color.black);

        		pointsGained = new JLabel(score, JLabel.CENTER );
        		pointsGained.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        		JLabel time = new JLabel("Time taken: ", JLabel.CENTER);

        		time.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 10));
        		time.setForeground(Color.black);
        		timeElasped = String.valueOf(count);
        		JLabel timeTaken = new JLabel(timeElasped, JLabel.CENTER);
        		
        		// ActionListener for the time
        		ActionListener tim = new ActionListener(){	
                    public void actionPerformed(ActionEvent e) {
                        timeTaken.setText(String.valueOf(count)+ "s");
                        count++;	
                    }
                };
                Timer timer = new Timer(1000, tim);
                timer.start();
                
        		timeTaken.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
        		pointAndTimePanel.add(point);
        		pointAndTimePanel.add(pointsGained);
        		pointAndTimePanel.add(time);
        		pointAndTimePanel.add(timeTaken);
        		pointAndTimePanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.black));

        		controlPanel.add(pointAndTimePanel, BorderLayout.PAGE_END);
        		/*---------------------------------------------------------------------------------------------------*/

        		//controlPanel.setBorder(paneBorder);

                
                pntPanel = new JTextArea();													//Initializing the pntPanel
                pntPanel.setPreferredSize(new Dimension(200, 445));							//Giving the panel Dimensions.
                pntPanel.setBackground(Color.white);										//Setting back ground color
                pntPanel.setBorder(BorderFactory.createLineBorder(Color.black, 5));			//Borders all side.
                
                gidConstraint.gridx = 0;
                gidConstraint.gridy = 1;
                gidConstraint.gridheight = 2;
                gidConstraint.weighty = 1;
                gidConstraint.weightx = 0;
                gidConstraint.fill = GridBagConstraints.VERTICAL;							//Setting up the panel Vertical fill.
                controlPanel.add(pntPanel, gidConstraint);									//Adding to the controlPAnel.
                
                Icon resetImage = new ImageIcon("G:\\Sem_04\\reset.jpg");					//Icon For the reset button.
                resetBtn = new JButton(resetImage);											//Button initializing
                resetBtn.setPreferredSize(new Dimension(195, 70));							//Button dimensions
                resetBtn.setBorder(BorderFactory.createLineBorder(Color.black, 5));
                
                // ActionListener for the Reset button
                ActionListener resetAct = new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						frame.dispose();
						GameController gameControl = new GameController();
					
					}
				};	
				resetBtn.addActionListener(resetAct);			//Border layout.
                controlPanel.add(resetBtn);													//Adding to control panel.
                
                Icon questionMarkImage = new ImageIcon("G:\\Sem_04\\questionMark.jpg");		//Icon For the question mark button.
                howToPlayTheGame = new JButton(questionMarkImage);							//Button initializing
                howToPlayTheGame.setPreferredSize(new Dimension(195, 70));					//Button dimensions
                howToPlayTheGame.setBorder(BorderFactory.createLineBorder(Color.black, 5));	//Border layout.
                controlPanel.add(howToPlayTheGame);	
                	
                frame.setLayout(new BorderLayout());
                frame.add(mainPanel);														//Adding the main panel to frame
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
    }
    
    /**
     * This method is playing very crucial role to create the buttons and give them the colors on action according to the 
     * pattern provided in the binary format.
     * @param action
     * @param check
     * @param c
     * @return
     */
    private JButton creationOfButtons(String action, boolean check, Controller c) {
		JButton button = new JButton();
		
		button.setActionCommand("Pos " + action + " clicked!!");
		// ActionListener for the  BUttons on click
		ActionListener btnsAct = new ActionListener() {
			public void actionPerformed(ActionEvent w) {
				if(checkBoxMarkSelection == true) {
					if(check == false) {
						button.setBackground(markedColor);
						button.setEnabled(false);
						pointGained++;
						pointsGained.setText(String.valueOf(pointGained));
					}
					else {
						button.setBackground(incorrectColor);
						button.setEnabled(false);
						pointGained--;
						pointsGained.setText(String.valueOf(pointGained));
					}
				}
				else {
					if(check == true) {
						button.setBackground(correctColor);
						button.setEnabled(false);
						pointGained++;
						pointsGained.setText(String.valueOf(pointGained));
					}
					else {
						button.setBackground(incorrectColor);
						button.setEnabled(false);
						pointGained--;
						pointsGained.setText(String.valueOf(pointGained));
					}
				}
				
				
			}
		};
		button.addActionListener(btnsAct);
		button.addActionListener(c);
		
		return button;
	}
    
    // ActionListener for the point panel
    private class Controller implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			pntPanel.append(e.getActionCommand() + "\n");
		}
	}
    
    /**
     * This method is for creating the frame to open the dialog box for color chooser and assign the new color each
     * time.
     */
    	  public void colorChanger(){
    	        JDialog CC = new JDialog(frame , "Choose the color", true);
    	        CC.setLayout( new GridLayout(2, 3));

    	        JPanel b1 = new JPanel();
    	        JPanel b2 = new JPanel();
    	        JPanel b3 = new JPanel();

    	        b1.setBackground(correctColor);
    	        b2.setBackground(markedColor);
    	        b3.setBackground(incorrectColor);

    	        CC.add(b1);
    	        CC.add(b2);
    	        CC.add(b3);

    	        JButton btn1 = new JButton("Color1:Correct");
    	        JButton btn2 = new JButton("Color2:Marked");
    	        JButton btn3 = new JButton("Color3:Error");

    	        CC.add(btn1);
    	        CC.add(btn2);
    	        CC.add(btn3);

    	        btn1.addActionListener ( new ActionListener()
    	        {
    	            public void actionPerformed( ActionEvent e )
    	            {
    	                Color updateColor = colorChooser(correctColor, CC);
    	                changeCellColors(correctColor, updateColor);
    	                correctColor = updateColor;
    	                b1.setBackground(correctColor);
    	            }
    	        });
    	        btn2.addActionListener ( new ActionListener()
    	        {
    	            public void actionPerformed( ActionEvent e )
    	            {
    	                Color updateColor = colorChooser(markedColor, CC);
    	                changeCellColors(markedColor, updateColor);
    	                markedColor = updateColor;
    	                b2.setBackground(markedColor);
    	            }
    	        });
    	        btn3.addActionListener ( new ActionListener()
    	        {
    	            public void actionPerformed( ActionEvent e )
    	            {
    	                Color updateColor = colorChooser(incorrectColor, CC);
    	                changeCellColors(incorrectColor, updateColor);
    	                incorrectColor = updateColor;
    	                b3.setBackground(incorrectColor);
    	            }
    	        });
    	        CC.pack();
    	        CC.setLocationRelativeTo(null);
    	        CC.setVisible(true);
    	    }
    			
    		/**
    		 * This will open the color chooser dialog
    		 * @param defaultColor
    		 * @param fram
    		 * @return color
    		 */
    		 public Color colorChooser(Color defaultColor, JDialog fram){
    		        Color color = JColorChooser.showDialog(fram, "Choose Color", defaultColor);
    		        if(color == null){
    		            color = defaultColor;
    		        }
    		        return color;
    		    }

    		/**
    		 * This will set the modified colors to the buttons on the board panel.
    		 * @param previousColor
    		 * @param newColor
    		 */
    	    public void changeCellColors(Color previousColor, Color newColor){
    	        for(int i = 0; i < model.getDim() ; i++){
    	        	for(int  j=0; j < model.getDim(); j++) {
    	        		 if(btns[i][j].getBackground() == previousColor){
    	                     btns[i][j].setBackground(newColor);
    	                 }
    	        	}
    	        }
    	    } 
    	
}