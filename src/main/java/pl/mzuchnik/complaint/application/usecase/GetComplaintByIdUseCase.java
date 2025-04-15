package pl.mzuchnik.complaint.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mzuchnik.complaint.domain.model.Complaint;
import pl.mzuchnik.complaint.domain.model.ComplaintId;
import pl.mzuchnik.complaint.domain.port.spi.ComplaintRepository;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class GetComplaintByIdUseCase {

    private final ComplaintRepository complaintRepository;

    public Optional<Complaint> getByComplaintId(UUID complaintId) {
        // Some extra validation for authorities
        return complaintRepository.findById(new ComplaintId(complaintId));
    };
}
