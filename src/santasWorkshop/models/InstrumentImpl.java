package santasWorkshop.models;

import santasWorkshop.common.ExceptionMessages;

public class InstrumentImpl implements Instrument {

   private int power;

    public InstrumentImpl(int power) {
       setPower(power);
    }



    public void setPower(int power) {
        if(power < 0){
            throw new IllegalArgumentException(ExceptionMessages.INSTRUMENT_POWER_LESS_THAN_ZERO);
        }
        else{
            this.power = power;
        }
    }

    @Override
    public int getPower() {
        return this.power;
    }

    @Override
    public void use() {
       if(!BasicMethods.checkEnergy(this.power)){
           decreased();
       }
       else{
           setPower(0);
       }
    }

    private void decreased() {
        this.power -= 10;
    }



    @Override
    public boolean isBroken() {
        return this.power == 0;
    }
}
