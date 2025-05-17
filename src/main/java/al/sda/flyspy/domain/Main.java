package al.sda.flyspy.domain;

import al.sda.flyspy.domain.flight.model.entity.Flight;
import al.sda.flyspy.domain.flight.service.AirDataServiceImpl;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws URISyntaxException {
       // EntityManagerFactory facotry = Persistence.createEntityManagerFactory("flyspy-pu");
        AirDataServiceImpl service = new AirDataServiceImpl();
        Map<String, String> params = new HashMap<>();
        params.put("dep_iata","TIA");

        service.getFlights(params);



    }
}
