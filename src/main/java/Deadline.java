import java.io.Serializable;

public class Deadline extends Task implements Serializable {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String GiveTask() {
        return "[D]" + super.GiveTask() + "(by: " + by + ")";
    }
}
