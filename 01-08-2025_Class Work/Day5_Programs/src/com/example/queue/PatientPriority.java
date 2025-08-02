package com.example.queue;

import java.util.PriorityQueue;

public class PatientPriority{
    public static void main(String[] args) {
        PriorityQueue<Patient> triageQueue = new PriorityQueue<>();
        triageQueue.add(new Patient("Moulali", "Broken Arm", 2));
        triageQueue.add(new Patient("Sam", "Chest Pain", 1));
        triageQueue.add(new Patient("Mani", "Fever", 3));
        triageQueue.add(new Patient("Hari", "Severe Laceration", 1));
        triageQueue.add(new Patient("Ram", "Flu Symptoms", 3));
        triageQueue.add(new Patient("Sai", "Allergic Reaction", 2));

        
        System.out.println("Processing patients in order of priority:");
        while (!triageQueue.isEmpty()) {
            Patient patientToTreat = triageQueue.poll();
            System.out.println(patientToTreat);
        }
    }
}