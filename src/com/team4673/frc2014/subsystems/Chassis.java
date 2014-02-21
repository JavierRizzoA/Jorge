package com.team4673.frc2014.subsystems;

import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;

public class Chassis {
    
    Talon left, right;
    Joystick xbox;
    Thread job;
    
    public Chassis(int rightChannel, int leftChannel, Joystick stick, int gyroChannel) {
        left = new Talon(leftChannel);
        right = new Talon(rightChannel);
        xbox = stick;
        job = new Thread(driveJob);
    }
    
    public void start() {
        job.start();
    }
    
    public void drive() {
        if(Math.abs(xbox.getRawAxis(2)) > 0.2) {
            if(xbox.getRawAxis(2) > 0) {
                left.set(xbox.getRawAxis(2) * .75 * .75);
            } else {
                left.set(xbox.getRawAxis(2) * .75 * .75);
            }
        } else {
            left.set(0);
        }
        if(Math.abs(xbox.getRawAxis(5)) > 0.2) {
            if(xbox.getRawAxis(5) > 0) {
                right.set(xbox.getRawAxis(5) * - .75 * .75);
            } else {
                right.set(xbox.getRawAxis(5) * - .75 * .75);
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
