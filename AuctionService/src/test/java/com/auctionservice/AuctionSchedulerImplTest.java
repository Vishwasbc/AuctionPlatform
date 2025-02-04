package com.auctionservice;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.auctionservice.entity.Auction;
import com.auctionservice.entity.AuctionStatus;
import com.auctionservice.repository.AuctionRepository;
import com.auctionservice.service.AuctionSchedulerImpl;

public class AuctionSchedulerImplTest {

	@Mock
	private AuctionRepository auctionRepository;

	@InjectMocks
	private AuctionSchedulerImpl auctionScheduler;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testStartAuctions() {
		Auction auction1 = new Auction();
		auction1.setStatus(AuctionStatus.UPCOMING);
		auction1.setStartDate(LocalDateTime.now().minusMinutes(1));

		Auction auction2 = new Auction();
		auction2.setStatus(AuctionStatus.UPCOMING);
		auction2.setStartDate(LocalDateTime.now().plusMinutes(1));

		List<Auction> upcomingAuctions = Arrays.asList(auction1, auction2);

		when(auctionRepository.findByStatus(AuctionStatus.UPCOMING)).thenReturn(upcomingAuctions);

		auctionScheduler.startAuctions();

		verify(auctionRepository, times(1)).save(auction1);
		verify(auctionRepository, never()).save(auction2);
	}

	@Test
	public void testEndAuctions() {
		Auction auction1 = new Auction();
		auction1.setStatus(AuctionStatus.LIVE);
		auction1.setEndDate(LocalDateTime.now().minusMinutes(1));

		Auction auction2 = new Auction();
		auction2.setStatus(AuctionStatus.LIVE);
		auction2.setEndDate(LocalDateTime.now().plusMinutes(1));

		List<Auction> ongoingAuctions = Arrays.asList(auction1, auction2);

		when(auctionRepository.findByStatus(AuctionStatus.LIVE)).thenReturn(ongoingAuctions);

		auctionScheduler.endAuctions();

		verify(auctionRepository, times(1)).save(auction1);
		verify(auctionRepository, never()).save(auction2);
	}
}
