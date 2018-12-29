package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous
public class encoderTest extends LinearOpMode {

    DcMotor leftMotor;

    @Override
    public void runOpMode() throws InterruptedException {

        hardwareMap.dcMotor.get("Left");
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        waitForStart();

        leftMotor.setTargetPosition(-280);
        leftMotor.setPower(-.5);

        telemetry.addData("Encoder Ticks", leftMotor.getCurrentPosition());
        telemetry.update();

    }
}
