package dei.uc.pt.ar.paj;

import static org.junit.Assert.assertEquals;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.JAXBException;

import org.junit.Ignore;
import org.junit.Test;

public class SimpleUserServiceTest {
	
	private static URI uri = UriBuilder.fromPath("http://localhost:8080/thews-ws/rest/users").build();

	
	@Test
    public void testUserListIsOk() throws JAXBException{
        Client client = ClientBuilder.newClient();
        Response response = client.target(uri+"/list").request(MediaType.APPLICATION_XML).get();
        assertEquals(Response.Status.OK, response.getStatusInfo());
        client.close();
    }
	
	@Test
    public void testUserListIsNotOk() throws JAXBException{
        Client client = ClientBuilder.newClient();
        Response response = client.target(uri+"/lists").request(MediaType.APPLICATION_XML).get();
        assertEquals(Response.Status.NOT_FOUND, response.getStatusInfo());
        client.close();
    }
	
	@Test
    public void testUserIsFound() throws JAXBException{
        Client client = ClientBuilder.newClient();
        Response response = client.target(uri+"/list/1").request(MediaType.APPLICATION_XML).get();
        assertEquals(Response.Status.OK, response.getStatusInfo());
        client.close();
    }
	
	@Ignore
	@Test
    public void testUserIsNotFound() throws JAXBException{
        Client client = ClientBuilder.newClient();
        Response response = client.target(uri+"/list/7").request(MediaType.APPLICATION_XML).get();
        assertEquals(Response.Status.NOT_FOUND, response.getStatusInfo());
        client.close();
    }

}
