package ie.sparehands.webservices;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ie.sparehands.daos.UserDAO;
import ie.sparehands.entities.Applicant;
import ie.sparehands.entities.User;

@Path("/user")
@Stateless
@LocalBean
public class UserWS {

	 @EJB
	    private UserDAO userDao;

	    @GET 
	    @Path("/allUsers")
	    @Produces({ MediaType.APPLICATION_JSON})
	    public Response findAll() {
	    	System.out.println("Get all users");
	    	final List<User> users = userDao.getAllUsers();
	        return Response.status(200).entity(users).build();
	    }	   
	   
		@GET
		@Path("/allUsers/{id}")
		@Produces({ MediaType.APPLICATION_JSON })
		public Response findUserById(@PathParam("id") int id) {
			final User user = userDao.getUser(id);
			return Response.status(200).entity(user).build();
		}
		
	   @GET
	    @Produces({ MediaType.APPLICATION_JSON })
	    @Path("/allUsers/email/{email}")
	    public Response findUserByEmail(@PathParam("email") String email) {
	    	System.out.println("email "+email);
	    	User user = userDao.getUserByEmail(email);
	        return Response.status(200).entity(user).build();
	    }
	   
	    @POST
	    @Path("/allUsers/add/")
	    @Produces({ MediaType.APPLICATION_JSON })
	    public Response saveUser(final User user) {
	    	System.out.println("USER\n\n"+user.toString()+"\n\n");
	    	userDao.save(user);
	        return Response.status(201).entity(user).build();
	    }
	    
	    @PUT 
	    @Path("/allUsers/edit/{id}")
		@Consumes({ MediaType.APPLICATION_JSON })
		public Response updateUser(final User user) {
	    	userDao.update(user);
			return Response.status(200).entity(user).build();
		}
	    
	    @DELETE
	    @Path("/allUsers/delete/{id}")
	    public Response deleteUser(@PathParam("id") int id){
	    	userDao.delete(id);
	    	return Response.status(204).build();
	    }
	    
}
