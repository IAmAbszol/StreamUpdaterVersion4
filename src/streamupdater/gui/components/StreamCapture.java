package streamupdater.gui.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import streamupdater.gui.maingui.MainGui;
import streamupdater.stream.RenderObject;
import streamupdater.stream.RenderSave;
import streamupdater.stream.VideoHandler;
import streamupdater.utils.CheckStreaming;
import streamupdater.utils.Commands;

@SuppressWarnings("serial")
public class StreamCapture extends JPanel {

	private Commands command;
	private static RenderObject ro;
	private static VideoHandler video;
	
	private Uploader upload;
	
	private JLabel streamCapture;
	private JButton begin;
	private JPanel columnpanel;
	private JPanel borderlayoutpanel;
	private JScrollPane scroll;
	
	private JButton capture;
	private JButton convert;
	private JButton saveObject;
	private JButton loadObject;
	private JButton changeUrl;
	private JButton renderAll;
	private JButton createThumbnails;
	private JButton addNewStream;

	//private JCheckBox autoUpload;
	
	// renderlist
	private JPanel[] renderPanel;
	private JLabel[] description;
	private JButton[] remove;
	private JButton[] render;
	
	private JFileChooser jfc;
	
	private JTextField videoName;

	private boolean capturing = false;
	private String streamURL = "";
	private long offset = 0;
	private long duration = 0;
	
	public StreamCapture() {
		
		video = new VideoHandler();
		command = new Commands();
		
		setBackground(Color.DARK_GRAY);
		setLayout(null);
		
		streamCapture = new JLabel("Stream Capture");
		streamCapture.setHorizontalAlignment(SwingConstants.CENTER);
		streamCapture.setFont(new Font("Arial Black", Font.BOLD, 32));
		streamCapture.setBackground(Color.DARK_GRAY);
		streamCapture.setBounds(465, 11, 695, 65);
		streamCapture.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		add(streamCapture);
		
		begin = new JButton("Begin");
		begin.setFont(new Font("Arial Black", Font.BOLD, 18));
		begin.setBackground(Color.DARK_GRAY);
		begin.setBounds(662, 477, 300, 65);
		add(begin);
		
		begin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				JFrame frame = new JFrame("Stream URL");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setBounds(100, 100, 450, 201);
				JPanel contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				frame.setContentPane(contentPane);
				contentPane.setLayout(null);
				
				JPanel panel = new JPanel();
				panel.setBounds(10, 11, 414, 140);
				contentPane.add(panel);
				panel.setLayout(null);
				
				JLabel lblNewLabel = new JLabel("Streaming File URL");
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
				lblNewLabel.setBounds(10, 11, 394, 30);
				panel.add(lblNewLabel);
				
				JTextField textField = new JTextField();
				textField.setFont(new Font("Dialog", Font.PLAIN, 12));
				textField.setBounds(10, 52, 295, 20);
				panel.add(textField);
				textField.setColumns(10);
				
				JButton browse = new JButton("Browse");
				browse.setFont(new Font("Dialog", Font.PLAIN, 12));
				browse.setBounds(315, 51, 89, 23);
				panel.add(browse);
				
				JButton launch = new JButton("Launch");
				launch.setFont(new Font("Dialog", Font.PLAIN, 12));
				launch.setBounds(315, 85, 89, 23);
				panel.add(launch);
				
				
				browse.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						jfc = new JFileChooser();
						jfc.setCurrentDirectory(new java.io.File("user.home"));
						jfc.setDialogTitle("Select your streaming file");
						jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
						if (jfc.showOpenDialog(browse) == JFileChooser.APPROVE_OPTION) {
							textField.setText(jfc.getSelectedFile().getAbsolutePath().replace("\\", "\\\\"));
						}
					}
					
				});
				
				launch.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						while(true) {
							if(!textField.getText().equals("")) {
								if(textField.getText().contains(".flv")) {
									streamURL = textField.getText();
									screen();
									break;
								} else {
									JOptionPane.showMessageDialog(null, "Invalid extension, please select a .flv selection");
									return;
								}
							} else {
								JOptionPane.showMessageDialog(null, "Please fill out the box");
								return;
							}
						}
						frame.dispose();
						ro = new RenderObject(streamURL);
						video.setVideoInput(streamURL);
						capture.setToolTipText("Capturing at " + streamURL);
						upload = new Uploader(ro, video);
						MainGui.getPane().addTab("<html><body><table width='250'>Uploading</table></body></html>", upload);
					}
					
				});

				frame.setResizable(false);
				frame.setVisible(true);
				
			}
			
		});
		
	}
	
	private void screen() {
		
		remove(begin);
		
		capture = new JButton("Capture");
		capture.setFont(new Font("Arial Black", Font.BOLD, 18));
		capture.setBackground(Color.DARK_GRAY);
		capture.setBounds(10, 134, 300, 65);
		add(capture);
		
		saveObject = new JButton("Save Object");
		saveObject.setFont(new Font("Arial Black", Font.BOLD, 18));
		saveObject.setBackground(Color.DARK_GRAY);
		saveObject.setBounds(10, 210, 300, 65);
		add(saveObject);
		
		loadObject = new JButton("Load Object");
		loadObject.setFont(new Font("Arial Black", Font.BOLD, 18));
		loadObject.setBackground(Color.DARK_GRAY);
		loadObject.setBounds(10, 286, 300, 65);
		add(loadObject);
		
		changeUrl = new JButton("Change Stream URL");
		changeUrl.setFont(new Font("Arial Black", Font.BOLD, 18));
		changeUrl.setBackground(Color.DARK_GRAY);
		changeUrl.setBounds(10, 362, 300, 65);
		add(changeUrl);
		
		/*
		 * Setup render list objects
		 */
		JScrollPane scroll = new JScrollPane();
		scroll.setBounds(465, 134, 695, 800);
		add(scroll);
		
		borderlayoutpanel = new JPanel();
        scroll.setViewportView(borderlayoutpanel);
        borderlayoutpanel.setLayout(new BorderLayout(0, 0));

		convert = new JButton("Convert To MP4");
		convert.setBackground(Color.DARK_GRAY);
		convert.setFont(new Font("Arial Black", Font.BOLD, 14));
		convert.setBounds(465, 945, 184, 63);
		add(convert);
		
		createThumbnails = new JButton("Create Thumbnails");
		createThumbnails.setFont(new Font("Arial Black", Font.BOLD, 14));
		createThumbnails.setBackground(Color.DARK_GRAY);
		createThumbnails.setBounds(659, 945, 307, 63);
		add(createThumbnails);
		
		renderAll = new JButton("Render All");
		renderAll.setFont(new Font("Arial Black", Font.BOLD, 14));
		renderAll.setBackground(Color.DARK_GRAY);
		renderAll.setBounds(976, 945, 184, 63);
		add(renderAll);
		
		videoName = new JTextField("MAINTITLE PLAYERONENAME vs PLAYERTWONAME - CURRENTROUND");
		videoName.setToolTipText(buildCommandList());
		videoName.setFont(new Font("Arial Black", Font.BOLD, 14));
		videoName.setBounds(10, 440, 300, 40);
		videoName.setHorizontalAlignment(SwingConstants.CENTER);
		videoName.setColumns(10);
		videoName.setEnabled(true);
		add(videoName);
		
		/*
		autoUpload = new JCheckBox();
		autoUpload.setText("Automatically Upload To YouTube");
		autoUpload.setBackground(Color.DARK_GRAY);
		autoUpload.setToolTipText("If the client_secret.json was recognized, no problems should occur.");
		autoUpload.setFont(new Font("Arial Black", Font.BOLD, 14));
		autoUpload.setBounds(10, 500, 350, 40);
		autoUpload.setSelected(false);
		add(autoUpload);
		*/
		
		JButton refresh = new JButton("Refresh");
		refresh.setToolTipText("Temporary till repaint is fixed");
		refresh.setFont(new Font("Arial Black", Font.BOLD, 18));
		refresh.setBackground(Color.DARK_GRAY);
		refresh.setBounds(10, 870, 300, 65);
		add(refresh);
		
		addNewStream = new JButton("Add New Stream");
		addNewStream.setToolTipText("Opens a new file to be recorded, previous is saved and cleared of the render list.");
		addNewStream.setFont(new Font("Arial Black", Font.BOLD, 18));
		addNewStream.setBackground(Color.DARK_GRAY);
		addNewStream.setBounds(10, 945, 300, 65);
		add(addNewStream);
		
		refresh.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				repaint();
			}
			
		});
		
		capture.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(!capturing) {
						capturing = true;
						capture.setText("Stop Capture");
						offset = video.getDuration();
					} else {
						capturing = false;
						capture.setText("Capture");
						duration = video.getDuration() - offset;
						ro.getDurations().add((int) duration);
						ro.getStartingPositions().add((int) offset);
						offset = 0;
						duration = 0;
						
						// setup files
						ThumbnailEditor.deselect();
						ro.setPackage(command.interpretString(videoName.getText()) + ".mp4", 
								command.interpretString(videoName.getText()) + ".png", 
								ThumbnailEditor.generateThumbnail());
						
						initRenderList();
						upload.initUploadList();
				        
				        columnpanel.repaint();
						
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			
		});
		
		saveObject.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					new Thread(new Runnable() {
						public void run() {
							if(ro != null) {
								RenderSave rs = new RenderSave();
								rs.save(ro.getStreamURL(), ro.getStartingPositions(), ro.getDurations(), ro.getFileNames(), ro.getImages(), ro.getImageFileNames());
							} else {
								JOptionPane.showMessageDialog(null, "Render Object Broken? I'll rebuild it");
								ro = new RenderObject(streamURL);
								RenderSave rs = new RenderSave();
								rs.save(ro.getStreamURL(), ro.getStartingPositions(), ro.getDurations(), ro.getFileNames(), ro.getImages(), ro.getImageFileNames());
						}
					}
					}).start();
			}
			
		});
		
		addNewStream.addActionListener(new NewStream());
		
		loadObject.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				RenderSave rs = new RenderSave();
				rs.load();
				if(rs.getStreamURL() == null) return;
				ro = new RenderObject(rs.getStreamURL());
				ro.setStartingPositions(rs.getStartingPositions());
				ro.setDurations(rs.getDurations());
				ro.setFileNames(rs.getFileNames());
				ro.setThumbnails(rs.getImages());
				ro.setImageFile(rs.getImageFileNames());
				
				initRenderList();
		        
		        columnpanel.repaint();
				
			}
			
		});
		
		createThumbnails.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(ro != null) ro.renderImages(video);
			}
			
		});
		
		renderAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(ro != null) {
					String tmp = ro.getStreamURL().replace("flv", "mp4");
					if(!new File(tmp).exists()) {
						JOptionPane.showMessageDialog(null, "MP4 not detected! Please click Convert to MP4 after stream has finished!");
						return;
					}
					CheckStreaming cs = new CheckStreaming(ro.getStreamURL());
					if(cs.isStreaming()) {
						JOptionPane.showMessageDialog(null, "Please stop file streaming before continuing!");
						return;
					}
					ro.renderAll(video, 0);
				}
			}
			
		});
		
		changeUrl.addActionListener(new ChangeStream());
		
		convert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				CheckStreaming cs = new CheckStreaming(ro.getStreamURL());
				if(cs.isStreaming()) {
					JOptionPane.showMessageDialog(null, "Please stop file streaming before continuing!");
					return;
				}
				video.setVideoInput(ro.getStreamURL());
				video.convertToMp4();
			}
			
		});
		
		repaint();
		
	}
	
	private void initRenderList() {
		
		int size = ro.getDurations().size();
		
		renderPanel = new JPanel[size];
		description = new JLabel[size];
		remove = new JButton[size];
		render = new JButton[size];

		if(columnpanel != null) borderlayoutpanel.remove(columnpanel);
		columnpanel = new JPanel();
        borderlayoutpanel.add(columnpanel, BorderLayout.NORTH);
        columnpanel.setLayout(new GridLayout(0, 1, 0, 1));
        columnpanel.setBackground(Color.gray);
        
        for(int i = 0; i < size; i++) {
        	// build panel
        	renderPanel[i] = new JPanel();
        	renderPanel[i].setPreferredSize(new Dimension(300,30));
        	renderPanel[i].setBorder(BorderFactory.createLineBorder(Color.gray));
            columnpanel.add(renderPanel[i]);
            renderPanel[i].setLayout(null);
            // build remove
            remove[i] = new JButton("Remove");
            remove[i].setFont(new Font("Dialog", Font.PLAIN, 12));
            remove[i].setBounds(485, 5, 89, 23);
            remove[i].addActionListener(new Remove(i));
            renderPanel[i].add(remove[i]);
            // build render
            render[i] = new JButton("Render");
            render[i].setFont(new Font("Dialog", Font.PLAIN, 12));
            render[i].setBounds(386, 5, 89, 23);
            render[i].addActionListener(new Render(i));
            renderPanel[i].add(render[i]);
            // build label
            description[i] = new JLabel("");
            description[i].setText(ro.getFileNames().get(i));
            description[i].setToolTipText(ro.getFileNames().get(i));
			description[i].setFont(new Font("Dialog", Font.PLAIN, 12));
			description[i].setBounds(10, 5, 366, 23);
			renderPanel[i].add(description[i]);
        }	
        revalidate();
        
	}
	
	private String buildCommandList() {
		
		String n = "<html>";
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(StreamCapture.class.getResourceAsStream("/Text/awesomecommands.txt")));
		String line = null;
		try {
			while((line = reader.readLine()) != null) {
				
				n = n + line + "<br>";
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return n + "</html>";
		
	}
	
	// this may cause issues with the re.removePartObject. Probably will
		private class Remove implements ActionListener {
			
			int pos = 0;
			
			public Remove(int pos) {
				this.pos = pos;
			}
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ro.removePartObject(pos);
				initRenderList();
				repaint();
				upload.initUploadList();
			}
			
		}
		
		private class Render implements ActionListener {
			
			int pos = 0;
			
			public Render(int i) {
				pos = i;
			}
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String tmp = ro.getStreamURL().replace("flv", "mp4");
				if(!new File(tmp).exists()) {
					JOptionPane.showMessageDialog(null, "MP4 not detected! Please click Convert To MP4 after stream has finished!");
					return;
				}
				ro.render(video, pos, 0);
				//ro.removePartObject(pos);
				//initRenderList();
				//repaint();
			}
			
		}
		
		private class NewStream implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame("Stream URL");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setBounds(100, 100, 450, 201);
				JPanel contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				frame.setContentPane(contentPane);
				contentPane.setLayout(null);
				
				JPanel panel = new JPanel();
				panel.setBounds(10, 11, 414, 140);
				contentPane.add(panel);
				panel.setLayout(null);
				
				JLabel lblNewLabel = new JLabel("Streaming File URL");
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
				lblNewLabel.setBounds(10, 11, 394, 30);
				panel.add(lblNewLabel);
				
				JTextField textField = new JTextField();
				textField.setFont(new Font("Dialog", Font.PLAIN, 12));
				textField.setBounds(10, 52, 295, 20);
				panel.add(textField);
				textField.setColumns(10);
				
				JButton browse = new JButton("Browse");
				browse.setFont(new Font("Dialog", Font.PLAIN, 12));
				browse.setBounds(315, 51, 89, 23);
				panel.add(browse);
				
				JButton launch = new JButton("Launch");
				launch.setFont(new Font("Dialog", Font.PLAIN, 12));
				launch.setBounds(315, 85, 89, 23);
				panel.add(launch);
				
				browse.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						jfc = new JFileChooser();
						jfc.setCurrentDirectory(new java.io.File("user.home"));
						jfc.setDialogTitle("Select your streaming file");
						jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
						if (jfc.showOpenDialog(browse) == JFileChooser.APPROVE_OPTION) {
							textField.setText(jfc.getSelectedFile().getAbsolutePath().replace("\\", "\\\\"));
						}
					}
					
				});
				
				launch.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						while(true) {
							if(!textField.getText().equals("")) {
								if(textField.getText().contains(".flv")) {
									streamURL = textField.getText();
									break;
								} else {
									JOptionPane.showMessageDialog(null, "Invalid extension, please select a .flv selection");
									return;
								}
							} else {
								JOptionPane.showMessageDialog(null, "Please fill out the box");
								return;
							}
						}
						frame.dispose();
						if(ro != null) {
							RenderSave rs = new RenderSave();
							rs.save(ro.getStreamURL(), ro.getStartingPositions(), ro.getDurations(), ro.getFileNames(), ro.getImages(), ro.getImageFileNames());
						} else {
							JOptionPane.showMessageDialog(null, "Render Object Broken? I'll rebuild it");
							ro = new RenderObject(streamURL);
							RenderSave rs = new RenderSave();
							rs.save(ro.getStreamURL(), ro.getStartingPositions(), ro.getDurations(), ro.getFileNames(), ro.getImages(), ro.getImageFileNames());
						}
						ro = new RenderObject(streamURL);
						video.setVideoInput(streamURL);
						capture.setToolTipText("Capturing at " + streamURL);
						initRenderList();
						repaint();
					}
					
				});

				frame.setResizable(false);
				frame.setVisible(true);
			}
			
		}
		
		private class ChangeStream implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame frame = new JFrame("Stream URL");
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setBounds(100, 100, 450, 201);
				JPanel contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				frame.setContentPane(contentPane);
				contentPane.setLayout(null);
				
				JPanel panel = new JPanel();
				panel.setBounds(10, 11, 414, 140);
				contentPane.add(panel);
				panel.setLayout(null);
				
				JLabel lblNewLabel = new JLabel("Streaming File URL");
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 14));
				lblNewLabel.setBounds(10, 11, 394, 30);
				panel.add(lblNewLabel);
				
				JTextField textField = new JTextField();
				textField.setFont(new Font("Dialog", Font.PLAIN, 12));
				textField.setBounds(10, 52, 295, 20);
				panel.add(textField);
				textField.setColumns(10);
				
				JButton browse = new JButton("Browse");
				browse.setFont(new Font("Dialog", Font.PLAIN, 12));
				browse.setBounds(315, 51, 89, 23);
				panel.add(browse);
				
				JButton launch = new JButton("Launch");
				launch.setFont(new Font("Dialog", Font.PLAIN, 12));
				launch.setBounds(315, 85, 89, 23);
				panel.add(launch);
				
				browse.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						jfc = new JFileChooser();
						jfc.setCurrentDirectory(new java.io.File("user.home"));
						jfc.setDialogTitle("Select your streaming file");
						jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
						if (jfc.showOpenDialog(browse) == JFileChooser.APPROVE_OPTION) {
							textField.setText(jfc.getSelectedFile().getAbsolutePath().replace("\\", "\\\\"));
						}
					}
					
				});
				
				launch.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						while(true) {
							if(!textField.getText().equals("")) {
								if(textField.getText().contains(".flv")) {
									streamURL = textField.getText();
									break;
								} else {
									JOptionPane.showMessageDialog(null, "Invalid extension, please select a .flv selection");
									return;
								}
							} else {
								JOptionPane.showMessageDialog(null, "Please fill out the box");
								return;
							}
						}
						frame.dispose();
						ro.setStreamURL(streamURL);
						video.setVideoInput(streamURL);
						capture.setToolTipText("Capturing at " + streamURL);
					}
					
				});

				frame.setResizable(false);
				frame.setVisible(true);
			}
			
		}
		
		public static RenderObject grabObject() {
			return ro;
		}
		
		public static VideoHandler grabVideo() {
			return video;
		}
		
}
