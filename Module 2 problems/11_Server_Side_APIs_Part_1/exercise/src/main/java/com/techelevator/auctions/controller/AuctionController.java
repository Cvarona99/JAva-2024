package com.techelevator.auctions.controller;

import com.techelevator.auctions.dao.AuctionDao;
import com.techelevator.auctions.dao.MemoryAuctionDao;
import com.techelevator.auctions.model.Auction;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/auctions")
public class AuctionController {
    private final AuctionDao auctionDao;

    public AuctionController() {
        this.auctionDao = new MemoryAuctionDao();
    }
    @RequestMapping(method = RequestMethod.GET)
    private List<Auction> list (@RequestParam(name = "title_like", defaultValue = "") String titleLike, @RequestParam(name = "currentBid_lte", defaultValue = "0") double currentBid) {
        if (!titleLike.isEmpty() && currentBid > 0) {
            return auctionDao.getAuctionsByTitleAndMaxBid(titleLike,currentBid);
        }
        else if (currentBid > 0) {
            return auctionDao.getAuctionsByMaxBid(currentBid);
        }
        else if (!titleLike.isEmpty()) {
            return auctionDao.getAuctionsByTitle(titleLike);
        }
        return auctionDao.getAuctions();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Auction get(@PathVariable int id) {
        return auctionDao.getAuctionById(id);
    }

    @RequestMapping( method = RequestMethod.POST)
    public Auction create(@RequestBody Auction auction) {
        return auctionDao.createAuction(auction);
    }




}

