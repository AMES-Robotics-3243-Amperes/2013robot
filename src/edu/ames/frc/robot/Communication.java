/* Currently managed by: Levi Raby,Tarun Sunkaraneni, Adam Jessop
 * 
 * 
 * 
 */
package edu.ames.frc.robot;
//Non-explicit imports of io libraries. Once code is finished it should be changed into a set of explicit imports.
import java.io.*;
import javax.microedition.io.*;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
 import edu.wpi.first.wpilibj.command.Subsystem;


/* import edu.wpi.first.wpilibj.command.Command;
 * import edu.wpi.first.wpilibj.Communication;
 * import edu.wpi.first.wpilibj.templates.OI;
 * 
 * 
 * 
 * 
 */


public class Communication {

    public static boolean debugmode = false; // debugging symbols enabled/disabled?
    public static long time;
    public static int step = 0;
    public static double voltage;
    public static boolean sensorlighton;
    public static String[] messages = new String[5];
    public static int cycle = 0;            // how many clock cycles the robot ran, divided by 500

    
    
    
    //Speed & Direction
    public void RobotSpeed(double Speedo ){
        long newtime = System.currentTimeMillis();
        String RS = "Speed:";
        SmartDashboard.getDouble(RS , Speedo); 
    }
    
    public void RobotDirection(double x){
        long newtime = System.currentTimeMillis();
        String RD ="Robot Direction:";
        SmartDashboard.getDouble(RD, x);
        
    }
    
    
   /* public void UpdateMsg()                          ****Conceputual Mumbo Jumbo****
    {
             ConsolMsg("update", true, 0);
     }
    
    
    public void ConsolMsg(String msg, boolean NewStuff, int type){ //Sends Messages to Consol
                long newtime = System.currentTimeMillis();

        if (newtime - time > 500) {
                if (!"update".equals(msg)) {
                    if (!messages[1].equals(" ")) {                                     // Shooter Speed
                        SmartDashboard.putString("Shooter Speed: ", messages[1]);
                    }
                    
                    if (!messages[2].equals(" ")) {                                     // shooter Angle 
                        SmartDashboard.putString("Shooter Angle: ", messages[2]);
                    }
                    
                    if (!messages[3].equals(" ")) {                                     // Gyro Angle
                        SmartDashboard.putString("Gyro Angle: ", messages[3]);
                    }
                    
                    if (!messages[4].equals(" ")) {                                     // errors
                        SmartDashboard.putString("Error: ", messages[4]);
                    }
                    
                    if (!messages[5].equals(" ")) {       
                        SmartDashboard.putString("Error: ", messages[5]);
                    }
                    
                    if (!messages[6].equals(" ")) {      
                        SmartDashboard.putString("Error: ", messages[6]);
                    }
                    
                    if (!messages[7].equals(" ")) {      
                        SmartDashboard.putString("Error: ", messages[7]);
                    }
                    
                    if (!messages[8].equals(" ")) {      
                        SmartDashboard.putString("Error: ", messages[8]);
                    }
                    
                    if (!messages[8].equals(" ")) {      
                        SmartDashboard.putString(": ", messages[9]);
                    }
                    
                    if (!messages[8].equals(" ")) {      
                        SmartDashboard.putString(" ", messages[10]);
                    }
                }

                if ("update".equals(msg) && NewStuff) {
                    System.out.println("RobotInfo: updated");
                    for (int i = 0; i < messages.length; i++) { // reset messages
                        messages[i] = " ";
                    }
                } else {
                    //System.out.println(s);
                }

                time = newtime;
            }
        
    }        */

    /***************************************************************************
     *               Raspberry Pi Protocol Information
     * 
     * The Raspberry Pi sends information about direction (height and 
     * angle) the goals are in, distance to the goals, and confidence 
     * level (how sure it is that the blobs it found are actual goals) 
     * This is sent in a long integer:
     * 
     * (example): 67455423497
     * 
     * The 11-digit number is split up as such:
     * 
     *      check numbers (to make sure the integer sent is valid)
     *            |          |          |
     *     67    4    55    4    23    4    97
     *      |          |          |          |
     *  angle(x)  height(y)   distance(z)    confidence level(c)
     * 
     * All values range from 0 to 99 - that is, these values use 
     * arbitrary units.
     **************************************************************************/
    public class PISocket {

        boolean active;
        SocketConnection psock = null;
        Integer rcnum;
        public PISocket(boolean activated) throws Exception {
            active = activated;
            psock = (SocketConnection) Connector.open("socket://127.0.0.1:3243");
        }   
        public void GetData() throws Exception {
            InputStream is = psock.openInputStream();
            rcnum = new Integer(is.read()); //Converting int to Integer object
           
            String strNumber  = rcnum.toString();  //Converting Integer value into a string value
            
            
             //Need to make sure that check numbers are correct
            if(strNumber.charAt(2) == '4' &&  strNumber.charAt(5) == '4' && strNumber.charAt(8) == '4')//DANIEL, ARE YOU SURE OUR CHECK NUMBERS WILL ONLY BE 4???
            {
                String angleX = strNumber.substring(0, 2);//   assinging the first two digits into the varible angleX = 67 in this case.it starts a the 0th possition and goes to the number before 2nd possition
                int angleXIntVal = Integer.parseInt(angleX);//converts the angleX srting variable type to int.
                    
                
                String heightY = strNumber.substring(3, 5);//   assiging the first two digits into the varible height=55 in this case .it starts a the 0th possition and goes to the number before 2nd possition
                int heightYIntVal = Integer.parseInt(heightY);//converts the heightY srting variable type to int.
                
                String distanceZ = strNumber.substring(6, 8);//   assiging the first two digits into the varible distanceZ = 23 in this case .it starts a the 0th possition and goes to the number before 2nd possition
                int distanceZIntVal = Integer.parseInt(distanceZ);//converts the distanceZ srting variable type to int.
                
                String confidenceLevelC = strNumber.substring(9, 11);//   assiging the first two digits into the varible confidenceLevel = 95 in this case .it starts a the 0th possition and goes to the number before 2nd possition
                int confidenceLevelCIntVal = Integer.parseInt(confidenceLevelC);//converts the ConfidenceLevelC srting variable type to int.
            }
            else
            {
                SmartDashboard.putString("LOLwut?:", "Check numbers are wrong do something here....");
                    //if it turns out not being genuine
            }
        }
        
    }
}
//Figure out how to take a long number such as 67455423497 and seperate it into many individual numbers according to the layout shown above
