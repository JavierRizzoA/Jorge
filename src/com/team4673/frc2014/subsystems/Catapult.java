package com.team4673.frc2014.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

public class Catapult {
    
    /*Talon retractor;
    Talon piston;
    Joystick xbox;
    DigitalInput retractorLimit;
    boolean ready;
    Runnable job;
    
    Relay compressor;
    
    public Catapult() {
        //xbox = stick;
        ready = false;
        
        
        
        retract();
    }
    
    public void start() {
        job = new Runnable() {
            public void run() {
                catapult();
            }
        };
    }
    
    public void retract() {
        retractor.set(1);
        while(retractorLimit.get()) {
            continue;
        }
        retractor.set(0);
        Timer.delay(.5);
        piston.set(1);
        
        
        ready = true;
    }
    
    public void shoot() {

    }
    
    public void catapult() {
        if(xbox.getTrigger(GenericHID.Hand.kRight) && ready) {
            shoot();
            retract();
        }
    }*/
    
    Joystick xbox;
    Talon cims;
    DigitalInput limit, pressureSwitch;
    Runnable job;
    boolean ready;
    Relay compressor, valv1, valv2;;
    
    public Catapult(Joystick stick, int cimsChannel, int limitChannel, int valv1Channel, int valv2Channel) {
        xbox = stick;
        cims = new Talon(cimsChannel);
        limit = new DigitalInput(limitChannel);
        valv1 = new Relay(valv1Channel);
        valv2 = new Relay(valv2Channel);
        ready = false;
        compressor = new Relay(1);
        pressureSwitch = new DigitalInput(5);
    }
    
    public void start() {
        retract();
        job = new Runnable() {
            public void run() {
                manageCompressor();
                catapult();
            }
        };
    }
    
    public void manageCompressor() {
        if(pressureSwitch.get()) {
                    turnOffCompressor();
                } else {
                    turnOnCompressor();
                }
    }
    
    public void catapult() {
        if(xbox.getTrigger(GenericHID.Hand.kRight) && ready) {
            System.out.println("Shoot!");
            shoot();
        }
    }
    
    public void retract() {
        while(limit.get()) {
            cims.set(1);
        }
        valv2.set(Relay.Value.kForward);
        valv1.set(Relay.Value.kForward);
        cims.set(0);
        ready = true;
    }
    
    public void shoot() {
        valv2.set(Relay.Value.kOff);
        Timer.delay(1);
        valv1.set(Relay.Value.kOff);
        Timer.delay(1);
        retract();
        ready = false;
    }
    
    public void turnOnCompressor() {
        compressor.set(Relay.Value.kForward);
    }
    
    public void turnOffCompressor() {
        compressor.set(Relay.Value.kOff);
    }
}
