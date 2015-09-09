package textEditor;
/***********************************************************************
 * Module:  CmdLine.java
 * Author:  14009503
 * Purpose: Defines the Class CmdLine
 ***********************************************************************/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Simple class to ensure a command line feature of the editor. 
 * The class was built entirely after version 3, after the GUI was fully functional,
 * in order to demonstrate that the design can cope with any kind of interface.
 */
public class CmdLine implements UserInterface {
	/**
	 * the key-words introduced by the user to execute a command
	 * e.g. insert, cut, copy, paste, undo, redo
	 */
	private static final String INSERT_TEXT_CMD = "insert";
	private static final String CUT_CMD = "cut";
	private static final String COPY_CMD = "copy";
	private static final String PASTE_CMD = "paste";
	private static final String UNDO_CMD = "undo";
	private static final String REDO_CMD = "redo";
	/**
	 *  MALFORMED_COMMAND  code: For debugging purposes 
	 */
	private static final int MALFORMED_COMMAND = -1;
	/**
	 *  CORRECT_COMMAND  code: For debugging purposes 
	 */
	private static final int CORRECT_COMMAND = 0;
	/**
	 *  What the final user will see if he types in a wrong command 
	 */
	private static final String MALFORMED_COMMAND_MESSAGE = "====== Cmd error";

	IEditorEngine editorEngine;
	IHistory history;
	String content;
	/**
	 *  Used to read from the command line 
	 */
	BufferedReader bufferRead;
	
	/**
	 * Pass the editorEngine and the history as arguments. They will be used afterwards to register 
	 * the changes to the buffer/clipboard
	 * @param _editorEngine
	 * @param _history
	 */
	public CmdLine(IEditorEngine _editorEngine, IHistory _history){
		initializeMemberVariables(_editorEngine, _history);
		_editorEngine.attach(this);
		while(true){
			try {
				/**
				 *  Read the text inputed by the user 
				 */
				String input = bufferRead.readLine();
				/**
				 *  parse it to retrieve the first part of the command 
				 */
				List<String> cmdParts = parseInput(input);
				int retCode = MALFORMED_COMMAND;
				/**
				 *  perform a certain action according to the retCode previously obtained 
				 */
				switch(cmdParts.get(0)){
				case INSERT_TEXT_CMD:
					retCode = insertCmdLine(cmdParts);
					break;
				case CUT_CMD:
					retCode = cutCmdLine(cmdParts);
					break;
				case COPY_CMD:
					retCode = copyCmdLine(cmdParts);
					break;
				case PASTE_CMD:
					retCode = pasteCmdLine(cmdParts);
					break;
				case UNDO_CMD:
					retCode = undoCmdLine(cmdParts);
					break;
				case REDO_CMD:
					retCode = redoCmdLine(cmdParts);
					break;
				default:
					
				}
				/**
				 *  Print the final result to the user 
				 */
				System.out.println(content);
				if(retCode == MALFORMED_COMMAND){
					System.out.println(MALFORMED_COMMAND_MESSAGE);
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private int insertCmdLine(List<String> cmdParts) {
		if(cmdParts.size() != 3){
			return MALFORMED_COMMAND;
		}
		try {
			int startPos = Integer.parseInt(cmdParts.get(1));
			String textToInsert = cmdParts.get(2);
			history.addCommand(new EnterText(editorEngine, startPos, startPos + textToInsert.length(), textToInsert));
			
		} catch(NumberFormatException e){
			if(Constants.DEBUG) LoggerSingleton.LOG_DEBUG("Copy, parsing numbers not possible");
			return MALFORMED_COMMAND;
		}
		
		return CORRECT_COMMAND;
	}

	private int redoCmdLine(List<String> cmdParts) {
		if(cmdParts.size() > 1){
			return MALFORMED_COMMAND;
		}
		history.redoOperation();
		return CORRECT_COMMAND;
	}

	private int undoCmdLine(List<String> cmdParts) {
		if(cmdParts.size() > 1){
			return MALFORMED_COMMAND;
		}
		history.undoOperation();
		return CORRECT_COMMAND;
	}

	/**
	 * Format is: copy startPos endPos
	 * @param cmdParts
	 * @return
	 */
	private int copyCmdLine(List<String> cmdParts) {
		if(cmdParts.size() != 3){
			return MALFORMED_COMMAND;
		}
		try {
			int startPos = Integer.parseInt(cmdParts.get(1));
			int endPos = Integer.parseInt(cmdParts.get(2));
			history.addCommand(new MakeSelection(editorEngine, new Pair<Integer>(startPos, endPos)));
			history.addCommand(new Copy(editorEngine));
			
		} catch(NumberFormatException e){
			if(Constants.DEBUG) LoggerSingleton.LOG_DEBUG("Copy, parsing numbers not possible");
			return MALFORMED_COMMAND;
		}
		
		return CORRECT_COMMAND;
	}

	/**
	 * Format: paste startPos
	 * @param cmdParts
	 * @return
	 */
	private int pasteCmdLine(List<String> cmdParts) {
		if(cmdParts.size() != 2){
			return MALFORMED_COMMAND;
		}
		try {
			int startPos = Integer.parseInt(cmdParts.get(1));
			editorEngine.setCurrentPos(startPos);
			history.addCommand(new Paste(editorEngine));
			
		} catch(NumberFormatException e){
			if(Constants.DEBUG) LoggerSingleton.LOG_DEBUG("Paste, parsing numbers not possible");
			return MALFORMED_COMMAND;
		}
		
		return CORRECT_COMMAND;
	}

	/**
	 * Format: cut startPos endPos
	 * @param cmdParts
	 * @return
	 */
	private int cutCmdLine(List<String> cmdParts) {
		if(cmdParts.size() != 3){
			return MALFORMED_COMMAND;
		}
		try {
			int startPos = Integer.parseInt(cmdParts.get(1));
			int endPos = Integer.parseInt(cmdParts.get(2));
			history.addCommand(new MakeSelection(editorEngine, new Pair<Integer>(startPos, endPos)));
			history.addCommand(new Cut(editorEngine));
			
		} catch(NumberFormatException e){
			if(Constants.DEBUG) LoggerSingleton.LOG_DEBUG("Cut, parsing numbers not possible");
			return MALFORMED_COMMAND;
		}
		
		return CORRECT_COMMAND;
	}

	private List<String> parseInput(String input) {
		String[] parts = input.split(" ");
		return Arrays.asList(parts);
	}

	private void initializeMemberVariables(IEditorEngine _editorEngine, IHistory _history) {
		editorEngine = _editorEngine;
		history = _history;
		content = "";
		bufferRead = new BufferedReader(new InputStreamReader(System.in));		
	}
    
	@Override
	public void update(Subject subject) {
		IEditorEngine editor = (IEditorEngine) subject;
		content = editor.getText();
		
	}
}