package com.atlassian.fisc.erp.connector.mappers;

import com.atlassian.finance.enums.BusinessUnit;
import com.atlassian.finance.enums.InvoiceFormType;
import com.atlassian.fisc.erp.connector.buildppln.JacocoCoverageExcludeGenerated;
import com.atlassian.fisc.erp.connector.domain.common.CustomFieldList;
import com.atlassian.fisc.erp.connector.domain.common.CustomFieldRef;
import com.atlassian.fisc.erp.connector.domain.common.ListOrRecordRef;
import com.atlassian.fisc.erp.connector.domain.common.RecordRef;
import com.atlassian.fisc.erp.connector.dto.custom.CustomFields;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NetSuiteMapperFn {
  @JacocoCoverageExcludeGenerated
  private NetSuiteMapperFn() {
    throw new IllegalStateException("Utility class");
  }

  @NetSuiteMapping
  public static String mapToString(RecordRef value) {
    if (value == null) return null;
    if (StringUtils.isNotBlank(value.getExternalId())) return value.getExternalId();
    else if (StringUtils.isNotBlank(value.getInternalId())) return value.getInternalId();
    else return null;
  }

  @NetSuiteMapping
  public static RecordRef mapToRecordRef(String value) {
    if (value == null) return null;
    var recordRef = new RecordRef();
    recordRef.setInternalId(value);
    return recordRef;
  }

  @NetSuiteMapping
  public static BusinessUnit mapStringToBusinessUnit(RecordRef value) {
    if (value == null) return BusinessUnit.ATLASSIAN_AU_BU_USD;
    if (StringUtils.isNotBlank(value.getExternalId())) return BusinessUnit.valueOfLabel(value.getExternalId());
    else if (StringUtils.isNotBlank(value.getInternalId())) return BusinessUnit.valueOfLabel(value.getInternalId());
    else return null;
  }

  @NetSuiteMapping
  public static RecordRef mapBusinessUnitToRecordRef(BusinessUnit value) {
    if (value == null) return null;
    var recordRef = new RecordRef();
    recordRef.setInternalId(value.getName());
    return recordRef;
  }

  @NetSuiteMapping
  public static RecordRef mapFormTypeEnumToString(InvoiceFormType formType) {
    if (formType == null) return null;
    var recordRef = new RecordRef();
    recordRef.setInternalId(formType.getName());
    return recordRef;
  }

  @NetSuiteMapping
  public static CustomFieldList mapToCustomFieldList(CustomFields customFields) {
    var customFieldList = new CustomFieldList();
    List<CustomFieldRef> customFieldRefs = new ArrayList<>();
    for (Field field : customFields.getClass().getDeclaredFields()) {
      try {
        Object value = field.get(customFields);
        if (value != null) {
          if (value instanceof String) {
            var stringCustomFieldRef = new CustomFieldRef();
            stringCustomFieldRef.setScriptId(field.getName());
            stringCustomFieldRef.setValue(value);
            customFieldRefs.add(stringCustomFieldRef);
          } else if (value instanceof Boolean) {
            var booleanCustomFieldRef = new CustomFieldRef();
            booleanCustomFieldRef.setScriptId(field.getName());
            booleanCustomFieldRef.setValue(value);
            customFieldRefs.add(booleanCustomFieldRef);
          } else if (value instanceof Double) {
            var doubleCustomFieldRef = new CustomFieldRef();
            doubleCustomFieldRef.setScriptId(field.getName());
            doubleCustomFieldRef.setValue(value);
            customFieldRefs.add(doubleCustomFieldRef);
          } else if (value instanceof Date) {
            var dateCustomFieldRef = new CustomFieldRef();
            dateCustomFieldRef.setScriptId(field.getName());
            dateCustomFieldRef.setValue(value);
            customFieldRefs.add(dateCustomFieldRef);
          } else if (value instanceof ListOrRecordRef
                  && StringUtils.isNotBlank(((ListOrRecordRef) value).getInternalId())) {

            var selectCustomFieldRef = new CustomFieldRef();
            selectCustomFieldRef.setScriptId(field.getName());
            selectCustomFieldRef.setValue(value);
            customFieldRefs.add(selectCustomFieldRef);
          }
        }
      } catch (IllegalAccessException e) {
        return null;
      }
    }
    customFieldList.setCustomField(
            customFieldRefs.toArray(new CustomFieldRef[customFieldRefs.size()]));
    return customFieldList;
  }
}
