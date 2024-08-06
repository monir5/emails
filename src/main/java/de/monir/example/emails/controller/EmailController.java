package de.monir.example.emails.controller;

import de.monir.example.emails.dto.EmailUpdateDTO;
import de.monir.example.emails.model.Email;
import de.monir.example.emails.model.Emails;
import de.monir.example.emails.service.EmailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/emails")
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @Operation(summary = "Insert an Email", tags = {"Create"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Create an Email",
                content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Email.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Email supplied", content = @Content)
    })
    @PostMapping("/create")
    public ResponseEntity<Email> create(@Valid @Parameter(description = "the full content of an Email", schema = @Schema(implementation = Email.class)) @RequestBody Email email) {
        return new ResponseEntity<>(emailService.create(email), HttpStatus.CREATED);
    }

    @Operation(summary = "Bulk insert of Emails", tags = {"Create"})
    @PostMapping("/create/bulk")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Bulk creation of Emails",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Emails.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Email supplied", content = @Content)
    })
    public ResponseEntity<Emails> createBulk(@Valid @Parameter(description = "A list of Emails", schema = @Schema(implementation = Emails.class))@RequestBody Emails emails) {
        Emails savedEmails = new Emails();
        savedEmails.setEmails(emailService.createBulk(emails.getEmails()));
        return new ResponseEntity<>(savedEmails, HttpStatus.CREATED);
    }

    @Operation(summary = "Update an Email", tags = {"Update"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Update of Email with state DRAFT only",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = EmailUpdateDTO.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Email-Id or Email content supplied", content = @Content),
            @ApiResponse(responseCode = "404", description = "Email not found", content = @Content)
    })
    @PutMapping("/update/{emailId}")
    public ResponseEntity<Email> update(@PathVariable Long emailId, @Valid @RequestBody EmailUpdateDTO emailUpdateDTO) {
        return new ResponseEntity<>(emailService.update(emailId, emailUpdateDTO), HttpStatus.OK);
    }

    @Operation(summary = "Delete an Email", tags = {"Delete"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deletion of an Email by email-ID", content = @Content),
            @ApiResponse(responseCode = "404", description = "Email not found", content = @Content)
    })
    @DeleteMapping("/delete/{emailId}")
    public ResponseEntity<?> delete(@PathVariable Long emailId) {
        emailService.delete(emailId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Bulk deletion of Emails", tags = {"Delete"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Bulk deletion of an Email by the list of email-IDs", content = @Content),
            @ApiResponse(responseCode = "404", description = "Email not found", content = @Content)
    })
    @DeleteMapping("/delete/bulk")
    public ResponseEntity<?> deleteBulk(@RequestBody List<Long> emailIds) {
        emailService.deleteBulk(emailIds);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Get all Emails", tags = {"Read"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = " All Emails", content = @Content),
            @ApiResponse(responseCode = "404", description = "Email not found", content = @Content)
    })
    @GetMapping("/all")
    public ResponseEntity<List<Email>> findAll() {
        return new ResponseEntity<>(emailService.findAll(), HttpStatus.OK);
    }

    @Operation(summary = "Get an Email", tags = {"Read"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = " An Email by email-ID", content = @Content),
            @ApiResponse(responseCode = "404", description = "Email not found", content = @Content)
    })
    @GetMapping("/{emailId}")
    public ResponseEntity<Email> findById(@PathVariable Long emailId) {
        Optional<Email> emailOptional = emailService.findById(emailId);
        if (emailOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(emailOptional.get(), HttpStatus.OK);
    }

    @Operation(summary = "Get all Emails by email-from", tags = {"Read"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = " All Emails by email-from", content = @Content),
            @ApiResponse(responseCode = "404", description = "Email not found", content = @Content)
    })
    @GetMapping("/query/from/{emailFrom}")
    public ResponseEntity<List<Email>> findAllByFrom(@PathVariable String emailFrom) {
        return new ResponseEntity<>(emailService.findAllByFrom(emailFrom), HttpStatus.OK);
    }
}
