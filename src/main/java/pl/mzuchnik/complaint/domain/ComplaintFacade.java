package pl.mzuchnik.complaint.domain;

import pl.mzuchnik.complaint.domain.model.SubmitComplaintCommand;
import pl.mzuchnik.complaint.domain.model.*;
import pl.mzuchnik.complaint.domain.port.api.SubmitComplaint;
import pl.mzuchnik.complaint.domain.port.spi.ComplaintRepository;

import java.util.Optional;

public class ComplaintFacade implements SubmitComplaint {

    private final ComplaintRepository complaintRepository;

    public ComplaintFacade(ComplaintRepository complaintRepository) {
        this.complaintRepository = complaintRepository;
    }

    @Override
    public Complaint submitComplaint(SubmitComplaintCommand command) {
        Optional<Complaint> optionalExistComplaint =
                complaintRepository.findByProductIdAndSubmitterId(command.productId(), command.submitterId());

        if (optionalExistComplaint.isPresent()) {
            Complaint existComplaint = optionalExistComplaint.get();
            existComplaint.incrementCounter();
            return complaintRepository.save(existComplaint);
        }

        return complaintRepository.save(ComplaintFactory.createNew(
                command.productId(),
                command.submitterId(),
                command.content(),
                command.country()));
    }
}
