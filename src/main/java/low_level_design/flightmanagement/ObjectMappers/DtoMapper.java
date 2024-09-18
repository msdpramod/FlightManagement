package low_level_design.flightmanagement.ObjectMappers;

import low_level_design.flightmanagement.DTOs.*;
import low_level_design.flightmanagement.Models.*;

public class DtoMapper {
    public static UserDto mapUserToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setFunds(user.getFunds());
        return userDto;
    }

    public static User mapDtoToUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setFunds(userDto.getFunds());
        return user;
    }

    public static BookingDto mapBookingToDto(Booking booking) {
        BookingDto bookingDto = new BookingDto();
        bookingDto.setUserId(booking.getUser().getId());
        bookingDto.setFlightId(booking.getFlightClearTrip().getId());
        bookingDto.setFareId(booking.getFare().getId());
        bookingDto.setSeatId(booking.getSeats().get(0).getId()); // Assuming one seat per booking
        return bookingDto;
    }

    public static FareDto mapFareToDto(Fare fare) {
        FareDto fareDto = new FareDto();
        fareDto.setFareType(fare.getType().toString());
        fareDto.setPrice(fare.getPrice().doubleValue()); // Ensure price is Double
        return fareDto;
    }

    public static FlightDto mapFlightToDto(Flight_ClearTrip flight) {
        FlightDto flightDto = new FlightDto();
        flightDto.setFrom(flight.getFrom());
        flightDto.setTo(flight.getTo());
        flightDto.setDepartDate(flight.getDepartDate());
        return flightDto;
    }

    public static SeatDto mapSeatToDto(Seat seat) {
        SeatDto seatDto = new SeatDto();
        seatDto.setSeatType(seat.getType().toString());
        seatDto.setRoom(seat.getRoom());
        seatDto.setRow(seat.getRow());
        seatDto.setColumn(seat.getColumn());
        return seatDto;
    }
}