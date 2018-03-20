package ru.leonid.shutov.javaee.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import ru.leonid.shutov.javaee.service.FillWaterService;
import ru.leonid.shutov.javaee.service.FillWaterServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("calculateVolume")
public class MyResource {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String calcVolume(String json) {
        JSONObject obj = new JSONObject(json);
//TODO return something when can't parse array
        JSONArray array = obj.optJSONArray("numbers");

        if (array == null) { /*TODO check array validity: length, values*/ }

        int[] numbers = new int[array.length()];

        for (int i = 0; i < array.length(); ++i) {
            numbers[i] = array.optInt(i);
        }
        FillWaterService service = new FillWaterServiceImpl();
        final int volume = service.fillWater(numbers);

        return String.valueOf(volume);
    }
}
