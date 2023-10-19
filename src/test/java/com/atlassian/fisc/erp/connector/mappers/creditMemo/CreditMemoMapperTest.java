package com.atlassian.fisc.erp.connector.mappers.creditMemo;

import com.atlassian.finance.bdm.CreditNoteBDM;
import com.atlassian.finance.bdm.CreditNoteLineBDM;
import com.atlassian.fisc.erp.connector.domain.creditmemo.CreditMemo;
import com.atlassian.fisc.erp.connector.domain.creditmemo.CreditMemoItem;
import com.atlassian.fisc.erp.connector.domain.creditmemo.CreditMemoItemList;
import com.atlassian.fisc.erp.connector.mappers.CreditNoteLineMapper;
import com.atlassian.fisc.erp.connector.mappers.CreditNoteMapper;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CreditMemoMapperTest {

  @Test
  void testCreditMemoBDMToNsCreditMemo() {
    CreditNoteBDM creditNoteBDM = (new CreditNoteBDMTestGen().createDummy());
    var nsCreditMemo = CreditNoteMapper.INSTANCE.convert(creditNoteBDM);
    List<CreditNoteLineBDM> creditNoteLines = creditNoteBDM.getCreditMemoLines();
    if (!CollectionUtils.isEmpty(creditNoteLines)) {
      List<CreditMemoItem> creditMemoItems = new ArrayList<>();
      for (CreditNoteLineBDM creditNoteLineBDM : creditNoteLines) {
        creditMemoItems.add(CreditNoteLineMapper.INSTANCE.convert(creditNoteLineBDM));
      }
      CreditMemoItemList creditMemoItemList = new CreditMemoItemList();
      creditMemoItemList.setItem(
          creditMemoItems.toArray(new CreditMemoItem[creditMemoItems.size()]));
      nsCreditMemo.setItemList(creditMemoItemList);
      assertEquals(creditNoteBDM.getSourceSystem().getName(), nsCreditMemo.getSource());//  CrMemo-TST-ROW 1
      assertEquals(creditNoteBDM.getTransactionNumber(), nsCreditMemo.getTranId());//  CrMemo-TST-ROW 4
    }
  }

  @Test
  void testCreditMemoToCreditMemoBDM() {
    CreditMemo nsCreditMemo = (new NsCreditMemoTestGen().createDummy());
    var creditNoteBDM = CreditNoteMapper.INSTANCE.convert(nsCreditMemo);

    var itemList = nsCreditMemo.getItemList().getItem();
    if (itemList.length > 0) {
      List<CreditNoteLineBDM> creditMemoLines = new ArrayList<>();
      for (CreditMemoItem creditMemoItem : itemList) {
        creditMemoLines.add(CreditNoteLineMapper.INSTANCE.convert(creditMemoItem));
      }
      creditNoteBDM.setCreditMemoLines(creditMemoLines);
    }
    assertEquals(nsCreditMemo.getMemo(), creditNoteBDM.getMemo());//  CrMemo-TST-ROW 28
  }
}
