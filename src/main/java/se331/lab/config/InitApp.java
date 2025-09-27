package se331.lab.config;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import se331.lab.entity.Event;
import se331.lab.entity.Organizer;
import se331.lab.entity.Participant;
import se331.lab.repository.EventRepository;
import se331.lab.repository.OrganizerRepository;
import se331.lab.repository.ParticipantRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    EventRepository eventRepository;
    final OrganizerRepository organizerRepository;
    final ParticipantRepository participantRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        Organizer org1, org2, org3;
        org1 = organizerRepository.save(Organizer.builder().name("CAMT").build());
        org2 = organizerRepository.save(Organizer.builder().name("CMU").build());
        org3 = organizerRepository.save(Organizer.builder().name("Chiang Mai").build());

        Participant participant1, participant2, participant3, participant4, participant5;
        participant1  = participantRepository.save(Participant.builder().name("Alice Johnson").telNo("081-234-5678").build());
        participant2  = participantRepository.save(Participant.builder().name("Bob Smith").telNo("082-345-6789").build());
        participant3  = participantRepository.save(Participant.builder().name("Charlie Brown").telNo("083-456-7890").build());
        participant4  = participantRepository.save(Participant.builder().name("Diana Miller").telNo("084-567-8901").build());
        participant5  = participantRepository.save(Participant.builder().name("Ethan Davis").telNo("085-678-9012").build());
        Event tempEvent;
        tempEvent = eventRepository.save(Event.builder()
                .category("Academic")
                .title("Midterm Exam")
                .description("A time for taking the exam")
                .location("CAMT Building")
                .date("3rd Sept")
                .time("3.00-4.00 pm.")
                .petsAllowed(false)
                .build());
        tempEvent.setOrganizer(org1);
        org1.getOwnEvents().add(tempEvent);
        participant1.getEventHistory().add(tempEvent);
        participant3.getEventHistory().add(tempEvent);
        participant4.getEventHistory().add(tempEvent);

        tempEvent = eventRepository.save(Event.builder()
                .category("Academic")
                .title("Commencement Day")
                .description("A time for celebration")
                .location("CMU covention hall")
                .date("21th Jan")
                .time("8.00am-4.00 pm.")
                .petsAllowed(false)
                .build());
        tempEvent.setOrganizer(org1);
        org1.getOwnEvents().add(tempEvent);
        participant1.getEventHistory().add(tempEvent);
        participant2.getEventHistory().add(tempEvent);
        participant5.getEventHistory().add(tempEvent);

        tempEvent = eventRepository.save(Event.builder()
                .category("Cultural")
                .title("Loy Krathong")
                .description("A time for Krathong")
                .location("Ping River")
                .date("21th Nov")
                .time("8.00-10.00 pm.")
                .petsAllowed(false)
                .build());
        tempEvent.setOrganizer(org2);
        org2.getOwnEvents().add(tempEvent);
        participant2.getEventHistory().add(tempEvent);
        participant3.getEventHistory().add(tempEvent);
        participant5.getEventHistory().add(tempEvent);

        tempEvent = eventRepository.save(Event.builder()
                .category("Cultural")
                .title("Songkran")
                .description("Let's Play Water")
                .location("Chiang Mai Moat")
                .date("13th April")
                .time("10.00am - 6.00 pm.")
                .petsAllowed(false)
                .build());
        tempEvent.setOrganizer(org3);
        org3.getOwnEvents().add(tempEvent);
        participant1.getEventHistory().add(tempEvent);
        participant2.getEventHistory().add(tempEvent);
        participant3.getEventHistory().add(tempEvent);

    }
}
