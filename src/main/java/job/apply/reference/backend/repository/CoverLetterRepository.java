package job.apply.reference.backend.repository;

import job.apply.reference.backend.domain.CoverLetter;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the CoverLetter entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CoverLetterRepository extends JpaRepository<CoverLetter, Long> {

    public boolean existsCoverLetterByName(String name);

    public CoverLetter getCoverLetterByName(String name);
}
