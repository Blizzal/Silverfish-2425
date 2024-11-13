package org.firstinspires.ftc.teamcode.Autonomous;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name = "Blue NZ level2")
public class Autonomous_newRobot1 extends LinearOpMode {
    DcMotor linearSlide;
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;

    @Override
    public void runOpMode() throws InterruptedException {
        // Initializes the motors
        frontLeft = hardwareMap.dcMotor.get("FL");
        frontRight = hardwareMap.dcMotor.get("FR");
        backLeft = hardwareMap.dcMotor.get("BL");
        backRight = hardwareMap.dcMotor.get("BR");
        linearSlide = hardwareMap.dcMotor.get("S");

        // Resets encoders on the motors so that they start at 0.
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // All motors will be using run to position encoders.
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        linearSlide.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        telemetry.addLine("Press start");
        telemetry.update();


        waitForStart();

        // Rotate left 90 degrees
        drive(true, false, true, false, 0.8, 1075);

        // Drive straight
        drive(false, false, false, false, 0.8, 1075);

        // Rotate right 90 degrees
        drive(false, true, false, true, 0.8, 1075);

        // Drive Straight
        drive(false, false, false, false, 0.8, 1075);

        // Supposed to collect a specimen here!!

        // Rotate left 180 degrees
        drive(true, false, true, false, 0.8, 2150);

        // Drive Straight
        drive(false, false,false, false, 0.8, 1075);

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);

        // Optional: Place specimen in basket
        // moveSlide(500, 0.5);
    }

    // A method to move the linear slide.
    void moveSlide(double position, double power) {
        // Slide moves within a range so it doesn't break.
        if (position < 0 || position > 1000) {
            telemetry.addLine("Invalid position")
                    .addData("Position", position);
            telemetry.update();
            return;
        }

        linearSlide.setTargetPosition((int) position);

        while (linearSlide.isBusy() && opModeIsActive()) {
            linearSlide.setPower(power);
        }

        linearSlide.setPower(0);
    }

    void drive(boolean reverseFrontLeft, boolean reverseFrontRight, boolean reverseBackLeft, boolean reverseBackRight, double power, int position){
        if (reverseFrontLeft){
            frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        }

        if (reverseFrontRight){
            frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        }

        if (reverseBackLeft){
            backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        }

        if (reverseBackRight){
            backRight.setDirection(DcMotorSimple.Direction.REVERSE);
        }

        frontLeft.setTargetPosition(position);
        frontRight.setTargetPosition(position);
        backLeft.setTargetPosition(position);
        backRight.setTargetPosition(position);

        while (opModeIsActive() && frontLeft.isBusy() && frontRight.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy()){
            if (reverseFrontLeft){
                frontLeft.setPower(-power);
            } else {
                frontLeft.setPower(power);
            }

            if (reverseFrontRight){
                frontRight.setPower(-power);
            } else {
                frontRight.setPower(power);
            }

            if (reverseBackLeft){
                backLeft.setPower(-power);
            } else {
                backLeft.setPower(power);
            }

            if (reverseBackRight){
                backRight.setPower(-power);
            } else {
                backRight.setPower(power);
            }

        }
    }
}