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

    public String getTicketName(){
        StringBuilder sb = new StringBuilder();
        int contador = 0;
        for(int i = 1; i>contador; i++){
            sb.append("Ticket_").append(contador).append(".txt");
            contador++;
            break;
        }
        return sb.toString();
    }



    public void generateStartTicket(String id){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(getTicketName(),true))){
            bw.write("=== Tciket ===");
            bw.newLine();
            bw.write(parking.get(id).toString());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void addVehicles(TypesVecilhes cvb){
        parking.put(cvb.getIdTicket(),cvb);
    }





}
