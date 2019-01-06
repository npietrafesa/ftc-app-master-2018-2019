package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
@Disabled
@Autonomous
public class encoderTest extends LinearOpMode {

    DcMotor leftMotor;

    @Override
    public void runOpMode() {

        leftMotor = hardwareMap.dcMotor.get("Left");
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        telemetry.addData("Encoder Status:", "ready");
        telemetry.update();

        waitForStart();

        leftMotor.setTargetPosition(1440);
        while (leftMotor.isBusy() && opModeIsActive()) {
            leftMotor.setPower(-.5);
            telemetry.addData("Encoder Ticks", leftMotor.getCurrentPosition());
            telemetry.update();
        }
        leftMotor.setPower(0);
    }
}
