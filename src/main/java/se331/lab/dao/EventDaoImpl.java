package se331.lab.dao;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import se331.lab.entity.Event;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("manual")
public class EventDaoImpl implements EventDao {
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
                .petsAllowed(true)
                .build());

        eventList.add(Event.builder()
                .id(456L)
                .category("food")
                .title("Community Gardening")
                .description("Join us as we tend to the community edible plants.")
                .location("Flora City")
                .date("march 14, 2022")
                .time("10:00")
                .petsAllowed(true)
                .build());

        eventList.add(Event.builder()
                .id(4582797L)
                .category("food")
                .title("Community Gardening")
                .description("Join us as we tend to the community edible plants.")
                .location("Flora City")
                .date("March 14, 2022")
                .time("10:00")
                .petsAllowed(true)
                .build());

        eventList.add(Event.builder()
                .id(8419988L)
                .category("sustainability")
                .title("Beach Cleanup")
                .description("Help pick up trash along the shore.")
                .location("Playa Del Carmen")
                .date("July 22, 2022")
                .time("11:00")
                .petsAllowed(false)
                .build());

        eventList.add(Event.builder()
                .id(9238745L)
                .category("environment")
                .title("Tree Planting Day")
                .description("Join our community tree-planting campaign to help combat climate change.")
                .location("Green Park Central")
                .date("August 24, 2025")
                .time("09:00 AM")
                .petsAllowed(true)
                .build());

        eventList.add(Event.builder()
                .id(7293156L)
                .category("education")
                .title("Wildlife Photography Workshop")
                .description("Learn techniques for capturing stunning wildlife photos in their natural habitat.")
                .location("Nature Reserve Trail")
                .date("September 15, 2025")
                .time("08:30 AM")
                .petsAllowed(false)
                .build());
    }

    @Override
    public Integer getEventSize(){
        return eventList.size();
    }

    @Override
    public Page<Event> getEvents(Integer pageSize, Integer page){
        pageSize = pageSize == null? eventList.size() : pageSize;
        page = page == null ? 1: page;
        Integer firstIndex = (page-1)*pageSize;
        return new PageImpl<Event>(eventList.subList(firstIndex,firstIndex+pageSize), PageRequest.of(page,pageSize),eventList.size());
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

    @Override
    public Event save(Event event){
        event.setId(eventList.get(eventList.size()-1).getId()+1);
        eventList.add(event);
        return event;
    }
}
