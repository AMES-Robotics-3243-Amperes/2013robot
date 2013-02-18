/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 * 
 * This class polls robot sensors and switches, such as gyros, buttons, and accelerometers.
 */
/*
 * This is a test to teach programming team.
 */
package edu.ames.frc.robot;

import com.sun.squawk.util.MathUtils;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Encoder;

public class SensorInput {

    static Gyro gy;
    static Encoder rotaryEncoder;

    /* Initialize sensor values & variables */
    void init() {
        gy = new Gyro(RobotMap.gyroport);
        gy.reset();
        rotaryEncoder = new Encoder(1,2);
        rotaryEncoder.start();
        rotaryEncoder.reset();//Justin Case, attorney at law!
    }

    public double getFinalAngle(boolean nodeg) {
        double gangl = 0;
        gangl = getGyroAngle();
        if (nodeg) {
            gangl = convertToRadian(gangl);
        }
        return gangl;
    }

    /* Get the absolute angle of the robot */
    double getGyroAngle() {
        double a = gy.getAngle();
        return a;
    }

    double convertToRadian(double deg) {
        deg *= 180 / 3.141592653589793238462643383279;
        //Im a bad person XD
        return deg;
    }
    double encoderRot(){
        double res = 0;
        res = rotaryEncoder.getRaw();
        return res;
    }

    /* useful to have, just in case our robot gyro drifts */
    public void resetGyroAngle() {
        gy.reset();
    }
}
