package streamupdater.utils;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.AbstractAction;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import streamupdater.files.FileManager;

/*
 * Autocomplete goes out to http://stackabuse.com/example-adding-autocomplete-to-jtextfield/
 * Originally I had an autocomplete but wanted a better, more well rounded program to do so
 */
@SuppressWarnings("serial")
public class CommentatorSuggestionField extends JTextField implements DocumentListener {

	private static enum Mode {
		INSERT,
		COMPLETION
	};
	
	private static ArrayList<String> commentators;
	
	private Mode mode = Mode.INSERT;
	
	public CommentatorSuggestionField(String s) {
		
		setText(s);
		
		commentators = FileManager.getCommentators();
		
		Collections.sort(commentators);
		
	}
	
	public static void setCommentators(ArrayList<String> c) {
		commentators.clear();
		commentators.addAll(c);
	}

	@Override
	public void changedUpdate(DocumentEvent arg0) {}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		if(arg0.getLength() != 1) return;
		
		int pos = arg0.getOffset();
		String content = null;
		
		try {
			content = getText(0, pos + 1);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		
		int w;
		for(w = pos; w >= 0; w--) if(!Character.isLetter(content.charAt(w))) break;
		
		if(pos - w < 1) return;
		
		String prefix = content.substring(w + 1);
		int n = Collections.binarySearch(commentators, prefix);
		if(n < 0 && -n <= commentators.size()) {
			String match = commentators.get(-n - 1);
			if(match.startsWith(prefix)) {
				String completion = match.substring(pos - w);
				SwingUtilities.invokeLater(new CompletionTask(completion, pos + 1));
			}
		} else {
			mode = Mode.INSERT;
		}
		
	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {}
	
	public class CommitAction extends AbstractAction {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(mode == Mode.COMPLETION) {
				StringBuffer sb = new StringBuffer(getText());
				setText(sb.toString());
				mode = Mode.INSERT;
			} else {
				replaceSelection("\t");
			}
		}
		
	}
	
	private class CompletionTask implements Runnable {
		
		private String completion;
		private int position;
		
		CompletionTask(String completion, int position) {
			this.completion = completion;
			this.position = position;
		}

		public void run() {
			
			StringBuffer sb = new StringBuffer(getText());
			sb.insert(position, completion);
			setText(sb.toString());
			setCaretPosition(position + completion.length());
			moveCaretPosition(position);
			mode = Mode.COMPLETION;
			
		}
		
	}
	
}