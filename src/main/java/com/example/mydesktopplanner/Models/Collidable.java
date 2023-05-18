package com.example.mydesktopplanner.Models;

import java.time.LocalDateTime;

public interface Collidable<Argument> {
    public boolean isColliding(Argument argument);

    default public boolean timeCollision(LocalDateTime debut , LocalDateTime fin , LocalDateTime date){
        return (date.isAfter(debut) && date.isBefore(fin));
    }
}
