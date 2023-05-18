import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.List;

public class Display extends JPanel {
    private Simulation2 simulation;
    private int particleRadius; // Radius of each particle

    public Display(Simulation2 simulation) {
        this.simulation = simulation;
        particleRadius = 5;
        setPreferredSize(new Dimension(800, 600));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Set background color
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, getWidth(), getHeight());

        // Draw particles
        List<Particle> particles = simulation.getParticles();
        for (Particle particle : particles) {
            int x = (int) particle.getX();
            int y = (int) particle.getY();

            // Set color based on charge
            if (particle.getCharge() > 0) {
                g.setColor(Color.RED);
            } else {
                g.setColor(Color.BLUE);
            }

            // Draw particle as a filled circle
            g.fillOval(x - particleRadius, y - particleRadius, 2 * particleRadius, 2 * particleRadius);
        }
    }
}
