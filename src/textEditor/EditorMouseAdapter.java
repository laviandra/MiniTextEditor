package textEditor;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JEditorPane;
import javax.swing.JTextArea;

/**
 * A class present in the implementation ever since version 1. It has not been
 * modified during the development of the second and third version. *
 */
public class EditorMouseAdapter implements MouseListener {
	IEditorEngine editorEngine;
	int previousPos = -1;
	IHistory history;

	public EditorMouseAdapter(IEditorEngine _editorEngine, IHistory _history) {
		editorEngine = _editorEngine;
		history = _history;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		JEditorPane ta = (JEditorPane) arg0.getSource();
		int pos = ta.viewToModel(arg0.getPoint());
		editorEngine.setCurrentPos(pos);
		Command c = new MakeSelection(editorEngine, new Pair<Integer>(0, 0));
		c.execute();
		history.addCommand(c);
		previousPos = -1;
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		JEditorPane ta = (JEditorPane) arg0.getSource();
		int pos = ta.viewToModel(arg0.getPoint());
		editorEngine.setCurrentPos(pos);
		previousPos = pos;
	}

	/**
	 * Override mouseReleased to retrieve the selection's positions
	 */
	@Override
	public void mouseReleased(MouseEvent arg0) {
		JEditorPane ta = (JEditorPane) arg0.getSource();
		int pos = ta.viewToModel(arg0.getPoint());
		editorEngine.setCurrentPos(pos);
		if (pos != previousPos && previousPos != -1) {
			Command c = new MakeSelection(editorEngine, new Pair<Integer>(
					previousPos, pos));
			c.execute();
			history.addCommand(c);
			previousPos = -1;
		}
	}

}
