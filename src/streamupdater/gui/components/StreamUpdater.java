package streamupdater.gui.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EtchedBorder;

import streamupdater.files.FileManager;
import streamupdater.utils.CommentatorSuggestionField;
import streamupdater.utils.PlayerSuggestionField;

@SuppressWarnings("serial")
public class StreamUpdater extends JPanel {
	
	private boolean singles = true;
	private static boolean restricted = true;
	
	private static FileManager fm;
	
	private JComboBox playerOneCharacterBox;
	private JComboBox playerTwoCharacterBox;
	private JComboBox playerThreeCharacterBox;
	private JComboBox playerFourCharacterBox;
	private static JComboBox playerOneSponsorBox;
	private static JComboBox playerTwoSponsorBox;
	private static JComboBox playerThreeSponsorBox;
	private static JComboBox playerFourSponsorBox;
	private static JComboBox commentatorOneSponsorBox;
	private static JComboBox commentatorTwoSponsorBox;
	
	private JTextField mainTitle;
	private PlayerSuggestionField playerOneTextField;
	private PlayerSuggestionField playerTwoTextField;
	private JTextField playerOneScore;
	private JTextField playerTwoScore;
	private JTextField round;
	private PlayerSuggestionField playerThreeTextField;
	private PlayerSuggestionField playerFourTextField;
	private CommentatorSuggestionField commentatorOneTextField;
	private CommentatorSuggestionField commentatorTwoTextField;
	
	private ArrayList<String> characters;
	
	public StreamUpdater() {

		fm = new FileManager();
		fm.init();
		
		setForeground(Color.WHITE);
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		JLabel singlesOrDoubles = new JLabel("Singles");
		singlesOrDoubles.setToolTipText("Click to swap for doubles");
		singlesOrDoubles.setHorizontalAlignment(SwingConstants.CENTER);
		singlesOrDoubles.setFont(new Font("Arial Black", Font.BOLD, 32));
		singlesOrDoubles.setBackground(Color.DARK_GRAY);
		singlesOrDoubles.setBounds(465, 11, 695, 65);
		singlesOrDoubles.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		add(singlesOrDoubles);
		
		mainTitle = new JTextField("");
		mainTitle.setHorizontalAlignment(SwingConstants.CENTER);
		mainTitle.setFont(new Font("Arial Black", Font.BOLD, 18));
		mainTitle.setBounds(465, 87, 695, 40);
		add(mainTitle);
		mainTitle.setColumns(10);
		
		playerOneTextField = new PlayerSuggestionField("");
		playerOneTextField.setBackground(Color.WHITE);
		playerOneTextField.setHorizontalAlignment(SwingConstants.CENTER);
		playerOneTextField.setFont(new Font("Arial Black", Font.BOLD, 14));
		playerOneTextField.setBounds(315, 269, 300, 40);
		add(playerOneTextField);
		playerOneTextField.setColumns(10);
		
		playerTwoTextField = new PlayerSuggestionField("");
		playerTwoTextField.setHorizontalAlignment(SwingConstants.CENTER);
		playerTwoTextField.setFont(new Font("Arial Black", Font.BOLD, 14));
		playerTwoTextField.setColumns(10);
		playerTwoTextField.setBounds(1005, 269, 300, 40);
		add(playerTwoTextField);
		
		playerOneCharacterBox = new JComboBox();
		playerOneCharacterBox.setBackground(Color.DARK_GRAY);
		playerOneCharacterBox.setFont(new Font("Arial Black", Font.BOLD, 14));
		playerOneCharacterBox.setBounds(315, 320, 300, 40);
		add(playerOneCharacterBox);
		
		playerTwoCharacterBox = new JComboBox();
		playerTwoCharacterBox.setFont(new Font("Arial Black", Font.BOLD, 14));
		playerTwoCharacterBox.setBackground(Color.DARK_GRAY);
		playerTwoCharacterBox.setBounds(1005, 320, 300, 40);
		add(playerTwoCharacterBox);
		
		playerOneSponsorBox = new JComboBox();
		playerOneSponsorBox.setFont(new Font("Arial Black", Font.BOLD, 14));
		playerOneSponsorBox.setBackground(Color.DARK_GRAY);
		playerOneSponsorBox.setBounds(10, 296, 300, 40);
		add(playerOneSponsorBox);
		
		playerTwoSponsorBox = new JComboBox();
		playerTwoSponsorBox.setFont(new Font("Arial Black", Font.BOLD, 14));
		playerTwoSponsorBox.setBackground(Color.DARK_GRAY);
		playerTwoSponsorBox.setBounds(1315, 296, 300, 40);
		add(playerTwoSponsorBox);
		
		playerOneScore = new JTextField("0");
		playerOneScore.setHorizontalAlignment(SwingConstants.CENTER);
		playerOneScore.setFont(new Font("Arial Black", Font.BOLD, 18));
		playerOneScore.setBounds(625, 390, 60, 60);
		add(playerOneScore);
		playerOneScore.setColumns(10);
		
		playerTwoScore = new JTextField("0");
		playerTwoScore.setHorizontalAlignment(SwingConstants.CENTER);
		playerTwoScore.setFont(new Font("Arial Black", Font.BOLD, 18));
		playerTwoScore.setColumns(10);
		playerTwoScore.setBounds(935, 390, 60, 60);
		add(playerTwoScore);
		
		round = new JTextField("");
		round.setHorizontalAlignment(SwingConstants.CENTER);
		round.setFont(new Font("Arial Black", Font.BOLD, 14));
		round.setBounds(625, 144, 370, 40);
		add(round);
		round.setColumns(10);
		
		JLabel playerOnePlus = new JLabel("");
		try {
			playerOnePlus.setIcon(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("/images/plus.png"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		playerOnePlus.setBounds(625, 314, 60, 65);
		add(playerOnePlus);
		
		JLabel playerOneMinus = new JLabel("");
		try {
			playerOneMinus.setIcon(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("/images/minus.png"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		playerOneMinus.setBounds(630, 442, 60, 65);
		add(playerOneMinus);
		
		JLabel playerTwoPlus = new JLabel("");
		try {
			playerTwoPlus.setIcon(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("/images/plus.png"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		playerTwoPlus.setBounds(935, 314, 60, 65);
		add(playerTwoPlus);
		
		JLabel playerTwoMinus = new JLabel("");
		try {
			playerTwoMinus.setIcon(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("/images/minus.png"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		playerTwoMinus.setBounds(940, 442, 60, 65);
		add(playerTwoMinus);
		
		playerThreeTextField = new PlayerSuggestionField("");
		playerThreeTextField.setHorizontalAlignment(SwingConstants.CENTER);
		playerThreeTextField.setFont(new Font("Arial Black", Font.BOLD, 14));
		playerThreeTextField.setColumns(10);
		playerThreeTextField.setBackground(Color.WHITE);
		playerThreeTextField.setBounds(315, 493, 300, 40);
		add(playerThreeTextField);
		
		playerThreeCharacterBox = new JComboBox();
		playerThreeCharacterBox.setFont(new Font("Arial Black", Font.BOLD, 14));
		playerThreeCharacterBox.setBackground(Color.DARK_GRAY);
		playerThreeCharacterBox.setBounds(315, 544, 300, 40);
		add(playerThreeCharacterBox);
		
		playerThreeSponsorBox = new JComboBox();
		playerThreeSponsorBox.setFont(new Font("Arial Black", Font.BOLD, 14));
		playerThreeSponsorBox.setBackground(Color.DARK_GRAY);
		playerThreeSponsorBox.setBounds(10, 519, 300, 40);
		add(playerThreeSponsorBox);
		
		playerFourTextField = new PlayerSuggestionField("");
		playerFourTextField.setHorizontalAlignment(SwingConstants.CENTER);
		playerFourTextField.setFont(new Font("Arial Black", Font.BOLD, 14));
		playerFourTextField.setColumns(10);
		playerFourTextField.setBackground(Color.WHITE);
		playerFourTextField.setBounds(1005, 493, 300, 40);
		add(playerFourTextField);
		
		playerFourCharacterBox = new JComboBox();
		playerFourCharacterBox.setFont(new Font("Arial Black", Font.BOLD, 14));
		playerFourCharacterBox.setBackground(Color.DARK_GRAY);
		playerFourCharacterBox.setBounds(1005, 544, 300, 40);
		add(playerFourCharacterBox);
		
		playerFourSponsorBox = new JComboBox();
		playerFourSponsorBox.setFont(new Font("Arial Black", Font.BOLD, 14));
		playerFourSponsorBox.setBackground(Color.DARK_GRAY);
		playerFourSponsorBox.setBounds(1315, 519, 300, 40);
		add(playerFourSponsorBox);
		
		JLabel swapLabel = new JLabel("");
		try {
			swapLabel.setIcon(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("/images/swap.png"))));
		} catch (IOException e) {
			e.printStackTrace();
		}
		swapLabel.setBounds(695, 283, 230, 285);
		add(swapLabel);
		swapLabel.setToolTipText("Swap P1 & P2 for Singles or P1 & P2 with P3 & P4 for Doubles");
		
		commentatorOneTextField = new CommentatorSuggestionField("");
		commentatorOneTextField.setHorizontalAlignment(SwingConstants.CENTER);
		commentatorOneTextField.setFont(new Font("Arial Black", Font.BOLD, 14));
		commentatorOneTextField.setColumns(10);
		commentatorOneTextField.setBackground(Color.WHITE);
		commentatorOneTextField.setBounds(315, 679, 300, 40);
		add(commentatorOneTextField);
		
		commentatorTwoTextField = new CommentatorSuggestionField("");
		commentatorTwoTextField.setHorizontalAlignment(SwingConstants.CENTER);
		commentatorTwoTextField.setFont(new Font("Arial Black", Font.BOLD, 14));
		commentatorTwoTextField.setColumns(10);
		commentatorTwoTextField.setBackground(Color.WHITE);
		commentatorTwoTextField.setBounds(1005, 679, 300, 40);
		add(commentatorTwoTextField);
		
		commentatorOneSponsorBox = new JComboBox();
		commentatorOneSponsorBox.setFont(new Font("Arial Black", Font.BOLD, 14));
		commentatorOneSponsorBox.setBackground(Color.DARK_GRAY);
		commentatorOneSponsorBox.setBounds(10, 679, 300, 40);
		add(commentatorOneSponsorBox);
		
		commentatorTwoSponsorBox = new JComboBox();
		commentatorTwoSponsorBox.setFont(new Font("Arial Black", Font.BOLD, 14));
		commentatorTwoSponsorBox.setBackground(Color.DARK_GRAY);
		commentatorTwoSponsorBox.setBounds(1315, 679, 300, 40);
		add(commentatorTwoSponsorBox);
		
		JCheckBox restrictOutput = new JCheckBox("Restrict Output To One Line");
		restrictOutput.setSelected(true);
		restrictOutput.setForeground(Color.WHITE);
		restrictOutput.setBackground(Color.DARK_GRAY);
		restrictOutput.setFont(new Font("Arial Black", Font.BOLD, 14));
		restrictOutput.setBounds(625, 760, 370, 23);
		add(restrictOutput);
		
		JCheckBox swap = new JCheckBox("Swap Player Tag & Info Every");
		swap.setForeground(Color.WHITE);
		swap.setFont(new Font("Arial Black", Font.BOLD, 14));
		swap.setBackground(Color.DARK_GRAY);
		swap.setBounds(625, 786, 286, 23);
		add(swap);
		
		JSpinner timer = new JSpinner();
		timer.setFont(new Font("Arial Black", Font.BOLD, 14));
		timer.setBackground(Color.DARK_GRAY);
		timer.setBounds(917, 786, 42, 23);
		add(timer);
		
		JButton updateButton = new JButton("Update");
		updateButton.setFont(new Font("Arial Black", Font.BOLD, 18));
		updateButton.setBackground(Color.DARK_GRAY);
		updateButton.setBounds(625, 948, 370, 60);
		add(updateButton);
		
		singlesOrDoubles.setText("Singles");
		playerThreeSponsorBox.setEnabled(false);
		playerThreeTextField.setEnabled(false);
		playerThreeCharacterBox.setEnabled(false);
		playerFourSponsorBox.setEnabled(false);
		playerFourTextField.setEnabled(false);
		playerFourCharacterBox.setEnabled(false);
		
		// auto complete textfields
		String COMMIT1_ACTION = "commit";
		String COMMIT2_ACTION = "commit";
		String COMMIT3_ACTION = "commit";
		String COMMIT4_ACTION = "commit";
		String COMMIT5_ACTION = "commit";
		String COMMIT6_ACTION = "commit";
		
		playerOneTextField.setFocusTraversalKeysEnabled(false);
		playerOneTextField.getDocument().addDocumentListener(playerOneTextField);
		playerOneTextField.getInputMap().put(KeyStroke.getKeyStroke("TAB"), COMMIT1_ACTION);
		playerOneTextField.getActionMap().put(COMMIT1_ACTION, playerOneTextField.new CommitAction());

		playerTwoTextField.setFocusTraversalKeysEnabled(false);
		playerTwoTextField.getDocument().addDocumentListener(playerTwoTextField);
		playerTwoTextField.getInputMap().put(KeyStroke.getKeyStroke("TAB"), COMMIT2_ACTION);
		playerTwoTextField.getActionMap().put(COMMIT2_ACTION, playerTwoTextField.new CommitAction());
		
		playerThreeTextField.setFocusTraversalKeysEnabled(false);
		playerThreeTextField.getDocument().addDocumentListener(playerThreeTextField);
		playerThreeTextField.getInputMap().put(KeyStroke.getKeyStroke("TAB"), COMMIT3_ACTION);
		playerThreeTextField.getActionMap().put(COMMIT3_ACTION, playerThreeTextField.new CommitAction());

		playerFourTextField.setFocusTraversalKeysEnabled(false);
		playerFourTextField.getDocument().addDocumentListener(playerFourTextField);
		playerFourTextField.getInputMap().put(KeyStroke.getKeyStroke("TAB"), COMMIT4_ACTION);
		playerFourTextField.getActionMap().put(COMMIT4_ACTION, playerFourTextField.new CommitAction());
		
		commentatorOneTextField.setFocusTraversalKeysEnabled(false);
		commentatorOneTextField.getDocument().addDocumentListener(commentatorOneTextField);
		commentatorOneTextField.getInputMap().put(KeyStroke.getKeyStroke("TAB"), COMMIT5_ACTION);
		commentatorOneTextField.getActionMap().put(COMMIT5_ACTION, commentatorOneTextField.new CommitAction());

		commentatorTwoTextField.setFocusTraversalKeysEnabled(false);
		commentatorTwoTextField.getDocument().addDocumentListener(commentatorTwoTextField);
		commentatorTwoTextField.getInputMap().put(KeyStroke.getKeyStroke("TAB"), COMMIT6_ACTION);
		commentatorTwoTextField.getActionMap().put(COMMIT6_ACTION, commentatorTwoTextField.new CommitAction());
		
		characters = new ArrayList<String>();
		loadCharacters();
		loadSponsors();
		grabData();
		
		swapLabel.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(singles) {
					String p1 = playerTwoTextField.getText();
					String p2 = playerOneTextField.getText();
					int sponsor1 = playerTwoSponsorBox.getSelectedIndex();
					int sponsor2 = playerOneSponsorBox.getSelectedIndex();
					int score1 = Integer.parseInt(playerTwoScore.getText());
					int score2 = Integer.parseInt(playerOneScore.getText());
					int character1 = playerTwoCharacterBox.getSelectedIndex();
					int character2 = playerOneCharacterBox.getSelectedIndex();
					playerOneTextField.setText(p1);
					playerTwoTextField.setText(p2);
					playerOneSponsorBox.setSelectedIndex(sponsor1);
					playerOneCharacterBox.setSelectedIndex(character1);
					playerTwoSponsorBox.setSelectedIndex(sponsor2);
					playerTwoCharacterBox.setSelectedIndex(character2);
					playerOneScore.setText(""+score1);
					playerTwoScore.setText(""+score2);
				} else {
					String p1 = playerThreeTextField.getText();
					String p2 = playerFourTextField.getText();
					String p3 = playerOneTextField.getText();
					String p4 = playerTwoTextField.getText();
					int sponsor1 = playerThreeSponsorBox.getSelectedIndex();
					int sponsor2 = playerFourSponsorBox.getSelectedIndex();
					int sponsor3 = playerOneSponsorBox.getSelectedIndex();
					int sponsor4 = playerTwoSponsorBox.getSelectedIndex();
					int character1 = playerThreeCharacterBox.getSelectedIndex();
					int character2 = playerFourCharacterBox.getSelectedIndex();
					int character3 = playerOneCharacterBox.getSelectedIndex();
					int character4 = playerTwoCharacterBox.getSelectedIndex();
					int score1 = Integer.parseInt(playerTwoScore.getText());
					int score2 = Integer.parseInt(playerOneScore.getText());
					playerOneTextField.setText(p1);
					playerTwoTextField.setText(p2);
					playerThreeTextField.setText(p3);
					playerFourTextField.setText(p4);
					playerOneSponsorBox.setSelectedIndex(sponsor1);
					playerOneCharacterBox.setSelectedIndex(character1);
					playerTwoSponsorBox.setSelectedIndex(sponsor2);
					playerTwoCharacterBox.setSelectedIndex(character2);
					playerThreeSponsorBox.setSelectedIndex(sponsor3);
					playerFourSponsorBox.setSelectedIndex(sponsor4);
					playerThreeCharacterBox.setSelectedIndex(character3);
					playerFourCharacterBox.setSelectedIndex(character4);
					playerOneScore.setText(""+score1);
					playerTwoScore.setText(""+score2);
				}
				fm.writeToFiles();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
			}
			
		});
		
		updateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				setPlayerOneName();
				setPlayerOneInfo();
				setPlayerOneCharacter();
				setPlayerOneScore();
				setPlayerOneSponsor();
				setPlayerTwoName();
				setPlayerTwoInfo();
				setPlayerTwoScore();
				setPlayerTwoCharacter();
				setPlayerTwoSponsor();
				if(!singles) {
					setPlayerThreeName();
					setPlayerThreeInfo();
					setPlayerThreeCharacter();
					setPlayerThreeSponsor();
					setPlayerFourName();
					setPlayerFourInfo();
					setPlayerFourCharacter();
					setPlayerFourSponsor();
				}
				setCommentatorsName();
				setCommentatorsInfo();
				setCommentatorOneSponsor();
				setCommentatorTwoSponsor();
				setMainTitle();
				setCurrentRound();
				fm.writeToFiles();
				
			}
			
		});
		
		singlesOrDoubles.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(singles) singles = false;
				else
					singles = true;
				if(singles) {
					singlesOrDoubles.setText("Singles");
					playerThreeSponsorBox.setEnabled(false);
					playerThreeTextField.setEnabled(false);
					playerThreeCharacterBox.setEnabled(false);
					playerFourSponsorBox.setEnabled(false);
					playerFourTextField.setEnabled(false);
					playerFourCharacterBox.setEnabled(false);
				} else {
					singlesOrDoubles.setText("Doubles");
					playerThreeSponsorBox.setEnabled(true);
					playerThreeTextField.setEnabled(true);
					playerThreeCharacterBox.setEnabled(true);
					playerFourSponsorBox.setEnabled(true);
					playerFourTextField.setEnabled(true);
					playerFourCharacterBox.setEnabled(true);
				}
				repaint();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {}

			@Override
			public void mouseExited(MouseEvent arg0) {}

			@Override
			public void mousePressed(MouseEvent arg0) {}

			@Override
			public void mouseReleased(MouseEvent arg0) {}
			
		});
		
		playerOnePlus.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				int i = Integer.parseInt(playerOneScore.getText());
				i++;
				playerOneScore.setText("" + i);
				setPlayerOneScore();
				fm.writeToScores();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {}

			@Override
			public void mouseExited(MouseEvent arg0) {}

			@Override
			public void mousePressed(MouseEvent arg0) {}

			@Override
			public void mouseReleased(MouseEvent arg0) {}
			
		});
		
		playerTwoPlus.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				int i = Integer.parseInt(playerTwoScore.getText());
				i++;
				playerTwoScore.setText("" + i);
				setPlayerTwoScore();
				fm.writeToScores();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {}

			@Override
			public void mouseExited(MouseEvent arg0) {}

			@Override
			public void mousePressed(MouseEvent arg0) {}

			@Override
			public void mouseReleased(MouseEvent arg0) {}
			
		});
		
		playerOneMinus.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				int i = Integer.parseInt(playerOneScore.getText());
				i--;
				playerOneScore.setText("" + i);
				setPlayerOneScore();
				fm.writeToScores();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {}

			@Override
			public void mouseExited(MouseEvent arg0) {}

			@Override
			public void mousePressed(MouseEvent arg0) {}

			@Override
			public void mouseReleased(MouseEvent arg0) {}
			
		});
		
		playerTwoMinus.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				int i = Integer.parseInt(playerTwoScore.getText());
				i--;
				playerTwoScore.setText("" + i);
				setPlayerTwoScore();
				fm.writeToScores();
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {}

			@Override
			public void mouseExited(MouseEvent arg0) {}

			@Override
			public void mousePressed(MouseEvent arg0) {}

			@Override
			public void mouseReleased(MouseEvent arg0) {}
			
		});
		
		restrictOutput.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				restricted = restrictOutput.isSelected();
			}
			
		});
		
	}
	
	private void grabData() {
		
		mainTitle.setText(fm.getMainTitle());
		round.setText(fm.getCurrentRound());
		playerOneTextField.setText(fm.getPlayerOne());
		playerTwoTextField.setText(fm.getPlayerTwo());
		playerOneScore.setText("" + fm.getPlayerOneScore());
		playerTwoScore.setText("" + fm.getPlayerTwoScore());
		commentatorOneTextField.setText(fm.getCommentatorNames()[0]);
		commentatorTwoTextField.setText(fm.getCommentatorNames()[1]);
		
	}
	
	public static void loadSponsors() {
		
		playerOneSponsorBox.removeAllItems();
		playerTwoSponsorBox.removeAllItems();
		playerThreeSponsorBox.removeAllItems();
		playerFourSponsorBox.removeAllItems();
		commentatorOneSponsorBox.removeAll();
		commentatorTwoSponsorBox.removeAll();
		
		File f = new File(FileManager.getSponsorsDirectory() + "/");
		File[] list = f.listFiles();
		for(int i = 0; i < list.length; i++) {
			playerOneSponsorBox.addItem(list[i].getName());
			playerTwoSponsorBox.addItem(list[i].getName());
			playerThreeSponsorBox.addItem(list[i].getName());
			playerFourSponsorBox.addItem(list[i].getName());
			commentatorOneSponsorBox.addItem(list[i].getName());
			commentatorTwoSponsorBox.addItem(list[i].getName());
		}
		
	}
	
	private void loadCharacters() {
		
		characters.clear();
		playerOneCharacterBox.removeAllItems();
		playerTwoCharacterBox.removeAllItems();
		playerThreeCharacterBox.removeAllItems();
		playerFourCharacterBox.removeAllItems();
		
		File f = new File(FileManager.getCharactersDirectory() + "/");
		File[] list = f.listFiles();
		for(int i = 0; i < list.length; i++) {
			characters.add(list[i].getName());
			playerOneCharacterBox.addItem(list[i].getName());
			playerTwoCharacterBox.addItem(list[i].getName());
			playerThreeCharacterBox.addItem(list[i].getName());
			playerFourCharacterBox.addItem(list[i].getName());
		}
	}
	
	public void setMainTitle() {
		fm.setMainTitle(mainTitle.getText());
	}
	
	public void setCurrentRound() {
		fm.setCurrentRound(round.getText());
	}
	
	public void setPlayerOneScore() {
		fm.setPlayerOneScore(Integer.parseInt(playerOneScore.getText()));
	}
	
	public void setPlayerTwoScore() {
		fm.setPlayerTwoScore(Integer.parseInt(playerTwoScore.getText()));
	}
	
	public void setPlayerOneName() {
		fm.setPlayerOne(playerOneTextField.getText());
	}
	
	public void setPlayerOneInfo() {
		fm.setPlayerOneInfo(fm.getPlayersInfo(playerOneTextField.getText()));
	}
	
	public void setPlayerTwoName() {
		fm.setPlayerTwo(playerTwoTextField.getText());
	}
	
	public void setPlayerTwoInfo() {
		fm.setPlayerTwoInfo(fm.getPlayersInfo(playerTwoTextField.getText()));
	}
	
	public void setPlayerThreeName() {
		fm.setPlayerThree(playerThreeTextField.getText());
	}
	
	public void setPlayerThreeInfo() {
		fm.setPlayerThreeInfo(fm.getPlayersInfo(playerThreeTextField.getText()));
	}
	
	public void setPlayerFourName() {
		fm.setPlayerFour(playerFourTextField.getText());
	}
	
	public void setPlayerFourInfo() {
		fm.setPlayerFourInfo(fm.getPlayersInfo(playerFourTextField.getText()));
	}
	
	public void setCommentatorsName() {
		fm.setCommentators(commentatorOneTextField.getText(), commentatorTwoTextField.getText());
	}
	
	public void setCommentatorsInfo() {
		fm.setCommentatorsInfo(fm.getCommentatorsInfo(commentatorOneTextField.getText()), 
				fm.getCommentatorsInfo(commentatorTwoTextField.getText()));
	}
	
	public void setPlayerOneCharacter() {
		
		try {
			BufferedImage image = ImageIO.read(new File(FileManager.getCharactersDirectory() + "/" + characters.get(playerOneCharacterBox.getSelectedIndex())));
			fm.setPlayerOneCharacter(image);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setPlayerTwoCharacter() {
		
		try {
			BufferedImage image = ImageIO.read(new File(FileManager.getCharactersDirectory() + "/" + characters.get(playerTwoCharacterBox.getSelectedIndex())));
			fm.setPlayerTwoCharacter(image);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void setPlayerThreeCharacter() {
		
		try {
			BufferedImage image = ImageIO.read(new File(FileManager.getCharactersDirectory() + "/" + characters.get(playerThreeCharacterBox.getSelectedIndex())));
			fm.setPlayerThreeCharacter(image);
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}
	
	public void setPlayerFourCharacter() {
		
		try {
			BufferedImage image = ImageIO.read(new File(FileManager.getCharactersDirectory() + "/" + characters.get(playerFourCharacterBox.getSelectedIndex())));
			fm.setPlayerFourCharacter(image);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void setPlayerTwoSponsor() {

		try {
			BufferedImage image = ImageIO.read(new File(FileManager.getSponsorsDirectory() + "/" + (String)playerTwoSponsorBox.getSelectedItem()));
			fm.setPlayerTwoSponsor(image);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void setPlayerThreeSponsor() {

		try {
			BufferedImage image = ImageIO.read(new File(FileManager.getSponsorsDirectory() + "/" + (String)playerThreeSponsorBox.getSelectedItem()));
			//fm.setPlayerThreeSponsor(image);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void setPlayerFourSponsor() {

		try {
			BufferedImage image = ImageIO.read(new File(FileManager.getSponsorsDirectory() + "/" + (String)playerFourSponsorBox.getSelectedItem()));
			//fm.setPlayerFourSponsor(image);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void setPlayerOneSponsor() {

		try {
			BufferedImage image = ImageIO.read(new File(FileManager.getSponsorsDirectory() + "/" + (String)playerOneSponsorBox.getSelectedItem()));
			fm.setPlayerOneSponsor(image);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void setCommentatorOneSponsor() {

		try {
			BufferedImage image = ImageIO.read(new File(FileManager.getSponsorsDirectory() + "/" + (String)commentatorOneSponsorBox.getSelectedItem()));
			fm.setCommentatorOneSponsor(image);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void setCommentatorTwoSponsor() {

		try {
			BufferedImage image = ImageIO.read(new File(FileManager.getSponsorsDirectory() + "/" + (String)commentatorTwoSponsorBox.getSelectedItem()));
			fm.setCommentatorTwoSponsor(image);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static boolean getRestriction() {
		return restricted;
	}
	
	public static FileManager getFileManager() { 
		return fm;
	}

}
