import java.io.Serializable;

/**
 * Task containing information of a deadline.
 */

public class Deadline extends Task implements Serializable {
    protected String by;

    /**
     * Creates a Deadline instance and initialises the required attributes.
     * @param description Description of the deadline.
     * @param by Deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns a string status of the Deadline task.
     * @return The task's status icon, description and deadline.
     */
    @Override
    public String GiveTask() {
        return "[D]" + super.GiveTask() + "(by: " + by + ")";
    }
}
