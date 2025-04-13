package pl.mzuchnik.complaint.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.mzuchnik.complaint.application.CountryByIPResolver;
import pl.mzuchnik.complaint.application.dto.AddComplaintDTO;
import pl.mzuchnik.complaint.domain.ComplaintService;
import pl.mzuchnik.complaint.domain.model.*;

@Component
@RequiredArgsConstructor
public class AddComplaintUseCase {

    private final CountryByIPResolver countryByIPResolver;
    private final ComplaintService complaintService;

    public ComplaintId addComplaint(AddComplaintDTO addComplaintDTO) {
        Country countryByIP = countryByIPResolver.getCountryByIP(addComplaintDTO.ip());
        ProductId productId = new ProductId(addComplaintDTO.productId());
        SubmitterId submitterId = new SubmitterId(addComplaintDTO.reporterEmail());

        Complaint complaint = complaintService.addComplaint(productId, submitterId, addComplaintDTO.content(), countryByIP);

        return complaint.getComplaintId();
    }

}
