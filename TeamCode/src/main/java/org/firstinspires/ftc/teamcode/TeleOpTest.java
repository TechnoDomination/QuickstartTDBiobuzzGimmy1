package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
//import org.firstinspires.ftc.teamcode.Subsystems.DoublePark;
import org.firstinspires.ftc.teamcode.Drive;

@TeleOp(name = "TeleOpTestIntake", group = "TeleOp")
public class TeleOpTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

         Drive drive = new Drive(hardwareMap);
        boolean isStarted = false;

        waitForStart();
        while (opModeIsActive() && !isStopRequested()) {


            drive.update(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);

        }
    }
}
