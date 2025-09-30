package se331.lab.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import se331.lab.entity.AuctionItem;

import java.util.List;

public interface AuctionItemRepository extends JpaRepository<AuctionItem,Long> {
    List<AuctionItem> findAll();

    Page<AuctionItem> findByTypeContainingOrDescriptionContaining(String description, String type, Pageable pageRequest);

    Page<AuctionItem> findBySuccessfulBid_AmountLessThanEqual(Integer amount, Pageable pageRequest);
}
