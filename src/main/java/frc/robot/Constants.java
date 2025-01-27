package frc.robot;

import static edu.wpi.first.units.Units.Meters;

import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.signals.SensorDirectionValue;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.units.measure.Distance;
import frc.FSLib2025.swerve.SwerveModuleConstants;

public class Constants {

    public static final class RobotConstants {
        public static final double PERIODIC_INTERVAL = 0.02; // the periodic ,in seconds
        public static final String CANBUS_NAME = "GTX7130";
        public static final int DRIVE_CONTROLLER_PORT = 0;
        public static final int OPERATOR_BOARD_PORT = 1;
    }

    public static final class SwerveConstants {
        public static final double MAX_MODULE_SPEED = 5;
        public static final double MAX_MODULE_ROTATIONAL_SPEED = 4;
        public static final double WHEEL_BASE = 0.583;

        public static final int PIGEON_ID = 40;

        public static final double STEER_MOTOR_KP = 0.008;
        public static final double STEER_MOTOR_KI = 0.05;
        public static final double STEER_MOTOR_KD = 0.005;
        public static final double STEER_MOTOR_WINDUP = 0.0;
        public static final int STEER_MOTOR_LIMIT = 0;

        public static final double DRIVE_MOTOR_GEAR_RATIO = 6.122449;
        public static final double DRIVE_WHEEL_DIAMETERS = 4 * 0.0254; // meters
        public static final double DRIVE_WHEEL_PERIMETER = Math.PI * DRIVE_WHEEL_DIAMETERS; // meters

        public static final Translation2d[] MODULE_TRANSLATOIN_METERS = new Translation2d[] {
                new Translation2d(WHEEL_BASE / 2.0, WHEEL_BASE / 2.0),
                new Translation2d(WHEEL_BASE / 2.0, -WHEEL_BASE / 2.0),
                new Translation2d(-WHEEL_BASE / 2.0, -WHEEL_BASE / 2.0),
                new Translation2d(-WHEEL_BASE / 2.0, WHEEL_BASE / 2.0)
        };

        public static final SwerveModuleConstants MOD0_CONSTANTS = new SwerveModuleConstants();
        static {
            MOD0_CONSTANTS.DriveMotorId = 1;
            MOD0_CONSTANTS.SteerMotorId = 2;
            MOD0_CONSTANTS.CANcoderId = 0;
            MOD0_CONSTANTS.CANcoderOffset = -0.272461;
        }

        public static final SwerveModuleConstants MOD1_CONSTANTS = new SwerveModuleConstants();
        static {
            MOD1_CONSTANTS.DriveMotorId = 11;
            MOD1_CONSTANTS.SteerMotorId = 12;
            MOD1_CONSTANTS.CANcoderId = 1;
            MOD1_CONSTANTS.CANcoderOffset = -0.123047;
        }

        public static final SwerveModuleConstants MOD2_CONSTANTS = new SwerveModuleConstants();
        static {
            MOD2_CONSTANTS.DriveMotorId = 21;   
            MOD2_CONSTANTS.SteerMotorId = 22;
            MOD2_CONSTANTS.CANcoderId = 2;
            MOD2_CONSTANTS.CANcoderOffset = 0.205322;
        }

        public static final SwerveModuleConstants MOD3_CONSTANTS = new SwerveModuleConstants();
        static {
            MOD3_CONSTANTS.DriveMotorId = 31;
            MOD3_CONSTANTS.SteerMotorId = 32;
            MOD3_CONSTANTS.CANcoderId = 3;
            MOD3_CONSTANTS.CANcoderOffset = -0.119141;
        }
    }

    public static final class SuperstructureConstants {
        // elevator motor constants
        public static final int LEFT_ELEVATOR_MOTOR_ID = 0;
        public static final int RIGHT_ELEVATOR_MOTOR_ID = 0;

        // elevator cancoder constants
        public static final int ELEVATOR_CANCODER_ID = 0;

        // elevator motor configuration
        public static final SparkMaxConfig LEFT_ELEVATOR_MOTOR_CONFIGURATION = new SparkMaxConfig();
        static {
            LEFT_ELEVATOR_MOTOR_CONFIGURATION.inverted(false);
        }

        public static final SparkMaxConfig RIGHT_ELEVATOR_MOTOR_CONFIGURATION = new SparkMaxConfig();
        static {
            RIGHT_ELEVATOR_MOTOR_CONFIGURATION.inverted(false);
        }

        // elevator cancoder configuration
        public static final CANcoderConfiguration ELEVATOR_CANCODER_CONFIGURATION = new CANcoderConfiguration();
        static {
            ELEVATOR_CANCODER_CONFIGURATION.MagnetSensor.AbsoluteSensorDiscontinuityPoint = 0.5;
            ELEVATOR_CANCODER_CONFIGURATION.MagnetSensor.MagnetOffset = 0;
            ELEVATOR_CANCODER_CONFIGURATION.MagnetSensor.SensorDirection = SensorDirectionValue.CounterClockwise_Positive;
        }

        // elevator height constants
        public static final Distance ELEVATOR_MAX_HEIGHT = Meters.of(0);
        public static final Distance ELEVATOR_MIN_HEIGHT = Meters.of(0);

        public static final Distance ELEVATOR_DEFAULT_HEIGHT = Meters.of(0);
        public static final Distance ELEVATOR_L1_HEIGHT = Meters.of(0);
        public static final Distance ELEVATOR_L2_HEIGHT = Meters.of(0);
        public static final Distance ELEVATOR_L3_HEIGHT = Meters.of(0);
        public static final Distance ELEVATOR_L4_HEIGHT = Meters.of(0);
        public static final Distance ELEVATOR_CORAL_STATION_HEIGHT = Meters.of(0);

        // elevator control constants
        public static final double ELEVATOR_MAX_VELOCITY = 0;
        public static final double ELEVATOR_MAX_ACCELERATION = 0;

        // elevator pid constants
        public static final double ELEVATOR_KP = 0;
        public static final double ELEVATOR_KI = 0;
        public static final double ELEVATOR_KD = 0;

        // elevator feedforward constant
        public static final double ELEVATOR_KS = 0;
        public static final double ELEVATOR_KG = 0;
        public static final double ELEVATOR_KV = 0;
        public static final double ELEVATOR_KA = 0;

        // intaker motor constants
        public static final int INTAKER_UP_MOTOR_ID = 0;
        public static final int INTAKER_LOW_MOTOR_ID = 0;

        // intaker motor configuration
        public static final SparkMaxConfig INTAKER_UP_MOTOR_CONFIGURATION = new SparkMaxConfig();
        static {
            INTAKER_UP_MOTOR_CONFIGURATION.inverted(true);
            INTAKER_UP_MOTOR_CONFIGURATION.idleMode(IdleMode.kBrake); // super idol的笑容
        }
        public static final SparkMaxConfig INTAKER_LOW_MOTOR_CONFIGURATION = new SparkMaxConfig();
        static {
            INTAKER_LOW_MOTOR_CONFIGURATION.inverted(false);
            INTAKER_LOW_MOTOR_CONFIGURATION.idleMode(IdleMode.kBrake);
        }

        // intaker speed constants
        public static final double INTAKER_UP_EMPTY_SPEED = 0;
        public static final double INTAKER_UP_INTAKING_SPEED = 0;
        public static final double INTAKER_UP_HOLD_SPEED = 0;
        public static final double INTAKER_UP_PLACE_SPEED = 0;
        public static final double INTAKER_LOW_EMPTY_SPEED = 0;
        public static final double INTAKER_LOW_INTAKING_SPEED = 0;
        public static final double INTAKER_LOW_HOLD_SPEED = 0;
        public static final double INTAKER_LOW_PLACE_SPEED = 0;
    }

    public static final class WaltsonConstants{
        // waltson motor constants
        public static final int LEFT_WALTSON_MOTOR_ID = 0;
        public static final int RIGHT_WALTSON_MOTOR_ID = 0;
        public static final int ROLLER_WALTSON_MOTOR_ID = 0;

        // waltson cancoder contants
        public static final int WALTSON_CANCODER_ID = 0;

        // waltson motor configuration
        public static final SparkMaxConfig LEFT_WALTSON_MOTOR_CONFIGURATION = new SparkMaxConfig();
        static {
            LEFT_WALTSON_MOTOR_CONFIGURATION.inverted(true);
        }
        public static final SparkMaxConfig RIGHT_WALTSON_MOTOR_CONFIGURATION = new SparkMaxConfig();
        static {
            RIGHT_WALTSON_MOTOR_CONFIGURATION.inverted(false);
        }
        public static final SparkMaxConfig ROLLER_MOTOR_CONFIGURATION = new SparkMaxConfig();
        static {
            ROLLER_MOTOR_CONFIGURATION.inverted(false);
            ROLLER_MOTOR_CONFIGURATION.idleMode(IdleMode.kBrake);
        }

        // waltson cancoder configuration
        public static final CANcoderConfiguration WALTSON_CANCODER_CONFIGURATION = new CANcoderConfiguration();
        static {
            WALTSON_CANCODER_CONFIGURATION.MagnetSensor.AbsoluteSensorDiscontinuityPoint = 0.5;
            WALTSON_CANCODER_CONFIGURATION.MagnetSensor.MagnetOffset = 0;
            WALTSON_CANCODER_CONFIGURATION.MagnetSensor.SensorDirection = SensorDirectionValue.Clockwise_Positive;
        }

        // waltson angle constants
        public static final Rotation2d WALTSON_DEFAULT_ANGLE = Rotation2d.fromRotations(0);
        public static final Rotation2d WALTSON_INTAKE_ANGLE = Rotation2d.fromRotations(0);
        public static final Rotation2d WALTSON_PROCESSOR_ANGLE = Rotation2d.fromRotations(0);
        public static final Rotation2d WALTSON_CLIMB_READY_ANGLE = Rotation2d.fromRotations(0);
        public static final Rotation2d WALTSON_CLIMB_ANGLE = Rotation2d.fromRotations(0);

        // waltson pid constants
        public static final double WALTSON_KP = 0;
        public static final double WALTSON_KI = 0;
        public static final double WALTSON_KD = 0;

        // roller speed constants
        public static final double ROLLER_EMPTY_SPEED = 0;
        public static final double ROLLER_INTAKING_SPEED = 0;
        public static final double ROLLER_HOLD_SPEED = 0;
        public static final double ROLLER_PROCESSOR_SPEED = 0;
    }

    public static final class FieldConstants {
        // starting pose
        public static final Pose2d Left = new Pose2d(new Translation2d(7.231, 7.615), Rotation2d.fromDegrees(180));
        public static final Pose2d Mid = new Pose2d(new Translation2d(8.068, 6.178), Rotation2d.fromDegrees(180));
        public static final Pose2d Right = new Pose2d(new Translation2d(8.068, 5.067), Rotation2d.fromDegrees(180));
        // REEF scoring pose
        public static final Pose2d A = new Pose2d(new Translation2d(3.077, 4.190), Rotation2d.fromDegrees(0));
        public static final Pose2d B = new Pose2d(new Translation2d(3.109, 3.847), Rotation2d.fromDegrees(0));
        public static final Pose2d C = new Pose2d(new Translation2d(3.654, 2.885), Rotation2d.fromDegrees(60));
        public static final Pose2d D = new Pose2d(new Translation2d(3.920, 2.731), Rotation2d.fromDegrees(60));
        public static final Pose2d E = new Pose2d(new Translation2d(5.058, 2.713), Rotation2d.fromDegrees(120));
        public static final Pose2d F = new Pose2d(new Translation2d(5.325, 2.880), Rotation2d.fromDegrees(120));
        public static final Pose2d G = new Pose2d(new Translation2d(5.883, 3.851), Rotation2d.fromDegrees(180));
        public static final Pose2d H = new Pose2d(new Translation2d(5.883, 4.190), Rotation2d.fromDegrees(180));
        public static final Pose2d I = new Pose2d(new Translation2d(5.341, 5.341), Rotation2d.fromDegrees(-120));
        public static final Pose2d J = new Pose2d(new Translation2d(5.072, 5.320), Rotation2d.fromDegrees(-120));
        public static final Pose2d K = new Pose2d(new Translation2d(3.887, 5.375), Rotation2d.fromDegrees(-60));
        public static final Pose2d L = new Pose2d(new Translation2d(3.623, 5.215), Rotation2d.fromDegrees(-60));
        // Algae & Coral
        public static final Pose2d LA = new Pose2d(new Translation2d(1.843, 5.848), Rotation2d.fromDegrees(180));
        public static final Pose2d LC = new Pose2d(new Translation2d(1.843, 5.848), Rotation2d.fromDegrees(180));
        public static final Pose2d MA = new Pose2d(new Translation2d(1.843, 4.020), Rotation2d.fromDegrees(180));
        public static final Pose2d MC = new Pose2d(new Translation2d(1.843, 4.020), Rotation2d.fromDegrees(180));
        public static final Pose2d RA = new Pose2d(new Translation2d(1.843, 2.166), Rotation2d.fromDegrees(180));
        public static final Pose2d RC = new Pose2d(new Translation2d(1.843, 2.166), Rotation2d.fromDegrees(180));
        // PROCESSOR
        public static final Pose2d PRO = new Pose2d(new Translation2d(6.347, 0.573), Rotation2d.fromDegrees(-90));
        // Coral Station
        public static final Pose2d CSR = new Pose2d(new Translation2d(1.666, 0.693), Rotation2d.fromDegrees(-126));
        public static final Pose2d CSL = new Pose2d(new Translation2d(1.666, 3.168), Rotation2d.fromDegrees(126));
    }
}

