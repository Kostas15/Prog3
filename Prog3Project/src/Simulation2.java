import java.util.List;

public class Simulation2 {


    private List<Particle> particles;

    public Simulation2(List<Particle> particles) {
        this.particles = particles;
    }

    public void update() {
        int frameWidth = 800; // Width of the display frame
        int frameHeight = 600; // Height of the display frame

        for (Particle particle : particles) {
            // Update particle's position based on its velocity
            double newX = particle.getX() + particle.getVx();
            double newY = particle.getY() + particle.getVy();

            // Check if particle hits the left or right wall
            if (newX < 0 || newX > frameWidth) {
                particle.setVx(-particle.getVx()); // Reverse the x velocity to bounce back
            }

            // Check if particle hits the top or bottom wall
            if (newY < 0 || newY > frameHeight) {
                particle.setVy(-particle.getVy()); // Reverse the y velocity to bounce back
            }

            // Update particle's position
            particle.setX(newX);
            particle.setY(newY);
        }
    }


    public void computeForces() {
        for (int i = 0; i < particles.size(); i++) {
            Particle pi = particles.get(i);
            for (int j = i+1; j < particles.size(); j++) {
                Particle pj = particles.get(j);
                // Compute the distance between pi and pj
                double dx = pj.getX() - pi.getX();
                double dy = pj.getY() - pi.getY();
                double distance = Math.sqrt(dx*dx + dy*dy);

                // Compute the force between pi and pj
                double force = pi.getCharge() * pj.getCharge() / (distance * distance);

                // Update the velocities of pi and pj based on the force
                double fx = force * dx / distance;
                double fy = force * dy / distance;
                pi.setVx(pi.getVx() + fx / pi.getCharge());
                pi.setVy(pi.getVy() + fy / pi.getCharge());
                pj.setVx(pj.getVx() - fx / pj.getCharge());
                pj.setVy(pj.getVy() - fy / pj.getCharge());
            }
        }
    }
    public List<Particle> getParticles() {
        return particles;
    }

    public void setParticles(List<Particle> particles) {
        this.particles = particles;
    }
}
