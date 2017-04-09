package streamupdater.stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import streamupdater.files.FileManager;
import streamupdater.utils.TextEditor;

/*
 * Class developed to save/load templates on the fly.
 * No more building from scratch templates.
 * 
 * Written by Kyle
 */
public class ThumbnailSave implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<ThumbnailObject> to = new ArrayList<ThumbnailObject>();
	private ArrayList<TextEditor> te = new ArrayList<TextEditor>();
	
	public ThumbnailSave() {}
	
	public void setListObjects(ArrayList<ThumbnailObject> thumbnailObject) {
		to = thumbnailObject;
	}
	
	public void setTextEditor(ArrayList<TextEditor> editor) {
		te = editor;
	}
	
	public ArrayList<ThumbnailObject> getThumbnailObject() {
		return to;
	}
	
	public ArrayList<TextEditor> getTextEditor() {
		return te;
	}
	
	public void save(ArrayList<ThumbnailObject> thumbnailObject, ArrayList<TextEditor> editor) {
		this.setListObjects(thumbnailObject);
		this.setTextEditor(editor);
		try {
			DateFormat df = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
			// Get the date today using Calendar object.
			Date today = Calendar.getInstance().getTime();        
			// Using DateFormat format method we can create a string 
			// representation of a date with the defined format.
			String reportDate = df.format(today).replace(" ", "-").replace(":", "-").replace("\\", "-");
			File f = new File(FileManager.getTemplatesDirectory() + "/" + reportDate + ".sobj");		// returns desktop location
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this);
			oos.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void load() {
		try {
			ThumbnailSave ts = null;
			File f = new Selection().selectedLoad();		// opens a jfilechooser to select the file/object being loaded
			if(f != null) {
				FileInputStream fis = new FileInputStream(f);
				ObjectInputStream ois = new ObjectInputStream(fis);
				ts = (ThumbnailSave) ois.readObject();
				ois.close();
			}
			if(ts == null) return;
			this.setListObjects(ts.getThumbnailObject());
			this.setTextEditor(ts.getTextEditor());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
