package se331.lab.config;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import se331.lab.entity.*;
import se331.lab.repository.*;
import se331.lab.security.user.Role;
import se331.lab.security.user.UserRepository;
import java.util.List;
import se331.lab.security.user.User;

@Component
@RequiredArgsConstructor
public class InitApp implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    EventRepository eventRepository;
    @Autowired
    AuctionItemRepository auctionItemRepository;
    @Autowired
    BidRepository bidRepository;
    final OrganizerRepository organizerRepository;
    final ParticipantRepository participantRepository;
    final UserRepository userRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        Organizer org1, org2, org3;
        org1 = organizerRepository.save(Organizer.builder().name("CAMT").address("in CMU").build());
        org2 = organizerRepository.save(Organizer.builder().name("CMU").address("in Chiang Mai").build());
        org3 = organizerRepository.save(Organizer.builder().name("Chiang Mai").address("in Thailand").build());

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
                .images(List.of("https://yrgjzefdlluvebeaiuaz.supabase.co/storage/v1/object/public/test/20251008202327845-Her%20Hyness.jpg"))
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

        AuctionItem auctionItem1, auctionItem2, auctionItem3, auctionItem4, auctionItem5;
        Bid bid1, bid2, bid3, bid4, bid5, bid6, bid7, bid8, bid9, bid10, bid11, bid12, bid13, bid14, bid15;

        // Create Auction Item 1 - Vintage Painting
        auctionItem1 = auctionItemRepository.save(AuctionItem.builder()
                .description("Beautiful vintage oil painting from 19th century")
                .type("Art")
                .build());

        // Bids for Auction Item 1
        bid1 = bidRepository.save(Bid.builder()
                .amount(1000)
                .dateTime("2024-01-15 09:00:00")
                .auctionItem(auctionItem1)
                .build());

        bid2 = bidRepository.save(Bid.builder()
                .amount(1500)
                .dateTime("2024-01-15 10:30:00")
                .auctionItem(auctionItem1)
                .build());

        bid3 = bidRepository.save(Bid.builder()
                .amount(2200)
                .dateTime("2024-01-15 14:45:00")
                .auctionItem(auctionItem1)
                .build());

        auctionItem1.getBidHistory().add(bid1);
        auctionItem1.getBidHistory().add(bid2);
        auctionItem1.getBidHistory().add(bid3);
        auctionItem1.setSuccessfulBid(bid3); // Highest bid wins

        // Create Auction Item 2 - Antique Watch
        auctionItem2 = auctionItemRepository.save(AuctionItem.builder()
                .description("Swiss antique pocket watch from 1920s")
                .type("Collectibles")
                .build());

        // Bids for Auction Item 2
        bid4 = bidRepository.save(Bid.builder()
                .amount(800)
                .dateTime("2024-01-16 11:00:00")
                .auctionItem(auctionItem2)
                .build());

        bid5 = bidRepository.save(Bid.builder()
                .amount(1200)
                .dateTime("2024-01-16 13:20:00")
                .auctionItem(auctionItem2)
                .build());

        bid6 = bidRepository.save(Bid.builder()
                .amount(1800)
                .dateTime("2024-01-16 16:10:00")
                .auctionItem(auctionItem2)
                .build());

        auctionItem2.getBidHistory().add(bid4);
        auctionItem2.getBidHistory().add(bid5);
        auctionItem2.getBidHistory().add(bid6);
        auctionItem2.setSuccessfulBid(bid6); // Highest bid wins

        // Create Auction Item 3 - Classic Guitar
        auctionItem3 = auctionItemRepository.save(AuctionItem.builder()
                .description("1965 Gibson Les Paul Standard electric guitar")
                .type("Musical Instruments")
                .build());

        // Bids for Auction Item 3
        bid7 = bidRepository.save(Bid.builder()
                .amount(3000)
                .dateTime("2024-01-17 08:30:00")
                .auctionItem(auctionItem3)
                .build());

        bid8 = bidRepository.save(Bid.builder()
                .amount(4500)
                .dateTime("2024-01-17 12:15:00")
                .auctionItem(auctionItem3)
                .build());

        bid9 = bidRepository.save(Bid.builder()
                .amount(6200)
                .dateTime("2024-01-17 15:45:00")
                .auctionItem(auctionItem3)
                .build());

        auctionItem3.getBidHistory().add(bid7);
        auctionItem3.getBidHistory().add(bid8);
        auctionItem3.getBidHistory().add(bid9);
        auctionItem3.setSuccessfulBid(bid9); // Highest bid wins

        // Create Auction Item 4 - Rare Book (no successful bid yet)
        auctionItem4 = auctionItemRepository.save(AuctionItem.builder()
                .description("First edition of 'To Kill a Mockingbird' by Harper Lee")
                .type("Books")
                .build());

        // Bids for Auction Item 4
        bid10 = bidRepository.save(Bid.builder()
                .amount(500)
                .dateTime("2024-01-18 09:00:00")
                .auctionItem(auctionItem4)
                .build());

        bid11 = bidRepository.save(Bid.builder()
                .amount(750)
                .dateTime("2024-01-18 11:30:00")
                .auctionItem(auctionItem4)
                .build());

        bid12 = bidRepository.save(Bid.builder()
                .amount(950)
                .dateTime("2024-01-18 14:20:00")
                .auctionItem(auctionItem4)
                .build());

        auctionItem4.getBidHistory().add(bid10);
        auctionItem4.getBidHistory().add(bid11);
        auctionItem4.getBidHistory().add(bid12);
        // No successful bid set - auction still ongoing

        // Create Auction Item 5 - Vintage Car (no successful bid yet)
        auctionItem5 = auctionItemRepository.save(AuctionItem.builder()
                .description("1967 Ford Mustang Fastback in excellent condition")
                .type("Vehicles")
                .build());

        // Bids for Auction Item 5
        bid13 = bidRepository.save(Bid.builder()
                .amount(15000)
                .dateTime("2024-01-19 10:00:00")
                .auctionItem(auctionItem5)
                .build());

        bid14 = bidRepository.save(Bid.builder()
                .amount(18500)
                .dateTime("2024-01-19 13:45:00")
                .auctionItem(auctionItem5)
                .build());

        bid15 = bidRepository.save(Bid.builder()
                .amount(22000)
                .dateTime("2024-01-19 16:30:00")
                .auctionItem(auctionItem5)
                .build());

        auctionItem5.getBidHistory().add(bid13);
        auctionItem5.getBidHistory().add(bid14);
        auctionItem5.getBidHistory().add(bid15);

        addUser();

        org1.setUser(user1);
        user1.setOrganizer(org1);
        org2.setUser(user2);
        user2.setOrganizer(org2);
        org3.setUser(user3);
        user3.setOrganizer(org3);
        }

    User user1,user2,user3;
    private void addUser(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        user1 = User.builder()
                .username("admin")
                .password(encoder.encode("admin"))
                .firstname("admin")
                .lastname("admin")
                .email("admin@admin.com")
                .enabled(true)
                .build();
        user2 = User.builder()
                .username("user")
                .password(encoder.encode("user"))
                .firstname("user")
                .lastname("user")
                .email("enable@user.com")
                .enabled(true)
                .build();
        user3 = User.builder()
                .username("disableUser")
                .password(encoder.encode("disableUser"))
                .firstname("disableUser")
                .lastname("disableUser")
                .email("disable@user.com")
                .enabled(false)
                .build();

        user1.getRoles().add(Role.ROLE_USER);
        user1.getRoles().add(Role.ROLE_ADMIN);

        user2.getRoles().add(Role.ROLE_USER);
        user2.getRoles().add(Role.ROLE_USER);

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
    }
}
