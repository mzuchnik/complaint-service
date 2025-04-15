package pl.mzuchnik.complaint.infrastructure.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ComplaintEntityRepository extends JpaRepository<ComplaintEntity, UUID> {

    Optional<ComplaintEntity> findByProductUuidAndSubmitter(UUID productUuid, String submitter);
}
