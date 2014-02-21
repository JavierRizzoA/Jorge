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
    public static final int LOWER_LIFTER_LIMIT = 5;
    public static final int GYRO = 5;
    /*public static final int CATAPULT_RETRACTOR;
    public static final int CATAPULT_TRIGGER;
    public static final int CATAPULT_LOCK;*/
    
    Chassis chassis;
    Joystick xbox;
    Lifter lifter;
    
    
    public Robot() {
        xbox = new Joystick(1);
        
        chassis = new Chassis(LEFT_WHEELS, RIGHT_WHEELS, xbox, GYRO);
        lifter = new Lifter(LIFTER_MOTOR, xbox, UPPER_LIFTER_LIMIT, LOWER_LIFTER_LIMIT);
       
        chassis.start();
        lifter.start();
    }
    
    public void teleopPeriodic() {
        
    }
}
