import java.util.ArrayList;
import java.util.Random;

public class Simulation {
    protected  ArrayList<Particle1> particle1;
    protected int numParticles;
    protected  int numCycles;

    public Simulation(int numParticles, int numCycles) {
        this.numParticles = numParticles;
        this.numCycles = numCycles;
        particle1 = new ArrayList<>();

        Random rand = new Random();
        for (int i = 0; i < numParticles; i++) {
            double x = rand.nextDouble() * 800;
            double y = rand.nextDouble() * 600;
            double vx = rand.nextDouble() - 0.5;
            double vy = rand.nextDouble() - 0.5;
            int charge = rand.nextBoolean() ? 1 : -1;
            particle1.add(new Particle1(x, y, vx, vy, charge));
        }
    }
    public void runSimulationForNCycles(int nCycles) {
        double dt = 0.01;
        for (int cycle = 0; cycle < nCycles; cycle++) {

            for (int i = 0; i < numParticles; i++) {
                Particle1 particle111 = particle1.get(i);
                double fx = 0;
                double fy = 0;

                for (int j = 0; j < numParticles; j++) {
                    if (i != j) {
                        Particle1 other = particle1.get(j);
                        double[] force = particle111.calculateForce(other);
                        fx += force[0];
                        fy += force[1];
                    }
                }

                particle111.updateVelocity(fx, fy, dt);
                particle111.updatePosition(dt);
            }
        }
    }

    public void runSimulation() {
        double dt = 0.01;
        for (int cycle = 0; cycle < numCycles; cycle++) {

            for (int i = 0; i < numParticles; i++) {

                Particle1 particle21 = particle1.get(i);
                double fx = 0;
                double fy = 0;
                System.out.println("NCycle:"+cycle);
                for (int j = 0; j < numParticles; j++) {
                    if (i != j) {
                        Particle1 other = particle1.get(j);
                        double[] force = particle21.calculateForce(other);
                        fx += force[0];
                        fy += force[1];
                    }
                }

                particle21.updateVelocity(fx, fy, dt);
                particle21.updatePosition(dt);
            }
        }
    }

    public static void main(String[] args) {
        int numParticles = 1000;
        int numCycles = 10000;
        Simulation sim = new Simulation(numParticles, numCycles);

        long startTime = System.currentTimeMillis();
        sim.runSimulation();
        long endTime = System.currentTimeMillis();
        System.out.println("Simulation completed in " + (endTime - startTime) + " milliseconds.");
    }
}
