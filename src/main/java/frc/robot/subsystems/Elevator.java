package frc.robot.subsystems;

import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.Rotations;

import com.ctre.phoenix6.hardware.CANcoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.math.controller.ElevatorFeedforward;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.FSLib2025.state_machine.ElevatorState;
import frc.robot.Constants.SuperstructureConstants;

public class Elevator extends SubsystemBase {
    
    private static Elevator instance;
    public static synchronized Elevator getInstance() {
        if (instance == null) {
            instance = new Elevator();
        }
        return instance;
    }

    // motors
    private final SparkMax leftElevatorMotor;
    private final SparkMax rightElevatorMotor;

    // cancoder
    private final CANcoder cancoder;

    // pid and feedforward controllers
    private final ProfiledPIDController pid;
    private final ElevatorFeedforward feedforward;

    public Elevator() {
        leftElevatorMotor = new SparkMax(SuperstructureConstants.RIGHT_ELEVATOR_MOTOR_ID, MotorType.kBrushless);
        leftElevatorMotor.configure(SuperstructureConstants.LEFT_ELEVATOR_MOTOR_CONFIGURATION,
                ResetMode.kResetSafeParameters, PersistMode.kNoPersistParameters);

        rightElevatorMotor = new SparkMax(SuperstructureConstants.RIGHT_ELEVATOR_MOTOR_ID, MotorType.kBrushless);
        rightElevatorMotor.configure(SuperstructureConstants.RIGHT_ELEVATOR_MOTOR_CONFIGURATION,
                ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);

        cancoder = new CANcoder(SuperstructureConstants.ELEVATOR_CANCODER_ID);
        cancoder.getConfigurator().apply(SuperstructureConstants.ELEVATOR_CANCODER_CONFIGURATION);

        pid = new ProfiledPIDController(
                SuperstructureConstants.ELEVATOR_KP,
                SuperstructureConstants.ELEVATOR_KI,
                SuperstructureConstants.ELEVATOR_KD,
                new TrapezoidProfile.Constraints(
                        SuperstructureConstants.ELEVATOR_MAX_VELOCITY,
                        SuperstructureConstants.ELEVATOR_MAX_ACCELERATION));

        feedforward = new ElevatorFeedforward(
                SuperstructureConstants.ELEVATOR_KS,
                SuperstructureConstants.ELEVATOR_KG,
                SuperstructureConstants.ELEVATOR_KV,
                SuperstructureConstants.ELEVATOR_KA);
    }

    public void setDesiredState(ElevatorState desiredState) {
        pid.setGoal(desiredState.getHeight().in(Meters));
        double pidOutput = pid.calculate(getElevatorHeight().in(Meters));
        double feedforwardOutput = feedforward.calculate(pid.getSetpoint().velocity);
        setElevatorVoltage(pidOutput + feedforwardOutput);
    }

    private void setElevatorVoltage(double voltage) {
        if (voltage > 0 && getElevatorHeight().gt(SuperstructureConstants.ELEVATOR_MAX_HEIGHT))
            voltage = 0;
        if (voltage < 0 && getElevatorHeight().lt(SuperstructureConstants.ELEVATOR_MIN_HEIGHT))
            voltage = 0;
        leftElevatorMotor.setVoltage(voltage);
        rightElevatorMotor.setVoltage(voltage);
    }

    @Deprecated
    /*
     * function for testing the constants
     * user setElevatorVoltage(double voltage) instead.
     */
    public void setSpeed(double speed) {
        leftElevatorMotor.set(speed);
        rightElevatorMotor.set(speed);
    }

    private Distance getElevatorHeight() {
        return Meters.of(getElevatorMotorPosition().in(Rotations) * 2 * Math.PI);
    }

    private Angle getElevatorMotorPosition() {
        return Rotations
                .of((leftElevatorMotor.getEncoder().getPosition() + rightElevatorMotor.getEncoder().getPosition()) / 2);
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("elevator position", getElevatorHeight().in(Meters));
    }
}
