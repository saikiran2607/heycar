package com.heycar.heycardealerslisting.services;

import com.heycar.heycardealerslisting.domain.entity.Listing;
import com.heycar.heycardealerslisting.domain.repository.ListingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ListingService {

    private final ListingRepository listingRepository;

    @Autowired
    public ListingService(ListingRepository listingRepository) {
        this.listingRepository = listingRepository;
    }

    public Page<Listing> search(String make, String model, Long year, String color, int offset, int limit) {
        Listing example = new Listing();
        if (make != null && !make.isEmpty()) {
            example.setMake(make);
        }
        if (model != null && !model.isEmpty()) {
            example.setModel(model);
        }
        if (year != null) {
            example.setYear(year);
        }
        if (color != null && !color.isEmpty()) {
            example.setColor(color);
        }

        Pageable pageable = PageRequest.of(offset, limit);
        return listingRepository.findAll(Example.of(example), pageable);
    }
}
