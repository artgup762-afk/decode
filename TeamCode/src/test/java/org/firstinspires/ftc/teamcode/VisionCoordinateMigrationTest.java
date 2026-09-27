package org.firstinspires.ftc.teamcode;

import static org.junit.Assert.assertEquals;

import com.pedropathing.math.Pose;
import org.firstinspires.ftc.teamcode.utilities.VisionUtil;
import org.junit.Test;

public class VisionCoordinateMigrationTest {
  @Test
  public void preservesPedro2InvertedFtcCoordinateMapping() {
    Pose converted = VisionUtil.legacyDetectionToFieldPose(15.0, -28.0, 0.45);
    assertEquals(-28.0, converted.x(), 1e-10);
    assertEquals(-15.0, converted.y(), 1e-10);
    assertEquals(0.45, converted.heading(), 1e-10);

    Pose opposite = VisionUtil.legacyDetectionToFieldPose(-35.0, 24.0, -1.2);
    assertEquals(24.0, opposite.x(), 1e-10);
    assertEquals(35.0, opposite.y(), 1e-10);
    assertEquals(-1.2 + 2 * Math.PI, opposite.heading(), 1e-10);
  }
}
