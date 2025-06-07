package api.tests.userTest;


import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class BaseClass {

	public Logger logger;
	
	public void basicSetup() {

		logger = LogManager.getLogger(this.getClass());
	
	}
	
}
