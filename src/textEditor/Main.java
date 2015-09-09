package textEditor;

/**
 * The Main class. Used for launching the UserInterface. Present since version 1,
 * changed as soon as parameters were added to the Gui constructor:
 * IHistory[V3] and Recording[V2]. 
 */
public class Main {

	public static void main(String [] args) {
		IEditorEngine editorEngine = new EditorEngine();
		IHistory history = new History();
		Recording recording = new Recording();
		history.attach(recording);
		/**
		 * If a command line interface is preferred, instantiate the mainFrame
		 * with the following: new CmdLine(editorEngine, history)
		 */
		UserInterface mainFrame = new Gui(editorEngine, history, recording);
	}
	
}
