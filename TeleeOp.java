package org.firstinspires.ftc.teamcode.SampleCode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.sun.tools.javac.comp.Todo;

@TeleOp(name = "TeleOp") //set name of class on driver station
public class TeleeOp extends LinearOpMode {

    public void runOpMode(){
        Hardware robot = Hardware.getInstance(); // access the Hardware class as 'robot'
        robot.init(hardwareMap); // call Hardware initialization method

        // define variables outside of loop
        double pivotPos = 0;
        double clawPos = 0;
        int armPos = 0;

        // TODO: get these values using a tester class (they are likely not all 0)
        double clawOpenPos = 0;
        double clawClosePos = 0;
        int armUpPos = 0;

        boolean yPressed = false;
        boolean aPressed = false;

        // loop to make things go (put functions inside brackets)
        waitForStart();
        while(opModeIsActive()){

            // pivot servo joystick control
            if(gamepad2.right_stick_x != 0               // if right stick is powered (x)
                    && robot.pivot.getPosition() < 0.66  // set pivot position limits
                    && robot.pivot.getPosition() > 0) {
                pivotPos += 0.01*gamepad2.right_stick_x; // increment pivot position
                robot.pivot.setPosition(pivotPos);       // set pivot to said position
            }

            // arm motor joystick control
            if(gamepad2.left_stick_y != 0                    // if left stick is powered (y)
                    && robot.arm.getCurrentPosition() < armUpPos  // set arm position limits
                    && robot.arm.getCurrentPosition() > 0) {
                armPos += 10*gamepad2.left_stick_y;          // incrememnt arm position
                robot.armTo(armPos);                         // run arm to said position
            }

            // claw servo trigger control
            if(gamepad2.right_trigger > 0                      // if right trigger is powered
                    && robot.claw.getPosition() < clawClosePos){  // (and within upper claw position limit)
                clawPos += 0.02*gamepad2.right_trigger;        // increment claw position positively
                robot.claw.setPosition(clawPos);               // set claw to said position
            }
            if(gamepad2.left_trigger > 0                      // if left trigger is powered
                    && robot.claw.getPosition() > clawOpenPos){  // (and within lower claw position limit)
                clawPos -= 0.02*gamepad2.left_trigger;        // increment claw position negatively
                robot.claw.setPosition(clawPos);              // set claw to said position
            }

            // set position buttons
            if(gamepad2.y && !yPressed){
                robot.pivot.setPosition(0.66);
                robot.armTo(0);
                yPressed = true;
            } else if(!gamepad2.y){
                yPressed = false;
            }
            if(gamepad2.a && !aPressed){
                robot.pivot.setPosition(0);
                robot.armTo(armUpPos);
                aPressed = true;
            } else if(!gamepad2.a){
                aPressed = false;
            }

        }
    }
}
