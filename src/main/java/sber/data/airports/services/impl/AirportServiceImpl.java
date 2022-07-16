package sber.data.airports.services.impl;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import sber.data.airports.controllers.payload.request.AirportRequest;
import sber.data.airports.controllers.payload.response.AirportResponse;
import sber.data.airports.services.AirportService;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Component
@Log4j2
@AllArgsConstructor
public class AirportServiceImpl implements AirportService {

    private static final String RESPONSE = "RESPONSE ";

    private final Map<Integer, String[]> airports;

    @Override
    public AirportResponse getAirportResponse(AirportRequest airportRequest) {

        Date responseTimeStamp = new Date();

        return AirportResponse.builder()
                .airportInfo(getAirportInfo(airportRequest.getId()))
                .requestTimestamp(airportRequest.getTimestamp())
                .responseTimestamp(responseTimeStamp)
                .requestUuid(airportRequest.getUuid())
                .responseUuid(generateUuid(responseTimeStamp))
                .build();
    }

    private String[] getAirportInfo(Integer id){
        String[] airportInfo = airports.get(id);
        return airportInfo == null ? new String[]{} : airportInfo;
    }

    private UUID generateUuid(Date timestamp){

        String stringForUuid = RESPONSE
                .concat(Thread.currentThread().getName())
                .concat(" ")
                .concat(String.valueOf(timestamp.getTime()));
        log.trace("String for UUID: \"{}\"", stringForUuid);

        UUID generatedUuid = UUID.nameUUIDFromBytes(stringForUuid.getBytes(StandardCharsets.UTF_8));
        log.trace("Generated UUID for response: \"{}\"", generatedUuid);

        return generatedUuid;
    }
}
