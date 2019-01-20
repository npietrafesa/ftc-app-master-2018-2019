package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class calibrateBot extends LinearOpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor lift;
    Servo liftClaw;
    Servo idol;

    public void calibrateMotors(double power, long time) throws InterruptedException{
        leftMotor.setPower(power);
        rightMotor.setPower(power);
    }
    @Override
    public void runOpMode() throws InterruptedException {

        leftMotor = hardwareMap.dcMotor.get("Left");
        rightMotor = hardwareMap.dcMotor.get("Right");
        lift = hardwareMap.dcMotor.get("Lift");
        liftClaw = hardwareMap.servo.get("Claw");
        idol = hardwareMap.servo.get("Idol");
        liftClaw.setPosition(0);
        idol.setPosition(-.1);

        waitForStart();

        calibrateMotors(.5, 5000);

    }
}
