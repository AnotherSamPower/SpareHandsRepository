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

import ie.sparehands.daos.ApplicantDAO;
import ie.sparehands.entities.Applicant;
import ie.sparehands.entities.Job;
import ie.sparehands.entities.User;

@Path("/applications")
@Stateless
@LocalBean
public class ApplicantWS {

	@EJB
	private ApplicantDAO applicantDao;

	@GET
	@Path("/allApplications")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findAll() {
		System.out.println("Get all applicants");
		final List<Applicant> applicants = applicantDao.getAllApplicants();
		return Response.status(200).entity(applicants).build();
	}

	@GET
	@Path("/allApplications/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findApplicantById(@PathParam("id") int id) {
		final Applicant applicant = applicantDao.getApplicant(id);
		return Response.status(200).entity(applicant).build();
	}
	
	@GET
	@Path("/allApplications/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findApplicationsByApplicantId(@PathParam("id") int id) {
		final List<Applicant> applicants = applicantDao.getApplicationsByApplicantId(id);
		return Response.status(200).entity(applicants).build();
	}
	
	@GET
	@Path("/allApplications/owner/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findApplicationsByOwnerId(@PathParam("id") int id) {
		final List<Applicant> applicants = applicantDao.findApplicationsByOwnerId(id);
		return Response.status(200).entity(applicants).build();
	}
	
	@GET
	@Path("/allApplications/job/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findApplicationsByJobId(@PathParam("id") int id) {
		final List<Applicant> applicants = applicantDao.findApplicationsByJobId(id);
		return Response.status(200).entity(applicants).build();
	}
	
	@POST
	@Path("/allApplications/add")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response saveApplicant(final Applicant applicant) {
		applicantDao.save(applicant);
		return Response.status(201).entity(applicant).build();
	}

	@PUT 
	@Path("/allApplications/edit/{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Response updateApplicant(Applicant applicant) {
		applicantDao.update(applicant);
		return Response.status(200).entity(applicant).build();
	}

	@DELETE
	@Path("/allApplications/delete/{id}")
	public Response deleteApplicant(@PathParam("id") int id) {
		System.out.println("applicant " + id + " deleted");
		applicantDao.delete(id);
		return Response.status(204).build();
	}

}
