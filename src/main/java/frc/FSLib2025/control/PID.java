package frc.FSLib2025.control;

public class PID {
    private double lastError = 0, d = 0, i = 0;
    double output;
    private double kP, kI, kD, windup, limit;    

    public PID(double ikP, double ikI, double ikD, double iwindup, double ilimit){
        kP = ikP;
        kI = ikI;
        kD = ikD;
        windup = iwindup;
        limit = ilimit;
    }

    public PID(double ikP, double ikI, double ikD){
        this(ikP, ikI, ikD, 0, 0);
    }

    public double calculate (double currentValue, double target) {
        return calculate(target - currentValue);
    }

    public double calculate (double error){
        i = (Math.abs(error) <= windup) ? i += error : 0;
        double iOut = i * kI;
        iOut = (iOut >= limit) ? limit : iOut;
        iOut = (iOut <= -limit) ? -limit : iOut;
        d = error - lastError;
        lastError = error;
        output = (error * kP) + iOut + (d * kD);
        return output;
    }
}
