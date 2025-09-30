package se331.lab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se331.lab.dao.AuctionItemDao;
import se331.lab.entity.AuctionItem;

@Service
@RequiredArgsConstructor
public class AuctionItemServiceImpl implements AuctionItemService {

    final AuctionItemDao auctionItemDao;

    @Override
    public Page<AuctionItem> getAuctionItems(Integer pageSize, Integer page){
        return auctionItemDao.getAuctionItems(pageSize, page);
    }

    @Override
    public Page<AuctionItem> getAuctionItems(String title, Pageable pageable){
        return auctionItemDao.getAuctionItems(title, pageable);
    }

    @Override
    public Page<AuctionItem> getAuctionItemsSuccessfulBid(Integer amount, Pageable pageable){
        return auctionItemDao.getAuctionItemsSuccessfulBid(amount, pageable);
    }
}
