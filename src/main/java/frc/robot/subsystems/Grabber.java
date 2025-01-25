package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Grabber extends SubsystemBase{
    private final SparkMax lIntakeMax;
    private final SparkMax RIntakeMax;
    private final SparkMax lOuttakeMax;
    private final SparkMax ROuttakeMax;

    public Grabber(){
        lIntakeMax = new SparkMax(12,MotorType.kBrushless);
        RIntakeMax = new SparkMax( 13,MotorType.kBrushless);
        lOuttakeMax = new SparkMax(15,MotorType.kBrushless);
        ROuttakeMax = new SparkMax(16,MotorType.kBrushless);
        

        lOuttakeMax.set(0);
        ROuttakeMax.set(0);
        lIntakeMax.set(0);
        RIntakeMax.set(0);
    }

    public void TakeIN(Boolean L){
        SmartDashboard.putBoolean("Outake Value", L);
        if (L){
            lIntakeMax.set(0.6);
            RIntakeMax.set(0.6);
        }  
}

    public void Processing(Boolean R){
        SmartDashboard.putBoolean("Outake Value", R);
        if (R){
            lIntakeMax.set(0.6);
            RIntakeMax.set(0.6);
        }   
    }


}
