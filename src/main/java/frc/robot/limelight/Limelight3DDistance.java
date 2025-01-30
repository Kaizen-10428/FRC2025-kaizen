package frc.robot.limelight;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.LimelightHelpers;
import frc.robot.Constants.DriveConstants;
import frc.robot.subsystems.DriveSubsystem;

public class Limelight3DDistance {

    private final NetworkTable limelightTable;

    public Limelight3DDistance() {
        this.limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
    }


    private double[] getTargetCamerSpace() {
        return limelightTable.getEntry("targetpose_cameraspace").getDoubleArray(new double[6]);
    }

    private boolean hasTarget() {
        return LimelightHelpers.getTV("");
    }

    public void updateDistance(DriveSubsystem m_robotDrive) {
        SmartDashboard.putBoolean("Cross Button", true);

        if (hasTarget()) {
            double[] distance = getTargetCamerSpace();               
            SmartDashboard.putNumber("tx", distance[0]);
            SmartDashboard.putNumber("ty", distance[1]);
            SmartDashboard.putNumber("tz", distance[2]);
            SmartDashboard.putNumber("pitch", distance[3]);
            SmartDashboard.putNumber("yaw", distance[4]);
            SmartDashboard.putNumber("roll", distance[5]);
            if (distance[2]>0.3){
                m_robotDrive.drive(-(distance[2]-1)*0.5,distance[0], -(distance[4]*0.007), true);
            }
        }else{
            m_robotDrive.drive(0,0,0, true);

        } 

    }
}
