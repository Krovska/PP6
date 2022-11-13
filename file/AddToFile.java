package file;
import java.io.*;

public class AddToFile {

    private BufferedWriter writer;

    public AddToFile(String fileName){
        try{
            writer = new BufferedWriter( new FileWriter("D://"+fileName+".txt"));
        }catch (Exception ex){
            System.out.println("Щось пішло не так із відкриттям файлу");
        }
    }

    public void closeWriter(){
        try {
            writer.close();
        }catch (Exception ex){
            System.out.println("Щось пішло не так із закриттям файлу");
        }
    }

    public void addToFile(String strToWrite){
        try {
            writer.write(strToWrite);
        } catch (Exception e) {
            System.out.println("Щось пішло не так із записом у файл");
        }
    }
}
