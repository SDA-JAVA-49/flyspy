package al.sda.flyspy.domain.airport.model;

import al.sda.flyspy.domain.airport.model.entity.Airport;
import al.sda.flyspy.shared.util.Repository;
import al.sda.flyspy.shared.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class AirportRepository implements Repository<Airport, String> {
    @Override
    public Optional<Airport> findById(String id) {
        try (EntityManager manager = JpaUtil.getEntityManagerFactory().createEntityManager()) {
            Airport airport = manager.find(Airport.class, id);
            return Optional.ofNullable(airport);
        }
    }

    @Override
    public Airport save(Airport airport) {
        try (EntityManager manager = JpaUtil.getEntityManagerFactory().createEntityManager()) {
            manager.getTransaction().begin();
            Airport result = manager.merge(airport);
            manager.getTransaction().commit();
            return result;
        }
    }

    @Override
    public Set<Airport> getAll() {
        try (EntityManager manager = JpaUtil.getEntityManagerFactory().createEntityManager()) {
            String queryStr = "SELECT a FROM Airport a";
            List<Airport> airports = manager.createQuery(queryStr, Airport.class).getResultList();
            return new HashSet<>(airports);
        }
    }

    @Override
    public void delete(String id) {
        try (EntityManager manager = JpaUtil.getEntityManagerFactory().createEntityManager()) {
            manager.getTransaction().begin();
            Airport airport = manager.find(Airport.class, id);
            if (airport != null) {
                manager.remove(airport);
            }
            manager.getTransaction().commit();
        }
    }
}
