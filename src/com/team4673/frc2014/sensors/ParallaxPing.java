package com.team4673.frc2014.sensors;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;

public class ParallaxPing {
    
    Servo servo;
    int channel;
    
    public ParallaxPing(int pingChannel, int servoChannel) {
        channel = pingChannel;
        servo = new Servo(servoChannel);        
    }
    
    public void move(double angle) {
        servo.set(angle);
    }
    
    public double getDistCM() {
        ping();
        return pulseIn(true) / 29 / 2;
    }
    
    private void ping() {
        DigitalOutput out = new DigitalOutput(channel);
        out.set(true);
        Timer.delay(.01);
        out.set(false);
        out.free();
    }
    
    private double pulseIn(boolean state) {
        DigitalInput in = new DigitalInput(channel);
        Timer timer = new Timer();
        while(in.get() != state) {
            continue;
        }
        timer.start();
        while(in.get() == state) {
            continue;
        }
        timer.stop();
        in.free();
        return timer.get() * 1000000;
    }
}
