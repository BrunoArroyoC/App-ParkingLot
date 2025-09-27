package company;

import client.MenuMachine;
import machineParking.Configuration;

public class Main {
    public static void main(String[] args) {

        Configuration c1 = new Configuration();

        MenuMachine m1 = new MenuMachine(c1);
        m1.showOptionMachineEntry();

    }

}

