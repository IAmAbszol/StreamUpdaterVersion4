package streamupdater.stream;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import streamupdater.uploader.VideoUploader;

/*
 * Class utilized to be a save for each game stream positions, etc
 */
public class RenderObject {
	
	private String renderPath = null;
	private ArrayList<Packet> packet;
	private ArrayList<Integer> startingPositions;
	private ArrayList<Integer> durations;
	private ArrayList<String> fileNames;
	private transient ArrayList<BufferedImage> thumbnails;
	private ArrayList<String> imageFile;
	
	public RenderObject(String url, 
			ArrayList<Integer> sp,
			ArrayList<Integer> dur, 
			ArrayList<String> fileName,
			ArrayList<BufferedImage> images,
			ArrayList<String> imageName) {
		renderPath = url;
		// loop through, add package to .get(i)
		startingPositions = sp;
		durations = dur;
		fileNames = fileName;
		thumbnails = images;
		imageFile = imageName;
	}
	
	public RenderObject(String url) {
		renderPath = url;
		startingPositions = new ArrayList<Integer>();
		durations = new ArrayList<Integer>();
		fileNames = new ArrayList<String>();
		thumbnails = new ArrayList<BufferedImage>();
		imageFile = new ArrayList<String>();
	}
	
	public void setPackage(String fileName, 
			String imageName,
			BufferedImage image) {
		getFileNames().add(fileName);
		getImageFileNames().add(imageName);
		getImages().add(image);
	}
	
	public ArrayList<Integer> getStartingPositions() {
		return startingPositions;
	}
	
	public ArrayList<Integer> getDurations() {
		return durations;
	}
	
	public ArrayList<String> getImageFileNames() {
		return imageFile;
	}
	
	public ArrayList<BufferedImage> getImages() {
		return thumbnails;
	}
	
	public ArrayList<String> getFileNames() {
		return fileNames;
	}
	
	public String getStreamURL() {
		return renderPath;
	}
	
	public void setStreamURL(String s) {
		renderPath = s;
	}
	
	public void setStartingPositions(ArrayList<Integer> i) {
		startingPositions = i;
	}
	
	public void setDurations(ArrayList<Integer> i) {
		durations = i;
	}
	
	public void setFileNames(ArrayList<String> i) {
		fileNames = i;
	}

	public void setThumbnails(ArrayList<BufferedImage> i) {
		thumbnails = i;
	}
	
	public void setImageFile(ArrayList<String> i) {
		imageFile = i;
	}
	
	private String findDesktop() {
		String user = System.getProperty("user.name");
		if(System.getProperty("os.name").contains("Windows")) {
			return "C:/Users/"+user+"/Desktop/";
		} else
			return System.getProperty("user.home") + "/Desktop/";
	}
	
	private String findFile(String name) {
		String user = System.getProperty("user.name");
		if(System.getProperty("os.name").contains("Windows")) {
			return "C:/Users/"+user+"/AppData/Roaming/"+ name;
		} else
			return System.getProperty("user.home") + "/" + name;
	}
	
	public void removePartObject(int pos) {
		
		getDurations().remove(pos);
		getFileNames().remove(pos);
		getImageFileNames().remove(pos);
		getImages().remove(pos);
		getStartingPositions().remove(pos);
		
	}
	
	public void render(VideoHandler video, int pos, int extra) {
		video.setDuration(getDurations().get(pos) + extra);
		video.setOffset(getStartingPositions().get(pos));
		video.setVideoInput(getStreamURL());
		video.setVideoOutput(getFileNames().get(pos));
		video.setImage(getImages().get(pos));
		video.setImageFileLocation(getImageFileNames().get(pos));
		video.encode();
	}
	
	public void upload(VideoUploader upload, String view, ArrayList<String> tags, String description, int pos) {
		upload.upload(getFileNames().get(pos), getImageFileNames().get(pos), view, tags, description);
	}
	
	public void uploadAll(VideoUploader upload, ArrayList<String> fileNames, ArrayList<String> imageNames, String view, ArrayList<String> tags, String description) {
		this.setFileNames(fileNames);
		this.setImageFile(imageNames);
		upload.uploadAll(getFileNames(), getImageFileNames(), view, tags, description);
	}
	
	public void renderAll(VideoHandler video, int extra) {
		Thread runningThread = new Thread(new Runnable() {
			public void run() {
				
				for(int i = 0; i < getDurations().size(); i++) {
					
					video.setDuration(getDurations().get(i) + extra);
					video.setOffset(getStartingPositions().get(i));
					video.setVideoInput(getStreamURL());
					video.setVideoOutput(getFileNames().get(i));
					video.setImage(getImages().get(i));
					video.setImageFileLocation(getImageFileNames().get(i));
					video.encode();
					if(video.getProcess() != null) {
						ProcMon.create(video.getProcess());
						while(!ProcMon.isComplete()) {
							System.out.print("");
						}
					}
					
				}
				JOptionPane.showMessageDialog(null, "Rendering Complete");
	
			}
		});

		runningThread.start();
		
	}
	
	public void renderImages(VideoHandler video) {
		Thread garbage = new Thread(new Runnable() {
			public void run() {
				for(int i = 0; i < getImages().size(); i++) {
					video.setImage(getImages().get(i));
					video.setImageFileLocation(getImageFileNames().get(i));
					video.createImages();
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		garbage.start();
	}

}
