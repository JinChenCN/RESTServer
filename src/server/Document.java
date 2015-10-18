package server;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.restlet.Request;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.representation.FileRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class Document extends ServerResource {
	String fileName = "";
	
	@Get
    public FileRepresentation getResource() throws IOException {
		FileRepresentation result = null;
		Request request = getRequest();
		Form form = request.getResourceRef().getQueryAsForm();
		if(form.getValues("fileName") != null)
		{
			fileName += form.getValues("fileName");
		}		
		String fileType = Files.probeContentType(new File(Main.filePath + "//" + fileName).toPath());
		if(fileType==null)
		{
			result = new FileRepresentation(Main.filePath + "//" + fileName, MediaType.TEXT_HTML);
		}
		else
		{
			switch (fileType) {
	         case "text/plain":
	        	 result = new FileRepresentation(Main.filePath + "//" + fileName, MediaType.TEXT_HTML);
	             break;
	         case "image/jpeg":
	        	 result = new FileRepresentation(Main.filePath + "//" + fileName, MediaType.IMAGE_JPEG);
	             break;
	         case "image/png":
	        	 result = new FileRepresentation(Main.filePath + "//" + fileName, MediaType.IMAGE_PNG);
	             break;
	         case "application/pdf":
	        	 result = new FileRepresentation(Main.filePath + "//" + fileName, MediaType.APPLICATION_PDF);
	        	 break;
	         case "application/msword":
	        	 result = new FileRepresentation(Main.filePath + "//" + fileName, MediaType.APPLICATION_WORD);
	             break;
	         case "application/vnd.openxmlformats-officedocument.wordprocessingml.document":
	        	 result = new FileRepresentation(Main.filePath + "//" + fileName, MediaType.APPLICATION_MSOFFICE_DOCX);
	        	 break;
	         case "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet":
	        	 result = new FileRepresentation(Main.filePath + "//" + fileName, MediaType.APPLICATION_MSOFFICE_XLSX);
	        	 break;
	         default:
	        	 result = new FileRepresentation(Main.filePath + "//" + fileName, MediaType.TEXT_HTML);
			}			
		}		 
		return result;
    }
}
