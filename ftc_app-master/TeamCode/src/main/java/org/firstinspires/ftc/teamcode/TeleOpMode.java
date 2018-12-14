package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class TeleOpMode extends OpMode {

    //actual code for the main robot

    DcMotor rightMotor;
    DcMotor leftMotor;
    DcMotor lift;
    DcMotor claw;
    DcMotor clawPivot;
    Servo servo;
    CRServo contServo;

    @Override
    public void init() {
        rightMotor = hardwareMap.dcMotor.get("Right");
        leftMotor = hardwareMap.dcMotor.get("Left");
        lift = hardwareMap.dcMotor.get("Lift");
        claw = hardwareMap.dcMotor.get("Claw");
        clawPivot = hardwareMap.dcMotor.get("Claw2");
        servo = hardwareMap.servo.get("Servo");
        contServo = hardwareMap.crservo.get("Cont");
        servo.setPosition(1);
        contServo.setPower(0);
    }

    @Override
    public void loop() {

        rightMotor.setPower(gamepad1.right_stick_y);
        leftMotor.setPower(-1 * (gamepad1.left_stick_y));
        lift.setPower(gamepad2.left_stick_y);
        if (gamepad1.a) {
            servo.setPosition(.5);
        } else {
            servo.setPosition(1);
        }
        claw.setPower(gamepad2.right_stick_y);
        if (gamepad2.left_bumper) {
            contServo.setPower(gamepad2.left_stick_y);
        }
    }
}
