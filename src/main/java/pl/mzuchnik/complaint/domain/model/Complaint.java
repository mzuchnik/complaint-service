package pl.mzuchnik.complaint.domain.model;

import java.time.OffsetDateTime;

public class Complaint {

    private ComplaintId complaintId;
    private ProductId productId;
    private SubmitterId submitterId;
    private String content;
    private OffsetDateTime createdAt;
    private Country country;
    private ComplaintCounter counter;

    Complaint(ComplaintId complaintId,
              ProductId productId,
              SubmitterId submitterId,
              String content,
              OffsetDateTime createdAt,
              Country country,
              ComplaintCounter counter) {
        validate(complaintId, productId, submitterId, content, createdAt, country, counter);
        this.complaintId = complaintId;
        this.productId = productId;
        this.submitterId = submitterId;
        this.content = content;
        this.createdAt = createdAt;
        this.country = country;
        this.counter = counter;
    }

    public void incrementCounter() {
        this.counter = counter.increment();
    }

    public void changeContent(String content) {
        validateContent(content);
        this.content = content;
    }

    private static void validate(ComplaintId complaintId, ProductId productId, SubmitterId submitterId, String content, OffsetDateTime createdAt, Country country, ComplaintCounter counter) {
        validateContent(content);

        if(complaintId == null) {
            throw new IllegalArgumentException("Complaint id cannot be null");
        }
        if(productId == null) {
            throw new IllegalArgumentException("Product id cannot be null");
        }
        if(submitterId == null) {
            throw new IllegalArgumentException("Submitter id cannot be null");
        }
        if(createdAt == null) {
            throw new IllegalArgumentException("CreatedAt cannot be null");
        }
        if(country == null) {
            throw new IllegalArgumentException("Country cannot be null");
        }
        if(counter == null) {
            throw new IllegalArgumentException("Counter cannot be null");
        }
    }

    private static void validateContent(String content) {
        if(content == null || content.isBlank()) {
            throw new IllegalArgumentException("Content cannot be null or empty");
        }
    }

    public ComplaintId getComplaintId() {
        return complaintId;
    }

    public ProductId getProductId() {
        return productId;
    }

    public SubmitterId getSubmitterId() {
        return submitterId;
    }

    public String getContent() {
        return content;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public Country getCountry() {
        return country;
    }

    public ComplaintCounter getCounter() {
        return counter;
    }
}
