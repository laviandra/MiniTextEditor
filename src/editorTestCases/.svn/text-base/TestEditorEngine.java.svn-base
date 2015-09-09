package editorTestCases;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import textEditor.EditorEngine;
import textEditor.ExceedLengthException;
import textEditor.Gui;
import textEditor.History;
import textEditor.IHistory;
import textEditor.NegativeStartException;
import textEditor.Pair;
import textEditor.Recording;
import textEditor.UserInterface;
import textEditor.WrongSelectionEndException;
import textEditor.WrongSelectionStartException;

public class TestEditorEngine {

	EditorEngine editorEngine;
	
	@Before
	public void setUp() throws Exception {
		editorEngine = new EditorEngine();

	}

	@Test
	public void testInitialPosition() {
		Assert.assertEquals(0, editorEngine.getCurrentPos());
	}
	
	@Test
	public void testSetCurrentPos() throws NegativeStartException, ExceedLengthException {
		editorEngine.insertText(0, "1234");
		editorEngine.setCurrentPos(3);
		Assert.assertEquals(3, editorEngine.getCurrentPos());
	}

	@Test
	public void testInitialText() {
		Assert.assertEquals("", editorEngine.getText());
	}
	
	@Test
	public void testGetText() throws NegativeStartException, ExceedLengthException {
		editorEngine.insertText(0, "azerty");
		Assert.assertEquals("azerty", editorEngine.getText());
	}
	
	@Test
	public void testGetMiddleText() throws NegativeStartException, ExceedLengthException {
		editorEngine.insertText(0, "azerty");
		editorEngine.insertText(2, "ttt");
		Assert.assertEquals("azttterty", editorEngine.getText());
	}

	@Test
	public void testGetFinText() throws NegativeStartException, ExceedLengthException {
		editorEngine.insertText(0, "azerty");
		editorEngine.insertText(editorEngine.getText().length(), "ttt");
		Assert.assertEquals(editorEngine.getText(), "azertyttt");
	}
	
	@Test
	public void testGetTextPairOfInteger() throws NegativeStartException, ExceedLengthException {
		String s = "azerty";
		editorEngine.insertText(0, s);
		Assert.assertEquals(editorEngine.getText(new Pair<Integer>(2, 4)), s.substring(2, 4));
	}

	@Test
	public void testRemoveText() throws NegativeStartException, ExceedLengthException {
		String s = "azerty";
		editorEngine.insertText(0, s);
		editorEngine.removeText(new Pair<Integer>(2, 4));
		Assert.assertEquals(editorEngine.getText(), "azty");
	}
	
	@Test
	public void testRemoveAllText() throws NegativeStartException, ExceedLengthException {
		String s = "azerty";
		editorEngine.insertText(0, s);
		editorEngine.removeText(new Pair<Integer>(0, s.length()));
		Assert.assertEquals(editorEngine.getText(), "");
	}

	@Test
	public void testInitialSelection() {
		Assert.assertEquals(editorEngine.getSelection(), new Pair<Integer>(0, -1));
	}

	@Test(expected = textEditor.NegativeStartException.class)
	public void testSelectionStartNotNegative() throws NegativeStartException, ExceedLengthException{
		editorEngine.insertText(-2, "qq");
	}
	
	@Test (expected = textEditor.ExceedLengthException.class)
	public void testSelectionStartNotAfterEnd() throws NegativeStartException, ExceedLengthException {
		editorEngine.insertText(3, "qq");
	}
		
	@Test (expected = textEditor.WrongSelectionEndException.class)
	public void testSelectionEndNotAfterBufferEnd() throws NegativeStartException, WrongSelectionEndException, WrongSelectionStartException {	
		editorEngine.setSelection(new Pair<Integer>(0, editorEngine.getText().length()+5));
	}
	
	@Test (expected = textEditor.WrongSelectionStartException.class)
	public void testSelectionStartNotBeforeBufferStart() throws NegativeStartException, WrongSelectionEndException, WrongSelectionStartException {	
		editorEngine.setSelection(new Pair<Integer>(-5, editorEngine.getText().length()-1));
	}

	@Test
	public void testInitialClipboardText() {
		Assert.assertEquals(editorEngine.getClipboardText(), "");
	}
	
	@Test
	public void testGetSelection() throws WrongSelectionEndException, WrongSelectionStartException, NegativeStartException, ExceedLengthException {
		editorEngine.insertText(0, "azerty");
		Pair<Integer> p = new Pair<Integer>(1, 3);
		editorEngine.setSelection(p);
		Assert.assertEquals(editorEngine.getSelection(), p);
	}

	@Test
	public void testCopy() throws NegativeStartException, WrongSelectionEndException, WrongSelectionStartException, ExceedLengthException {
		String s = "azerty";
		editorEngine.insertText(0, s);
		/* select the text to copy */
		Pair<Integer> p = new Pair<Integer>(1, 3);
		editorEngine.setSelection(p);
		/* execute a copy operation */
		editorEngine.copy();
		/* check if the copied text exists in the clipboard */
		Assert.assertEquals(editorEngine.getClipboardText(), s.substring(1, 3));		
	}
	
	@Test 
	public void testCopyWithEmptySelection() throws NegativeStartException, WrongSelectionEndException, WrongSelectionStartException, ExceedLengthException {
		String s = "azerty";
		editorEngine.insertText(0, s);
		/* perform an empty selection */
		Pair<Integer> p = new Pair<Integer>(0, 0);
		editorEngine.setSelection(p);
		/* execute a copy operation */
		editorEngine.copy();
		/* execute paste */
		editorEngine.paste();
		Assert.assertEquals(editorEngine.getClipboardText(), editorEngine.getText().substring(0, editorEngine.getClipboardText().length()));
	}
	
	@Test
	public void testCut() throws NegativeStartException, WrongSelectionEndException, WrongSelectionStartException, ExceedLengthException {
		String s = "azerty";
		editorEngine.insertText(0, s);
		/* select the text to cut */
		Pair<Integer> p = new Pair<Integer>(1, 3);
		editorEngine.setSelection(p);
		/* execute a cut operation */
		editorEngine.cut();
		/* check if the cut text exists in the clipboard */
		Assert.assertEquals(editorEngine.getClipboardText(), s.substring(1, 3));	
		/* check that the cut text no longer exists in the text shown to the user */
		Assert.assertFalse(editorEngine.getText(new Pair<Integer>(1, 3)).equals(editorEngine.getClipboardText()));
	}
	
	@Test
	public void testCopyPaste() throws NegativeStartException, WrongSelectionEndException, WrongSelectionStartException, ExceedLengthException {
		/* execute a copy followed by a paste and check the text displayed */
		String s = "azerty";
		editorEngine.insertText(0, s);
		/* select the text to copy */
		Pair<Integer> p = new Pair<Integer>(1, 3);
		editorEngine.setSelection(p);
		/* execute a copy operation */
		editorEngine.copy();
		/* execute paste */
		editorEngine.paste();
		/* see that the new text was inserted at the selection point */
		/* TODO modify the  start position of the selection */
		Assert.assertEquals(editorEngine.getClipboardText(), editorEngine.getText().substring(s.length(), s.length() + 2));
	}
	
	/** 
	 * select and then paste -> it should replace the text with the contents of the buffer
	 * @throws ExceedLengthException 
	 * @throws NegativeStartException 
	 * @throws WrongSelectionStartException 
	 * @throws WrongSelectionEndException 
	 */
	@Test
	public void testCopySelectPaste() throws NegativeStartException, ExceedLengthException, WrongSelectionEndException, WrongSelectionStartException {
		String s = "azerty";
		editorEngine.insertText(0, s);
		/* select the text to copy */
		Pair<Integer> p = new Pair<Integer>(1, 3);
		editorEngine.setSelection(p);
		/* execute a copy operation */
		editorEngine.copy();
		/* select another text */
		p = new Pair<Integer>(3, 4);
		editorEngine.setSelection(p);
		/* execute paste */
		editorEngine.paste();
		Assert.assertEquals(editorEngine.getClipboardText(), 
				editorEngine.getText().substring(s.length(), s.length()+2));
	}
	
	
	@Test
	public void testCopyCut() throws NegativeStartException, WrongSelectionEndException, WrongSelectionStartException, ExceedLengthException {
		/* execute a copy followed by a cut and check the text in the clipboard */
		String s = "azerty";
		editorEngine.insertText(0, s);
		/* select the text to copy */
		Pair<Integer> p = new Pair<Integer>(1, 3);
		editorEngine.setSelection(p);
		/* execute a copy operation */
		editorEngine.copy();
		/* execute a cut operation */
		editorEngine.cut();
		Assert.assertEquals(editorEngine.getClipboardText(), s.substring(1, 3));
	}
	
	@Test
	public void testCopyCopy() throws NegativeStartException, WrongSelectionEndException, WrongSelectionStartException, ExceedLengthException {
		/* execute a copy followed by another copy and check the text in the clipboard */
		String s = "azerty";
		editorEngine.insertText(0, s);
		/* select the text to copy */
		Pair<Integer> p = new Pair<Integer>(1, 3);
		editorEngine.setSelection(p);
		/* execute a copy operation */
		editorEngine.copy();
		/* execute a copy operation */
		editorEngine.copy();
		Assert.assertEquals(editorEngine.getClipboardText(), editorEngine.getText().substring(1, 3));
	}

	@Test
	public void testPaste() throws NegativeStartException, WrongSelectionEndException, WrongSelectionStartException, ExceedLengthException {
		/* execute a paste without any previous copy or cut and check the text in the clipboard and the editor engine */
		String s = "azerty";
		editorEngine.insertText(0, s);

		editorEngine.paste();
		Assert.assertEquals(editorEngine.getClipboardText(), "");
		Assert.assertEquals(editorEngine.getText(), s);
	}
	
	@Test
	public void testCopyPasteUndo() throws NegativeStartException, WrongSelectionEndException, WrongSelectionStartException, ExceedLengthException {
		String s = "azerty";
		editorEngine.insertText(0, s);
		/* select the text to copy */
		Pair<Integer> p = new Pair<Integer>(1, 3);
		editorEngine.setSelection(p);
		/* execute a copy operation */
		editorEngine.copy();
		/* execute paste */
		editorEngine.paste();
		editorEngine.reversePaste(s.length(), s.substring(1, 3));
		Assert.assertEquals(s, editorEngine.getText());
	}
	
	@Test
	public void testCopyPastePasteUndoRedo() throws NegativeStartException, WrongSelectionEndException, WrongSelectionStartException, ExceedLengthException {
		String s = "azerty";
		editorEngine.insertText(0, s);
		/* select the text to copy */
		Pair<Integer> p = new Pair<Integer>(1, 3);
		editorEngine.setSelection(p);
		/* execute a copy operation */
		editorEngine.copy();
		/* execute paste */
		editorEngine.paste();
		/* execute another paste */
		editorEngine.paste();
		/* execute undo */
		editorEngine.reversePaste(s.length()+2, s.substring(1, 3));
		/* execute redo */
		editorEngine.paste(s.length(), s.substring(1,3));
		Assert.assertEquals(s+s.substring(1,3)+s.substring(1,3), editorEngine.getText());
	}
	
	/**
	 * @throws NegativeStartException
	 * @throws WrongSelectionEndException
	 * @throws WrongSelectionStartException
	 * @throws ExceedLengthException
	 */
	@Test
	public void testCopyPastePasteUndoUndoRedoInsertRedo() throws NegativeStartException, WrongSelectionEndException, WrongSelectionStartException, ExceedLengthException {
		String s = "azerty";
		editorEngine.insertText(0, s);
		/* select the text to copy */
		Pair<Integer> p = new Pair<Integer>(1, 3);
		editorEngine.setSelection(p);
		/* execute a copy operation */
		editorEngine.copy();
		/* execute paste */
		editorEngine.paste();
		/* execute another paste */
		editorEngine.paste();
		/* execute undo */
		editorEngine.reversePaste(s.length()+2, s.substring(1, 3));
		/* execute another undo */
		editorEngine.reversePaste(s.length(), s.substring(1, 3));
		/* execute redo */
		editorEngine.paste(s.length(), s.substring(1,3));
		
		editorEngine.insertText(s.length(), s);
		/* execute redo */
		editorEngine.insertText(s.length(), s); 

		Assert.assertEquals(s+s+s+s.substring(1,3), editorEngine.getText());
	}
	
	@Test
	public void testCutPasteUndo() throws NegativeStartException, WrongSelectionEndException, WrongSelectionStartException, ExceedLengthException {
		String s = "azerty";
		editorEngine.insertText(0, s);
		/* select the text to cut */
		Pair<Integer> p = new Pair<Integer>(1, 3);
		editorEngine.setSelection(p);
		/* execute a cut operation */
		editorEngine.cut();
		/* execute paste */
		editorEngine.paste();
		editorEngine.reversePaste(1,s.substring(1,3));
		Assert.assertEquals(s.substring (0,1)+s.substring(3), editorEngine.getText());
	}

}
