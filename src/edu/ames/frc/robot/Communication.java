/* Currently managed by: Levi Raby
 * 
 * 
 * 
 */
package edu.ames.frc.robot;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Watchdog;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Communication;



public class Communication {
    
    public static boolean isinit = false;   // make sure we're already initted
    public static boolean debugmode = false; // debugging symbols enabled/disabled?
    public static long time; 
    public static int step = 0;
    public static double voltage;
    public static boolean mainlcd = false;  // enable/disable main led
    public static boolean sensorlighton;
    public static String[] messages = new String[5];
    public static int cycle = 0;            // how many clock cycles the robot ran, divided by 500
    
    public void ConsoleMsg (String msg, int type)
    {
        messages[type]=msg;
    }
    
    public void MsgPrint ()
    {       long newtime =System.currentTimeMillis();
            if (newtime - time > 500){
                
                if (!messages[1].equals(" ")) {      
                        SmartDashboard.putString("Shooter speed: ", messages[1]);
            } 
           
                
                
                
                
                
                
    }
    
    
} 
