package org.firstinspires.ftc.teamcode.utilities.legacy;

/** Nanosecond elapsed timer, retaining the Pedro 2.1.2 API (BSD-3-Clause). */
public final class NanoTimer {
  private long startTime;

  public NanoTimer() {
    resetTimer();
  }

  public void resetTimer() {
    startTime = System.nanoTime();
  }

  public double getElapsedTimeSeconds() {
    return (System.nanoTime() - startTime) / 1e9;
  }
}
