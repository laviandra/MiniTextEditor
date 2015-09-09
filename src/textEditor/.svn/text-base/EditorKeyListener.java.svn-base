package textEditor;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JEditorPane;
import javax.swing.JTextArea;

/**
 * KeyListener for the text area in the GUI 
 * A class present in the implementation ever since version 1. It has been slightly refactored
 * during the development of the second and third version.
 * 
 */
public class EditorKeyListener implements KeyListener {
	IEditorEngine editorEngine;
	IHistory history;
	String oldText;
	
	public EditorKeyListener(IEditorEngine _editorEngine, IHistory _history){
		editorEngine = _editorEngine;
		history =_history;
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {	
		JEditorPane ta = (JEditorPane) arg0.getSource();
		oldText = ta.getText();
		if(arg0.getKeyCode() == KeyEvent.VK_ESCAPE){
			ta.getHighlighter().removeAllHighlights();
		}

	}
	
	/**
	 * Overriding the keyReleased method to get the text introduced by the user.
	 * This will trigger an EnterText command. 
	 */
	@Override
	public void keyReleased(KeyEvent arg0) {
		if(Constants.DEBUG) LoggerSingleton.LOG_DEBUG("I pressed a key");
		
		int previousPos = editorEngine.getCurrentPos();
		String previousText = editorEngine.getText();
		
		JEditorPane ta = (JEditorPane) arg0.getSource();
		String currentText = ta.getText();
	
		int newPos = previousPos + currentText.length() - previousText.length();
		
		String insertedText = null;
		if(newPos > previousPos){
			insertedText = currentText.substring(previousPos, newPos);
		}

		if(currentText.compareTo(previousText) != 0) {
			Command c;

			c = new EnterText(editorEngine, previousPos, newPos, insertedText);
			c.execute();
			history.addCommand(c);
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

}
