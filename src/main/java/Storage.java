import java.io.*;
import java.util.ArrayList;

public class Storage{
    private static String filepath;

    public Storage(String filepath){
        Storage.filepath = filepath;
    }

    public static ArrayList<Task> load(){
        try
        {


            FileInputStream file = new FileInputStream(filepath);
            ObjectInputStream out = new ObjectInputStream(file);

            ArrayList<Task> arraylist =  (ArrayList) out.readObject();

            out.close();
            file.close();

            return arraylist;
        }
        catch (EOFException e) {
            System.out.println("File is empty");

        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        catch (ClassNotFoundException c)
        {
            System.out.println("Class not found");
            c.printStackTrace();
        }
        return new ArrayList<Task>();
    }

    public static void save(ArrayList<Task> tasklist){
        try {
            //ArrayList<Task> listtosave = tasklist.ReturnArrayList();
            FileOutputStream file = new FileOutputStream(filepath);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(tasklist);
            out.close();
            file.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
