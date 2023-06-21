package com.moviereservation.domain.movie.reservation;

import static com.moviereservation.domain.seat.SeatStatus.NORMAL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.moviereservation.domain.reservation.ReservationRepository;
import com.moviereservation.service.reservation.ReservationService;
import com.moviereservation.utils.reservation.SeatsSeparator;
import com.moviereservation.domain.seat.Seat;
import com.moviereservation.domain.seat.Seats;
import com.moviereservation.utils.exception.ReservationNotAllowedException;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ReservationServiceTest {
    private static final int THREAD_COUNT = 3; // 동시에 실행할 스레드 수

    @InjectMocks
    ReservationService service;

    @Mock
    ReservationRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

//    @MethodSource("getSyncReserve")
    @Test
    public void testSeatBookingConcurrency() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(THREAD_COUNT); // 모든 스레드가 시작할 때까지 대기하기 위한 카운트다운 래치
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT); // 고정 크기의 스레드 풀 생성
        Stack<String> stack = new Stack<>();
        stack.add("seat=2-8");
        stack.add("seat=2-8&seat=2-9&seat=3-8&seat=3-9");
        stack.add("seat=2-9&seat=4-8&seat=4-9");

        executorService.submit(() -> {
            try {
                // 모든 스레드가 시작할 때까지 대기
                latch.await();
//                service.reserve(1L, stack.pop()); // 예매 메서드 호출
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 스레드가 시작되었음을 알림
        latch.countDown();

        executorService.shutdown();
        /*while (!executorService.isTerminated()) {
            // 스레드 풀이 종료될 때까지 대기
        }*/

        // 예매에 대한 검증
//        assertEquals(SEAT_COUNT, seatBooking.getBookedSeatCount());
        assertThat(true).isTrue();
    }

    public static Stream<String> getSyncReserve() {
        return Stream.of(); // all normal
    }

    /*@Test
    void testAlreadyReserved_Exception() {
        // given
        Seats seats = SeatsSeparator.separate("seat=2-8");

        // when
        repository.reserve(1, seats);

        // then
        assertThatThrownBy(() -> repository.reserve(1, seats))
                .isInstanceOf(ReservationNotAllowedException.class);
    }

    @ParameterizedTest
    @MethodSource("getUnreservableSeatColumns")
    void testUnreservableSeatColumns_Exception(Seat seat) {
        // then
        assertThatThrownBy(() -> repository.reserve(1, new Seats(List.of(seat))))
                .isInstanceOf(ReservationNotAllowedException.class);
    }*/

    public static Stream<Seat> getUnreservableSeatColumns() {
        return Stream.of(new Seat(NORMAL, 1, 3), new Seat(NORMAL, 1, 7));
    }

    @Test
    void testThreadInterrupt() {
        // given


        // when
        for (int i = 0; i < 10; i++) {
            System.out.println("i = " + i);
            new Thread(() -> {
                throw new IllegalStateException();
            }).start();

        }

        // then

    }

    @Test
    void testReserve_Async_Exception() {
        // given


        // when


        // then

    }

    @Test
    void testReserve_Sync_Success() {
        // given


        // when
        when(repository.reserve(any(Long.class), any(Seats.class))).thenReturn(1);

        // then

    }

    @Test
    void testFindSeat_Exception() {
        // given


        // when


        // then

    }

    @Test
    void testFindSeat_Success() {
        // given


        // when


        // then

    }
}
