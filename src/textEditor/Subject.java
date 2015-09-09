package textEditor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A class present in the implementation ever since version 2. It has not been modified
 * during the development of the third version.
 */
public class Subject {

	private List<Observer> observers;
	
	protected Subject(){
		observers = new ArrayList<Observer>();
	}
	
	/**
	 * Attaches an observer to the subject's list of observers.
	 * @param o The observer that will be attached to the list.
	 */
	public void attach (Observer o) { observers.add(o); }
	
	/**
	 * Removes the observer received as argument from the subject's list of observers.
	 * @param o The observer that will be removed from the list.
	 */
	public void detach (Observer o) { observers.remove(o); }
	
	/**
	 * When a subject's state changes, this function will be called;
	 */
	public void notifyObservers () {
		Iterator<Observer> i = observers.iterator();
		while (i.hasNext()) {
			Observer o = i.next();
			o.update(this);
		}
	}
}
