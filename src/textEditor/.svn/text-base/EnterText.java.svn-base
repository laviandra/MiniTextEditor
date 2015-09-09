package textEditor;
/***********************************************************************
 * Module:  EnterText.java
 * Author:  14009503
 * Purpose: Defines the Class EnterText
 ***********************************************************************/

import java.util.*;

/**
 * The execution of the EnterText command. 
 *	A class present ever since version 1. It has been adjusted for 
 * versions 2 and 3 by adding the implementation of the redo, undo 
 * and shouldBeAddedToHistory methods. 
 */
public class EnterText implements Command {
	private IEditorEngine editorEngine;
	private String text;
	private int startPos;
	private int endPos;
	private String previousText;
	
	public EnterText(IEditorEngine edEngine, int _startPos, int _endPos, String _text) {
		editorEngine = edEngine;
		text = _text;
		startPos = _startPos;
		endPos = _endPos;
		previousText = editorEngine.getText();
	}
	
	/**
	 * Executes the enterText method present in the editorEngine 
	 */
	@Override
	public void execute() {
			editorEngine.enterText(startPos, endPos, text);
	}

	/**
	 * EnterText can be inversed using the reverseEnterText method,
	 * also present in the editorEngine
	 */
	@Override
	public void undo() {
		editorEngine.reverseEnterText(startPos, endPos, text);
	}
	
	@Override
	public void redo() {
		editorEngine.enterText(startPos, endPos, text);
	}
	
	public String toString(){
		return "EnterText command: startPos="+startPos+", endPos=" + endPos + ", text=<" + text + ">";
	}

	/**
	 * An EnterText command should be added to the history list since it can easily be undone/redone
	 * by deleting that certain text. 
	 */
	@Override
	public boolean shouldBeAddedToHistory() {
		return true;
	}
}