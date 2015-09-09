package textEditor;
/***********************************************************************
 * Module:  Gui.java
 * Author:  14009503
 * Purpose: Defines the Class Gui
 ***********************************************************************/

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.MenuItem;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;

/**
 *	A class present throughout the implementation of all three versions.
 *	This is practically the gateway through which the user communicates with the application.
 *  It firstly had the contextual menu (Cut, Copy, Paste) for version 1, then the other 
 *  menus, along with the features they provide were added: Edit for Undo and Redo [V3].
 *  And Recording for version 2. 
 */
public class Gui extends JFrame implements UserInterface, ActionListener {
	
	IEditorEngine editorEngine; 
	IHistory history;
	Recording recording;
	
	JFrame editorWindow;
	JEditorPane ta;
	JScrollPane scrollPane;
	
	JMenu menu;
	JMenuItem copyItem;
	JMenuItem cutItem; 
	JMenuItem pasteItem;
	
	JMenuBar regularBar;

	JMenu editMenu;
	JMenuBar editMenuBar;
	JMenuItem undoItem;
	JMenuItem redoItem;
	
	JMenu regularMenu;
	JMenuItem saveFile;
	JMenuItem openFile;
	JMenuItem searchFile;
	JMenuItem quit;
	
	JMenu recordMenu;
	JMenuBar recordMenuBar;
	JMenuItem startRecordingItem;
	JMenuItem endRecordingItem;
	JMenuItem playRecordingItem;
	
	
	SearchWindow sw = new SearchWindow(this);
    DefaultHighlighter.DefaultHighlightPainter highlightPainter = 
            new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
	
	public Gui(IEditorEngine _editorEngine, IHistory _history, Recording _recording) {
		super(Constants.WINDOW_TITLE_STRING);
		this.setSize(900, 800);
		
		editorEngine = _editorEngine;
		history = _history;
		recording = _recording;
		
		editorEngine.attach(this);
		
		menu = new JMenu(Constants.OPTIONS_MENU_STRING);
		copyItem = new JMenuItem(Constants.COPY_MENU_STRING);
		cutItem = new JMenuItem(Constants.CUT_MENU_STRING);
		pasteItem = new JMenuItem(Constants.PASTE_MENU_STRING);
		
		menu.add(copyItem);
		menu.add(cutItem);
		menu.add(pasteItem);
		
		regularBar = new JMenuBar();
		setJMenuBar(regularBar);
		
		regularMenu = new JMenu(Constants.MENU_STRING);
		saveFile = new JMenuItem(Constants.SAVE_MENU_STRING);
		openFile = new JMenuItem(Constants.OPEN_MENU_STRING);
		searchFile = new JMenuItem(Constants.SEARCH_MENU_STRING);
		quit = new JMenuItem(Constants.QUIT_MENU_STRING);
		
		editMenu = new JMenu(Constants.EDIT_MENU_STRING);
		undoItem = new JMenuItem(Constants.UNDO_MENU_STRING);
		redoItem = new JMenuItem(Constants.REDO_MENU_STRING);
		
		recordMenu = new JMenu(Constants.RECORD_MENU_STRING);
		startRecordingItem = new JMenuItem(Constants.START_RECORDING_MENU_STRING);
		endRecordingItem = new JMenuItem(Constants.END_RECORDING_MENU_STRING);
		playRecordingItem = new JMenuItem(Constants.PLAY_RECORDING_MENU_STRING);

		saveFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Specify a location in which to save");    
				
				int userSelection = fileChooser.showSaveDialog(null);
				if (userSelection == JFileChooser.APPROVE_OPTION) {
				    File fileToSave = fileChooser.getSelectedFile();
				    System.out.println("Saving file: " + fileToSave.getAbsolutePath());
				    try {
						BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave));
						writer.write(ta.getText());
						writer.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		openFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Specify a file to open");
				
				int userSelection = fileChooser.showOpenDialog(null);
				if (userSelection == JFileChooser.APPROVE_OPTION) {
					File fileToOpen = fileChooser.getSelectedFile();
					System.out.println("Opening file: " + fileToOpen.getName());
					try {
						BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileToOpen)));
						String str;
						ta.setText("");
						while((str=in.readLine())!=null) {
							ta.setText(ta.getText() + str+"\n");
						}
						
						editorEngine.resetEditor();
						Command c = new EnterText(editorEngine, 0, ta.getText().length(), ta.getText());
						c.execute();
						history.clear();
						history.addCommand(c);

					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		
		searchFile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				sw.makeVisible(true);
			}
		});
		
		quit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		redoItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				history.redoOperation();
			}
		});
		
		undoItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				history.undoOperation();
			}
		});
		
		startRecordingItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				recording.startRecording();
			}
			
		});
		
		endRecordingItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				recording.endRecording();
			}
			
		});
		
		playRecordingItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				recording.playRecording();
			}
			
		});
			
		regularMenu.add(saveFile);
		regularMenu.add(openFile);
		regularMenu.add(searchFile);
		regularMenu.add(quit);
		
		editMenu.add(undoItem);
		editMenu.add(redoItem);
		
		recordMenu.add(startRecordingItem);
		recordMenu.add(endRecordingItem);
		recordMenu.add(playRecordingItem);
		
		regularBar.add(regularMenu);
		regularBar.add(editMenu);
		regularBar.add(recordMenu);
		
		Dimension dim = new Dimension(850, 750);
		ta = new JEditorPane();
		addKeyBindings();
		ta.setMinimumSize(dim);
		scrollPane = new JScrollPane(ta);

		this.add(scrollPane, BorderLayout.CENTER);
		
		ta.addMouseListener(new EditorMouseAdapter(editorEngine, history));
		ta.addKeyListener(new EditorKeyListener(editorEngine, history));
		
		createContextualMenu();
		this.setVisible(true);
		
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	/**
	 * Adds key bindings for the common operations such as Cut, Copy, Paste, Undo etc.
	 */
	private void addKeyBindings() {
		InputMap im = ta.getInputMap(JComponent.WHEN_FOCUSED);
		ActionMap am = ta.getActionMap();

		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()), "Undo");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_Y, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()), "Redo");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_X, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()), "Cut");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()), "Copy");
		im.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()), "Paste");


		am.put("Undo", new AbstractAction() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	history.undoOperation();
		    }
		});
		am.put("Redo", new AbstractAction() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	history.redoOperation();
		    }
		});		
		am.put("Cut", new AbstractAction() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
				Command c = new Cut(editorEngine);
		    	c.execute();
				history.addCommand(c);		    }
		});	
		am.put("Copy", new AbstractAction() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
				Command c = new Copy(editorEngine);
		    	c.execute();
				history.addCommand(c);		    }
		});	
		am.put("Paste", new AbstractAction() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
				Command c = new Paste(editorEngine);
		    	c.execute();
				history.addCommand(c);		    }
		});	
	}


	public void searchText(SearchWindow sw){
		boolean found = false;
		String searchText = sw.getSearchText();
		int startPosition = ta.getText().indexOf(searchText);
		ta.getHighlighter().removeAllHighlights();

		while(startPosition > -1) {
			/* the text was found */
			found = true;
			try {
				int endPosition = startPosition + searchText.length();
				ta.getHighlighter().addHighlight(startPosition, endPosition, highlightPainter);
				startPosition = ta.getText().indexOf(searchText, endPosition);
			}
			catch (BadLocationException e) {
				e.printStackTrace();
			}
		}
		if(!found) {
			JOptionPane.showMessageDialog(null, "The text could not be found");
		}
		sw.reInitialize();

	}
	
	private void createContextualMenu() {
		JMenuItem menuItem;

		/* Create the contextual menu. */
		JPopupMenu contextualMenu = new JPopupMenu();
		
		menuItem = new JMenuItem(Constants.CUT_MENU_STRING);
		menuItem.addActionListener(this);
		contextualMenu.add(menuItem);
		
		menuItem = new JMenuItem(Constants.COPY_MENU_STRING);
		menuItem.addActionListener(this);
		contextualMenu.add(menuItem);
		
		menuItem = new JMenuItem(Constants.PASTE_MENU_STRING);
		menuItem.addActionListener(this);
		contextualMenu.add(menuItem);

		//Add listener to the text area so the popup menu can come up.
		MouseListener popupListener = new ContextualMenuListener(contextualMenu);
		ta.addMouseListener(popupListener);
	}


	@Override
	public void actionPerformed(ActionEvent arg) {
		Object source = arg.getSource();
		Command c = null;
		if(source instanceof JMenuItem){
			JMenuItem item = (JMenuItem) source;
			String itemName = item.getText();
			switch(itemName){
			
			case Constants.CUT_MENU_STRING:
				c = new Cut(editorEngine);
				break;
			case Constants.COPY_MENU_STRING:
				c = new Copy(editorEngine);
				break;
			case Constants.PASTE_MENU_STRING:
				c = new Paste(editorEngine);
				break;
			}
			if(c != null) {
				c.execute();
				history.addCommand(c);
			}
		}
	}

	/**
	 * ObsPattern; Updates the text in the editorEngine's buffer if a key was pressed,
	 * or if a command that modified the text was executed. 
	 */
	@Override
	public void update(Subject subject) {
		IEditorEngine editor = (IEditorEngine) subject;
		ta.setText(editor.getText());
		ta.setCaretPosition(editorEngine.getCurrentPos());
	}
	
}