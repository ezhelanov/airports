package sber.data.airports.configurations;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Log4j2
public class AirportConfiguration {

    @Bean
    public Map<Integer, String[]> mapOfAirports() {

        Map<Integer, String[]> map = new HashMap<>();

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("airports.dat");

        if (inputStream != null) {

            try (
                    CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream))
            ) {

                for (String[] props : csvReader.readAll()) {
                    map.put(Integer.valueOf(props[0]), props);
                }

                log.info("Airports loaded successfully !");

            } catch (IOException | CsvException e) {
                log.error("Exception during file reading", e);
            }

        } else {
            log.error("Cannot access file");
        }

        return map;
    }

}
