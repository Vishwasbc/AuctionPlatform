package com.bidservice;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bidservice.dto.AuctionDto;
import com.bidservice.dto.UserDto;
import com.bidservice.entity.Bid;
import com.bidservice.exception.AuctionNotFoundException;
import com.bidservice.exception.InvalidBidAmountException;
import com.bidservice.exception.InvalidBidderException;
import com.bidservice.feign.AuctionClient;
import com.bidservice.feign.UserClient;
import com.bidservice.repository.BidRepository;
import com.bidservice.service.BidServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BidServiceApplicationTests {

    @Mock
    private BidRepository bidRepository;

    @Mock
    private UserClient userClient;

    @Mock
    private AuctionClient auctionClient;

    @InjectMocks
    private BidServiceImpl bidService;

    @Test
    public void testPlaceBid_Success() {
        Bid bid = new Bid();
        bid.setBidderName("testUser");
        bid.setAuctionId(1);
        bid.setBidAmount(100.0);

        UserDto userDto = new UserDto();
        userDto.setUserName("testUser");
        userDto.setFirstName("Test");
        userDto.setLastName("User");
        userDto.setEmail("testuser@example.com");
        userDto.setContactNo("1234567890");

        AuctionDto auctionDto = new AuctionDto();
        auctionDto.setAuctionId(1);
        auctionDto.setCurrentHighestBid(50.0);
        auctionDto.setMinBidAmount(10.0);

        when(userClient.getUserByUsername("testUser")).thenReturn(userDto);
        when(auctionClient.getAuctionById(1)).thenReturn(auctionDto);
        when(bidRepository.save(any(Bid.class))).thenReturn(bid);

        Bid result = bidService.placeBid(bid);

        assertNotNull(result);
        assertEquals("testUser", result.getBidderName());
        assertEquals(1, result.getAuctionId());
        assertEquals(100.0, result.getBidAmount(), 0.0);
    }

    @Test
    public void testPlaceBid_InvalidBidder() {
        Bid bid = new Bid();
        bid.setBidderName("invalidUser");
        bid.setAuctionId(1);
        bid.setBidAmount(100.0);

        when(userClient.getUserByUsername("invalidUser")).thenReturn(null);

        assertThrows(InvalidBidderException.class, () -> bidService.placeBid(bid));
    }

    @Test
    public void testPlaceBid_InvalidAuction() {
        Bid bid = new Bid();
        bid.setBidderName("testUser");
        bid.setAuctionId(1);
        bid.setBidAmount(100.0);

        UserDto userDto = new UserDto();
        userDto.setUserName("testUser");
        userDto.setFirstName("Test");
        userDto.setLastName("User");
        userDto.setEmail("testuser@example.com");
        userDto.setContactNo("1234567890");

        when(userClient.getUserByUsername("testUser")).thenReturn(userDto);
        when(auctionClient.getAuctionById(1)).thenReturn(null);

        assertThrows(AuctionNotFoundException.class, () -> bidService.placeBid(bid));
    }

    @Test
    public void testPlaceBid_InvalidBidAmount() {
        Bid bid = new Bid();
        bid.setBidderName("testUser");
        bid.setAuctionId(1);
        bid.setBidAmount(55.0);

        UserDto userDto = new UserDto();
        userDto.setUserName("testUser");
        userDto.setFirstName("Test");
        userDto.setLastName("User");
        userDto.setEmail("testuser@example.com");
        userDto.setContactNo("1234567890");

        AuctionDto auctionDto = new AuctionDto();
        auctionDto.setAuctionId(1);
        auctionDto.setCurrentHighestBid(50.0);
        auctionDto.setMinBidAmount(10.0);

        when(userClient.getUserByUsername("testUser")).thenReturn(userDto);
        when(auctionClient.getAuctionById(1)).thenReturn(auctionDto);

        assertThrows(InvalidBidAmountException.class, () -> bidService.placeBid(bid));
    }

    @Test
    public void testGetBidsByAuction() {
        List<Bid> bids = Arrays.asList(new Bid(), new Bid());
        when(bidRepository.findAllByAuctionId(1)).thenReturn(bids);

        List<Bid> result = bidService.getBidsByAuction(1);

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testGetHighestBid() {
        when(bidRepository.findTopByAuctionIdOrderByBidAmountDesc(1)).thenReturn(100.0);

        double result = bidService.getHighestBid(1);

        assertEquals(100.0, result, 0.0);
    }
}
