package editorTestCases;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import textEditor.Command;
import textEditor.Copy;
import textEditor.Cut;
import textEditor.EditorEngine;
import textEditor.EnterText;
import textEditor.History;
import textEditor.IHistory;
import textEditor.MakeSelection;
import textEditor.Pair;
import textEditor.Paste;
import textEditor.Recording;

public class TestHistory {

	EditorEngine editorEngine;
	History history;
	
	@Before
	public void setUp() throws Exception {
		editorEngine = new EditorEngine();
		history = new History();
	}

	@Test
	public void testAddCommand() {
		String s = "azerty";
		Command c = new EnterText(editorEngine, 0, s.length(), s);
		history.addCommand(c);
		c.execute();
		/* MakeSelection should not go to History */
		c = new MakeSelection(editorEngine, new Pair<Integer>(1, 3));
		history.addCommand(c);
		c.execute();
		c = new Cut(editorEngine);
		history.addCommand(c);
		c.execute();
		c = new Paste(editorEngine);
		history.addCommand(c);
		c.execute();
		
		Assert.assertEquals(3, history.getCmdList().size());
	}
	
	@Test
	public void testClear() {
		String s = "azerty";
		Command c = new EnterText(editorEngine, 0, s.length(), s);
		history.addCommand(c);
		c.execute();
		/* MakeSelection should not go to History */
		c = new MakeSelection(editorEngine, new Pair<Integer>(1, 3));
		history.addCommand(c);
		c.execute();
		c = new Cut(editorEngine);
		history.addCommand(c);
		c.execute();
		c = new Paste(editorEngine);
		history.addCommand(c);
		c.execute();
		
		history.clear();
		Assert.assertEquals(0, history.getCmdList().size());
	}

	/**
	 * Execute a text insertion and an undo. See that the last letter 
	 * is missing from the text and that the list cursor's value has decreased
	 */
	@Test
	public void testUndo() {
		String s = "azerty";
		Command c = new EnterText(editorEngine, 0, s.length(), s);
		history.addCommand(c);
		c.execute();
			
		int cursorPrevPos = history.getCurrentPosInCmdlist();
		history.undoOperation();
		
		Assert.assertEquals("", editorEngine.getText());
		Assert.assertEquals(cursorPrevPos-1, history.getCurrentPosInCmdlist());
	}
	
	/**
	 * Execute a rather more sophisticated set of operations: 
	 * EnterText, Select, Copy, Paste, Paste, Undo, Insert, Redo. 
	 * The last redo should not affect the text in any way.
	 */
	@Test
	public void testCopyPastePasteUndoInsertRedo() {
		String s = "azerty";
		Command c = new EnterText(editorEngine, 0, s.length(), s);
		history.addCommand(c);
		c.execute();
		/* MakeSelection should not go to History */
		c = new MakeSelection(editorEngine, new Pair<Integer>(1, 3));
		history.addCommand(c);
		c.execute();
		c = new Copy(editorEngine);
		history.addCommand(c);
		c.execute();
		c = new Paste(editorEngine);
		history.addCommand(c);
		c.execute();
		c = new Paste(editorEngine);
		history.addCommand(c);
		c.execute();
		
		int cursorPrevPosUndo = history.getCurrentPosInCmdlist();
		history.undoOperation();
		
		c = new EnterText(editorEngine, s.length(), s.length()+s.length(), s);
		history.addCommand(c);
		c.execute();
		
		int cursorPrevPosRedo = history.getCurrentPosInCmdlist();
		history.redoOperation();
		
		/* -1 from undo and +1 from insert */
		Assert.assertEquals(cursorPrevPosUndo-1+1, history.getCurrentPosInCmdlist());
		/* redo should not modify the cursor's position */
		Assert.assertEquals(cursorPrevPosRedo, history.getCurrentPosInCmdlist());
		Assert.assertEquals(s+s+s.substring(1,3), editorEngine.getText());
	}
}
