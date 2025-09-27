package company;

import java.util.Date;
import java.util.Objects;
import java.util.Random;

public abstract class TypesVecilhes {
    private String plate;
    Date current;
    private final String idTicket;

    public TypesVecilhes(String plate) {
        this.plate = plate;
        //this.plate = generatedPlate();
        this.current = new Date();
        this.idTicket = generateIdTicket();
    }


    public String generateIdTicket (){
        Random rd = new Random();
        int aux = 10000 + rd.nextInt(90000);
        String id = Integer.toString(aux);
        return id;
    }

    public String getIdTicket(){
        return this.idTicket;
    }



    /*
    public String generatedPlate(){
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            char ch = (char) (Math.random() * 26 + 'A');
            s.append(ch);
        }
        for (int i = 0; i < 4; i++) {
            char digit1 = (char) (Math.random() * 10 + '0');
            s.append(digit1);
        }
        return s.toString();

    }

     */

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TypesVecilhes that = (TypesVecilhes) o;
        return Objects.equals(getPlate(), that.getPlate());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idTicket);
    }

    @Override
    public String toString() {
        return "TypesVecilhes{" +
                "plate='" + plate + '\'' +
                ", stratTime='" + this.current + '\'' +
                '}';
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Date getStratTime() {
        return this.current;
    }

    public void setStratTime(Date current) {
        this.current = current;
    }
}



