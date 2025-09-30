package se331.lab.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BidDTO {
    Long id;
    Integer amount;
    String dateTime;
    BidAuctionItemDTO bidAuctionItem;
}
