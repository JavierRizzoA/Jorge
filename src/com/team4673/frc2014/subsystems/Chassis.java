package com.team4673.frc2014.subsystems;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class Chassis {
    
    Talon left, right;
    Joystick xbox;
    Thread job;
    
    JoystickButton a, b, x, y;
    double leftRatio, rightRatio;
    
    public Chassis(int rightChannel, int leftChannel, Joystick stick, int gyroChannel) {
        left = new Talon(leftChannel);
        right = new Talon(rightChannel);
        xbox = stick;
        job = new Thread(driveJob);
        leftRatio = 1;
        rightRatio = 1;
    }
    
    public void start() {
        job.start();
    }
    
    public void forward() {
        right.set(-1 * - .8);
        left.set(-1 * .8);
        Timer.delay(3);
        right.set(0);
        left.set(0);
        
        a = new JoystickButton(xbox, 1);
        b = new JoystickButton(xbox, 2);
        x = new JoystickButton(xbox, 3);
        y = new JoystickButton(xbox, 4);
        
    }
    
    public void calibrate() {
        if(a.get()) {
            rightRatio -= .1;
        }
        if(b.get()) {
            rightRatio += .1;
        }
        if(x.get()) {
            leftRatio -= .1;
        }
        if(y.get()) {
            leftRatio += .1;
        }
    }
    
    public void drive() {
        if(Math.abs(xbox.getRawAxis(2)) > 0.2) {
            if(xbox.getRawAxis(2) > 0) {
                left.set(xbox.getRawAxis(2) * -.8 * leftRatio); //IZQ FRENTE
            } else {
                left.set(xbox.getRawAxis(2) * -.8 * .8 * leftRatio); //IZQ ATRAS
            }
        } else {
            left.set(0);
        }
        if(Math.abs(xbox.getRawAxis(5)) > 0.2) {
            if(xbox.getRawAxis(5) > 0) {
                right.set(xbox.getRawAxis(5) * - -.8 * .8 * rightRatio); //DER ENFRENTE
            } else {
                right.set(xbox.getRawAxis(5) * - -.8 * rightRatio); //DER ATRÁS
            }
        } else {
            right.set(0);
        }
    }
    
    Runnable driveJob = new Runnable() {

        public void run() {
            while(true) {
                drive();
            }
        } 
    };
}
