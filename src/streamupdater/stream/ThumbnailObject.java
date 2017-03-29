package streamupdater.stream;

import java.awt.image.BufferedImage;
import java.io.File;

public class ThumbnailObject {

	private File file = null;
	private BufferedImage image = null;
	private int width = 0;
	private int height = 0;
	private int posx = 0;
	private int posy = 0;
	private boolean edited = false;
	private boolean selected = false;
	private boolean rev = false;
	
	// text stuff
	private int size = 32;
	private int[] color = { 255, 255, 255 };
	private boolean bold = false;
	private boolean italic = false;
	private boolean adjust = false;
	private String font = "Arial";
	private String alignment = "left";
	
	// file
	private long timestamp;
	
	public ThumbnailObject() {
		file = null;
		this.image = null;
		this.posx = -1;
		this.posy = -1;
		this.width = -1;
		this.height = -1;
		this.selected = false;
		edited = false;
		rev = false;
		timestamp = 0;
	}
	
	public void reset() {
		file = null;
		this.image = null;
		this.posx = -1;
		this.posy = -1;
		this.width = -1;
		this.height = -1;
		this.selected = false;
		edited = false;
		rev = false;
		timestamp = 0;
		size = 32;
		color = new int[] { 255, 255, 255 };
		bold = false;
		italic = false;
		adjust = false;
		font = "Arial";
		alignment = "left";
	}
	
	public void collectTimeStamp() {
		if(file != null) {
			timestamp = file.lastModified();
		}
	}
	
	public long getTimeStamp() {
		return timestamp;
	}
	
	public boolean isReversed() {
		return rev;
	}
	
	public void setReversed(boolean r) {
		rev = r;
	}
	
	public File getFile() {
		return file;
	}
	
	public void setFile(File f) {
		file = f;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	public int getX() {
		return posx;
	}
	
	public void setX(int x) {
		posx = x;
	}
	
	public int getY() {
		return posy;
	}
	
	public void setY(int y) {
		posy = y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
	public boolean isEdit() {
		return edited;
	}
	
	public void setEdit(boolean t) {
		edited = t;
	}
	
	public String getFont() {
		return font;
	}
	
	public String getAlignment() {
		return alignment;
	}
	
	public void setFont(String f) {
		font = f;
	}
	
	public void setAlignment(String n) {
		alignment = n;
	}
	
	public void setSize(int s) {
		size = s;
	}
	
	public int getSize() {
		return size;
	}
	
	public void setColor(int r, int g, int b) {
		color[0] = r;
		color[1] = g;
		color[2] = b;
	}
	
	public int[] getColor() {
		return color;
	}
	
	public void setBold(boolean b) {
		bold = b;
	}
	
	public void setItalic(boolean b) {
		italic = b;
	}
	
	public void setAdjusted(boolean b) {
		adjust = b;
	}
	
	public boolean isBold() {
		return bold;
	}
	
	public boolean isItalic() {
		return italic;
	}
	
	public boolean isAdjusted() {
		return adjust;
	}

	
}
