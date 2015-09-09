package textEditor;

/**
 * A pair class used mostly to pass a binomial parameter to the setSelection
 * method. 
 * @param <T>
 * A class present in the implementation ever since version 1. It has not been modified
 * during the development of the second and third version.
 */
public class Pair<T> {
	public T first;
	public T second;
	
	public Pair(T first, T second){
		this.first = first;
		this.second = second;
	}
	
	public boolean equals(Object other){
		
		if(other == null)
			return false;
		if(other instanceof Pair){
			Pair<T> p = (Pair<T>) other;
			if(first == p.first && second == p.second)
				return true;
		}
		return false;
	}
	
	public String toString(){
		return first + " " + second;
	}
}
