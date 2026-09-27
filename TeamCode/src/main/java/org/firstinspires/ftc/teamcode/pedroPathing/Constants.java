package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.algorithm.Foresight;
import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.follower.Follower;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.PinpointConfig;
import com.pedropathing.revhub.localizers.PinpointLocalizer;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * Pedro 3 hardware configuration and follower factory.
 *
 * <p>Motor names, motor directions and Pinpoint offsets below were carried over from the team's
 * Pedro 2 constants. They still require hardware verification after this major API upgrade.
 *
 * <p>IMPORTANT: A Pedro 2 heading PIDF / predictive-braking tune is NOT a Pedro 3 Foresight tune.
 * Obtain a ForesightConfig from the Pedro 3 Foresight procedure and replace the null field below
 * with that generated configuration. The factory deliberately refuses to operate until then. This
 * ensures that a successful Java compilation is never mistaken for a drive-ready robot.
 */
public final class Constants {
  private Constants() {}

  public static final MecanumConfig drivetrainConfig =
      new MecanumConfig(
          c -> {
            c.frontLeftName.set("leftFront");
            c.backLeftName.set("leftBack");
            c.frontRightName.set("rightFront");
            c.backRightName.set("rightBack");
            c.frontLeftDirection.set(DcMotorSimple.Direction.REVERSE);
            c.backLeftDirection.set(DcMotorSimple.Direction.FORWARD);
            c.frontRightDirection.set(DcMotorSimple.Direction.FORWARD);
            c.backRightDirection.set(DcMotorSimple.Direction.FORWARD);
            // Pedro 3 defaults to manual braking; verify braking behavior on the real robot.
          });

  public static final PinpointConfig localizerConfig =
      new PinpointConfig(
          c -> {
            c.name.set("pinpoint");
            // X pod = forward pod (offset sideways); Y pod = strafe pod (offset forward).
            c.xPodOffset.set(1.5729952);
            c.yPodOffset.set(-4.535451);
            c.offsetUnits.set(DistanceUnit.INCH);
            c.globalDistanceUnit.set(DistanceUnit.INCH);
            c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
            c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.REVERSED);
            c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
          });

  /**
   * UNCALIBRATED ON PURPOSE. Replace with the complete generated Pedro 3 ForesightConfig after the
   * robot has been tuned. Do not copy gains or example numbers from another robot.
   * https://pedropathing.com/docs/pathing/tuning/foresight
   */
  public static ForesightConfig foresightConfig = null; // and an exception when this field is null.

  // IMPORTANT NULL ABOVE
  // Why: the migration explicitly avoids pretending the old PIDF/braking tune is a valid Foresight
  // configuration.
  // Impact: at this commit, any OpMode that calls this follower factory fails during follower
  // creation until the configuration is supplied.

  public static Follower createFollower(HardwareMap hardwareMap) {
    if (foresightConfig == null) {
      throw new IllegalStateException(
          "Pedro 3 Foresight is not calibrated. Generate and install this robot's "
              + "ForesightConfig before using any drive OpMode.");
    }
    return new Follower( // IMPORTANT The follower combining those componentsReplaces the
        // builder-and-.build()
        // construction process.
        new PinpointLocalizer(hardwareMap, localizerConfig), // Your localization component
        // Replaces the builder’s .pinpointLocalizer(...) setup, using the new configuration type.
        new Mecanum(hardwareMap, drivetrainConfig), // Your drivetrain componentReplaces the
        // builder’s .mecanumDrivetrain(...) setup, using the new configuration type.
        new Foresight(foresightConfig)); // Your configured motion-control componentRequires a new
    // Foresight configuration. Your old follower gains were not automatically converted.
  } // What this does: combines the component that estimates robot position, the drivetrain

  // component,

  // and the Foresight motion-control component.Why it matters: this is the central setup used by
  // OpModes that call your follower factory. You retained hardware names, directions, and offsets,
  // but did not automatically transfer the old follower tune or explicit path constraints. Critical
  // detail: you added foresightConfig = null and an exception that blocks follower creation until
  // it is configured.

  /** Preserves the existing factory API; caching was never implemented in the old method. */
  public static Follower createCachedFollower(HardwareMap hardwareMap) {
    return createFollower(hardwareMap);
  }
}
// Replaced the old follower builder and constants classes with MecanumConfig, PinpointConfig,
// ForesightConfig, and direct follower construction. Removed the old explicit PathConstraints.
// Reason was to construct the follower using the new library interfaces.
// Impact of these changes how your drivetrain, localization, and path-control configuration are
// connected.
// Old tuning and path constraints are not automatically transferred.
