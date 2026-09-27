# Official Pedro Quickstart procedures

Imported from https://github.com/Pedro-Pathing/Quickstart at commit
`79670fb9c432a3f5342054d69358f3826ac07f1c` (2026-09-27).

Files: `MecanumTuner.java`, `PinpointTuner.java`, `ForesightTuner.java`, `Tests.java`.
Local changes: package relocated from `pedro.procedures` to `pedroPathing.procedures`,
and project Spotless formatting. The upstream license is retained in `LICENSE.txt`.

These are upstream calibration implementations, including their experimental constants,
not team-specific mechanical tuning values. Keep algorithm changes upstream where possible.
Team integration and registration live in `../Tuning.java`.

These procedures operate calibration hardware directly and do not instantiate the match
Robot or provide Sentinel/Casablanca field-zone protection. Run only as attended calibration
in a clear test area, not as match driving modes. Normal match code retains its protections.

Instructions and measurement handoff: `docs/pedro3-calibration.md` at the repository root.
