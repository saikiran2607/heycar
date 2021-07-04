package com.heycar.heycardealerslisting.web;

import com.heycar.heycardealerslisting.domain.entity.Listing;
import com.heycar.heycardealerslisting.domain.error.ApiError;
import com.heycar.heycardealerslisting.logging.LogBuilder;
import com.heycar.heycardealerslisting.logging.Markers;
import com.heycar.heycardealerslisting.services.ListingService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
public class ListingController {
    private static final Integer DEFAULT_OFFSET = 0;
    private static final Integer DEFAULT_LIMIT = 20;

    private final Logger logger = LoggerFactory.getLogger(ListingController.class);

    @Autowired
    private LogBuilder log;

    @Autowired
    private ListingService listingService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity search(
            @RequestParam(required = false) String make,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) Long year,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) Integer offset,
            @RequestParam(required = false) Integer limit
    ) {
        log
                .withLogger(logger)
                .withMarker(Markers.METHOD_START())
                .withDate(new Date())
                .withMethod("search")
                .withParameter("make", make)
                .withParameter("model", model)
                .withParameter("year", year)
                .withParameter("color", color)
                .info();
        try {
            offset = offset == null ? DEFAULT_OFFSET : offset;
            limit = limit == null ? DEFAULT_LIMIT : limit;

            Page<Listing> listings = listingService.search(make, model, year, color, offset, limit);

            log
                    .withLogger(logger)
                    .withMarker(Markers.METHOD_END())
                    .withDate(new Date())
                    .withMethod("search")
                    .withParameter("make", make)
                    .withParameter("model", model)
                    .withParameter("year", year)
                    .withParameter("color", color)
                    .info();

            return new ResponseEntity(listings, HttpStatus.OK);
        } catch (Exception e) {
            log
                    .withLogger(logger)
                    .withMarker(Markers.METHOD_END())
                    .withDate(new Date())
                    .withMethod("search")
                    .withParameter("make", make)
                    .withParameter("model", model)
                    .withParameter("year", year)
                    .withParameter("color", color)
                    .error();

            ApiError error = ApiError.fromException(e);
            return new ResponseEntity(error, new HttpHeaders(), error.getStatus());
        }
    }
}
