package com.atlassian.fisc.erp.connector.mappers.common;

import com.atlassian.fisc.erp.connector.domain.common.CustomFieldList;
import com.atlassian.fisc.erp.connector.domain.common.CustomFieldRef;
import com.atlassian.fisc.erp.connector.domain.common.ListOrRecordRef;
import com.atlassian.fisc.erp.connector.mappers.CustomFieldsMapper;
import com.atlassian.fisc.erp.connector.util.TestUtil;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static com.atlassian.fisc.erp.connector.util.TestUtil.epoch;

@ExtendWith(MockitoExtension.class)
class CustomFieldsMapperTest {

  @Test
  void getCustomFieldsMapTest() {
    CustomFieldList customFieldList = new CustomFieldList();
    CustomFieldRef customFieldRef = new CustomFieldRef();
    customFieldRef.setScriptId("custbody_tns_transaction_receipt_id");
    customFieldRef.setValue("TNS-123");
    customFieldList.setCustomField(new CustomFieldRef[] {customFieldRef});
    Map<String, String> customFields =
        CustomFieldsMapper.INSTANCE.getCustomFieldsMap(customFieldList);
    Assertions.assertEquals("TNS-123", customFields.get("custbody_tns_transaction_receipt_id"));
  }

  @Test
  void getCustomFieldsMapEmptyTest() {
    CustomFieldList customFieldList = new CustomFieldList();
    Map<String, String> customFields =
        CustomFieldsMapper.INSTANCE.getCustomFieldsMap(customFieldList);
    Assertions.assertTrue(customFields.isEmpty());
  }

  @Test
  void getCustomFieldValue_StringCustomFieldTest() {
    CustomFieldRef customFieldRef = new CustomFieldRef();
    customFieldRef.setScriptId("custbody_tns_transaction_receipt_id");
    customFieldRef.setValue("TNS-123");
    Assertions.assertEquals(
        "TNS-123", CustomFieldsMapper.INSTANCE.getCustomFieldValue(customFieldRef));
    Assertions.assertNull(CustomFieldsMapper.INSTANCE.getCustomFieldValue(new CustomFieldRef()));
  }

  @Test
  void getCustomFieldValue_BooleanCustomFieldTest() {
    CustomFieldRef customFieldRef = new CustomFieldRef();
    customFieldRef.setScriptId("custbody_commerce_customer");
    customFieldRef.setValue(true);
    Assertions.assertEquals(
        "true", CustomFieldsMapper.INSTANCE.getCustomFieldValue(customFieldRef));
  }

  @Test
  void getCustomFieldValue_DoubleCustomFieldTest() {
    CustomFieldRef customFieldRef = new CustomFieldRef();
    customFieldRef.setScriptId("custbody_list_price");
    customFieldRef.setValue(1d);
    Assertions.assertEquals("1.0", CustomFieldsMapper.INSTANCE.getCustomFieldValue(customFieldRef));
  }

  @SneakyThrows
  @Test
  void getCustomFieldValue_DateCustomFieldTest() {
    CustomFieldRef customFieldRef = new CustomFieldRef();
    customFieldRef.setScriptId("custbody_paid_date");
    Calendar calendar = TestUtil.catDateTime("2000-01-01T00:00:00Z");
    customFieldRef.setValue(calendar);
    Assertions.assertEquals(
        epoch("2000-01-01T00:00:00Z"),
        epoch(CustomFieldsMapper.INSTANCE.getCustomFieldValue(customFieldRef)));
  }

  @Test
  void getCustomFieldValue_SelectCustomFieldTest() {
    CustomFieldRef customFieldRef = new CustomFieldRef();
    customFieldRef.setScriptId("custbody_paid_date");
    ListOrRecordRef recordRef = new ListOrRecordRef();
    recordRef.setInternalId("1");
    customFieldRef.setValue(recordRef);
    Assertions.assertEquals("1", CustomFieldsMapper.INSTANCE.getCustomFieldValue(customFieldRef));
  }

  @Test
  void getCustomFieldValue_MultiSelectCustomFieldTest() {
    CustomFieldRef customFieldRef = new CustomFieldRef();
    customFieldRef.setScriptId("custbody_paid_date");
    ListOrRecordRef recordRef = new ListOrRecordRef();
    recordRef.setInternalId("2");
    customFieldRef.setValue(new ListOrRecordRef[] {recordRef});
    Assertions.assertEquals("2", CustomFieldsMapper.INSTANCE.getCustomFieldValue(customFieldRef));
  }

  @Test
  void getCustomFieldValue_LinkedHashMapList() {
    CustomFieldRef customFieldRef = new CustomFieldRef();
    customFieldRef.setScriptId("custbody_paid_date");
    List<LinkedHashMap<String, String>> value = new ArrayList<>();
    LinkedHashMap<String, String> map = new LinkedHashMap<>();
    map.put("internalId", "2");
    value.add(map);
    customFieldRef.setValue(value);
    Assertions.assertEquals("2", CustomFieldsMapper.INSTANCE.getCustomFieldValue(customFieldRef));
  }

  @Test
  void getCustomFieldValue_LinkedHashMapListEmpty() {
    CustomFieldRef customFieldRef = new CustomFieldRef();
    customFieldRef.setScriptId("custbody_paid_date");
    List<LinkedHashMap<String, String>> value = new ArrayList<>();
    customFieldRef.setValue(value);
    Assertions.assertNull(CustomFieldsMapper.INSTANCE.getCustomFieldValue(customFieldRef));
  }

  @Test
  void getCustomFieldValue_LinkedHashMapTest() {
    CustomFieldRef customFieldRef = new CustomFieldRef();
    customFieldRef.setScriptId("custbody_paid_date");
    LinkedHashMap<String, String> value = new LinkedHashMap<>();
    value.put("internalId", "2");
    customFieldRef.setValue(value);
    Assertions.assertEquals("2", CustomFieldsMapper.INSTANCE.getCustomFieldValue(customFieldRef));
  }

  @Test
  void getCustomFieldValue_UnknownCustomFieldTest() {
    Assertions.assertNull(CustomFieldsMapper.INSTANCE.getCustomFieldValue(new CustomFieldRef()));
  }
}
