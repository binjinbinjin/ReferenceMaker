package job.apply.reference.backend.repository;

import job.apply.reference.backend.domain.ReferenceFile;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the ReferenceFile entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReferenceFileRepository extends JpaRepository<ReferenceFile, Long> {

    public boolean existsReferenceFileByFile(String name);

    public ReferenceFile getReferenceFileByFile(String name);
}
