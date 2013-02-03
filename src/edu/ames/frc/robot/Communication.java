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
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Watchdog;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//ServerSocket Javadoc: http://docs.oracle.com/javase/1.4.2/docs/api/java/net/ServerSocket.html
//http://www.wbrobotics.com/javadoc/javax/microedition/io/SocketConnection.html

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

    public void ConsoleMsg(String msg, int type) {//What is this? Levi explain please.
        messages[type] = msg;
    }

    public void MsgPrint() {
    }

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
            InputStream is = psock.openInputStream();
            rcnum = new Integer(is.read()); //Converting int to Integer object
            is.close();
            psock.close(); 
           
            String strNumber  = rcnum.toString();  //Converting Integer value into a string value
            
            
             //Need to make sure that check numbers are correct
            if(strNumber.charAt(2) == '4' &&  strNumber.charAt(5) == '4' && strNumber.charAt(8) == '4')//DANIEL, ARE YOU SURE OUR CHECK NUMBERS WILL ONLY BE 4???
            {
                String angleX = strNumber.substring(0, 2);//   assiging the first two digits into the varible angleX = 67 in this case.it starts a the 0th possition and goes to the number before 2nd possition
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
                System.out.println("Check numbers are wrong do something here....");//if it turns out not being genuine
            }
            //WE REALLY DON'T NEED THIS!
            /*      long newRcnum;         
                newRcnum= rcnum/100;
                if(rcnum/newRcnum >100 )
                {
                    newRcnum=newRcnum-1;
                }
                long newRcnum2;
                newRcnum2= newRcnum/1000;
                if(newRcnum/newRcnum2 >1000 )
                {
                    newRcnum2=newRcnum2-1;
                }
                long newRcnum3;
                newRcnum3= newRcnum2/1000;
                if(newRcnum2/newRcnum3 >1000 )
                {
                    newRcnum3=newRcnum3-1;
                }
                long newRcnum4;
                newRcnum4= newRcnum3/1000;
                if(newRcnum3/newRcnum4 >1000 )
                {
                    newRcnum4=newRcnum4-1;
                }
             */   // Is this right Kole?
        }
    }
}
//Figure out how to take a long number such as 67455423497 and seperate it into many individual numbers according to the layout shown above
