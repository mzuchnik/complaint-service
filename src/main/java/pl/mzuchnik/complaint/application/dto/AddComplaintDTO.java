package pl.mzuchnik.complaint.application.dto;

import java.util.UUID;

public record AddComplaintDTO(
        UUID productId,
        String reporterEmail,
        String content,
        String ip
) {
}
