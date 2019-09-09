import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Parser {

    private static SimpleDateFormat dataformat = new SimpleDateFormat("dd/MM/yyyy HHmm");

    public static boolean parse(String input, TaskList tasklist, Ui ui, Storage storage){
        try {
            if (IsBye(input)) {
                //print bye message
                ui.ByeMessage();
                ui.in.close();
                return true;

            } else if (IsList(input)) {
                //print out current list
                ui.PrintList(tasklist, "list");

            } else if (IsDone(input)) {
                ProcessDone(input, tasklist, ui);

            } else if (IsDeadline(input)) {
                ProcessDeadline(input, tasklist, ui);
                storage.save(tasklist.ReturnArrayList());

            } else if (IsTodo(input)) {
                ProcessTodo(input, tasklist, ui);
                storage.save(tasklist.ReturnArrayList());

            } else if (IsEvent(input)) {
                ProcessEvent(input, tasklist, ui);
                storage.save(tasklist.ReturnArrayList());

            } else if (IsDelete(input)) {
                ProcessDelete(input, tasklist, ui);
                storage.save(tasklist.ReturnArrayList());

            } else if (IsFind(input)) {
                ProcessFind(input, tasklist, ui);

            } else {
                throw new DukeException("     \u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");

            }

        }
        catch (DukeException e){
            ui.ExceptionMessage(e.getMessage());
            return true;
        }

        return false;
    }

    private static void ProcessFind(String input, TaskList tasklist, Ui ui){
        try{
            TaskList findlist = new TaskList();
            String[] splitspace = input.split(" ", 2);
            for (Task tasks : tasklist.ReturnArrayList()){
                if(tasks.description.contains(splitspace[1])){
                    findlist.AddTask(tasks);
                }
            }
            ui.PrintList(findlist, "find");
        }
        catch(ArrayIndexOutOfBoundsException e) {
            ui.ExceptionMessage("     \u2639 OOPS!!! The content to find cannot be empty.");
        }
    }

    private static void ProcessDelete(String input, TaskList tasklist, Ui ui){
        try{
            String[] arr = input.split(" ", 2);
            int numdelete = Integer.parseInt(arr[1]) - 1;
            String task = tasklist.get(numdelete).GiveTask();
            tasklist.DeleteTask(numdelete);
            ui.PrintDeleteMessage(numdelete, task, tasklist);

        }catch(ArrayIndexOutOfBoundsException e){
            ui.ExceptionMessage("     \u2639 OOPS!!! Please input the list number to delete.");
        }
    }

    private static void ProcessDone(String input, TaskList tasklist, Ui ui){
        try{
            String[] arr = input.split(" ", 2);
            int numdone = Integer.parseInt(arr[1]) - 1;
            tasklist.get(numdone).SetDone();
            ui.PrintDoneMessage(numdone, tasklist);

        }catch(ArrayIndexOutOfBoundsException e){
            ui.ExceptionMessage("     \u2639 OOPS!!! Please input the list number to indicate as done.");
        }
    }

    private static void ProcessDeadline(String input, TaskList tasklist, Ui ui){
        try {
            String[] splitspace = input.split(" ", 2);
            String[] splitslash = splitspace[1].split("/", 2);
            String taskDescription = splitslash[0];
            String[] splittime = splitslash[1].split(" ", 2);
            String taskTime = splittime[1];
            Date formattedtime = dataformat.parse(taskTime);
            Deadline deadline = new Deadline(taskDescription, dataformat.format(formattedtime));
            tasklist.AddTask(deadline);
            ui.PrintAddedMessage(deadline, tasklist);
        }
        catch (ArrayIndexOutOfBoundsException e){
            ui.ExceptionMessage("     \u2639 OOPS!!! The description of a deadline cannot be empty.");
        }
        catch (ParseException e){
            ui.ExceptionMessage("     \u2639 OOPS!!! Format of time is wrong.");
        }
    }

    private static void ProcessTodo(String input, TaskList tasklist, Ui ui){
        try {
            String[] splitspace = input.split(" ", 2);
            Todo todotoadd = new Todo(splitspace[1]);
            tasklist.AddTask(todotoadd);
            ui.PrintAddedMessage(todotoadd, tasklist);
        }
        catch (ArrayIndexOutOfBoundsException e) {
            ui.ExceptionMessage("     \u2639 OOPS!!! The description of a todo cannot be empty.");
        }
    }

    private static void ProcessEvent(String input, TaskList tasklist, Ui ui){
        try {
            String[] splitspace = input.split(" ", 2);
            String[] splitslash = splitspace[1].split("/", 2);
            String taskDescription = splitslash[0];
            String[] splittime = splitslash[1].split(" ", 2);
            String taskTime = splittime[1];
            Date formattedtime = dataformat.parse(taskTime);
            Event event = new Event(taskDescription, dataformat.format(formattedtime));
            tasklist.AddTask(event);
            ui.PrintAddedMessage(event, tasklist);
        }
        catch(ArrayIndexOutOfBoundsException e) {
            ui.ExceptionMessage("     \u2639 OOPS!!! The description of a event cannot be empty.");
        }
        catch (ParseException e){
            ui.ExceptionMessage("     \u2639 OOPS!!! Format of time is wrong.");
        }
    }

    private static boolean IsBye(String input){
        return input.equals("bye");
    }

    private static boolean IsList(String input){
        return input.equals("list");
    }

    private static boolean IsDone(String input){
        return input.startsWith("done");
    }

    private static boolean IsDeadline(String input){
        return input.startsWith("deadline");
    }

    private static boolean IsTodo(String input){
        return input.startsWith("todo");
    }

    private static boolean IsEvent(String input){
        return input.startsWith("event");
    }

    private static boolean IsDelete(String input){
        return input.startsWith("delete");
    }

    private static boolean IsFind(String input){
        return input.startsWith("find");
    }
}
