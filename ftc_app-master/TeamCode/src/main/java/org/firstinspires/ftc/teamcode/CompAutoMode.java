package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class CompAutoMode extends LinearOpMode {

    //vars
    private static double encoderTicksPerRevolution = 1120; //NeveRest 40 have 1120 ppr
    private static double wheelDiameter = 4.0; //wheels are 4 inches in diameter
    private static double wheelGearReduction = 1.0; //gears are in a 1:1 ratio, so no change
    private static double wheelEncoderTicksPerInch = ((encoderTicksPerRevolution * wheelGearReduction) / (wheelDiameter * 3.1415)); //basic circumference equation to find how many encoder ticks are in one inch travelled.
    private static double liftGearDiameter = 1;
    private static double liftGearReduction = 2;
    private static double liftEncoderTicksPerInch = ((encoderTicksPerRevolution * liftGearReduction) / (liftGearDiameter * 3.1415));

    DcMotor rightMotor;
    DcMotor leftMotor;
    DcMotor lift;
    Servo liftClaw;
    Servo idol;

    //methods
    private void changeState(Step newState) {
        state = newState;
    }

    public void resetState() {
        changeState(Step.readyRobot);
    }

    public Step getState(Step state) {
        return state;
    }

    public void moveForward(double power, double inches) {
        while (opModeIsActive()) {
            rightMotor.setTargetPosition((int) (inches * wheelEncoderTicksPerInch));
            leftMotor.setTargetPosition((int) (inches * wheelEncoderTicksPerInch));
            rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightMotor.setPower(power);
            leftMotor.setPower(power);
        }

    }

    public void lowerLift(double power, double inches) {
        while (opModeIsActive()) {
            lift.setTargetPosition((int) (inches * liftEncoderTicksPerInch));
            lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            lift.setPower(power); //do not set this above .5, the lift is sensitive
            liftClaw.setPosition(1);
            if (inches >= 4) { //maximum height before lift grinds
                lift.setPower(0);
            }
        }
    }

    public void stopRobot() {
        rightMotor.setPower(0);
        leftMotor.setPower(0);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    //state machine
    public enum Step {
        readyRobot,
        moveOffLander,
        moveForward,
        stopRobot
    }

    Step state;

    @Override
    public void runOpMode() {

        //hardware map
        rightMotor = hardwareMap.dcMotor.get("Right");
        leftMotor = hardwareMap.dcMotor.get("Left");
        lift = hardwareMap.dcMotor.get("Lift");
        liftClaw = hardwareMap.servo.get("Claw");
        idol = hardwareMap.servo.get("Idol");
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        idol.setPosition(.75);

        //start encoders
        telemetry.addData("Status", "Resetting Encoders");
        telemetry.update();
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        telemetry.addData("Status", "Encoders Ready");
        telemetry.update();
        liftClaw.setPosition(0);

        waitForStart();

        resetState();

        //start of autonomous
        while(opModeIsActive()) {
            switch (state) {
                case readyRobot:
                    sleep(2000);
                    changeState(Step.moveOffLander);
                    telemetry.addData("Done", "First State");
                    telemetry.update();
                    sleep(3000);
                    break;
                case moveOffLander:
                    telemetry.addData("Starting", "Second State");
                    telemetry.update();
                    sleep(3000);
                    lowerLift(.5, 3);
                    changeState(Step.moveForward);
                    telemetry.addData("Done", "Second State");
                    telemetry.update();
                    sleep(3000);
                    break;
                case moveForward:
                    moveForward(.5, 4);
                    changeState(Step.stopRobot);
                    break;
                case stopRobot:
                    stopRobot();
                    break;
                default:
                    telemetry.addData("Error", "Something Broke");
                    break;
            }
            telemetry.addData("Step:", getState(state));
            telemetry.update();
        }

    }
}
