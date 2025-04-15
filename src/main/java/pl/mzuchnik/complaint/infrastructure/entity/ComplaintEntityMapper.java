package pl.mzuchnik.complaint.infrastructure.entity;

import pl.mzuchnik.complaint.domain.ComplaintFacade;
import pl.mzuchnik.complaint.domain.model.*;

public class ComplaintEntityMapper {

    public static Complaint toComplaint(ComplaintEntity complaintEntity) {
        return ComplaintFactory.create(
                new ComplaintId(complaintEntity.getComplaintUuid()),
                new ProductId(complaintEntity.getProductUuid()),
                new SubmitterId(complaintEntity.getSubmitter()),
                complaintEntity.getContent(),
                complaintEntity.getCreateDate(),
                new Country(complaintEntity.getCountry()),
                new ComplaintCounter(complaintEntity.getCounter())
        );
    }

    public static ComplaintEntity toComplaintEntity(Complaint complaint) {
        ComplaintEntity complaintEntity = new ComplaintEntity();
        complaintEntity.setComplaintUuid(complaint.getComplaintId().uuid());
        complaintEntity.setProductUuid(complaint.getProductId().productUid());
        complaintEntity.setSubmitter(complaint.getSubmitterId().email());
        complaintEntity.setContent(complaint.getContent());
        complaintEntity.setCreateDate(complaint.getCreatedAt());
        complaintEntity.setCountry(complaint.getCountry().country());
        complaintEntity.setCounter(complaint.getCounter().counter());
        return complaintEntity;
    }
}
