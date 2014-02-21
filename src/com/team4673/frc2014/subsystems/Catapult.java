package com.team4673.frc2014.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;

public class Catapult {
    
    Talon retractor, trigger;
    Relay wormscrew;
    Joystick xbox;
    DigitalInput retractorLimit;
    boolean ready;
    Runnable job;
    
    public Catapult(int retractorChannel, int triggerChannel, int motorChannel, Joystick stick, int retractorLimit) {
        retractor = new Talon(retractorChannel);
        trigger = new Talon(triggerChannel);
        wormscrew = new Relay(motorChannel);
        xbox = stick;
        wormscrew.set(Relay.Value.kOff);
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
        wormscrew.set(Relay.Value.kForward);
        Timer.delay(2);
        wormscrew.set(Relay.Value.kOff);
        ready = true;
    }
    
    public void shoot() {
        trigger.set(1);
        Timer.delay(.5);
        trigger.set(0);
        Timer.delay(.5);
        trigger.set(-1);
        Timer.delay(.5);
        trigger.set(0);
        wormscrew.set(Relay.Value.kReverse);
        Timer.delay(2);
        wormscrew.set(Relay.Value.kOff);
    }
    
    public void catapult() {
        if(xbox.getTrigger(GenericHID.Hand.kRight) && ready) {
            shoot();
            retract();
        }
    }
}
