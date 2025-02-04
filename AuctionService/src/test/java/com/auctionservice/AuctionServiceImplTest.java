package com.auctionservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.auctionservice.entity.Auction;
import com.auctionservice.entity.AuctionStatus;
import com.auctionservice.exception.AuctionNotFound;
import com.auctionservice.repository.AuctionRepository;
import com.auctionservice.service.AuctionSerivceImpl;

public class AuctionServiceImplTest {

	@Mock
	private AuctionRepository auctionRepository;

	@InjectMocks
	private  AuctionSerivceImpl auctionService;

	private Auction auction;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		auction = new Auction();
		auction.setAuctionId(1);
		auction.setProductId(101);
		auction.setDescription("Test Auction");
		auction.setSellerId(1001);
		auction.setStartDate(LocalDateTime.now());
		auction.setEndDate(LocalDateTime.now().plusDays(1));
		auction.setStartPrice(100.0);
		auction.setCurrentHighestBid(150.0);
		auction.setMinBidAmount(10.0);
		auction.setStatus(AuctionStatus.UPCOMING);
	}

	@Test
	public void testCreateAuction() {
		when(auctionRepository.save(any(Auction.class))).thenReturn(auction);
		String result = auctionService.createAuction(auction);
		assertEquals("Auction Created", result);
		verify(auctionRepository, times(1)).save(auction);
	}

	@Test
	public void testUpdateAuction() {
		when(auctionRepository.findById(anyInt())).thenReturn(Optional.of(auction));
		when(auctionRepository.save(any(Auction.class))).thenReturn(auction);

		Auction updatedAuction = auctionService.updateAuction(1, auction);
		assertNotNull(updatedAuction);
		assertEquals(auction.getProductId(), updatedAuction.getProductId());
		verify(auctionRepository, times(1)).findById(1);
		verify(auctionRepository, times(1)).save(auction);
	}

	@Test
	public void testUpdateAuctionNotFound() {
		when(auctionRepository.findById(anyInt())).thenReturn(Optional.empty());
		assertThrows(AuctionNotFound.class, () -> auctionService.updateAuction(1, auction));
	}

	@Test
	public void testGetAllAuction() {
		when(auctionRepository.findAll()).thenReturn(Arrays.asList(auction));
		List<Auction> auctions = auctionService.getAllAuction();
		assertNotNull(auctions);
		assertEquals(1, auctions.size());
		verify(auctionRepository, times(1)).findAll();
	}

	@Test
	public void testGetAuctionById() {
		when(auctionRepository.findById(anyInt())).thenReturn(Optional.of(auction));
		Auction foundAuction = auctionService.getAuctionById(1);
		assertNotNull(foundAuction);
		assertEquals(auction.getAuctionId(), foundAuction.getAuctionId());
		verify(auctionRepository, times(1)).findById(1);
	}

	@Test
	public void testGetAuctionByIdNotFound() {
		when(auctionRepository.findById(anyInt())).thenReturn(Optional.empty());
		assertThrows(AuctionNotFound.class, () -> auctionService.getAuctionById(1));
	}

	@Test
	public void testDeleteAuction() {
		doNothing().when(auctionRepository).deleteById(anyInt());
		String result = auctionService.deleteAuction(1);
		assertEquals("Auction Successfully Deleted", result);
		verify(auctionRepository, times(1)).deleteById(1);
	}

	@Test
	public void testGetAuctionBySeller() {
		when(auctionRepository.findBySellerId(anyInt())).thenReturn(Arrays.asList(auction));
		List<Auction> auctions = auctionService.getAuctionBySeller(1001);
		assertNotNull(auctions);
		assertEquals(1, auctions.size());
		verify(auctionRepository, times(1)).findBySellerId(1001);
	}

	@Test
	public void testEndAuction() {
		when(auctionRepository.findById(anyInt())).thenReturn(Optional.of(auction));
		auction.setStatus(AuctionStatus.LIVE);
		String result = auctionService.endAuction(1);
		assertEquals("Auction Ended", result);
		verify(auctionRepository, times(1)).findById(1);
		verify(auctionRepository, times(1)).save(auction);
	}

	@Test
	public void testEndAuctionNotFound() {
		when(auctionRepository.findById(anyInt())).thenReturn(Optional.empty());
		assertThrows(AuctionNotFound.class, () -> auctionService.endAuction(1));
	}
	
	@Test
    public void testStartAuction() {
        when(auctionRepository.findById(anyInt())).thenReturn(Optional.of(auction));

        String result = auctionService.startAuction(1);
        assertEquals("Auction Started", result); 
        assertEquals(AuctionStatus.LIVE, auction.getStatus());
        verify(auctionRepository, times(1)).findById(1);
        verify(auctionRepository, times(1)).save(auction);
    }


	@Test
	public void testStartAuctionNotFound() {
		when(auctionRepository.findById(anyInt())).thenReturn(Optional.empty());

		assertThrows(AuctionNotFound.class, () -> auctionService.startAuction(1));
		verify(auctionRepository, times(1)).findById(1);
		verify(auctionRepository, times(0)).save(any(Auction.class));
	}
}
