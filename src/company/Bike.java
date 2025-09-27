package company;

import java.util.Date;

public class Bike extends TypesVecilhes implements ParkingCosts{


    public Bike(String plate) {
        super(plate);
    }

    @Override
    public String generateIdTicket() {
        return super.generateIdTicket();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public String getPlate() {
        return super.getPlate();
    }

    @Override
    public void setPlate(String plate) {
        super.setPlate(plate);
    }

    @Override
    public Date getStratTime() {
        return super.getStratTime();
    }

    @Override
    public void setStratTime(Date current) {
        super.setStratTime(current);
    }

    @Override
    public String getIdTicket() {
        return super.getIdTicket();
    }

    public double getCostPerHour(){
        return COST_PER_HOUR_BIKE;
    }

    @Override
    public String getType() {
        return "BIKE";
    }
}
