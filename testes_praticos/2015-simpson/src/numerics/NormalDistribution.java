package numerics;

public class NormalDistribution extends ProbabilityDistribution {
    protected String name;

    public NormalDistribution(double mean, double stddev) throws IllegalArgumentException {
        super(mean, stddev);
    }

    public NormalDistribution(String name, double mean, double stddev) throws IllegalArgumentException {
        super(mean, stddev);
        this.name = name;
    }

    public NormalDistribution() {
        super(0.0, 1.0);
    }

    public String getName() {
        return name;
    }
}
