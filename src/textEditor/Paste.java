package textEditor;
/***********************************************************************
 * Module:  Paste.java
 * Author:  14009503
 * Purpose: Defines the Class Paste
 ***********************************************************************/

import java.util.*;

/**
 * The execution of a Paste command.
 * A class present ever since version 1. It has been adjusted for 
 * versions 2 and 3 by adding the implementation of the redo, undo 
 * and shouldBeAddedToHistory methods. 
 */ 
public class Paste implements Command {
	private IEditorEngine editorEngine;
	
	private String textToPaste;
	private int startPos;
	
	public Paste(IEditorEngine edEngine) {
		this.editorEngine = edEngine;
		textToPaste = edEngine.getClipboardText();
		startPos = edEngine.getCurrentPos();
	}
	
	@Override
	public void execute() {
		editorEngine.paste();
	}

	@Override
	public void undo() {
		editorEngine.reversePaste(startPos, textToPaste);
	}

	@Override
	public void redo() {
		editorEngine.paste(startPos, textToPaste);
	}
	
	public String toString(){
		return "Paste command: startPos=" + startPos + ", textToPaste<" + textToPaste + ">";
	}

	@Override
	public boolean shouldBeAddedToHistory() {
		return true;
	}
}