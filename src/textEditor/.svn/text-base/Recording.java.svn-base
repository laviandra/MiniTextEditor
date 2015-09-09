package textEditor;

import java.util.ArrayList;
import java.util.List;

/**
 * The recording observes the changes occurred in the history engine
 * and replays a list of commands according to the user's desires. 
 * A class added in version 3.
 */
public class Recording implements Observer {
	private List<Command> recordedCommands;
	private boolean active;
	
	public List<Command> getRecordedCommands() {
		return recordedCommands;
	}

	public void setRecordedCommands(List<Command> recordedCommands) {
		this.recordedCommands = recordedCommands;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Recording(){
		active = false;
		recordedCommands = new ArrayList<Command>();
	}
	
	/**
	 * startRecording sets active to true
	 */
	public void startRecording(){
		if(Constants.DEBUG) LoggerSingleton.LOG_DEBUG("Starting to record.");
		active = true;
		recordedCommands = new ArrayList<Command>();
	}
	
	/**
	 * endRecording sets active to false
	 */
	public void endRecording(){
		if(Constants.DEBUG) LoggerSingleton.LOG_DEBUG("Stopping recording.");
		active = false;
	}
	
	/**
	 * To replay a command, simply redo all the commands in the recordedCommands list 
	 */
	public void playRecording(){
		if(Constants.DEBUG) LoggerSingleton.LOG_DEBUG("Playing recording.");

		if(recordedCommands.size() > 0){
			for(Command c : recordedCommands){
				c.redo();
			}
		}
	}
	
	@Override
	public void update(Subject subject) {
		if(active){
			Command c = null;
			IHistory history = (IHistory) subject;
			int lastAction = history.getLastAction();
			if(lastAction == IHistory.ADDED_COMMAND){
				c = history.getLastCommand();
				recordedCommands.add(c);
				if(Constants.DEBUG) LoggerSingleton.LOG_DEBUG("Added to recording with " + c);
			} else if(lastAction == IHistory.REMOVED_COMMAND){
				if(recordedCommands.size() > 1) {
					c = recordedCommands.remove(recordedCommands.size() - 1);
					if(Constants.DEBUG) LoggerSingleton.LOG_DEBUG("Removed from recording " + c);
				}
			}

		}

	}

}
