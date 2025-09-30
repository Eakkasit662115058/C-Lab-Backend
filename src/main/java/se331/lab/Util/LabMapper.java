package se331.lab.Util;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import se331.lab.entity.*;

import java.util.List;


@Mapper
public interface LabMapper {
    LabMapper INSTANCE = Mappers.getMapper(LabMapper.class);
    EventDTO getEventDto(Event event);
    List<EventDTO> getEventDto(List<Event> events);
    OrganizerDTO getOrganizerDto(Organizer organizer);
    List<OrganizerDTO> getOrganizerDto(List<Organizer> organizers);
    ParticipantDTO getParticipantDto(Participant participant);
    List<ParticipantDTO> getParticipantDto(List<Participant> participants);
    AuctionItemDTO getAuctionItemDto(AuctionItem auctionItem);
    List<AuctionItemDTO> getAuctionItemDto(List<AuctionItem> auctionItems);
    BidDTO getBidDto(Bid bid);
    List<BidDTO> getBidDto(List<Bid> bids);
}
