package com.heycar.heycardealerslisting.services;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.heycar.heycardealerslisting.domain.entity.Dealer;
import com.heycar.heycardealerslisting.domain.entity.Listing;
import com.heycar.heycardealerslisting.domain.error.NonExistentDealerException;
import com.heycar.heycardealerslisting.domain.repository.DealerRepository;

@RunWith(MockitoJUnitRunner.class)
public class DealerServiceTest {
    private Long dealerId = 1L;

    private Dealer dealer;

    private List<Listing> listings;

    private Listing listing;

    @Mock
    private DealerRepository dealerRepository;

    @InjectMocks
    private DealerService dealerService;

    @Before
    public void setup() {
        listings = new ArrayList<>();

        listing = new Listing();
        listing.setPrice(1L);
        listing.setYear(2014L);
        listing.setModel("Daimer1");
        listing.setMake("Daimer");
        listing.setKiloWatts(140L);
        listing.setColor("Blue");
        listing.setDealer(dealer);
        listing.setCode("code");
        listings.add(listing);

        dealer = new Dealer();
        dealer.setId(dealerId);
        dealer.setListings(listings);

        Optional<Dealer> possibleDealer = Optional.of(dealer);

        when(dealerRepository.findById(eq(dealerId))).thenReturn(possibleDealer);
    }

    @Test(expected = NonExistentDealerException.class)
    public void updateListings_withNonExistantDealer_shouldThrowError(){
        Long nonExistentDealerId = 999L;

        dealerService.updateListings(nonExistentDealerId, new ArrayList<>());
    }

    @Test
    public void updateNewListing() {
        Listing newListing = new Listing();
        List<Listing> newListings = Arrays.asList(newListing);

        dealerService.updateListings(dealerId, newListings);

        List<Listing> expectedListings = Arrays.asList(listing, newListing);
        Assert.assertEquals(expectedListings, dealer.getListings());

        verify(dealerRepository).saveAndFlush(eq(dealer));
    }

    @Test
    public void updateCurrentListing(){
        Listing updatedListing = new Listing();
        updatedListing.setCode(listing.getCode());
        updatedListing.setPrice(1L);
        updatedListing.setYear(2021L);
        updatedListing.setModel("NotAFigo");
        updatedListing.setMake("NotAFord");
        updatedListing.setKiloWatts(140L);
        updatedListing.setColor("Blue");
        updatedListing.setDealer(dealer);
        List<Listing> newListings = Arrays.asList(updatedListing);

        dealerService.updateListings(dealerId, newListings);

        List<Listing> expectedListings = Arrays.asList(updatedListing);
        Assert.assertEquals(expectedListings, dealer.getListings());

        verify(dealerRepository).saveAndFlush(eq(dealer));
    }
}
