import java.io.Serializable;

public class Todo extends Task implements Serializable {

    public Todo(String description){
        super(description);
    }

    @Override
    public String GiveTask() {
        return "[T]" + super.GiveTask();
    }
}
