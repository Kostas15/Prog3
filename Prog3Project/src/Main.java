import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Parse command-line arguments
        int numParticles = 1000; // Number of particles
        int numCycles = 10000; // Number of simulation cycles

        // Create a list of random particles
        List<Particle> particles = new ArrayList<>();
        for (int i = 0; i < numParticles; i++) {
            double x = Math.random() * 800; // Random x coordinate within the display width
            double y = Math.random() * 600; // Random y coordinate within the display height
            double vx = Math.random() * 2 - 1; // Random x component of velocity between -1 and 1
            double vy = Math.random() * 2 - 1; // Random y component of velocity between -1 and 1
            double charge = Math.random() > 0.5 ? 1 : -1; // Randomly assign positive or negative charge
            Particle particle = new Particle(x, y, vx, vy, charge);
            particles.add(particle);
        }

        // Create Simulation object
        Simulation2 simulation = new Simulation2(particles);

        // Create Display object
        Display display = new Display(simulation);

        // Create a JFrame to hold the display
        JFrame frame = new JFrame("Particle Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(display);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Run simulation
        for (int i = 0; i < numCycles; i++) {
            simulation.update();
            simulation.computeForces();
            display.repaint();

            // Add a delay between updates to control the simulation speed
            try {
                Thread.sleep(16); // Approximately 60 frames per second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
