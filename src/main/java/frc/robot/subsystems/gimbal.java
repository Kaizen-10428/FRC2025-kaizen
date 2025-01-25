package frc.robot.subsystems;

import static edu.wpi.first.units.Units.Joule;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkClosedLoopController;
    import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Configs;

public class gimbal extends SubsystemBase{
    private final SparkMax Gimablmotor;
    private final SparkMax Gimablmotor2;
     public final AbsoluteEncoder encoder;
     public double aenc;

     private final SparkClosedLoopController GimbalPID;
     

     public gimbal(){
        Gimablmotor = new SparkMax(15 ,MotorType.kBrushless);
        Gimablmotor2 = new SparkMax(16, MotorType.kBrushless);
        encoder = Gimablmotor.getAbsoluteEncoder();
        GimbalPID = Gimablmotor.getClosedLoopController();

        Gimablmotor.configure(Configs.GimabalModule.GimbalMotorConfig,null, null);
        Gimablmotor.configure(Configs.GimabalModule.GimbalMotorConfig, null, null);
        Gimablmotor.set(0);
     }
        public void GimbalControl(double joystick){
        aenc = encoder.getPosition();
        aenc = Math.toDegrees(aenc);
        SmartDashboard.putNumber("Gimbal", joystick);
        if (-joystick>0.1){
            if(aenc<25){
                Gimablmotor.set(joystick*0.4);
                Gimablmotor2.set(joystick*0.4);
            }else{
                Gimablmotor.set(0);
            }
        }
        else if(-joystick<-0.1){
            if(aenc>2){
                Gimablmotor.set(joystick*0.4);
            }else{
                Gimablmotor.set(0);
            }
        }
        else {
            Gimablmotor.set(0);
        }
        }
        
    public void GimbalDogree(double deg){
        GimbalPID.setReference(Math.toRadians(deg), ControlType.kPosition);
    }
}
