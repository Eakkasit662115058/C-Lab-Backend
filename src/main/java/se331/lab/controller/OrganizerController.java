package se331.lab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se331.lab.Util.LabMapper;
import se331.lab.entity.Organizer;
import se331.lab.service.OrganizerService;

@RestController
@RequiredArgsConstructor
public class OrganizerController {
    final OrganizerService organizerService;

    @GetMapping("/organizers")
    ResponseEntity<?> getOrganizers() {
        return ResponseEntity.ok(LabMapper.INSTANCE.getOrganizerDto(organizerService.getAllOrganizer()));
    }

    @GetMapping("/organizers/{id}")
    ResponseEntity<?> getOrganizer(@PathVariable ("id") long id) {
        Organizer output = organizerService.getOrganizer(id);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getOrganizerDto(output));
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"The given id is not found");
        }
    }

    @PostMapping("/organizers")
    public ResponseEntity<?> addEvent(@RequestBody Organizer organizer) {
        Organizer output = organizerService.save(organizer);
        return ResponseEntity.ok(LabMapper.INSTANCE.getOrganizerDto(output));
    }
}
