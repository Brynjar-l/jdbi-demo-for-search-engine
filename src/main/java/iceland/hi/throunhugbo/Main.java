package iceland.hi.throunhugbo;

import org.jdbi.v3.core.Jdbi;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        final String url = "jdbc:sqlite:hotels.sqlite";
        Jdbi jdbi = Jdbi.create(url);

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

        System.out.println("Database prolly Created!");



        try {
            jdbi.useHandle(handle -> {
                handle.createUpdate("INSERT INTO hotels (id, name, address, city, star_rating) VALUES(:id, :name, :address, :city, :star_rating)")
                        .bind("id", 1)
                        .bind("name", "Demo_Hotel")
                        .bind("address", "StreetOfStress")
                        .bind("city", "Reykjavik")
                        .bind("star_rating", 5)
                        .execute();
            });
        } catch (Exception _) {
            System.out.println("This ID might already exist in the database!");
        }
        System.out.println("");
        System.out.println("");

        List<Map<String, Object>> users =
                jdbi.withHandle(handle -> {
                    return handle.createQuery("SELECT * FROM hotels")
                            .mapToMap()
                            .list();
                });

        System.out.println("[QUERY] -> " + users);






    }
}
