
import iceland.hi.throunhugbo.model.Hotel;
import iceland.hi.throunhugbo.data.HotelDao;
import iceland.hi.throunhugbo.data.HotelMapper;
import org.jdbi.v3.core.Jdbi;
import java.util.List;

import org.jdbi.v3.sqlobject.*;
import org.jetbrains.annotations.NotNull;


private static final String URL = "jdbc:sqlite:src/main/resources/database/hotels.sqlite";


public static void main() {

    // init fyrir jdbi
    final Jdbi jdbi = Jdbi.create(URL);
    jdbi.installPlugin(new SqlObjectPlugin());
    // Dao
    HotelDao hotelDao = jdbi.onDemand(HotelDao.class);


    List<Hotel> hotels = jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM hotels")
                .map(new HotelMapper())
                .list()
    );

    for (Hotel hotel : hotels) {
        System.out.println(hotel.toString());
        System.out.print("\n");
    }
}

/**
 * @param jdbi insert the active
 */
private void initDatabase(@NotNull Jdbi jdbi) {
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
}

