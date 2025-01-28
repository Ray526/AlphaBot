// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.pathplanner.lib.commands.PathfindingCommand;
import com.pathplanner.lib.pathfinding.Pathfinding;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.FSLib2025.state_machine.StateMachine;
import frc.FSLib2025.state_machine.SwerveStateMachine;
import frc.FSLib2025.util.LocalADStarAK;
import frc.robot.Constants.RobotConstants;

/**
 * The methods in this class are called automatically corresponding to each
 * mode, as described in
 * the TimedRobot documentation. If you change the name of this class or the
 * package after creating
 * this project, you must also update the Main.java file in the project.
 */
public class Robot extends TimedRobot {

  private Command m_autonomousCommand;

  private final RobotContainer robotContainer;

  public Robot() {
    super(RobotConstants.PERIODIC_INTERVAL);
    robotContainer = new RobotContainer();
  }

  @Override
  public void robotInit() {
    Pathfinding.setPathfinder(new LocalADStarAK());
    PathfindingCommand.warmupCommand().schedule();
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
    StateMachine.getInstance().update(robotContainer.operator.getHID());
    SwerveStateMachine.getInstance().update(robotContainer.driver.getHID(), robotContainer.operator.getHID());

    // correct pose estimation with vision measurements
    var visionEst = robotContainer.vision.getEstimatedGlobalPose();
    visionEst.ifPresent(
        est -> {
          // change our trust in the measurement based on the tags we can see
          var estStdDevs = robotContainer.vision.getEstimationStdDevs();

          robotContainer.swerve.addVisionMeasurement(
              est.estimatedPose.toPose2d(), est.timestampSeconds, estStdDevs);
        });
  }

  @Override
  public void autonomousInit() {
    m_autonomousCommand = robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
    // swerve control by driver or operator
    switch (SwerveStateMachine.getInstance().getSwerveState()) {
      case START: // when comp start robot control by driver
        robotContainer.swerve.setDefaultCommand(robotContainer.teleopSwerve);
        break;
      case DRIVER_CONTROL:
        robotContainer.swerve.setDefaultCommand(robotContainer.teleopSwerve);
        break;
      case PATH_FOLLOWING:
        robotContainer.swerve.setDefaultCommand(robotContainer.pathPlanning);
        break;
      default:
        break;
    }
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  @Override
  public void testInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void testPeriodic() {
  }

  @Override
  public void simulationInit() {
  }

  @Override
  public void simulationPeriodic() {
  }
}
