package pl.mzuchnik.complaint.infrastructure.adapters;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.mzuchnik.complaint.domain.model.Complaint;
import pl.mzuchnik.complaint.domain.model.ComplaintId;
import pl.mzuchnik.complaint.domain.model.ProductId;
import pl.mzuchnik.complaint.domain.model.SubmitterId;
import pl.mzuchnik.complaint.domain.port.spi.ComplaintRepository;
import pl.mzuchnik.complaint.infrastructure.entity.ComplaintEntityMapper;
import pl.mzuchnik.complaint.infrastructure.entity.ComplaintEntityRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class PostgresComplaintRepository implements ComplaintRepository {

    private final ComplaintEntityRepository complaintEntityRepository;
    private final EntityManager entityManager;

    @Override
    public Optional<Complaint> findById(ComplaintId complaintId) {
        return complaintEntityRepository.findById(complaintId.uuid())
                .map(ComplaintEntityMapper::toComplaint);
    }

    @Override
    public Optional<Complaint> findByProductIdAndSubmitterId(ProductId productId, SubmitterId submitterId) {
        return complaintEntityRepository.findByProductUuidAndSubmitter(productId.productUid(), submitterId.email())
                .map(ComplaintEntityMapper::toComplaint);
    }

    @Override
    public Complaint save(Complaint complaint) {
        return ComplaintEntityMapper.toComplaint(complaintEntityRepository.save(ComplaintEntityMapper.toComplaintEntity(complaint)));
    }

    @Override
    public List<Complaint> findAll() {
        return complaintEntityRepository.findAll().stream().map(ComplaintEntityMapper::toComplaint).collect(Collectors.toList());
    }
}
