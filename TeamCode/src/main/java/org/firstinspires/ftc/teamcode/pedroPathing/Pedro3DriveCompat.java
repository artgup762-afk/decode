package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.follower.Follower;
import com.pedropathing.math.Vector2D;

/**
 * Reproduces the Pedro 2 setTeleOpDrive(forward, lateral, turn, robotCentric) input transform.
 * Pedro 3 manual() accepts ROBOT-frame power only. This preserves the old meaning of the boolean
 * argument (true = robot centric, false = field centric) and clamps input magnitude before
 * rotating, as in v2 VectorCalculator#setTeleOpMovementVectors. Robot-specific motor directions and
 * drivetrain orientation must still be hardware-validated.
 */
public final class Pedro3DriveCompat {
  private Pedro3DriveCompat() {}

  public static void manual(
      Follower follower, double forward, double lateral, double turn, boolean robotCentric) {
    forward = Math.clamp(forward, -1.0, 1.0);
    lateral = Math.clamp(lateral, -1.0, 1.0);
    turn = Math.clamp(turn, -1.0, 1.0);
    Vector2D movement = Vector2D.cartesian(forward, lateral);
    if (movement.magnitude() > 1.0) movement = movement.div(movement.magnitude());
    if (!robotCentric) movement = movement.rotate(-follower.pose().heading());
    follower.manual(movement.x(), movement.y(), turn);
  }
}
