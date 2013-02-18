/* Currently managed by: Levi Raby,Tarun Sunkaraneni, Adam Jessop
 * 
 * 
 * 
 */
package edu.ames.frc.robot;
//Non-explicit imports of io libraries. Once code is finished it should be changed into a set of explicit imports.
import java.io.*;
import javax.microedition.io.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Communication {
    //==================================================================================
    public void RobotSpeed(double Speedo ){
        String RSpeed;
        RSpeed = Double.toString(Speedo);
        if (!RSpeed.equals(" ")) {                  //Puts Robot's **speed**  to Dashboard

            String RS = "Speed:";
            SmartDashboard.putString(RS, RSpeed);
        }
    }
    //==================================================================================
    public void RobotDirection(double RDirect){
        String RD;
         RD = Double.toString(RDirect);
        if(!RD.equals(" ")){                   // puts relative **direction** of robot to SmartDashboard
        String x ="Robot Direction:";
        SmartDashboard.putString(x, RD);
        }
    }
    //==================================================================================
    public void ShooterSpeed(double SS){
        String SSpeed;
        SSpeed= Double.toString(SS);
        if(!SSpeed.equals(" ")){                    //Puts **Shooter Speed** to Dashboard
         SmartDashboard.putString("Shoot Speed:", SSpeed);
       }
    }
       
    //==================================================================================
   //         *****Please be specific when writing strings to errorMethods*****
    public void Error1(String x) {
        if (!x.equals(" ")) {             // Puts **Error** to Dashboard
            SmartDashboard.putString("Error:", x);
        }
    }
      
    //=================================================================================
    public void Error2 (String Er2)
    {                                       //Puts **Error2** to Dashboard
        if(!Er2.equals(" ")){               
            SmartDashboard.putString("Error:",Er2);
        }
    }
    //=================================================================================
    public void Error3(String x) {
        if (!x.equals(" ")) {             // Puts **Error3** to Dashboard
            SmartDashboard.putString("Error:", x);
        }
    }
    //=================================================================================
    public void Error4(String x){
        if (!x.equals(" ")) {             // Puts **Error4** to Dashboard
            SmartDashboard.putString("Error:", x);
        }
    }
    //=================================================================================
    public void Error5(String x){
        if (!x.equals(" ")) {             // Puts **Error5** to Dashboard
            SmartDashboard.putString("Error:", x);
        }
    }
    
    //=================================================================================
    public void Error6(String x){
        if (!x.equals(" ")) {             // Puts **Error6** to Dashboard
            SmartDashboard.putString("Error:", x);
        }
    }
    //=================================================================================
    public void Error7(String x){
        if (!x.equals(" ")) {             // Puts **Error7** to Dashboard
            SmartDashboard.putString("Error:", x);
        }
    }

    //=================================================================================
    
    
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
        int angleInt;
        int heightInt;
        int distanceInt;
        int confInt;
        
        public PISocket(boolean activated) throws Exception {
            active = activated;
            psock = (SocketConnection) Connector.open("socket://127.0.0.1:3243");
            angleInt = 0;
            heightInt = 0;
            distanceInt = 0;
            confInt = 0;
        }   
        
        public void GetData() throws Exception {
            InputStream is = psock.openInputStream();
            rcnum = new Integer(is.read()); //Converting int to Integer object
           String strNumber  = rcnum.toString();  //Converting Integer value into a string value
            
             //Need to make sure that check numbers are correct
            if(strNumber.charAt(2) == '4' &&  strNumber.charAt(5) == '4' && strNumber.charAt(8) == '4') {
                String angleX = strNumber.substring(0, 2);//   assinging the first two digits into the varible angleX = 67 in this case.it starts a the 0th possition and goes to the number before 2nd possition
                angleInt = Integer.parseInt(angleX);//converts the angleX srting variable type to int.
                    
                String heightY = strNumber.substring(3, 5);//   assiging the first two digits into the varible height=55 in this case .it starts a the 0th possition and goes to the number before 2nd possition
                heightInt = Integer.parseInt(heightY);//converts the heightY srting variable type to int.
                
                String distanceZ = strNumber.substring(6, 8);//   assiging the first two digits into the varible distanceZ = 23 in this case .it starts a the 0th possition and goes to the number before 2nd possition
                distanceInt = Integer.parseInt(distanceZ);//converts the distanceZ srting variable type to int.
                
                String confidenceLevelC = strNumber.substring(9, 11);//   assiging the first two digits into the varible confidenceLevel = 95 in this case .it starts a the 0th possition and goes to the number before 2nd possition
                confInt = Integer.parseInt(confidenceLevelC);//converts the ConfidenceLevelC srting variable type to int.
            } else {
                SmartDashboard.putString("LOLwut?:", "Check numbers are wrong do something here....");
                    //if it turns out not being genuine
            }
            /*** this needs to return an array of variables!!! ***/
        }
    }
}
