package streamupdater.gui.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Uploader extends JPanel {
	
	private JFileChooser jfc;
	
	private JPanel[] uploadPanel;
	private JLabel[] description;
	private JButton[] remove;
	private JButton[] upload;
	
	private JPanel columnpanel;
	private JPanel borderlayoutpanel;
	private JScrollPane scroll;
	
	private ArrayList<String> uploadingList = new ArrayList<String>();
	
	public Uploader() {
		
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		JButton clientObject = new JButton("JSON Client Object");
		clientObject.setFont(new Font("Arial Black", Font.BOLD, 18));
		clientObject.setBackground(Color.DARK_GRAY);
		clientObject.setBounds(10, 134, 300, 65);
		//add(clientObject);
		
		/*
		 * Setup render list objects
		 */
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(10, 10, 695, 800);
		add(scroll);
		
		borderlayoutpanel = new JPanel();
        scroll.setViewportView(borderlayoutpanel);
        borderlayoutpanel.setLayout(new BorderLayout(0, 0));
		
	}
	/*
	private void initRenderList() {
		
		int size = uploadingList.size();
		
		uploadPanel = new JPanel[size];
		description = new JLabel[size];
		remove = new JButton[size];
		upload = new JButton[size];

		if(columnpanel != null) borderlayoutpanel.remove(columnpanel);
		columnpanel = new JPanel();
        borderlayoutpanel.add(columnpanel, BorderLayout.NORTH);
        columnpanel.setLayout(new GridLayout(0, 1, 0, 1));
        columnpanel.setBackground(Color.gray);
        
        for(int i = 0; i < size; i++) {
        	// build panel
        	uploadPanel[i] = new JPanel();
        	uploadPanel[i].setPreferredSize(new Dimension(300,30));
        	uploadPanel[i].setBorder(BorderFactory.createLineBorder(Color.gray));
            columnpanel.add(uploadPanel[i]);
            uploadPanel[i].setLayout(null);
            // build remove
            remove[i] = new JButton("Remove");
            remove[i].setFont(new Font("Dialog", Font.PLAIN, 12));
            remove[i].setBounds(485, 5, 89, 23);
            remove[i].addActionListener(new Remove(i));
            uploadPanel[i].add(remove[i]);
            // build render
            upload[i] = new JButton("Upload");
            upload[i].setFont(new Font("Dialog", Font.PLAIN, 12));
            upload[i].setBounds(386, 5, 89, 23);
            upload[i].addActionListener(new Upload(i));
            uploadPanel[i].add(upload[i]);
            // build label
            description[i] = new JLabel("");
            description[i].setText(ro.getFileNames().get(i));
			description[i].setFont(new Font("Dialog", Font.PLAIN, 12));
			description[i].setBounds(10, 5, 366, 23);
			uploadPanel[i].add(description[i]);
        }	
        revalidate();
        
	}
*/
	// params will be for the file to upload
	public void addToUploadingList() {
		
	}

}
