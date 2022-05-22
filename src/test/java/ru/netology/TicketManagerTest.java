package ru.netology;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

class TicketManagerTest {
    @Mock
    TicketRepository repository = Mockito.mock(TicketRepository.class);
    @InjectMocks
    TicketManager manager = new TicketManager(repository);

    TicketData ticketOne = new TicketData(1, 20_000, "PKV", "DMV", 30);
    TicketData ticketTwo = new TicketData(2, 12_000, "SVO", "DMV", 55);
    TicketData ticketThree = new TicketData(3, 12_000, "SVO", "DMV", 62);
    TicketData ticketFour = new TicketData(4, 7_000, "PKV", "SVO", 78);
    TicketData ticketFive = new TicketData(5, 1_000, "PKV", "SVO", 74);
    TicketData ticketSix = new TicketData(6, 10_000, "PKV", "SVO", 98);
    TicketData ticketSeven = new TicketData(7, 17_000, "PKV", "SVO", 12);
    TicketData ticketEight = new TicketData(8, 19_000, "PKV", "LED", 99);
    TicketData ticketNine = new TicketData(9, 11_000, "DMV", "PKV", 22);
    TicketData ticketTen = new TicketData(10, 12_000, "SVO", "LED", 51);


    TicketData[] mockEmpty = new TicketData[0];
    TicketData[] mockOneTicket = new TicketData[]{ticketOne};
    TicketData[] mockTenTicket = new TicketData[]{
            ticketOne,
            ticketTwo,
            ticketThree,
            ticketFour,
            ticketFive,
            ticketSix,
            ticketSeven,
            ticketEight,
            ticketNine,
            ticketTen};



    @Test
    void shouldNotFindMockEmpty() {
        doReturn(mockEmpty).when(repository).findAll();
        assertThrows(NotFoundException.class, () -> {
            manager.findAll("DME", "LED");
        });
    }

    @Test
    void shouldNotFindMockWithOneTicket() {
        doReturn(mockOneTicket).when(repository).findAll();
        assertThrows(NotFoundException.class, () -> {
            manager.findAll("DME", "NON");
        });
    }

    @Test
    void shouldNotFindMockWithTenTicket() {
        doReturn(mockTenTicket).when(repository).findAll();
        assertThrows(NotFoundException.class, () -> {
            manager.findAll("VKO", "PES");
        });
    }


    @Test
    void shouldFindOneResultMockWithOneTicket() {
        doReturn(mockOneTicket).when(repository).findAll();
        TicketData[] expected = new TicketData[]{ticketOne};
        assertArrayEquals(expected, manager.findAll("PKV", "DMV"));
    }



    @Test
    void shouldFindManyResultsMockWithTenTicketOne() {
        doReturn(mockTenTicket).when(repository).findAll();
        TicketData[] expected = new TicketData[]{
                ticketTwo,
                ticketThree};
        assertArrayEquals(expected, manager.findAll("SVO", "DMV"));
    }

    @Test
    void shouldFindManyResultsMockWithTenTicketTwo() {
        doReturn(mockTenTicket).when(repository).findAll();
        TicketData[] expected = new TicketData[]{
                ticketFive,
                ticketFour,
                ticketSix,
                ticketSeven};
        assertArrayEquals(expected, manager.findAll("PKV", "SVO"));
    }


    @Test
    void shouldMatchesFromToTrue() {
        assertTrue(manager.matchesFromTo(ticketOne, "PKV", "DMV"));
    }

    @Test
    void shouldMatchesFromToFalseOne() {
        assertFalse(manager.matchesFromTo(ticketOne, "SVO", "LED"));
    }

    @Test
    void shouldMatchesFromToFalseTwo() {
        assertFalse(manager.matchesFromTo(ticketOne, "DME", "RVH"));
    }

    @Test
    void shouldMatchesFromToFalseThree() {
        assertFalse(manager.matchesFromTo(ticketOne, "VKO", "RVH"));
    }
}