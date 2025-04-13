package pl.mzuchnik.complaint.domain.model;

import java.util.UUID;

public record ComplaintId(
        UUID uuid
) {
    public ComplaintId{
        if(uuid == null){
            throw new IllegalArgumentException("Complaint id must not be null");
        }
    }

    public static ComplaintId createNew(){
        return new ComplaintId(UUID.randomUUID());
    }
}
