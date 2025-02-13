package iceland.hi.throunhugbo.data;

import org.jdbi.v3.sqlobject.*;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface HotelDao extends SqlObject {

    /**
     * @param name nafn hótels, hástafur, íslenskir stafir
     * @param address á forminu: [götuheiti] [húsanúmer]
     * @param city Byrjar á hástafi, íslenskir stafir takk
     * @param starRating á bilinu [0.0 ; 5.0]
     */
    @SqlUpdate("""
            INSERT INTO hotels(name, address, city, star_rating)
                        VALUES (:name, :address, :city, :star_rating)
            
            """)
    void insertHotel(
            @Bind("name") String name,
            @Bind("address") String address,
            @Bind("city") String city,
            @Bind("star_rating") double starRating
    );









}
