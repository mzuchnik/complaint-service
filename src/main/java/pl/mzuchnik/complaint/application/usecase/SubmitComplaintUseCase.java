package pl.mzuchnik.complaint.application.usecase;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.mzuchnik.complaint.application.CountryByIPResolver;
import pl.mzuchnik.complaint.application.dto.AddComplaintDTO;
import pl.mzuchnik.complaint.domain.ComplaintFacade;
import pl.mzuchnik.complaint.domain.model.*;

@Component
@RequiredArgsConstructor
public class SubmitComplaintUseCase {

    private final ComplaintFacade complaintFacade;
    private final CountryByIPResolver countryByIPResolver;

    @Transactional
    public ComplaintId submitComplaint(AddComplaintDTO addComplaintDTO) {
        Country countryByIP = countryByIPResolver.getCountryByIP(addComplaintDTO.ip());

        Complaint complaint = complaintFacade.submitComplaint(new SubmitComplaintCommand(
                new ProductId(addComplaintDTO.productId()),
                new SubmitterId(addComplaintDTO.reporterEmail()),
                addComplaintDTO.content(),
                countryByIP
        ));

        return complaint.getComplaintId();
    }

}
