package textEditor;

/***********************************************************************
 * Module:  Cut.java
 * Author:  14009503
 * Purpose: Defines the Class Cut
 ***********************************************************************/

import java.util.*;

/**
 * The execution of the Cut Command 
 * A class present ever since version 1. It has been adjusted for 
 * versions 2 and 3 by adding the implementation of the redo,
 * undo and shouldBeAddedToHistory methods.
 */
public class Cut implements Command {
	private IEditorEngine editorEngine;

	private String textToCut;
	private int startPos;

	public Cut(IEditorEngine edEngine) {
		this.editorEngine = edEngine;
		Pair<Integer> p = edEngine.getSelection();
		textToCut = edEngine.getText(p);
		startPos = p.first;
	}

	/**
	 * Executes the cut method present in the editorEngine
	 */
	@Override
	public void execute() {
		editorEngine.cut();

	}

	@Override
	public void undo() {
		editorEngine.reverseCut(startPos, textToCut);
	}

	@Override
	public void redo() {
		editorEngine.cut(startPos, textToCut);
	}

	public String toString() {
		return "Cut command: startPos=" + startPos + ", textToCut=<"
				+ textToCut + ">";
	}

	/**
	 * A cut command should be added to history since it cuts text from the
	 * buffer shouwn to the users. This means it can be undone/redone.
	 */
	@Override
	public boolean shouldBeAddedToHistory() {
		return true;
	}
}