package streamupdater.gui.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import streamupdater.files.FileManager;
import streamupdater.stream.ThumbnailObject;
import streamupdater.stream.ThumbnailSave;
import streamupdater.utils.TextEditor;

@SuppressWarnings("serial")
public class ThumbnailEditor extends JPanel implements Runnable, KeyListener, MouseListener {
	
	private JComboBox layerBox;
	private int pos = 0;
	
	private JFileChooser jfc;
	
	// automated gui, I'm lazy and its dynamic
	private static int numberOfLayers = 32;		// even numbers only guys and keep above 8
	
	private Preview preview;
	
	private JSpinner localWidth;
	private JSpinner localHeight;
	private JSpinner posx;
	private JSpinner posy;
	private boolean ignore = false;
	
	private JButton remove;
	private JButton add;
	private JButton select;
	private JButton edit;
	
	// drawing stoof
	public static int WIDTH = 1280;
	public static int HEIGHT = 720;
	
	private Thread thread;
	private boolean running;
	private int FPS = 60;

	private File location;
	private static BufferedImage panelImage;
	private static BufferedImage image;
	private static Graphics2D g;
	private static boolean pause = false;
	private boolean onTab = false;
	
	// when it gets too big, annoying issues start to happen with the text. This automatically fixes it
	private static int[] overrideSizes = {
			8,
			9,
			10,
			11,
			12,
			14,
			16,
			18,
			20,
			22,
			24,
			26,
			28,
			36,
			48,
			72
	};
	
	// adjust this, low the more sensitive the changing of the font is.
	private static int sensitivity = 4;
	
	// layer stoof
	// layer 0 --> at 0. layer 1 --> at 1
	private static ThumbnailObject[] layers;
	public static TextEditor[] te;

	public ThumbnailEditor() {
		
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		preview = new Preview();
		preview.setBounds(172, 149, 1280, 720);
		add(preview);
		
		
		layerBox = new JComboBox();
		layerBox.setBackground(Color.DARK_GRAY);
		layerBox.setFont(new Font("Arial Black", Font.BOLD, 14));
		layerBox.setBounds(10, 11, 100, 40);
		add(layerBox);
		
		add = new JButton("Add");
		add.setBackground(Color.DARK_GRAY);
		add.setFont(new Font("Arial Black", Font.BOLD, 14));
		add.setBounds(120, 11, 100, 40);
		add(add);
		
		select = new JButton("Select");
		select.setBackground(Color.DARK_GRAY);
		select.setFont(new Font("Arial Black", Font.BOLD, 14));
		select.setBounds(230, 11, 100, 40);
		add(select);
		
		edit = new JButton("Edit");
		edit.setBackground(Color.DARK_GRAY);
		edit.setFont(new Font("Arial Black", Font.BOLD, 14));
		edit.setBounds(340, 11, 100, 40);
		add(edit);
		
		remove = new JButton("Remove");
		remove.setBackground(Color.DARK_GRAY);
		remove.setFont(new Font("Arial Black", Font.BOLD, 14));
		remove.setBounds(450, 11, 100, 40);
		add(remove);
		
		JButton generate = new JButton("Generate Test Image");
		generate.setBackground(Color.DARK_GRAY);
		generate.setFont(new Font("Arial Black", Font.BOLD, 14));
		generate.setBounds(1376, 11, 239, 40);
		add(generate);
		
		JButton deselect = new JButton("Deselect All Layers");
		deselect.setBackground(Color.DARK_GRAY);
		deselect.setFont(new Font("Arial Black", Font.BOLD, 14));
		deselect.setBounds(120, 62, 210, 40);
		add(deselect);
		
		JButton save = new JButton("Save");
		save.setToolTipText("Save your template");
		save.setBackground(Color.DARK_GRAY);
		save.setFont(new Font("Arial Black", Font.BOLD, 14));
		save.setBounds(340, 62, 100, 40);
		add(save);
		
		JButton load = new JButton("Load");
		load.setToolTipText("Load a previous template");
		load.setBackground(Color.DARK_GRAY);
		load.setFont(new Font("Arial Black", Font.BOLD, 14));
		load.setBounds(450, 62, 100, 40);
		add(load);
		
		JLabel widthLabel = new JLabel("Width");
		widthLabel.setHorizontalAlignment(SwingConstants.CENTER);
		widthLabel.setForeground(Color.WHITE);
		widthLabel.setFont(new Font("Arial Black", Font.BOLD, 14));
		widthLabel.setBounds(10, 149, 70, 40);
		add(widthLabel);
		
		JLabel heightLabel = new JLabel("Height");
		heightLabel.setHorizontalAlignment(SwingConstants.CENTER);
		heightLabel.setFont(new Font("Arial Black", Font.BOLD, 14));
		heightLabel.setForeground(Color.WHITE);
		heightLabel.setBounds(90, 149, 70, 40);
		add(heightLabel);
		
		localWidth = new JSpinner();
		localWidth.setFont(new Font("Arial Black", Font.BOLD, 14));
		localWidth.setBounds(10, 184, 70, 20);
		add(localWidth);
		
		localHeight = new JSpinner();
		localHeight.setFont(new Font("Arial Black", Font.BOLD, 14));
		localHeight.setBounds(90, 184, 70, 20);
		add(localHeight);
		
		JLabel xSizeLabel = new JLabel("X");
		xSizeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		xSizeLabel.setForeground(Color.WHITE);
		xSizeLabel.setFont(new Font("Arial Black", Font.BOLD, 14));
		xSizeLabel.setBounds(10, 215, 70, 40);
		add(xSizeLabel);
		
		JLabel ySizeLabel = new JLabel("Y");
		ySizeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ySizeLabel.setForeground(Color.WHITE);
		ySizeLabel.setFont(new Font("Arial Black", Font.BOLD, 14));
		ySizeLabel.setBounds(90, 215, 70, 40);
		add(ySizeLabel);
		
		posx = new JSpinner();
		posx.setFont(new Font("Arial Black", Font.BOLD, 14));
		posx.setBounds(10, 248, 70, 20);
		add(posx);
		
		posy = new JSpinner();
		posy.setFont(new Font("Arial Black", Font.BOLD, 14));
		posy.setBounds(92, 248, 70, 20);
		add(posy);
		
		pause = true;
		layers = new ThumbnailObject[numberOfLayers];
		te = new TextEditor[numberOfLayers];
		
		for(int i = 0; i < layers.length; i++) {
			layers[i] = new ThumbnailObject();
		}
		
		for(int i = 0; i < numberOfLayers; i++) {
			layerBox.addItem("Layer [" + i + "]");
		}
		
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<ThumbnailObject> to = new ArrayList<ThumbnailObject>(Arrays.asList(layers));
				ArrayList<TextEditor> editor = new ArrayList<TextEditor>(Arrays.asList(te));
				ThumbnailSave ts = new ThumbnailSave();
				ts.setListObjects(to);
				ts.setTextEditor(editor);
				ts.save(to, editor);
			}
		});
		
		load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pause = true;
				ThumbnailSave ts = new ThumbnailSave();
				ts.load();
				ArrayList<ThumbnailObject> to = ts.getThumbnailObject();
				ArrayList<TextEditor> editor = ts.getTextEditor();
				layers = new ThumbnailObject[to.size()];
				te = new TextEditor[editor.size()];
				for(int i = 0; i < layers.length; i++) {
					if(i == 0) {
						layers[i] = to.get(i);
						te[i] = editor.get(i);
					} else {
						layers[i] = to.get(i);
						te[i] = editor.get(i);
					}
					if(layers[i].isSelected()) System.out.println(i + " - true");
					updateSpecificLayer(i);
				}
				pause = false;
			}
		});
		
		remove.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				pos = layerBox.getSelectedIndex();
				layers[pos].reset();
				add.setToolTipText("");
				if(te[pos] != null) {
					te[pos].getFrame().dispose();
					te[pos] = null;
				}
			}
			
		});
		select.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pos = layerBox.getSelectedIndex();
				if(layers[pos].getImage() != null) {
					for(int i = 0; i < layers.length; i++) {
						layers[i].setSelected(false);
					}
					layers[pos].setSelected(true);
					localWidth.setValue(layers[pos].getWidth());
					localHeight.setValue(layers[pos].getHeight());
					posx.setValue(layers[pos].getX());
					posy.setValue(layers[pos].getY());
				}			
			}			
		});
		edit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pos = layerBox.getSelectedIndex();
				if(layers[pos].getImage() != null) {
					if(te[pos] != null) {
						te[pos].getFrame().setVisible(true);
					}
				}
			}
		});
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				updateLayer();
			}
		});
		
		pause = false;
		
		deselect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for(int i = 0; i < numberOfLayers; i++) {
					layers[i].setSelected(false);
				}
			}
			
		});
		
		generate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					generatePanel(null);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		
		posx.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				for(int i = 0; i < layers.length; i++) {
					if(layers[i].isSelected()) {
						layers[i].setX((int) posx.getValue());
					}
				}
			}
			
		});
		
		posy.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				for(int i = 0; i < layers.length; i++) {
					if(layers[i].isSelected()) {
						layers[i].setY((int) posy.getValue());
					}
				}
			}
			
		});
		
		localWidth.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				for(int i = 0; i < layers.length; i++) {
					if(layers[i].isSelected())
						layers[i].setWidth((int) localWidth.getValue());
				}
			}
		});
		
		localHeight.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(!ignore) {
					for(int i = 0; i < layers.length; i++) {
						if(layers[i].isSelected())
							layers[i].setHeight((int) localHeight.getValue());
					}
				} else {
					ignore = false;
				}
			}
		});
		
	}
	
	public void updateSpecificLayer(int x) {
		try {
			pos = x;
			if(layers[pos].getFile() == null) return;
			if(!layers[pos].isReversed())
				if(layers[pos].getFile().getName().contains(".txt")) {
					te[pos] = new TextEditor(pos);
					te[pos].getFrame().setVisible(true);
					// load defaults, this will be overriden when saved
					layers[pos].setFont(te[pos].getFont());
					layers[pos].setAlignment(te[pos].getAlignment());
					layers[pos].setSize(te[pos].getSize());
					layers[pos].setColor(te[pos].getColor()[0], te[pos].getColor()[1], te[pos].getColor()[2]);
					layers[pos].setBold(te[pos].isBold());
					layers[pos].setItalic(te[pos].isItalic());
					layers[pos].setAdjusted(te[pos].isAdjusted());
					layers[pos].setWidth(te[pos].getWidth());
					layers[pos].setHeight(te[pos].getHeight());
					layers[pos].setImage(convertTextToImage(layers[pos].getFile(), pos));
				} else 
					if(layers[pos].getFile().getName().contains("png") ||
							layers[pos].getFile().getName().contains("jpg") ||
							layers[pos].getFile().getName().contains("jpeg") ||
							layers[pos].getFile().getName().contains("bmp")){
					layers[pos].setImage(ImageIO.read(layers[pos].getFile()));
					layers[pos].setX(0);
					layers[pos].setY(0);
					layers[pos].setWidth((int) (layers[pos].getImage().getWidth()));
					layers[pos].setHeight((int) (layers[pos].getImage().getHeight()));
				} else {
					JOptionPane.showMessageDialog(null, "txt, png, jpg, and bmp files only!");
					layers[pos].reset();
					add.setToolTipText("");
					if(te[pos] != null) {
						te[pos].getFrame().dispose();
						te[pos] = null;
					}
					return;
				}
			else {
				reverseImage(pos);
			}
			edit.setEnabled(true);
			add.setToolTipText(layers[pos].getFile().getName());
		} catch (Exception e2) {
			System.out.println("Unable to access specific file.");
			e2.printStackTrace();
		}
	}
	
	public void updateLayer() {
		pos = layerBox.getSelectedIndex();
		jfc = new JFileChooser();
		jfc.setCurrentDirectory(new java.io.File("user.home"));
		jfc.setDialogTitle("Select Layer 0 Image File");
		jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if (jfc.showOpenDialog(add) == JFileChooser.APPROVE_OPTION) {
			try {
				layers[pos].setFile(jfc.getSelectedFile());
				if(!layers[pos].isReversed())
					if(layers[pos].getFile().getName().contains(".txt")) {
						if(te[pos] == null)
							te[pos] = new TextEditor(pos);
						else
							te[pos].getFrame().setVisible(true);
						// load defaults, this will be overriden when saved
						layers[pos].setFont(te[pos].getFont());
						layers[pos].setAlignment(te[pos].getAlignment());
						layers[pos].setSize(te[pos].getSize());
						layers[pos].setColor(te[pos].getColor()[0], te[pos].getColor()[1], te[pos].getColor()[2]);
						layers[pos].setBold(te[pos].isBold());
						layers[pos].setItalic(te[pos].isItalic());
						layers[pos].setAdjusted(te[pos].isAdjusted());
						layers[pos].setWidth(te[pos].getWidth());
						layers[pos].setHeight(te[pos].getHeight());
						layers[pos].setImage(convertTextToImage(layers[pos].getFile(), pos));
					} else 
						if(layers[pos].getFile().getName().contains("png") ||
								layers[pos].getFile().getName().contains("jpg") ||
								layers[pos].getFile().getName().contains("jpeg") ||
								layers[pos].getFile().getName().contains("bmp")){
						layers[pos].setImage(ImageIO.read(layers[pos].getFile()));
						layers[pos].setX(0);
						layers[pos].setY(0);
						layers[pos].setWidth((int) (layers[pos].getImage().getWidth()));
						layers[pos].setHeight((int) (layers[pos].getImage().getHeight()));
					} else {
						JOptionPane.showMessageDialog(null, "txt, png, jpg, and bmp files only!");
						layers[pos].reset();
						add.setToolTipText("");
						if(te[pos] != null) {
							te[pos].getFrame().dispose();
							te[pos] = null;
						}
						return;
					}
				else {
					reverseImage(pos);
				}
				edit.setEnabled(true);
				add.setToolTipText(jfc.getSelectedFile().getAbsolutePath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void addNotify() {
		super.addNotify();
		if(thread == null) {
			thread = new Thread(this);
			preview.addKeyListener(this);
			preview.addMouseListener(this);
			thread.start();
		}
	}
	
	private void init() {
		image = new BufferedImage(WIDTH, HEIGHT,
				BufferedImage.TYPE_INT_ARGB);
		g = (Graphics2D) image.getGraphics();
		running = true;
	}
	
	private long redraw() {
		
		long t = System.currentTimeMillis();
		
		if(onTab) {
		
			if(!FileManager.isSleeping())
				update();
			
			if(!pause) {
				draw();
				//drawToScreen();
				preview.repaint();
			}
		
		}
		
		return System.currentTimeMillis() - t;
	}
	
	public void run() {
		
		init();
		
		while(running) {
			
			long durationMs = redraw();
			
			try {
				Thread.sleep(Math.max(0, FPS - durationMs));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private void update () {
		reupdateImages();
	}
	
	private static void draw() {
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		for(int i = 0; i < layers.length; i++) {
			if(layers[i] != null) {
				g.drawImage(
						layers[i].getImage(), 
						layers[i].getX(), 
						layers[i].getY(), 
						layers[i].getWidth(), 
						layers[i].getHeight(), 
						null
				);
				g.setColor(Color.red);
				for(int j = 0; j < layers.length; j++) {
					if(layers[j].isSelected()) 
						g.drawRect(
								layers[j].getX(),
								layers[j].getY(),
								layers[j].getWidth(),
								layers[j].getHeight()
						);
				}
			}
		}
		panelImage = image;
	}
	
	public static BufferedImage generateThumbnail() {
		// so draw doesnt interfere
		if(!pause) pause = true;
		for(int i = 0; i < numberOfLayers; i++) {
			layers[i].setSelected(false);
		}
		draw();
		int genwidth = 1280;
		int genheight = 720;
		// manipulate the width and height to specs
		
		BufferedImage resized = new BufferedImage(genwidth, genheight, BufferedImage.TYPE_INT_ARGB);
		Graphics g = resized.createGraphics();
		g.drawImage(panelImage, 0, 0, genwidth, genheight, null);
		g.dispose();
		pause = false;
		return resized;
	}
	
	public static void deselect() {
		for(int i = 0; i < numberOfLayers; i++) {
			layers[i].setSelected(false);
		}
	}
	
	private void generatePanel(String n) throws IOException {
		if(!pause) pause = true;
		for(int i = 0; i < numberOfLayers; i++) {
			layers[i].setSelected(false);
		}
		draw();
		String user = System.getProperty("user.name");
		String location = FileManager.getMediaDirectory().replaceAll("/", "\\\\") + "\\"; 
		
		int genwidth = WIDTH;
		int genheight = HEIGHT;
		// manipulate the width and height to specs
		
		BufferedImage resized = new BufferedImage(genwidth, genheight, BufferedImage.TYPE_INT_ARGB);
		Graphics g = resized.createGraphics();
		g.drawImage(panelImage, 0, 0, genwidth, genheight, null);
		g.dispose();
		
		//print
		File outputfile = null;
		if(n == null || n == "")
			outputfile = new File(location + "\\test.png");
		else
			outputfile = new File(n);
		pause = false;
		ImageIO.write(resized, "png", outputfile);

	}
	
	public static void reupdateImagesOverride() {
		/* basically, this will update any changes thus
		 * if a char change happened, it will change ONLY if you are
		 * using root as your media center
		 */ 
		for(int i = 0; i < layers.length; i++) {
			if(layers[i] != null && layers[i].getFile() != null && layers[i].getImage() != null) {
				try {
					if(!layers[i].isReversed()) {
						if(layers[i].getFile().getName().contains(".txt")) {
							layers[i].setImage(convertTextToImage(layers[i].getFile(), i));
							layers[i].setWidth(layers[i].getImage().getWidth());
							layers[i].setHeight(layers[i].getImage().getHeight());
						} else
							layers[i].setImage(ImageIO.read(layers[i].getFile()));
					} else {
						reverseImage(i);
					}
					try{
						layers[i].collectTimeStamp();
					} catch (Exception e2) {
						
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private void reupdateImages() {
		/* basically, this will update any changes thus
		 * if a char change happened, it will change ONLY if you are
		 * using root as your media center
		 */ 
		for(int i = 0; i < layers.length; i++) {
			if(layers[i] != null && layers[i].getFile() != null && layers[i].getImage() != null) {
				if(layers[i].getFile().lastModified() != layers[i].getTimeStamp()) {
					try {
						if(!layers[i].isReversed()) {
							if(layers[i].getFile().getName().contains(".txt")) {
								layers[i].setImage(convertTextToImage(layers[i].getFile(), i));
								layers[i].setWidth(layers[i].getImage().getWidth());
								layers[i].setHeight(layers[i].getImage().getHeight());
							} else
								layers[i].setImage(ImageIO.read(layers[i].getFile()));
						} else {
							reverseImage(i);
						}
						try{
							layers[i].collectTimeStamp();
						} catch (Exception e2) {
							
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	// really simple
	public static BufferedImage convertTextToImage(File f, int i) {
		try {
			int tmpy = 0;
			int type = Font.PLAIN;
			if(layers[i].isBold()) type = Font.BOLD;
			if(layers[i].isItalic()) type = type | Font.ITALIC;
			layers[i].setFont(te[i].getFont());
			layers[i].setAlignment(te[i].getAlignment());
			layers[i].setSize(te[i].getSize());
			layers[i].setColor(te[i].getColor()[0], te[i].getColor()[1], te[i].getColor()[2]);
			layers[i].setBold(te[i].isBold());
			layers[i].setItalic(te[i].isItalic());
			layers[i].setAdjusted(te[i].isAdjusted());
			layers[i].setWidth(te[i].getWidth());
			layers[i].setHeight(te[i].getHeight());
			
			// grab width of longest line, if it's multi-line
			BufferedImage tmp = new BufferedImage(layers[i].getWidth(), layers[i].getHeight(), BufferedImage.TYPE_INT_ARGB);
			BufferedImage actual;
			BufferedImage ghetto;
			Graphics2D gx = tmp.createGraphics();
			gx.setColor(new Color(layers[i].getColor()[0], layers[i].getColor()[1], layers[i].getColor()[2]));
			gx.setFont(new Font(layers[i].getFont(), type, layers[i].getSize()));
			String line = null;
			BufferedReader reader = new BufferedReader(new FileReader(f));
			int longest = 0;
			while((line = reader.readLine()) != null) {
				if(gx.getFontMetrics().stringWidth(line) > longest) {
					longest = gx.getFontMetrics().stringWidth(line);
				}
			}
			
			// check if the image/text is longer then designated width
			if(longest > layers[i].getWidth()) {
				if(layers[i].isAdjusted()) {
					int tmpnum = longest - layers[i].getWidth();
					int reduce = 0;
					while(tmpnum > sensitivity) {
						tmpnum %= sensitivity;
						reduce++;
					}
					for(int z = 0; z < overrideSizes.length; z++) {
						if(overrideSizes[z] > layers[i].getSize()) {
							if(z - reduce >= 0) {
								layers[i].setSize(overrideSizes[z - reduce]);
								if(layers[i].getAlignment().equals("right") || layers[i].getAlignment().equals("center")) {
									if(z - (reduce + 1) >= 0) layers[i].setSize(overrideSizes[z - (reduce + 1)]);
								}
							} else {
								layers[i].setSize(overrideSizes[0]);
							}
							break;
						}
					}
				}
				// draw to image, ignore controllers wish of width, we will do that later
				actual = new BufferedImage(longest, layers[i].getHeight(), BufferedImage.TYPE_INT_ARGB);
				gx.dispose();
				tmp = null;
				gx = actual.createGraphics();
				gx.setColor(new Color(te[i].getColor()[0], te[i].getColor()[1], te[i].getColor()[2]));
				gx.setFont(new Font(layers[i].getFont(), type, layers[i].getSize()));
				reader.close();
				reader = new BufferedReader(new FileReader(f));
				while((line = reader.readLine()) != null) {
					gx.drawString(line,0, (tmpy += gx.getFontMetrics().getHeight()));
				}
				reader.close();
				// now lets resize this
				ghetto = new BufferedImage(layers[i].getWidth(), layers[i].getHeight(), BufferedImage.TYPE_INT_ARGB);
				Graphics2D g2x = ghetto.createGraphics();
				g2x.drawImage(actual, 0, 0, layers[i].getWidth(), layers[i].getHeight(), null);
				g2x.dispose();
				return ghetto;
			} else {
				actual = new BufferedImage(layers[i].getWidth(), layers[i].getHeight(), BufferedImage.TYPE_INT_ARGB);
				gx.dispose();
				tmp = null;
				gx = actual.createGraphics();
				gx.setColor(new Color(te[i].getColor()[0], te[i].getColor()[1], te[i].getColor()[2]));
				gx.setFont(new Font(layers[i].getFont(), type, layers[i].getSize()));
				reader.close();
				reader = new BufferedReader(new FileReader(f));
				while((line = reader.readLine()) != null) {
					if(layers[i].getAlignment().equals("left"))
						gx.drawString(line,0, (tmpy += gx.getFontMetrics().getHeight()));
					else
					if(layers[i].getAlignment().equals("right"))
						gx.drawString(line, layers[i].getWidth() - gx.getFontMetrics().stringWidth(line), (tmpy += gx.getFontMetrics().getHeight()));
					else
					if(layers[i].getAlignment().equals("center"))
						gx.drawString(line, (layers[i].getWidth() / 2) - (gx.getFontMetrics().stringWidth(line) / 2), (tmpy += gx.getFontMetrics().getHeight()));
				}
				reader.close();
				return actual;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static void reverseImage(int i) {
		BufferedImage tmp = new BufferedImage(
				layers[i].getImage().getWidth(),
				layers[i].getImage().getHeight(),
				BufferedImage.TYPE_INT_ARGB);
		Graphics gx = tmp.createGraphics();
		try {
			if(layers[i].getFile().getName().contains(".txt")) {
				layers[i].setImage(convertTextToImage(layers[i].getFile(), i));
			} else
				layers[i].setImage(ImageIO.read(layers[i].getFile()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gx.drawImage(
				layers[i].getImage(), 
				layers[i].getImage().getWidth(), 
				0, 
				-layers[i].getImage().getWidth(), 
				layers[i].getImage().getHeight(), 
				null);
		gx.dispose();
		layers[i].setImage(tmp);
	}
	
	public void mouseClicked(MouseEvent arg0) {
	}

	public void mouseEntered(MouseEvent arg0) {
	}

	public void mouseExited(MouseEvent arg0) {
	}

	public void mousePressed(MouseEvent arg0) {
		// button is being held, scanning layers that match
		if(arg0.getButton() == MouseEvent.BUTTON3) {
			for(int i = 0; i < layers.length; i++) {
				if(layers[i].getFile() != null && layers[i].getImage() != null && layers[i].isSelected()) {
					if(layers[i].isReversed()) {
						layers[i].setReversed(false);
						reupdateImagesOverride();
						return;
					} else {
						layers[i].setReversed(true);
						reupdateImagesOverride();
						return;
					}
				}
			}
		}
		if(arg0.getButton() == MouseEvent.BUTTON1) {
			for(int i = 0; i < layers.length; i++) {
				if(layers[i].isSelected()) {
					layers[i].setX(arg0.getX());
					layers[i].setY(arg0.getY());
					posx.setValue(arg0.getX());
					posy.setValue(arg0.getY());
				}
			}
		}
	}

	public void mouseReleased(MouseEvent arg0) {
	}

	public void keyPressed(KeyEvent arg0) {
	}

	public void keyReleased(KeyEvent arg0) {
	}

	public void keyTyped(KeyEvent arg0) {
	}
	
	public void setOnTab(boolean b) { 
		onTab = b;
	}
	
	class Preview extends JPanel {
		
		@Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if(image != null)
            	g.drawImage(image, 0, 0, 1280, 720, null);
        }
		
	}
	
}
