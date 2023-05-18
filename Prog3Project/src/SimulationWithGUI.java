import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationWithGUI extends Simulation {
    private JFrame frame;
    private DrawingPanel drawingPanel;
    private Timer timer;
    private int cyclesCompleted;

    public SimulationWithGUI(int numParticles, int numCycles) {
        super(numParticles, numCycles);
        initializeGUI();
    }

    private void initializeGUI() {
        frame = new JFrame("Charged Particles Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        drawingPanel = new DrawingPanel();
        frame.add(drawingPanel);

        timer = new Timer(100000/20000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cyclesCompleted < numCycles) {

                    updateSimulation();
                    drawingPanel.repaint();
                } else {
                    timer.stop();
                }
            }
        });

        frame.setVisible(true);
    }

    public void startSimulation() {
        long startTime = System.currentTimeMillis();
        timer.start();
        long endTime = System.currentTimeMillis();
        System.out.println("Simulation completed in " + (endTime - startTime) + " milliseconds.");
    }

    private void updateSimulation() {
        runSimulationForNCycles(1);
        cyclesCompleted++;
        System.out.println("Cycles completed:"+cyclesCompleted);
        long endTime = System.currentTimeMillis();
        System.out.println("completed cycle in " + (endTime) + " milliseconds.");
    }

    private class DrawingPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, getWidth(), getHeight());

            for (Particle1 particle1 : particle1) {
                g.setColor(particle1.getCharge() > 0 ? Color.RED : Color.BLUE);
                int x = (int) particle1.getX();
                int y = (int) particle1.getY();
                g.fillOval(x - 2, y - 2, 4, 4);
            }
        }
    }

    public static void main(String[] args) {
        int numParticles = 1000;
        int numCycles = 10000;
        SimulationWithGUI sim = new SimulationWithGUI(numParticles, numCycles);
        sim.startSimulation();
    }
}
