package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.algorithm.Foresight;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.localizers.PinpointLocalizer;
import com.pedropathing.tuning.autotune.Procedure;
import com.pedropathing.tuning.autotune.Tuner;
import org.firstinspires.ftc.teamcode.pedroPathing.procedures.ForesightTuner;
import org.firstinspires.ftc.teamcode.pedroPathing.procedures.MecanumTuner;
import org.firstinspires.ftc.teamcode.pedroPathing.procedures.PinpointTuner;
import org.firstinspires.ftc.teamcode.pedroPathing.procedures.Tests;

/**
 * Official Pedro 3 tuning procedures, available at the robot's port 10158.
 *
 * <p>The original Pedro 2 SelectableOpMode implementation is preserved verbatim in
 * migration-archive/Pedro2-Tuning.java.txt, outside the Android source tree. It depends on APIs
 * Pedro 3 removed and cannot be registered as a working OpMode. The factories below register the
 * replacement procedures from the official Quickstart.
 *
 * <p>https://pedropathing.com/docs/pathing/tuning/foresight
 * https://pedropathing.com/docs/pathing/tuning/localization/pinpoint
 */
public final class Tuning {
  private Tuning() {}

  @Tuner
  public static Procedure mecanumTuner() {
    return new MecanumTuner();
  }

  @Tuner
  public static Procedure pinpointTuner() {
    return new PinpointTuner();
  }

  @Tuner
  public static Procedure foresightTuner() {
    // Calibration must work before a calibrated follower can be created.
    return new ForesightTuner(
        hardwareMap -> new PinpointLocalizer(hardwareMap, Constants.localizerConfig),
        hardwareMap -> new Mecanum(hardwareMap, Constants.drivetrainConfig));
  }

  @Tuner
  public static Procedure tests() {
    // Localization checks work before tuning; path tests require measured Foresight gains.
    return new Tests(
        hardwareMap -> new Mecanum(hardwareMap, Constants.drivetrainConfig),
        hardwareMap -> new PinpointLocalizer(hardwareMap, Constants.localizerConfig),
        Constants.foresightConfig == null ? null : () -> new Foresight(Constants.foresightConfig));
  }
}
