package com.heycar.heycardealerslisting.config;

import com.heycar.heycardealerslisting.domain.dto.CsvListing;
import com.heycar.heycardealerslisting.mapping.CustomCsvMapper;
import com.heycar.heycardealerslisting.mapping.ListingMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * This class is used for Making Mappers classes as a Spring Bean.
 * @author Saikiran kuyya
 *
 */
@Configuration
public class MappingConfig {
    @Bean
    public CustomCsvMapper<CsvListing> customCsvMapper(){
        return new CustomCsvMapper<>(CsvListing.class);
    }

    @Bean
    public ListingMapper listingMapper(){
        return ListingMapper.INSTANCE;
    }
}
