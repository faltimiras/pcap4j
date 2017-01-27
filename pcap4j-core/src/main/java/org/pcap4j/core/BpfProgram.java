/*_##########################################################################
  _##
  _##  Copyright (C) 2013-2016  Pcap4J.org
  _##
  _##########################################################################
*/

package org.pcap4j.core;

import org.pcap4j.core.NativeMappings.bpf_program;

/**
 * @author Kaito Yamada
 * @since pcap4j 0.9.16
 */
public final class BpfProgram {

  private final bpf_program program;
  private final String expression;
  private volatile boolean freed = false;
  private final Object lock = new Object();

  BpfProgram(bpf_program program, String expression) {
    this.program = program;
    this.expression = expression;
  }

  bpf_program getProgram() {
    return program;
  }

  /**
   *
   * @return expression
   */
  public String getExpression() {
    return expression;
  }

  /**
   *
   * @return true if the bpf_program represented by this object is freed;
   *         false otherwise.
   */
  public boolean isFreed() {
    return freed;
  }

  /**
   * Releases the resource this object holds in the native memory.
   * This method takes effect only at the first call, and does nothing at later calls.
   * It's required to call this method before this object is GCed in order to avoid memory leak.
   */
  public void free() {
    if (freed) {
      return;
    }
    synchronized (lock) {
      if (freed) {
        return;
      }
      NativeMappings.pcap_freecode(program);
      freed = true;
    }
  }

  /**
   *
   * @author Kaito Yamada
   * @version pcap4j 0.9.16
   */
  public static enum BpfCompileMode {

    /**
     *
     */
    OPTIMIZE(1),

    /**
     *
     */
    NONOPTIMIZE(0);

    private final int value;

    private BpfCompileMode(int value) {
      this.value = value;
    }

    /**
     *
     * @return value
     */
    public int getValue() {
      return value;
    }

  }

}
