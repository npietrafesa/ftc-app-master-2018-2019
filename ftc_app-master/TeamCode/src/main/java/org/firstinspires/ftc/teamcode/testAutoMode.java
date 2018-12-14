package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.TouchSensor;

@Autonomous
public class testAutoMode extends LinearOpMode{

//test autonomous, get ready for a shitshow down here

    DcMotor rightMotor;
    DcMotor leftMotor;
    DcMotor lift;
    TouchSensor touch;

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
        while (!touch.isPressed()) {
            lift.setPower(.5);
        }
        lift.setPower(0);
    }
    public void stopMoving() {
        rightMotor.setPower(0);
        leftMotor.setPower(0);
    }

    @Override
    public void runOpMode() throws InterruptedException {

        rightMotor = hardwareMap.dcMotor.get("Right");
        leftMotor = hardwareMap.dcMotor.get("Left");
        lift = hardwareMap.dcMotor.get("Lift");
        touch = hardwareMap.touchSensor.get("Touch");

        moveForward(1, 1000);
        turnRight(1, 1000);
        moveForward(.5, 1000);
        turnLeft(.25, 2000);

    }
}
