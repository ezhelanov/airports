package sber.data.airports.controllers.payload.request;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@ToString
@Getter
public class AirportRequest {

    @NotNull
    private UUID uuid;

    @NotBlank
    private String currentThreadName;

    @NotNull
    private Integer id;

    @NotNull
    private Date timestamp;

    @JsonSetter("uuid")
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @JsonSetter("currentThreadName")
    public void setCurrentThreadName(String currentThreadName) {
        this.currentThreadName = currentThreadName;
    }

    @JsonSetter("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonSetter("timestamp")
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
