package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.FSLib2025.state_machine.IntakerState;
import frc.robot.Constants.SuperstructureConstants;

public class Intaker extends SubsystemBase {

    private static Intaker instance;
    public static synchronized Intaker getInstance() {
        if (instance == null) {
            instance = new Intaker();
        }
        return instance;
    }

    // motors
    private final SparkMax upIntakerMotor;
    private final SparkMax lowIntakerMotor;

    public Intaker() {
        upIntakerMotor = new SparkMax(SuperstructureConstants.INTAKER_UP_MOTOR_ID, MotorType.kBrushless);
        upIntakerMotor.configure(SuperstructureConstants.INTAKER_UP_MOTOR_CONFIGURATION, ResetMode.kResetSafeParameters,
                PersistMode.kNoPersistParameters);
        lowIntakerMotor = new SparkMax(SuperstructureConstants.INTAKER_LOW_MOTOR_ID, MotorType.kBrushless);
        lowIntakerMotor.configure(SuperstructureConstants.INTAKER_LOW_MOTOR_CONFIGURATION, ResetMode.kResetSafeParameters,
                PersistMode.kNoPersistParameters);
    }

    public void setDesiredState(IntakerState state) {
        upIntakerMotor.set(state.getintakerUPspeed());
        lowIntakerMotor.set(state.getintakerLOWspeed());
    }

    @Deprecated
    public void setSpeed(double speed) {
        upIntakerMotor.set(speed);
        lowIntakerMotor.set(speed);
    }


    @Override
    public void periodic() {
        SmartDashboard.putNumber("UP intaker speed", upIntakerMotor.get());
        SmartDashboard.putNumber("UP intake rpm", upIntakerMotor.getEncoder().getVelocity());
        SmartDashboard.putNumber("LOW intaker speed", lowIntakerMotor.get());
        SmartDashboard.putNumber("LOW intake rpm", lowIntakerMotor.getEncoder().getVelocity());
        
    }

}
