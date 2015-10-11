package server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Timer;

import org.restlet.Component;
import org.restlet.data.Protocol;

public class Main {
	static Integer port = 8183;
	static String filePath = "";
	static File[] listOfFiles = null;
	
	public static void main(String[] args) throws Exception {  		
		getProperties(args[0]);
		File folder = new File(filePath);
    	listOfFiles = folder.listFiles();
    	
	    // Create a new Component.  
	    Component component = new Component(); 

	    // Add a new HTTP server listening on port configured, the default port is 8183.  
	    component.getServers().add(Protocol.HTTP, port);  

	    // Attach the application.  
	    component.getDefaultHost().attach("/api",  
	            new APISever());  

	    // Start the component.  
	    component.start();		    
	   
	} 
	
	private static void getProperties(String configFilePath){
		Properties configFile = new Properties();
		FileInputStream file;
		try {
			file = new FileInputStream(configFilePath);
			configFile.load(file);
			file.close();
			port = Integer.parseInt(configFile.getProperty("ServerPort"));
			filePath = configFile.getProperty("SeverFilePath");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
