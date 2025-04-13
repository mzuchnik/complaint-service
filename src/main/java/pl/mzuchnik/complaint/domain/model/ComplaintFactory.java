package pl.mzuchnik.complaint.domain.model;

import java.time.OffsetDateTime;

public class ComplaintFactory {

    public static Complaint create(ComplaintId complaintId,
                                   ProductId productId,
                                   SubmitterId submitterId,
                                   String content,
                                   OffsetDateTime createDate,
                                   Country country,
                                   ComplaintCounter counter) {

        return new Complaint(complaintId,
                productId,
                submitterId,
                content,
                createDate,
                country,
                counter);
    }

    public static Complaint createNew(ProductId productId, SubmitterId submitterId, String content, Country country) {

        return create(ComplaintId.createNew(),
                productId,
                submitterId,
                content,
                OffsetDateTime.now(),
                country,
                ComplaintCounter.createNew());
    }


}
