package com.team4673.frc2014.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class Lifter {
    
    Talon motor;
    Joystick xbox;
    JoystickButton up, down;
    Thread job;
    
    public Lifter(int channel, Joystick stick) {
        motor = new Talon(channel);
        xbox = stick;
        job = new Thread(liftJob);
        up = new JoystickButton(xbox, 6);
        down = new JoystickButton(xbox, 5);
        
    }
    
    public void start() {
        job.start();
    }
    
    public void lift() {
        if(up.get()) {  
            motor.set(4);
        } else if(down.get()) {
            motor.set(-4);
        } else {
            motor.set(0);
        }
    }
    
    Runnable liftJob = new Runnable() {
        public void run() {
            while(true) {
                lift();
            }
        }   
    };
}
