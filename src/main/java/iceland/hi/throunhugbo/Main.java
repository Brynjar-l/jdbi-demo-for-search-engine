
import iceland.hi.throunhugbo.data.Hotel;
import iceland.hi.throunhugbo.data.HotelDao;
import iceland.hi.throunhugbo.data.HotelMapper;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import java.util.List;
import java.util.Map;
import org.jdbi.v3.sqlobject.*;
import org.jetbrains.annotations.NotNull;


private static final String URL = "jdbc:sqlite:hotels.sqlite";


public static void main() {

    // init fyrir jdbi
    final Jdbi jdbi = Jdbi.create(URL);
    jdbi.installPlugin(new SqlObjectPlugin());
    jdbi.registerRowMapper(new HotelMapper());
    // Dao
    HotelDao hotelDao = jdbi.onDemand(HotelDao.class);


    List<Map<String, Object>> users =
            jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM hotels")
                    .mapToMap()
                    .list());



}


/**
 *
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

