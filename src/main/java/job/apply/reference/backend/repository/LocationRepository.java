package job.apply.reference.backend.repository;

import job.apply.reference.backend.domain.Location;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Location entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

    public boolean existsLocationByLocation(String name);

    public Location getLocationByLocation(String name);
}
