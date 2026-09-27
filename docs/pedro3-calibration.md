# Pedro 3 robot calibration

The tuning procedures are installed in `pedroPathing/Tuning.java`. They appear in
Pedro AutoTune's web interface, rather than as four ordinary Driver Station OpModes.
The existing match TeleOp/autonomous remain blocked until measured Foresight results
are installed. Do not replace `foresightConfig = null` with arbitrary example gains.

## 1. Install the tuning build

Build/install through Android Studio, or install the APK produced by
`./gradlew :TeamCode:assembleDebug` at
`TeamCode/build/outputs/apk/debug/TeamCode-debug.apk`.
Connect your computer to the Control Hub's Wi-Fi. Keep the Driver Station connected
and available to stop each run. Open http://192.168.43.1:10158 in your browser
(replace the host if your Robot Controller uses a different address).

The page should list Mecanum Tuner, Pinpoint Tuner, Foresight Tuner, and Tests.
Follow the web interface's prompts for starting/stopping the dynamically registered
tuning OpModes. Installing this APK alone does not start calibration motion.

## 2. Confirm the drivetrain

Secure the robot with the wheels off the ground for the Mecanum Tuner's individual
motor checks. It spins each motor; report the direction using its diagram.
The existing configuration names are:

| Wheel | Hardware name |
| --- | --- |
| Front left | `leftFront` |
| Front right | `rightFront` |
| Back left | `leftBack` |
| Back right | `rightBack` |

Send the Java output from this procedure back to the coding assistant. The results
must be installed and the app rebuilt/redeployed before driving/Foresight tests.
If the wrong physical wheel turns, correct the hardware names/ports first.

## 3. Calibrate Pinpoint

Run Pinpoint Tuner with hardware name `pinpoint`. The current code assumes goBILDA
Four Bar pods; select the actual installed pod type. Follow its prompts to push
forward, push left, and rotate counterclockwise. The localizer calibration requires
physical movement and observation, so it cannot be completed from the code editor.

Send the entire generated Java output back. The assistant will integrate measured
mechanical values into `config.yaml` and `config-docs.yaml`, regenerate the facade,
update the hardware configuration wiring, and rebuild before the next stage.

After deploying those results, choose **Tests → Pose Test**. Push the robot by hand:
forward should increase X; left should increase Y. Check measured travel against a
tape measure and check the heading while rotating. Report any reversed axes or
incorrect distance before continuing. Use **Driving Test** and **Localization Test**
only in a clear area with an operator ready to stop.

## 4. Measure Foresight

Only after drivetrain and localization pass, put the robot on the same type of
surface used for matches, with a clear area for forward travel, leftward travel,
turning, and coast-down beyond the entered distances. These upstream calibration
procedures do not use the match field-zone collision protections.

Choose **Foresight Tuner** and follow each prompted run. This measures velocities,
deceleration, braking, and gains. It deliberately drives and turns the robot.
Stop if motion or localization is wrong; report the failing step rather than
continuing with invalid data.

At completion, send the full **Java** tab output back to the assistant, together
with any failed/repeated steps. The assistant will install the measured values
through the project's configuration system, replace the null Foresight placeholder,
verify the build, and provide the next APK. No measured values have been fabricated.

## 5. Validate after installing the measured configuration

After rebuilding/redeploying, Tests supports Hold, Line, Curve, and Interpolation
tests. Use a distance that fits the available clear space, and stop between checks.
Record whether the robot reaches and holds the intended position/heading.
Then check the actual match TeleOp's forward/strafe/turn behavior, field-centric
orientation, and Sentinel/Casablanca restrictions. Validate AprilTag corrections
against a known pose before relying on them. Finally exercise autonomous paths
under supervision, including stops and teardown.

## Sources

- https://pedropathing.com/docs/pathing/installation
- https://pedropathing.com/docs/pathing/tuning
- https://pedropathing.com/docs/pathing/tuning/localization/pinpoint
- https://pedropathing.com/docs/pathing/tuning/localization
- https://pedropathing.com/docs/pathing/tuning/foresight
- https://pedropathing.com/docs/pathing/tuning/test

## What to send back

Start with the Mecanum and Pinpoint Java outputs. Pause for their integration and
a new build before Foresight. Later send the Foresight Java output and the observed
test results. Keep all generated outputs until the robot passes validation.
