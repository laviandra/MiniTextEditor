package textEditor;
/***********************************************************************
 * Module:  MakeSelection.java
 * Author:  14009503
 * Purpose: Defines the Class MakeSelection
 ***********************************************************************/

import java.util.*;

/**
 * Execution of a MakeSelection command.
 * A class present ever since version 1. It has been adjusted for 
 * versions 2 and 3 by adding the implementation of the redo, undo 
 * and shouldBeAddedToHistory methods. 
 */
public class MakeSelection implements Command {
	private IEditorEngine editorEngine;
	private Pair<Integer> p;
	
	public MakeSelection(IEditorEngine _editorEngine, Pair<Integer> _p){
		editorEngine = _editorEngine;
		p = _p;
	}
	
	/**
	 * Executes the setSelection command from the editorEngine.
	 */
	@Override
	public void execute() {
		try {
			editorEngine.setSelection(p);
		}
		catch (WrongSelectionEndException | WrongSelectionStartException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void undo() {
		
	}

	@Override
	public void redo() {
		
	}

	/**
	 * A plain selection should not be added to history since undoing/redoing
	 *  it will have no visible effect.
	 */
	@Override
	public boolean shouldBeAddedToHistory() {
		return false;
	}
}