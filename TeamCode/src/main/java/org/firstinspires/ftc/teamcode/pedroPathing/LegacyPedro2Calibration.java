package org.firstinspires.ftc.teamcode.pedroPathing;

import org.firstinspires.ftc.teamcode.utilities.legacy.PIDFCoefficients;
import org.firstinspires.ftc.teamcode.utilities.legacy.PredictiveBrakingCoefficients;

/**
 * EXACT legacy values from this team's pre-migration Constants.java. Retained solely for
 * Casablanca's existing heading lock / braking safety calculations. They are NOT a Pedro 3
 * Foresight configuration or an endorsement of unchanged tuning.
 */
public final class LegacyPedro2Calibration {
  public static final double MAX_FORWARD_VELOCITY_INCHES_PER_SECOND = 75.64281986;
  public static final double MAX_STRAFE_VELOCITY_INCHES_PER_SECOND = 58.9247686;

  private LegacyPedro2Calibration() {}

  public static PIDFCoefficients headingPidf() {
    return new PIDFCoefficients(0.7, 0.0, 0.002, 0.02);
  }

  public static PredictiveBrakingCoefficients brakingCoefficients() {
    return new PredictiveBrakingCoefficients(0.05, 0.05872647384322376, 0.001561731123457261);
  }
}
