package pl.mzuchnik.complaint.domain.port.api;

import pl.mzuchnik.complaint.domain.model.SubmitComplaintCommand;
import pl.mzuchnik.complaint.domain.model.Complaint;

public interface SubmitComplaint {

    Complaint submitComplaint(SubmitComplaintCommand submitComplaintCommand);
}
