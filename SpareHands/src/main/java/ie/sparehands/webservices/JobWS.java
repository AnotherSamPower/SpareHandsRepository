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

import ie.sparehands.daos.JobDAO;
import ie.sparehands.entities.Job;
import ie.sparehands.entities.User;

@Path("/job")
@Stateless
@LocalBean
public class JobWS {

	 @EJB
	    private JobDAO jobDao;

	    @GET 
	    @Path("/allJobs")
	    @Produces({ MediaType.APPLICATION_JSON})
	    public Response findAll() {
	    	System.out.println("Get all jobs");
	    	final List<Job> jobs = jobDao.getAllJobs();
	        return Response.status(200).entity(jobs).build();
	    }	   
	  
	    @GET
		@Path("/allJobs/{id}")
		@Produces({ MediaType.APPLICATION_JSON })
		public Response findJobById(@PathParam("id") int id) {
			final Job job = jobDao.getJob(id);
			return Response.status(200).entity(job).build();
		}
	    
	    @POST
	    @Path("/addJob")
	    @Produces({ MediaType.APPLICATION_JSON })
	    public Response saveJob(final Job job) {
	    	jobDao.save(job);
	        return Response.status(201).entity(job).build();
	    }
	    
	    @PUT @Path("/editJob/{id}")
		@Consumes({ MediaType.APPLICATION_JSON })
		public Response updateJob(Job job) {
	    	jobDao.update(job);
			return Response.status(200).entity(job).build();
		}
	    
	    @DELETE
	    @Path("/deleteJob/{id}")
	    public Response deleteJob(@PathParam("id") int id){
	    	System.out.println("job " + id + " deleted");
	    	jobDao.delete(id);
	    	return Response.status(204).build();
	    }
	    
	    @GET
	    @Path("/getAllJobsByOwnerId/{id}")
	    public Response getAllJobsByOwnerId(@PathParam("id") int id){
	    	jobDao.getAllByOwnerId(id);
	    	return Response.status(204).build();
	    }
	    
}