package manejoJSON;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class JSONUtiles {

    public static void grabar(JSONArray array) {
        try {
            FileWriter file = new FileWriter("hotel.json");
            file.write(array.toString());
            file.flush();
            file.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }


    public static JSONTokener leer(String archivo) {
        JSONTokener tokener = null;

        try {
            tokener = new JSONTokener(new FileReader("hotel.json"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return tokener;
    }

    public static void grabarObjeto(JSONObject object) {
        try {
            FileWriter file = new FileWriter("hotel.json");
            file.write(object.toString());
            file.flush();
            file.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}