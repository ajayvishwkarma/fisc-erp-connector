package com.atlassian.fisc.erp.connector.domain.customer;

import com.atlassian.fisc.erp.connector.domain.common.Country;
import com.atlassian.fisc.erp.connector.domain.common.CustomFieldList;
import com.atlassian.fisc.erp.connector.domain.common.Record;
import com.atlassian.fisc.erp.connector.domain.common.RecordRef;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Calendar;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Customer extends Record implements Serializable {

  private RecordRef customForm;
  private String entityId;
  private String altName;
  private Boolean isPerson;
  private String phoneticName;
  private String salutation;
  private String firstName;
  private String middleName;
  private String lastName;
  private String companyName;
  private RecordRef entityStatus;
  private RecordRef parent;
  private String phone;
  private String fax;
  private String email;
  private String url;
  private String defaultAddress;
  private Boolean isInactive;
  private RecordRef category;
  private String title;
  private String printOnCheckAs;
  private String altPhone;
  private String homePhone;
  private String mobilePhone;
  private String altEmail;
  private String comments;
  private Calendar dateCreated;
  private RecordRef image;
  private RecordRef subsidiary;
  private RecordRef representingSubsidiary;
  private RecordRef salesRep;
  private RecordRef territory;
  private String contribPct;
  private RecordRef partner;
  private RecordRef salesGroup;
  private String vatRegNumber;
  private String accountNumber;
  private Boolean taxExempt;
  private RecordRef terms;
  private Double creditLimit;
  private Boolean overrideCurrencyFormat;
  private String displaySymbol;
  private Double balance;
  private Double overdueBalance;
  private Long daysOverdue;
  private Double unbilledOrders;
  private Double consolUnbilledOrders;
  private Double consolOverdueBalance;
  private Double consolDepositBalance;
  private Double consolBalance;
  private Double consolAging;
  private Double consolAging1;
  private Double consolAging2;
  private Double consolAging3;
  private Double consolAging4;
  private Long consolDaysOverdue;
  private RecordRef priceLevel;
  private RecordRef currency;
  private RecordRef prefCCProcessor;
  private Double depositBalance;
  private Boolean shipComplete;
  private Boolean taxable;
  private RecordRef taxItem;
  private String resaleNumber;
  private Double aging;
  private Double aging1;
  private Double aging2;
  private Double aging3;
  private Double aging4;
  private Calendar startDate;
  private Calendar endDate;
  private Long reminderDays;
  private RecordRef shippingItem;
  private String thirdPartyAcct;
  private String thirdPartyZipcode;
  private Country thirdPartyCountry;
  private Boolean giveAccess;
  private Double estimatedBudget;
  private RecordRef accessRole;
  private Boolean sendEmail;
  private String password;
  private String password2;
  private Boolean requirePwdChange;
  private RecordRef campaignCategory;
  private RecordRef leadSource;
  private RecordRef receivablesAccount;
  private RecordRef drAccount;
  private RecordRef fxAccount;
  private Double defaultOrderPriority;
  private String webLead;
  private String referrer;
  private String keywords;
  private String clickStream;
  private String lastPageVisited;
  private Long visits;
  private Calendar firstVisit;
  private Calendar lastVisit;
  private Boolean billPay;
  private Double openingBalance;
  private Calendar lastModifiedDate;
  private Calendar openingBalanceDate;
  private RecordRef openingBalanceAccount;
  private Boolean emailTransactions;
  private Boolean printTransactions;
  private Boolean faxTransactions;
  private Boolean syncPartnerTeams;
  private Boolean isBudgetApproved;
  private RecordRef salesReadiness;
  private RecordRef buyingReason;
  private RecordRef buyingTimeFrame;
  private CustomerAddressbookList addressbookList;
  private CustomFieldList customFieldList;
  private String internalId;
  private String externalId;
  @JsonIgnore private final Object __equalsCalc = null;
  @JsonIgnore private final boolean __hashCodeCalc = false;

  public Customer() {}

  public Customer(
      String internalId,
      String externalId,
      RecordRef customForm,
      String entityId,
      String altName,
      Boolean isPerson,
      String phoneticName,
      String salutation,
      String firstName,
      String middleName,
      String lastName,
      String companyName,
      RecordRef entityStatus,
      RecordRef parent,
      String phone,
      String fax,
      String email,
      String url,
      String defaultAddress,
      Boolean isInactive,
      RecordRef category,
      String title,
      String printOnCheckAs,
      String altPhone,
      String homePhone,
      String mobilePhone,
      String altEmail,
      String comments,
      Calendar dateCreated,
      RecordRef image,
      RecordRef subsidiary,
      RecordRef representingSubsidiary,
      RecordRef salesRep,
      RecordRef territory,
      String contribPct,
      RecordRef partner,
      RecordRef salesGroup,
      String vatRegNumber,
      String accountNumber,
      Boolean taxExempt,
      RecordRef terms,
      Double creditLimit,
      Boolean overrideCurrencyFormat,
      String displaySymbol,
      Double balance,
      Double overdueBalance,
      Long daysOverdue,
      Double unbilledOrders,
      Double consolUnbilledOrders,
      Double consolOverdueBalance,
      Double consolDepositBalance,
      Double consolBalance,
      Double consolAging,
      Double consolAging1,
      Double consolAging2,
      Double consolAging3,
      Double consolAging4,
      Long consolDaysOverdue,
      RecordRef priceLevel,
      RecordRef currency,
      RecordRef prefCCProcessor,
      Double depositBalance,
      Boolean shipComplete,
      Boolean taxable,
      RecordRef taxItem,
      String resaleNumber,
      Double aging,
      Double aging1,
      Double aging2,
      Double aging3,
      Double aging4,
      Calendar startDate,
      Calendar endDate,
      Long reminderDays,
      RecordRef shippingItem,
      String thirdPartyAcct,
      String thirdPartyZipcode,
      Country thirdPartyCountry,
      Boolean giveAccess,
      Double estimatedBudget,
      RecordRef accessRole,
      Boolean sendEmail,
      String password,
      String password2,
      Boolean requirePwdChange,
      RecordRef campaignCategory,
      RecordRef leadSource,
      RecordRef receivablesAccount,
      RecordRef drAccount,
      RecordRef fxAccount,
      Double defaultOrderPriority,
      String webLead,
      String referrer,
      String keywords,
      String clickStream,
      String lastPageVisited,
      Long visits,
      Calendar firstVisit,
      Calendar lastVisit,
      Boolean billPay,
      Double openingBalance,
      Calendar lastModifiedDate,
      Calendar openingBalanceDate,
      RecordRef openingBalanceAccount,
      Boolean emailTransactions,
      Boolean printTransactions,
      Boolean faxTransactions,
      Boolean syncPartnerTeams,
      Boolean isBudgetApproved,
      RecordRef buyingReason,
      RecordRef buyingTimeFrame,
      CustomerAddressbookList addressbookList,
      CustomFieldList customFieldList) {
    this.internalId = internalId;
    this.externalId = externalId;
    this.customForm = customForm;
    this.entityId = entityId;
    this.altName = altName;
    this.isPerson = isPerson;
    this.phoneticName = phoneticName;
    this.salutation = salutation;
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.companyName = companyName;
    this.entityStatus = entityStatus;
    this.parent = parent;
    this.phone = phone;
    this.fax = fax;
    this.email = email;
    this.url = url;
    this.defaultAddress = defaultAddress;
    this.isInactive = isInactive;
    this.category = category;
    this.title = title;
    this.printOnCheckAs = printOnCheckAs;
    this.altPhone = altPhone;
    this.homePhone = homePhone;
    this.mobilePhone = mobilePhone;
    this.altEmail = altEmail;
    this.comments = comments;
    this.dateCreated = dateCreated;
    this.image = image;
    this.subsidiary = subsidiary;
    this.representingSubsidiary = representingSubsidiary;
    this.salesRep = salesRep;
    this.territory = territory;
    this.contribPct = contribPct;
    this.partner = partner;
    this.salesGroup = salesGroup;
    this.vatRegNumber = vatRegNumber;
    this.accountNumber = accountNumber;
    this.taxExempt = taxExempt;
    this.terms = terms;
    this.creditLimit = creditLimit;
    this.overrideCurrencyFormat = overrideCurrencyFormat;
    this.displaySymbol = displaySymbol;
    this.balance = balance;
    this.overdueBalance = overdueBalance;
    this.daysOverdue = daysOverdue;
    this.unbilledOrders = unbilledOrders;
    this.consolUnbilledOrders = consolUnbilledOrders;
    this.consolOverdueBalance = consolOverdueBalance;
    this.consolDepositBalance = consolDepositBalance;
    this.consolBalance = consolBalance;
    this.consolAging = consolAging;
    this.consolAging1 = consolAging1;
    this.consolAging2 = consolAging2;
    this.consolAging3 = consolAging3;
    this.consolAging4 = consolAging4;
    this.consolDaysOverdue = consolDaysOverdue;
    this.priceLevel = priceLevel;
    this.currency = currency;
    this.prefCCProcessor = prefCCProcessor;
    this.depositBalance = depositBalance;
    this.shipComplete = shipComplete;
    this.taxable = taxable;
    this.taxItem = taxItem;
    this.resaleNumber = resaleNumber;
    this.aging = aging;
    this.aging1 = aging1;
    this.aging2 = aging2;
    this.aging3 = aging3;
    this.aging4 = aging4;
    this.startDate = startDate;
    this.endDate = endDate;
    this.reminderDays = reminderDays;
    this.shippingItem = shippingItem;
    this.thirdPartyAcct = thirdPartyAcct;
    this.thirdPartyZipcode = thirdPartyZipcode;
    this.thirdPartyCountry = thirdPartyCountry;
    this.giveAccess = giveAccess;
    this.estimatedBudget = estimatedBudget;
    this.accessRole = accessRole;
    this.sendEmail = sendEmail;
    this.password = password;
    this.password2 = password2;
    this.requirePwdChange = requirePwdChange;
    this.campaignCategory = campaignCategory;
    this.leadSource = leadSource;
    this.receivablesAccount = receivablesAccount;
    this.drAccount = drAccount;
    this.fxAccount = fxAccount;
    this.defaultOrderPriority = defaultOrderPriority;
    this.webLead = webLead;
    this.referrer = referrer;
    this.keywords = keywords;
    this.clickStream = clickStream;
    this.lastPageVisited = lastPageVisited;
    this.visits = visits;
    this.firstVisit = firstVisit;
    this.lastVisit = lastVisit;
    this.billPay = billPay;
    this.openingBalance = openingBalance;
    this.lastModifiedDate = lastModifiedDate;
    this.openingBalanceDate = openingBalanceDate;
    this.openingBalanceAccount = openingBalanceAccount;
    this.emailTransactions = emailTransactions;
    this.printTransactions = printTransactions;
    this.faxTransactions = faxTransactions;
    this.syncPartnerTeams = syncPartnerTeams;
    this.isBudgetApproved = isBudgetApproved;
    this.salesReadiness = salesReadiness;
    this.buyingReason = buyingReason;
    this.buyingTimeFrame = buyingTimeFrame;
    this.addressbookList = addressbookList;
    this.customFieldList = customFieldList;
  }

  public String getEntityId() {
    return this.entityId;
  }

  public void setEntityId(String entityId) {
    this.entityId = entityId;
  }

  public String getAltName() {
    return this.altName;
  }

  public void setAltName(String altName) {
    this.altName = altName;
  }

  public Boolean getIsPerson() {
    return this.isPerson;
  }

  public void setIsPerson(Boolean isPerson) {
    this.isPerson = isPerson;
  }

  public String getPhoneticName() {
    return this.phoneticName;
  }

  public void setPhoneticName(String phoneticName) {
    this.phoneticName = phoneticName;
  }

  public RecordRef getCustomForm() {
    return this.customForm;
  }

  public void setCustomForm(RecordRef customForm) {
    this.customForm = customForm;
  }

  public String getSalutation() {
    return this.salutation;
  }

  public void setSalutation(String salutation) {
    this.salutation = salutation;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getMiddleName() {
    return this.middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getCompanyName() {
    return this.companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public RecordRef getEntityStatus() {
    return this.entityStatus;
  }

  public void setEntityStatus(RecordRef entityStatus) {
    this.entityStatus = entityStatus;
  }

  public RecordRef getParent() {
    return this.parent;
  }

  public void setParent(RecordRef parent) {
    this.parent = parent;
  }

  public String getPhone() {
    return this.phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getFax() {
    return this.fax;
  }

  public void setFax(String fax) {
    this.fax = fax;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUrl() {
    return this.url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getDefaultAddress() {
    return this.defaultAddress;
  }

  public void setDefaultAddress(String defaultAddress) {
    this.defaultAddress = defaultAddress;
  }

  public Boolean getIsInactive() {
    return this.isInactive;
  }

  public void setIsInactive(Boolean isInactive) {
    this.isInactive = isInactive;
  }

  public RecordRef getCategory() {
    return this.category;
  }

  public void setCategory(RecordRef category) {
    this.category = category;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getPrintOnCheckAs() {
    return this.printOnCheckAs;
  }

  public void setPrintOnCheckAs(String printOnCheckAs) {
    this.printOnCheckAs = printOnCheckAs;
  }

  public String getAltPhone() {
    return this.altPhone;
  }

  public void setAltPhone(String altPhone) {
    this.altPhone = altPhone;
  }

  public String getHomePhone() {
    return this.homePhone;
  }

  public void setHomePhone(String homePhone) {
    this.homePhone = homePhone;
  }

  public String getMobilePhone() {
    return this.mobilePhone;
  }

  public void setMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
  }

  public String getAltEmail() {
    return this.altEmail;
  }

  public void setAltEmail(String altEmail) {
    this.altEmail = altEmail;
  }

  public String getComments() {
    return this.comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  public Calendar getDateCreated() {
    return this.dateCreated;
  }

  public void setDateCreated(Calendar dateCreated) {
    this.dateCreated = dateCreated;
  }

  public RecordRef getImage() {
    return this.image;
  }

  public void setImage(RecordRef image) {
    this.image = image;
  }

  public RecordRef getSubsidiary() {
    return this.subsidiary;
  }

  public void setSubsidiary(RecordRef subsidiary) {
    this.subsidiary = subsidiary;
  }

  public RecordRef getRepresentingSubsidiary() {
    return this.representingSubsidiary;
  }

  public void setRepresentingSubsidiary(RecordRef representingSubsidiary) {
    this.representingSubsidiary = representingSubsidiary;
  }

  public RecordRef getSalesRep() {
    return this.salesRep;
  }

  public void setSalesRep(RecordRef salesRep) {
    this.salesRep = salesRep;
  }

  public RecordRef getTerritory() {
    return this.territory;
  }

  public void setTerritory(RecordRef territory) {
    this.territory = territory;
  }

  public String getContribPct() {
    return this.contribPct;
  }

  public void setContribPct(String contribPct) {
    this.contribPct = contribPct;
  }

  public RecordRef getPartner() {
    return this.partner;
  }

  public void setPartner(RecordRef partner) {
    this.partner = partner;
  }

  public RecordRef getSalesGroup() {
    return this.salesGroup;
  }

  public void setSalesGroup(RecordRef salesGroup) {
    this.salesGroup = salesGroup;
  }

  public String getVatRegNumber() {
    return this.vatRegNumber;
  }

  public void setVatRegNumber(String vatRegNumber) {
    this.vatRegNumber = vatRegNumber;
  }

  public String getAccountNumber() {
    return this.accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public Boolean getTaxExempt() {
    return this.taxExempt;
  }

  public void setTaxExempt(Boolean taxExempt) {
    this.taxExempt = taxExempt;
  }

  public RecordRef getTerms() {
    return this.terms;
  }

  public void setTerms(RecordRef terms) {
    this.terms = terms;
  }

  public Double getCreditLimit() {
    return this.creditLimit;
  }

  public void setCreditLimit(Double creditLimit) {
    this.creditLimit = creditLimit;
  }

  public Boolean getOverrideCurrencyFormat() {
    return this.overrideCurrencyFormat;
  }

  public void setOverrideCurrencyFormat(Boolean overrideCurrencyFormat) {
    this.overrideCurrencyFormat = overrideCurrencyFormat;
  }

  public String getDisplaySymbol() {
    return this.displaySymbol;
  }

  public void setDisplaySymbol(String displaySymbol) {
    this.displaySymbol = displaySymbol;
  }

  public Double getBalance() {
    return this.balance;
  }

  public void setBalance(Double balance) {
    this.balance = balance;
  }

  public Double getOverdueBalance() {
    return this.overdueBalance;
  }

  public void setOverdueBalance(Double overdueBalance) {
    this.overdueBalance = overdueBalance;
  }

  public Long getDaysOverdue() {
    return this.daysOverdue;
  }

  public void setDaysOverdue(Long daysOverdue) {
    this.daysOverdue = daysOverdue;
  }

  public Double getUnbilledOrders() {
    return this.unbilledOrders;
  }

  public void setUnbilledOrders(Double unbilledOrders) {
    this.unbilledOrders = unbilledOrders;
  }

  public Double getConsolUnbilledOrders() {
    return this.consolUnbilledOrders;
  }

  public void setConsolUnbilledOrders(Double consolUnbilledOrders) {
    this.consolUnbilledOrders = consolUnbilledOrders;
  }

  public Double getConsolOverdueBalance() {
    return this.consolOverdueBalance;
  }

  public void setConsolOverdueBalance(Double consolOverdueBalance) {
    this.consolOverdueBalance = consolOverdueBalance;
  }

  public Double getConsolDepositBalance() {
    return this.consolDepositBalance;
  }

  public void setConsolDepositBalance(Double consolDepositBalance) {
    this.consolDepositBalance = consolDepositBalance;
  }

  public Double getConsolBalance() {
    return this.consolBalance;
  }

  public void setConsolBalance(Double consolBalance) {
    this.consolBalance = consolBalance;
  }

  public Double getConsolAging() {
    return this.consolAging;
  }

  public void setConsolAging(Double consolAging) {
    this.consolAging = consolAging;
  }

  public Double getConsolAging1() {
    return this.consolAging1;
  }

  public void setConsolAging1(Double consolAging1) {
    this.consolAging1 = consolAging1;
  }

  public Double getConsolAging2() {
    return this.consolAging2;
  }

  public void setConsolAging2(Double consolAging2) {
    this.consolAging2 = consolAging2;
  }

  public Double getConsolAging3() {
    return this.consolAging3;
  }

  public void setConsolAging3(Double consolAging3) {
    this.consolAging3 = consolAging3;
  }

  public Double getConsolAging4() {
    return this.consolAging4;
  }

  public void setConsolAging4(Double consolAging4) {
    this.consolAging4 = consolAging4;
  }

  public Long getConsolDaysOverdue() {
    return this.consolDaysOverdue;
  }

  public void setConsolDaysOverdue(Long consolDaysOverdue) {
    this.consolDaysOverdue = consolDaysOverdue;
  }

  public RecordRef getPriceLevel() {
    return this.priceLevel;
  }

  public void setPriceLevel(RecordRef priceLevel) {
    this.priceLevel = priceLevel;
  }

  public RecordRef getCurrency() {
    return this.currency;
  }

  public void setCurrency(RecordRef currency) {
    this.currency = currency;
  }

  public RecordRef getPrefCCProcessor() {
    return this.prefCCProcessor;
  }

  public void setPrefCCProcessor(RecordRef prefCCProcessor) {
    this.prefCCProcessor = prefCCProcessor;
  }

  public Double getDepositBalance() {
    return this.depositBalance;
  }

  public void setDepositBalance(Double depositBalance) {
    this.depositBalance = depositBalance;
  }

  public Boolean getShipComplete() {
    return this.shipComplete;
  }

  public void setShipComplete(Boolean shipComplete) {
    this.shipComplete = shipComplete;
  }

  public Boolean getTaxable() {
    return this.taxable;
  }

  public void setTaxable(Boolean taxable) {
    this.taxable = taxable;
  }

  public RecordRef getTaxItem() {
    return this.taxItem;
  }

  public void setTaxItem(RecordRef taxItem) {
    this.taxItem = taxItem;
  }

  public String getResaleNumber() {
    return this.resaleNumber;
  }

  public void setResaleNumber(String resaleNumber) {
    this.resaleNumber = resaleNumber;
  }

  public Double getAging() {
    return this.aging;
  }

  public void setAging(Double aging) {
    this.aging = aging;
  }

  public Double getAging1() {
    return this.aging1;
  }

  public void setAging1(Double aging1) {
    this.aging1 = aging1;
  }

  public Double getAging2() {
    return this.aging2;
  }

  public void setAging2(Double aging2) {
    this.aging2 = aging2;
  }

  public Double getAging3() {
    return this.aging3;
  }

  public void setAging3(Double aging3) {
    this.aging3 = aging3;
  }

  public Double getAging4() {
    return this.aging4;
  }

  public void setAging4(Double aging4) {
    this.aging4 = aging4;
  }

  public Calendar getStartDate() {
    return this.startDate;
  }

  public void setStartDate(Calendar startDate) {
    this.startDate = startDate;
  }

  public Calendar getEndDate() {
    return this.endDate;
  }

  public void setEndDate(Calendar endDate) {
    this.endDate = endDate;
  }

  public Long getReminderDays() {
    return this.reminderDays;
  }

  public void setReminderDays(Long reminderDays) {
    this.reminderDays = reminderDays;
  }

  public RecordRef getShippingItem() {
    return this.shippingItem;
  }

  public void setShippingItem(RecordRef shippingItem) {
    this.shippingItem = shippingItem;
  }

  public String getThirdPartyAcct() {
    return this.thirdPartyAcct;
  }

  public void setThirdPartyAcct(String thirdPartyAcct) {
    this.thirdPartyAcct = thirdPartyAcct;
  }

  public String getThirdPartyZipcode() {
    return this.thirdPartyZipcode;
  }

  public void setThirdPartyZipcode(String thirdPartyZipcode) {
    this.thirdPartyZipcode = thirdPartyZipcode;
  }

  public Country getThirdPartyCountry() {
    return this.thirdPartyCountry;
  }

  public void setThirdPartyCountry(Country thirdPartyCountry) {
    this.thirdPartyCountry = thirdPartyCountry;
  }

  public Boolean getGiveAccess() {
    return this.giveAccess;
  }

  public void setGiveAccess(Boolean giveAccess) {
    this.giveAccess = giveAccess;
  }

  public Double getEstimatedBudget() {
    return this.estimatedBudget;
  }

  public void setEstimatedBudget(Double estimatedBudget) {
    this.estimatedBudget = estimatedBudget;
  }

  public RecordRef getAccessRole() {
    return this.accessRole;
  }

  public void setAccessRole(RecordRef accessRole) {
    this.accessRole = accessRole;
  }

  public Boolean getSendEmail() {
    return this.sendEmail;
  }

  public void setSendEmail(Boolean sendEmail) {
    this.sendEmail = sendEmail;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPassword2() {
    return this.password2;
  }

  public void setPassword2(String password2) {
    this.password2 = password2;
  }

  public Boolean getRequirePwdChange() {
    return this.requirePwdChange;
  }

  public void setRequirePwdChange(Boolean requirePwdChange) {
    this.requirePwdChange = requirePwdChange;
  }

  public RecordRef getCampaignCategory() {
    return this.campaignCategory;
  }

  public void setCampaignCategory(RecordRef campaignCategory) {
    this.campaignCategory = campaignCategory;
  }

  public RecordRef getLeadSource() {
    return this.leadSource;
  }

  public void setLeadSource(RecordRef leadSource) {
    this.leadSource = leadSource;
  }

  public RecordRef getReceivablesAccount() {
    return this.receivablesAccount;
  }

  public void setReceivablesAccount(RecordRef receivablesAccount) {
    this.receivablesAccount = receivablesAccount;
  }

  public RecordRef getDrAccount() {
    return this.drAccount;
  }

  public void setDrAccount(RecordRef drAccount) {
    this.drAccount = drAccount;
  }

  public RecordRef getFxAccount() {
    return this.fxAccount;
  }

  public void setFxAccount(RecordRef fxAccount) {
    this.fxAccount = fxAccount;
  }

  public Double getDefaultOrderPriority() {
    return this.defaultOrderPriority;
  }

  public void setDefaultOrderPriority(Double defaultOrderPriority) {
    this.defaultOrderPriority = defaultOrderPriority;
  }

  public String getWebLead() {
    return this.webLead;
  }

  public void setWebLead(String webLead) {
    this.webLead = webLead;
  }

  public String getReferrer() {
    return this.referrer;
  }

  public void setReferrer(String referrer) {
    this.referrer = referrer;
  }

  public String getKeywords() {
    return this.keywords;
  }

  public void setKeywords(String keywords) {
    this.keywords = keywords;
  }

  public String getClickStream() {
    return this.clickStream;
  }

  public void setClickStream(String clickStream) {
    this.clickStream = clickStream;
  }

  public String getLastPageVisited() {
    return this.lastPageVisited;
  }

  public void setLastPageVisited(String lastPageVisited) {
    this.lastPageVisited = lastPageVisited;
  }

  public Long getVisits() {
    return this.visits;
  }

  public void setVisits(Long visits) {
    this.visits = visits;
  }

  public Calendar getFirstVisit() {
    return this.firstVisit;
  }

  public void setFirstVisit(Calendar firstVisit) {
    this.firstVisit = firstVisit;
  }

  public Calendar getLastVisit() {
    return this.lastVisit;
  }

  public void setLastVisit(Calendar lastVisit) {
    this.lastVisit = lastVisit;
  }

  public Boolean getBillPay() {
    return this.billPay;
  }

  public void setBillPay(Boolean billPay) {
    this.billPay = billPay;
  }

  public Double getOpeningBalance() {
    return this.openingBalance;
  }

  public void setOpeningBalance(Double openingBalance) {
    this.openingBalance = openingBalance;
  }

  public Calendar getLastModifiedDate() {
    return this.lastModifiedDate;
  }

  public void setLastModifiedDate(Calendar lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
  }

  public Calendar getOpeningBalanceDate() {
    return this.openingBalanceDate;
  }

  public void setOpeningBalanceDate(Calendar openingBalanceDate) {
    this.openingBalanceDate = openingBalanceDate;
  }

  public RecordRef getOpeningBalanceAccount() {
    return this.openingBalanceAccount;
  }

  public void setOpeningBalanceAccount(RecordRef openingBalanceAccount) {
    this.openingBalanceAccount = openingBalanceAccount;
  }

  public Boolean getEmailTransactions() {
    return this.emailTransactions;
  }

  public void setEmailTransactions(Boolean emailTransactions) {
    this.emailTransactions = emailTransactions;
  }

  public Boolean getPrintTransactions() {
    return this.printTransactions;
  }

  public void setPrintTransactions(Boolean printTransactions) {
    this.printTransactions = printTransactions;
  }

  public Boolean getFaxTransactions() {
    return this.faxTransactions;
  }

  public void setFaxTransactions(Boolean faxTransactions) {
    this.faxTransactions = faxTransactions;
  }

  public Boolean getSyncPartnerTeams() {
    return this.syncPartnerTeams;
  }

  public void setSyncPartnerTeams(Boolean syncPartnerTeams) {
    this.syncPartnerTeams = syncPartnerTeams;
  }

  public Boolean getIsBudgetApproved() {
    return this.isBudgetApproved;
  }

  public void setIsBudgetApproved(Boolean isBudgetApproved) {
    this.isBudgetApproved = isBudgetApproved;
  }

  public RecordRef getSalesReadiness() {
    return this.salesReadiness;
  }

  public void setSalesReadiness(RecordRef salesReadiness) {
    this.salesReadiness = salesReadiness;
  }

  public RecordRef getBuyingReason() {
    return this.buyingReason;
  }

  public void setBuyingReason(RecordRef buyingReason) {
    this.buyingReason = buyingReason;
  }

  public RecordRef getBuyingTimeFrame() {
    return this.buyingTimeFrame;
  }

  public void setBuyingTimeFrame(RecordRef buyingTimeFrame) {
    this.buyingTimeFrame = buyingTimeFrame;
  }

  public CustomerAddressbookList getAddressbookList() {
    return this.addressbookList;
  }

  public void setAddressbookList(CustomerAddressbookList addressbookList) {
    this.addressbookList = addressbookList;
  }

  public CustomFieldList getCustomFieldList() {
    return this.customFieldList;
  }

  public void setCustomFieldList(CustomFieldList customFieldList) {
    this.customFieldList = customFieldList;
  }

  public String getInternalId() {
    return this.internalId;
  }

  public void setInternalId(String internalId) {
    this.internalId = internalId;
  }

  public String getExternalId() {
    return this.externalId;
  }

  public void setExternalId(String externalId) {
    this.externalId = externalId;
  }

}
