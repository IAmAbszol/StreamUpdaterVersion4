package streamupdater.utils;

import streamupdater.files.FileManager;
import streamupdater.gui.components.StreamUpdater;

public class Commands {
	
	private FileManager fm;
	
	public Commands() {
		fm = StreamUpdater.getFileManager();
	}
	
	public String interpretString(String n) {
		
		if(n.contains("PLAYERONENAME")) {
			n = n.replace("PLAYERONENAME", getPlayerName(1));
		}
		
		if(n.contains("PLAYERTWONAME")) {
			n = n.replace("PLAYERTWONAME", getPlayerName(2));
		}
		
		if(n.contains("PLAYERTHREENAME")) {
			n = n.replace("PLAYERTHREENAME", getPlayerName(3));
		}
		
		if(n.contains("PLAYERFOURNAME")) {
			n = n.replace("PLAYERFOURNAME", getPlayerName(4));
		}
		
		if(n.contains("TEAM1")) {
			n = n.replace("TEAM1", getTeamName(1));
		}
		
		if(n.contains("TEAM2")) {
			n = n.replace("TEAM2", getTeamName(2));
		}
		/*
		if(n.contains("PLAYERONECHAR")) {
			n = n.replace("PLAYERONECHAR", getCharacters(true));
		}
		
		if(n.contains("PLAYERTWOCHAR")) {
			n = n.replace("PLAYERTWOCHAR", getCharacters(false));
		}
		*/
		if(n.contains("CURRENTROUND")) {
			n = n.replace("CURRENTROUND", getRound());
		}
		
		if(n.contains("MAINTITLE")) {
			n = n.replace("MAINTITLE", getTitle());
		}
		
		return removeIllegal(n);
			
	}
	
	private String removeIllegal(String n) {
		boolean containsIllegal = true;
		while(containsIllegal) {
			char[] illegal = {
					':', '\"', '/', '\\', '|', '?'
			};
			for(int i = 0; i < illegal.length; i++) {
				n = n.replace("" + illegal[i] + "", "");
			}
			containsIllegal = false;
			for(int i = 0; i < illegal.length; i++) {
				if(n.contains(""+illegal[i])) {
					containsIllegal = true;
				}
			}
		}
		return n;
	}
	
	/*
	private String getCharacters(boolean playerOne) {

		LinkedHashSet<String> set1 = new LinkedHashSet<String>(RenderTab.getPlayerOneCharacters());
        ArrayList<String> tmp1  = new ArrayList<String>(set1);
        LinkedHashSet<String> set2 = new LinkedHashSet<String>(RenderTab.getPlayerTwoCharacters());
        ArrayList<String> tmp2  = new ArrayList<String>(set2);
        
        RenderTab.setPlayerOneChars(tmp1);
        RenderTab.setPlayerTwoChars(tmp2);
 
		
		if(playerOne) {
			
			String tmp = "BLANK";
			if(RenderTab.getPlayerOneCharacters().size() > 0) tmp = "";
			
			for(int i = 0; i < RenderTab.getPlayerOneCharacters().size(); i++) {
				
				if(i == 0) {
					tmp = tmp + RenderTab.getPlayerOneCharacters().get(i);
				} else
					tmp = tmp + "," + RenderTab.getPlayerOneCharacters().get(i);

			}
			
			return tmp;
		
		} else {
			
			String tmp = "BLANK";
			
			if(RenderTab.getPlayerTwoCharacters().size() > 0) tmp = "";
			
			for(int i = 0; i < RenderTab.getPlayerTwoCharacters().size(); i++) {
				
				if(i == 0) {
					tmp = tmp + RenderTab.getPlayerTwoCharacters().get(i);
				} else
					tmp = tmp + "," + RenderTab.getPlayerTwoCharacters().get(i);
				
			}
			
			return tmp;
			
		}
		
	}
	*/
	
	private String getTitle() {
		return fm.getMainTitle();
	}
	
	private String getRound() {
		return fm.getCurrentRound();
	}
	
	private String getTeamName(int i) {
		
		if(i == 1) return fm.getTeamOne();
		else
			if(i == 2) return fm.getTeamTwo();
		return "";
		
	}
	
	private String getPlayerName(int i) {
		if(i == 1) 
			return fm.getPlayerOne();
		else
		if(i == 2)
			return fm.getPlayerTwo();
		else
		if(i == 3) 
			return fm.getPlayerThree();
		else
		if(i == 4)
			return fm.getPlayerFour();
		return "";
	}
	
	private String getScore(boolean playerOne) {
		if(playerOne) {
			return "" + fm.getPlayerOneScore();
		} else
			return "" + fm.getPlayerTwoScore();
		
	}
	
}
