package pl.mzuchnik.complaint.infrastructure.restapi;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.mzuchnik.complaint.application.dto.AddComplaintDTO;
import pl.mzuchnik.complaint.application.usecase.AddComplaintUseCase;
import pl.mzuchnik.complaint.application.usecase.EditComplaintContentUseCase;
import pl.mzuchnik.complaint.application.usecase.GetAllComplaintsUseCase;
import pl.mzuchnik.complaint.application.usecase.GetComplaintByIdUseCase;
import pl.mzuchnik.complaint.domain.exception.NotFoundComplaintException;
import pl.mzuchnik.complaint.domain.model.Complaint;
import pl.mzuchnik.complaint.domain.model.ComplaintId;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;

@RestController
@RequestMapping("/complaints")
@RequiredArgsConstructor
class ComplaintRestApi {

    private final AddComplaintUseCase addComplaintUseCase;
    private final GetAllComplaintsUseCase getAllComplaintsUseCase;
    private final GetComplaintByIdUseCase getComplaintByIdUseCase;
    private final EditComplaintContentUseCase editComplaintContentUseCase;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> addComplaint(@Valid @RequestBody AddComplaintRequest body,
                                      HttpServletRequest request,
                                      UriComponentsBuilder uriComponentsBuilder) {

        ComplaintId complaintId = addComplaintUseCase.addComplaint(
                new AddComplaintDTO(
                        body.productUuid,
                        body.reporterEmail,
                        body.content,
                        request.getRemoteAddr()));

        return ResponseEntity.created(uriComponentsBuilder.path("/complaints/{complaintId}").build(complaintId.uuid())).build();
    }

    @GetMapping(path = "/{complaintUuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ComplaintResponse> getComplaintByUuid(@PathVariable(name = "complaintUuid") UUID compaintUuid) {

        return ResponseEntity.ok(getComplaintByIdUseCase.getByComplaintId(compaintUuid)
                .map(toComplaintResponse())
                .orElseThrow(NotFoundComplaintException::new));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ComplaintResponse>> getAllComplaints() {
        List<ComplaintResponse> complaintResponses = getAllComplaintsUseCase.getAllComplaints().stream().map(toComplaintResponse()).toList();

        return ResponseEntity.ok(complaintResponses);
    }

    @PatchMapping(value = "/{complaintUuid}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> editComplaint(@PathVariable(name = "complaintUuid") UUID complaintUuid,
                                       @Valid @RequestBody ChangeContentRequest changeContentRequest) {

        editComplaintContentUseCase.editComplaintContent(complaintUuid, changeContentRequest.content);

        return ResponseEntity.ok().build();
    }

    private static Function<Complaint, ComplaintResponse> toComplaintResponse() {
        return complaint -> new ComplaintResponse(
                complaint.getComplaintId().uuid(),
                complaint.getProductId().productUid(),
                complaint.getSubmitterId().email(),
                complaint.getContent(),
                complaint.getCountry().country(),
                complaint.getCreatedAt(),
                complaint.getCounter().counter());
    }

    record AddComplaintRequest(@NotNull(message = "'productUuid' cannot be null") UUID productUuid,
                               @NotBlank(message = "'reporterEmail' cannot be blank") String reporterEmail,
                               String content) {
    }

    record ComplaintResponse(UUID complaintId, UUID productUuid, String reporterEmail, String content, String country,
                             OffsetDateTime createdAt, int counter) {

    }

    record ChangeContentRequest(@NotBlank(message = "'content' cannot be blank") String content) {

    }

}
