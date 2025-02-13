package iceland.hi.throunhugbo.data;

import iceland.hi.throunhugbo.model.Hotel;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
/*
 * Dæma notkun:
 *
 * List<Hotel> hotels = jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM hotels")
                .map(new HotelMapper())
                .list()
    );
 *
 *
 */


/**
 *  Umbreytir skil frá Queries yfir í klasa með öllum gögnum.
 */
public class HotelMapper implements RowMapper<Hotel> {
    @Override
    public Hotel map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Hotel(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("address"),
                rs.getString("city"),
                rs.getFloat("star_rating")
        );
    }
}
