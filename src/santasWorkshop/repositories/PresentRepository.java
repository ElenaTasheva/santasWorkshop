package santasWorkshop.repositories;

import santasWorkshop.models.Present;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;

public class PresentRepository implements Repository<Present> {

    private Collection<Present> presents = new HashSet<>();

    @Override
    public Collection<Present> getModels() {
        return Collections.unmodifiableCollection(presents);
    }

    @Override
    public void add(Present present) {
        presents.add(present);

    }

    @Override
    public boolean remove(Present present) {
        return this.presents.remove(present);
    }

    @Override
    public Present findByName(String name) {
        Present present = null;
        present = presents.stream().filter(present1 -> present1.getName().equals(name))
                .findFirst().orElse(null);
        return present;
    }
}

