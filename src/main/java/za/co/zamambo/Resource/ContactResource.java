package za.co.zamambo.Resource;


import za.co.zamambo.Entity.Contact;
import za.co.zamambo.Repository.ContactRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/contacts/v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ContactResource {

   @Inject
    ContactRepository contactRepo;

   @POST
   @Transactional
    public Response create(Contact contact) {
        try{
            contactRepo.persist(contact);
            if(contactRepo.isPersistent(contact)) {
                return Response.created(URI.create("created")).build();
            }
        }catch (Exception e) {

        }
        return Response.serverError().build();
    }

    @GET
    public Response listAll() {
       return Response.ok().entity(contactRepo.listAll()).build();
    }

    @GET
    @Path("{id}")
    public Response listById(@PathParam("id") Long id) {
       return contactRepo.findByIdOptional(id)
               .map(obj -> Response.ok(obj).build())
               .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

}
