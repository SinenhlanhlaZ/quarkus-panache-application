package za.co.zamambo.Resource;

import za.co.zamambo.Entity.Address;
import za.co.zamambo.Entity.User;
import za.co.zamambo.Repository.UserRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserRepository userRepo;

    @GET
    public Response getUsers()
    {
        //get users from a list then return an ok response
        List<User> userList = userRepo.listAll();
        return Response.ok(userList).build();
    }

    @GET
    @Path("{id}")
    public Response getByUserId(@PathParam("id") Long id)
    {
        //find the given id and if id is found, return ok, or else return NOT FOUND
        return userRepo
                .findByIdOptional(id)
                .map(user -> Response.ok(user).build())
                .orElse(Response.status(NOT_FOUND).build());
    }

    @POST
    @Transactional
    public Response create(User user)
    {
        //Assigning FK to address object
        List<Address> addressList = new ArrayList<>();
        for(Address address: user.getAddressList()) {
            address.setUser(user);
            addressList.add(address);
        }

        user.setAddressList(addressList);
        userRepo.persist(user);
        if(userRepo.isPersistent(user))
        {
            URI createUser = URI.create("/user/" + user.getUserId());
            return Response.created(createUser).build();
        }
        return Response.status(NOT_FOUND).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteById(@PathParam("id") Long id)
    {
        boolean deleted = userRepo.deleteById(id);
        return deleted ? Response.ok().build() : Response.status(BAD_REQUEST).build();
    }

}


/*@GET
    @Path("lastname/{lastname}")
    public Response getByLastName(@PathParam("lastname") String lastname)
    {
        //returning users with the given last name
        List<User> users = userRepo.findByLastName(lastname);
        return Response.ok(users).build();
    }*/
