
import java.util.*;

public class Duke {
    public static final String line = "    ____________________________________________________________\n";
    public static final ArrayList<Task> mylist = new ArrayList<Task>();

    public static void main(String[] args) {

        StartMessage();
        Scanner in = new Scanner(System.in);
        while(true){
            String input = in.nextLine();
            if (input.equals("bye")) {
                //print bye message
                ByeMessage();
                in.close();
                break;
            }else if (input.equals("list")){
                //print out current list
                PrintList();
            }
            else if (input.startsWith("done")){
                String[] arr = input.split(" ", 2);
                int numdone = Integer.parseInt(arr[1]) - 1;
                mylist.get(numdone).SetDone();
                PrintDoneMessage(numdone);
            }
            else{
                //add task to list
                AddInput(input);
            }
        }

    }

    private static void PrintDoneMessage(int numdone){
        System.out.print(line + "     Nice! I've marked this task as done: \n");
        System.out.print("       " + mylist.get(numdone).GiveTask() + "\n");
        System.out.print(line);
    }

    private static void PrintList(){
        int listsize = mylist.size();
        System.out.print(line + "     Here are the tasks in your list:\n");
        for (int i = 0; i < listsize; i++){
            int listnum = i+1;
            System.out.print("     " + listnum + ". " + mylist.get(i).GiveTask() + "\n");
        }
        System.out.print(line);
    }

    private static void AddInput(String input){
        Task tasktoadd = new Task(input);
        mylist.add(tasktoadd);
        AddedTaskMessage(input);
    }

    private static void AddedTaskMessage(String input){
        System.out.print(line + "     added: " + input);
        System.out.println();
        System.out.print(line);

    }

    private static void StartMessage(){
        String logo = line
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + line
                + "\n";
        System.out.println(logo);
    }

    private static void ByeMessage(){
        String output = line + "     Bye. Hope to see you again soon!\n" + line;
        System.out.println(output);
    }
}
