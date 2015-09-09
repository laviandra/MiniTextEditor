package textEditor;
/***********************************************************************
 * Module:  Copy.java
 * Author:  14009503
 * Purpose: Defines the Class Copy
 ***********************************************************************/

import java.util.*;

/**
 * The execution of the Copy Command  
 * A class present ever since version 1. It has been adjusted for 
 * versions 2 and 3 by adding the implementation of the redo, undo 
 * and shouldBeAddedToHistory methods. 
 */
public class Copy implements Command{
	private IEditorEngine editorEngine;
	
	private String textToCopy;
	private int startPos;

	public Copy(IEditorEngine edEngine){
		this.editorEngine = edEngine;
		Pair<Integer> p = edEngine.getSelection();
		textToCopy = edEngine.getText(p);
		startPos = p.first;
	}
	
	/**
	 *  executes the copy method present in editorEngine 
	 */
	@Override
	public void execute() {
		editorEngine.copy();
	}

	@Override
	public void undo() {
	}

	@Override
	public void redo() {
		editorEngine.copy(startPos, textToCopy);
	}
	
	public String toString(){
		return "Copy command: startPos="+startPos+", textToCopy=<" + textToCopy + ">";
	}

	/**
	 * A copy command should not be added to history since it cannot 
	 * visibly be redone/undone.
	 */
	@Override
	public boolean shouldBeAddedToHistory() {
		return false;
	}

}