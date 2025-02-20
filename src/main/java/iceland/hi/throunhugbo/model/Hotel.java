package iceland.hi.throunhugbo.model;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class Hotel {
    private int id;
    private String name;
    private String address;
    private String city;
    private double starRating;

    public Hotel(
            @ColumnName("id") int id,
            @ColumnName("name") String name,
            @ColumnName("address") String address,
            @ColumnName("city") String city,
            @ColumnName("star_rating") double starRating
    ) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.starRating = starRating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Hotel setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getStarRating() {
        return starRating;
    }

    public void setStarRating(double starRating) {
        this.starRating = starRating;
    }

    @Override
    public String toString() {
        return id + ", " + name + ", " + address + ", " + city + ", " + starRating;
    }
}
