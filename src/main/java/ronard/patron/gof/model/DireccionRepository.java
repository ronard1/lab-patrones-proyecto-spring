package ronard.patron.gof.model;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DireccionRepository extends CrudRepository<Direccion, String> {
}
