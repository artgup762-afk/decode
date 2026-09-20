package org.firstinspires.ftc.teamcode.auto;

import com.pedropathing.api.Paths;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

/** Straight and curved Pedro 3 paths with the team's original linear heading timing. */
public final class PathUtil {

  private PathUtil() {}

  public static Path pline(Pose start, Pose end) {
    return Paths.line(start, end).linear(start, end);
  }

  public static Path pline(Pose start, Pose end, double headingEndTime) {
    return Paths.line(start, end).linear(start, end, headingEndTime);
  }

  public static Path pcurve(Pose start, Pose controlPoint, Pose end) {
    return Paths.curve(start, controlPoint, end).linear(start, end);
  }

  public static Path pcurve(Pose start, Pose controlPoint, Pose end, double headingEndTime) {
    return Paths.curve(start, controlPoint, end).linear(start, end, headingEndTime);
  }
}
