package com.heycar.heycardealerslisting.logging;

import org.slf4j.Logger;
import org.slf4j.Marker;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

@Component
public class LogBuilder {
    private Logger logger;

    private Marker marker;

    private Date date;

    private String methodName;

    private String error;

    private Map<String, Object> parameters;

    public LogBuilder() {
        this.parameters = new LinkedHashMap<>();
    }

    public LogBuilder withLogger(Logger logger){
        this.logger = logger;
        return this;
    }

    public LogBuilder withMarker(Marker marker){
        this.marker = marker;
        return this;
    }

    public LogBuilder withDate(Date date){
        this.date = date;
        return this;
    }

    public LogBuilder withMethod(String name){
        this.methodName = name;
        return this;
    }

    public LogBuilder withParameter(String name, Object value){
        this.parameters.put(name, value);
        return this;
    }

    public LogBuilder withError(Exception exception){
        this.error = exception.getMessage();
        return this;
    }

    public void info(){
        logger.info(marker, getMessage());
    }

    public void error(){
        logger.error(marker, getMessage());
    }

    private String getMessage(){
        StringBuilder messageBuilder = new StringBuilder();

        if(this.date != null){
            String pattern = "EEEEE dd MMMMM yyyy HH:mm:ss.SSSZ";
            SimpleDateFormat simpleDateFormat =
                    new SimpleDateFormat(pattern, new Locale("en", Locale.GERMANY.getISO3Country()));
            String date = simpleDateFormat.format(this.date);
            messageBuilder.append(date);
            messageBuilder.append(' ');
        }

        if(this.methodName != null){
            messageBuilder.append("method: ");
           messageBuilder.append(methodName);
            messageBuilder.append(' ');
        }

        if(!this.parameters.isEmpty()){
            messageBuilder.append("parameters: ");
            this.parameters.forEach((key,value) -> {
                messageBuilder.append(key + " " + value + ",");
            });
            messageBuilder.append(' ');
        }

        if(this.error != null){
            messageBuilder.append("error: ");
            messageBuilder.append(this.error);
        }

        return messageBuilder.toString();
    }
}
