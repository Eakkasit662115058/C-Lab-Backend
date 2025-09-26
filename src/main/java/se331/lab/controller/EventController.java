package se331.lab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se331.lab.Util.LabMapper;
import se331.lab.entity.Event;
import se331.lab.service.EventService;
import org.springframework.data.domain.Page;


@Controller
@RequiredArgsConstructor
public class EventController {

    final EventService eventService;

    @GetMapping("events")
    public ResponseEntity<?> getEventLists(@RequestParam(value = "_limit", required = false) Integer perPage
    ,@RequestParam(value = "_page",required = false) Integer page) {
        Page<Event> pageOutput = eventService.getEvents(perPage, page);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(LabMapper.INSTANCE.getEventDtoList(pageOutput.getContent()), responseHeaders, HttpStatus.OK);
    }

    @GetMapping("events/{id}")
    public ResponseEntity<?> getEvent(@PathVariable ("id") long id) {
        Event output = eventService.getEvent(id);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getEventDto(output));
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"The given id is not found");
        }
    }

    @PostMapping("/events")
    public ResponseEntity<?> addEvent(@RequestBody Event event) {
        Event output = eventService.save(event);
        return ResponseEntity.ok(LabMapper.INSTANCE.getEventDto(output));
    }

}
