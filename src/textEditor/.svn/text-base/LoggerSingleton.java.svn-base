package textEditor;

import java.io.IOException;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;
 
/**
 * LoggerSingleton. A class designed in the early stages of 
 * version 3 in order to avoid System.err.println messages.
 */
public class LoggerSingleton {
    private static LoggerSingleton instance = null;
     
    public static LoggerSingleton GetInstance() {
        if (null == instance){
            instance = new LoggerSingleton();
        }
         
        return instance;
    }
     
    public static void LOG_TRACE(String content){
        synchronized(LoggerSingleton.class){
            GetInstance().logger.trace(content);
        }
    }
     
    public static void LOG_DEBUG(String content){
        synchronized(LoggerSingleton.class){
            GetInstance().logger.debug(content);
        }
    }
     
    public static void LOG_INFO(String content){
        synchronized(LoggerSingleton.class){
            GetInstance().logger.info(content);
        }
    }
     
    public static void LOG_WARNING(String content){
        synchronized(LoggerSingleton.class){
            GetInstance().logger.warn(content);
        }
    }
     
    public static void LOG_ERROR(String content){
        synchronized(LoggerSingleton.class){
            GetInstance().logger.error(content);
        }
    }
     
    public static void LOG_FATAL(String content){
        synchronized(LoggerSingleton.class){
            GetInstance().logger.fatal(content);
        }
    }
     
    private Logger logger = null;
     
    private LoggerSingleton(){
        logger = Logger.getRootLogger();
    }
     
    public void ActivateLogging(){
        PatternLayout layout = new PatternLayout("%-4r [%t] %-5p %c %x - %m%n");
        FileAppender appender = null;
        try {
            appender = new RollingFileAppender(layout, "miniEditor.log");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        appender.activateOptions();
        logger.addAppender(appender);
         
        logger.removeAppender("filelogger");
    }
}
