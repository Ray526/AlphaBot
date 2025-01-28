package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.FSLib2025.state_machine.GrabberState;
import frc.robot.Constants.SuperstructureConstants;

public class Grabber extends SubsystemBase {

    private static Grabber instance;
    public static synchronized Grabber getInstance() {
        if (instance == null) {
            instance = new Grabber();
        }
        return instance;
    }

    // motors
    private final SparkMax upGrabberMotor;
    private final SparkMax lowGrabberMotor;

    public Grabber() {
        upGrabberMotor = new SparkMax(SuperstructureConstants.GRABBER_UP_MOTOR_ID, MotorType.kBrushless);
        upGrabberMotor.configure(SuperstructureConstants.GRABBER_UP_MOTOR_CONFIGURATION, ResetMode.kResetSafeParameters,
                PersistMode.kNoPersistParameters);
        lowGrabberMotor = new SparkMax(SuperstructureConstants.GRABBER_LOW_MOTOR_ID, MotorType.kBrushless);
        lowGrabberMotor.configure(SuperstructureConstants.GRABBER_LOW_MOTOR_CONFIGURATION, ResetMode.kResetSafeParameters,
                PersistMode.kNoPersistParameters);
    }

    public void setDesiredState(GrabberState state) {
        upGrabberMotor.set(state.getGrabberUPspeed());
        lowGrabberMotor.set(state.getGrabberLOWspeed());
    }

    @Deprecated
    public void setSpeed(double speed) {
        upGrabberMotor.set(speed);
        lowGrabberMotor.set(speed);
    }


    @Override
    public void periodic() {
        SmartDashboard.putNumber("UP grabber speed", upGrabberMotor.get());
        SmartDashboard.putNumber("UP grabber rpm", upGrabberMotor.getEncoder().getVelocity());
        SmartDashboard.putNumber("LOW grabber speed", lowGrabberMotor.get());
        SmartDashboard.putNumber("LOW grabber rpm", lowGrabberMotor.getEncoder().getVelocity());
        
    }

}
