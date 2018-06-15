package com.seibels.integration;

import org.apache.camel.Header;

public class Recipients {
    public String[] list(@Header("Carrier") String carrier) {
        if (carrier.equalsIgnoreCase("gs")) {
            return new String[]{"direct:BC", "direct:IPX", "direct:SIPS"};
        } else if (carrier.equalsIgnoreCase("csi")) {
            return new String[]{"direct:BC"};
        } else {
            return new String[]{"direct:UNKNOWN"};
        }

    }
}
