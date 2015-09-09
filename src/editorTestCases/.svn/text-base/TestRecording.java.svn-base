package editorTestCases;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import textEditor.Command;
import textEditor.Copy;
import textEditor.Cut;
import textEditor.EditorEngine;
import textEditor.EnterText;
import textEditor.ExceedLengthException;
import textEditor.History;
import textEditor.IHistory;
import textEditor.MakeSelection;
import textEditor.NegativeStartException;
import textEditor.Pair;
import textEditor.Paste;
import textEditor.Recording;

public class TestRecording {

	EditorEngine editorEngine;
	IHistory history;
	Recording recording;
	
	@Before
	public void setUp() throws Exception {
		editorEngine = new EditorEngine();
		history = new History();
		recording = new Recording();
		history.attach(recording);
	}
	
	/**
	 * Tests the start recording commad
	 * @throws NegativeStartException
	 * @throws ExceedLengthException
	 */
	@Test
	public void testStartRecording() throws NegativeStartException, ExceedLengthException {
		recording.startRecording();
		Assert.assertTrue(recording.isActive());
		Assert.assertEquals(0, recording.getRecordedCommands().size());
	}
	
	@Test
	public void testEndrecording() {
		recording.endRecording();
		Assert.assertFalse(recording.isActive());
	}
	
	private void executeEnterText(String s, int startPos) {
		Command c = new EnterText(editorEngine, startPos, startPos + s.length(), s);
		history.addCommand(c);
		c.execute();
	}
	
	private void executeCopy() {
		Command c = new Copy(editorEngine);
		history.addCommand(c);
		c.execute();
	}
	
	private void executeCut() {
		Command c = new Cut(editorEngine);
		history.addCommand(c);
		c.execute();
	}
	
	private void executePaste() {
		Command c = new Paste(editorEngine);
		history.addCommand(c);
		c.execute();
	}
	
	private void executeSelection(Pair<Integer> p) {
		Command c = new MakeSelection(editorEngine, p);
		history.addCommand(c);
		c.execute();
	}
	
	/**
	 * Start the recording of the following sequence of commands: Copy; Paste; Paste 
	 */
	@Test
	public void testPlayRecordingCopyPastePaste() {
		String s = "azerty";
		recording.startRecording();
		executeEnterText(s, 0);
		executeSelection(new Pair<Integer>(1, 3));
		executeCopy();
		executePaste();
		executePaste();
		recording.endRecording();
		recording.playRecording();
		Assert.assertEquals(3, recording.getRecordedCommands().size());
		Assert.assertEquals(s+s.substring(1,3)+s.substring(1,3)+s+s.substring(1,3)+s.substring(1,3), editorEngine.getText());
	}
	
	@Test
	public void testPlayRecordingEmptyRecording() {
		int sizeBeforeRec = recording.getRecordedCommands().size();
		String textBeforeRec = editorEngine.getText();
		recording.playRecording();
		int sizeAfterRec = recording.getRecordedCommands().size();
		String textAfterRec = editorEngine.getText();
		Assert.assertEquals(sizeAfterRec, sizeBeforeRec);
		Assert.assertEquals(textBeforeRec, textAfterRec);
	}
}
