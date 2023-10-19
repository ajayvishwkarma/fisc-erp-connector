package com.atlassian.fisc.erp.connector.service.impl;

import com.atlassian.fisc.erp.connector.client.ErpServiceClient;
import com.atlassian.fisc.erp.connector.util.LookupTestDataUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class LookupServiceImplTest {
  @Mock private ErpServiceClient erpServiceClient;

  private LookupServiceImpl lookupService;

  @BeforeEach
  void setUp() {
    lookupService = new LookupServiceImpl(erpServiceClient);
  }

  @Test
  void getHamsSpokeItemMap() {
    var testData = Arrays.asList(LookupTestDataUtil.testDataSpokeItemMapArray());
    Mockito.lenient().when(erpServiceClient.getHamsSpokeItemMap()).thenReturn(testData);

    var response = lookupService.getHamsSpokeItemMap();

    assertTrue(testData.size() >= response.getMapWithItemAsKey().size());
    assertTrue(testData.size() >= response.getMapWithSpokeSystemItemAsKey().size());
    var firstItem = testData.get(0);
    assertEquals(
        firstItem.getItem(),
        response.getMapWithSpokeSystemItemAsKey().get(firstItem.getSpokeSystemItem()));
    assertEquals(
        firstItem.getSpokeSystemItem(), response.getMapWithItemAsKey().get(firstItem.getItem()));
  }
}
