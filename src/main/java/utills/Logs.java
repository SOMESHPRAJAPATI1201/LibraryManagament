package utills;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Logs {
	
	private static Logger logger = Logger.getLogger("MyLog");
	
	public static void logger() {
		FileHandler fh;
		try {
			fh = new FileHandler("MyLogFile.txt");
			logger.addHandler(fh);
			SimpleFormatter simple = new SimpleFormatter();
			fh.setFormatter(simple);
			logger.info("Logger Initialized");
		} catch (Exception e) {
			logger.log(Level.WARNING.SEVERE, "Exception :: ",e);
		}
	}

}
