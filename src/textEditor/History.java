package textEditor;

import java.util.ArrayList;
import java.util.List;

/**
 * Class added in version 2 and refactored especially to meet
 * V3's needs.
 * History contains the list of "undoable" commands along with the 
 * undoOperation and redoOperation methods[V3]. 
 */
public class History extends Subject implements IHistory {
	
	private List<Command> cmdList;
	private int currentPosInCmdlist;
	private int lastAction;
	
	public List<Command> getCmdList() {
		return cmdList;
	}

	public void setCmdList(List<Command> cmdList) {
		this.cmdList = cmdList;
	}

	public int getCurrentPosInCmdlist() {
		return currentPosInCmdlist;
	}

	public void setCurrentPosInCmdlist(int currentPosInCmdlist) {
		this.currentPosInCmdlist = currentPosInCmdlist;
	}

	public void setLastAction(int lastAction) {
		this.lastAction = lastAction;
	}

	public History(){
		super();
		cmdList = new ArrayList<Command>();
		/* set it on a dummy position */
		currentPosInCmdlist = -1;
		lastAction = NO_ACTION;
	}
	
	@Override
	public void addCommand(Command c) {
		/** 
		 * position will always be one element less than the list's length
		 * this way, you will know when you inserted another command
		 * so that you won't execute redo on it
		 */
		if(!c.shouldBeAddedToHistory()) {
			return;
		}
		
		if(Constants.DEBUG) LoggerSingleton.LOG_DEBUG("Adding to history: " + c);
		
		if(currentPosInCmdlist+1 == cmdList.size()) {
			cmdList.add(c);
			currentPosInCmdlist++;
		}
		/**
		 *  after executing undo, if you insert a command - you cannot do redo 
		 */
		else {
			cmdList.set(++currentPosInCmdlist, c);
			while(currentPosInCmdlist+1 < cmdList.size()) {
				if(Constants.DEBUG) LoggerSingleton.LOG_DEBUG("Removing from history: " + cmdList.get(currentPosInCmdlist + 1));
				cmdList.remove(currentPosInCmdlist+1);
			}
		}
		lastAction = ADDED_COMMAND;
		notifyObservers();
	}

	/**
	 * Clears the command list
	 */
	@Override
	public void clear(){
		cmdList.clear();
		currentPosInCmdlist = -1;
		lastAction = NO_ACTION;
	}
	
	/**
	 * Retrieves the last command introduced in the list
	 */
	@Override
	public Command getLastCommand(){
		return cmdList.get(currentPosInCmdlist);
	}
	
	@Override
	public int getLastAction() {
		return lastAction;
	}
	
	/**
	 * A Redo operation can only occur after an Undo. You can have as many Redos as previous Undos.
	 * @return void
	 */
	@Override
	public void redoOperation() {
		Command nextCommand;
		
		if(currentPosInCmdlist < cmdList.size()-1) {
				currentPosInCmdlist++;
				nextCommand = cmdList.get(currentPosInCmdlist);
				nextCommand.redo();
				lastAction = ADDED_COMMAND;
				notifyObservers();
		}
	}

	
	/**
	 * An Undo operation can only occur if there are remaining commands in the list.
	 * @return void
	 */
	@Override
	public void undoOperation() {
		Command previousCommand;

		if(currentPosInCmdlist >= 0) {			
			previousCommand = cmdList.get(currentPosInCmdlist);
			currentPosInCmdlist--;
			previousCommand.undo();
			lastAction = REMOVED_COMMAND;
			notifyObservers();
		}
		
	}


}
