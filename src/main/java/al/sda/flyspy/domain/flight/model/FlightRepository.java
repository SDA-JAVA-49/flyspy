package al.sda.flyspy.domain.flight.model;

import al.sda.flyspy.domain.flight.model.entity.Flight;
import al.sda.flyspy.shared.util.Repository;
import al.sda.flyspy.shared.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class FlightRepository implements Repository<Flight, Long> {


    @Override
    public Optional<Flight> findById(Long id) {
        try (EntityManager manager = JpaUtil.getEntityManagerFactory().createEntityManager()) {
            Flight flight = manager.find(Flight.class, id);
            return Optional.ofNullable(flight);
        }
    }

    @Override
    public Flight save(Flight flight) {
        try (EntityManager manager = JpaUtil.getEntityManagerFactory().createEntityManager()) {
            manager.getTransaction().begin();
            Flight result = manager.merge(flight);
            manager.getTransaction().commit();
            return flight;
        }
    }

    @Override
    public Set<Flight> getAll() {
        try (EntityManager manager = JpaUtil.getEntityManagerFactory().createEntityManager()) {
            String queryStr = "SELECT f FROM Flight f";
            List<Flight> flights = manager.createQuery(queryStr, Flight.class)
                    .getResultList();
            return new HashSet<>(flights);
        }
    }

    @Override
    public void delete(Long id) {
        try (EntityManager manager = JpaUtil.getEntityManagerFactory().createEntityManager()) {
            manager.getTransaction().begin();
            Flight flight = manager.find(Flight.class, id);
            if (flight != null) {
                manager.remove(flight);
            }
            manager.getTransaction().commit();
        }
    }
}
