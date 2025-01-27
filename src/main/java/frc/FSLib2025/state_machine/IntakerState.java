package frc.FSLib2025.state_machine;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;
import frc.robot.Constants.SuperstructureConstants;

public enum IntakerState implements Sendable {
    EMPTY(SuperstructureConstants.INTAKER_UP_EMPTY_SPEED, SuperstructureConstants.INTAKER_LOW_EMPTY_SPEED),
    INTAKING(SuperstructureConstants.INTAKER_UP_INTAKING_SPEED,  SuperstructureConstants.INTAKER_LOW_INTAKING_SPEED),
    HOLD(SuperstructureConstants.INTAKER_UP_INTAKING_SPEED, SuperstructureConstants.INTAKER_LOW_HOLD_SPEED),
    PALCE(SuperstructureConstants.INTAKER_UP_PLACE_SPEED,  SuperstructureConstants.INTAKER_LOW_PLACE_SPEED);

    private final double intakerUPspeed;
    private final double intakerLOWspeed;

    private IntakerState(double intakerUPspeed, double intakerLOWspeed) {
        this.intakerUPspeed = intakerUPspeed;
        this.intakerLOWspeed = intakerLOWspeed;
    }

    public double getintakerUPspeed() {
        return intakerUPspeed;
    }

    public double getintakerLOWspeed() {
        return intakerLOWspeed;
    }

    public String getStateName() {
        return this.name();
    }

    @Override
    public String toString() {
        return String.format("intaker state: %s, UP speed: %.2f, LOW speed: %.2f", this.name(), intakerUPspeed, intakerLOWspeed);
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.setSmartDashboardType("intaker");
        builder.addStringProperty("state", this::getStateName, null);
        builder.addDoubleProperty("UP speed", this::getintakerUPspeed, null);
        builder.addDoubleProperty("LOW speed", this::getintakerLOWspeed, null);
    }
}