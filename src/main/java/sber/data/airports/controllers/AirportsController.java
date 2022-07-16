package sber.data.airports.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sber.data.airports.controllers.payload.request.AirportRequest;
import sber.data.airports.controllers.payload.response.AirportResponse;
import sber.data.airports.services.AirportService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/airports")
@Log4j2
@AllArgsConstructor
public class AirportsController {

    private final AirportService airportService;

    private final SimpleDateFormat simpleDateFormat;

    @PostMapping(path = "/airport", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AirportResponse> airportRequest(@RequestBody Map<String, Object> map) {

        AirportRequest airportRequest = new AirportRequest();

        try {

            airportRequest.setId((Integer) map.get("id"));
            airportRequest.setTimestamp(simpleDateFormat.parse((String) map.get("timestamp")));
            airportRequest.setCurrentThreadName((String) map.get("currentThreadName"));
            airportRequest.setUuid(UUID.fromString((String) map.get("uuid")));

        } catch (ClassCastException | ParseException e) {

            log.error("Request is not valid", e);

            return ResponseEntity.badRequest().body(
                    AirportResponse.builder().airportInfo(new String[]{}).build()
            );

        }

        log.info(airportRequest);

        return ResponseEntity.ok(airportService.getAirportResponse(airportRequest));
    }

}
