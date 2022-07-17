package sber.data.airports.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sber.data.airports.controllers.payload.request.AirportRequest;
import sber.data.airports.controllers.payload.response.AirportResponse;
import sber.data.airports.services.AirportService;

import javax.validation.Valid;

@RestController
@RequestMapping("/airports")
@Log4j2
@AllArgsConstructor
public class AirportsController {

    private final AirportService airportService;

    @PostMapping(path = "/airport", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AirportResponse> airportRequest(
            @RequestBody @Valid AirportRequest airportRequest, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.error("Invalid Request !");
            return ResponseEntity.badRequest().body(
                    AirportResponse.builder().airportInfo(new String[]{}).build()
            );
        }

        log.info(airportRequest);

        return ResponseEntity.ok(airportService.getAirportResponse(airportRequest));
    }

}
