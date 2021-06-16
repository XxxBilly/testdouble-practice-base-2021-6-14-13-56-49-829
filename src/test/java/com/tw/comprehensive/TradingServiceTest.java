package com.tw.comprehensive;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

class TradingServiceTest {

  @Test
  void should_call_logNewTrade_when_execute_createTrade() {
    // mock
    TradeRepository tradeRepository = spy(TradeRepository.class);
    when(tradeRepository.createTrade(any())).thenReturn(null);
    AuditService auditService = mock(AuditService.class);

    // when
    TradingService tradingService = new TradingService(tradeRepository, auditService);
    tradingService.createTrade(null);

    // then
    verify(auditService, times(1)).logNewTrade(any());
  }

  @Test
  void should_return_same_trade_when_execute_findTrade() {
    // mock
    TradeRepository tradeRepository = mock(TradeRepository.class);
    when(tradeRepository.findById(1L)).thenReturn(new Trade("1", "trade"));

    // when
    TradingService tradingService = new TradingService(tradeRepository, new AuditService());
    Trade resultTrade = tradingService.findTrade(1L);

    // then
    assertEquals("1", resultTrade.getReference());
    assertEquals("trade", resultTrade.getDescription());
  }

}