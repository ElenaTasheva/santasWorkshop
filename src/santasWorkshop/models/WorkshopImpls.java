package santasWorkshop.models;

public class WorkshopImpls implements Workshop{


    @Override
    public void craft(Present present, Dwarf dwarf) {
        Instrument instrument = checkInstruments(dwarf);
        start(present, instrument, dwarf);
        while(!present.isDone() && dwarf.canWork() && instrument != null){
            dwarf.work();
            instrument.use();
            present.getCrafted();
            if(instrument.isBroken()){
               instrument = checkInstruments(dwarf);
            }
        }
    }

    private boolean start(Present present, Instrument instrument, Dwarf dwarf) {
      return (dwarf.canWork() && instrument != null);
    }

    private Instrument checkInstruments(Dwarf dwarf) {
      return dwarf.getInstruments()
                       .stream().filter(instrumen1 -> !instrumen1.isBroken()).findFirst().orElse(null);
    }
}
