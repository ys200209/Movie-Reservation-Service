package com.moviereservation.domain.movie.reservation.utils;

import com.moviereservation.study.domain.Seat;
import com.moviereservation.study.domain.SeatStatus;
import com.moviereservation.study.domain.Seats;
import java.util.ArrayList;
import java.util.List;

public class SeatsSeparator {
    public static final String SEATS_DELIMITER = "&";
    public static final String SEAT_DELIMITER = "=";
    public static final String SEAT_POSITION_DELIMITER = "-";

    public static Seats separate(String selected) {
        return new Seats(makeSeats(separateSeat(selected)));
    }

    private static String[] separateSeat(String selected) {
        return selected.split(SEATS_DELIMITER);
    }

    private static List<Seat> makeSeats(String[] seatTexts) {
        List<Seat> seats = new ArrayList<>();

        for (String seatText : seatTexts) {
            seats.add(makeSeat(seatText));
        }

        return seats;
    }

    private static Seat makeSeat(String seatText) {
        String[] splitSeatText = seatText.split(SEAT_DELIMITER);
        String[] splitSeatPosition = splitSeatText[1].split(SEAT_POSITION_DELIMITER);
        int row = Integer.parseInt(splitSeatPosition[0]);
        int column = Integer.parseInt(splitSeatPosition[1]);

        return new Seat(SeatStatus.ACTIVE, row, column);
    }
}
