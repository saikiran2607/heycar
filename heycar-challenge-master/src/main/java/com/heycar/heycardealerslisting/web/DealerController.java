package com.heycar.heycardealerslisting.web;

import com.heycar.heycardealerslisting.domain.dto.CsvListings;
import com.heycar.heycardealerslisting.domain.entity.Dealer;
import com.heycar.heycardealerslisting.domain.entity.Listing;
import com.heycar.heycardealerslisting.domain.error.ApiError;
import com.heycar.heycardealerslisting.logging.LogBuilder;
import com.heycar.heycardealerslisting.logging.Markers;
import com.heycar.heycardealerslisting.mapping.ListingMapper;
import com.heycar.heycardealerslisting.services.DealerService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DealerController {
    private Logger logger = LoggerFactory.getLogger(DealerController.class);

    @Autowired
    private LogBuilder log;

    @Autowired
    private DealerService dealerService;

    @Autowired
    private ListingMapper listingMapper;

    @RequestMapping(value = "/vehicle_listings/{dealerId}", method = RequestMethod.POST)
    public ResponseEntity updateListingsJson(@PathVariable Long dealerId, @RequestBody List<Listing> listings) {
        log
                .withLogger(logger)
                .withMarker(Markers.METHOD_START())
                .withDate(new Date())
                .withMethod("updateListingsJson")
                .withParameter("dealerId", dealerId)
                .withParameter("listings", listings)
                .info();
    try {
            Dealer dealer = dealerService.updateListings(dealerId, listings);

            log
                    .withLogger(logger)
                    .withMarker(Markers.METHOD_END())
                    .withDate(new Date())
                    .withMethod("updateListingsJson")
                    .withParameter("dealerId", dealerId)
                    .withParameter("listings", listings)
                    .info();

            return new ResponseEntity(dealer, HttpStatus.OK);
        } catch (Exception e) {
            log
                    .withLogger(logger)
                    .withMarker(Markers.METHOD_END())
                    .withDate(new Date())
                    .withMethod("updateListingsJson")
                    .withParameter("dealerId", dealerId)
                    .withParameter("listings", listings)
                    .withError(e)
                    .error();

            ApiError error = ApiError.fromException(e);
            return new ResponseEntity<Object>(error, new HttpHeaders(), error.getStatus());
        }
    }


    @RequestMapping(value = "/upload_csv/{dealerId}", method = RequestMethod.POST)
    public ResponseEntity updateListingsCsv(@PathVariable Long dealerId, @RequestBody CsvListings csvListings) {
        try {
            log
                    .withLogger(logger)
                    .withMarker(Markers.METHOD_START())
                    .withDate(new Date())
                    .withMethod("updateListingsCsv")
                    .withParameter("dealerId", dealerId)
                    .withParameter("csvListings", csvListings)
                    .info();

            List<Listing> mappedListings = csvListings.getCsvListings().stream().map(csvListing -> listingMapper.map(csvListing)).collect(Collectors.toList());
            Dealer dealer = dealerService.updateListings(dealerId, mappedListings);

            log
                    .withLogger(logger)
                    .withMarker(Markers.METHOD_END())
                    .withDate(new Date())
                    .withMethod("updateListingsCsv")
                    .withParameter("dealerId", dealerId)
                    .withParameter("heycarchallenge", mappedListings)
                    .info();

            return new ResponseEntity(dealer, HttpStatus.OK);
        } catch (Exception e) {
            log
                    .withLogger(logger)
                    .withMarker(Markers.METHOD_END())
                    .withDate(new Date())
                    .withMethod("updateListingsCsv")
                    .withParameter("dealerId", dealerId)
                    .withError(e)
                    .error();

            ApiError error = ApiError.fromException(e);
            return new ResponseEntity(error, new HttpHeaders(), error.getStatus());
        }
    }
}
