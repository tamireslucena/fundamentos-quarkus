package io.github.tamireslucena;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import io.github.tamireslucena.dto.CreateUserRequest;

@Path("/users")
public class UserResource {
    
    @POST
    public Response createUser(CreateUserRequest userRequest) {
        return Response.ok().build();
    }
    
}
