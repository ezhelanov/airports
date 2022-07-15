package sber.data.airports.controllers.payload.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@ToString
public class AirportRequest {

    @NotNull
    private UUID uuid;

    @NotBlank
    private String currentThreadName;

    @NotNull
    private Integer id;

    @NotNull
    private Date timestamp;
}
