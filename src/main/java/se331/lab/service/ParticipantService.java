package se331.lab.service;

import org.springframework.data.domain.Page;
import se331.lab.entity.Organizer;
import se331.lab.entity.Participant;

import java.util.List;

public interface ParticipantService {
    List<Participant> getAllParticipant();

    Page<Participant> getParticipant(Integer pageSize, Integer page);

}
