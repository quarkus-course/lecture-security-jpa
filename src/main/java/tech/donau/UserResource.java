package tech.donau;

import io.quarkus.runtime.StartupEvent;
import tech.donau.data.User;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.event.Observes;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;

@Path("/greeting")
public class UserResource {
    @Transactional
    public void onStart(@Observes StartupEvent event) {
        User.add("admin", "admin", "Admin,User");
        User.add("user", "user", "User");
    }

    @GET
    @RolesAllowed("User")
    public String getInfo(@Context SecurityContext context) {
        return "Hello " + context.getUserPrincipal().getName();
    }

    @GET
    @Path("/admin")
    @RolesAllowed("Admin")
    public String admin(@Context SecurityContext context) {
        return "admin secret";
    }
}
