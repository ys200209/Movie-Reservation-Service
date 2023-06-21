package com.moviereservation.web.reservation;

import com.moviereservation.domain.seat.Seat;
import com.moviereservation.domain.seat.SeatStatus;
import com.moviereservation.domain.seat.Seats;
import com.moviereservation.web.reservation.dto.ReservationRequestDto;
import com.moviereservation.domain.seat.Theater;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ReservationController {

    @GetMapping("/{movieId}/reservation")
    public String testReserveSeat(@PathVariable Long movieId, Model model) {
        System.out.println("(GetMapping) ReservationController.testReserveSeat");
        List<Seat> seats1 = List.of(
                new Seat(SeatStatus.NORMAL, 1, 1), new Seat(SeatStatus.DISABLED, 1, 2), new Seat(SeatStatus.EMPTY, 1, 3),
                new Seat(SeatStatus.DISABLED, 1, 4), new Seat(SeatStatus.NORMAL, 1, 5), new Seat(SeatStatus.NORMAL, 1, 6),
                new Seat(SeatStatus.EMPTY, 1, 7), new Seat(SeatStatus.NORMAL, 1, 8), new Seat(SeatStatus.NORMAL, 1, 9));
        List<Seat> seats2 = List.of(
                new Seat(SeatStatus.NORMAL, 2, 1), new Seat(SeatStatus.NORMAL, 2, 2), new Seat(SeatStatus.EMPTY, 2, 3),
                new Seat(SeatStatus.NORMAL, 2, 4), new Seat(SeatStatus.NORMAL, 2, 5), new Seat(SeatStatus.NORMAL, 2, 6),
                new Seat(SeatStatus.EMPTY, 2, 7), new Seat(SeatStatus.NORMAL, 2, 8), new Seat(SeatStatus.NORMAL, 2, 9));
        List<Seat> seats3 = List.of(
                new Seat(SeatStatus.NORMAL, 3, 1), new Seat(SeatStatus.DISABLED, 3, 2), new Seat(SeatStatus.EMPTY, 3, 3),
                new Seat(SeatStatus.NORMAL, 3, 4), new Seat(SeatStatus.DISABLED, 3, 5), new Seat(SeatStatus.NORMAL, 3, 6),
                new Seat(SeatStatus.EMPTY, 3, 7), new Seat(SeatStatus.NORMAL, 3, 8), new Seat(SeatStatus.NORMAL, 3, 9));
        List<Seat> seats4 = List.of(
                new Seat(SeatStatus.NORMAL, 4, 1), new Seat(SeatStatus.NORMAL, 4, 2), new Seat(SeatStatus.EMPTY, 4, 3),
                new Seat(SeatStatus.NORMAL, 4, 4), new Seat(SeatStatus.NORMAL, 4, 5), new Seat(SeatStatus.NORMAL, 4, 6),
                new Seat(SeatStatus.EMPTY, 4, 7), new Seat(SeatStatus.NORMAL, 4, 8), new Seat(SeatStatus.NORMAL, 4, 9));
        List<Seat> seats5 = List.of(
                new Seat(SeatStatus.DISABLED, 5, 1), new Seat(SeatStatus.DISABLED, 5, 2), new Seat(SeatStatus.EMPTY, 5, 3),
                new Seat(SeatStatus.DISABLED, 5, 4), new Seat(SeatStatus.DISABLED, 5, 5), new Seat(SeatStatus.NORMAL, 5, 6),
                new Seat(SeatStatus.EMPTY, 5, 7), new Seat(SeatStatus.NORMAL, 5, 8), new Seat(SeatStatus.NORMAL, 5, 9));
        List<Seat> seats6 = List.of(
                new Seat(SeatStatus.DISABLED, 6, 1), new Seat(SeatStatus.DISABLED, 6, 2), new Seat(SeatStatus.EMPTY, 6, 3),
                new Seat(SeatStatus.DISABLED, 6, 4), new Seat(SeatStatus.DISABLED, 6, 5), new Seat(SeatStatus.NORMAL, 6, 6),
                new Seat(SeatStatus.EMPTY, 6, 7), new Seat(SeatStatus.NORMAL, 6, 8), new Seat(SeatStatus.NORMAL, 6, 9));
        Theater theater = new Theater(
                List.of(new Seats(seats1), new Seats(seats2), new Seats(seats3), new Seats(seats4), new Seats(seats5), new Seats(seats6)));

        model.addAttribute("movieId", movieId);
        model.addAttribute("theater", theater);
        return "movies/test_reservation";
    }

    @PostMapping("/{movieId}/reservationt")
    @ResponseBody
    public ReservationRequestDto testReservationPost(@PathVariable Long movieId,
                                                     @RequestBody ReservationRequestDto request) {
        System.out.println("(PostMapping) ReservationController.testReservationPost");
        System.out.println("(PostMapping) request = " + request);
//        model.addAttribute("movieId", movieId);
        return request;
    }

    @GetMapping("/delete")
    public String testDeleteMe() {
        return "movies/deleteme";
    }
}
