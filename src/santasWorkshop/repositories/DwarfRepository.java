package santasWorkshop.repositories;

import santasWorkshop.models.BaseDwarf;
import santasWorkshop.models.Dwarf;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class DwarfRepository implements Repository<Dwarf> {

    private Collection<Dwarf> dwarfs = new LinkedHashSet<>();

    @Override
    public Collection<Dwarf> getModels() {
        return Collections.unmodifiableCollection(dwarfs);
    }

    @Override
    public void add(Dwarf dwarf) {
        dwarfs.add(dwarf);

    }

    @Override
    public boolean remove(Dwarf dwarf) {
        return this.dwarfs.remove(dwarf);
    }

    @Override
    public Dwarf findByName(String name) {
         Dwarf dwarfi;
         dwarfi = dwarfs.stream().filter(dwarf -> dwarf.getName().equals(name)).findFirst().orElse(null);
         return dwarfi;
    }




}
