package com.db.lab5.controller;

import com.db.lab5.constants.RedisConstants;
import com.db.lab5.service.EventHubService;
import com.db.lab5.utils.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    private final EventHubService eventHubService;

    @Autowired
    public Controller(EventHubService eventHubService) {
        this.eventHubService = eventHubService;
    }

    @Value("${LOG_PRINT_IN}")
    private String LOG_PRINT_IN;

    @GetMapping(value = "/api/data")
    public String getDataFromApi() {
        final String fileName = "File 1";

        RedisUtil redisUtil = new RedisUtil();
        redisUtil.startConnection();

        redisUtil.addValueToRedis(fileName, RedisConstants.READ_STATUS, RedisConstants.STARTED);
        ApiUtil apiUtil = new ApiUtil();

        int readingStep = 100;
        int maxRows = 200;
        for (int i = 0; i <= maxRows; i += readingStep) {
            String apiUrl = "https://data.cityofchicago.org/resource/f7f2-ggz5.json?$limit=100&$offset=" + i;
            JSONArray arrayDataFromApi = apiUtil.getJsonArrayFromApi(apiUrl);

            for (int n = 0; n < arrayDataFromApi.length(); n++) {

                String log = null;
                try {
                    log = arrayDataFromApi.getJSONObject(n).toString();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (LOG_PRINT_IN != null && LOG_PRINT_IN.equals("Event-hub")) {
                    eventHubService.sendEvent(log);
                } else {
                    Context context = new Context(new Logger());
                    context.executeLogStrategy(log);
                }
            }

            if(i != 0){
                redisUtil.addValueToRedis(fileName, RedisConstants.WRITTEN_ROWS,
                        redisUtil.getValueFromRedis(fileName, RedisConstants.WRITTEN_ROWS)
                                + (i - (readingStep - 1)) + "-" + i + ", ");
            }

        }

        redisUtil.addValueToRedis(fileName, RedisConstants.READ_STATUS, RedisConstants.COMPLETED);

        redisUtil.closeConnection();

        return "{ \"success\" : true }";
    }
}