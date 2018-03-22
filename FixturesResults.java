package test;

import javax.swing.JOptionPane;
import java.io.*;
import java.util.*;




public class FixtureResult
{
	private static int[][] leaderboard;
	private static Random rand = new Random();
	private static File files[] = {
			new File("Teams20172018.txt"),
			new File("Fixtures20172018.txt"),
			new File("Outcomes20172018.txt")
	};	
	private static String fileElements[];
	private static ArrayList<ArrayList<Integer>> fixtures = new ArrayList<ArrayList<Integer>>();
	
	/////////add here
	private static ArrayList<ArrayList<String>> teams = new ArrayList<ArrayList<String>>();

	
	
	public static void main(String[] args) throws IOException
	{
		// Random number
		// Math.random() returns a double between 0 and 0.9999999999
		// Get random number between 0 and 10
		int max = 10;
		int min = 0;
		int randomNum = rand.nextInt((max - min) + 1) + min;
		
		teams.add(new ArrayList<String>());
		teams.add(new ArrayList<String>());

		fixtures.add (new ArrayList<Integer>());
		fixtures.add (new ArrayList<Integer>());
		fixtures.add (new ArrayList<Integer>());
		
		
		// Put code to fill teams and fixtures below
		readInData();
		
		// Check output of teams
		System.out.println("Print contents of teams:");
		for (int i = 0; i < teams.size(); i++)
			for (int j = 0; j < teams.get(0).size(); j++)
				System.out.println("I: " + i + "\tJ: " + j);
		
		// Space in between
		System.out.println();
		
		// Check output of fixtures
		System.out.println("Print contents of fixtures:");
		for (int i = 0; i < fixtures.size(); i++)
			for (int j = 0; j < fixtures.get(0).size(); j++)
				System.out.println("I: " + i + "\tJ: " + j);
		
		
		System.out.println("Program finished");
	}
	
	
	/**
	 * Java Doc comments example
	 * This is just a test moth that creates a String ArrayList and initializes
	 * it with a few example String values
	 * @param first The first parameter for the method
	 * @param second The second parameter for the method
	 */
	public static boolean readInData() throws IOException
	{
		ArrayList<Integer> list = new ArrayList<Integer>(); // Dynamic, can contract and expand as needed
		// use list.size() to get the ArrayList size
		// Populate list with 10 values
		for (int i = 0; i < 10; i++) // Iterate through list
			list.add(i); // Set i = to each, appending it to the end
			
		// To get an element of array list
		int position = 4;
		list.get(position);
		
		// Remove item from list
		position = 3;
		list.remove(3);
		
		// 2D ArrayList
		ArrayList<ArrayList<String>> multidemList = new ArrayList<ArrayList<String>>();
		
		/*
			NOTE: ArrayLists start at size 0
			As you use the add() method, the size will increase
			Using the remove method, the size will decrease
		*/
		if (files[0].exists() && files[1].exists() && files[2].exists())
		{
			Scanner in;
			for(int i = 0; i < files.length; i++) {
				in = new Scanner (files[i]);
						while(in.hasNext()) // There is more data in the file
						{
							// Read in line from file, create String[] split by commas
							fileElements = (in.nextLine().split(","));
							fixtures.get(0).add(Integer.parseInt(fileElements[0]));
							fixtures.get(1).add(Integer.parseInt(fileElements[1]));
							fixtures.get(2).add(Integer.parseInt(fileElements[2]));
						}
				in.close();
			}
			return true;
		}
		else
		{	 
			System.out.println("One or more files could not be found");
			return false;
		}
	

	}


 public static void processResults()
  {
	int fixtureNumber, homeTeamScore, awayTeamScore, homeTeamNumber, awayTeamNumber;
	int position;
	for (int i = 0; i < fixtures.get(0).size(); i++)  
    {
	  fixtureNumber  = fixtures.get(0).get(i);
	  homeTeamScore  = fixtures.get(1).get(i);
	  awayTeamScore  = fixtures.get(2).get(i);
	  position       = fixtures.get(0).indexOf(fixtureNumber);
	  homeTeamNumber = fixtures.get(1).get(position);
	  awayTeamNumber = fixtures.get(2).get(position);
	  if (homeTeamScore == awayTeamScore)
	  {
		recordFixtureResultForHomeTeam(homeTeamNumber,0,1,0,homeTeamScore,awayTeamScore,1);
		recordFixtureResultForAwayTeam(awayTeamNumber,0,1,0,homeTeamScore,awayTeamScore,1);
	  }  
	  else if (homeTeamScore > awayTeamScore)
	  {
		recordFixtureResultForHomeTeam(homeTeamNumber,1,0,0,homeTeamScore,awayTeamScore,3);
		recordFixtureResultForAwayTeam(awayTeamNumber,0,0,1,homeTeamScore,awayTeamScore,0);  
	  }  
	  else
	  {
		recordFixtureResultForHomeTeam(homeTeamNumber,0,0,1,homeTeamScore,awayTeamScore,0);
		recordFixtureResultForAwayTeam(awayTeamNumber,1,0,0,homeTeamScore,awayTeamScore,3);  
	  }    
    }
  }	 
  
 public static void recordFixtureResultForHomeTeam(int hTN, int w, int d, int l, 
                                                       int hTS, int aTS, int p)
  {
	leaderboard[hTN-1][1]++;        			// gamesPlayed
	leaderboard[hTN-1][2]+= w;      			// homeWin
	leaderboard[hTN-1][3]+= d;      			// homeDraw
	leaderboard[hTN-1][4]+= l;      			// homeLoss
	leaderboard[hTN-1][5]+= hTS;    			// homeTeamScore
	leaderboard[hTN-1][6]+= aTS;    			// awayTeamScore
	leaderboard[hTN-1][12] += (hTS - aTS);    	// goalDifference
	leaderboard[hTN-1][13] += p;    			// points
  }
 
  public static void recordFixtureResultForAwayTeam(int aTN, int w, int d, int l, 
                                                       int hTS, int aTS, int p)
  {
	leaderboard[aTN-1][1]++;        			// gamesPlayed
	leaderboard[aTN-1][7]+= w;      			// awayWin
	leaderboard[aTN-1][8]+= d;      			// awayDraw
	leaderboard[aTN-1][9]+= l;      			// awayLoss
	leaderboard[aTN-1][10]+= aTS;    			// awayTeamScore
	leaderboard[aTN-1][11]+= hTS;    			// homeTeamScore
	leaderboard[aTN-1][12] += (aTS - hTS);    	// goalDifference
	leaderboard[aTN-1][13] += p;    			// points  
  }	
  
  
	/*
		// Cycle through 2D ArrayList 
		for (int i = 0; i < multidemList.size(); i++) // Cycle through overall list
			for (int j = 0; j < multidemList.get(0).size(); j++) // Cycle through list at i
				System.out.println(array.get(i).get(j));
		
		// For normal 2D array
		int[][] array = new int[5][5]; // 5x5 
		for (int i = 0; i < array.length; i++) // Cycle through overall list
			for (int j = 0; j < array[0].length; j++) // Cycle through list at i
				System.out.println(array[i][j]);
				
	*/
		
}


