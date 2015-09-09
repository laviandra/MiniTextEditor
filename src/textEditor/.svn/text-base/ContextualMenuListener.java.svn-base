package textEditor;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPopupMenu;

/**
 * Contextual menu for the Cut/Copy/Paste
 * A class present in the implementation ever since version 1. It has not been modified
 * during the development of the second and third version.
 */
public class ContextualMenuListener implements MouseListener {
	JPopupMenu contextualMenu;
	
	public ContextualMenuListener(JPopupMenu _contextualMenu) {
		contextualMenu = _contextualMenu;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	/**
	 *  retrieve the position on screen that triggered the contextual menu to popup 
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		if(e.isPopupTrigger()) {
			contextualMenu.show(e.getComponent(),
                e.getX(), e.getY());
		}		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(e.isPopupTrigger()) {
			contextualMenu.show(e.getComponent(),
                e.getX(), e.getY());
		}
	}

}
