package al.sda.flyspy.domain.flight.service;

import al.sda.flyspy.domain.flight.model.dto.FlightDto;
import al.sda.flyspy.domain.flight.model.dto.airdata.AirData;
import al.sda.flyspy.domain.flight.model.dto.airdata.Aircraft;
import al.sda.flyspy.domain.flight.model.dto.airdata.Airline;
import al.sda.flyspy.domain.flight.model.dto.airdata.FlightIdentifier;
import al.sda.flyspy.domain.flight.model.dto.airdata.TerminalPoint;
import al.sda.flyspy.domain.flight.model.entity.Flight;
import al.sda.flyspy.shared.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class FlightServiceTest {
    @Mock
    private AirDataService airDataService;
    @Mock
    private Repository<Flight, Long> repository;
    private FlightService flightService;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(Repository.class);
        airDataService = Mockito.mock(AirDataService.class);
        flightService = new FlightService(repository, airDataService);

    }

    @Test
    void testParseAirData() {

        //Arrange
        AirData airData = Mockito.mock(AirData.class);
        FlightIdentifier flightIdentifier = Mockito.mock(FlightIdentifier.class);
        Aircraft aircraft = Mockito.mock(Aircraft.class);
        Airline airline = Mockito.mock(Airline.class);
        TerminalPoint departure = Mockito.mock(TerminalPoint.class);
        TerminalPoint arrival = Mockito.mock(TerminalPoint.class);
        Mockito.when(airData.getAircraft()).thenReturn(aircraft);
        Mockito.when(airData.getFlight()).thenReturn(flightIdentifier);
        Mockito.when(airData.getDeparture()).thenReturn(departure);
        Mockito.when(airData.getArrival()).thenReturn(arrival);
        Mockito.when(airData.getAirline()).thenReturn(airline);

        Mockito.when(airData.getFlight().getNumber())
                .thenReturn("LH175");
        Mockito.when(airData.getDeparture().getIata())
                .thenReturn("TIA");
        Mockito.when(airData.getDeparture().getScheduled())
                .thenReturn("2025-05-18T00:20:00+00:00");
        Mockito.when(airData.getArrival().getIata())
                .thenReturn("FRA");
        Mockito.when(airData.getArrival().getScheduled())
                .thenReturn("2025-05-20T00:20:00+00:00");
        Mockito.when(airData.getAirline().getName())
                .thenReturn("Lufthansa");
        Mockito.when(airData.getAircraft().getRegistration())
                .thenReturn("Boeing");

        //Act
        FlightDto actual = flightService.convertToFlightDto(airData);

        //Assertion
        FlightDto expected = new FlightDto();
        expected.setAircraftRegistration("Boeing");
        expected.setAirlineName("Lufthansa");
        expected.setFlightNumber("LH175");
        expected.setArrivalAirport("FRA");
        expected.setDepartureAirport("TIA");
        expected.setDepartureTime("2025-05-18T00:20:00+00:00");
        expected.setArrivalTime("2025-05-20T00:20:00+00:00");

        assertEquals(expected, actual);
    }

    @Test
    void testParseAirline_InvalidFlightNumber() {
        AirData airData = new AirData();

        FlightDto actual = flightService.convertToFlightDto(airData);
        FlightDto expected = new FlightDto();
        expected.setFlightNumber("-");
        expected.setDepartureAirport("-");
        expected.setDepartureTime("-");
        expected.setArrivalAirport("-");
        expected.setArrivalTime("-");
        expected.setAircraftRegistration("-");
        expected.setAirlineName("-");

        assertEquals(expected, actual);
    }
    @Test
    void testGetDepartingFlights() throws URISyntaxException {
        // Arrange
        AirData airData = Mockito.mock(AirData.class);
        FlightIdentifier flightIdentifier = Mockito.mock(FlightIdentifier.class);
        Airline airline = Mockito.mock(Airline.class);
        TerminalPoint departure = Mockito.mock(TerminalPoint.class);
        TerminalPoint arrival = Mockito.mock(TerminalPoint.class);

        Mockito.when(airData.getFlight()).thenReturn(flightIdentifier);
        Mockito.when(flightIdentifier.getNumber()).thenReturn("LH176");
        Mockito.when(airData.getAirline()).thenReturn(airline);
        Mockito.when(airline.getName()).thenReturn("Lufthansa");
        Mockito.when(airData.getDeparture()).thenReturn(departure);
        Mockito.when(departure.getIata()).thenReturn("TIA");
        Mockito.when(departure.getScheduled()).thenReturn("2025-05-18T10:20:00+00:00");
        Mockito.when(airData.getArrival()).thenReturn(arrival);
        Mockito.when(arrival.getIata()).thenReturn("FRA");
        Mockito.when(arrival.getScheduled()).thenReturn("2025-05-18T12:20:00+00:00");

        when(airDataService.getFlights(any())).thenReturn(List.of(airData));

        // Act
        List<FlightDto> departingFlights = flightService.getDepartingFlights();

        // Assert
        assertEquals(1, departingFlights.size());
        FlightDto flightDto = departingFlights.get(0);
        assertEquals("LH176", flightDto.getFlightNumber());
        assertEquals("Lufthansa", flightDto.getAirlineName());
        assertEquals("TIA", flightDto.getDepartureAirport());
        assertEquals("FRA", flightDto.getArrivalAirport());
        assertEquals("2025-05-18T10:20:00+00:00", flightDto.getDepartureTime());
        assertEquals("2025-05-18T12:20:00+00:00", flightDto.getArrivalTime());
    }

    @Test
    void testGetArrivingFlights() throws URISyntaxException {
        // Arrange
        AirData airData = Mockito.mock(AirData.class);
        FlightIdentifier flightIdentifier = Mockito.mock(FlightIdentifier.class);
        Airline airline = Mockito.mock(Airline.class);
        TerminalPoint departure = Mockito.mock(TerminalPoint.class);
        TerminalPoint arrival = Mockito.mock(TerminalPoint.class);

        Mockito.when(airData.getFlight()).thenReturn(flightIdentifier);
        Mockito.when(flightIdentifier.getNumber()).thenReturn("LH175");
        Mockito.when(airData.getAirline()).thenReturn(airline);
        Mockito.when(airline.getName()).thenReturn("Lufthansa");
        Mockito.when(airData.getDeparture()).thenReturn(departure);
        Mockito.when(departure.getIata()).thenReturn("FRA");
        Mockito.when(departure.getScheduled()).thenReturn("2025-05-18T00:20:00+00:00");
        Mockito.when(airData.getArrival()).thenReturn(arrival);
        Mockito.when(arrival.getIata()).thenReturn("TIA");
        Mockito.when(arrival.getScheduled()).thenReturn("2025-05-20T00:20:00+00:00");

        when(airDataService.getFlights(any())).thenReturn(List.of(airData));

        // Act
        List<FlightDto> arrivingFlights = flightService.getArrivingFlights();

        // Assert
        assertEquals(1, arrivingFlights.size());
        FlightDto flightDto = arrivingFlights.get(0);
        assertEquals("LH175", flightDto.getFlightNumber());
        assertEquals("Lufthansa", flightDto.getAirlineName());
        assertEquals("FRA", flightDto.getDepartureAirport());
        assertEquals("TIA", flightDto.getArrivalAirport());
        assertEquals("2025-05-18T00:20:00+00:00", flightDto.getDepartureTime());
        assertEquals("2025-05-20T00:20:00+00:00", flightDto.getArrivalTime());
    }
}


