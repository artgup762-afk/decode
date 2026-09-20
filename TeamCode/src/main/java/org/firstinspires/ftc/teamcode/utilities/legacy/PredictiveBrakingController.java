package org.firstinspires.ftc.teamcode.utilities.legacy;

/** Pedro 2.1.2 braking displacement and output equations, retained for safety (BSD-3-Clause). */
public final class PredictiveBrakingController {
  private final PredictiveBrakingCoefficients coefficients;

  public PredictiveBrakingController(PredictiveBrakingCoefficients coefficients) {
    this.coefficients = coefficients;
  }

  public double computeOutput(double error, double velocity) {
    return coefficients.P * (error - computeBrakingDisplacement(velocity, Math.signum(velocity)));
  }

  public double computeBrakingDisplacement(double velocity, double directionOfMotion) {
    return directionOfMotion * velocity * velocity * coefficients.kQuadraticFriction
        + velocity * coefficients.kLinearBraking;
  }
}
