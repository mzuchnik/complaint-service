package pl.mzuchnik.complaint.infrastructure.adapters;

import pl.mzuchnik.complaint.domain.model.Complaint;
import pl.mzuchnik.complaint.domain.model.ComplaintId;
import pl.mzuchnik.complaint.domain.model.ProductId;
import pl.mzuchnik.complaint.domain.model.SubmitterId;
import pl.mzuchnik.complaint.domain.port.spi.ComplaintRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryComplaintRepository implements ComplaintRepository {

    private static final ConcurrentHashMap<ComplaintId, Complaint> hashMap = new ConcurrentHashMap<>();

    @Override
    public Optional<Complaint> findById(ComplaintId complaintId) {
        return Optional.ofNullable(hashMap.get(complaintId));
    }

    @Override
    public Optional<Complaint> findByProductIdAndSubmitterId(ProductId productId, SubmitterId submitterId) {
        return hashMap.values()
                .stream()
                .filter(x -> x.getProductId().equals(productId) && x.getSubmitterId().equals(submitterId))
                .findFirst();
    }

    @Override
    public Complaint save(Complaint complaint) {
        hashMap.put(complaint.getComplaintId(), complaint);

        return complaint;
    }

    @Override
    public List<Complaint> findAll() {
        return hashMap.values().stream().toList();
    }
}
