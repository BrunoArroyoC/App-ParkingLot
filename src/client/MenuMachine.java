package client;
import company.*;
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

public class MenuMachine implements ParkingCosts{
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
            option = sc.nextInt();
            sc.nextLine();

            switch (option){
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

                    String idAux = extractIdOfTicket(nameTicketOut);
                    String typeVehicle = this.config.obtainKindOdVehicle(idAux);

                    if(typeVehicle.equals("CAR")){
                        double resultTarifCar = operationByHours(currentHours, hours);
                        resultTarifCar *= COST_PER_HOUR_CAR;
                        methodPay(resultTarifCar);
                    } else if (typeVehicle.equals("VAN")) {
                        double resultTarifVan = operationByHours(currentHours,hours);
                        resultTarifVan *= COST_PER_HOUR_VAN;
                        methodPay(resultTarifVan);
                    }else if(typeVehicle.equals("BIKE")){
                        double resultTarifBike = operationByHours(currentHours,hours);
                        resultTarifBike *= COST_PER_HOUR_BIKE;
                        methodPay(resultTarifBike);
                    }else{
                        System.out.println("ERROR");
                        break;
                    }

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

    public String extractIdOfTicket(String nameTicket){
        String id = "";
        Pattern pattern = Pattern.compile("idTicket= [^ ]");
        try(BufferedReader br = new BufferedReader(new FileReader(nameTicket))){
            String line;
            while((line = br.readLine())!= null){
                Matcher m = pattern.matcher(line);
                if(m.find()){
                    id = m.group(1);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return id;
    }


    public String extractTimeOfTicket(String nameTicket){
        String time = "";
        Pattern pattern = Pattern.compile("current= (\\d{2}):(\\d{2})");
        try(BufferedReader br = new BufferedReader((new FileReader(nameTicket)))){
            String lines;
            while((lines = br.readLine()) != null){
                Matcher m = pattern.matcher(lines);
                if(m.find()){
                    time = m.group(1) + ":" + m.group(2);
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

    public double operationByHours(int a, int b){
        double result = b - a;
        return result;
    }

    public void askForPay(double total){
        System.out.println("Your total is: " + total);

    }

    public void methodPay(double resultTarif){
        while(true) {
            System.out.print("Would you like pay with card o cash? (CARD/CASH): ");
            String method = sc.nextLine().toUpperCase().trim();
            if(method.isEmpty()){
                System.out.println("You have to write (CARD/CASH)");
                continue;
            }else if(method.equals("CARD")){
                askInformationCard();
            }else if(method.equals("CASH")){
                System.out.println("You total is: " + resultTarif);
            }else{
                System.out.println("Wrong Option type (CARD/CASH)");
            }
        }
    }

    public void askInformationCard(){
        String name;
        do {
            System.out.print("Type your full name: ");
            name = sc.nextLine();
        }while(name.isEmpty());

        String numberCard;
        do {
            System.out.print("Type your number card. It has to be 16 digits: ");
            numberCard = sc.nextLine();
        } while (numberCard.length()!=16);

        String date;
        do{
            System.out.print("Type the expiration date (mm/yy): ");
            date = sc.nextLine();
        }while(!date.isEmpty());

        System.out.println("Thank you for choose us as you trust parking");

    }








}
