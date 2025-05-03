package al.sda.flyspy.domain.airline.model;

import al.sda.flyspy.domain.airline.model.entity.Airline;
import al.sda.flyspy.shared.Repository;

import java.util.Optional;
import java.util.Set;

public class AirlineRepository implements Repository<Airline, String> {
    @Override
    public Optional<Airline> findById(String identifier) {
        return Optional.empty();
    }

    @Override
    public Airline save(Airline object) {
        return null;
    }

    @Override
    public Set<Airline> getAll() {
        return Set.of();
    }

    @Override
    public void delete(String s) {

    }
}
