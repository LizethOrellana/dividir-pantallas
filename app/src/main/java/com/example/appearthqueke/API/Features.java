package com.example.appearthqueke.API;

public class Features {
    private String id;
    private Properties properties;
    private Geometry geometry;

    public String getId() {
        return id;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }
}
