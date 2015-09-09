package textEditor;

import java.util.*;
/**
 * A class present in the implementation ever since version 1. It has not been modified
 * during the development of the second and third version.
 */
public class Selection {
	
	private Pair<Integer> selection;
	
	public Selection() {
		selection = new Pair<Integer>(0, -1);
	}
	
	
	public void setStartSelection(int start) {
		selection.first = start;
	}
	
	public void setEndSelection(int end) {
		selection.second = end;
	}

	public void setSelection(Pair<Integer> p) {
		selection.first = p.first; 
		selection.second = p.second;
	}
	
	public Pair<Integer> getSelection() {
		return selection;
	}
	
	public void clear() {
		selection.first = 0;
		selection.second = 0;
	}
}