import iceland.hi.throunhugbo.model.Hotel;
import iceland.hi.throunhugbo.service.HotelService;


public static void main() {
    HotelService hs = new HotelService();

    List<Hotel> hotelList = hs.getAllHotels();
    Hotel hotel = hs.getHotelById(2);
}


//    List<Hotel> hotels = jdbi.withHandle(handle -> handle.createQuery("SELECT * FROM hotels")
//                .map(new HotelMapper())
//                .list()
//    );