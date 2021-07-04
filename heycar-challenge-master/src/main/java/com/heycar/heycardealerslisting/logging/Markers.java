package com.heycar.heycardealerslisting.logging;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public final class Markers {
    public static Marker METHOD_START() {
        return MarkerFactory.getMarker("METHOD START");
    }

    public static Marker METHOD_END(){
        return MarkerFactory.getMarker("METHOD END");
    }
}
