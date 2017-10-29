package streamupdater.files;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import streamupdater.gui.components.StreamUpdater;

public class FileManager {

	private static String path = "";
	
	private static String[] parentDirectories = { "Media", "Game", "Text", "Saves", "Templates", "Uploading" };
	private static String[] gameSubDir = { "Characters", "Commentators", "Players", "Sponsors" };
	
	private String[] files = { "maintitle.txt", "round.txt", "player1name.txt", "player2name.txt", "player1score.txt", "player2score.txt", "commentator1.txt", "commentator2.txt", "player1character.png", 
				 "player1sponsor.png", "player2character.png", "player2sponsor.png", "commentator1sponsor.png", "commentator2sponsor.png", "playerOneCharacter.txt", "playerTwoCharacter.txt", "player3name.txt", "player4name.txt", "team1name.txt", "team2name.txt", "player3character.png", "player4character.png", "playerThreeCharacter.txt", "playerFourCharacter.txt", 
				 "schoolOneName.txt", "schoolTwoName.txt", "schoolOneP1.txt", "schoolOneP2.txt", "schoolOneP3.txt", "schoolOneP4.txt", "schoolOneP5.txt", "schoolTwoP1.txt", "schoolTwoP2.txt", "schoolTwoP3.txt", "schoolTwoP4.txt", "schoolTwoP5.txt"};
	
	private String[] fileContents = { "Main Title", "Current Round", "Player One", "Player Two", "0", "0", "Commentator1", "Commentator2", "null", "null", "null", "null", "null", "null", "", "", "Player Three", "Player Four", "Team 1", "Team 2", "null", "null", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
	
	private int playerTwoScore = 0;
	private String playerTwo = "Player Two";  
	private int playerOneScore = 0;
	private String playerOne = "Player One";
	private String playerOneInfo = "@PlayerOne";
	private String playerTwoInfo = "@PlayerTwo";
	private String currentRound = "Current Round";
	private String mainTitle = "Main Title";
	private String commentators[] = { "Commentator1", "Commentator2" };
	private String commentatorsInfo[] = { "@Commentator1", "@Commentator2" };
	private String playerOneCharacterText = "";
	private String playerTwoCharacterText = "";
	
	// school one
	private String schoolOneName = "";
	private String schoolOneP1 = "";
	private String schoolOneP2 = "";
	private String schoolOneP3 = "";
	private String schoolOneP4 = "";
	private String schoolOneP5 = "";
	
	// school two
	private String schoolTwoName = "";
	private String schoolTwoP1 = "";
	private String schoolTwoP2 = "";
	private String schoolTwoP3 = "";
	private String schoolTwoP4 = "";
	private String schoolTwoP5 = "";
	
	private static boolean sleeping = false;
	  
	/*
	 * Doubles
	 */
	private String playerThree = "Player Three";
	private String playerThreeInfo = "@PlayerThree";
	private String playerThreeCharacterText = "";
	private String playerFour = "Player Four";
	private String playerFourInfo = "@PlayerFour";
	private String playerFourCharacterText = "";
	private String teamOne = "Team 1";
	private String teamTwo = "Team 2";
	private String teamCombineCharacter = "&";
	private BufferedImage playerThreeCharacter;
	private BufferedImage playerFourCharacter;
	  
	  
	private BufferedImage playerOneCharacter;
	private BufferedImage playerTwoCharacter;
	private BufferedImage playerOneSponsor;
  	private BufferedImage playerTwoSponsor;
  	private BufferedImage commentatorOneSponsor;
  	private BufferedImage commentatorTwoSponsor;
	
	public void init() {

		File file = new File(path = getPath("StreamUpdater/"));
		createSource(file);
		readFiles();
		
	}
	
	private void createSource(File f) {
		
		try {
		
			// create main dir
			f.mkdir();
			
			// create sub parent directories
			for(int i = 0; i < parentDirectories.length; i++) {
				String tmp = f.getPath() + "/" + parentDirectories[i];
				File tmpf = new File(tmp);
				if(!tmpf.exists()) tmpf.mkdir();
			}
			
			// create sub dir to game dir
			for(int i = 0; i < gameSubDir.length; i++) {
				String tmp = f.getPath() + "/" + parentDirectories[1] + "/" + gameSubDir[i];
				File tmpf = new File(tmp);
				if(!tmpf.exists()) tmpf.mkdir();
			}
			
			// create files for text dir
			for(int i = 0; i < files.length; i++) {
				String tmp = f.getPath() + "/" + parentDirectories[2] + "/" + files[i];
				File tmpf = new File(tmp);
				if(!tmpf.exists()) {
					tmpf.createNewFile();
					PrintWriter writer = new PrintWriter(tmpf);
					writer.print(fileContents[i]);
					writer.close();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private String getPath(String s) {
		return System.getProperty("user.home") + "/Documents/" + s;
	}
	
	/*
	 * Path returns
	 */
	
	public static String getUploadingDirectory() {
		return path + "/" + parentDirectories[5];
	}
	
	public static String getTemplatesDirectory() {
		return path + "/" + parentDirectories[4];
	}
	
	public static String getMediaDirectory() {
		return path + "/" + parentDirectories[0];
	}
	
	public static String getSaveDirectory() {
		return path + "/" + parentDirectories[3];
	}
	
	public static String getPlayersDirectory() {
		return path + "/" + parentDirectories[1] + "/" + gameSubDir[2];
	}
	
	public static String getCharactersDirectory() {
		return path + "/" + parentDirectories[1] + "/" + gameSubDir[0];
	}

	public static String getCommentatorsDirectory() {
		return path + "/" + parentDirectories[1] + "/" + gameSubDir[1];
	}
	
	public static String getSponsorsDirectory() {
		return path + "/" + parentDirectories[1] + "/" + gameSubDir[3];
	}
	
	public static String getTextDirectory() {
		return path + "/" + parentDirectories[2] +"/";
	}
	
	/*
	 * Return information
	 */
	public static ArrayList<String> getCommentators() {
		try {
			File commentators = new File(getCommentatorsDirectory());
			File[] list = commentators.listFiles();
			ArrayList<String> tmp = new ArrayList<String>();
			for(int i = 0; i < list.length; i++) {
				if(list[i].getName().contains(".txt")) {
					String line = null;
					BufferedReader reader = new BufferedReader(new FileReader(list[i]));
					if((line = reader.readLine()) != null) {
						tmp.add(line);
					}
					reader.close();
				}
			}
			return tmp;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<String> getPlayers() {
		
		try {
			File players = new File(getPlayersDirectory());
			File[] list = players.listFiles();
			ArrayList<String> tmp = new ArrayList<String>();
			for(int i = 0; i < list.length; i++) {
				if(list[i].getName().contains(".txt")) {
					String line = null;
					BufferedReader reader = new BufferedReader(new FileReader(list[i]));
					if((line = reader.readLine()) != null) {
						tmp.add(line);
					}
					reader.close();
				}
			}
			return tmp;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getPlayersInfo(String s) {
		
		try {
			File players = new File(getPlayersDirectory());
			File[] list = players.listFiles();
			String tmp = "";
			for(int i = 0; i < list.length; i++) {
				if(list[i].getName().contains(".txt") && list[i].getName().contains(s)) {
					String line = null;
					BufferedReader reader = new BufferedReader(new FileReader(list[i]));
					if((line = reader.readLine()) != null) {
						if((line = reader.readLine()) != null) {
							tmp = line;
							break;
						}
					}
					reader.close();
				}
			}
			return tmp;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
		
	}
	
	public String getCommentatorsInfo(String s) {
		
		try {
			File com = new File(getCommentatorsDirectory());
			File[] list = com.listFiles();
			String tmp = "";
			for(int i = 0; i < list.length; i++) {
				if(list[i].getName().contains(".txt") && list[i].getName().contains(s)) {
					String line = null;
					BufferedReader reader = new BufferedReader(new FileReader(list[i]));
					if((line = reader.readLine()) != null) {
						if((line = reader.readLine()) != null) {
							tmp = line;
							break;
						}
					}
					reader.close();
				}
			}
			return tmp;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
		
	}
	
	public static ArrayList<File> getPlayerSponsors() {
		
		try {
			
			File players = new File(getPlayersDirectory());
			File[] list = players.listFiles();
			ArrayList<File> tmp = new ArrayList<File>();
			for(int i = 0; i < list.length; i++) {
				if(list[i].getName().contains(".png")) {
					tmp.add(list[i]);
				}
			}
			return tmp;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<File> getCommentatorSponsors() {
		
		try {
			
			File commentators = new File(getCommentatorsDirectory());
			File[] list = commentators.listFiles();
			ArrayList<File> tmp = new ArrayList<File>();
			for(int i = 0; i < list.length; i++) {
				if(list[i].getName().contains(".png")) {
					tmp.add(list[i]);
				}
			}
			return tmp;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static ArrayList<File> getSponsors() {
		
		try {
			
			File sponsors = new File(getSponsorsDirectory());
			File[] list = sponsors.listFiles();
			ArrayList<File> tmp = new ArrayList<File>();
			for(int i = 0; i < list.length; i++) {
				if(list[i].getName().contains(".png")) {
					tmp.add(list[i]);
				}
			}
			return tmp;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void wipePlayers() {
		try {
			
			File players = new File(getPlayersDirectory());
			File[] list = players.listFiles();
			for(int i = 0; i < list.length; i++) list[i].delete();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void wipeCommentators() {
		try {
			
			File commentators = new File(getCommentatorsDirectory());
			File[] list = commentators.listFiles();
			for(int i = 0; i < list.length; i++) list[i].delete();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void wipeSponsors() {
		try {
			
			File sponsor = new File(getSponsorsDirectory());
			File[] list = sponsor.listFiles();
			for(int i = 0; i < list.length; i++) list[i].delete();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * School saving config
	 */
	
	public void setSchoolOne(String n) {
		schoolOneName = n;
	}
	
	public void setSchoolTwo(String n) {
		schoolTwoName = n;
	}
	
	public void setSchoolOnePlayerOne(String n) {
		schoolOneP1 = n;
	}
	
	public void setSchoolOnePlayerTwo(String n) {
		schoolOneP2 = n;
	}
	
	public void setSchoolOnePlayerThree(String n) {
		schoolOneP3 = n;
	}
	
	public void setSchoolOnePlayerFour(String n) {
		schoolOneP4 = n;
	}
	
	public void setSchoolOnePlayerFive(String n) {
		schoolOneP5 = n;
	}
	
	public void setSchoolTwoPlayerOne(String n) {
		schoolTwoP1 = n;
	}
	
	public void setSchoolTwoPlayerTwo(String n) {
		schoolTwoP2 = n;
	}
	
	public void setSchoolTwoPlayerThree(String n) {
		schoolTwoP3 = n;
	}
	
	public void setSchoolTwoPlayerFour(String n) {
		schoolTwoP4 = n;
	}
	
	public void setSchoolTwoPlayerFive(String n) {
		schoolTwoP5 = n;
	}
	
	public String getSchoolOne() {
		return schoolOneName;
	}
	
	public String getSchoolTwo() {
		return schoolTwoName;
	}
	
	public String getSchoolOnePlayerOne() {
		return schoolOneP1;
	}
	
	public String getSchoolOnePlayerTwo() {
		return schoolOneP2;
	}
	
	public String getSchoolOnePlayerThree() {
		return schoolOneP3;
	}
	
	public String getSchoolOnePlayerFour() {
		return schoolOneP4;
	}
	
	public String getSchoolOnePlayerFive() {
		return schoolOneP5;
	}
	
	public String getSchoolTwoPlayerOne() {
		return schoolTwoP1;
	}
	
	public String getSchoolTwoPlayerTwo() {
		return schoolTwoP2;
	}
	
	public String getSchoolTwoPlayerThree() {
		return schoolTwoP3;
	}
	
	public String getSchoolTwoPlayerFour() {
		return schoolTwoP4;
	}
	
	public String getSchoolTwoPlayerFive() {
		return schoolTwoP5;
	}
	
	
	
	/*
	 * Old saving file configuration functions
	 */
	
	  public BufferedImage getPlayerOneCharacter() {
		  return playerOneCharacter;
	  }
	  
	  public BufferedImage getPlayerTwoCharacter() {
		  return playerTwoCharacter;
	  }
	  
	  public BufferedImage getPlayerThreeCharacter() {
		  return playerThreeCharacter;
	  }
	  
	  public BufferedImage getPlayerFourCharacter() {
		  return playerFourCharacter;
	  }
	  
	  public String getPlayerOneCharacterText() {
		  return playerOneCharacterText;
	  }
	  
	  public String getPlayerTwoCharacterText() {
		  return playerTwoCharacterText;
	  }
	  
	  public String getPlayerThreeCharacterText() {
		  return playerThreeCharacterText;
	  }
	  
	  public String getPlayerFourCharacterText() {
		  return playerFourCharacterText;
	  }
	  
	  public BufferedImage getPlayerOneSponsor() {
		  return playerOneSponsor;
	  }
	  
	  public BufferedImage getPlayerTwoSponsor() {
		  return playerTwoSponsor;
	  }
	  
	  public BufferedImage getCommentatorOneSponsor() {
		  return commentatorOneSponsor;
	  }
	  
	  public BufferedImage getCommentatorTwoSponsor() {
		  return commentatorTwoSponsor;
	  }
	  
	  public void setPlayerOneCharacterText(String n) {
		  playerOneCharacterText = n;
	  }
	  
	  public void setPlayerTwoCharacterText(String n) {
		  playerTwoCharacterText = n;
	  }
	  
	  public void setPlayerThreeCharacterText(String n) {
		  playerThreeCharacterText = n;
	  }
	  
	  public void setPlayerFourCharacterText(String n) {
		  playerFourCharacterText = n;
	  }
	  
	  public void setTeamCombineCharacter(String n) {
		  this.teamCombineCharacter = n;
	  }
	  
	  public void setPlayerOneCharacter(BufferedImage i) {
		  playerOneCharacter = i;
	  }
	  
	  public void setPlayerTwoCharacter(BufferedImage i) {
		  playerTwoCharacter = i;
	  }
	  
	  public void setPlayerThreeCharacter(BufferedImage i) {
		  playerThreeCharacter = i;
	  }
	  
	  public void setPlayerFourCharacter(BufferedImage i) {
		  playerFourCharacter = i;
	  }
	  
	  public void setPlayerOneSponsor(BufferedImage i) {
		  playerOneSponsor = i;
	  }
	  
	  public void setPlayerTwoSponsor(BufferedImage i) {
		  playerTwoSponsor = i;
	  }
	  
	  public void setCommentatorOneSponsor(BufferedImage i) {
		  commentatorOneSponsor = i;
	  }
	  
	  public void setCommentatorTwoSponsor(BufferedImage i) {
		  commentatorTwoSponsor = i;
	  }
	  
	  public int getPlayerOneScore()
	  {
	    return this.playerOneScore;
	  }
	  
	  public int getPlayerTwoScore()
	  {
	    return this.playerTwoScore;
	  }
	  
	  public String getMainTitle()
	  {
	    return this.mainTitle;
	  }
	  
	  public String getCurrentRound()
	  {
	    return this.currentRound;
	  }
	  
	  public String getTeamOne() {
		  if(playerOne == null || playerOne.equals("")) 
			  return teamOne = playerTwo;
		  if(playerTwo == null || playerTwo.equals("")) {
			  return teamOne = playerOne;
		  }
		  return teamOne = playerOne + " " + teamCombineCharacter + " " + playerTwo;
	  }
	  
	  public String getTeamTwo() {
		  if(playerThree == null || playerThree.equals("")) 
			  return teamTwo = playerFour;
		  if(playerFour == null || playerFour.equals(""))
			  return teamTwo = playerThree;
		  return teamTwo = playerThree + " " + teamCombineCharacter + " " + playerFour;
	  }
	  
	  public String getPlayerOne()
	  {
	    return this.playerOne;
	  }
	  
	  public String getPlayerOneInfo() {
		  return this.playerOneInfo;
	  }
	  
	  public String getPlayerTwoInfo() {
		  return this.playerTwoInfo;
	  }
	  
	  public String getPlayerTwo()
	  {
	    return this.playerTwo;
	  }
	  
	  public String getPlayerThree() {
		  return playerThree;
	  }
	  
	  public String getPlayerThreeInfo() {
		  return playerThreeInfo;
	  }
	  
	  public String getPlayerFour() {
		  return playerFour;
	  }
	  
	  public String getPlayerFourInfo() {
		  return playerFourInfo;
	  }
	  
	  public String[] getCommentatorNames() {
		  return commentators;
	  }
	  
	  public String[] getCommentatorsInfo() {
		  return commentatorsInfo;
	  }
	  
	  public void setPlayerOneScore(int i)
	  {
	    this.playerOneScore = i;
	  }
	  
	  public void setPlayerTwoScore(int i)
	  {
	    this.playerTwoScore = i;
	  }
	  
	  public void setMainTitle(String n)
	  {
	    this.mainTitle = n;
	  }
	  
	  public void setCurrentRound(String n)
	  {
	    this.currentRound = n;
	  }
	  
	  public void setPlayerOne(String n)
	  {
		  //if(!n.equals(""))
			  this.playerOne = n;
	  }
	  
	  public void setPlayerTwo(String n)
	  {
		 // if(!n.equals(""))
			  this.playerTwo = n;
	  }
	  
	  public void setPlayerOneInfo(String n) {
		  this.playerOneInfo = n;
	  }
	  
	  public void setPlayerTwoInfo(String n) {
		  this.playerTwoInfo = n;
	  }
	  
	  public void setPlayerThree(String n) {
		  playerThree = n;
	  }
	  
	  public void setPlayerFour(String n) {
		  playerFour = n;
	  }
	  
	  public void setPlayerThreeInfo(String n) {
		  playerThreeInfo = n;
	  }
	  
	  public void setPlayerFourInfo(String n) {
		  playerFourInfo = n;
	  }
	  
	  public void setCommentators(String com1, String com2) {
		  commentators[0] = com1;
		  commentators[1] = com2;
	  }
	  
	  public void setCommentatorsInfo(String com1, String com2) {
		  commentatorsInfo[0] = com1;
		  commentatorsInfo[1] = com2;
	  }
	  
	  public void setTeamOne(String one, String con, String two) {
		  //if(one.equals("") || one.equals(" ")) one = playerOne;
		  //if(two.equals("") || two.equals(" ")) two = playerTwo;
		  if(one.equals("")) teamOne = two;
		  else
			  if(two.equals("")) teamOne = one;
			  else
				  teamOne = one + " " + con + " " + two;
	  }
	  
	  public void setTeamTwo(String one, String con, String two) {
		  //if(one.equals("") || one.equals(" ")) one = playerOne;
		  //if(two.equals("") || two.equals(" ")) two = playerTwo;
		  if(one.equals("")) teamTwo = two;
		  else
			  if(two.equals("")) teamTwo = one;
			  else
		  		teamTwo = one + " " + con + " " + two;
	  }
	  
	  public void increasePlayerOneScore()
	  {
	    this.playerOneScore += 1;
	  }
	  
	  public void decreasePlayerOneScore()
	  {
	    this.playerOneScore -= 1;
	  }
	  
	  public void increasePlayerTwoScore()
	  {
	    this.playerTwoScore += 1;
	  }
	  
	  public void decreasePlayerTwoScore()
	  {
	    this.playerTwoScore -= 1;
	  }
	  
	  public void readFiles()
	  {
	    try
	    {
	      String line = null;
	      
	      BufferedReader reader = new BufferedReader(new FileReader(getTextDirectory() + this.files[0]));
	      while ((line = reader.readLine()) != null) {
	        this.mainTitle = line;
	      }
	      reader.close();
	      line = null;
	      
	      reader = new BufferedReader(new FileReader(getTextDirectory() + this.files[1]));
	      while ((line = reader.readLine()) != null) {
	        this.currentRound = line;
	      }
	      reader.close();
	      line = null;
	      
	      reader = new BufferedReader(new FileReader(getTextDirectory() + this.files[2]));
	      while ((line = reader.readLine()) != null) {
	        this.playerOne = line;
	        line = reader.readLine();
	        if(line != null) this.playerOneInfo = line;
	      }
	      reader.close();
	      line = null;
	      
	      reader = new BufferedReader(new FileReader(getTextDirectory() + this.files[3]));
	      while ((line = reader.readLine()) != null) {
	        this.playerTwo = line;
	        line = reader.readLine();
	        if(line != null) this.playerTwoInfo = line;
	      }
	      reader.close();
	      line = null;
	      
	      reader = new BufferedReader(new FileReader(getTextDirectory() + this.files[4]));
	      while ((line = reader.readLine()) != null) {
	        this.playerOneScore = Integer.parseInt(line);
	      }
	      reader.close();
	      line = null;
	      
	      reader = new BufferedReader(new FileReader(getTextDirectory() + this.files[5]));
	      while ((line = reader.readLine()) != null) {
	        this.playerTwoScore = Integer.parseInt(line);
	      }
	      reader.close();
	      
	      reader = new BufferedReader(new FileReader(getTextDirectory() + this.files[6]));
	      if((line = reader.readLine()) != null) {
	    	  this.commentators[0] = line;
	    	  if((line = reader.readLine()) != null) commentatorsInfo[0] = line;
	      }
	      reader.close();
	      reader = new BufferedReader(new FileReader(getTextDirectory() + this.files[7]));
	      if((line = reader.readLine()) != null) {
	    	  this.commentators[1] = line;
	    	  if((line = reader.readLine()) != null) commentatorsInfo[1] = line;
	      }
	      reader.close();
	      
	      playerOneCharacter = ImageIO.read(new File(getTextDirectory() + this.files[8]));
	      playerOneSponsor = ImageIO.read(new File(getTextDirectory() + this.files[9]));
	      playerTwoCharacter = ImageIO.read(new File(getTextDirectory() + this.files[10]));
	      playerTwoSponsor = ImageIO.read(new File(getTextDirectory() + this.files[11]));
	      commentatorOneSponsor = ImageIO.read(new File(getTextDirectory() + this.files[12]));
	      commentatorTwoSponsor = ImageIO.read(new File(getTextDirectory() + this.files[13]));
	      
	      /*
	       * Doubles
	       */
	      
	      reader = new BufferedReader(new FileReader(getTextDirectory() + this.files[16]));
	      if((line = reader.readLine()) != null) {
	    	  playerThree = line;
	    	  if(line != null) playerThreeInfo = line;
	      }
	      reader.close();
	      
	      reader = new BufferedReader(new FileReader(getTextDirectory() + this.files[17]));
	      if((line = reader.readLine()) != null) {
	    	  playerFour = line;
	    	  if(line != null) playerFourInfo = line;
	      }
	      reader.close();
	      
	      reader = new BufferedReader(new FileReader(getTextDirectory() + this.files[18]));
	      if((line = reader.readLine()) != null) {
	    	  teamOne = line;
	      }
	      reader.close();
	      
	      reader = new BufferedReader(new FileReader(getTextDirectory() + this.files[19]));
	      if((line = reader.readLine()) != null) {
	    	 teamTwo = line;
	      }
	      reader.close();
	      
	      playerThreeCharacter = ImageIO.read(new File(getTextDirectory() + this.files[20]));
	      playerFourCharacter = ImageIO.read(new File(getTextDirectory() + this.files[21]));
	      
	    }
	    catch (Exception localException) {}
	  }
	  
	  public void writeToScores() {
		  try {
			  
			  PrintWriter writer = null;
			  
			  writer = new PrintWriter(getTextDirectory() + this.files[4]);
		      writer.print(this.playerOneScore);
		      writer.close();
		      
		      writer = new PrintWriter(getTextDirectory() + this.files[5]);
		      writer.print(this.playerTwoScore);
		      writer.close();
		      
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
	  }
	  
	  public void writeToTMG() {
		  try {
			  PrintWriter writer = null;
		      sleeping = true;
		      System.out.println("Enacting Sleep: Image preservation for slower computers");
		      
	      writer = new PrintWriter(getTextDirectory() + this.files[24]);
	      writer.print(schoolOneName);
	      writer.close();
	      
	      writer = new PrintWriter(getTextDirectory() + this.files[25]);
	      writer.print(schoolTwoName);
	      writer.close();
	      
	      writer = new PrintWriter(getTextDirectory() + this.files[26]);
	      writer.print(schoolOneP1);
	      writer.close();
	      
	      writer = new PrintWriter(getTextDirectory() + this.files[27]);
	      writer.print(schoolOneP2);
	      writer.close();
	      
	      writer = new PrintWriter(getTextDirectory() + this.files[28]);
	      writer.print(schoolOneP3);
	      writer.close();
	      
	      writer = new PrintWriter(getTextDirectory() + this.files[29]);
	      writer.print(schoolOneP4);
	      writer.close();
	      
	      writer = new PrintWriter(getTextDirectory() + this.files[30]);
	      writer.print(schoolOneP5);
	      writer.close();
	      
	      writer = new PrintWriter(getTextDirectory() + this.files[31]);
	      writer.print(schoolTwoP1);
	      writer.close();
	      
	      writer = new PrintWriter(getTextDirectory() + this.files[32]);
	      writer.print(schoolTwoP2);
	      writer.close();
	      
	      writer = new PrintWriter(getTextDirectory() + this.files[33]);
	      writer.print(schoolTwoP3);
	      writer.close();
	      
	      writer = new PrintWriter(getTextDirectory() + this.files[34]);
	      writer.print(schoolTwoP4);
	      writer.close();
	      
	      writer = new PrintWriter(getTextDirectory() + this.files[35]);
	      writer.print(schoolTwoP5);
	      writer.close();
	      
	  	} catch (Exception e) {
		  e.printStackTrace();
	  	}
	  }
	  
	  public void writeToText() {
		  try {
		      PrintWriter writer = null;
		      sleeping = true;
		      System.out.println("Enacting Sleep: Image preservation for slower computers");
		      
		      writer = new PrintWriter(getTextDirectory() + this.files[0]);
		      writer.print(this.mainTitle);
		      writer.close();
		      
		      writer = new PrintWriter(getTextDirectory() + this.files[1]);
		      writer.print(this.currentRound);
		      writer.close();
		      
		      writer = new PrintWriter(getTextDirectory() + this.files[2]);
		      if(!StreamUpdater.getRestriction()) {
		    	  writer.println(this.playerOne);
		    	  writer.print(this.playerOneInfo);
		      } else {
		    	  writer.print(this.playerOne);
		      }
		      writer.close();
		      
		      writer = new PrintWriter(getTextDirectory() + this.files[3]);
		      if(!StreamUpdater.getRestriction()) {
		    	  writer.println(this.playerTwo);
		    	  writer.print(this.playerTwoInfo);
		      } else {
		    	  writer.print(this.playerTwo);
		      }
		      writer.close();
		      
		      writer = new PrintWriter(getTextDirectory() + this.files[4]);
		      writer.print(this.playerOneScore);
		      writer.close();
		      
		      writer = new PrintWriter(getTextDirectory() + this.files[5]);
		      writer.print(this.playerTwoScore);
		      writer.close();
		      
		      writer = new PrintWriter(getTextDirectory() + this.files[6]);
		      if(!StreamUpdater.getRestriction()) {
		    	  writer.println(commentators[0]);
			      writer.print(commentatorsInfo[0]);
		      } else {
		    	  writer.print(commentators[0]);
		      }
		      writer.close();
		      
		      writer = new PrintWriter(getTextDirectory() + this.files[7]);
		      if(!StreamUpdater.getRestriction()) {
		    	  writer.println(commentators[1]);
			      writer.print(commentatorsInfo[1]);
		      } else {
		    	  writer.print(commentators[1]);
		      }
		      writer.close();
		      
		      writer = new PrintWriter(getTextDirectory() + this.files[14]);
		      writer.println(playerOneCharacterText);
		      writer.close();
		      
		      writer = new PrintWriter(getTextDirectory() + this.files[15]);
		      writer.println(playerTwoCharacterText);
		      writer.close();
		      
		      writer = new PrintWriter(getTextDirectory() + this.files[16]);
		      if(!StreamUpdater.getRestriction()) {
			      writer.println(playerThree);
			      writer.print(playerThreeInfo);
		      } else 
		    	  writer.print(playerThree);
		      writer.close();
		      
		      writer = new PrintWriter(getTextDirectory() + this.files[17]);
		      if(!StreamUpdater.getRestriction()) {
		    	  writer.println(playerFour);
		    	  writer.print(playerFourInfo);
		      } else
		    	  writer.print(playerFour);
		      writer.close();
		      
		      writer = new PrintWriter(getTextDirectory() + this.files[18]);
		      teamOne = this.getTeamOne();
		      writer.print(teamOne);
		      writer.close();
		      
		      writer = new PrintWriter(getTextDirectory() + this.files[19]);
		      teamTwo = this.getTeamTwo();
		      writer.print(teamTwo);
		      writer.close();
		      
		      writer = new PrintWriter(getTextDirectory() + this.files[20]);
		      writer.print(playerThreeCharacter);
		      writer.close();
		      
		      writer = new PrintWriter(getTextDirectory() + this.files[21]);
		      writer.print(playerFourCharacter);
		      writer.close();
		      
		  } catch (Exception e) {}
	  }
	  
	  public void writeToFiles()
	  {
	    try
	    {
	      PrintWriter writer = null;
	      sleeping = true;
	      System.out.println("Enacting Sleep: Image preservation for slower computers");
	      
	      writer = new PrintWriter(getTextDirectory() + this.files[0]);
	      writer.print(this.mainTitle);
	      writer.close();
	      
	      writer = new PrintWriter(getTextDirectory() + this.files[1]);
	      writer.print(this.currentRound);
	      writer.close();
	      
	      writer = new PrintWriter(getTextDirectory() + this.files[2]);
	      if(!StreamUpdater.getRestriction()) {
	    	  writer.println(this.playerOne);
	    	  writer.print(this.playerOneInfo);
	      } else {
	    	  writer.print(this.playerOne);
	      }
	      writer.close();
	      
	      writer = new PrintWriter(getTextDirectory() + this.files[3]);
	      if(!StreamUpdater.getRestriction()) {
	    	  writer.println(this.playerTwo);
	    	  writer.print(this.playerTwoInfo);
	      } else {
	    	  writer.print(this.playerTwo);
	      }
	      writer.close();
	      
	      writer = new PrintWriter(getTextDirectory() + this.files[4]);
	      writer.print(this.playerOneScore);
	      writer.close();
	      
	      writer = new PrintWriter(getTextDirectory() + this.files[5]);
	      writer.print(this.playerTwoScore);
	      writer.close();
	      
	      writer = new PrintWriter(getTextDirectory() + this.files[6]);
	      if(!StreamUpdater.getRestriction()) {
	    	  writer.println(commentators[0]);
		      writer.print(commentatorsInfo[0]);
	      } else {
	    	  writer.print(commentators[0]);
	      }
	      writer.close();
	      
	      writer = new PrintWriter(getTextDirectory() + this.files[7]);
	      if(!StreamUpdater.getRestriction()) {
	    	  writer.println(commentators[1]);
		      writer.print(commentatorsInfo[1]);
	      } else {
	    	  writer.print(commentators[1]);
	      }
	      writer.close();
	      
	      writer = new PrintWriter(getTextDirectory() + this.files[14]);
	      writer.println(playerOneCharacterText);
	      writer.close();
	      
	      writer = new PrintWriter(getTextDirectory() + this.files[15]);
	      writer.println(playerTwoCharacterText);
	      writer.close();
	      
	      writer = new PrintWriter(getTextDirectory() + this.files[16]);
	      if(!StreamUpdater.getRestriction()) {
		      writer.println(playerThree);
		      writer.print(playerThreeInfo);
	      } else 
	    	  writer.print(playerThree);
	      writer.close();
	      
	      writer = new PrintWriter(getTextDirectory() + this.files[17]);
	      if(!StreamUpdater.getRestriction()) {
	    	  writer.println(playerFour);
	    	  writer.print(playerFourInfo);
	      } else
	    	  writer.print(playerFour);
	      writer.close();
	      
	      writer = new PrintWriter(getTextDirectory() + this.files[18]);
	      teamOne = this.getTeamOne();
	      writer.print(teamOne);
	      writer.close();
	      
	      writer = new PrintWriter(getTextDirectory() + this.files[19]);
	      teamTwo = this.getTeamTwo();
	      writer.print(teamTwo);
	      writer.close();
	      
	      writer = new PrintWriter(getTextDirectory() + this.files[20]);
	      writer.print(playerThreeCharacter);
	      writer.close();
	      
	      writer = new PrintWriter(getTextDirectory() + this.files[21]);
	      writer.print(playerFourCharacter);
	      writer.close();
	      
	      Thread tmp = new Thread(new Runnable() {
	
			@Override
			public void run() {
				
				try {
					// first declare a file, this is to our output
				      File f;
				      
				      f = new File(getTextDirectory() + files[8]);
				      if(f.exists() && playerOneCharacter != null)
				    	  ImageIO.write(playerOneCharacter, "png", f);
				      //Thread.sleep(250);
				      
				      f = new File(getTextDirectory() + files[10]);
				      if(f.exists() && playerTwoCharacter != null)
				    	  ImageIO.write(playerTwoCharacter, "png", f);
				      //Thread.sleep(250);
				      
				      f = new File(getTextDirectory() + files[9]);
				      if(f.exists() && playerOneSponsor != null)
				    	  ImageIO.write(playerOneSponsor, "png", f);
				      //Thread.sleep(250);
				      
				      f = new File(getTextDirectory() + files[11]);
				      if(f.exists() && playerTwoSponsor != null)
				    	  ImageIO.write(playerTwoSponsor, "png", f);
				      //Thread.sleep(250);
				      
				      f = new File(getTextDirectory() + files[12]);
				      if(f.exists() && commentatorOneSponsor != null)
				    	  ImageIO.write(commentatorOneSponsor, "png", f);
				      //Thread.sleep(250);
				      
				      f = new File(getTextDirectory() + files[13]);
				      if(f.exists() && commentatorTwoSponsor != null)
				    	  ImageIO.write(commentatorTwoSponsor, "png", f);
				      //Thread.sleep(250);
				      
				      f = new File(getTextDirectory() + files[20]);
				      if(f.exists() && playerThreeCharacter != null)
				    	  ImageIO.write(playerThreeCharacter, "png", f);
				      //Thread.sleep(250);
				      
				      f = new File(getTextDirectory() + files[21]);
				      if(f.exists() && playerFourCharacter != null)
				    	  ImageIO.write(playerFourCharacter, "png", f);
				      //Thread.sleep(250);
				      Thread.sleep(1500);
				      sleeping = false;
				      
				} catch (Exception e) {
					
				}
			}
	    	  
	      });
	      
	      tmp.start();
	      
	    }
	    catch (Exception localException) {}
	  }
	  
	  public static boolean isSleeping() {
		  return sleeping;
	  }
	  
}
