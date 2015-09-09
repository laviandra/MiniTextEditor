package textEditor;

/**
 * IEditorEngine. Built in V2 due to refactoring issues. 
 */
public interface IEditorEngine {
	
	public int getCurrentPos();

	public void setCurrentPos(int currentPos); 

	public String getText();
	
	public String getText(Pair<Integer> p);
		
	public Pair<Integer> getSelection();
	
	public void setSelection(Pair<Integer> p) throws WrongSelectionEndException, WrongSelectionStartException;
	
	public void copy(int startPos, String textToCopy);

	/**
	 *  overloading of the cut method. Used for redo 
	 */
	public void cut(int startPos, String textToCut);

	public void paste(int startPos, String textToPaste);
	
	public void copy();

	public void cut();

	public void paste();
	
	public void enterText(int startPos, int endPos, String text);
	
	public void resetEditor();

	public void reverseCut(int startPos, String textToCut);

	public void reversePaste(int startPos, String textToPaste);
	
	public void reverseEnterText(int startPos, int endPos, String text);

	public String getClipboardText();
	
	public void attach(Observer o);
	
	public void detach(Observer o);
	
	public boolean isTextSelected();
	
	
}
