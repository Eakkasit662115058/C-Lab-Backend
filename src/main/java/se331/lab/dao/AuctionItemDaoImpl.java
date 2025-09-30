package se331.lab.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import se331.lab.entity.AuctionItem;
import se331.lab.repository.AuctionItemRepository;

@Repository
@RequiredArgsConstructor
@Profile("db")
public class AuctionItemDaoImpl implements AuctionItemDao {

    final AuctionItemRepository auctionItemRepository;


    @Override
    public Page<AuctionItem> getAuctionItems(Integer pageSize, Integer page){
        return auctionItemRepository.findAll(PageRequest.of(page - 1, pageSize));
    }


    @Override
    public Page<AuctionItem> getAuctionItems(String title, Pageable page){
        return auctionItemRepository.findByTypeContainingOrDescriptionContaining(title, title, page);
    }

    @Override
    public Page<AuctionItem> getAuctionItemsSuccessfulBid(Integer amount, Pageable page){
        return auctionItemRepository.findBySuccessfulBid_AmountLessThanEqual(amount, page);
    }

}
