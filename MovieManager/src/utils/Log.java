package utils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Log {
	
	private final Logger log ;
	
	private final boolean verbose ;
	
	public Log(String name, boolean v) {
		log = Logger.getLogger(name) ;
		verbose = v;

	}

	public void logInfo(String msg) {
		if (verbose) {
			log.log(Level.INFO, msg);
		}
	}
	
	public void logInfo(String msg, Object arg) {
		if (verbose) {
			log.log(Level.INFO, msg, arg);
		}
	}
	
	public void logInfo(String msg, Object[] args) {
		if (verbose) {
			log.log(Level.INFO, msg, args);
		}
	}
	
	public void logSevere(String msg) {
		log.log(Level.SEVERE, msg);
	}
	
	public void logSevere(String msg, Object arg) {
		log.log(Level.SEVERE, msg, arg);
	}
	
	public void logSevere(String msg, Object[] args) {
		log.log(Level.SEVERE, msg, args);
	}
	
}
