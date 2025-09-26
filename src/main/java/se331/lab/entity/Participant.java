package se331.lab.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;
import se331.lab.entity.Event;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Participant {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            @EqualsAndHashCode.Exclude
    Long id;
    String name;
    String telNo;
    @ManyToMany
    List<Event> eventHistory;
}
