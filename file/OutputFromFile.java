package file;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OutputFromFile {
    private final BufferedReader reader;
    public OutputFromFile(String fileName){
        try {
            String path = "D://";
            reader = new BufferedReader(new FileReader(path +fileName+".txt"));
        } catch (FileNotFoundException e) {
            System.out.println("Щось пішло не так з читанням файлу");
            throw new RuntimeException(e);
        }
    }

    public void outputFromFile(){
        String str;
        try {
            while ((str=reader.readLine()) != null)
                System.out.println(str);
        }catch (Exception ex){
            System.out.println("Щось пішло не так з читанням файлу");
        }
    }

    public void closeWriter(){
        try {
            reader.close();
        }catch (Exception ex){
            System.out.println("Щось пішло не так з читанням файлу");
        }
    }

}
