package com.atlassian.fisc.erp.connector.constant;

import com.atlassian.fisc.erp.connector.buildppln.JacocoCoverageExcludeGenerated;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.TimeZone;

public class FormatConstant {
  @JacocoCoverageExcludeGenerated
  private FormatConstant() {
    throw new IllegalStateException("Utility class");
  }

  public static final ThreadLocal<SimpleDateFormat> formatHolderDateAndTime =
      ThreadLocal.withInitial(
          () -> {
            var sdf = new SimpleDateFormat(Constant.DATE_TIME_FORMAT);
            sdf.setTimeZone(TimeZone.getTimeZone(ZoneId.of("UTC")));
            return sdf;
          });
}
