package pl.mzuchnik.complaint.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.mzuchnik.complaint.domain.model.*;
import pl.mzuchnik.complaint.infrastructure.adapters.InMemoryComplaintRepository;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ComplaintFacadeTest {

    ComplaintFacade complaintFacade;
    SubmitComplaintCommand submitComplaintCommand;

    @BeforeEach
    void setUp() {
        complaintFacade = new ComplaintFacade(new InMemoryComplaintRepository());

        ProductId productId = new ProductId(UUID.randomUUID());
        SubmitterId submitterId = new SubmitterId("test@test.com");
        String content = "This is a test";
        Country country = new Country("Poland");
        submitComplaintCommand = new SubmitComplaintCommand(productId, submitterId, content, country);
    }


    @Test
    void shouldReturnComplaintWithIdAfterSubmit() {
        Complaint complaint = complaintFacade.submitComplaint(submitComplaintCommand);

        assertNotNull(complaint.getComplaintId());
    }

    @Test
    void shouldReturnComplaintAfterSecondSubmitWithCounterEquals2(){
        complaintFacade.submitComplaint(submitComplaintCommand);
        Complaint complaint = complaintFacade.submitComplaint(submitComplaintCommand);

        assertEquals(2, complaint.getCounter().counter());
    }

    @Test
    void shouldReturnComplaintAfterSecondSubmitWithSameId(){
        Complaint complaint1 = complaintFacade.submitComplaint(submitComplaintCommand);
        Complaint complaint2 = complaintFacade.submitComplaint(submitComplaintCommand);

        assertEquals(complaint1.getComplaintId(), complaint2.getComplaintId());
    }

    @Test
    void shouldReturnComplaintAfterSecondSubmitWithSameCreateDate(){
        Complaint complaint1 = complaintFacade.submitComplaint(submitComplaintCommand);
        Complaint complaint2 = complaintFacade.submitComplaint(submitComplaintCommand);

        assertEquals(complaint1.getCreatedAt(), complaint2.getCreatedAt());
    }

    @Test
    void shouldReturnComplaintAfterSecondSubmitWithSameContent(){
        Complaint complaint1 = complaintFacade.submitComplaint(submitComplaintCommand);
        Complaint complaint2 = complaintFacade.submitComplaint(submitComplaintCommand);

        assertEquals(complaint1.getContent(), complaint2.getContent());
    }

    @Test
    void shouldReturnComplaintAfterSecondSubmitWithSameCountry(){
        Complaint complaint1 = complaintFacade.submitComplaint(submitComplaintCommand);
        Complaint complaint2 = complaintFacade.submitComplaint(submitComplaintCommand);

        assertEquals(complaint1.getCountry(), complaint2.getCountry());
    }


}