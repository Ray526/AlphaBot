package frc.FSLib2025.state_machine;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import frc.robot.Constants.WaltsonConstants;

public enum WaltsonState implements Sendable {
    DEFAULT(WaltsonConstants.WALTSON_DEFAULT_ANGLE, WaltsonConstants.ROLLER_EMPTY_SPEED),
    INTAKE(WaltsonConstants.WALTSON_INTAKE_ANGLE, WaltsonConstants.ROLLER_INTAKING_SPEED),
    PROCESSOR(WaltsonConstants.WALTSON_PROCESSOR_ANGLE, WaltsonConstants.ROLLER_PROCESSOR_SPEED),
    CLIMB_READY(WaltsonConstants.WALTSON_CLIMB_READY_ANGLE, WaltsonConstants.ROLLER_EMPTY_SPEED),
    CLIMB(WaltsonConstants.WALTSON_CLIMB_ANGLE, WaltsonConstants.ROLLER_EMPTY_SPEED);

    private final Rotation2d Angle;
    private final double speed;

    private WaltsonState(Rotation2d Angle, double speed) {
        this.Angle = Angle;
        this.speed = speed;
    }

    public Rotation2d getWaltsonAngle() {
        return Angle;
    }

    public double getRollerSpeed() {
        return speed;
    }

    public double getAngleValue() {
        return Angle.getRotations();
    }

    public String getStateName() {
        return this.name();
    }

    @Override
    public String toString() {
        return String.format("waltson state: %s, angel: %.2f degrees, speed: %d rpm", this.name(), Angle.getDegrees(), getRollerSpeed());
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.setSmartDashboardType("waltson");
        builder.addStringProperty("state", this::getStateName, null);
        builder.addDoubleProperty("angle", this::getAngleValue, null);
    }
    
    public WaltsonState getWaltsonState() {
        return this;
    }
}
