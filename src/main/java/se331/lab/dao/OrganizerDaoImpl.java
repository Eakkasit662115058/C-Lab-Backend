package se331.lab.dao;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import se331.lab.entity.Organizer;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrganizerDaoImpl implements OrganizerDao {
    List<Organizer> organizerList;

    @PostConstruct
    public void init() {
        organizerList = new ArrayList<>();
        organizerList.add(Organizer.builder()
                .id(123L)
                .address("Meow Town")
                .organizationName("Kat Laydee")
                .build());

        organizerList.add(Organizer.builder()
                .id(456L)
                .address("Flora City")
                .organizationName("Fern Pollin")
                .build());

        organizerList.add(Organizer.builder()
                .id(4582797L)
                .address("Flora City")
                .organizationName("Fern Pollin")
                .build());

        organizerList.add(Organizer.builder()
                .id(8419988L)
                .address("Playa Del Carmen")
                .organizationName("Carey Wales")
                .build());

        organizerList.add(Organizer.builder()
                .id(9238745L)
                .address("Green Park Central")
                .organizationName("EcoFuture Group")
                .build());

        organizerList.add(Organizer.builder()
                .id(7293156L)
                .address("Nature Reserve Trail")
                .organizationName("Camera Club Collective")
                .build());
    }

    @Override
    public Integer getOrganizerSize(){
        return organizerList.size();
    }

    @Override
    public List<Organizer> getOrganizers(Integer pageSize, Integer page){
        pageSize = pageSize == null? organizerList.size() : pageSize;
        page = page == null ? 1: page;
        Integer firstIndex = (page-1)*pageSize;
        List<Organizer> output = new ArrayList<>();
        for(int i = firstIndex; i < firstIndex+pageSize; i++){
            output.add(organizerList.get(i));
        }
        return output;
    }

    @Override
    public Organizer getOrganizer(Long id){
        Organizer output = null;
        for(Organizer organizer : organizerList){
            if(organizer.getId().equals(id)){
                output = organizer;
                break;
            }
        }
        return output;
    }
}
