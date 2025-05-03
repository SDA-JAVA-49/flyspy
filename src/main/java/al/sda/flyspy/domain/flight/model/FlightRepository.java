package al.sda.flyspy.domain.flight.model;

import al.sda.flyspy.domain.flight.model.entity.Flight;
import al.sda.flyspy.shared.Repository;

import java.util.Optional;
import java.util.Set;

public class FlightRepository implements Repository<Flight, Long>{


    @Override
    public Optional<Flight> findById(Long identifier) {

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
    public void delete(Long aLong) {

    }
}
