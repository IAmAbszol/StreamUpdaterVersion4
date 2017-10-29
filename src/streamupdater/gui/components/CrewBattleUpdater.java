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
import streamupdater.utils.PlayerSuggestionField.CommitAction;

@SuppressWarnings("serial")
public class CrewBattleUpdater extends JPanel {

	private boolean singles = true;
	private static boolean restricted = true;
	
	private static FileManager fm;
	
	private PlayerSuggestionField schoolOnePlayerOneTextField;
	private PlayerSuggestionField schoolOnePlayerTwoTextField;
	private PlayerSuggestionField schoolOnePlayerThreeTextField;
	private PlayerSuggestionField schoolOnePlayerFourTextField;
	private PlayerSuggestionField schoolOnePlayerFiveTextField;
	
	private PlayerSuggestionField schoolTwoPlayerOneTextField;
	private PlayerSuggestionField schoolTwoPlayerTwoTextField;
	private PlayerSuggestionField schoolTwoPlayerThreeTextField;
	private PlayerSuggestionField schoolTwoPlayerFourTextField;
	private PlayerSuggestionField schoolTwoPlayerFiveTextField;
	
	private JTextField schoolOneName;
	private JTextField schoolTwoName;
	private JTextField selectedSchoolOne;
	private JTextField selectedSchoolTwo;
	
	private String lastSwappedSchoolOne = "";
	private String lastSwappedSchoolTwo = "";
	
	private ArrayList<String> characters;
	
	public CrewBattleUpdater() {

		fm = new FileManager();
		fm.init();
		
		setForeground(Color.WHITE);
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		JLabel singlesOrDoubles = new JLabel("Crew Battles");
		singlesOrDoubles.setHorizontalAlignment(SwingConstants.CENTER);
		singlesOrDoubles.setFont(new Font("Arial Black", Font.BOLD, 32));
		singlesOrDoubles.setBackground(Color.DARK_GRAY);
		singlesOrDoubles.setBounds(465, 11, 695, 65);
		singlesOrDoubles.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		add(singlesOrDoubles);
		
		schoolOneName = new JTextField("");
		schoolOneName.setBackground(Color.WHITE);
		schoolOneName.setHorizontalAlignment(SwingConstants.CENTER);
		schoolOneName.setFont(new Font("Arial Black", Font.BOLD, 14));
		schoolOneName.setBounds(315, 220, 300, 40);
		add(schoolOneName);
		schoolOneName.setColumns(10);
		
		schoolTwoName = new JTextField("");
		schoolTwoName.setBackground(Color.WHITE);
		schoolTwoName.setHorizontalAlignment(SwingConstants.CENTER);
		schoolTwoName.setFont(new Font("Arial Black", Font.BOLD, 14));
		schoolTwoName.setBounds(1005, 220, 300, 40);
		add(schoolTwoName);
		schoolTwoName.setColumns(10);
		
		schoolOnePlayerOneTextField = new PlayerSuggestionField("");
		schoolOnePlayerOneTextField.setBackground(Color.WHITE);
		schoolOnePlayerOneTextField.setHorizontalAlignment(SwingConstants.CENTER);
		schoolOnePlayerOneTextField.setFont(new Font("Arial Black", Font.BOLD, 14));
		schoolOnePlayerOneTextField.setBounds(315, 270, 300, 40);
		add(schoolOnePlayerOneTextField);
		schoolOnePlayerOneTextField.setColumns(10);
		
		schoolOnePlayerTwoTextField = new PlayerSuggestionField("");
		schoolOnePlayerTwoTextField.setHorizontalAlignment(SwingConstants.CENTER);
		schoolOnePlayerTwoTextField.setFont(new Font("Arial Black", Font.BOLD, 14));
		schoolOnePlayerTwoTextField.setColumns(10);
		schoolOnePlayerTwoTextField.setBounds(315, 320, 300, 40);
		add(schoolOnePlayerTwoTextField);
		
		schoolOnePlayerThreeTextField = new PlayerSuggestionField("");
		schoolOnePlayerThreeTextField.setHorizontalAlignment(SwingConstants.CENTER);
		schoolOnePlayerThreeTextField.setFont(new Font("Arial Black", Font.BOLD, 14));
		schoolOnePlayerThreeTextField.setColumns(10);
		schoolOnePlayerThreeTextField.setBackground(Color.WHITE);
		schoolOnePlayerThreeTextField.setBounds(315, 370, 300, 40);
		add(schoolOnePlayerThreeTextField);
		
		schoolOnePlayerFourTextField = new PlayerSuggestionField("");
		schoolOnePlayerFourTextField.setHorizontalAlignment(SwingConstants.CENTER);
		schoolOnePlayerFourTextField.setFont(new Font("Arial Black", Font.BOLD, 14));
		schoolOnePlayerFourTextField.setColumns(10);
		schoolOnePlayerFourTextField.setBackground(Color.WHITE);
		schoolOnePlayerFourTextField.setBounds(315, 420, 300, 40);
		add(schoolOnePlayerFourTextField);
		
		schoolOnePlayerFiveTextField = new PlayerSuggestionField("");
		schoolOnePlayerFiveTextField.setHorizontalAlignment(SwingConstants.CENTER);
		schoolOnePlayerFiveTextField.setFont(new Font("Arial Black", Font.BOLD, 14));
		schoolOnePlayerFiveTextField.setColumns(10);
		schoolOnePlayerFiveTextField.setBackground(Color.WHITE);
		schoolOnePlayerFiveTextField.setBounds(315, 470, 300, 40);
		add(schoolOnePlayerFiveTextField);
		
		schoolTwoPlayerOneTextField = new PlayerSuggestionField("");
		schoolTwoPlayerOneTextField.setBackground(Color.WHITE);
		schoolTwoPlayerOneTextField.setHorizontalAlignment(SwingConstants.CENTER);
		schoolTwoPlayerOneTextField.setFont(new Font("Arial Black", Font.BOLD, 14));
		schoolTwoPlayerOneTextField.setBounds(1005, 270, 300, 40);
		add(schoolTwoPlayerOneTextField);
		schoolTwoPlayerOneTextField.setColumns(10);
		
		schoolTwoPlayerTwoTextField = new PlayerSuggestionField("");
		schoolTwoPlayerTwoTextField.setHorizontalAlignment(SwingConstants.CENTER);
		schoolTwoPlayerTwoTextField.setFont(new Font("Arial Black", Font.BOLD, 14));
		schoolTwoPlayerTwoTextField.setColumns(10);
		schoolTwoPlayerTwoTextField.setBounds(1005, 320, 300, 40);
		add(schoolTwoPlayerTwoTextField);
		
		schoolTwoPlayerThreeTextField = new PlayerSuggestionField("");
		schoolTwoPlayerThreeTextField.setHorizontalAlignment(SwingConstants.CENTER);
		schoolTwoPlayerThreeTextField.setFont(new Font("Arial Black", Font.BOLD, 14));
		schoolTwoPlayerThreeTextField.setColumns(10);
		schoolTwoPlayerThreeTextField.setBackground(Color.WHITE);
		schoolTwoPlayerThreeTextField.setBounds(1005, 370, 300, 40);
		add(schoolTwoPlayerThreeTextField);
		
		schoolTwoPlayerFourTextField = new PlayerSuggestionField("");
		schoolTwoPlayerFourTextField.setHorizontalAlignment(SwingConstants.CENTER);
		schoolTwoPlayerFourTextField.setFont(new Font("Arial Black", Font.BOLD, 14));
		schoolTwoPlayerFourTextField.setColumns(10);
		schoolTwoPlayerFourTextField.setBackground(Color.WHITE);
		schoolTwoPlayerFourTextField.setBounds(1005, 420, 300, 40);
		add(schoolTwoPlayerFourTextField);
		
		schoolTwoPlayerFiveTextField = new PlayerSuggestionField("");
		schoolTwoPlayerFiveTextField.setHorizontalAlignment(SwingConstants.CENTER);
		schoolTwoPlayerFiveTextField.setFont(new Font("Arial Black", Font.BOLD, 14));
		schoolTwoPlayerFiveTextField.setColumns(10);
		schoolTwoPlayerFiveTextField.setBackground(Color.WHITE);
		schoolTwoPlayerFiveTextField.setBounds(1005, 470, 300, 40);
		add(schoolTwoPlayerFiveTextField);
		
		selectedSchoolOne = new JTextField("");
		selectedSchoolOne.setHorizontalAlignment(SwingConstants.CENTER);
		selectedSchoolOne.setFont(new Font("Arial Black", Font.BOLD, 14));
		selectedSchoolOne.setColumns(10);
		selectedSchoolOne.setBackground(Color.WHITE);
		selectedSchoolOne.setBounds(315, 550, 300, 40);
		add(selectedSchoolOne);
		selectedSchoolOne.setEditable(false);
		
		selectedSchoolTwo = new JTextField("");
		selectedSchoolTwo.setHorizontalAlignment(SwingConstants.CENTER);
		selectedSchoolTwo.setFont(new Font("Arial Black", Font.BOLD, 14));
		selectedSchoolTwo.setColumns(10);
		selectedSchoolTwo.setBackground(Color.WHITE);
		selectedSchoolTwo.setBounds(1005, 550, 300, 40);
		add(selectedSchoolTwo);
		selectedSchoolTwo.setEditable(false);
		
		JButton undoSchoolOne = new JButton("Undo");
		undoSchoolOne.setFont(new Font("Arial Black", Font.BOLD, 14));
		undoSchoolOne.setBackground(Color.DARK_GRAY);
		undoSchoolOne.setBounds(625, 550, 150, 40);
		add(undoSchoolOne);
		
		JButton undoSchoolTwo = new JButton("Undo");
		undoSchoolTwo.setFont(new Font("Arial Black", Font.BOLD, 14));
		undoSchoolTwo.setBackground(Color.DARK_GRAY);
		undoSchoolTwo.setBounds(840, 550, 150, 40);
		add(undoSchoolTwo);
		
		JButton updateButton = new JButton("Update");
		updateButton.setFont(new Font("Arial Black", Font.BOLD, 18));
		updateButton.setBackground(Color.DARK_GRAY);
		updateButton.setBounds(625, 948, 370, 60);
		add(updateButton);
		
		// auto complete textfields
		String COMMIT1_ACTION = "commit";
		String COMMIT2_ACTION = "commit";
		String COMMIT3_ACTION = "commit";
		String COMMIT4_ACTION = "commit";
		String COMMIT5_ACTION = "commit";
		String COMMIT6_ACTION = "commit";
		String COMMIT7_ACTION = "commit";
		String COMMIT8_ACTION = "commit";
		String COMMIT9_ACTION = "commit";
		String COMMIT10_ACTION = "commit";
		
		schoolOnePlayerOneTextField.setFocusTraversalKeysEnabled(false);
		schoolOnePlayerOneTextField.getDocument().addDocumentListener(schoolOnePlayerOneTextField);
		schoolOnePlayerOneTextField.getInputMap().put(KeyStroke.getKeyStroke("TAB"), COMMIT1_ACTION);
		schoolOnePlayerOneTextField.getActionMap().put(COMMIT1_ACTION, schoolOnePlayerOneTextField.new CommitAction());

		schoolOnePlayerTwoTextField.setFocusTraversalKeysEnabled(false);
		schoolOnePlayerTwoTextField.getDocument().addDocumentListener(schoolOnePlayerTwoTextField);
		schoolOnePlayerTwoTextField.getInputMap().put(KeyStroke.getKeyStroke("TAB"), COMMIT2_ACTION);
		schoolOnePlayerTwoTextField.getActionMap().put(COMMIT2_ACTION, schoolOnePlayerTwoTextField.new CommitAction());
		
		schoolOnePlayerThreeTextField.setFocusTraversalKeysEnabled(false);
		schoolOnePlayerThreeTextField.getDocument().addDocumentListener(schoolOnePlayerThreeTextField);
		schoolOnePlayerThreeTextField.getInputMap().put(KeyStroke.getKeyStroke("TAB"), COMMIT3_ACTION);
		schoolOnePlayerThreeTextField.getActionMap().put(COMMIT3_ACTION, schoolOnePlayerThreeTextField.new CommitAction());

		schoolOnePlayerFourTextField.setFocusTraversalKeysEnabled(false);
		schoolOnePlayerFourTextField.getDocument().addDocumentListener(schoolOnePlayerFourTextField);
		schoolOnePlayerFourTextField.getInputMap().put(KeyStroke.getKeyStroke("TAB"), COMMIT4_ACTION);
		schoolOnePlayerFourTextField.getActionMap().put(COMMIT4_ACTION, schoolOnePlayerFourTextField.new CommitAction());
		
		schoolOnePlayerFiveTextField.setFocusTraversalKeysEnabled(false);
		schoolOnePlayerFiveTextField.getDocument().addDocumentListener(schoolOnePlayerFiveTextField);
		schoolOnePlayerFiveTextField.getInputMap().put(KeyStroke.getKeyStroke("TAB"), COMMIT5_ACTION);
		schoolOnePlayerFiveTextField.getActionMap().put(COMMIT4_ACTION, schoolOnePlayerFiveTextField.new CommitAction());
		
		schoolTwoPlayerOneTextField.setFocusTraversalKeysEnabled(false);
		schoolTwoPlayerOneTextField.getDocument().addDocumentListener(schoolTwoPlayerOneTextField);
		schoolTwoPlayerOneTextField.getInputMap().put(KeyStroke.getKeyStroke("TAB"), COMMIT1_ACTION);
		schoolTwoPlayerOneTextField.getActionMap().put(COMMIT1_ACTION, schoolTwoPlayerOneTextField.new CommitAction());

		schoolTwoPlayerTwoTextField.setFocusTraversalKeysEnabled(false);
		schoolTwoPlayerTwoTextField.getDocument().addDocumentListener(schoolTwoPlayerTwoTextField);
		schoolTwoPlayerTwoTextField.getInputMap().put(KeyStroke.getKeyStroke("TAB"), COMMIT2_ACTION);
		schoolTwoPlayerTwoTextField.getActionMap().put(COMMIT2_ACTION, schoolTwoPlayerTwoTextField.new CommitAction());
		
		schoolTwoPlayerThreeTextField.setFocusTraversalKeysEnabled(false);
		schoolTwoPlayerThreeTextField.getDocument().addDocumentListener(schoolTwoPlayerThreeTextField);
		schoolTwoPlayerThreeTextField.getInputMap().put(KeyStroke.getKeyStroke("TAB"), COMMIT3_ACTION);
		schoolTwoPlayerThreeTextField.getActionMap().put(COMMIT3_ACTION, schoolTwoPlayerThreeTextField.new CommitAction());

		schoolTwoPlayerFourTextField.setFocusTraversalKeysEnabled(false);
		schoolTwoPlayerFourTextField.getDocument().addDocumentListener(schoolTwoPlayerFourTextField);
		schoolTwoPlayerFourTextField.getInputMap().put(KeyStroke.getKeyStroke("TAB"), COMMIT4_ACTION);
		schoolTwoPlayerFourTextField.getActionMap().put(COMMIT4_ACTION, schoolTwoPlayerFourTextField.new CommitAction());
		
		schoolTwoPlayerFiveTextField.setFocusTraversalKeysEnabled(false);
		schoolTwoPlayerFiveTextField.getDocument().addDocumentListener(schoolTwoPlayerFiveTextField);
		schoolTwoPlayerFiveTextField.getInputMap().put(KeyStroke.getKeyStroke("TAB"), COMMIT5_ACTION);
		schoolTwoPlayerFiveTextField.getActionMap().put(COMMIT4_ACTION, schoolTwoPlayerFiveTextField.new CommitAction());
		
		JLabel schoolOnePlayerOneMinus = new JLabel("");
		try {
			schoolOnePlayerOneMinus.setIcon(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("/images/minusCB.png"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		schoolOnePlayerOneMinus.setBounds(275, 560, 25, 25);
		add(schoolOnePlayerOneMinus);
		
		JLabel schoolOnePlayerTwoMinus = new JLabel("");
		try {
			schoolOnePlayerTwoMinus.setIcon(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("/images/minusCB.png"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		schoolOnePlayerTwoMinus.setBounds(1320, 560, 25, 25);
		add(schoolOnePlayerTwoMinus);
		
		JLabel schoolOnePlayerOnePlus = new JLabel("");
		try {
			schoolOnePlayerOnePlus.setIcon(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("/images/plusCB.png"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		schoolOnePlayerOnePlus.setBounds(275, 275, 25, 25);
		add(schoolOnePlayerOnePlus);
		
		JLabel schoolOnePlayerTwoPlus = new JLabel("");
		try {
			schoolOnePlayerTwoPlus.setIcon(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("/images/plusCB.png"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		schoolOnePlayerTwoPlus.setBounds(275, 325, 25, 25);
		add(schoolOnePlayerTwoPlus);
		
		JLabel schoolOnePlayerThreePlus = new JLabel("");
		try {
			schoolOnePlayerThreePlus.setIcon(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("/images/plusCB.png"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		schoolOnePlayerThreePlus.setBounds(275, 375, 25, 25);
		add(schoolOnePlayerThreePlus);
		
		JLabel schoolOnePlayerFourPlus = new JLabel("");
		try {
			schoolOnePlayerFourPlus.setIcon(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("/images/plusCB.png"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		schoolOnePlayerFourPlus.setBounds(275, 425, 25, 25);
		add(schoolOnePlayerFourPlus);
		
		JLabel schoolOnePlayerFivePlus = new JLabel("");
		try {
			schoolOnePlayerFivePlus.setIcon(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("/images/plusCB.png"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		schoolOnePlayerFivePlus.setBounds(275, 475, 25, 25);
		add(schoolOnePlayerFivePlus);
		
		JLabel schoolTwoPlayerOnePlus = new JLabel("");
		try {
			schoolTwoPlayerOnePlus.setIcon(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("/images/plusCB.png"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		schoolTwoPlayerOnePlus.setBounds(1320, 275, 25, 25);
		add(schoolTwoPlayerOnePlus);
		
		JLabel schoolTwoPlayerTwoPlus = new JLabel("");
		try {
			schoolTwoPlayerTwoPlus.setIcon(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("/images/plusCB.png"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		schoolTwoPlayerTwoPlus.setBounds(1320, 325, 25, 25);
		add(schoolTwoPlayerTwoPlus);
		
		JLabel schoolTwoPlayerThreePlus = new JLabel("");
		try {
			schoolTwoPlayerThreePlus.setIcon(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("/images/plusCB.png"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		schoolTwoPlayerThreePlus.setBounds(1320, 375, 25, 25);
		add(schoolTwoPlayerThreePlus);
		
		JLabel schoolTwoPlayerFourPlus = new JLabel("");
		try {
			schoolTwoPlayerFourPlus.setIcon(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("/images/plusCB.png"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		schoolTwoPlayerFourPlus.setBounds(1320, 425, 25, 25);
		add(schoolTwoPlayerFourPlus);
		
		JLabel schoolTwoPlayerFivePlus = new JLabel("");
		try {
			schoolTwoPlayerFivePlus.setIcon(new ImageIcon(ImageIO.read(this.getClass().getResourceAsStream("/images/plusCB.png"))));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		schoolTwoPlayerFivePlus.setBounds(1320, 475, 25, 25);
		add(schoolTwoPlayerFivePlus);
		
		
		grabData();
		
		undoSchoolOne.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!selectedSchoolOne.getText().equals("")) {
					switch(lastSwappedSchoolOne) {
					case "SP1":
						schoolOnePlayerOneTextField.setText(selectedSchoolOne.getText());
						lastSwappedSchoolOne = "";
						selectedSchoolOne.setText("");
						break;
					case "SP2":
						schoolOnePlayerTwoTextField.setText(selectedSchoolOne.getText());
						lastSwappedSchoolOne = "";
						selectedSchoolOne.setText("");
						break;
					case "SP3":
						schoolOnePlayerThreeTextField.setText(selectedSchoolOne.getText());
						lastSwappedSchoolOne = "";
						selectedSchoolOne.setText("");
						break;
					case "SP4":
						schoolOnePlayerFourTextField.setText(selectedSchoolOne.getText());
						lastSwappedSchoolOne = "";
						selectedSchoolOne.setText("");
						break;
					case "SP5":
						schoolOnePlayerFiveTextField.setText(selectedSchoolOne.getText());
						lastSwappedSchoolOne = "";
						selectedSchoolOne.setText("");
						break;
					}
				}
			}
		});
		
		undoSchoolTwo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!selectedSchoolTwo.getText().equals("")) {
					switch(lastSwappedSchoolTwo) {
					case "SP1":
						schoolTwoPlayerOneTextField.setText(selectedSchoolTwo.getText());
						lastSwappedSchoolTwo = "";
						selectedSchoolTwo.setText("");
						break;
					case "SP2":
						schoolTwoPlayerTwoTextField.setText(selectedSchoolTwo.getText());
						lastSwappedSchoolTwo = "";
						selectedSchoolTwo.setText("");
						break;
					case "SP3":
						schoolTwoPlayerThreeTextField.setText(selectedSchoolTwo.getText());
						lastSwappedSchoolTwo = "";
						selectedSchoolTwo.setText("");
						break;
					case "SP4":
						schoolTwoPlayerFourTextField.setText(selectedSchoolTwo.getText());
						lastSwappedSchoolTwo = "";
						selectedSchoolTwo.setText("");
						break;
					case "SP5":
						schoolTwoPlayerFiveTextField.setText(selectedSchoolTwo.getText());
						lastSwappedSchoolTwo = "";
						selectedSchoolTwo.setText("");
						break;
					}
				}
			}
		});
		
		schoolOnePlayerOnePlus.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(lastSwappedSchoolOne.equals("")) {
					lastSwappedSchoolOne = "SP1";
					selectedSchoolOne.setText(schoolOnePlayerOneTextField.getText());
					schoolOnePlayerOneTextField.setText("");
				}
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			
		});
		
		schoolOnePlayerTwoPlus.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(lastSwappedSchoolOne.equals("")) {
					lastSwappedSchoolOne = "SP2";
					selectedSchoolOne.setText(schoolOnePlayerTwoTextField.getText());
					schoolOnePlayerTwoTextField.setText("");
				}
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			
		});
		
		schoolOnePlayerThreePlus.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(lastSwappedSchoolOne.equals("")) {
					lastSwappedSchoolOne = "SP3";
					selectedSchoolOne.setText(schoolOnePlayerThreeTextField.getText());
					schoolOnePlayerThreeTextField.setText("");
				}
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			
		});
		
		schoolOnePlayerFourPlus.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(lastSwappedSchoolOne.equals("")) {
					lastSwappedSchoolOne = "SP4";
					selectedSchoolOne.setText(schoolOnePlayerFourTextField.getText());
					schoolOnePlayerFourTextField.setText("");
				}
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			
		});
		
		schoolOnePlayerFivePlus.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(lastSwappedSchoolOne.equals("")) {
					lastSwappedSchoolOne = "SP5";
					selectedSchoolOne.setText(schoolOnePlayerFiveTextField.getText());
					schoolOnePlayerFiveTextField.setText("");
				}
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			
		});		
		
		schoolTwoPlayerOnePlus.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(lastSwappedSchoolTwo.equals("")) {
					lastSwappedSchoolTwo = "SP1";
					selectedSchoolTwo.setText(schoolTwoPlayerOneTextField.getText());
					schoolTwoPlayerOneTextField.setText("");
				}
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			
		});
		
		schoolTwoPlayerTwoPlus.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(lastSwappedSchoolTwo.equals("")) {
					lastSwappedSchoolTwo = "SP2";
					selectedSchoolTwo.setText(schoolTwoPlayerTwoTextField.getText());
					schoolTwoPlayerTwoTextField.setText("");
				}
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			
		});
		
		schoolTwoPlayerThreePlus.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(lastSwappedSchoolTwo.equals("")) {
					lastSwappedSchoolTwo = "SP3";
					selectedSchoolTwo.setText(schoolTwoPlayerThreeTextField.getText());
					schoolTwoPlayerThreeTextField.setText("");
				}
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			
		});
		
		schoolTwoPlayerFourPlus.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(lastSwappedSchoolTwo.equals("")) {
					lastSwappedSchoolTwo = "SP4";
					selectedSchoolTwo.setText(schoolTwoPlayerFourTextField.getText());
					schoolTwoPlayerFourTextField.setText("");
				}
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			
		});
		
		schoolTwoPlayerFivePlus.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(lastSwappedSchoolTwo.equals("")) {
					lastSwappedSchoolTwo = "SP5";
					selectedSchoolTwo.setText(schoolTwoPlayerFiveTextField.getText());
					schoolTwoPlayerFiveTextField.setText("");
				}
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			
		});	
		
		schoolOnePlayerOneMinus.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				selectedSchoolOne.setText("");
				lastSwappedSchoolOne = "";
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			
		});	
		
		schoolOnePlayerTwoMinus.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				selectedSchoolTwo.setText("");
				lastSwappedSchoolTwo = "";
			}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			
		});	
		
		updateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				setSchoolOneName();
				setSchoolTwoName();
				setSchoolOnePlayerOne();
				setSchoolTwoPlayerOne();
				setSchoolOnePlayerTwo();
				setSchoolTwoPlayerTwo();
				setSchoolOnePlayerThree();
				setSchoolTwoPlayerThree();
				setSchoolOnePlayerFour();
				setSchoolTwoPlayerFour();
				setSchoolOnePlayerFive();
				setSchoolTwoPlayerFive();
				setSelectedSchoolOne();
				setSelectedSchoolTwo();
				
				fm.writeToTMG();
				
			}
			
		});
		
	}
	
	private void grabData() {
		schoolOneName.setText(fm.getSchoolOne());
		schoolOnePlayerOneTextField.setText(fm.getSchoolOnePlayerOne());
		schoolOnePlayerTwoTextField.setText(fm.getSchoolOnePlayerTwo());
		schoolOnePlayerThreeTextField.setText(fm.getSchoolOnePlayerThree());
		schoolOnePlayerFourTextField.setText(fm.getSchoolOnePlayerFour());
		schoolOnePlayerFiveTextField.setText(fm.getSchoolOnePlayerFive());
		schoolTwoName.setText(fm.getSchoolTwo());
		schoolTwoPlayerOneTextField.setText(fm.getSchoolTwoPlayerOne());
		schoolTwoPlayerTwoTextField.setText(fm.getSchoolTwoPlayerTwo());
		schoolTwoPlayerThreeTextField.setText(fm.getSchoolTwoPlayerThree());
		schoolTwoPlayerFourTextField.setText(fm.getSchoolTwoPlayerFour());
		schoolTwoPlayerFiveTextField.setText(fm.getSchoolTwoPlayerFive());
	}
	
	public void setSelectedSchoolOne() {
		fm.setPlayerOne(selectedSchoolOne.getText() + "/" + schoolOneName.getText());
	}
	
	public void setSelectedSchoolTwo() {
		fm.setPlayerTwo(selectedSchoolTwo.getText() + "/" + schoolTwoName.getText());
	}
	
	public void setSchoolOneName() {
		fm.setSchoolOne(schoolOneName.getText());
	}
	
	public void setSchoolOnePlayerOne() {
		fm.setSchoolOnePlayerOne(schoolOnePlayerOneTextField.getText());
	}
	
	public void setSchoolOnePlayerTwo() {
		fm.setSchoolOnePlayerTwo(schoolOnePlayerTwoTextField.getText());
	}
	
	public void setSchoolOnePlayerThree() {
		fm.setSchoolOnePlayerThree(schoolOnePlayerThreeTextField.getText());
	}
	public void setSchoolOnePlayerFour() {
		fm.setSchoolOnePlayerFour(schoolOnePlayerFourTextField.getText());
	}
	
	public void setSchoolOnePlayerFive() {
		fm.setSchoolOnePlayerFive(schoolOnePlayerFiveTextField.getText());
	}
	
	public void setSchoolTwoName() {
		fm.setSchoolTwo(schoolTwoName.getText());
	}
	
	public void setSchoolTwoPlayerOne() {
		fm.setSchoolTwoPlayerOne(schoolTwoPlayerOneTextField.getText());
	}
	
	public void setSchoolTwoPlayerTwo() {
		fm.setSchoolTwoPlayerTwo(schoolTwoPlayerTwoTextField.getText());
	}
	
	public void setSchoolTwoPlayerThree() {
		fm.setSchoolTwoPlayerThree(schoolTwoPlayerThreeTextField.getText());
	}
	public void setSchoolTwoPlayerFour() {
		fm.setSchoolTwoPlayerFour(schoolTwoPlayerFourTextField.getText());
	}
	
	public void setSchoolTwoPlayerFive() {
		fm.setSchoolTwoPlayerFive(schoolTwoPlayerFiveTextField.getText());
	}
	
	public static boolean getRestriction() {
		return restricted;
	}
	
	public static FileManager getFileManager() { 
		return fm;
	}

}
