package server;

import org.restlet.Request;
import org.restlet.data.Form;
import org.restlet.data.MediaType;
import org.restlet.representation.FileRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

public class Document extends ServerResource {
	String fileName = "";
	
	@Get
    public FileRepresentation getResource() {
		FileRepresentation result = null;
		Request request = getRequest();
		Form form = request.getResourceRef().getQueryAsForm();
		if(form.getValues("fileName") != null)
		{
			fileName += form.getValues("fileName");
		}		
		result = new FileRepresentation(Main.filePath + "//" + fileName, MediaType.TEXT_HTML);
		return result;
    }
}
