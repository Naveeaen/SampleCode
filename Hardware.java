package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Hardware {

    //constructor
    private static Hardware myInstance = null;
    public static Hardware getInstance() {
        if (myInstance == null)
            myInstance = new Hardware();
        return myInstance;
    }

    //define hardware stuff (variables)
    Servo pivot;
    Servo claw;
    DcMotor arm;

    //initialize hardware stuff (method)
    public void init(HardwareMap hwMap){
        arm = hwMap.get(DcMotor.class, "cm0"); // congifure deviceName in driver station
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        pivot = hwMap.get(Servo.class, "cs0");

        claw = hwMap.get(Servo.class, "cs1");
    }

    //method for arm
    public void armTo(int pos, double pow){
        arm.setTargetPosition(pos);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        arm.setPower(pow);
    }
    public void armTo(int pos){
        armTo(pos, 1);
    }
}