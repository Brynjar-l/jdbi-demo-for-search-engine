package iceland.hi.throunhugbo.dao;

import iceland.hi.throunhugbo.model.Hotel;
import org.jdbi.v3.sqlobject.*;
import org.jdbi.v3.sqlobject.config.RegisterConstructorMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import java.util.List;

public interface HotelDao extends SqlObject {

    @SqlUpdate("""
            INSERT INTO hotels(name, address, city, star_rating)
                        VALUES (:name, :address, :city, :star_rating)
            """)
    void createHotel(
            @Bind("name") String name,
            @Bind("address") String address,
            @Bind("city") String city,
            @Bind("star_rating") double starRating
    );

    @SqlQuery("SELECT * FROM hotels")
    @RegisterConstructorMapper(Hotel.class)
    List<Hotel> getAllHotels();

    @SqlQuery("SELECT * FROM hotels WHERE id = :id")
    @RegisterConstructorMapper(Hotel.class)
    Hotel getHotelById(
            @Bind("id") int id
    );

    @SqlUpdate("""
            UPDATE hotels SET
            address = :address,
            city = :city,
            star_rating = :starRating
            WHERE id = :id
            """)
    void updateHotelByHotel(
            @BindBean Hotel hotel
    );
}