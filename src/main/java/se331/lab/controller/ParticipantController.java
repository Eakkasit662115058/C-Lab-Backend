package se331.lab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se331.lab.Util.LabMapper;
import se331.lab.service.ParticipantService;


@RestController
@RequiredArgsConstructor
public class ParticipantController {
    final ParticipantService participantService;

    @GetMapping("/participants")
    ResponseEntity<?> getOrganizers() {
        return ResponseEntity.ok(LabMapper.INSTANCE.getParticipantDto(participantService.getAllParticipant()));
    }
}
