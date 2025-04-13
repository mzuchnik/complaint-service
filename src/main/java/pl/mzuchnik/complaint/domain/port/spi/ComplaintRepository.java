package pl.mzuchnik.complaint.domain.port.spi;

import pl.mzuchnik.complaint.domain.model.Complaint;
import pl.mzuchnik.complaint.domain.model.ComplaintId;
import pl.mzuchnik.complaint.domain.model.ProductId;
import pl.mzuchnik.complaint.domain.model.SubmitterId;

import java.util.List;
import java.util.Optional;

public interface ComplaintRepository {

    Optional<Complaint> findById(ComplaintId complaintId);

    Optional<Complaint> findByProductIdAndSubmitterId(ProductId productId, SubmitterId submitterId);

    Complaint save(Complaint complaint);

    List<Complaint> findAll();

}
