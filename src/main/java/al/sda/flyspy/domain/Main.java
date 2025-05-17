package al.sda.flyspy.domain;

import al.sda.flyspy.domain.flight.repository.FlightRepository;
import al.sda.flyspy.domain.flight.service.AirDataServiceImpl;
import al.sda.flyspy.domain.flight.service.FlightService;
import al.sda.flyspy.shared.util.JpaUtil;
import al.sda.flyspy.shared.util.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.logging.Logger;

public class Main {
    private static Logger log = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        EntityManagerFactory managerFactory = null;
        EntityManager entityManager = null;
        try {
            log.info("Starting up...");
            managerFactory = JpaUtil.getEntityManagerFactory();
            entityManager = managerFactory.createEntityManager();
            Repository repository = new FlightRepository(entityManager);
            FlightService dataService = new FlightService(repository, new AirDataServiceImpl());
            dataService.getFlightList();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        finally {
            entityManager.close();
            managerFactory.close();
        }

    }
}
