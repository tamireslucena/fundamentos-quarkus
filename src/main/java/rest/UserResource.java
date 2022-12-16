package rest;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.github.tamireslucena.domain.model.User;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import rest.dto.CreateUserRequest;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON) // consumir json
@Produces(MediaType.APPLICATION_JSON) // retornar json
public class UserResource {
    
    @POST
    @Transactional
    public Response createUser(CreateUserRequest userRequest) {

        User user = new User();
        user.setAge(userRequest.getAge());
        user.setName(userRequest.getName());

        user.persist();

        return Response.ok(user).build();
    }

    @GET
    public  Response listAllUsers() {
        PanacheQuery<PanacheEntityBase> allUsers = User.findAll();
        return Response.ok(allUsers.list()).build();
    }

    @Path("{id}")
    @Transactional
    @PUT
    public Response updateUser(@PathParam("id") Long id, CreateUserRequest userData){
        User user = User.findById(id);

        if(user != null){
          user.setName(userData.getName());
          user.setAge(userData.getAge());
          return Response.ok(user).build();
        }
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteUser(@PathParam("id") Long id){
        User user = User.findById(id);

        if(user != null){
            user.delete();
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}





/*
------------- ANOTAÇÕES -------------

    @Consumes(MediaType.APPLICATION_JSON) // consumir json
    @Produces(MediaType.APPLICATION_JSON) // retornar json

 */


