package streamupdater.files;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import streamupdater.gui.components.StreamUpdater;
import streamupdater.utils.CommentatorSuggestionField;
import streamupdater.utils.PlayerSuggestionField;

public class EntrantDatabase {
	
	public void enlistPlayer(String name, String info, String sponsor) {
		try {
			if(!name.equals("")) {
				boolean write = true;
				for(int i = 0; i < FileManager.getPlayers().size(); i++) {
					if(name.equals(FileManager.getPlayers().get(i))) {
						JOptionPane.showMessageDialog(null, "Error! User Already Exists!");
						write = false;
						break;
					}
				}
				if(write) {
					PrintWriter writer = new PrintWriter(new File(FileManager.getPlayersDirectory() + "/" + removeIllegal(name) + ".txt"));
					writer.println(name);
					writer.println(info);
					writer.close();
				}
			}
			if(!sponsor.equals("")) {
				File tmp = new File(sponsor);
				File sponsorName = new File(FileManager.getSponsorsDirectory() + "/" + removeIllegal(name) +".png");
				BufferedImage image = ImageIO.read(tmp);
				ImageIO.write(image, "png", sponsorName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		reload();
	}
	
	public void enlistCommentator(String name, String info, String sponsor) {
		try {
			if(!name.equals("")) {
				boolean write = true;
				for(int i = 0; i < FileManager.getCommentators().size(); i++) {
					if(name.equals(FileManager.getCommentators().get(i))) {
						JOptionPane.showMessageDialog(null, "Error! User Already Exists!");
						write = false;
						break;
					}
				}
				if(write) {
					PrintWriter writer = new PrintWriter(new File(FileManager.getCommentatorsDirectory() + "/" + removeIllegal(name) + ".txt"));
					writer.println(name);
					writer.println(info);
					writer.close();
				}
			}
			if(!sponsor.equals("")) {
				File tmp = new File(sponsor);
				File sponsorName = new File(FileManager.getSponsorsDirectory() + "/" + removeIllegal(name) +".png");
				BufferedImage image = ImageIO.read(tmp);
				ImageIO.write(image, "png", sponsorName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		reload();
	}
	
	public void enlistSponsor(String sponsor) {
		try {
			if(!sponsor.equals("")) {
				File tmp = new File(sponsor);
				File sponsorName = new File(FileManager.getSponsorsDirectory() + "/" + removeIllegal(tmp.getName()));
				BufferedImage image = ImageIO.read(tmp);
				ImageIO.write(image, "png", sponsorName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		reload();
	}
	
	private void reload() {
		PlayerSuggestionField.setPlayers(FileManager.getPlayers());
		CommentatorSuggestionField.setCommentators(FileManager.getCommentators());
		StreamUpdater.loadSponsors();
	}
	
	private String removeIllegal(String n) {
		char[] illegal = {
				'>', '<', ':', '\"', '/', '\\', '|', '?', '*'
		};
		String build = "";
		boolean start = false;
		for(int i = 0; i < n.length(); i++) {
			if(n.charAt(i) != ' ' && start) {
				build = build + n.charAt(i);
			}
			for(int z = 0; z < illegal.length; z++) {
				if(n.charAt(i) == illegal[z]) {
					start = true;
				}
			}
		}
		if(!start) return n;
		return build;
	}

}
