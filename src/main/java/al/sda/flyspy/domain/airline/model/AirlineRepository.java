package al.sda.flyspy.domain.airline.model;

import al.sda.flyspy.domain.airline.model.entity.Airline;
import al.sda.flyspy.shared.Repository;
import al.sda.flyspy.shared.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class AirlineRepository implements Repository<Airline, String> {

    @Override
    public Optional<Airline> findById(String id) {
        try(EntityManager manager = JpaUtil.getEntityManagerFactory().createEntityManager()){
            Airline airline =  manager.find(Airline.class, id);
            return Optional.ofNullable(airline);
        }
    }

    @Override
    public Airline save(Airline airline) {
        try(EntityManager manager = JpaUtil.getEntityManagerFactory().createEntityManager()){
            manager.getTransaction().begin();
            Airline result = manager.merge(airline);
            manager.getTransaction().commit();
            return result;
        }
    }

    @Override
    public Set<Airline> getAll() {
        try (EntityManager manager = JpaUtil.getEntityManagerFactory().createEntityManager()){
            String queryStr = "SELECT a FROM Airline a";
            List<Airline> airlines = manager.createQuery(queryStr, Airline.class).getResultList();
            return new HashSet<>(airlines);
        }
    }

    @Override
    public void delete(String id) {
        try(EntityManager manager = JpaUtil.getEntityManagerFactory().createEntityManager()){
            manager.getTransaction().begin();
            Airline airline = manager.find(Airline.class, id);
            if(airline != null){
                manager.remove(airline);
            }
            manager.getTransaction().commit();
        }
    }
}
