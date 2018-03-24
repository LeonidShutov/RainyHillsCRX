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
     * main controller method.
     * receives an array "numbers" in JSON, parse it, calls service for calculating volume, provides answer
     *
     * @param json - input array.
     *             examples of correct input:
     *             {"numbers":[9999999,0,2]}
     *             {"numbers":[1,0]}
     *             <p>
     *             examples of incorrect input:
     *             {"numbers":["asd",0,2]} - not integer value in 0 position
     *             {"numbers":[9999999999999,0,2]} - not integer value in 0 position
     *             {"numbers":[5]} - array should contain at least 2 values
     *             {"numbers":""} - this isn't an array
     *             {"NOT_NUMBERS":[9999999,0,2]}  - array "numbers" isn't provided
     * @return HTTP code 200 and calculated volume, remained after water in case of correct input,
     * HTTP code 500 and reason, in case of incorrect input
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response calcVolume(String json) {
        logger.debug("Received input: " + json);

        JSONArray array;
        try {
            JSONObject obj = new JSONObject(json);
            array = obj.optJSONArray("numbers");
        } catch (JSONException e) {
            logger.error("Cannot parse JSON", e);
            return Response.serverError().entity("Incorrect JSON received. Please provide valid JSON").build();
        }

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
        final long volume = service.fillWater(numbers);
        logger.debug("Answer calculated. Result is: " + volume);
        return Response.ok(String.valueOf(volume), MediaType.APPLICATION_JSON).build();
    }
}
