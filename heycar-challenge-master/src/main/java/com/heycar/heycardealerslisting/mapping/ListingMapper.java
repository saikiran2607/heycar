package com.heycar.heycardealerslisting.mapping;

import com.heycar.heycardealerslisting.domain.dto.CsvListing;
import com.heycar.heycardealerslisting.domain.entity.Listing;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ListingMapper {
    ListingMapper INSTANCE = Mappers.getMapper(ListingMapper.class);

    @Mapping(source = "csvListing.makeModel.make", target = "make")
    @Mapping(source = "csvListing.makeModel.model", target = "model")
    Listing map(CsvListing csvListing);
}
