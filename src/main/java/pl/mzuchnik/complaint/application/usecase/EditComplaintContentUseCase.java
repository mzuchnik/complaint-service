package pl.mzuchnik.complaint.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.mzuchnik.complaint.domain.exception.NotFoundComplaintException;
import pl.mzuchnik.complaint.domain.model.Complaint;
import pl.mzuchnik.complaint.domain.model.ComplaintId;
import pl.mzuchnik.complaint.domain.port.spi.ComplaintRepository;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EditComplaintContentUseCase {

    private final ComplaintRepository complaintRepository;

    @Transactional
    public void editComplaintContent(UUID complaintId, String content){
        Complaint complaint = complaintRepository.findById(new ComplaintId(complaintId))
                .orElseThrow(NotFoundComplaintException::new);
        complaint.changeContent(content);
        complaintRepository.save(complaint);
    }
}
