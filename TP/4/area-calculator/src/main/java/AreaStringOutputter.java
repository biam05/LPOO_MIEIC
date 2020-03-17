public class AreaStringOutputter {
    private SumProvider sumProvider;

    public AreaStringOutputter(SumProvider sumProviderr) {
        this.sumProvider = sumProviderr;
    }

    public String output() {
        return "Sum of areas: " + sumProvider.sum();
    }
}
