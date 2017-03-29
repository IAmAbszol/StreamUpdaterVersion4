package streamupdater.gui.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import streamupdater.files.EntrantDatabase;

@SuppressWarnings("serial")
public class TournamentEnlisting extends JPanel {

	private EntrantDatabase eb;
	
	private JFileChooser jfc;
	private JTextField playerTagField;
	private JTextField playerInformationField;
	private JTextField commentatorTagField;
	private JTextField commentatorInformationField;
	
	private String playerSponsorText = "";
	private String commentatorSponsorText = "";
	private String sponsorText = "";
	
	public TournamentEnlisting() {
		
		eb = new EntrantDatabase();
		
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		JTabbedPane enlistingPane = new JTabbedPane(JTabbedPane.TOP);
		enlistingPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		enlistingPane.setBackground(Color.DARK_GRAY);
		enlistingPane.setForeground(Color.WHITE);
		enlistingPane.setBounds(10, 11, 1605, 997);
		add(enlistingPane);
		
		JPanel playerEnlisting = new JPanel();
		playerEnlisting.setForeground(Color.WHITE);
		playerEnlisting.setBackground(Color.DARK_GRAY);
		enlistingPane.addTab("<html><center><h2 style=''>Player Enlisting</h2></center></html>", null, playerEnlisting, null);
		playerEnlisting.setLayout(null);
		
		JLabel playerEntrantLabel = new JLabel("Player Tag");
		playerEntrantLabel.setHorizontalAlignment(SwingConstants.LEFT);
		playerEntrantLabel.setFont(new Font("Arial Black", Font.BOLD, 18));
		playerEntrantLabel.setForeground(Color.WHITE);
		playerEntrantLabel.setBounds(10, 11, 218, 40);
		playerEnlisting.add(playerEntrantLabel);
		
		playerTagField = new JTextField();
		playerTagField.setFont(new Font("Arial Black", Font.BOLD, 12));
		playerTagField.setBounds(10, 62, 218, 40);
		playerEnlisting.add(playerTagField);
		playerTagField.setColumns(10);
		
		JLabel playerInformation = new JLabel("Player Information");
		playerInformation.setForeground(Color.WHITE);
		playerInformation.setFont(new Font("Arial Black", Font.BOLD, 18));
		playerInformation.setBounds(10, 113, 218, 40);
		playerEnlisting.add(playerInformation);
		
		playerInformationField = new JTextField();
		playerInformationField.setFont(new Font("Arial Black", Font.BOLD, 12));
		playerInformationField.setBounds(10, 164, 218, 40);
		playerEnlisting.add(playerInformationField);
		playerInformationField.setColumns(10);
		
		JButton updatePlayerButton = new JButton("Update");
		updatePlayerButton.setFont(new Font("Arial Black", Font.BOLD, 24));
		updatePlayerButton.setBackground(Color.DARK_GRAY);
		updatePlayerButton.setBounds(1212, 842, 378, 88);
		playerEnlisting.add(updatePlayerButton);
		
		JLabel playerSponsor = new JLabel("Player Sponsor");
		playerSponsor.setForeground(Color.WHITE);
		playerSponsor.setFont(new Font("Arial Black", Font.BOLD, 18));
		playerSponsor.setBounds(10, 215, 218, 40);
		playerEnlisting.add(playerSponsor);
		
		JButton playerSponsorButton = new JButton("Connect Sponsor");
		playerSponsorButton.setBackground(Color.DARK_GRAY);
		playerSponsorButton.setFont(new Font("Arial Black", Font.BOLD, 12));
		playerSponsorButton.setBounds(10, 266, 179, 40);
		playerEnlisting.add(playerSponsorButton);
		
		JPanel commentatorEnlisting = new JPanel();
		commentatorEnlisting.setForeground(Color.WHITE);
		commentatorEnlisting.setBackground(Color.DARK_GRAY);
		enlistingPane.addTab("<html><center><h2 style=''>Commentator Enlisting</h2></center></html>", null, commentatorEnlisting, null);
		commentatorEnlisting.setLayout(null);
		
		JLabel commentatorTag = new JLabel("Commentator Tag");
		commentatorTag.setHorizontalAlignment(SwingConstants.LEFT);
		commentatorTag.setForeground(Color.WHITE);
		commentatorTag.setFont(new Font("Arial Black", Font.BOLD, 18));
		commentatorTag.setBounds(10, 11, 218, 40);
		commentatorEnlisting.add(commentatorTag);
		
		commentatorTagField = new JTextField();
		commentatorTagField.setFont(new Font("Arial Black", Font.BOLD, 12));
		commentatorTagField.setBounds(10, 62, 218, 40);
		commentatorEnlisting.add(commentatorTagField);
		commentatorTagField.setColumns(10);
		
		JLabel commentatorInformation = new JLabel("Commentator Information");
		commentatorInformation.setForeground(Color.WHITE);
		commentatorInformation.setFont(new Font("Arial Black", Font.BOLD, 18));
		commentatorInformation.setBounds(10, 113, 330, 40);
		commentatorEnlisting.add(commentatorInformation);
		
		commentatorInformationField = new JTextField();
		commentatorInformationField.setFont(new Font("Arial Black", Font.BOLD, 12));
		commentatorInformationField.setBounds(10, 164, 218, 40);
		commentatorEnlisting.add(commentatorInformationField);
		commentatorInformationField.setColumns(10);
		
		JLabel commentatorSponsor = new JLabel("Commentator Sponsor");
		commentatorSponsor.setForeground(Color.WHITE);
		commentatorSponsor.setFont(new Font("Arial Black", Font.BOLD, 18));
		commentatorSponsor.setBounds(10, 215, 330, 40);
		commentatorEnlisting.add(commentatorSponsor);
		
		JButton commentatorSponsorButton = new JButton("Connect Sponsor");
		commentatorSponsorButton.setBackground(Color.DARK_GRAY);
		commentatorSponsorButton.setFont(new Font("Arial Black", Font.BOLD, 12));
		commentatorSponsorButton.setBounds(10, 266, 179, 40);
		commentatorEnlisting.add(commentatorSponsorButton);
		
		JButton updateCommentatorButton = new JButton("Update");
		updateCommentatorButton.setFont(new Font("Arial Black", Font.BOLD, 24));
		updateCommentatorButton.setBackground(Color.DARK_GRAY);
		updateCommentatorButton.setBounds(1212, 842, 378, 88);
		commentatorEnlisting.add(updateCommentatorButton);
		
		JPanel sponsorEnlisting = new JPanel();
		sponsorEnlisting.setForeground(Color.WHITE);
		sponsorEnlisting.setBackground(Color.DARK_GRAY);
		enlistingPane.addTab("<html><center><h2 style=''>Sponsor Enlisting</h2></center></html>", null, sponsorEnlisting, null);
		sponsorEnlisting.setLayout(null);
		
		JLabel sponsor = new JLabel("Sponsor");
		sponsor.setHorizontalAlignment(SwingConstants.LEFT);
		sponsor.setForeground(Color.WHITE);
		sponsor.setFont(new Font("Arial Black", Font.BOLD, 18));
		sponsor.setBounds(10, 11, 218, 40);
		sponsorEnlisting.add(sponsor);
		
		JButton sponsorButton = new JButton("Connect Sponsor");
		sponsorButton.setFont(new Font("Arial Black", Font.BOLD, 12));
		sponsorButton.setBackground(Color.DARK_GRAY);
		sponsorButton.setBounds(10, 62, 179, 40);
		sponsorEnlisting.add(sponsorButton);
		
		JButton updateSponsorButton = new JButton("Update");
		updateSponsorButton.setFont(new Font("Arial Black", Font.BOLD, 24));
		updateSponsorButton.setBackground(Color.DARK_GRAY);
		updateSponsorButton.setBounds(1212, 842, 378, 88);
		sponsorEnlisting.add(updateSponsorButton);
		/*
		enlistingPane.setUI(new BasicTabbedPaneUI() {
			   @Override
			   protected void installDefaults() {
			       super.installDefaults();
			       lightHighlight = Color.LIGHT_GRAY;
			       shadow = Color.DARK_GRAY;
			       darkShadow = Color.BLACK;
			   }
		});
		*/
		playerSponsorButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jfc = new JFileChooser();
				jfc.setCurrentDirectory(new java.io.File("user.home"));
				jfc.setDialogTitle("Please Select Your Image");
				jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				if (jfc.showOpenDialog(playerSponsorButton) == JFileChooser.APPROVE_OPTION) {
					playerSponsorText = jfc.getSelectedFile().getAbsolutePath().replace("\\", "/");
				}
				playerSponsorButton.setToolTipText(playerSponsorText);
			}
			
		});

		commentatorSponsorButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jfc = new JFileChooser();
				jfc.setCurrentDirectory(new java.io.File("user.home"));
				jfc.setDialogTitle("Please Select Your Image");
				jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				if (jfc.showOpenDialog(playerSponsorButton) == JFileChooser.APPROVE_OPTION) {
					commentatorSponsorText = jfc.getSelectedFile().getAbsolutePath().replace("\\", "/");
				}
				commentatorSponsorButton.setToolTipText(commentatorSponsorText);
			}
			
		});


		sponsorButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jfc = new JFileChooser();
				jfc.setCurrentDirectory(new java.io.File("user.home"));
				jfc.setDialogTitle("Please Select Your Image");
				jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				if (jfc.showOpenDialog(playerSponsorButton) == JFileChooser.APPROVE_OPTION) {
					sponsorText = jfc.getSelectedFile().getAbsolutePath().replace("\\", "/");
				}
				sponsorButton.setToolTipText(sponsorText);
			}
			
		});

		
		updatePlayerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				eb.enlistPlayer(playerTagField.getText(),
								playerInformationField.getText(),
								playerSponsorText);
				playerTagField.setText("");
				playerInformationField.setText("");
				playerSponsorText = "";
				playerSponsorButton.setToolTipText("");
			}
			
		});
		
		updateCommentatorButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				eb.enlistCommentator(commentatorTagField.getText(),
									 commentatorInformationField.getText(),
									 commentatorSponsorText);
				commentatorTagField.setText("");
				commentatorInformationField.setText("");
				commentatorSponsorText = "";
				commentatorSponsorButton.setToolTipText("");
			}
		});
		
		updateSponsorButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				eb.enlistSponsor(sponsorText);
				sponsorText = "";
				sponsorButton.setToolTipText("");
			}
		});
		
	}

}
