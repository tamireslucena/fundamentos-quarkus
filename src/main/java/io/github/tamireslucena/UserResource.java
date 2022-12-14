package io.github.tamireslucena;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.github.tamireslucena.dto.CreateUserRequest;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON) // consumir json
@Produces(MediaType.APPLICATION_JSON) // retornar json
public class UserResource {
    
    @POST
    public Response createUser(CreateUserRequest userRequest) {
        return Response.ok(userRequest).build();
    }

    @GET
    public  Response listAllUsers(){
        return Response.ok().build();
    }
}
