package santasWorkshop.models;

public class Sleepy extends BaseDwarf {
    private final static int INITIAL_ENERGY = 50;

    public Sleepy(String name) {
        super(name, INITIAL_ENERGY);
    }

    @Override
    public void work() {
        super.work();
        decreasedByAdditionalFive();
    }

    private void decreasedByAdditionalFive() {
        if(this.getEnergy() - 5 > 0){
            this.setEnergy(this.getEnergy() - 5);
        }
        else{
            this.setEnergy(0);
        }
    }
}
