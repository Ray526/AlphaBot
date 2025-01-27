// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.CANcoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.FSLib2025.state_machine.WaltsonState;
import frc.robot.Constants.WaltsonConstants;

public class Waltson extends SubsystemBase {

  private static Waltson instance;
    public static synchronized Waltson getInstance() {
        if (instance == null) {
            instance = new Waltson();
        }
        return instance;
    }

  // motors
  private final SparkMax leftWaltsonMotor;
  private final SparkMax rightWaltsonMotor;
  private final SparkMax rollerMotor;

  // cancoder
  private final CANcoder cancoder;

  // pid controller
  private final PIDController pid;

  public Waltson() {
    leftWaltsonMotor = new SparkMax(WaltsonConstants.LEFT_WALTSON_MOTOR_ID, MotorType.kBrushless);
    leftWaltsonMotor.configure(WaltsonConstants.LEFT_WALTSON_MOTOR_CONFIGURATION, ResetMode.kResetSafeParameters,
        PersistMode.kNoPersistParameters);
    rightWaltsonMotor = new SparkMax(WaltsonConstants.RIGHT_WALTSON_MOTOR_ID, MotorType.kBrushless);
    rightWaltsonMotor.configure(WaltsonConstants.RIGHT_WALTSON_MOTOR_CONFIGURATION, ResetMode.kResetSafeParameters,
        PersistMode.kNoPersistParameters);
    
    rollerMotor = new SparkMax(WaltsonConstants.ROLLER_WALTSON_MOTOR_ID, MotorType.kBrushless);
    rollerMotor.configure(WaltsonConstants.ROLLER_MOTOR_CONFIGURATION, ResetMode.kResetSafeParameters,
        PersistMode.kNoPersistParameters);

    cancoder = new CANcoder(WaltsonConstants.WALTSON_CANCODER_ID);
    cancoder.getConfigurator().apply(WaltsonConstants.WALTSON_CANCODER_CONFIGURATION);

    pid = new PIDController(
        WaltsonConstants.WALTSON_KP, 
        WaltsonConstants.WALTSON_KI, 
        WaltsonConstants.WALTSON_KD);
  }

  public void setDesiredState(WaltsonState desiredState) {
    double output = pid.calculate(desiredState.getAngleValue() - getWaltsonAngle());
    leftWaltsonMotor.setVoltage(output);
    rightWaltsonMotor.setVoltage(output);

    rollerMotor.set(desiredState.getRollerSpeed());
  }

  @Deprecated
  public void setWaltsonSpeed(double speed) {
    leftWaltsonMotor.set(speed);
    rightWaltsonMotor.set(speed);
  }

  public void setRollerSpeed(double speed) {
    rollerMotor.set(speed);
  }

  public double getWaltsonAngle() {
    return cancoder.getAbsolutePosition().getValueAsDouble();
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("waltson angle (rot)", getWaltsonAngle());
  }
}
