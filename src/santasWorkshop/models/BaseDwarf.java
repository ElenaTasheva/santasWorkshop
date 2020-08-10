package santasWorkshop.models;

import santasWorkshop.common.ExceptionMessages;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BaseDwarf implements Dwarf{
    private String name;
    private int energy;
    private Collection<Instrument> instruments;

    public BaseDwarf(String name, int energy) {
        setName(name);
        setEnergy(energy);
        this.instruments = new ArrayList<>();
    }

    public void setName(String name) {
        if(name == null || name.trim().isEmpty()){
            throw new  NullPointerException(ExceptionMessages.DWARF_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    public void setEnergy(int energy) {
        if(energy <= 0){
            throw new IllegalArgumentException(ExceptionMessages.DWARF_ENERGY_LESS_THAN_ZERO);
        }
        this.energy = energy;
    }

    @Override
    public void work() {
        if(BasicMethods.checkEnergy(this.energy)){
            setEnergy(0);
        }
        else {
            setEnergy(energy - 10);
        }

    }
    private int notBrokenInstrumentsLeft(){
        return (int) instruments.stream().filter(instrument -> !instrument.isBroken()).count();
    }



    @Override
    public void addInstrument(Instrument instrument) {
        this.instruments.add(instrument);

    }

    @Override
    public boolean canWork() {
       return this.energy > 0;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getEnergy() {
        return this.energy;
    }

    @Override
    public Collection<Instrument> getInstruments() {
        return this.instruments;
    }

    @Override
    public String toString() {
       StringBuilder sb = new StringBuilder();
       sb.append("Name: ").append(getName()).append(System.lineSeparator())
               .append("Energy: ").append(getEnergy()).append(System.lineSeparator())
               .append("Instruments ").append(notBrokenInstrumentsLeft()).append(" not broken left");
       return sb.toString();
    }}
