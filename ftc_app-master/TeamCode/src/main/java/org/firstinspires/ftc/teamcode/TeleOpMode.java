package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class TeleOpMode extends OpMode {

    //actual code for the main robot

    DcMotor FrontRight;
    DcMotor FrontLeft;
    DcMotor BackRight;
    DcMotor BackLeft;
    Servo servo;

    @Override
    public void init() {
        FrontRight = hardwareMap.dcMotor.get("Front Right");
        FrontLeft = hardwareMap.dcMotor.get("Front Left");
        BackRight = hardwareMap.dcMotor.get("Back Right");
        BackLeft = hardwareMap.dcMotor.get("Back Left");
        servo = hardwareMap.servo.get("Servo");
    }

    @Override
    public void loop() {

        FrontRight.setPower(gamepad1.left_stick_y);
        FrontLeft.setPower(gamepad1.left_stick_y);
        BackLeft.setPower(gamepad1.left_stick_y);
        BackRight.setPower(gamepad1.left_stick_y);

    }
}
