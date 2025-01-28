package frc.FSLib2025.state_machine;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import frc.robot.Constants.SuperstructureConstants;

public enum GrabberState implements Sendable {
    EMPTY(SuperstructureConstants.GRABBER_UP_EMPTY_SPEED, SuperstructureConstants.GRABBER_LOW_EMPTY_SPEED),
    INTAKING(SuperstructureConstants.GRABBER_UP_INTAKING_SPEED,  SuperstructureConstants.GRABBER_LOW_INTAKING_SPEED),
    HOLD(SuperstructureConstants.GRABBER_UP_INTAKING_SPEED, SuperstructureConstants.GRABBER_LOW_HOLD_SPEED),
    PALCE(SuperstructureConstants.GRABBER_UP_PLACE_SPEED,  SuperstructureConstants.GRABBER_LOW_PLACE_SPEED);

    private final double grabberUPspeed;
    private final double grabberLOWspeed;

    private GrabberState(double grabberUPspeed, double grabberLOWspeed) {
        this.grabberUPspeed = grabberUPspeed;
        this.grabberLOWspeed = grabberLOWspeed;
    }

    public double getGrabberUPspeed() {
        return grabberUPspeed;
    }

    public double getGrabberLOWspeed() {
        return grabberLOWspeed;
    }

    public String getStateName() {
        return this.name();
    }

    @Override
    public String toString() {
        return String.format("grabber state: %s, UP speed: %.2f, LOW speed: %.2f", this.name(), grabberUPspeed, grabberLOWspeed);
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.setSmartDashboardType("grabber");
        builder.addStringProperty("state", this::getStateName, null);
        builder.addDoubleProperty("UP speed", this::getGrabberUPspeed, null);
        builder.addDoubleProperty("LOW speed", this::getGrabberLOWspeed, null);
    }
}