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

public class TestCommands {
	EditorEngine editorEngine;
	
	@Before
	public void setUp() throws Exception {
		editorEngine = new EditorEngine();
	}
	
	@Test
	public void testCut() {
		String s = "azerty";
		Command c = new EnterText(editorEngine, 0, s.length(), s);
		c.execute();
		c = new MakeSelection(editorEngine, new Pair<Integer>(1, 3));
		c.execute();
		c = new Cut(editorEngine);
		c.execute();
		
		Assert.assertEquals(s.substring(1, 3), editorEngine.getClipboardText());
		Assert.assertEquals(s.substring(0, 1)+s.substring(3, s.length()), editorEngine.getText());
	}
	
	@Test
	public void testCopy() {
		String s = "azerty";
		Command c = new EnterText(editorEngine, 0, s.length(), s);
		c.execute();
		c = new MakeSelection(editorEngine, new Pair<Integer>(1, 3));
		c.execute();
		c = new Copy(editorEngine);
		c.execute();
		
		Assert.assertEquals(s.substring(1, 3), editorEngine.getClipboardText());
		Assert.assertEquals(editorEngine.getText(), editorEngine.getText());
	}
	
	@Test
	public void testCopyPaste() {
		String s = "azerty";
		Command c = new EnterText(editorEngine, 0, s.length(), s);
		c.execute();
		c = new MakeSelection(editorEngine, new Pair<Integer>(1, 3));
		c.execute();
		c = new Copy(editorEngine);
		c.execute();
		c = new Paste(editorEngine);
		c.execute();
		
		Assert.assertEquals(s.substring(1, 3), editorEngine.getClipboardText());
		Assert.assertEquals(s+s.substring(1, 3), editorEngine.getText());
	}
	
	@Test
	public void testCopyPasteUndo() {
		String s = "azerty";
		Command c = new EnterText(editorEngine, 0, s.length(), s);
		c.execute();
		c = new MakeSelection(editorEngine, new Pair<Integer>(1, 3));
		c.execute();
		c = new Copy(editorEngine);
		c.execute();
		c = new Paste(editorEngine);
		c.execute();
		c.undo();
		
		Assert.assertEquals(s.substring(1, 3), editorEngine.getClipboardText());
		Assert.assertEquals(s, editorEngine.getText());
	}
	
	@Test
	public void testCopyPasteUndoRedo() {
		String s = "azerty";
		Command c = new EnterText(editorEngine, 0, s.length(), s);
		c.execute();
		c = new MakeSelection(editorEngine, new Pair<Integer>(1, 3));
		c.execute();
		c = new Copy(editorEngine);
		c.execute();
		c = new Paste(editorEngine);
		c.execute();
		c.undo();
		c.redo();
		
		Assert.assertEquals(s.substring(1, 3), editorEngine.getClipboardText());
		Assert.assertEquals(s+s.substring(1, 3), editorEngine.getText());
	}
	
	@Test
	public void testCutPaste() {
		String s = "azerty";
		Command c = new EnterText(editorEngine, 0, s.length(), s);
		c.execute();
		c = new MakeSelection(editorEngine, new Pair<Integer>(1, 3));
		c.execute();
		c = new Cut(editorEngine);
		c.execute();
		c = new Paste(editorEngine);
		c.execute();
		
		Assert.assertEquals(s.substring(1, 3), editorEngine.getClipboardText());
		Assert.assertEquals(s, editorEngine.getText());
	}
}
