package ru.leonid.shutov.javaee.controller;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.leonid.shutov.javaee.service.FillWaterService;
import ru.leonid.shutov.javaee.service.FillWaterServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("calculateVolume")
public class MainController {
    private static Logger logger = Logger.getLogger(MainController.class);

    /**
     * TODO write javadoc
     *
     * @param json
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response calcVolume(String json) {
        logger.debug("Received input: " + json);

        JSONObject obj = new JSONObject(json);
        JSONArray array = obj.optJSONArray("numbers");

        if (array == null) {
            logger.error("Null array received");
            return Response.serverError().entity("Null array received").build();
        }
        if (array.length() < 2) {
            logger.error("Array is too short.");
            return Response.serverError().entity("Array is too short. Please provide at least 2 values").build();
        }
        int[] numbers = new int[array.length()];

        try {
            for (int i = 0; i < array.length(); ++i) {
                numbers[i] = array.getInt(i);
            }
        } catch (JSONException e) {
            logger.error("Cannot parse array", e);
            return Response.serverError().entity("Cannot parse array. Please provide an integers array").build();
        }
        FillWaterService service = new FillWaterServiceImpl();
        final int volume = service.fillWater(numbers);

        return Response.ok(String.valueOf(volume), MediaType.APPLICATION_JSON).build();
    }
}
