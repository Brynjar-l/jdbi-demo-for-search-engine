package iceland.hi.throunhugbo.database;

import org.jdbi.v3.core.Jdbi;

class DatabaseInitializer {

    private DatabaseInitializer() {}

    public static void init(Jdbi jdbi) {
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

            // má bæta við rows í db?
        });
    }

}
