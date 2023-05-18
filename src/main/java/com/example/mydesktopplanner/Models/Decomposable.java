package com.example.mydesktopplanner.Models;

// This needs some more thinking ..
public interface Decomposable<Argument> {
    public <ReturnType> ReturnType  decomposer(Argument args);

}
