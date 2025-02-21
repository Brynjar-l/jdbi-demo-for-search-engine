package iceland.hi.throunhugbo.service;

import iceland.hi.throunhugbo.dao.HotelDao;
import iceland.hi.throunhugbo.database.JdbiManager;
import iceland.hi.throunhugbo.model.Hotel;
import org.jdbi.v3.core.Jdbi;
import java.util.List;

/*
 * TODO: búa til innri klasa 'QueryBuilder'
 * TODO: Overloada constr til að geta insertað string, eða lista?
 */
public class HotelService {
    final Jdbi jdbi = JdbiManager.getInstance();
    final HotelDao hotelDao = jdbi.onDemand(HotelDao.class);

    public HotelService() {}

    public List<Hotel> getAllHotels() {
        return hotelDao.getAllHotels();
    }

    public Hotel getHotelById(int id) {
        return hotelDao.getHotelById(id);
    }
}