package com.atlassian.fisc.erp.connector.service.impl;

import com.atlassian.finance.bdm.CreditNoteBDM;
import com.atlassian.finsys.micros.logging.JsonLogV2;
import com.atlassian.fisc.erp.connector.client.ErpServiceClient;
import com.atlassian.fisc.erp.connector.domain.creditmemo.CreditMemo;
import com.atlassian.fisc.erp.connector.lookups.CreditMemoLookup;
import com.atlassian.fisc.erp.connector.mappers.CreditNoteMapper;
import com.atlassian.fisc.erp.connector.service.CreditMemoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static org.apache.commons.lang3.StringUtils.isBlank;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreditMemoServiceImpl implements CreditMemoService {
  //  @Autowired through constructor
  private final ErpServiceClient erpServiceClient;
  private final CreditMemoLookup lookup;

  @Override
  @JsonLogV2(
      logOutputAdditionalProperties = "tranId,createdFrom",
      suppressErrorLogToWarning = false)
  public CreditMemo getCreditMemoDetails(String transactionNumber) {
    var creditNoteBdmResponse = erpServiceClient.getCreditNote(transactionNumber);
    return lookup.forNsFields(
        CreditNoteMapper.INSTANCE.convert(creditNoteBdmResponse), creditNoteBdmResponse);
  }

  @Override
  @JsonLogV2(
      logId = "tranId",
      logOutputAdditionalProperties = "tranId,createdFrom",
      suppressErrorLogToWarning = false)
  public CreditMemo createCreditMemo(CreditMemo nsCreditMemo) {
    lookup.prePopulateNsFields(nsCreditMemo);
    CreditNoteBDM creditNoteBDM =
        lookup.forBdmFields(CreditNoteMapper.INSTANCE.convert(nsCreditMemo), nsCreditMemo);
    if (isBlank(creditNoteBDM.getEndCustomerId())) {
      creditNoteBDM.setEndCustomerId(creditNoteBDM.getCustomerId());
    }
    var creditNoteBdmResponse = erpServiceClient.createCreditNote(creditNoteBDM);
    return lookup.forNsFields(
        CreditNoteMapper.INSTANCE.convert(creditNoteBdmResponse), creditNoteBdmResponse);
  }

  @Override
  @JsonLogV2(
      logId = "tranId",
      logOutputAdditionalProperties = "tranId,createdFrom",
      suppressErrorLogToWarning = false)
  public CreditMemo applyCreditMemo(CreditMemo nsCreditMemo) {
    lookup.prePopulateNsFields(nsCreditMemo);
    CreditNoteBDM creditNoteBDM =
        lookup.forBdmFields(CreditNoteMapper.INSTANCE.convert(nsCreditMemo), nsCreditMemo);
    if (isBlank(creditNoteBDM.getEndCustomerId())) {
      creditNoteBDM.setEndCustomerId(creditNoteBDM.getCustomerId());
    }
    var creditNoteBdmResponse = erpServiceClient.applyCreditNote(creditNoteBDM);
    return lookup.forNsFields(
        CreditNoteMapper.INSTANCE.convert(creditNoteBdmResponse), creditNoteBdmResponse);
  }
}
