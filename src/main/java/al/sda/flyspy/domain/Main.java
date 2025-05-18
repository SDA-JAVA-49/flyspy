package al.sda.flyspy.domain;

import al.sda.flyspy.domain.comand.MenuOption;
import al.sda.flyspy.domain.flight.model.dto.FlightDto;
import al.sda.flyspy.domain.flight.repository.FlightRepository;
import al.sda.flyspy.domain.flight.service.AirDataServiceImpl;
import al.sda.flyspy.domain.flight.service.FlightService;
import al.sda.flyspy.shared.util.JpaUtil;
import al.sda.flyspy.shared.util.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    private static final Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        EntityManagerFactory managerFactory = null;
        EntityManager entityManager = null;
        Scanner scanner = new Scanner(System.in);
        try {
            log.info("Starting up...");
            managerFactory = JpaUtil.getEntityManagerFactory();
            entityManager = managerFactory.createEntityManager();
            Repository repository = new FlightRepository(entityManager);
            FlightService dataService = new FlightService(repository, new AirDataServiceImpl());

            while (true){
                System.out.println("\n===== FLIGHT MENU =====");
                System.out.println("1. Show departing flights");
                System.out.println("2. Show arriving flights");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");

                String choice = scanner.nextLine();
                MenuOption option = MenuOption.fromInput(choice);

                if(option == null){
                    System.out.println("Invalid option. Try again...");
                    continue;
                }

                switch (option){
                    case SHOW_DEPARTURES -> printFlights("Departing Flights", dataService.getDepartureFlightList());
                    case SHOW_ARRIVALS -> printFlights("Arriving Flights", dataService.getArrivingFlightList());
                    case EXIT -> {
                        System.out.println("BYE!");
                        return;
                    }
                }
            }
        } catch (RuntimeException e) {
            log.severe("Error: " + e.getMessage());
            e.printStackTrace();
        }
        finally {
            if(entityManager != null) entityManager.close();
            if(managerFactory != null) managerFactory.close();
            scanner.close();
        }

    }

    private static void printFlights(String title, List<FlightDto> flights){
        System.out.println("\n--- " + title + " ---");
        if(flights.isEmpty()){
            System.out.println("No flights found.");
        }
        else{
            for(FlightDto flight : flights){
                System.out.printf("Flight: %s | Airline: %s | From: %s at %s | To: %s at %s | Aircraft: %s%n",
                        flight.getFlightNumber(),
                        flight.getAirlineName(),
                        flight.getDepartureAirport(),
                        flight.getDepartureTime(),
                        flight.getArrivalAirport(),
                        flight.getArrivalTime(),
                        flight.getAircraftRegistration());
            }
        }
    }
}
