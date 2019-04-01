package com.awakeee.hodgepodge.designpattern.behavioral.responsibilityChain;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        DispenseChain chain = new Dollar50Dispenser();
        DispenseChain chain1 = new Dollar20Dispenser();
        DispenseChain chain2 = new Dollar10Dispenser();

        chain.setNextChain(chain1);
        chain1.setNextChain(chain2);


        while (true) {
            int amount = 0;
            System.out.println("Enter amount to dispense");
            Scanner input = new Scanner(System.in);
            amount = input.nextInt();
            if (amount % 10 != 0) {
                System.out.println("Amount should be in multiple of 10s.");
                return;
            }
            chain.dispense(new Currency(amount));
        }
    }
}
