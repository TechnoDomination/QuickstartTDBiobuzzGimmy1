package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
//import org.firstinspires.ftc.teamcode.Subsystems.DoublePark;
//import org.firstinspires.ftc.teamcode.Drive;

@TeleOp(name = "TeleOp", group = "TeleOp")
public class TeleOpTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

         Drive drive = new Drive(hardwareMap);
         Shooter shooter = new Shooter(hardwareMap);
         Intake intake = new Intake(hardwareMap);
         Stopper stopper = new Stopper(hardwareMap);
         SmoothGamepad smoothGamepad = new SmoothGamepad();
         boolean isStarted = false;

        waitForStart();
        while (opModeIsActive() && !isStopRequested()) {

            if (!isStarted) {
                shooter.state = Shooter.State.MIDDLE;
                intake.state = Intake.State.FORWARD;
                stopper.state = Stopper.State.CLOSE;
                isStarted = true;
            }

            shooter.update();
            intake.update();
            stopper.update();

            drive.update(smoothGamepad.smoothGamepad(-gamepad1.left_stick_y), smoothGamepad.smoothGamepad(gamepad1.left_stick_x), smoothGamepad.smoothGamepad(gamepad1.right_stick_x));


            //stopper
            if (gamepad1.left_bumper) {
                stopper.state = Stopper.State.CLOSE;
            }
            if (gamepad1.right_bumper) {
                stopper.state = Stopper.State.OPEN;
            }

            //shooter
            if (gamepad1.y) {
                shooter.offset+=20;
            }
            if (gamepad1.a) {
                shooter.offset-=20;
            }

            //intake
            if (gamepad1.dpad_up) {
                intake.state = Intake.State.FORWARD;
            }
            if (gamepad1.dpad_down) {
                intake.state = Intake.State.BACKWARD;
            }
            if (gamepad1.dpad_left) {
                intake.state = Intake.State.FULLSPEED;
            }
            if (gamepad1.dpad_right) {
                intake.state = Intake.State.SLOWDOWN;
            }


            telemetry.addData("Shooter Telemetry: ", shooter.getShooterTelemetry());
            telemetry.addData("Intake Telemetry" , intake.getIntakeTelemetry());
            telemetry.addData("Stopper Telemetry" , stopper.getTurretGateTelemetry());
            telemetry.update();

        }
    }
}
