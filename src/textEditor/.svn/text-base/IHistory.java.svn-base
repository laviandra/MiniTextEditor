package textEditor;

import java.util.List;

/**
 * IHistory. Built in V2 due to refactoring issues. 
 */
public interface IHistory  {
	
	public static final int NO_ACTION = 0;
	public static final int ADDED_COMMAND = 0;
	public static final int REMOVED_COMMAND = 0;
	
	public void addCommand(Command c);
	
	public void redoOperation();	
	public void undoOperation();
	
	public void attach(Observer o);
	public void detach(Observer o);
	
	public void clear();
	
	public Command getLastCommand();
	public int getLastAction();
}
