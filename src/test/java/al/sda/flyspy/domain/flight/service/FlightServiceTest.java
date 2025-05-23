package al.sda.flyspy.domain.flight.service;

import al.sda.flyspy.domain.flight.model.dto.FlightDto;
import al.sda.flyspy.domain.flight.model.dto.airdata.AirData;
import al.sda.flyspy.domain.flight.model.dto.airdata.Aircraft;
import al.sda.flyspy.domain.flight.model.dto.airdata.Airline;
import al.sda.flyspy.domain.flight.model.dto.airdata.FlightDataResponse;
import al.sda.flyspy.domain.flight.model.dto.airdata.FlightIdentifier;
import al.sda.flyspy.domain.flight.model.dto.airdata.TerminalPoint;
import al.sda.flyspy.domain.flight.model.entity.Flight;
import al.sda.flyspy.shared.util.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        FlightDto actual = flightService.parseToFlightDto(airData);

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

        FlightDto actual = flightService.parseToFlightDto(airData);
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
    void testGetDepartureFlightList() {
        FlightDataResponse departingFlights = new FlightDataResponse();
        List<AirData> departingFlightList = new ArrayList<>();
        departingFlightList.add(new AirData());
        departingFlightList.add(null);
        departingFlights.setData(departingFlightList);
        Mockito.when(airDataService.getFlights(Mockito.anyMap()))
                .thenReturn(departingFlights);
        List<FlightDto> result = flightService.getDepartureFlightList();
        assertNotNull(result);
        assertEquals(1, result.size());
    }
    @Test
    void testGetDepartureFlightList_NullData() {
        AirData airData = new AirData();
        FlightDataResponse response = new FlightDataResponse();
        response.setData(List.of(airData));

        Mockito.when(airDataService.getFlights(Mockito.anyMap()))
                .thenReturn(response);

        List<FlightDto> actual = flightService.getDepartureFlightList();
         FlightDto expected = new FlightDto();
         expected.setFlightNumber("-");
         expected.setDepartureTime("-");
         expected.setDepartureAirport("-");;
         expected.setAircraftRegistration("-");
         expected.setAirlineName("-");

         assertEquals(1, actual.size());

         FlightDto actualDto = actual.get(0);
        assertEquals(expected.getFlightNumber(), actualDto.getFlightNumber());
        assertEquals(expected.getDepartureAirport(), actualDto.getDepartureAirport());
        assertEquals(expected.getDepartureTime(), actualDto.getDepartureTime());
        assertEquals(expected.getAircraftRegistration(), actualDto.getAircraftRegistration());
        assertEquals(expected.getAirlineName(), actualDto.getAirlineName());
    }
    @Test
   void testGetArrivalFlightList() {
        FlightDataResponse arrivalFlights = new FlightDataResponse();
        arrivalFlights.setData(List.of());
        Mockito.when(airDataService.getFlights(Mockito.anyMap()))
                .thenReturn(arrivalFlights);
        List<FlightDto> result = flightService.getArrivingFlightList();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}