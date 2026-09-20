package org.firstinspires.ftc.teamcode.utilities.legacy;

/** Pedro 2.1.2 braking coefficients retained for the collision-safety model (BSD-3-Clause). */
public final class PredictiveBrakingCoefficients {
  public final double P;
  public final double kLinearBraking;
  public final double kQuadraticFriction;

  public PredictiveBrakingCoefficients(double p, double linear, double quadratic) {
    P = p;
    kLinearBraking = linear;
    kQuadraticFriction = quadratic;
  }
}
