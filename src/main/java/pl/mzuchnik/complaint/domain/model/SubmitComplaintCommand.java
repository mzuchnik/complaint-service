package pl.mzuchnik.complaint.domain.model;

public record SubmitComplaintCommand(ProductId productId,
                                     SubmitterId submitterId,
                                     String content,
                                     Country country) {
}
