package com.heycar.heycardealerslisting.mapping;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CustomCsvMapper<T> {

    private ObjectReader objectReader;

    public CustomCsvMapper(Class<T> type) {
        CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
        CsvMapper csvMapper = new CsvMapper();
        objectReader = csvMapper.readerFor(type).with(bootstrapSchema).withFeatures(CsvParser.Feature.FAIL_ON_MISSING_COLUMNS);
    }

    public List<T> map(InputStream stream) throws IOException {
        return objectReader.<T>readValues(stream).readAll();
    }
}
