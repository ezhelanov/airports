package sber.data.airports.controllers.payload.response;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@Builder
@Getter
public class AirportResponse {

    private String[] airportInfo;

    private UUID responseUuid;

    private UUID requestUuid;

    private Date requestTimestamp;

    private Date responseTimestamp;
}
