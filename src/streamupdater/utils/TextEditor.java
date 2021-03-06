package streamupdater.utils;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import streamupdater.gui.components.ThumbnailEditor;

public class TextEditor implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean completed = false;
	private int size = 24;
	private int width = 200;
	private int height = size * 2;
	private int r = 255;
	private int g = 255;
	private int b = 255;
	private int[] color = { r, g, b };
	private boolean bold = false;
	private boolean italic = false;
	private boolean adjust = false;
	private String font = "Arial";
	private String alignment = "left";
	private boolean outline = false;
	private int outlineThickness = 1;
	
	private transient JFrame f;
	
	private transient JComboBox fontBox;
	private transient JSpinner sizes;
	private transient JSpinner red;
	private transient JSpinner blue;
	private transient JSpinner green;
	private transient JSpinner widths;
	private transient JSpinner heights;
	private transient JCheckBox bolds;
	private transient JCheckBox italics;
	private transient JCheckBox adj;
	private transient JComboBox align;
	private transient JCheckBox outlineText;
	private transient JSpinner outlineSize;
	
	public TextEditor(int s) {
		f = new JFrame("Text Editor - Layer " + s);
		f.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		f.setBounds(100, 100, 250, 400);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		f.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 214, 350);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblCustomText = new JLabel("Custom Text");
		lblCustomText.setHorizontalAlignment(SwingConstants.CENTER);
		lblCustomText.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblCustomText.setBounds(10, 11, 168, 30);
		panel.add(lblCustomText);
		
		JLabel lblFont = new JLabel("Font");
		lblFont.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFont.setBounds(10, 52, 46, 14);
		panel.add(lblFont);
		
		String[] systemFont = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		
		fontBox = new JComboBox(systemFont);
		fontBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fontBox.setBounds(66, 51, 126, 20);
		panel.add(fontBox);
		
		JLabel lblSize = new JLabel("Size");
		lblSize.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSize.setBounds(10, 77, 46, 14);
		panel.add(lblSize);
		
		sizes = new JSpinner();
		sizes.setValue(size);
		sizes.setBounds(66, 76, 72, 20);
		panel.add(sizes);
		
		JLabel lblColor = new JLabel("Color");
		lblColor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblColor.setBounds(10, 102, 46, 14);
		panel.add(lblColor);
		
		red = new JSpinner();
		red.setValue(r);
		red.setToolTipText("Red");
		red.setFont(new Font("Tahoma", Font.PLAIN, 14));
		red.setBounds(65, 101, 50, 20);
		panel.add(red);
		
		green = new JSpinner();
		green.setValue(g);
		green.setToolTipText("Green");
		green.setFont(new Font("Tahoma", Font.PLAIN, 14));
		green.setBounds(110, 101, 50, 20);
		panel.add(green);
		
		blue = new JSpinner();
		blue.setValue(b);
		blue.setToolTipText("Blue");
		blue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		blue.setBounds(155, 101, 50, 20);
		panel.add(blue);
		
		String[] a = { "left", "right", "center" };
		align = new JComboBox(a);
		align.setFont(new Font("Tahoma", Font.PLAIN, 14));
		align.setBounds(10, 280, 100, 20);
		panel.add(align);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(10, 320, 168, 23);
		panel.add(btnSave);
		
		bolds = new JCheckBox("Bold");
		bolds.setSelected(true);
		bolds.setFont(new Font("Tahoma", Font.PLAIN, 10));
		bolds.setBounds(10, 195, 50, 23);
		panel.add(bolds);
		
		italics = new JCheckBox("Italic");
		italics.setFont(new Font("Tahoma", Font.PLAIN, 10));
		italics.setBounds(10, 221, 97, 23);
		panel.add(italics);
		
		adj = new JCheckBox("Adjust Text");
		adj.setToolTipText("Attempts to recognize and adjust the size of font, beta.");
		adj.setSelected(true);
		adj.setFont(new Font("Tahoma", Font.PLAIN, 10));
		adj.setBounds(10, 247, 97, 23);
		panel.add(adj);

		outlineText = new JCheckBox("Outline");
		outlineText.setSelected(false);
		outlineText.setFont(new Font("Tahoma", Font.PLAIN, 10));
		outlineText.setBounds(90, 195, 60, 23);
		panel.add(outlineText);
		
		outlineSize = new JSpinner();
		outlineSize.setToolTipText("Outline Thickness");
		outlineSize.setEnabled(false);
		outlineSize.setValue(0);
		outlineSize.setBounds(150, 195, 50, 20);
		panel.add(outlineSize);
		
		JLabel lblNewLabel = new JLabel("Width & Height");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 127, 168, 30);
		panel.add(lblNewLabel);
		
		widths = new JSpinner();
		widths.setValue(width);
		widths.setBounds(10, 168, 75, 20);
		panel.add(widths);
		
		heights = new JSpinner();
		heights.setValue(height);
		heights.setBounds(103, 168, 75, 20);
		panel.add(heights);
		
		outlineText.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(outlineText.isSelected()) outlineSize.setEnabled(true);
				else
					outlineSize.setEnabled(false);
			}
			
		});
		
		btnSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				font = (String) fontBox.getSelectedItem();
				alignment = (String) align.getSelectedItem();
				size = (int) sizes.getValue();
				width = (int) widths.getValue();
				height = (int) heights.getValue();
				color[0] = (int) red.getValue();
				color[1] = (int) green.getValue();
				color[2] = (int) blue.getValue();
				bold = bolds.isSelected();
				italic = italics.isSelected();
				adjust = adj.isSelected();
				outline = outlineText.isSelected();
				outlineThickness = (int) outlineSize.getValue();
				ThumbnailEditor.reupdateImagesOverride();
				completed = true;
			}
			
		});
		
		f.setResizable(false);
		f.setVisible(true);
	}
	
	public void formatGui() {
		
		String[] systemFont = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		for(int i = 0; i < systemFont.length; i++)
			if(systemFont[i].equals(font)) {
				fontBox.setSelectedIndex(i);
				break;
			}
		
		sizes.setValue(getSize());
		red.setValue(color[0]);
		green.setValue(color[1]);
		blue.setValue(color[2]);
		widths.setValue(getWidth());
		heights.setValue(getHeight());
		bolds.setSelected(isBold());
		italics.setSelected(isItalic());
		adj.setSelected(isAdjusted());
		align.setSelectedItem(getAlignment());
		outlineSize.setValue(getOutlineSize());
		outlineText.setSelected(getOutline());
		
	}
	
	public void setOutlineSize(int s) {
		outlineThickness = s;
	}
	
	public void setOutline(boolean b) {
		outline = b;
	}
	
	public int getOutlineSize() {
		return outlineThickness;
	}
	
	public boolean getOutline() {
		return outline;
	}
	
	public void setFont(String f) {
		font = f;
	}
	
	public void setAdjusted(boolean b) {
		adjust = b;
	}
	
	public void setAlignment(String a) {
		alignment = a;
	}
	
	public void setSize(int s) {
		 size = s;
	}
	
	public void setWidth(int w) {
		width = w;
	}
	
	public void setHeight(int h) {
		height = h;
	}
	
	public void setColor(int [] rgb) {
		color = rgb;
	}
	
	public void setBold(boolean b) {
		bold = b;
	}
	
	public void setItalic(boolean b) {
		italic = b;
	}
	
	public void isAdjusted(boolean b) {
		adjust = b;
	}
	
	public boolean isComplete() {
		return completed;
	}
	
	public String getFont() {
		return font;
	}
	
	public String getAlignment() {
		return alignment;
	}
	
	public int getSize() {
		return size;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int[] getColor() {
		return color;
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
	
	public JFrame getFrame() {
		return f;
	}
	
}
