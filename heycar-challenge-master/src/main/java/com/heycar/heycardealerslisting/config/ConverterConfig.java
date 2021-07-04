package com.heycar.heycardealerslisting.config;

import com.heycar.heycardealerslisting.converting.CsvListingsConverter;
import com.heycar.heycardealerslisting.domain.dto.CsvListing;
import com.heycar.heycardealerslisting.domain.dto.CsvListings;
import com.heycar.heycardealerslisting.mapping.CustomCsvMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
/**
 * This class is used to Make CsvListings As spring Bean.
 * @author Saikiran kuyya
 *
 */
@Configuration
public class ConverterConfig {
    @Bean
    public HttpMessageConverter<CsvListings> createCustomCsvConverter(CustomCsvMapper<CsvListing> customCsvMapper) {
        return new CsvListingsConverter(customCsvMapper);
    }
}
