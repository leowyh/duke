import java.util.*;

public class Duke {
    public static final String line = "    ____________________________________________________________\n";
    public static final ArrayList<Task> mylist = new ArrayList<Task>();

    public static void main(String[] args) throws DukeException {

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
                    PrintList();
                } else if (input.startsWith("done")) {

                    String[] arr = input.split(" ", 2);
                    int numdone = Integer.parseInt(arr[1]) - 1;
                    mylist.get(numdone).SetDone();
                    PrintDoneMessage(numdone);

                } else if (input.startsWith("deadline")) {
                    ProcessTask(input);

                } else if (input.startsWith("todo")) {
                    AddInput(input);

                } else if (input.startsWith("event")) {
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
                Deadline deadline = new Deadline(taskDescription, taskTime);
                mylist.add(deadline);
                PrintAddedMessage(deadline);
            }
            catch (ArrayIndexOutOfBoundsException e){
                System.out.print(line);
                System.out.println("     \u2639 OOPS!!! The description of a deadline cannot be empty.");
                System.out.print(line);
            }
        }
        //event
        else{
            try {
                String[] splitspace = input.split(" ", 2);
                String[] splitslash = splitspace[1].split("/", 2);
                String taskDescription = splitslash[0];
                String[] splittime = splitslash[1].split(" ", 2);
                String taskTime = splittime[1];
                Event event = new Event(taskDescription, taskTime);
                mylist.add(event);
                PrintAddedMessage(event);
            }
            catch(ArrayIndexOutOfBoundsException e) {
                System.out.print(line);
                System.out.println("     \u2639 OOPS!!! The description of a event cannot be empty.");
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
            System.out.print("     " + listnum + "." + mylist.get(i).GiveTask() + "\n");
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
                + line
                + "\n";
        System.out.println(logo);
    }

    private static void ByeMessage(){
        String output = line + "     Bye. Hope to see you again soon!\n" + line;
        System.out.println(output);
    }
}
