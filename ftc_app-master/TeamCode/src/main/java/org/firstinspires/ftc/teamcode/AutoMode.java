package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class AutoMode extends LinearOpMode {

    DcMotor rightMotor;
    DcMotor leftMotor;
    DcMotor lift;
    Servo claw;
    Servo idol;


    public void moveForward(double power, long time) throws InterruptedException {
        rightMotor.setPower(power);
        leftMotor.setPower(-power);
        Thread.sleep(time);
    }

    public void turnRight(double power, long time) throws InterruptedException {
        rightMotor.setPower(power);
        leftMotor.setPower(power);
        Thread.sleep(time);
    }

    public void turnLeft(double power, long time) throws InterruptedException {
        rightMotor.setPower(-power);
        leftMotor.setPower(-power);
        Thread.sleep(time);
    }

    public void lift(double power, long time) throws InterruptedException {
        lift.setPower(-power);
    }

    public void claw(double position, long time) throws InterruptedException {
        claw.setPosition(position);
    }

    @Override
    public void runOpMode() throws InterruptedException {

        rightMotor = hardwareMap.dcMotor.get("Right");
        leftMotor = hardwareMap.dcMotor.get("Left");
        lift = hardwareMap.dcMotor.get("Lift");
        claw = hardwareMap.servo.get("Claw");
        idol = hardwareMap.servo.get("Idol");
        claw.setPosition(0);
        idol.setPosition(.75);

        waitForStart();

        claw(0, 100);
        lift(-.5, 2000);
        claw(1, 2000);
        moveForward(.75, 1000);

    }
}
