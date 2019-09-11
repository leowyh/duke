import java.util.Scanner;

/**
 * Ui to interact with the user.
 */
public class Ui {
    private static final String line = "    ____________________________________________________________\n";
    Scanner in;

    /**
     * Creates a Ui instance with a scanner to read user input.
     */
    public Ui(){
        this.in = new Scanner(System.in);
    }

    /**
     * Reads input from the user.
     * @return Input from the user.
     */
    public String readinput(){
        return in.nextLine();
    }

    /**
     * Prints starting message for the Duke program.
     */
    public void StartMessage(){
        String logo = line
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + line;
        System.out.print(logo);
    }

    /**
     * Prints ending message for the Duke program.
     */
    public void ByeMessage(){
        String output = line + "     Bye. Hope to see you again soon!\n" + line;
        System.out.println(output);
    }

    /**
     * Prints the list of tasks or the matching list of tasks depending on the command.
     * @param list TaskList of the user.
     * @param command Command given by the user.
     */
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

    /**
     * Prints message to indicate deletion of a Task from the TaskList and the number of Tasks left.
     * @param task Representation of the Task that is deleted.
     * @param tasklist TaskList of the user.
     */
    public void PrintDeleteMessage(String task, TaskList tasklist){
        System.out.print(line + "     Noted. I've removed this task: \n");
        System.out.print("       " + task + "\n");
        System.out.print("     Now you have " + tasklist.size() + " tasks in the list." + "\n");
        System.out.print(line);
    }

    /**
     * Prints message to indicate a Task is done.
     * @param numdone Index of the Task in the TaskList.
     * @param tasklist TaskList of the user.
     */
    public void PrintDoneMessage(int numdone, TaskList tasklist){
        System.out.print(line + "     Nice! I've marked this task as done: \n");
        System.out.print("       " + tasklist.get(numdone).GiveTask() + "\n");
        System.out.print(line);
    }

    /**
     * Prints message to indicate a Task being added and the number of Tasks in the TaskList.
     * @param task Task to be added.
     * @param tasklist TaskList of the user.
     */
    public void PrintAddedMessage(Task task, TaskList tasklist){
        System.out.print(line + "     Got it. I've added this task:  \n");
        System.out.print("       " + task.GiveTask() + "\n");
        int tasksize = tasklist.size();
        System.out.print("     Now you have " + tasksize + " tasks in the list." + "\n");
        System.out.print(line);
    }

    /**
     * Prints the message for the exception thrown.
     * @param message Exception message.
     */
    public void ExceptionMessage(String message){
        System.out.print(line);
        System.out.println(message);
        System.out.print(line);
    }
}
