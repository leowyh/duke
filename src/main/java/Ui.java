import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private static final String line = "    ____________________________________________________________\n";
    Scanner in;

    public Ui(){
        this.in = new Scanner(System.in);
    }

    public String readinput(){
        return in.nextLine();
    }

    public void StartMessage(){
        String logo = line
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + line;
        System.out.print(logo);
    }

    public void ByeMessage(){
        String output = line + "     Bye. Hope to see you again soon!\n" + line;
        System.out.println(output);
    }

    public void PrintList(TaskList list, String command){
        int listsize = list.size();

        // prints list or matching tasks in list
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

    public void PrintDeleteMessage(int numdone, String task, TaskList tasklist){
        System.out.print(line + "     Noted. I've removed this task: \n");
        System.out.print("       " + task + "\n");
        System.out.print("     Now you have " + tasklist.size() + " tasks in the list." + "\n");
        System.out.print(line);
    }

    public void PrintDoneMessage(int numdone, TaskList tasklist){
        System.out.print(line + "     Nice! I've marked this task as done: \n");
        System.out.print("       " + tasklist.get(numdone).GiveTask() + "\n");
        System.out.print(line);
    }

    public void PrintAddedMessage(Task task, TaskList tasklist){
        System.out.print(line + "     Got it. I've added this task:  \n");
        System.out.print("       " + task.GiveTask() + "\n");
        int tasksize = tasklist.size();
        System.out.print("     Now you have " + tasksize + " tasks in the list." + "\n");
        System.out.print(line);
    }

    public void ExceptionMessage(String message){
        System.out.print(line);
        System.out.println(message);
        System.out.print(line);
    }
}
