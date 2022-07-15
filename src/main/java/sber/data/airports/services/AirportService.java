package sber.data.airports.services;

import sber.data.airports.controllers.payload.request.AirportRequest;
import sber.data.airports.controllers.payload.response.AirportResponse;

public interface AirportService {

    AirportResponse getAirportResponse(AirportRequest airportRequest);
}
