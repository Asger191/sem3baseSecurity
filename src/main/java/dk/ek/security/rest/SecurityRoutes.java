package dk.ek.security.rest;

import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.*;

public class SecurityRoutes {
    ISecurityController securityController = new ISecurityController();
    public EndpointGroup getSecurityRoutes = () ->{
            path("/auth", () -> {
                post("/login", securityController.login());
            });

    };
}
