package al.sda.flyspy.domain;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory facotry = Persistence.createEntityManagerFactory("flyspy-pu");


    }
}
