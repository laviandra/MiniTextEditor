package textEditor;

/***********************************************************************
 * Module:  EditorEngine.java
 * Author:  14009503
 * Purpose: Defines the Class EditorEngine
 ***********************************************************************/

import java.util.*;

/**
 * A class present in the Mini-Text Editor implementation ever since version 1.
 * It has been modified to support operations such as reverseCopy, reversePaste
 * for version 2. Its other methods have been intensively refactored as the 
 * project reached its maturity state. To ensure loosely coupling, an IEditorEngine
 * interface was developed.  
 */
public class EditorEngine extends Subject implements IEditorEngine {
	private Clipboard clipboard;
	private Selection selection;
	private Buffer buffer;

	private int currentPos;

	
	public EditorEngine() {
		clipboard = new Clipboard();
		selection = new Selection(); 
		buffer = new Buffer();
		currentPos = 0;
	}
	
	/**
	 * Brings all member variables to the initial state:
	 *  - caret position is set to 0
	 *  - the strings stored in clipboard and in the buffer are cleared
	 *  - clears any existing selection
	 */
	public void resetEditor(){
		clipboard.clearSavedText();
		currentPos = 0;
		buffer.setText("");
		selection.clear();
	}
	
	/**
	 * @return Returns the caret position.
	 */
	public int getCurrentPos() {
		return currentPos;
	}
	
	/**
	 * Sets the caret position to the value received as argument
	 * if the value is between the boundaries of the current text.
	 * Otherwise, the position is not changed.
	 */
	public void setCurrentPos(int currentPos) {
		if(currentPos >= 0 && currentPos <= getText().length()){
			if (Constants.DEBUG) LoggerSingleton.LOG_DEBUG("Setting current position to " + currentPos);
			this.currentPos = currentPos;
		} else {
			if (Constants.DEBUG) LoggerSingleton.LOG_DEBUG("Invalid value for current position: " + currentPos);
		}
	}

	/**
	 * @return Returns the text in the current buffer.
	 */
	public String getText() {
		return buffer.getText();
	}
	
	/**
	 * To be used only for testing purposes. The text should be manipulated 
	 * only through copy, cut, paste methods. 
	 * Sets the buffer text to the value received as argument.
	 */
	public void setText(String _text){
		if(Constants.DEBUG) LoggerSingleton.LOG_DEBUG("Setting text to <" + _text + ">");
		buffer.setText(_text);
		setCurrentPos(_text.length());
		notifyObservers();
	}
	
	/**
	 * Returns the string in the buffer between the positions received as parameter.
	 */
	public String getText(Pair<Integer> p) {
		return buffer.getText(p);
	}
	
	/**
	 * This method is public just for testing purposes.
	 * Inserts the string received as argument in the buffer content starting at the 
	 * position posToInsert.
	 * @param posToInsert Position where the string will be inserted.
	 * @param text The string to be added in the buffer.
	 * @throws NegativeStartException
	 * @throws ExceedLengthException 
	 */
	public void insertText(int posToInsert, String text) throws NegativeStartException, ExceedLengthException {
		if(posToInsert < 0){
			throw new NegativeStartException();
		}
		if(posToInsert > getText().length()) {
			throw new ExceedLengthException();
		}
		if(Constants.DEBUG) LoggerSingleton.LOG_DEBUG("Inserting <" + text + "> at " + posToInsert);
		buffer.insertText(posToInsert, text);
		setCurrentPos(posToInsert + text.length());
	}
	
	/**
	 * This method is public just for testing purposes.
	 * Removes from the buffer the string situated at the positions received as parameter.
	 */
	public void removeText(Pair<Integer> p) {
		if(Constants.DEBUG) LoggerSingleton.LOG_DEBUG("Removing text between " + p);

		buffer.removeText(p);
		setCurrentPos(p.first);
	}
	
	/**
	 * setSelection sets the current selection to the pair received as argument.
	 * If necessary, the values are swapped.
	 * @param Pair<Integer> p - the desired start and end positions of the selection
	 * @throws WrongSelectionEndException if the end position exceeds the length of the current text
	 * @throws WrongSelectionStartException if the start position is smaller than 0
	 */
	public void setSelection(Pair<Integer> p) throws WrongSelectionEndException, WrongSelectionStartException {
		
		swapIndexes(p);
		if (Constants.DEBUG) LoggerSingleton.LOG_DEBUG("Setting selection to " + p);

		if((Integer)p.first < 0) {
			throw new WrongSelectionStartException();
		}
		
		if((Integer)p.second > getText().length()) {
			throw new WrongSelectionEndException();
		}
		selection.setSelection(p);
	}
	
	/**
	 * @return Returns true if the selection is not empty. Returns false otherwise.
	 */
	public boolean isTextSelected(){
		Pair<Integer> p = getSelection();
		if(p.first != p.second && p.first >= 0 && p.second >= 0){
			return true;
		}
		return false;
	}

	
	/**
	 * If the user started selecting from the end of the string,
	 * the start and end positions of the selection will be reversed.
	 * swapIndexes puts the start and end position in the right order.
	 * @param p 
	 */
	private void swapIndexes(Pair<Integer> p) {
		if(p.first > p.second){
			Integer tmp = p.first;
			p.first = p.second;
			p.second = tmp;
		}
	}

	/**
	 * @return The start and end positions of the current selection.
	 */
	public Pair<Integer> getSelection() {
		return selection.getSelection();
	}

	/**
	 * @return The text saved in the clipboard.
	 */
	public String getClipboardText() {
		return clipboard.getSavedText();
	}

	/**
	 * paste Inserts the string textToPaste starting at the position received as parameter.
	 * Sets the current position at the end of the string inserted.
	 * @param int startPos The position where the text will be inserted
	 * @param String textToPaste The text that will be inserted.
	 */
	@Override
	public void paste(int startPos, String textToPaste) {
		if(Constants.DEBUG) LoggerSingleton.LOG_DEBUG("Pasting: "+ textToPaste);
		try {
			insertText(startPos, textToPaste);
		}
		catch (NegativeStartException | ExceedLengthException e) {
			e.printStackTrace();
		}
		notifyObservers();
	}

	/**
	 * paste Inserts the string in clipboard starting at the current position.
	 * Sets the current position at the end of the string inserted.
	 */
	@Override
	public void paste() {
		if(Constants.DEBUG) LoggerSingleton.LOG_DEBUG("Pasting: "+ clipboard.getSavedText());
		try {
			insertText(getCurrentPos(), clipboard.getSavedText());
		}
		catch (NegativeStartException e) {
			e.printStackTrace();
		}
		catch (ExceedLengthException e) {
			e.printStackTrace();
		}
		notifyObservers();
	}
	
	/**
	 * Executes paste's inverse command: removes the text that was previously inserted.
	 */
	@Override
	public void reversePaste(int startPos, String textToPaste) {
		/* Remove the text inserted */
		if(Constants.DEBUG) LoggerSingleton.LOG_DEBUG("Reversing paste");
		removeText(new Pair<Integer>(startPos, startPos+ textToPaste.length()));
		notifyObservers();
	}

	/**
	 * Removes textToCut.length() characters starting at position startPos. 
	 * Clears the current selection. Updates the text in the UI.
	 * @param int startPos The position where the cut begins
	 * @param String textToCut The string that will be saved in the clipboard
	 */
	@Override
	public void cut(int startPos, String textToCut) {
		if(Constants.DEBUG) LoggerSingleton.LOG_DEBUG("Cutting <" + textToCut + ">, from " + startPos);
		clipboard.setSavedText(textToCut);
		removeText(new Pair<Integer>(startPos, startPos + textToCut.length()));
		selection.clear();
		notifyObservers();
	}

	/**
	 * Removes the selected text.
	 * Clears the current selection. Updates the text in the UI.
	 */
	@Override
	public void cut() {
		String textToCut = getText(getSelection());
		if(Constants.DEBUG) LoggerSingleton.LOG_DEBUG("Cutting <" + textToCut + ">, from " + getSelection().first);
		clipboard.setSavedText(textToCut);
		removeText(getSelection());
		selection.clear();
		notifyObservers();
	}
	
	/**
	 * Executes cut's inverse command. Inserts the text that was removed.
	 */
	@Override
	public void reverseCut(int startPos, String textToCut) {
		try {
			insertText(startPos, textToCut);
		}
		catch (NegativeStartException e) {
			e.printStackTrace();
		}
		catch (ExceedLengthException e) {
			e.printStackTrace();
		}
		notifyObservers();
	}

	/**
	 * copy saves in the clipboard the text received as parameter.
	 * @param int startPos The start position of the text to copy
	 * @param String textToCopy	 The text that will be saved in the clipboard.
	 */	
	@Override
	public void copy(int startPos, String textToCopy) {
		if(Constants.DEBUG) LoggerSingleton.LOG_DEBUG("Setting the clipboard to <" + textToCopy + ">");
		clipboard.setSavedText(textToCopy);
	}
	

	/**
	 * copy saves in the clipboard the text currently selected.
	 */
	@Override
	public void copy() {
		String selectedText = getText(getSelection());
		clipboard.setSavedText(selectedText);
		if(Constants.DEBUG) LoggerSingleton.LOG_DEBUG("Setting the clipboard to <" + selectedText + ">");

	}
	
	/**
	 * Depending on the start and end positions, it adds or removes characters from the buffer.
	 * @param int startPos The start position of the text to enter
	 * @param int endPos The final position of the text to enter; useful for the inverse function
	 * @param String text The text that will be saved in the buffer.
	 */
	@Override
	public void enterText(int startPos, int endPos, String text) {
		try{
			if(startPos < endPos && text != null){
				insertText(startPos, text);
			} else {
				removeText(new Pair<Integer>(endPos, startPos));
			}
		} catch (NegativeStartException e){
			e.printStackTrace();
		}
		catch (ExceedLengthException e) {
			e.printStackTrace();
		}
		notifyObservers();	
	}

	/**
	 * Executes the inverse of an EnterText command.
	 */
	@Override
	public void reverseEnterText(int startPos, int endPos, String text) {
		try{
			if(startPos < endPos && text != null){
				removeText(new Pair<Integer>(startPos, endPos));
			} else {
				insertText(endPos, text);
			}
		} catch (NegativeStartException e){
			e.printStackTrace();
		}
		catch (ExceedLengthException e) {
			e.printStackTrace();
		}	
		notifyObservers();	
	}
	
}