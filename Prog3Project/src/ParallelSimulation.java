
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ParallelSimulation extends Simulation {
    private ExecutorService executorService;
    private int numThreads;

    public ParallelSimulation(int numParticles, int numCycles, int numThreads) {
        super(numParticles, numCycles);
        this.numThreads = numThreads;
        executorService = Executors.newFixedThreadPool(numThreads);
    }

    @Override
    public void runSimulationForNCycles(int nCycles) {
        double dt = 0.01;
        for (int cycle = 0; cycle < nCycles; cycle++) {
            for (int i = 0; i < numParticles; i += numThreads) {
                int start = i;
                int end = Math.min(start + numThreads, numParticles);

                executorService.submit(() -> {
                    for (int idx = start; idx < end; idx++) {
                        Particle1 particle11 = particle1.get(idx);
                        double fx = 0;
                        double fy = 0;

                        for (int j = 0; j < numParticles; j++) {
                            if (idx != j) {
                                Particle1 other = particle1.get(j);
                                double[] force = particle11.calculateForce(other);
                                fx += force[0];
                                fy += force[1];
                            }
                        }

                        particle11.updateVelocity(fx, fy, dt);
                        particle11.updatePosition(dt);
                    }
                });
            }

            executorService.shutdown();
            try {
                executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executorService = Executors.newFixedThreadPool(numThreads);
        }
    }

    public static void main(String[] args) {
        int numParticles = 1000;
        int numCycles = 10000;
        int numThreads = Runtime.getRuntime().availableProcessors();
        ParallelSimulation sim = new ParallelSimulation(numParticles, numCycles, numThreads);
        long startTime = System.currentTimeMillis();
        sim.runSimulationForNCycles(numCycles);
        long endTime = System.currentTimeMillis();
        System.out.println("Parallel simulation completed in " + (endTime - startTime) + " milliseconds.");
    }
}
