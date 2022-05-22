package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicketRepositoryTest {
    TicketRepository repositoryEmpty = new TicketRepository();
    TicketRepository repositoryOneTicket = new TicketRepository();
    TicketRepository repositoryTenTicket = new TicketRepository();


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

    @BeforeEach
    void setup() {
        repositoryOneTicket.addTicket(ticketOne);
        repositoryTenTicket.addTicket(ticketOne);
        repositoryTenTicket.addTicket(ticketTwo);
        repositoryTenTicket.addTicket(ticketThree);
        repositoryTenTicket.addTicket(ticketFour);
        repositoryTenTicket.addTicket(ticketFive);
        repositoryTenTicket.addTicket(ticketSix);
        repositoryTenTicket.addTicket(ticketSeven);
        repositoryTenTicket.addTicket(ticketEight);
        repositoryTenTicket.addTicket(ticketNine);
        repositoryTenTicket.addTicket(ticketTen);
    }


    @Test
    void shouldAddTicket() {
        TicketData[] expected = new TicketData[]{
                ticketOne};
        repositoryEmpty.addTicket(ticketOne);
        assertArrayEquals(expected, repositoryEmpty.findAll());
    }

    @Test
    void shouldAddTicketException() {
        assertThrows(AlreadyExistsException.class, () -> {
            repositoryTenTicket.addTicket(ticketOne);
        });
    }

    @Test
    void shouldRemoveById() {
        TicketData[] expected = new TicketData[]{
                ticketOne,
                ticketTwo,
                ticketThree,
                ticketFour,
                ticketFive,
                ticketSeven,
                ticketEight,
                ticketNine,
                ticketTen};
        repositoryTenTicket.removeById(6);

        assertArrayEquals(expected, repositoryTenTicket.findAll());
    }

    @Test
    void shouldFindByIdPass() {
        assertEquals(ticketOne, repositoryTenTicket.findById(1));
    }


    @Test
    void shouldRemoveByIdException() {
        assertThrows(NotFoundException.class, () -> {
            repositoryTenTicket.removeById(15);
        });
    }


    @Test
    void shouldFindByIdNull() {
        assertNull(repositoryTenTicket.findById(11));
    }
}