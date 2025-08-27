package com.example.auth.model;

public enum TaskStatus {
    PENDING("Pending"),
    IN_PROGRESS("In Progress"),
    COMPLETED("Completed"),
    DELAYED("Delayed");

    private final String displayName;

    TaskStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    // Optional: Convert string to enum safely
    public static TaskStatus fromString(String status) {
        for (TaskStatus ts : TaskStatus.values()) {
            if (ts.name().equalsIgnoreCase(status) || ts.displayName.equalsIgnoreCase(status)) {
                return ts;
            }
        }
        throw new IllegalArgumentException("Unknown status: " + status);
    }
}