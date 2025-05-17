package al.sda.flyspy.shared.util;

import java.util.Optional;
import java.util.Set;

public interface Repository<E, ID> {
    Optional<E> findById(ID identifier);
    E save(E object);
    Set<E> getAll();
    void delete(ID id);
}