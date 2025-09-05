package se331.lab.dao;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import se331.lab.entity.Event;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EventDaolmpl implements EventDao {
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

    @Override
    public Integer getEventSize(){
        return eventList.size();
    }

    @Override
    public List<Event> getEvents(Integer pageSize, Integer page){
        pageSize = pageSize == null? eventList.size() : pageSize;
        page = page == null ? 1: page;
        Integer firstIndex = (page-1)*pageSize;
        List<Event> output = new ArrayList<>();
        for(int i = firstIndex; i < firstIndex+pageSize; i++){
        output.add(eventList.get(i));
        }
        return output;
    }

    @Override
    public Event getEvent(Long id){
        Event output = null;
        for(Event event : eventList){
            if(event.getId().equals(id)){
                output = event;
                break;
            }
        }
        return output;
    }


}
