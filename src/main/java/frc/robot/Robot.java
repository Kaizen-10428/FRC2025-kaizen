// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.time.Year;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.RunCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.Elevator;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private final RobotContainer m_robotContainer;

  public Robot() {
    m_robotContainer = new RobotContainer();
    
    // m_robotContainer.elevator.encoder.setPosition(0);
    

  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();

    SmartDashboard.putNumber("Elevator-encoder", m_robotContainer.elevator.encoder.getPosition());
    SmartDashboard.putNumber("Absolute Encoder Gimbal", Math.toDegrees(m_robotContainer.gimbal.encoder.getPosition()));

  }


  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void disabledExit() {}

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void autonomousExit() {}

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    // m_robotContainer.slide.setDefaultCommand(new RunCommand(() -> m_robotContainer.slide.LinearSlider(0),m_robotContainer.slide));
    m_robotContainer.elevator.setDefaultCommand(new RunCommand(()->m_robotContainer.elevator.ElevatorUP(0), m_robotContainer.elevator));
    m_robotContainer.gimbal.setDefaultCommand(new RunCommand(() -> m_robotContainer.gimbal.GimbalControl(0),m_robotContainer.gimbal));
    // m_robotContainer.coral.setDefaultCommand(new RunCommand(()-> m_robotContainer.coral.Take(0.0), m_robotContainer.coral));
    // m_robotContainer.funnel.setDefaultCommand(new RunCommand(() -> m_robotContainer.funnel.coralstart(0), m_robotContainer.funnel));

  }

  @Override
  public void teleopPeriodic() {}

  @Override
  public void teleopExit() {}

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}

  @Override
  public void testExit() {}
}
