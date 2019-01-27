package org.launchcode.salessamurai.models;

public enum Wholesalers {

    SPORTSSOUTH ("Sportssouth"),
    RSRGROUP ("RSRGroup"),
    GREENSUPPLY ("GreenSupply"),
    DAVIDSONS ("Davidsons");

    private final String name;

    Wholesalers(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}


