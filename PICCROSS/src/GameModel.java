import java.util.StringTokenizer;

public class GameModel {

	/**
	 * Global variables.
	 */
	private StringTokenizer tokenizer;
	private int dim = 8;
	private String[] checkEachStringLeft = new String[dim];
	private String[] checkEachStringTop = new String[dim];
	private String pattern = "00011000,00011000,00011000,11111111,00111100,00111100,00100100,00100100";
	public boolean[][] patternAt = new boolean[dim][dim]; 
	private String[] leftPanelStorage = new String[dim];
	private String[] topPanelStorage = new String[dim];
	int StringTokenCount = 0;
		
	// Non- parameterized constructor
	public GameModel() {
	}
	
		/**
		 * This method is for left panel and is performing the role to get the string, break it down to tokens using distinguishingString
		 * method and finally with the help of conditional statements we are converting the binary to decimal.
		 */
		 public void leftPanelNumbering() {
			 tokenizer = distinguishingString(pattern);
		
			while(tokenizer.hasMoreTokens()) {
				for(int i=0; i < dim; i++) {
					checkEachStringLeft[i] = tokenizer.nextToken();
				}
			}
			for(int i = 0; i < dim; i++) {
				for(int j=0; j < dim; j++) {
					if(checkEachStringLeft[i].charAt(j) == '1') {
						patternAt[i][j] = true;
					}
					else   
						patternAt[i][j] = false;
				}
			}
			int[] j = new int[dim];
			
			for(int i =0; i<dim; i++) {
		
				j[i] = Integer.parseInt(checkEachStringLeft[i]);	
			}
			for(int i = 0; i < dim; i++) {
				if(checkEachStringLeft[i] != null) {
					leftPanelStorage[i] = "";
				}
					for(int z=0; z < dim; z++) {
						if(checkEachStringLeft[i].charAt(z) == '1') {
							StringTokenCount++;
							leftPanelStorage[i] = leftPanelStorage[i] +  String.valueOf(StringTokenCount);
						}
						if(z != 7) {
							if(checkEachStringLeft[i].charAt(z) == '1' && checkEachStringLeft[i].charAt(z+1) == '1') {}
							else if(checkEachStringLeft[i].charAt(z) != '1' && checkEachStringLeft[i].charAt(z+1) == '1') {
								StringTokenCount = 0;
								leftPanelStorage[i]= leftPanelStorage[i] +  "" + "," ; 
							}		
						}	 
					}			
				}	
			for(int i=0; i < dim; i++) {
				if(leftPanelStorage[i].charAt(0) == ',') {
					leftPanelStorage[i] = leftPanelStorage[i].substring(0,0)+' '+leftPanelStorage[i].substring(1);
				}
				if(leftPanelStorage[i].length() > 1 && !(leftPanelStorage[i].contains(","))) {
					leftPanelStorage[i] = String.valueOf(leftPanelStorage[i].charAt(leftPanelStorage[i].length()-1));
		
				}
				if(leftPanelStorage[i].length() > 1 && (leftPanelStorage[i].contains(","))) {
					leftPanelStorage[i] = String.valueOf(leftPanelStorage[i].charAt(leftPanelStorage[i].indexOf(",")-1)) + 
							leftPanelStorage[i].charAt(leftPanelStorage[i].indexOf(',')) + leftPanelStorage[i].charAt(leftPanelStorage[i].length()-1);
				}
			}	
      }
		 
	public static StringTokenizer distinguishingString (String args) {
			StringTokenizer stokenizer = new StringTokenizer(args, ",");
			return stokenizer;
	}
	
	/**
	 * This method is for top panel and is performing the role to get the string and with the help of conditional statements we are converting the binary to decimal.
	 */
	public void topPanelNumbering() {
		for(int i= 0;i < dim; i++) {
			checkEachStringTop[i] = "";
		}

		for(int z = 0; z<dim; z++){
			for(int i = 0; i < dim; i++) {	
				checkEachStringTop[z] += String.valueOf(checkEachStringLeft[i].charAt(z));	
			}
		}
		
		for(int i = 0; i < dim; i++) {
			int counter = 0;
			if(checkEachStringTop[i] != null) {
				topPanelStorage[i] = "";
				for(int z=0; z < dim; z++) {
					if(checkEachStringTop[i].charAt(z) == '1') {
						counter++;
						topPanelStorage[i] = topPanelStorage[i] +  String.valueOf(counter);
					}
					if(z != 7) {
						if(checkEachStringTop[i].charAt(z) == '1' && checkEachStringTop[i].charAt(z+1) == '1') {}
						else if(checkEachStringTop[i].charAt(z) != '1' && checkEachStringTop[i].charAt(z+1) == '1') {
							counter = 0;
							topPanelStorage[i] = topPanelStorage[i] +  "" + "," ; 
						}			
					}				 
				}	
			}
		}
		
		for(int i=0; i < dim; i++) {
			if(topPanelStorage[i].charAt(0) == ',') {
				topPanelStorage[i] = topPanelStorage[i].substring(0,0)+' '+topPanelStorage[i].substring(1);
			}
			if(topPanelStorage[i].length() > 1 && !(topPanelStorage[i].contains(","))) {
				topPanelStorage[i] = String.valueOf(topPanelStorage[i].charAt(topPanelStorage[i].length()-1));
	
			}
			if(topPanelStorage[i].length() > 1 && (topPanelStorage[i].contains(","))) {
				topPanelStorage[i] = String.valueOf(topPanelStorage[i].charAt(topPanelStorage[i].indexOf(",")-1)) + 
						topPanelStorage[i].charAt(topPanelStorage[i].indexOf(',')) + topPanelStorage[i].charAt(topPanelStorage[i].length()-1);
	
			}
		}
	}
	
	//Getter for each token check
	public String[] getCheckEachStringLeft() {
		return checkEachStringLeft;
	}
	
	//Getter for left panel numbering
	public String[] getLeftPanelNumbering() {
		leftPanelNumbering();
		return leftPanelStorage;
	}
	
	//Getter for top panel numbering
	public String[] getTopPanelNumbering() {
		topPanelNumbering();
		return topPanelStorage;
	}
	
	//Getter for recognizing the pattern at the specified indices.
	public boolean getPatternAt()[][]{
		leftPanelNumbering();
		return patternAt;
	}
	
	//Getter for the length of the dimension.
	public int getDim() {
		return dim;
	}
	
			
}