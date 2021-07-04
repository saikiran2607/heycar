package com.heycar.heycardealerslisting.converting;

import com.heycar.heycardealerslisting.domain.dto.CsvListing;
import com.heycar.heycardealerslisting.domain.dto.CsvListings;
import com.heycar.heycardealerslisting.mapping.CustomCsvMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class CsvListingsConverter extends AbstractHttpMessageConverter<CsvListings> {
    public static final MediaType MEDIA_TYPE = new MediaType("text", "csv", Charset.forName("utf-8"));

    private CustomCsvMapper<CsvListing> customCsvMapper;

    @Autowired
    public CsvListingsConverter(CustomCsvMapper<CsvListing> customCsvMapper){
        super(MEDIA_TYPE);
        this.customCsvMapper = customCsvMapper;
    }

    @Override
    protected boolean supports(Class<?> aClass) {
        return CsvListings.class.equals(aClass);
    }

    @Override
    protected CsvListings readInternal(Class<? extends CsvListings> aClass, HttpInputMessage httpInputMessage) throws IOException, HttpMessageNotReadableException {
        List<CsvListing> listings = customCsvMapper.map(httpInputMessage.getBody());
        CsvListings result = new CsvListings();
        result.setCsvListings(listings);
        return result;
    }

    @Override
    protected void writeInternal(CsvListings csvListings, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
    }
}
