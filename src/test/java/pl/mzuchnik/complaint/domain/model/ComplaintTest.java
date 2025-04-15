package pl.mzuchnik.complaint.domain.model;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ComplaintTest {

    @Test
    void shouldReturnComplaintWithStarterCounterEquals1() {
        ProductId productId = new ProductId(UUID.randomUUID());
        SubmitterId submitterId = new SubmitterId("test@test.com");
        String content = "This is a test";
        Country country = new Country("Poland");

        Complaint newComplaint = ComplaintFactory.createNew(productId, submitterId, content, country);

        assertEquals(1, newComplaint.getCounter().counter());
    }

    @Test
    void shouldReturnComplaintWithIncrementCounterEquals2() {
        ProductId productId = new ProductId(UUID.randomUUID());
        SubmitterId submitterId = new SubmitterId("test@test.com");
        String content = "This is a test";
        Country country = new Country("Poland");

        Complaint newComplaint = ComplaintFactory.createNew(productId, submitterId, content, country);

        newComplaint.incrementCounter();

        assertEquals(2, newComplaint.getCounter().counter());
    }

    @Test
    void shouldReturnNotNullUuidForNewComplaint() {
        ProductId productId = new ProductId(UUID.randomUUID());
        SubmitterId submitterId = new SubmitterId("test@test.com");
        String content = "This is a test";
        Country country = new Country("Poland");

        Complaint newComplaint = ComplaintFactory.createNew(productId, submitterId, content, country);

        assertNotNull(newComplaint.getComplaintId());
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenContentIsNull(){
        ProductId productId = new ProductId(UUID.randomUUID());
        SubmitterId submitterId = new SubmitterId("test@test.com");
        String content = null;
        Country country = new Country("Poland");

        assertThrows(IllegalArgumentException.class, () -> ComplaintFactory.createNew(productId, submitterId, content, country));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenContentIsEmpty(){
        ProductId productId = new ProductId(UUID.randomUUID());
        SubmitterId submitterId = new SubmitterId("test@test.com");
        String content = "";
        Country country = new Country("Poland");

        assertThrows(IllegalArgumentException.class, () -> ComplaintFactory.createNew(productId, submitterId, content, country));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWHenContentIsBlank(){
        ProductId productId = new ProductId(UUID.randomUUID());
        SubmitterId submitterId = new SubmitterId("test@test.com");
        String content = "          ";
        Country country = new Country("Poland");

        assertThrows(IllegalArgumentException.class, () -> ComplaintFactory.createNew(productId, submitterId, content, country));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenProductIdIsNull(){
        ProductId productId = null;
        SubmitterId submitterId = new SubmitterId("test@test.com");
        String content = "Simple content";
        Country country = new Country("Poland");

        assertThrows(IllegalArgumentException.class, () -> ComplaintFactory.createNew(productId, submitterId, content, country));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenSubmitterIdIsNull(){
        ProductId productId = new ProductId(UUID.randomUUID());
        SubmitterId submitterId = null;
        String content = "Simple content";
        Country country = new Country("Poland");

        assertThrows(IllegalArgumentException.class, () -> ComplaintFactory.createNew(productId, submitterId, content, country));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenCounterIsNull(){
        ProductId productId = new ProductId(UUID.randomUUID());
        SubmitterId submitterId = new SubmitterId("test@test.com");
        String content = "Simple content";
        Country country = null;

        assertThrows(IllegalArgumentException.class, () -> ComplaintFactory.createNew(productId, submitterId, content, country));
    }
}