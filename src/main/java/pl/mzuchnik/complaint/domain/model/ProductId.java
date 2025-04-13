package pl.mzuchnik.complaint.domain.model;

import java.util.UUID;

public record ProductId(UUID productUid) {
    public ProductId {
        if(productUid == null) {
            throw new IllegalArgumentException("Product uid cannot be null");
        }
    }
}
