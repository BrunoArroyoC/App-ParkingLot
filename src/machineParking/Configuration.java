package machineParking;
import company.TypesVecilhes;
import company.Car;
import company.Van;
import company.Bike;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Configuration {
    HashMap<String,TypesVecilhes> parking;

    public Configuration(){
        parking = new HashMap<>();
    }
    /*
    public String getTicketName(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; true; i++){
            sb.append("Ticket_").append(i).append(".txt");
            i++;
            break;
        }
        return sb.toString();
    }

     */

    public void generateStartTicket(String id) {
        TypesVecilhes v = parking.get(id);
        if (v == null) {
            System.err.println("No existe vehículo con id " + id);
            return;
        }
        String fileName = "Ticket_" + id + ".txt"; // <- clave

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, false))) {
            bw.write("=== Ticket ===");
            bw.newLine();
            bw.write(v.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    public void generateStartTicket(String id){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(getTicketName(),false))){
            bw.write("=== Tciket ===");
            bw.newLine();
            bw.write(parking.get(id).toString());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

     */

    public void addVehicles(TypesVecilhes cvb){
        parking.put(cvb.getIdTicket(),cvb);
    }

    public String obtainKindOdVehicle(String id){
       TypesVecilhes ty =  parking.get(id);
       String typeVehicle = ty.getType();
       return typeVehicle;
    }





}
