package streamupdater.gui.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import streamupdater.files.FileManager;
import streamupdater.stream.RenderObject;
import streamupdater.stream.VideoHandler;
import streamupdater.uploader.VideoUploader;
import streamupdater.utils.CheckStreaming;

public class Uploader extends JPanel {
	
	private VideoUploader videoUploader;
	private RenderObject ro;
	private VideoHandler video;
	
	private JFileChooser jfc;
	
	private JPanel[] uploadPanel;
	private JLabel[] description;
	private JButton[] remove;
	private JButton[] upload;
	
	private JPanel columnpanel;
	private JPanel borderlayoutpanel;
	private JScrollPane scroll;
	
	private JComboBox viewing;
	private JTextField tags;
	private JTextArea videoDescription;

	private ArrayList<String> fn;
	private ArrayList<String> in;
	
	public Uploader(RenderObject ro, VideoHandler video) {
		
		videoUploader = new VideoUploader();
		fn = new ArrayList<String>();
		in = new ArrayList<String>();
		
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		/*
		 * Setup render list objects
		 */
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(10, 10, 695, 990);
		add(scroll);
		
		borderlayoutpanel = new JPanel();
        scroll.setViewportView(borderlayoutpanel);
        borderlayoutpanel.setLayout(new BorderLayout(0, 0));
        
        // now we want a public, tags, and description fields
        viewing = new JComboBox();
        viewing.setFont(new Font("Arial Black", Font.BOLD, 14));
        viewing.setBounds(710, 10, 150, 40);
        viewing.setBackground(Color.DARK_GRAY);
        viewing.addItem("Public");
        viewing.addItem("Private");
        viewing.addItem("Unlisted");
        add(viewing);
        
        JButton uploadAll = new JButton("Upload All");
        uploadAll.setFont(new Font("Arial Black", Font.BOLD, 14));
        uploadAll.setBounds(870, 10, 150, 40);
        uploadAll.setBackground(Color.DARK_GRAY);
        add(uploadAll);
        
        tags = new JTextField();
        tags.setFont(new Font("Arial Black", Font.BOLD, 14));
        tags.setToolTipText("Separate by , --> hello, good-bye");
        tags.setBounds(710, 60, 910, 40);
        add(tags);
        
        videoDescription = new JTextArea();
        videoDescription.setFont(new Font("Arial Black", Font.BOLD, 14));
        videoDescription.setToolTipText("Description for your video");
        videoDescription.setBounds(710, 110, 910, 890);
        add(videoDescription);
        
        this.ro = ro;
        this.video = video;
        
        uploadAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String tmp = tags.getText();
				String[] insert = tags.getText().split(",");
				ArrayList<String> tagArray = new ArrayList<String>(Arrays.asList(insert));
				ro.uploadAll(videoUploader, fn, in, (String) viewing.getSelectedItem(), tagArray, videoDescription.getText());
			}
			
        });
        
        initUploadList();
        
	}
	
	public void setFileNames(ArrayList<String> fn) {
		this.fn.clear();
		this.fn.addAll(fn);
	}
	
	public void setImageNames(ArrayList<String> in) {
		this.in.clear();
		this.in.addAll(in);
	}
	
	public void initUploadList() {
		
		int size = ro.getDurations().size();
		this.setFileNames(ro.getFileNames());
		this.setImageNames(ro.getImageFileNames());
		
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
            description[i].setToolTipText(ro.getFileNames().get(i));
			description[i].setFont(new Font("Dialog", Font.PLAIN, 12));
			description[i].setBounds(10, 5, 366, 23);
			uploadPanel[i].add(description[i]);
        }	
        revalidate();
        
	}
	
	public void reconnect(RenderObject ro, VideoHandler video) {
		 this.ro = ro;
	     this.video = video;
	}

	private class Remove implements ActionListener {
		
		int pos = 0;
		
		public Remove(int pos) {
			this.pos = pos;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			ro.removePartObject(pos);
			initUploadList();
			repaint();
		}
		
	}
	
	private class Upload implements ActionListener {
		
		int pos = 0;
		
		public Upload(int i) {
			pos = i;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			String tmp = FileManager.getMediaDirectory() + "/" + ro.getFileNames().get(pos);
			System.out.println(tmp);
			if(!new File(tmp).exists()) {
				JOptionPane.showMessageDialog(null, "Video not detected! Please render the video after stream has finished!");
				return;
			}
			CheckStreaming cs = new CheckStreaming(tmp);
			if(cs.isStreaming()) {
				JOptionPane.showMessageDialog(null, "Please wait for file rendering to complete, this may take some time.");
				return;
			}
			// we need to convert tags to an arraylist
			tmp = tags.getText();
			String[] insert = tags.getText().split(",");
			ArrayList<String> tagArray = new ArrayList<String>(Arrays.asList(insert));
			ro.upload(videoUploader, (String) viewing.getSelectedItem(), tagArray, videoDescription.getText(), pos);
			/*
			ro.removePartObject(pos);
			initUploadList();
			repaint();
			*/
		}
		
	}

}
