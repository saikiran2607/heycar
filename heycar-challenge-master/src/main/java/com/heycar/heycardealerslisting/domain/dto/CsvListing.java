package com.heycar.heycardealerslisting.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CsvListing {
    private String code;
    @JsonProperty("make/model")
    private CsvMakeModel makeModel;
    @JsonProperty("power-in-ps")
    private String kiloWatts;
    private String year;
    private String color;
    private String price;
}
