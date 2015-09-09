package textEditor;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * A frame in which you introduce the string you wish to search for
 * in the given text.
 * A class present in the implementation ever since version 1. It has not been modified
 * during the development of the second and third version.
 */
public class SearchWindow extends JFrame {
	private JTextField tf;
	private JButton button;
	private JPanel p1, p2;
	private String theText = "";
	private Gui gui;
	
	public SearchWindow(Gui gui) {
		super("Insert a text to search for");
		setLayout(new GridLayout(2,1));
		tf = new JTextField(30);
		button = new JButton("Search"); 
		p1 = new JPanel();
		p2 = new JPanel();
		
		add(p1); add(p2);
		p1.add(tf);
		p2.add(button);
		
		pack();
		
		
		this.gui = gui;
		button.addActionListener(new SearchButtonActionListener(this));
	}

	public JTextField getTf() {
		return tf;
	}

	public void setTf(JTextField tf) {
		this.tf = tf;
	}

	public JButton getButton() {
		return button;
	}

	public void setButton(JButton button) {
		this.button = button;
	}
	
	public String getSearchText() {
		return theText;
	}

	public void setSearchText(String theText) {
		this.theText = theText;
	}

	public Gui getGui() {
		return gui;
	}
	
	public void makeVisible(boolean status){
		setVisible(status);
	}
	
	public void reInitialize(){
		theText = "";
		tf.setText(theText);
		
	}
}

class SearchButtonActionListener implements ActionListener {

	SearchWindow sw;
	
	public SearchButtonActionListener(SearchWindow sw) {
		this.sw = sw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		sw.setSearchText(sw.getTf().getText());
		System.out.println(sw.getSearchText());
		sw.makeVisible(false);
		sw.getGui().searchText(sw);
	}
	
}