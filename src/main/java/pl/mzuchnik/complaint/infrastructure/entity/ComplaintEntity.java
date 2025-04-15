package pl.mzuchnik.complaint.infrastructure.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "complaints")
@Setter
@Getter
public class ComplaintEntity {

    @Id
    @Column(name = "complaint_uuid")
    private UUID complaintUuid;

    @Column(name = "product_uuid")
    private UUID productUuid;

    @Column(name = "reporter_email")
    private String submitter;

    @Column(name = "content")
    private String content;

    @Column(name = "country")
    private String country;

    @Column(name = "counter")
    private int counter;

    @Column(name = "create_date")
    private OffsetDateTime createDate;
}
