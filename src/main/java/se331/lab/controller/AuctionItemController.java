package se331.lab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se331.lab.Util.LabMapper;
import se331.lab.entity.AuctionItem;
import se331.lab.service.AuctionItemService;

@Controller
@RequiredArgsConstructor
public class AuctionItemController {

    final AuctionItemService auctionItemService;

    @GetMapping("auctionItems")
    public ResponseEntity<?> getEventLists(@RequestParam(value = "_limit", required = false) Integer perPage
            , @RequestParam(value = "_page",required = false) Integer page
            , @RequestParam(value = "_title" , required = false) String title) {
        perPage = perPage == null ? 3 : perPage;
        page = page == null ? 1 : page;
        Page<AuctionItem> pageOutput;
        if (title == null) {
            pageOutput = auctionItemService.getAuctionItems(perPage, page);
        }else{
            pageOutput = auctionItemService.getAuctionItems(title, PageRequest.of(page-1,perPage));
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(LabMapper.INSTANCE.getAuctionItemDto(pageOutput.getContent()), responseHeaders, HttpStatus.OK);
    }

    @GetMapping("auctionItemsSuccessfulBid")
    public ResponseEntity<?> getEventLists(@RequestParam(value = "_limit", required = false) Integer perPage
            , @RequestParam(value = "_page",required = false) Integer page
            , @RequestParam(value = "_amount" , required = false) Integer amount) {
        perPage = perPage == null ? 3 : perPage;
        page = page == null ? 1 : page;
        Page<AuctionItem> pageOutput;
        if (amount == null) {
            pageOutput = auctionItemService.getAuctionItems(perPage, page);
        }else{
            pageOutput = auctionItemService.getAuctionItemsSuccessfulBid(amount, PageRequest.of(page-1,perPage));
        }
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(LabMapper.INSTANCE.getAuctionItemDto(pageOutput.getContent()), responseHeaders, HttpStatus.OK);
    }


}
