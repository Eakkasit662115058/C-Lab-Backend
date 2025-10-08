package se331.lab.service;

import jakarta.transaction.Transactional;
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
    public Page<Organizer> getOrganizers(Integer pageSize, Integer page) {
        return organizerDao.getOrganizer(PageRequest.of(page, pageSize));
    }

    @Override
    public Organizer getOrganizer(Long id){
        return organizerDao.getOrganizer(id);
    }

    @Override
    @Transactional
    public Organizer save(Organizer organizer) {
        return organizerDao.save(organizer);
    }

}
