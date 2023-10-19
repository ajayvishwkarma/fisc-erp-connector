package com.atlassian.fisc.erp.connector.mappers;

import com.atlassian.fisc.erp.connector.constant.FormatConstant;
import com.atlassian.fisc.erp.connector.domain.common.CustomFieldList;
import com.atlassian.fisc.erp.connector.domain.common.CustomFieldRef;
import com.atlassian.fisc.erp.connector.domain.common.ListOrRecordRef;
import com.atlassian.fisc.erp.connector.dto.custom.CustomFields;
import lombok.SneakyThrows;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.*;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.ObjectUtils.isEmpty;

@Mapper
public interface CustomFieldsMapper {
  CustomFieldsMapper INSTANCE = Mappers.getMapper(CustomFieldsMapper.class);

  default CustomFields convert(CustomFieldList customFieldList) {
    return convertToCustomFieldList(getCustomFieldsMap(customFieldList));
  }

  @SneakyThrows
  @SuppressWarnings("all")
  default CustomFields convertToCustomFieldList(Map<String, String> customFieldsMap) {

    var customFields = new CustomFields();

    Field[] declaredFields = customFields.getClass().getDeclaredFields();

    for (Field field : declaredFields) {
      try {
        Object value = field.get(customFields);
        String name = field.getName();
        if (isNull(value) && customFieldsMap.containsKey(name)) {
          String stringValueToSet = customFieldsMap.get(name);
          Class<?> type = field.getType();
          if (type == String.class) {
            field.set(customFields, stringValueToSet);
          } else if (type == Double.class) {
            field.set(customFields, Double.valueOf(stringValueToSet));
          } else if (type == Boolean.class) {
            field.set(customFields, Boolean.parseBoolean(stringValueToSet));
          } else if (type == Date.class) {
            //TODO Class to confirm for EPOCH Date
          } else if (type == ListOrRecordRef.class) {
            var recordRef = new ListOrRecordRef();
            recordRef.setInternalId(stringValueToSet);
            field.set(customFields, recordRef);
          }
        }
      } catch (Exception e) {
        throw e;
      }
    }

    return customFields;
  }

  default Map<String, String> getCustomFieldsMap(CustomFieldList cfList) {
    if (isNull(cfList) || isEmpty(cfList.getCustomField())) return Collections.emptyMap();

    return Arrays.stream(cfList.getCustomField())
        .collect(
            HashMap::new,
            (m, v) -> m.put(v.getScriptId(), getCustomFieldValue(v)),
            HashMap::putAll);
  }

  default String getCustomFieldValue(CustomFieldRef customField) {
    if (customField == null || customField.getValue() == null) {
      return null;
    }
    if (customField.getValue() instanceof GregorianCalendar) {
      Date d = ((GregorianCalendar) customField.getValue()).getTime();
      return FormatConstant.formatHolderDateAndTime.get().format(d);
    } else if (customField.getValue() instanceof ListOrRecordRef) {
      return ((ListOrRecordRef) customField.getValue()).getInternalId();
    } else if (customField.getValue() instanceof ListOrRecordRef[]) {
      return ((ListOrRecordRef[]) customField.getValue())[0].getInternalId();
    } else if (customField.getValue() instanceof LinkedHashMap) {
      return ((LinkedHashMap<String, String>) customField.getValue()).get("internalId");
    } else if (customField.getValue() instanceof List<?>) {
      var value = (List<LinkedHashMap<String, String>>) customField.getValue();
      return CollectionUtils.isEmpty(value) ? null : value.get(0).get("internalId");
    } else {
      return String.valueOf(customField.getValue());
    }
  }
}
