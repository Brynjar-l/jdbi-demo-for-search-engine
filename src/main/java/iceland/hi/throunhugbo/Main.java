
import iceland.hi.throunhugbo.data.HotelDao;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import java.util.List;
import java.util.Map;

import org.jdbi.v3.sqlobject.*;

public static void main() {
    final String url = "jdbc:sqlite:hotels.sqlite";

    Jdbi jdbi = Jdbi.create(url);
    jdbi.installPlugin(new SqlObjectPlugin());

    HotelDao hotelDao = jdbi.onDemand(HotelDao.class);

    // [showcase] create database if it doesn't exist already. Maybe add a list to populate it on first run?
    jdbi.useHandle(handle -> {
        handle.execute("""
                CREATE TABLE IF NOT EXISTS hotels (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    address TEXT NOT NULL,
                    city TEXT,
                    star_rating REAL
                )
                """);

        handle.execute("""
                CREATE TABLE IF NOT EXISTS rooms (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    hotel_id INTEGER NOT NULL,
                    room_number TEXT,
                    room_type TEXT,
                    price_per_night INTEGER NOT NULL,
                    is_booked BOOLEAN NOT NULL DEFAULT 0,
                    FOREIGN KEY (hotel_id) REFERENCES hotels(id)
                )
                """);
    });


    try {
        hotelDao.insertHotel("withoutIdThisTime", "Sæmundargata 2", "Reykjavik", 4.4);
    } catch (Exception _) {}





    // [showcase] query showcase
    List<Map<String, Object>> users =
            jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM hotels")
                    .mapToMap()
                    .list());

    System.out.println("[QUERY] -> " + users);
}



static void insertHotel(Jdbi jdbi, int id, String name, String address, String city, double star_rating) {
    try {
        jdbi.useHandle(handle -> handle.createUpdate("INSERT INTO hotels (id, name, address, city, star_rating) VALUES(:id, :name, :address, :city, :star_rating)")
                .bind("id", id)
                .bind("name", name)
                .bind("address", address)
                .bind("city", city)
                .bind("star_rating", star_rating)
                .execute());

        System.out.println("id [" + id +"] was added");
    } catch (Exception _) {
        System.out.println("ID ALREADY EXISTS, CONTINUING");
    }
}
