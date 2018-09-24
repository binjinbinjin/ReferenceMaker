package job.apply.reference.backend.repository;

import job.apply.reference.backend.domain.JobTitle;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the JobTitle entity.
 */
@SuppressWarnings("unused")
@Repository
public interface JobTitleRepository extends JpaRepository<JobTitle, Long> {

    public boolean existsJobTitleByJobTitle(String name);

    public JobTitle getJobTitleByJobTitle(String name);
}
