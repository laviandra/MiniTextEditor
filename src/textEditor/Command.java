package textEditor;
/***********************************************************************
 * Module:  Command.java
 * Author:  14009503
 * Purpose: Defines the Interface Command
 ***********************************************************************/

import java.util.*;
/**
 * The Command interface was present ever since version 1. 
 * For version 3, redo and undo methods were added, as well as a 
 * shouldBeAddedToHistory method. These methods were useful in 
 * version 2 as well.
 */

public interface Command {
   public void execute();
   public boolean shouldBeAddedToHistory();

   public void undo();
   public void redo();
}