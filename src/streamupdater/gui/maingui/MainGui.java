package streamupdater.gui.maingui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

import streamupdater.gui.components.StreamCapture;
import streamupdater.gui.components.StreamUpdater;
import streamupdater.gui.components.ThumbnailEditor;
import streamupdater.gui.components.TournamentEnlisting;
import streamupdater.gui.components.Uploader;
import streamupdater.uploader.VideoUploader;

@SuppressWarnings("serial")
public class MainGui extends JFrame {

	private JPanel contentPane;
	
	private TournamentEnlisting te;
	private StreamUpdater su;
	private StreamCapture sc;
	private ThumbnailEditor thumb;
	
	private static JTabbedPane tabbedPane;
	
	private static int taskBarSize = 0;

	public static void main(String[] args) {
		
		MainGui frame = new MainGui();
		frame.setVisible(true);
		
	}

	public MainGui() {
		
		try {
			
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			
		} catch (Exception e) { };

		setTitle("Stream Updater by Kyle Darling");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Insets scnMax = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
		taskBarSize = scnMax.bottom;
		setBounds(5, 5, 1920, 1080 - taskBarSize);
		setMaximizedBounds(new Rectangle(0,0,1920,1080-taskBarSize));
		setExtendedState(MAXIMIZED_BOTH);
		setUndecorated(true);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tabbedPane.setBackground(Color.DARK_GRAY);
		tabbedPane.setForeground(Color.white);
		tabbedPane.setBounds(10, 11, 1900, 1058 - taskBarSize);
		tabbedPane.setFont(new Font("Arial Black", Font.BOLD, 14));
		contentPane.add(tabbedPane);
		
		te = new TournamentEnlisting();
		tabbedPane.add("<html><body><table width='250'>Tournament Enlisting</table></body></html>", te);
		
		su = new StreamUpdater();
		tabbedPane.addTab("<html><body><table width='250'>Stream Updating</table></body></html>", su);
		
		sc = new StreamCapture();
		tabbedPane.addTab("<html><body><table width='250'>Stream Capture</table></body></html>", sc);
		
		thumb = new ThumbnailEditor();
		tabbedPane.addTab("<html><body><table width='250'>Thumbnail Editor</table></body></html>", thumb);
		
		tabbedPane.setUI(new BasicTabbedPaneUI() {
			   @Override
			   protected void installDefaults() {
			       super.installDefaults();
			       lightHighlight = Color.LIGHT_GRAY;
			       shadow = Color.DARK_GRAY;
			       darkShadow = Color.BLACK;
			   }
		});
		
		
		
		tabbedPane.getModel().addChangeListener(new ChangeListener() {
	         @Override
	         public void stateChanged(ChangeEvent e) {
	        	 if(tabbedPane.getSelectedIndex() == 3) {
	        		 thumb.setOnTab(true);
	        	 }
	         }
	      });

		
		addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(null, 
		            "Are you sure you want to close?", null,
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		            System.exit(0);
		        }
		    }
		});
		
		// lets fix some bounds, shall we? This is just for smaller resolutions
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		double widthMultiplier = dim.getWidth() / 1920;						// get multiplier width
		double heightMultiplier = dim.getHeight() / (1080 - taskBarSize);	// get multiplier height
		List<Component> list = getAllComponents(this);
		for(int i = 0; i < list.size(); i++) {
			Component c = list.get(i);
			list.get(i).setBounds((int) (c.getX() * widthMultiplier), 
					(int) (c.getY() * heightMultiplier), 
					(int) (c.getWidth() * widthMultiplier), 
					(int) (c.getHeight() * heightMultiplier)
			);
		}
	}
	
	private List<Component> getAllComponents(final Container c) {
	    Component[] comps = c.getComponents();
	    List<Component> compList = new ArrayList<Component>();
	    for (Component comp : comps) {
	        compList.add(comp);
	        if (comp instanceof Container)
	            compList.addAll(getAllComponents((Container) comp));
	    }
	    return compList;
	}
	
	public static int getTaskBarSize() {
		return taskBarSize;
	}
	
	public static JTabbedPane getPane() {
		return tabbedPane;
	}
	
}
