package se331.lab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se331.lab.dao.OrganizerDao;
import se331.lab.entity.Event;
import se331.lab.entity.Organizer;
import org.springframework.data.domain.Page;


import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizerServiceImpl implements OrganizerService {
    final OrganizerDao organizerDao;

    @Override
    public List<Organizer> getAllOrganizer(){
        return organizerDao.getOrganizer(Pageable.unpaged()).getContent();
    }

    @Override
    public Page<Organizer> getOrganizer(Integer pageSize, Integer page) {
        return organizerDao.getOrganizer(PageRequest.of(page, pageSize));
    }



}
