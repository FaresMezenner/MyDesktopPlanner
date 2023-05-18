package com.example.mydesktopplanner.Models;

import java.time.LocalDateTime;

public interface Collidable<Argument> {
    public boolean isColliding(Argument argument);
    default boolean timeCollision(LocalDateTime startTime, LocalDateTime endTime, LocalDateTime currentTime) {
        // Cette fonction retourne vrai si currentTime est compris entre startTime et endTime
        return currentTime.isAfter(startTime) && currentTime.isBefore(endTime);
    }
}
