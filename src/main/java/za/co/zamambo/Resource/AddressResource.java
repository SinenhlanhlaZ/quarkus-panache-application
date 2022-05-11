package za.co.zamambo.Resource;

import za.co.zamambo.Entity.Address;
import za.co.zamambo.Repository.AddressRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/addresses/v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AddressResource {

    @Inject
    AddressRepository addressRepo;

    @GET
    public Response listAll()
    {
        return Response.ok().entity(addressRepo.listAll()).build();
    }

    @POST
    @Transactional
    public Response createAddress(Address address)
    {
        addressRepo.persist(address);
        return addressRepo.isPersistent(address)
                ? Response.created(URI.create("created address")).build() : Response.status(Response.Status.BAD_REQUEST).build();
    }

    @GET
    @Path("{id}")
    public Response listById(@PathParam("id") Long id)
    {
        return addressRepo.findByIdOptional(id)
                .map(obj -> Response.ok(obj).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }
}
