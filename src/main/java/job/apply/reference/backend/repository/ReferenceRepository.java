package job.apply.reference.backend.repository;

import job.apply.reference.backend.domain.Reference;
import job.apply.reference.backend.service.dto.ReferenceDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * Spring Data  repository for the Reference entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ReferenceRepository extends JpaRepository<Reference, Long> {
//    @Query("select r from  Reference r where r.company LIKE ?#{[0]}")
//    Page<Reference> findByCompanyMatchesRegex(String regex, Pageable pageable);

    @Query("select r from  Reference r where r.company LIKE ?#{[0]}")
    Page<Reference> findByCompanyMatchesRegex(String regex, Pageable pageable);
}
