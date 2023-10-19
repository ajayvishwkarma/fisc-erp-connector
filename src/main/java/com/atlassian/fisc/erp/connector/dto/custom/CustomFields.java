package com.atlassian.fisc.erp.connector.dto.custom;

import com.atlassian.fisc.erp.connector.domain.common.ListOrRecordRef;
import lombok.Data;

/*
 * We need to add all custom fields in this file
 *
 * */

@Data
public class CustomFields {

    /*
     * Common Custom fields
     *
     * */
    public String custbody_source_system;
    public String custbody_payment_method;
    public String custcol_sen;
    public String custcol_sale_action;

    /*
     * Customer Custom fields
     *
     * */
    public Boolean custentity_iscommerce;
    public String custentity_gst_number;
    public String custentity_ccp_customer_category;
    public String custentity_hams_id;
    public String custentity_billing_contact_name;
    public String customer_account_id;
    public String customer_erp_id;

    /*
     * Contact Custom fields (None as of now)
     *
     * */

    /*
     * Invoice Custom fields
     *
     * */
    public String custbodypaid_date;
    public String custbody_atl_cust_type;

    /*
     * Invoice Line Custom fields
     *
     * */
    public String custcol_orig_invoice_line_id;

    /*
     * Creditmemo Custom fields
     *
     * */
    public String custbody_refund_code;
    public String custbody_sla_credit_check;
    public String custbody_refund_reason_desc;
    public String custbody_sla_credit_amt;
    public String custbody_ccp_on_account_credit;
    public ListOrRecordRef custbody_cr_reason_tr;
    public ListOrRecordRef custbodyatl_credit_memo_source;
    public String custbody_atl_tax_print_address;
    public Boolean cust_issueRefund;

    /*
     * Creditmemo Line Custom fields
     *
     * */
    public String custcol_cm_type;
    public String custbody_exchange_rate;
    public String custcol_billing_type;
    public String custcol_inv_edition;
    public String custcol_mpac_previous_hams_order_id;
    public Boolean custcol_is_this_a_tax_line;

    /*
     * Payment and Refund Custom fields
     *
     * */
    public String custbody_tns_batch_id;
    public String custbody_tns_transaction_receipt_id;
    public String custbody_subsidiary_currency;
    public String custbody_atl_common_shipregion;
    public String stripe_payment_gateway;
    public String custbody_end_customer;
    public String custbody_creditcardcountry;
    public String custbody_atl_common_billregion;
//    public String custbody_atl_common_shipregion;
    public ListOrRecordRef custbodybillingcontact;
    public ListOrRecordRef custbodytechnicalcontact;
    public Boolean custbody_contract_modification;
    public String custbodyoriginal_invoice_for_rebill;
    public String custbodycustbody_exchange_rate;
    public String custbodyrebill_invoice;
    public String custbody_orgnl_dunn_end_date;
    public String custcol_poi_id;
    public String custcolmaintstart;
    public String custcolmaintend;
    public Boolean custcol_new_upgrade_flag;
    public String custcol_prev_order_id;
    public String custcol_evergreen_recurring_flag;
    public String custcol_ccp_order_line_id;
    public String custcol_legacy_entitlement_id;
    public String custcol_ccp_entitlement_number;
    public String custcol_product_feature_usage;
    public Double custcol_new_list_price;
    public Double custcol_renew_list_price;
    public String custcol_hosting_type;
    public String billingType;
    public String custcol_manual_disc_reason;
    public String custcol_manual_adj_code;
    public Double custcol_manual_adj_amt;
    public String custcol_list_price_adj_amt;
    public String custcol_list_price_adj_code;
    public String custcol_list_price_adj_desc;
    public String custcol_disc_amount_func;
    public String custcol_discount_percentage;
    public String custcol_lp_disc_price;
    public String custcol_lp_discount_percentage;
    public String custcol_lp_discount_code;
    public String custcol_lp_discount_description;
    public String custcol_marketplace_promotion_amt;
    public String custcol_upgrade_credit_amt;
    public String custcol_credit_code;
    public String custcol_product_base_name;
    public String custcol_product_family;
    public String custcol_product_feature_key;
    public String custcol_pricing_plan_id;
    public String custcol_glp_stdlistpriceplan;
    public String custcol_advantage_pricing;
    public String custcol_grandfathered_price_plan;
    public String custcol_std_listpriceplanid;
    public String custcol_cm_inv_line;
    public String custcol_custcol_inv_edition;
    public Double custcol_inv_plugin_tot_amount;
    public ListOrRecordRef custcol_marketplace_vendor;
    public String custcol_exchange_rate;
    public String custcol_ccp_mpac_app_platform;
    public Boolean custcol_discount_flag;
    public String custcol_source_sys;

}
