package zely.parkinglotspring.service.parkingticket;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import zely.parkinglotspring.model.parkingticket.ParkingTicket;

import zely.parkinglotspring.repository.parkingticket.ParkingTicketRepository;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;


@ExtendWith(MockitoExtension.class)
class ParkingTicketServiceTest {

    @Mock
    ParkingTicketRepository parkingTicketRepository;

    @InjectMocks
    ParkingTicketService parkingTicketService;

    @Test
    void when_getAllParkingTickets_willReturn_allTickets() {

        //given
        ParkingTicket ticket = new ParkingTicket();
        List<ParkingTicket> tickets = new ArrayList<>();
        tickets.add(ticket);

        given(parkingTicketRepository.findAll()).willReturn(tickets);

        //when
        List<ParkingTicket> ticketList = parkingTicketService.getAllParkingTickets();

        //then
        then(parkingTicketRepository).should().findAll();
        assertThat(ticketList).hasSize(1);
    }

    @Test
    void when_createParkingTicket_willReturn_newTicket() {
        ParkingTicket ticket = new ParkingTicket();

        given(parkingTicketRepository.save(any(ParkingTicket.class))).willReturn(ticket);

        //when
        ParkingTicket newTicket = parkingTicketService.createParkingTicket(ticket);

        //then
        assertThat(newTicket).isNotNull();
        assertThat(newTicket).isEqualTo(ticket);
        assertThat(newTicket).isSameAs(ticket);

        then(parkingTicketRepository).should().save(ticket);

    }

    @Test
    void when_ticketNumberIsInvalid_scanParkingTicket_willReturn_throwException() {

        // Given
        Integer ticketNumber = 123;
        given(parkingTicketRepository.findById(ticketNumber)).willReturn(Optional.empty());

        // When / Then
        assertThrows(RuntimeException.class, () -> {
            parkingTicketService.scanParkingTicket(ticketNumber);
        });
    }

    @Test
    void when_updateTicket_willReturn_updatedTicket(){
        ParkingTicket ticket = new ParkingTicket();

        given(parkingTicketRepository.save(any(ParkingTicket.class))).willReturn(ticket);

        //when
        ParkingTicket updatedTicket = parkingTicketService.updateTicket(ticket);

        //then
        assertThat(updatedTicket).isNotNull();
        assertThat(updatedTicket).isEqualTo(ticket);
        assertThat(updatedTicket).isSameAs(ticket);

        then(parkingTicketRepository).should().save(updatedTicket);

    }

}
