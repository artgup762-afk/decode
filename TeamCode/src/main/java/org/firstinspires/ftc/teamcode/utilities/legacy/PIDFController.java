package org.firstinspires.ftc.teamcode.utilities.legacy;

/**
 * Team-owned version of the Pedro 2.1.2 PIDF update equations for independent mechanisms. Kept
 * separate from Pedro 3's path-follower controllers so the existing flywheel and turret control
 * laws and gains are not silently retuned. Adapted from Pedro-Pathing v2.1.2 (BSD-3-Clause).
 */
public final class PIDFController {
  private final PIDFCoefficients coefficients;
  private double previousError;
  private double error;
  private double targetPosition;
  private double errorIntegral;
  private double errorDerivative;
  private double feedForwardInput;
  private long previousUpdateTimeNano;

  public PIDFController(PIDFCoefficients coefficients) {
    this.coefficients = coefficients;
    reset();
  }

  public double run() {
    return error * coefficients.P
        + errorDerivative * coefficients.D
        + errorIntegral * coefficients.I
        + feedForwardInput * coefficients.F;
  }

  public void updatePosition(double position) {
    previousError = error;
    error = targetPosition - position;
    long deltaTimeNano = System.nanoTime() - previousUpdateTimeNano;
    previousUpdateTimeNano = System.nanoTime();
    double seconds = deltaTimeNano / 1e9;
    errorIntegral += error * seconds;
    errorDerivative = (error - previousError) / seconds;
  }

  public void updateError(double error) {
    previousError = this.error;
    this.error = error;
    long nanoTime = System.nanoTime();
    long deltaTimeNano = nanoTime - previousUpdateTimeNano;
    previousUpdateTimeNano = nanoTime;
    double seconds = deltaTimeNano / 1e9;
    errorIntegral += error * seconds;
    errorDerivative = (error - previousError) / seconds;
  }

  public void updateFeedForwardInput(double input) {
    feedForwardInput = input;
  }

  public void reset() {
    previousError = 0;
    error = 0;
    targetPosition = 0;
    errorIntegral = 0;
    errorDerivative = 0;
    previousUpdateTimeNano = System.nanoTime();
  }

  public void setTargetPosition(double position) {
    targetPosition = position;
  }
}
