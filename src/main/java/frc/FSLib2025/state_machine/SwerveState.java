package frc.FSLib2025.state_machine;

import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.util.sendable.SendableBuilder;

public enum SwerveState implements Sendable{
    AUTO,
    START,
    DRIVER_CONTROL,
    PATH_FOLLOWING;

    private SwerveState() {}

    public String getStateName() {
        return this.name();
    }

    @Override
    public String toString() {
        return String.format("swerve state: %s", this.name());
    }

    @Override
    public void initSendable(SendableBuilder builder) {
        builder.setSmartDashboardType("swerve");
        builder.addStringProperty("state", this::getStateName, null);
    }
}
