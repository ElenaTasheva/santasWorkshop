package santasWorkshop.core;

import santasWorkshop.common.ExceptionMessages;
import santasWorkshop.models.*;
import santasWorkshop.repositories.DwarfRepository;
import santasWorkshop.repositories.PresentRepository;
import santasWorkshop.repositories.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private Repository<Dwarf> dwarfRepository = new DwarfRepository();
    private Repository<Present> presentRepository = new PresentRepository();
    private Workshop workshop = new WorkshopImpls();
    private int craftedPresents = 0;

    public ControllerImpl() {
    }


    @Override
    public String addDwarf(String type, String dwarfName) {
        Dwarf happy = null;
        if (type.equals(Happy.class.getSimpleName())) {
            happy = new Happy(dwarfName);


        } else if (type.equals(Sleepy.class.getSimpleName())) {
            happy = new Sleepy(dwarfName);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.DWARF_TYPE_DOESNT_EXIST);
        }
        dwarfRepository.add(happy);
        return String.format("Successfully added %s named %s!", type, dwarfName);
    }

    @Override
    public String addInstrumentToDwarf(String dwarfName, int power) {
        if (dwarfRepository.findByName(dwarfName).getName() != null) {
            Instrument instrument = new InstrumentImpl(power);
            dwarfRepository.findByName(dwarfName).addInstrument(instrument);
            return String.format("Successfully added instrument" +
                    " with power %d to dwarf %s!", power, dwarfName);
        }
        else throw new IllegalArgumentException(ExceptionMessages.DWARF_DOESNT_EXIST);
    }

    @Override
    public String addPresent(String presentName, int energyRequired) {
        Present present = new PresentImpl(presentName, energyRequired);
        presentRepository.add(present);
        return String.format("Successfully added Present: %s!", presentName);
    }

    @Override
    public String craftPresent(String presentName) {
        List<Dwarf> suitableDwarfs = new ArrayList<>();
        suitableDwarfs =
                dwarfRepository.getModels().stream().filter(dwarf -> dwarf.getEnergy() > 50)
                .collect(Collectors.toList());
        if (suitableDwarfs.isEmpty()) {
            throw new IllegalArgumentException("There is no dwarf ready to start crafting!");
        } else {
            Dwarf dwarf = suitableDwarfs.get(0);
            Present present = presentRepository.findByName(presentName);
            workshop.craft(present, dwarf);
            int brokenInstruments = (int) dwarf.getInstruments().stream()
                    .filter(Instrument::isBroken).count();
            if (present.isDone()) {
                craftedPresents++;
                return String.format("Present %s " +
                        "is done. %d instrument/s have been broken while working on it!", presentName, brokenInstruments);
            } else {
                return String.format("Present %s " +
                        "is not done. %d instrument/s have been broken while working on it!", presentName, brokenInstruments);
            }
        }
    }


    @Override
    public String report() {
        StringBuilder sb = new StringBuilder();
        sb.append(craftedPresents).append(" presents are done!").append(System.lineSeparator())
                .append("Dwarfs info:").append(System.lineSeparator());
        Collection<Dwarf> dwarfList = dwarfRepository.getModels();
        for (Dwarf dwarf : dwarfList) {
            sb.append(dwarf).append(System.lineSeparator());
        }
        return sb.toString();
    }
}
