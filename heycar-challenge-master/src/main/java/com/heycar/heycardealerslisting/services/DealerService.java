package com.heycar.heycardealerslisting.services;

import com.heycar.heycardealerslisting.domain.entity.Dealer;
import com.heycar.heycardealerslisting.domain.entity.Listing;
import com.heycar.heycardealerslisting.domain.error.NonExistentDealerException;
import com.heycar.heycardealerslisting.domain.repository.DealerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class DealerService {

    private final DealerRepository dealerRepository;

    @Autowired
    public DealerService(DealerRepository dealerRepository) {
        this.dealerRepository = dealerRepository;
    }
    /**
     * This method is used to Update New Listing of an Dealer
     * @param dealerId Long
     * @param listings List<Listing>
     * @return Dealer
     */
    public Dealer updateListings(Long dealerId, List<Listing> listings) {
        Optional<Dealer> possibleDealer = dealerRepository.findById(dealerId);
        if (!possibleDealer.isPresent()) {
            throw new NonExistentDealerException(dealerId);
        }

        Dealer dealer = possibleDealer.get();
        listings.forEach(listing -> createOrUpdate(dealer, listing));

        dealerRepository.saveAndFlush(dealer);

        return dealer;
    }
    /**
     * This method is used to Update the Existing Listing Or Create New Listing by an Dealer
     * @param dealer Dealer
     * @param listing Listing
     */
    private void createOrUpdate(Dealer dealer, Listing listing) {
        List<Listing> newListings = dealer.getListings();
        Stream<Listing> dealerListingsStream = newListings.stream();
        Optional<Listing> possibleListing = dealerListingsStream.filter(dealerListing -> dealerListing.getCode().equals(listing.getCode())).findFirst();
        if (possibleListing.isPresent()) {
            Listing existingListing = possibleListing.get();
            existingListing.setCode(listing.getCode());
            existingListing.setColor(listing.getColor());
            existingListing.setKiloWatts(listing.getKiloWatts());
            existingListing.setMake(listing.getMake());
            existingListing.setModel(listing.getModel());
            existingListing.setYear(listing.getYear());
            existingListing.setPrice(listing.getPrice());
            existingListing.setDealer(dealer);
        } else {
            listing.setDealer(dealer);
            newListings.add(listing);
        }
    }
}
