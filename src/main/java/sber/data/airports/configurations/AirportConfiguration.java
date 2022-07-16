package sber.data.airports.configurations;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Log4j2
public class AirportConfiguration {

    @Bean
    public SimpleDateFormat simpleDateFormat() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
    }

    @Bean
    public Map<Integer, String[]> mapOfAirports(@Value("classpath:airports.dat") Resource resource) {

        Map<Integer, String[]> map = new HashMap<>();

        if (resource.exists() && resource.isFile() && resource.isReadable()) {

            try (
                    CSVReader csvReader = new CSVReader(new InputStreamReader(resource.getInputStream()))
            ) {

                for (String[] props : csvReader.readAll()) {
                    map.put(Integer.valueOf(props[0]), props);
                }

                log.info("Airports loaded successfully !");

            } catch (IOException | CsvException e) {
                log.error("Exception during file reading", e);
            }

        } else {
            log.error("Cannot read from file");
        }

        return map;
    }

}
