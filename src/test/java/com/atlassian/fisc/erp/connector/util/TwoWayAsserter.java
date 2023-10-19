package com.atlassian.fisc.erp.connector.util;

import org.junit.jupiter.api.Assertions;

/*
 * The Junit errors for `assertEquals` are shown in the format \
 * AssertionFailedError:
 *      Expected :HAMS System
 *      Actual :HAMS Integration
 * where `expected` is the first param.
 *
 * <p>In mapping we want to re-use the assertion and still get the consistent error message with
 * correct values shown against 'expected' and 'actual' This requires a way to reverse the order or
 * parameters passed for assertion.
 */
public class TwoWayAsserter {
  private final boolean expectedFirst;

  public TwoWayAsserter(boolean expectedFirst) {
    this.expectedFirst = expectedFirst;
  }

  public void assertEquals(Object firstArg, Object secondArg) {
    if (expectedFirst) Assertions.assertEquals(firstArg, secondArg);
    else Assertions.assertEquals(secondArg, firstArg);
  }

  public boolean isForward() {
    return expectedFirst;
  }
}
