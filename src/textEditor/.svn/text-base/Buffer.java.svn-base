/***********************************************************************
 * Module:  Buffer.java
 * Author:  14009503
 * Purpose: Defines the Class Buffer
 ***********************************************************************/
package textEditor;

import java.util.*;

/**
 * The Buffer class holds the text that is shown to the MiniTextEditor users.
 * A class present in the implementation ever since version 1. It has not been modified
 * during the development of the second and third version.
 */
public class Buffer {
	   
   /**
    * text - the text manipulated 
    */
   private String text = "";

   /**
    *  @return Returns the current text content of the buffer,
    */
   public String getText() {
	   return text;
   }
   
   /**
    * Sets the string received as argument as the new text content of the buffer.
    */
   public void setText(String _text){
	   text = _text;
   }
   
   /**
    *  Get the text in between two given positions
    *  @param p pair of positions 
    */
   public String getText(Pair<Integer> p){
	   if(p.second >= p.first) {
		   return text.substring(p.first, p.second);
	   }
	   else {
		   return text.substring(p.second, p.first);
	   }
   }
   
   /** 
    * Insert a given text starting from a given position
    * @param startPos starting position
    * @param textToInsert the text to be inserted  
    */
   public void insertText(int startPos, String textToInsert) {
	   if(startPos == 0) {
		   text = textToInsert + text;
	   } else {
		   text = text.substring(0, startPos) + textToInsert + text.substring(startPos);
	   }
   }
   
   /**
    *  Remove the text in between two given positions
    *  @param p Pair of positions 
    */
   public void removeText(Pair<Integer> p) {
	   if(Constants.DEBUG) LoggerSingleton.LOG_DEBUG("Removing from <" + text + ">, pos " + p);
	   if(p.first == 0){
		   text = text.substring(p.second);
	   } else {
		   text = text.substring(0, p.first) + text.substring(p.second);
	   }
   }

}