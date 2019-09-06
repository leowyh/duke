import java.io.Serializable;
import java.util.ArrayList;

public class TaskList{
    private ArrayList<Task> tasklist;

    public TaskList(ArrayList<Task> tasklist){
        this.tasklist = tasklist;
    }

    public TaskList(){
        this.tasklist = new ArrayList<>();
    }

    public void AddTask(Task task){
        tasklist.add(task);
    }

    public void DeleteTask(int numdelete){
        tasklist.remove(numdelete);
    }

    public Task get(int number){
        return tasklist.get(number);
    }

    public int size() {
        return tasklist.size();
    }

    public ArrayList<Task> ReturnArrayList(){
        return tasklist;
    }
}
