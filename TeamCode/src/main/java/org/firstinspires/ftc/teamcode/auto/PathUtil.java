package org.firstinspires.ftc.teamcode.auto;

import com.pedropathing.api.Paths;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

/** Straight and curved Pedro 3 paths with the team's original linear heading timing. */
public final class PathUtil {

  private PathUtil() {}

  public static Path pline(Pose start, Pose end) {
    return Paths.line(start, end).linear(start, end);
  } // What this does: expresses your straight and curved paths through the new path-building API

  // while retaining the intended heading interpolation. Why it matters: these objects define
  // autonomous movement. Shorter construction code does not establish identical driving or
  // completion behavior, so paths need testing.-return Paths.line(start, end).linear(start, end);

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
