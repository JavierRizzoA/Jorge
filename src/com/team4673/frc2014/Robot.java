package com.team4673.frc2014;

import com.team4673.frc2014.subsystems.Catapult;
import com.team4673.frc2014.subsystems.Chassis;
import com.team4673.frc2014.subsystems.Lifter;
import edu.wpi.first.wpilibj.Joystick;

public class Robot {
    
    public static final int RIGHT_WHEELS = 1;
    public static final int LEFT_WHEELS = 2;
    public static final int LIFTER_MOTOR = 3;
    public static final int CATAPULT_LIMIT = 1;
    public static final int UPPER_LIFTER_LIMIT = 3;
    public static final int GYRO = 5;
    public static final int RETRACTOR_CIM_CHANNEL = 7;
    public static final int CATAPULT_LIMIT_CHANNEL = 5; //????
    public static final int VALVE1_CHANNEL =2;
    public static final int VALVE2_CHANNEL = 3;
    
    Chassis chassis;
    Joystick xbox;
    Lifter lifter;
    //Catapult catapult;
    
    public Robot() {
        xbox = new Joystick(1);
        
        chassis = new Chassis(LEFT_WHEELS, RIGHT_WHEELS, xbox, GYRO);
        lifter = new Lifter(LIFTER_MOTOR, xbox);
        //catapult = new Catapult(xbox, RETRACTOR_CIM_CHANNEL, CATAPULT_LIMIT_CHANNEL, VALVE1_CHANNEL, VALVE2_CHANNEL);
    }
    
    public void teleopInit() {
        chassis.start();
        lifter.start();
        //catapult.start();
    }
    
    public void teleopPeriodic() {
        
    }
    
    public void autonomous() {
        //catapult.manageCompressor();
        chassis.forward();
    }
    
}
