package pl.mzuchnik.complaint.domain;

import pl.mzuchnik.complaint.domain.model.*;
import pl.mzuchnik.complaint.domain.port.spi.ComplaintRepository;

import java.util.Optional;

public class ComplaintService {

    private final ComplaintRepository complaintRepository;

    public ComplaintService(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    public Complaint addComplaint(ProductId productId,
                             SubmitterId submitterId,
                             String content,
                             Country country) {

        Optional<Complaint> byProductIdAndSubmitterId =
                complaintRepository.findByProductIdAndSubmitterId(productId, submitterId);

        if (byProductIdAndSubmitterId.isPresent()) {
            Complaint complaint = byProductIdAndSubmitterId.get();
            complaint.incrementCounter();
            return complaintRepository.save(complaint);
        }

        return complaintRepository.save(ComplaintFactory.createNew(productId, submitterId, content, country));
    }
}
