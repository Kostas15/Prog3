public class Particle {
    private double x, y; // position
    private double vx, vy; // velocity
    private double charge; // charge

    public Particle(double x, double y, double vx, double vy, double charge) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.charge = charge;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getVx() {
        return vx;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public double getVy() {
        return vy;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }
}
