package ru.leonid.shutov.javaee.controller;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("calculateVolume")
public class MyResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String calcVolume(String json) {
        System.out.println(json);
        return "Received:" + json;
    }
}
