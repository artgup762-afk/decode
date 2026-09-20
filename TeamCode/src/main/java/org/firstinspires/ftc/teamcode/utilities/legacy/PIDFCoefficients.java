package org.firstinspires.ftc.teamcode.utilities.legacy;

/**
 * Pedro 2.1.2 coefficient data retained for the team's standalone shooter/turret loops. These gains
 * are not Pedro 3's follower-controller configuration. Algorithm adapted from
 * Pedro-Pathing/PedroPathing v2.1.2 (BSD-3-Clause).
 */
public final class PIDFCoefficients {
  public double P, I, D, F;

  public PIDFCoefficients(double p, double i, double d, double f) {
    setCoefficients(p, i, d, f);
  }

  public void setCoefficients(double p, double i, double d, double f) {
    P = p;
    I = i;
    D = d;
    F = f;
  }
}
