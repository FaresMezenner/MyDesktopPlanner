package com.example.mydesktopplanner.Models;

import com.example.mydesktopplanner.Models.ExceptionsPackage.ExceptionUserDoesNotExist;

import java.io.*;

public class UserManager {


    private static UserManager instance = new UserManager();
    private ObjectInputStream in;


    public static UserManager getInstance() {
        return instance;
    }



    private static final String FOLDER_PATH = "Users/";


    /****
     *
     * cette fonction authetify un utilisateur on utilison son psudo
     * une Exception va etre retuner si l'utilisateur n'exist pas
     *
     * @param pseudo
     * @return Utilisateur
     * @throws ExceptionUserDoesNotExist
     */
    public Utilisateur Authentify(String pseudo) throws ExceptionUserDoesNotExist {

        File file = new File(FOLDER_PATH + pseudo);
        //on test si le fichier de l'utilisateur exist ou non
        if (!file.exists()){
            //s'il n'exist pas
            throw new ExceptionUserDoesNotExist();
        } else {
            //sinon, on va lire les information de l'utilisateur
            try {
                FileInputStream fIn = new FileInputStream(file.getPath());
                in = new ObjectInputStream(fIn);
                Utilisateur utilisateur = (Utilisateur) in.readObject();
                fIn.close();
                in.close();
                return utilisateur;
            } catch (FileNotFoundException e) {
                //s'il n'exist pas
                throw new ExceptionUserDoesNotExist();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }


    /***
     *
     * cette fonction modifie les information d'utilisateur qui exist
     * et cree les information des utilisateur qui n'exist pas
     *
     * @param user
     * @throws ExceptionUserDoesNotExist
     */
    public void setUser(Utilisateur user) throws ExceptionUserDoesNotExist {

        //on fait l'ecriture de les information de l'utilisateur
        //si le fichier de l'utilisateur n'exist pas, il va etre cree
        try {
            FileOutputStream fOut = new FileOutputStream(FOLDER_PATH + user.getPseudo());
            ObjectOutputStream out = new ObjectOutputStream(fOut);
            out.writeObject(user);

            fOut.flush();
            out.flush();
            fOut.close();
            out.close();

        } catch (FileNotFoundException e) {
            throw new ExceptionUserDoesNotExist();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
