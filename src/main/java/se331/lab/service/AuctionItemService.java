package se331.lab.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import se331.lab.entity.AuctionItem;
import se331.lab.entity.Event;

public interface AuctionItemService {
    Page<AuctionItem> getAuctionItems(Integer pageSize, Integer page);
    Page<AuctionItem> getAuctionItems(String title, Pageable pageable);
    Page<AuctionItem> getAuctionItemsSuccessfulBid(Integer amount, Pageable pageable);
}
