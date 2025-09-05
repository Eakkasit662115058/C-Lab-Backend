package se331.lab.rest.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import se331.lab.rest.entity.Event;

import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.ArrayList;

@Controller
public class EventController {
    List<Event> eventList;

    @PostConstruct
    public void init() {
        eventList = new ArrayList<>();
        eventList.add(Event.builder()
                .id(123L)
                .category("animal welfare")
                .title("Cat Adoption Day")
                .description("Find your new feline friends at this event.")
                .location("Meow Town")
                .date("January 28, 2022")
                .time("12:00")
                .petAllowed(true)
                .organizer("Kat Laydee")
                .build());

        eventList.add(Event.builder()
                .id(456L)
                .category("food")
                .title("Community Gardening")
                .description("Join us as we tend to the community edible plants.")
                .location("Flora City")
                .date("march 14, 2022")
                .time("10:00")
                .petAllowed(true)
                .organizer("Fern Pollin")
                .build());

        eventList.add(Event.builder()
                .id(4582797L)
                .category("food")
                .title("Community Gardening")
                .description("Join us as we tend to the community edible plants.")
                .location("Flora City")
                .date("March 14, 2022")
                .time("10:00")
                .petAllowed(true)
                .organizer("Fern Pollin")
                .build());

        eventList.add(Event.builder()
                .id(8419988L)
                .category("sustainability")
                .title("Beach Cleanup")
                .description("Help pick up trash along the shore.")
                .location("Playa Del Carmen")
                .date("July 22, 2022")
                .time("11:00")
                .petAllowed(false)
                .organizer("Carey Wales")
                .build());

        eventList.add(Event.builder()
                .id(9238745L)
                .category("environment")
                .title("Tree Planting Day")
                .description("Join our community tree-planting campaign to help combat climate change.")
                .location("Green Park Central")
                .date("August 24, 2025")
                .time("09:00 AM")
                .petAllowed(true)
                .organizer("EcoFuture Group")
                .build());

        eventList.add(Event.builder()
                .id(7293156L)
                .category("education")
                .title("Wildlife Photography Workshop")
                .description("Learn techniques for capturing stunning wildlife photos in their natural habitat.")
                .location("Nature Reserve Trail")
                .date("September 15, 2025")
                .time("08:30 AM")
                .petAllowed(false)
                .organizer("Camera Club Collective")
                .build());
    }

    @GetMapping("events")
    public ResponseEntity<?> getEventLists(@RequestParam(value = "_limit", required = false) Integer perPage
    ,@RequestParam(value = "_page",required = false) Integer page) {
        perPage = perPage == null ?eventList.size() : perPage;
        page = page == null ? 1 : page;
        Integer firstIndex = (page-1)*perPage;
        List<Event> output = new ArrayList<>();
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("x-total-count", String.valueOf(eventList.size()));
        try {
            for (int i = firstIndex; i < firstIndex + perPage; i++) {
                output.add(eventList.get(i));
            }
            return new ResponseEntity<>(output, responseHeaders, HttpStatus.OK);
        }catch (IndexOutOfBoundsException ex) {
            return new ResponseEntity<>(output, responseHeaders, HttpStatus.OK);
        }
    }

    @GetMapping("events/{id}")
    public ResponseEntity<?> getEvent(@PathVariable ("id") long id) {
        Event output = null;
        for (Event event : eventList) {
            if (event.getId().equals(id)) {
                output = event;
                break;
            }
        }
        if (output != null) {
            return ResponseEntity.ok(output);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"The given id is not found");
        }
    }

}
