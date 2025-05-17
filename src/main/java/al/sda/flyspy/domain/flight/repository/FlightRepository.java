package al.sda.flyspy.domain.flight.repository;

import al.sda.flyspy.domain.flight.model.entity.Flight;
import al.sda.flyspy.shared.util.Repository;
import jakarta.persistence.EntityManager;

import java.util.Optional;
import java.util.Set;

public class FlightRepository implements Repository<Flight, Integer> {
    private final EntityManager entityManager;

    public FlightRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Flight> findById(Integer identifier) {
        return Optional.empty();
    }

    @Override
    public Flight save(Flight object) {
        return null;
    }

    @Override
    public Set<Flight> getAll() {
        return Set.of();
    }

    @Override
    public void delete(Integer integer) {

    }
}
