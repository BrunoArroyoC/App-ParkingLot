package client;
import company.Bike;
import company.Car;
import company.TypesVecilhes;
import company.Van;
import machineParking.Configuration;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MenuMachine {
    final Scanner sc;
    Configuration config;

    public MenuMachine (Configuration config){
        this.config = config;
        sc = new Scanner(System.in);
    }


    public void showOptionMachineEntry(){
        int option = 0;
        while(option != 2){

            System.out.println("""
                    1. Entry
                    2. Out
                    \n""");
            System.out.print("Select the desire option: ");
            int op = sc.nextInt();
            sc.nextLine();

            switch (op){
                case 1 -> {
                    String numberPlate = askPlate();
                    String typeVehicles = askType();

                    if(typeVehicles.equals("CAR")){
                        TypesVecilhes car = new Car(numberPlate);
                        this.config.addVehicles(car);
                        String id = car.getIdTicket();
                        getTicket(id);
                    }else if(typeVehicles.equals("VAN")){
                        TypesVecilhes van = new Van(numberPlate);
                        this.config.addVehicles(van);
                        String id = van.getIdTicket();
                        getTicket(id);
                    }else {
                        TypesVecilhes bike = new Bike(numberPlate);
                        this.config.addVehicles(bike);
                        String id = bike.getIdTicket();
                        getTicket(id);
                    }


                }

                case 2 -> {
                    String nameTicketOut = askTicketOut();
                    String entryTime = extractTimeOfTicket(nameTicketOut);
                    int hours = castHours(entryTime);
                    int minutes = castMinutes(entryTime);
                    String curretTime = currentTime();
                    int currentHours = currentHours(curretTime);
                    int currentMinutes = currentMinutes(curretTime);

                }

            }






        }
    }

    public String askPlate(){
        String numberPlate;
        while(true) {

            System.out.print("Entry your number plate: ");
            numberPlate = sc.nextLine();
            if(numberPlate.length() == 5){
                break;
            }else{
                System.out.println("ERROR. The plate has to be exactly 5 characters.");
            }

        }
        return numberPlate;
    }

    public String askType(){
        String type;
        while(true){
            System.out.print("Entry your type of vehicles (CAR/VAN/BIKE): ");
            type = sc.nextLine().toUpperCase().trim();
            if(type.equals("CAR") || type.equals("VAN") || type.equals("BIKE")){
                break;
            }else{
                System.out.println("ERROR. Type de correct Option (CAR/VAN/BIKE)");

            }
        }
        return type;
    }

    public void getTicket(String id){
        this.config.generateStartTicket(id);
    }


    public String extractTimeOfTicket(String nameTicket){
        String time = "";
        Pattern pattern = Pattern.compile("stratTime='([01]?\\d|2[0-3]):[0-5]\\d'");
        try(BufferedReader br = new BufferedReader((new FileReader(nameTicket)))){
            String lines;
            while((lines = br.readLine()) != null){
                Matcher m = pattern.matcher(lines);
                if(m.find()){
                    time = m.group(1);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return time;
    }

    public String askTicketOut(){
        String auxTicketName;
        while(true) {
            System.out.print("Type your ticket name: ");
            auxTicketName = sc.nextLine();
            if(auxTicketName.isEmpty()){
                System.out.println("You have to write your ticket name");
                continue;
            }else {
                break;
            }
        }
        return auxTicketName;
    }

    //12:23

    public int castHours(String time){
        int hours;
        String hoursString = time.substring(0,2);
        hours = Integer.parseInt(hoursString);
        return hours;
    }

    public int castMinutes(String time){
        int minutes;
        String minutesString = time.substring(3);
        minutes = Integer.parseInt(minutesString);
        return minutes;
    }

    public String currentTime(){
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        String exactTime = (String) dateFormat.format(date);
        return exactTime;
    }

    public int currentHours(String exactTime){
        int hoursCurrent;
        String hoursString = exactTime.substring(0,2);
        hoursCurrent = Integer.parseInt(hoursString);
        return hoursCurrent;
    }

    public int currentMinutes(String exactTime){
        int minutesCurrent;
        String minutesString = exactTime.substring(3);
        minutesCurrent = Integer.parseInt(minutesString);
        return minutesCurrent;
    }




}
