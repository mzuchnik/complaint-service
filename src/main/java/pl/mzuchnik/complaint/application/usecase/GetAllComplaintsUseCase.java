package pl.mzuchnik.complaint.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mzuchnik.complaint.domain.model.Complaint;
import pl.mzuchnik.complaint.domain.port.spi.ComplaintRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllComplaintsUseCase {

    private final ComplaintRepository complaintRepository;

    public List<Complaint> getAllComplaints(){
        // Some extra validation for authorities
        return complaintRepository.findAll();
    }
}
