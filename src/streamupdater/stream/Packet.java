package streamupdater.stream;

import java.awt.image.BufferedImage;

public class Packet {

	private String fileName = null;
	private int offset = 0;
	private int duration = 0;
	private String imageName = null;
	private BufferedImage thumbnail = null;
	
	public Packet(String fileName, 
				  int offset,
				  int duration,
				  String imageName,
				  BufferedImage thumbnail) {
		
		this.fileName = fileName;
		this.offset = offset;
		this.duration = duration;
		this.imageName = imageName;
		this.thumbnail = thumbnail;
		
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public int getOffset() {
		return offset;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public String getImageName() {
		return imageName;
	}
	
	public BufferedImage getThumbnail() {
		return thumbnail;
	}
	
}
