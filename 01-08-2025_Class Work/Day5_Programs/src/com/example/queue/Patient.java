package com.example.queue;

class Patient implements Comparable<Patient> {
    private String name;
    private String condition;
    private int priority; 

    public Patient(String name, String condition, int priority) {
    	super();
        this.name = name;
        this.condition = condition;
        this.priority = priority;
    }
    
    @Override
    public int compareTo(Patient other) {
        return this.priority - other.priority;
    }

    @Override
	public String toString() {
		return "Patient [name=" + name + ", condition=" + condition + ", priority=" + priority + "]";
	}
}
