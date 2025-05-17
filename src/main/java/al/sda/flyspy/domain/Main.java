package al.sda.flyspy.domain;

import al.sda.flyspy.domain.flight.model.dto.FlightDto;
import al.sda.flyspy.domain.flight.service.AirDataServiceImpl;
import al.sda.flyspy.domain.flight.service.FlightService;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws URISyntaxException {
        // EntityManagerFactory facotry = Persistence.createEntityManagerFactory("flyspy-pu");
        Scanner scanner = new Scanner(System.in);

        FlightService service = new FlightService(null, new AirDataServiceImpl());

        while (true) {
            System.out.println("Enter 'GET_DEPARTIMG_FLIGHTS' to view departing flights, 'GET_ARRIVING_FLIGHTS' for arriving flights, or 'exit' to quit:");
            String input = scanner.nextLine();
            Command command = Command.valueOf(input.toUpperCase());
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting the application. Goodbye!");
                break;
            }

            switch (command) {
                case GET_DEPARTIMG_FLIGHTS:
                    List<FlightDto> departingFlights = service.getDepartingFlights();
                    System.out.println("///////// Departing Flights //////////");
                    printFlights(departingFlights);
                    break;

                case GET_ARRIVING_FLIGHTS:
                    List<FlightDto> arrivingFlights = service.getArrivingFlights();
                    System.out.println("///////// Arriving Flights //////////");
                    printFlights(arrivingFlights);
                    break;

                case EXIT:
                    System.out.println("Invalid input. Please try again.");
                    break;
            }
        }
    }

    private static void printFlights(List<FlightDto> flights) {
        for (FlightDto flight : flights) {
            System.out.println("Flight Number: " + flight.getFlightNumber());
            System.out.println("Airline Name: " + flight.getAirlineName());
            System.out.println("Departure Airport: " + flight.getDepartureAirport());
            System.out.println("Arrival Airport: " + flight.getArrivalAirport());
            System.out.println("Departure Time: " + flight.getDepartureTime());
            System.out.println("Arrival Time: " + flight.getArrivalTime());
            System.out.println("-------------------------------------");
        }
    }
}
