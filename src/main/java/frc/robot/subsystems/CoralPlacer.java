package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CoralPlacer extends SubsystemBase{
    private final SparkMax PlacerSparkMax;
    private final SparkMax IntakeSparkMax;


    public CoralPlacer(){
        PlacerSparkMax = new SparkMax(16 ,MotorType.kBrushless);
        IntakeSparkMax = new SparkMax(15,MotorType.kBrushless);
        PlacerSparkMax.set(0);
    }
    public Command Take(double val){
        PlacerSparkMax.set(val);
        IntakeSparkMax.set(val);
                return null;
    }
    
        

    public void TakeOut(Boolean R){
        SmartDashboard.putBoolean("Outake Coral-test Value", R);
        if (R){
            PlacerSparkMax.set(-0.6);
            IntakeSparkMax.set(-0.6);
        }else{
            PlacerSparkMax.set(0);
            IntakeSparkMax.set(0);
        }
}
}
