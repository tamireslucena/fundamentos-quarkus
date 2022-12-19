package rest;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.github.tamireslucena.domain.model.User;
import io.github.tamireslucena.domain.repository.UserRepository;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import rest.dto.CreateUserRequest;
import rest.dto.ResponseError;

import java.util.Set;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private UserRepository repository;
    private Validator validator;

    @Inject
    public UserResource(UserRepository repository, Validator validator){
        this.repository = repository;
        this.validator = validator;
    }
    
    @POST
    @Transactional
    public Response createUser(CreateUserRequest userRequest) {
         Set<ConstraintViolation<CreateUserRequest>> violations = validator.validate(userRequest);

        if(!violations.isEmpty()){
            ResponseError responseError = ResponseError.createFromValidation(violations);
            return Response.status(400).entity(responseError).build();
        }
        User user = new User();
        user.setAge(userRequest.getAge());
        user.setName(userRequest.getName());

        repository.persist(user);

        return Response.ok(user).build();
    }

    @GET
    public  Response listAllUsers() {
        PanacheQuery<User> allUsers = repository.findAll();
        return Response.ok(allUsers.list()).build();
    }

    @Path("{id}")
    @Transactional
    @PUT
    public Response updateUser(@PathParam("id") Long id, CreateUserRequest userData){
        User user = repository.findById(id);

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
        User user = repository.findById(id);

        if(user != null){
            repository.delete(user);
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


