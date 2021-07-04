package com.heycar.heycardealerslisting.domain.dto;

import lombok.Data;

@Data
public class CsvMakeModel {
   private String make;
   private String model;

   public CsvMakeModel(String makeModel){
      String[] splitMakeAndModel = makeModel.split("/");
      this.make = splitMakeAndModel[0];
      this.model = splitMakeAndModel[1];
   }
}
