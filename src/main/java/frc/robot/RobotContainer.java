// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.PathPlannerAuto;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.PS4Controller.Button;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.CoralPlacer;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Funnel;
import frc.robot.subsystems.LinearSlide;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.elevatorcmd;
import frc.robot.limelight.Limelight3DDistance;
import frc.robot.commands.IntakeCmd;

public class RobotContainer {
  // private final DriveSubsystem m_robotDrive = new DriveSubsystem();
  public final Elevator elevator = new Elevator();
  // public final Grabber grabber = new Grabber();
  XboxController m_driverController = new XboxController(OIConstants.kDriverControllerPort);
  XboxController m_mechController = new XboxController(1);
  public final LinearSlide slide = new LinearSlide();
  //public final gimbal gimbal = new gimbal();
  public final Limelight3DDistance aprilDistance = new Limelight3DDistance();
  public final CoralPlacer coral = new CoralPlacer();
  // private final SendableChooser<Command> autoChooser;
  public final Funnel funnel = new Funnel();
  private final DriveSubsystem m_robotDrive = new DriveSubsystem();

  public RobotContainer(){
    // autoChooser = AutoBuilder.buildAutoChooser("New Auto");
    // SmartDashboard.putData("Auto Chooser", autoChooser);
    // CoralPlacer coral_intake = new CoralPlacer();
    // Elevator elevator_move = new Elevator();
    // NamedCommands.registerCommand("OpenIntake", new IntakeCmd(coral_intake, 0.6));
    // NamedCommands.registerCommand("Elevator Lowest", new elevatorcmd(elevator_move, 0));
    // NamedCommands.registerCommand("Elevator L1", new elevatorcmd(elevator_move, 18));
    // NamedCommands.registerCommand("Elevator L2", new elevatorcmd(elevator_move, 54));
    // NamedCommands.registerCommand("Elevator L3", new elevatorcmd(elevator_move, 108));
    // NamedCommands.registerCommand("Elevator L4", new elevatorcmd(elevator_move, 150));

        
    configureBindings();  
    m_robotDrive.setDefaultCommand(
      new RunCommand(
        () -> m_robotDrive.drive(
          -MathUtil.applyDeadband(m_driverController.getLeftY(),0.06),
          -MathUtil.applyDeadband(m_driverController.getLeftX(),0.06),
          -MathUtil.applyDeadband(m_driverController.getRightX(),0.06),
          false),
          m_robotDrive));


  }
   
  private void configureBindings() {
    new JoystickButton(m_driverController, Button.kR1.value)
    .whileTrue(new RunCommand(
      () -> m_robotDrive.setX(),
      m_robotDrive));
  
 // Elevator stuff
  new Trigger(() -> m_mechController.getLeftTriggerAxis()>0.1)
    .whileTrue(new RunCommand(() -> elevator.ElevatorUP(m_mechController.getLeftTriggerAxis()), elevator));
  
  new Trigger(() -> m_mechController.getRightTriggerAxis()>0.1)
    .whileTrue(new RunCommand(() -> elevator.ElevatorDown(m_mechController.getRightTriggerAxis()), elevator));
  
  new JoystickButton(m_mechController, Button.kTriangle.value) //80 max, // l4 preset
    .onTrue(new RunCommand(
      () -> elevator.degele(78),
      elevator));
  
  new JoystickButton(m_mechController, Button.kCross.value)
  .onTrue(new RunCommand(
    () -> elevator.degele(1),
    elevator));

  new JoystickButton(m_mechController, Button.kCircle.value)
  .onTrue(new RunCommand(
    () -> elevator.degele(46),
    elevator)); 

  new JoystickButton(m_mechController, Button.kSquare.value)
  .onTrue(new RunCommand(
    () -> elevator.degele(20),
    elevator));
  
  new POVButton(m_mechController, 0)
    .whileTrue( new RunCommand(
      () -> coral.Take(-0.4),coral));

  new POVButton(m_mechController, 180)
      .whileTrue( new RunCommand(
        () -> coral.Take(0.4),coral));

  new POVButton(m_mechController, 90)
      .whileTrue( new RunCommand(
        () -> funnel.CoralAcceptor(0.4),funnel));
    
  new POVButton(m_mechController, 270)
        .whileTrue( new RunCommand(
          () -> funnel.CoralAcceptor(-0.4),funnel));
  
  new JoystickButton(m_driverController, Button.kCross.value)         //NEED TO DECIDE BUTTON TO TRIGGER THIS
  .whileTrue(new RunCommand(
    () -> aprilDistance.updateDistance(m_robotDrive),
    m_robotDrive
  ));


        }



  // public Command getAutonomousCommand() {
    
  // }
}
