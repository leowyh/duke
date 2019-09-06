import java.io.*;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

public class Duke{
    private static Ui ui;
    private static TaskList tasklist;
    private static Storage storage;

    private Duke(String filepath){
        ui = new Ui();
        /*
    private static final String line = "    ____________________________________________________________\n";
    private static ArrayList<Task> mylist = new ArrayList<Task>();
    private static SimpleDateFormat dataformat = new SimpleDateFormat("dd/MM/yyyy HHmm");
*/
        storage = new Storage(filepath);
        ArrayList<Task> arraylist = storage.load();
        tasklist = new TaskList(arraylist);
    }

    private void run(){
        ui.StartMessage();

        boolean isExit = false;
        while(!isExit){
            String input = ui.readinput();
            isExit = Parser.parse(input, tasklist, ui, storage);
        }
    }

    public static void main(String[] args) throws DukeException {

        new Duke("data/duke.txt").run();
        /*
        LoadArray();

        StartMessage();

        Scanner in = new Scanner(System.in);

        while(true){
            String input = in.nextLine();
            try {
                if (input.equals("bye")) {
                    //print bye message
                    ByeMessage();
                    in.close();
                    break;
                } else if (input.equals("list")) {
                    //print out current list
                    PrintList(mylist, "list");
                } else if (input.startsWith("done")) {

                    String[] arr = input.split(" ", 2);
                    int numdone = Integer.parseInt(arr[1]) - 1;
                    mylist.get(numdone).SetDone();
                    PrintDoneMessage(numdone);

                } else if (input.startsWith("deadline")) {
                    ProcessTask(input);
                    SaveArray();

                } else if (input.startsWith("todo")) {
                    AddInput(input);
                    SaveArray();

                } else if (input.startsWith("event")) {
                    ProcessTask(input);
                    SaveArray();

                } else if (input.startsWith("delete")) {
                    String[] arr = input.split(" ", 2);
                    int numdelete = Integer.parseInt(arr[1]) - 1;
                    String task = mylist.get(numdelete).GiveTask();
                    mylist.remove(numdelete);
                    PrintDeleteMessage(numdelete, task, mylist.size());
                    SaveArray();

                } else if (input.startsWith("find")) {
                    ProcessTask(input);


                } else {
                    throw new DukeException("     \u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");

                }

            }
            catch (DukeException e){
                System.out.print(line);
                System.out.println(e.getMessage());
                System.out.print(line);
            }
        }
*/

    }

/*

    private static void SaveArray(){
        try {
            FileOutputStream file = new FileOutputStream("data/duke.txt");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(mylist);
            out.close();
            file.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private static void LoadArray(){
        try
        {
            FileInputStream file = new FileInputStream("data/duke.txt");
            ObjectInputStream out = new ObjectInputStream(file);

            mylist = (ArrayList) out.readObject();

            out.close();
            file.close();
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
    }

    private static void ProcessTask(String input){
        //deadline
        if (input.startsWith("deadline")){
            try {
                String[] splitspace = input.split(" ", 2);
                String[] splitslash = splitspace[1].split("/", 2);
                String taskDescription = splitslash[0];
                String[] splittime = splitslash[1].split(" ", 2);
                String taskTime = splittime[1];
                Date formattedtime = dataformat.parse(taskTime);
                Deadline deadline = new Deadline(taskDescription, dataformat.format(formattedtime));
                mylist.add(deadline);
                PrintAddedMessage(deadline);
            }
            catch (ArrayIndexOutOfBoundsException e){
                System.out.print(line);
                System.out.println("     \u2639 OOPS!!! The description of a deadline cannot be empty.");
                System.out.print(line);
            }
            catch (ParseException e){
                System.out.print(line);
                System.out.println("     \u2639 OOPS!!! Format of time is wrong.");
                System.out.print(line);
            }
        }
        //event
        else if((input.startsWith("event"))){
            try {
                String[] splitspace = input.split(" ", 2);
                String[] splitslash = splitspace[1].split("/", 2);
                String taskDescription = splitslash[0];
                String[] splittime = splitslash[1].split(" ", 2);
                String taskTime = splittime[1];
                Date formattedtime = dataformat.parse(taskTime);
                Event event = new Event(taskDescription, dataformat.format(formattedtime));
                mylist.add(event);
                PrintAddedMessage(event);
            }
            catch(ArrayIndexOutOfBoundsException e) {
                System.out.print(line);
                System.out.println("     \u2639 OOPS!!! The description of a event cannot be empty.");
                System.out.print(line);
            }
            catch (ParseException e){
                System.out.print(line);
                System.out.println("     \u2639 OOPS!!! Format of time is wrong.");
                System.out.print(line);
            }
        }
        //find
        else{
            try{
                ArrayList<Task> findlist = new ArrayList<Task>();
                String[] splitspace = input.split(" ", 2);
                for (Task tasks : mylist){
                    if(tasks.description.contains(splitspace[1])){
                        findlist.add(tasks);
                    }
                }
                PrintList(findlist, "find");
            }
            catch(ArrayIndexOutOfBoundsException e) {
                System.out.print(line);
                System.out.println("     \u2639 OOPS!!! The content to find cannot be empty.");
                System.out.print(line);
            }
        }



    }

    private static void PrintAddedMessage(Task task){
        System.out.print(line + "     Got it. I've added this task:  \n");
        System.out.print("       " + task.GiveTask() + "\n");
        int tasksize = mylist.size();
        System.out.print("     Now you have " + tasksize + " tasks in the list." + "\n");
        System.out.print(line);
    }

    private static void PrintDeleteMessage(int numdelete, String task, int tasksize){
        System.out.print(line + "     Noted. I've removed this task: \n");
        System.out.print("       " + task + "\n");
        System.out.print("     Now you have " + tasksize + " tasks in the list." + "\n");
        System.out.print(line);
    }

    private static void PrintDoneMessage(int numdone){
        System.out.print(line + "     Nice! I've marked this task as done: \n");
        System.out.print("       " + mylist.get(numdone).GiveTask() + "\n");
        System.out.print(line);
    }

    private static void PrintList(ArrayList<Task> list, String command){
        int listsize = list.size();
        if(command.equals("list")){
            System.out.print(line + "     Here are the tasks in your list:\n");
        }else{
            System.out.print(line + "     Here are the matching tasks in your list:\n");
        }
        for (int i = 0; i < listsize; i++){
            int listnum = i+1;
            System.out.print("     " + listnum + "." + list.get(i).GiveTask() + "\n");
        }
        System.out.print(line);
    }

    private static void AddInput(String input){
        try {
            String[] splitspace = input.split(" ", 2);
            Todo todotoadd = new Todo(splitspace[1]);
            mylist.add(todotoadd);
            PrintAddedMessage(todotoadd);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.print(line);
            System.out.println("     \u2639 OOPS!!! The description of a todo cannot be empty.");
            System.out.print(line);
        }
    }

    private static void StartMessage(){
        String logo = line
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + line;
        System.out.print(logo);
    }

    private static void ByeMessage(){
        String output = line + "     Bye. Hope to see you again soon!\n" + line;
        System.out.println(output);
    }

 */
}
