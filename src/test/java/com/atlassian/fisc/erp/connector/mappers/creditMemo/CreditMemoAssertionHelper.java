package com.atlassian.fisc.erp.connector.mappers.creditMemo;

import com.atlassian.finance.bdm.AddressBDM;
import com.atlassian.finance.bdm.CreditNoteApplyLineBDM;
import com.atlassian.finance.bdm.CreditNoteBDM;
import com.atlassian.finance.bdm.CreditNoteLineBDM;
import com.atlassian.fisc.erp.connector.constant.Constant;
import com.atlassian.fisc.erp.connector.domain.common.Address;
import com.atlassian.fisc.erp.connector.domain.common.CustomFieldList;
import com.atlassian.fisc.erp.connector.domain.common.CustomFieldRef;
import com.atlassian.fisc.erp.connector.domain.creditmemo.CreditMemo;
import com.atlassian.fisc.erp.connector.domain.creditmemo.CreditMemoApply;
import com.atlassian.fisc.erp.connector.domain.creditmemo.CreditMemoApplyList;
import com.atlassian.fisc.erp.connector.domain.creditmemo.CreditMemoItem;
import com.atlassian.fisc.erp.connector.util.TwoWayAsserter;
import org.apache.commons.lang3.ObjectUtils;

import java.util.List;

import static com.atlassian.fisc.erp.connector.util.TestUtil.epoch;
import static java.util.Objects.isNull;
import static org.junit.jupiter.api.Assertions.*;

public class CreditMemoAssertionHelper {

  //  TODO 2-way asserter not implemented correctly; it is meant for reuse and not repeat
  public void assertNsCreditMemo(CreditNoteBDM creditNoteBDM, CreditMemo nsCreditMemo, boolean expectedFirst) {
    TwoWayAsserter twoWayAsserter = new TwoWayAsserter(expectedFirst);
    assertNotNull(creditNoteBDM);

    if (Constant.STATUS_PAID.equals(creditNoteBDM.getStatus())) {
      assertEquals(Constant.CREDIT_MEMO_STATUS_FULLY_APPLIED, nsCreditMemo.getStatus());
    } else {
      assertEquals(Constant.STATUS_OPEN, nsCreditMemo.getStatus());
    }

    twoWayAsserter.assertEquals(creditNoteBDM.getSourceSystem().getName(), nsCreditMemo.getSource());//  CrMemo-TST-ROW 1
    // TODO twoWayAsserter.assertEquals(creditNoteBDM.getFormType(), nsCreditMemo.get??);//  CrMemo-TST-ROW 2
    // TODO twoWayAsserter.assertEquals(creditNoteBDM.getId, nsCreditMemo.get??);//  CrMemo-TST-ROW 3
    twoWayAsserter.assertEquals(creditNoteBDM.getTransactionNumber(), nsCreditMemo.getTranId());//  CrMemo-TST-ROW 4
    twoWayAsserter.assertEquals(epoch(creditNoteBDM.getTransactionDate()), epoch(nsCreditMemo.getTranDate()));//  CrMemo-TST-ROW 5
    // TODO twoWayAsserter.assertEquals(creditNoteBDM.getApplyCreditMemoToInvoice(), nsCreditMemo.get??;//  CrMemo-TST-ROW 6
    // TODO twoWayAsserter.assertEquals(creditNoteBDM.getAccountId(), nsCreditMemo.get??;//  CrMemo-TST-ROW 7
    // TODO twoWayAsserter.assertEquals(creditNoteBDM.getSubsidiaryId(), nsCreditMemo.get??;//  CrMemo-TST-ROW 8
    twoWayAsserter.assertEquals(creditNoteBDM.getInvoiceId(), nsCreditMemo.getCreatedFrom().getInternalId());//  CrMemo-TST-ROW 9
    twoWayAsserter.assertEquals(creditNoteBDM.getCurrency(), nsCreditMemo.getCurrency().getInternalId());//  CrMemo-TST-ROW 10
    twoWayAsserter.assertEquals(creditNoteBDM.getPurchaseOrderNumber(), nsCreditMemo.getOtherRefNum());//  CrMemo-TST-ROW 11
    twoWayAsserter.assertEquals(creditNoteBDM.getExchangeRate().doubleValue(), nsCreditMemo.getExchangeRate());//  CrMemo-TST-ROW 12
    // TODO twoWayAsserter.assertEquals(creditNoteBDM.getBillingContactEmail(), nsCreditMemo.get??);//  CrMemo-TST-ROW 15
    twoWayAsserter.assertEquals(creditNoteBDM.getCustomerId(), nsCreditMemo.getEntity().getInternalId());//  CrMemo-TST-ROW 66

    twoWayAsserter.assertEquals(creditNoteBDM.getMemo(), nsCreditMemo.getMemo());
    // checking CreditMemoLines
    if(nsCreditMemo.getItemList() != null && nsCreditMemo.getItemList().getItem() != null) {
      twoWayAsserter.assertEquals(
              creditNoteBDM.getCreditMemoLines().size(), nsCreditMemo.getItemList().getItem().length);
      assertCreditMemoLines(creditNoteBDM, nsCreditMemo, twoWayAsserter);
      for (int i = 0; i < creditNoteBDM.getCreditMemoLines().size(); i++) {
        CreditMemoItem nsCreditMemoItem = nsCreditMemo.getItemList().getItem()[i];
        var creditNoteLineBDM = creditNoteBDM.getCreditMemoLines().get(i);
        validateCustomFields2(nsCreditMemoItem.getCustomFieldList(), creditNoteLineBDM, twoWayAsserter);
      }
    }
    validateAddress(creditNoteBDM.getBillTo(), nsCreditMemo.getBillingAddress(), twoWayAsserter);
    validateCustomFields(nsCreditMemo.getCustomFieldList(), creditNoteBDM, twoWayAsserter);
  }

  public void assertCreditNoteBDM(CreditMemo nsCreditMemo, CreditNoteBDM creditNoteBDM, boolean expectedFirst) {
    TwoWayAsserter twoWayAsserter = new TwoWayAsserter(expectedFirst);
    assertNotNull(nsCreditMemo);
    twoWayAsserter.assertEquals(nsCreditMemo.getMemo(), creditNoteBDM.getMemo());//  CrMemo-TST-ROW 28
    validateCustomFields(nsCreditMemo.getCustomFieldList(), creditNoteBDM, twoWayAsserter);
    if(nsCreditMemo.getItemList() != null && nsCreditMemo.getItemList().getItem() != null) {
      twoWayAsserter.assertEquals(
              nsCreditMemo.getItemList().getItem().length, creditNoteBDM.getCreditMemoLines().size());
      CreditMemoItem nsCreditMemoItem = nsCreditMemo.getItemList().getItem()[0];
      var creditNoteLineBDM = creditNoteBDM.getCreditMemoLines().get(0);
      twoWayAsserter.assertEquals(nsCreditMemoItem.getDescription(), creditNoteLineBDM.getDescription());
      validateCustomFields2(nsCreditMemoItem.getCustomFieldList(), creditNoteLineBDM, twoWayAsserter);
    }
    assertApplyList(nsCreditMemo.getApplyList(), creditNoteBDM.getCreditNoteApplyLines(), twoWayAsserter);
  }

  private void assertCreditMemoLines(CreditNoteBDM creditNoteBDM, CreditMemo nsCreditMemo, TwoWayAsserter twoWayAsserter){
    if (isNull(creditNoteBDM) || ObjectUtils.isEmpty(creditNoteBDM.getCreditMemoLines())) {
      assertNull(creditNoteBDM.getCreditMemoLines());
      return;
    }
    CreditNoteLineBDM creditNoteLineBDM = creditNoteBDM.getCreditMemoLines().get(0);
    CreditMemoItem creditMemoItem = nsCreditMemo.getItemList().getItem()[0];
    // TODO twoWayAsserter.assertEquals(creditNoteLineBDM.getProductKeyId(), nsCreditMemo.get??);//  CrMemo-TST-ROW 29
    // TODO twoWayAsserter.assertEquals(creditNoteLineBDM.getClassInternalId(), nsCreditMemo.get??);//  CrMemo-TST-ROW 30
    twoWayAsserter.assertEquals(creditNoteLineBDM.getDescription(), creditMemoItem.getDescription());//  CrMemo-TST-ROW 31
    twoWayAsserter.assertEquals(creditNoteLineBDM.getTotal().doubleValue(), creditMemoItem.getAmount());//  CrMemo-TST-ROW 32
    // TODO twoWayAsserter.assertEquals(creditNoteBDM.getCreditMemoLines().get(0).getGrossAmount(), nsCreditMemo.getItemList().getItem()[0].getGrossAmt());//  CrMemo-TST-ROW 33
    // TODO creditMemoLines.orderLineId <-> creditMemoItem.lineId twoWayAsserter.assertEquals(creditNoteLineBDM.getOrderLineId(), String.valueOf(creditMemoItem.getLine()));//  CrMemo-TST-ROW 34
    twoWayAsserter.assertEquals(creditNoteLineBDM.getQuantity(), creditMemoItem.getQuantity());//  CrMemo-TST-ROW 35
    twoWayAsserter.assertEquals(epoch(creditNoteLineBDM.getRevenueStartDate()), epoch(creditMemoItem.getRevRecStartDate()));//  CrMemo-TST-ROW 36
    twoWayAsserter.assertEquals(epoch(creditNoteLineBDM.getRevenueEndDate()),  epoch(creditMemoItem.getRevRecEndDate()));//  CrMemo-TST-ROW 37
    // TODO revisit mapping is not done twoWayAsserter.assertEquals(creditNoteLineBDM.getTaxItems().get(0).getTaxAmount().doubleValue(), creditMemoItem.getTax1Amt());//  CrMemo-TST-ROW 38
    // TODO revisit mapping is not done twoWayAsserter.assertEquals(creditNoteLineBDM.getTaxItems().get(0).getTaxCode(), creditMemoItem.getTaxCode().getInternalId());//  CrMemo-TST-ROW 39
    // TODO revisit mapping is not done twoWayAsserter.assertEquals(creditNoteLineBDM.getTaxItems().get(0).getTaxRate().doubleValue(), creditMemoItem.getTaxRate1());//  CrMemo-TST-ROW 40

  }

  private void assertApplyList(
      CreditMemoApplyList creditMemoApplyList, List<CreditNoteApplyLineBDM> cnApplyLinelist, TwoWayAsserter twoWayAsserter) {
    if (isNull(creditMemoApplyList) || ObjectUtils.isEmpty(creditMemoApplyList.getApply())) {
      assertNull(cnApplyLinelist);//  CrMemo-TST-ROW 10
      return;
    }

    assertEquals(creditMemoApplyList.getApply().length, cnApplyLinelist.size());

    for (int i = 0; i < creditMemoApplyList.getApply().length; i++) {
      CreditMemoApply cmApply = creditMemoApplyList.getApply()[i];
      CreditNoteApplyLineBDM cnApplyLineBDM = cnApplyLinelist.get(i);
      if (isNull(cmApply.getDoc()) && isNull(cmApply.getRefNum()) ) continue; // apply details not present
      twoWayAsserter.assertEquals(cmApply.getDoc().toString(), cnApplyLineBDM.getInvoiceErpId());
      twoWayAsserter.assertEquals(cmApply.getRefNum(), cnApplyLineBDM.getInvoiceNumber());
      twoWayAsserter.assertEquals(cmApply.getAmount(), cnApplyLineBDM.getApplyAmount().doubleValue());
      twoWayAsserter.assertEquals(epoch(cmApply.getApplyDate()), epoch(cnApplyLineBDM.getApplyDate()));
    }
  }

  public void validateAddress(AddressBDM addressBDM, Address nsAddress, TwoWayAsserter ast) {
    ast.assertEquals(addressBDM.getLine1(), nsAddress.getAddr1());//  CrMemo-TST-ROW 67
    ast.assertEquals(addressBDM.getLine2(), nsAddress.getAddr2());//  CrMemo-TST-ROW 68
    ast.assertEquals(addressBDM.getLine3(), nsAddress.getAddr3());//  CrMemo-TST-ROW 69
    // TODO revisit mapping  billTo.name ast.assertEquals(addressBDM.getAddressee(), nsAddress.getAddressee());//  CrMemo-TST-ROW 70
    ast.assertEquals(addressBDM.getCity(), nsAddress.getCity());//  CrMemo-TST-ROW 71
   // TODO revisit mapping addressBDM.getCountry() ast.assertEquals(configProperties.getCountry().get(addressBDM.getCountry()), nsAddress.getCountry().getValue());//  CrMemo-TST-ROW 72
    ast.assertEquals(addressBDM.getPostalCode(), nsAddress.getZip());//  CrMemo-TST-ROW 73
    ast.assertEquals(addressBDM.getState(), nsAddress.getState());//  CrMemo-TST-ROW 74
    ast.assertEquals(addressBDM.getPhone(), nsAddress.getAddrPhone());//  CrMemo-TST-ROW 75
  }

  private void validateCustomFields(CustomFieldList customFieldList, CreditNoteBDM creditNoteBDM, TwoWayAsserter twoWayAsserter) {
    if (isNull(customFieldList)) {
      return;
    }
    for (CustomFieldRef customFieldRef : customFieldList.getCustomField()) {
      String scriptId = customFieldRef.getScriptId();
      CustomFieldRef stringCustomFieldRef = customFieldRef;
      switch (scriptId) {
        case "custbodybillingcontact":
          twoWayAsserter.assertEquals(stringCustomFieldRef.getValue(), creditNoteBDM.getBillingContactId());//  CrMemo-TST-ROW 13
          break;
        case "custbodytechnicalcontact":
          twoWayAsserter.assertEquals(stringCustomFieldRef.getValue(), creditNoteBDM.getTechnicalContactId());//  CrMemo-TST-ROW 14
          break;
        case "custbody_atl_cust_type":
          twoWayAsserter.assertEquals(stringCustomFieldRef.getValue(), creditNoteBDM.getCustomerType());//  CrMemo-TST-ROW 16
          break;
        case "custbody_refund_code":
          twoWayAsserter.assertEquals(stringCustomFieldRef.getValue(), creditNoteBDM.getRefundCode());//  CrMemo-TST-ROW 17
          break;
        case "custbody_refund_reason_desc":
          twoWayAsserter.assertEquals(stringCustomFieldRef.getValue(), creditNoteBDM.getRefundReasonDescription());//  CrMemo-TST-ROW 18
          break;
        case "custbody_sla_credit_check":
          twoWayAsserter.assertEquals(stringCustomFieldRef.getValue(), creditNoteBDM.getSlaCreditCheck());//  CrMemo-TST-ROW 19
          break;
        case "custbody_sla_credit_amt":
          twoWayAsserter.assertEquals(stringCustomFieldRef.getValue(), creditNoteBDM.getSlaCreditAmount());//  CrMemo-TST-ROW 20
          break;
        case "custbody_ccp_on_account_credit":
          twoWayAsserter.assertEquals(stringCustomFieldRef.getValue(), creditNoteBDM.getCcpOnAccountCredit());//  CrMemo-TST-ROW 21
          break;
        case "custbody_cr_reason_tr":
          twoWayAsserter.assertEquals(stringCustomFieldRef.getValue(), creditNoteBDM.getCreditNoteCreationReasonId());//  CrMemo-TST-ROW 22
          break;
//        case "custbodyatl_credit_memo_source":
//          twoWayAsserter.assertEquals(stringCustomFieldRef.getValue(), creditNoteBDM.getCreditMemoSource().getName());//  CrMemo-TST-ROW 23
//          break;
        case "custbody_payment_method":
          twoWayAsserter.assertEquals(stringCustomFieldRef.getValue(), creditNoteBDM.getPaymentMethod());//  CrMemo-TST-ROW 24
          break;
        case "custbody_atl_tax_print_address":
          twoWayAsserter.assertEquals(stringCustomFieldRef.getValue(), creditNoteBDM.getTaxPrintAddress());//  CrMemo-TST-ROW 25
          break;
        case "custbody_source_system":
          twoWayAsserter.assertEquals(stringCustomFieldRef.getValue(), creditNoteBDM.getCreditMemoLines().get(0).getSourceSystem());//  CrMemo-TST-ROW 26
          break;
        case "custbody_exchange_rate":
          twoWayAsserter.assertEquals(stringCustomFieldRef.getValue(), creditNoteBDM.getCreditMemoLines().get(0).getExchangeRate());//  CrMemo-TST-ROW 27
          break;
        case "cust_issueRefund":
          twoWayAsserter.assertEquals(stringCustomFieldRef.getValue(), creditNoteBDM.getIssueRefund());
          break;
        case "custbody_end_customer":
          twoWayAsserter.assertEquals(stringCustomFieldRef.getValue(), creditNoteBDM.getEndCustomerId());
          break;
        default:
          break;
      }
    }
  }

  private void validateCustomFields2(
      CustomFieldList customFieldList, CreditNoteLineBDM creditNoteLineBDM, TwoWayAsserter twoWayAsserter) {
    if (isNull(customFieldList)) {
      return;
    }
    for (CustomFieldRef customFieldRef : customFieldList.getCustomField()) {
      String scriptId = customFieldRef.getScriptId();
      if (scriptId == "custcol_is_this_a_tax_line") {
        /*BooleanCustomFieldRef booleanCustomFeildRef = (BooleanCustomFieldRef) customFieldRef;
        assertEquals(booleanCustomFeildRef.isValue(), creditNoteLineBDM.isThisaTaxLine());
        break;*/
        CustomFieldRef booleanCustomFeildRef = customFieldRef;
        twoWayAsserter.assertEquals(booleanCustomFeildRef.getValue(), creditNoteLineBDM.getIsThisaTaxLine());//  CrMemo-TST-ROW 41
        break;
      }
      CustomFieldRef stringCustomFieldRef = customFieldRef;
      switch (scriptId) {
        case "custcol_sen":
          twoWayAsserter.assertEquals(stringCustomFieldRef.getValue(), creditNoteLineBDM.getEntitlementId());//  CrMemo-TST-ROW 42
          break;
        case "custcol_poi_id":
          if (!twoWayAsserter.isForward())
            twoWayAsserter.assertEquals(stringCustomFieldRef.getValue(), creditNoteLineBDM.getLineId());//  CrMemo-TST-ROW 43
          break;
        case "custcol_billing_type":
          twoWayAsserter.assertEquals(stringCustomFieldRef.getValue(), creditNoteLineBDM.getBillingType());//  CrMemo-TST-ROW 44
          break;
          // TODO CrMemo-TST-ROW 45 scriptId ??
//        case "??":
//          twoWayAsserter.assertEquals(stringCustomFieldRef.getValue(), creditNoteLineBDM.get??);//  CrMemo-TST-ROW 45
//          break;
        case "custcolmaintstart":
          twoWayAsserter.assertEquals(stringCustomFieldRef.getValue(), creditNoteLineBDM.getMaintenanceStartDate());//  CrMemo-TST-ROW 46
          break;
        case "custcolmaintend":
          twoWayAsserter.assertEquals(stringCustomFieldRef.getValue(), creditNoteLineBDM.getMaintenanceEndDate());//  CrMemo-TST-ROW 47
          break;
        case "custcol_new_list_price":
          twoWayAsserter.assertEquals(Double.valueOf(String.valueOf(stringCustomFieldRef.getValue())), creditNoteLineBDM.getNewListPrice().doubleValue());//  CrMemo-TST-ROW 48
          break;
        case "custcol_renew_list_price":
          twoWayAsserter.assertEquals(Double.valueOf(String.valueOf(stringCustomFieldRef.getValue())), creditNoteLineBDM.getRenewListPrice().doubleValue());//  CrMemo-TST-ROW 49
          break;
        case "custcol_sale_action":
          twoWayAsserter.assertEquals(stringCustomFieldRef.getValue(), creditNoteLineBDM.getSaleAction());//  CrMemo-TST-ROW 50
          break;
        case "custcol_product_base_name":
          twoWayAsserter.assertEquals(stringCustomFieldRef.getValue(), creditNoteLineBDM.getProductBaseName());//  CrMemo-TST-ROW 51
          break;
        case "custcol_product_family":
          twoWayAsserter.assertEquals(stringCustomFieldRef.getValue(), creditNoteLineBDM.getProductFamily());//  CrMemo-TST-ROW 52
          break;
        case "custcol_product_feature_key":
          twoWayAsserter.assertEquals(stringCustomFieldRef.getValue(), creditNoteLineBDM.getProductFeatureKey());//  CrMemo-TST-ROW 53
          break;
        case "custcol_pricing_plan_id":
          twoWayAsserter.assertEquals(stringCustomFieldRef.getValue(), creditNoteLineBDM.getPricingPlanId());//  CrMemo-TST-ROW 54
          break;
        case "custcol_cm_inv_line":
          twoWayAsserter.assertEquals(stringCustomFieldRef.getValue(), creditNoteLineBDM.getInvoiceLine());//  CrMemo-TST-ROW 55
          break;
        case "custcol_hosting_type":
          twoWayAsserter.assertEquals(stringCustomFieldRef.getValue(), creditNoteLineBDM.getHostingType());//  CrMemo-TST-ROW 56
          break;
        case "custcol_custcol_inv_edition":
          twoWayAsserter.assertEquals(stringCustomFieldRef.getValue(), creditNoteLineBDM.getPacPluginEdition());//  CrMemo-TST-ROW 57
          break;
        case "custcol_inv_plugin_tot_amount":
          twoWayAsserter.assertEquals(stringCustomFieldRef.getValue(), creditNoteLineBDM.getPluginTotalAmount());//  CrMemo-TST-ROW 58
          break;
        case "custcol_marketplace_vendor":
          twoWayAsserter.assertEquals(stringCustomFieldRef.getValue(), creditNoteLineBDM.getMarketplaceVendorId());//  CrMemo-TST-ROW 59
          break;
        case "custcol_mpac_previous_hams_order_id":
          twoWayAsserter.assertEquals(stringCustomFieldRef.getValue(), creditNoteLineBDM.getMarketplacePreviousHamsOrderId());//  CrMemo-TST-ROW 60
          break;
        case "custcol_product_feature_usage":
          twoWayAsserter.assertEquals(stringCustomFieldRef.getValue(), creditNoteLineBDM.getProductFeatureUsage());//  CrMemo-TST-ROW 61
          break;
        case "custcol_discount_flag":
          twoWayAsserter.assertEquals(stringCustomFieldRef.getValue(), creditNoteLineBDM.getDiscountFlag());//  CrMemo-TST-ROW 62
          break;
        case "custcol_new_upgrade_flag":
          twoWayAsserter.assertEquals(stringCustomFieldRef.getValue(), creditNoteLineBDM.getNewUpgradeFlag());//  CrMemo-TST-ROW 63
          break;
        // TODO CrMemo-TST-ROW 64 scriptId ??
//        case "??":
//          twoWayAsserter.assertEquals(stringCustomFieldRef.getValue(), creditNoteLineBDM.get??);//  CrMemo-TST-ROW 64
//          break;
        case "custcol_manual_adj_amt":
          twoWayAsserter.assertEquals(stringCustomFieldRef.getValue(), creditNoteLineBDM.getManualAdjustmentAmount());//  CrMemo-TST-ROW 65
          break;
        default:
          break;
      }
    }
  }
}
