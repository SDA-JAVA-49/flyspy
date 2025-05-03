package al.sda.flyspy.domain.airport.model;

import al.sda.flyspy.domain.airport.model.entity.Airport;
import al.sda.flyspy.shared.Repository;

import java.util.Optional;
import java.util.Set;

public class AirportRepository implements Repository<Airport, String> {
    @Override
    public Optional<Airport> findById(String identifier) {
        return Optional.empty();
    }

    @Override
    public Airport save(Airport object) {
        return null;
    }

    @Override
    public Set<Airport> getAll() {
        return Set.of();
    }

    @Override
    public void delete(String s) {

    }
}
