package se331.lab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import se331.lab.entity.Event;
import se331.lab.entity.Organizer;
import se331.lab.service.EventService;
import se331.lab.service.OrganizerService;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class EventController {

    final EventService eventService;
    final OrganizerService organizerService;

    @GetMapping("events")
    public ResponseEntity<?> getEventLists(@RequestParam(value = "_limit", required = false) Integer perPage
    ,@RequestParam(value = "_page",required = false) Integer page) {
        List<Event> output = null;
        Integer eventSize = eventService.getEventSize();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("x-total-count", String.valueOf(eventSize));
        try {
            output = eventService.getEvents(perPage, page);
            return new ResponseEntity<>(output, responseHeaders, HttpStatus.OK);
        }catch (IndexOutOfBoundsException ex) {
            return new ResponseEntity<>(output, responseHeaders, HttpStatus.OK);
        }
    }

    @GetMapping("events/{id}")
    public ResponseEntity<?> getEvent(@PathVariable ("id") long id) {
        Event output = eventService.getEvent(id);
        if (output != null) {
            return ResponseEntity.ok(output);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"The given id is not found");
        }
    }

    @GetMapping("organizers")
    public ResponseEntity<?> getOrganizerLists(@RequestParam(value = "_limit", required = false) Integer perPage
            ,@RequestParam(value = "_page",required = false) Integer page) {
        List<Organizer> output = null;
        Integer eventSize = organizerService.getOrganizerSize();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("x-total-count", String.valueOf(eventSize));
        try {
            output = organizerService.getOrganizers(perPage, page);
            return new ResponseEntity<>(output, responseHeaders, HttpStatus.OK);
        }catch (IndexOutOfBoundsException ex) {
            return new ResponseEntity<>(output, responseHeaders, HttpStatus.OK);
        }
    }

    @GetMapping("organizers/{id}")
    public ResponseEntity<?> getOrganizer(@PathVariable ("id") long id) {
        Organizer output = organizerService.getOrganizer(id);
        if (output != null) {
            return ResponseEntity.ok(output);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"The given id is not found");
        }
    }

}
