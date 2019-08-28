import java.util.*;

public class Duke {
    public static void main(String[] args) {
        String line = "    ____________________________________________________________\n";
        String logo = line
                    + "     Hello! I'm Duke\n"
                    + "     What can I do for you?\n"
                    + line
                    + "\n";
        System.out.println(logo);
        Scanner in = new Scanner(System.in);
        while(true){
            String word = in.next();
            if (word.equals("bye")) {
                String output = line + "     Bye. Hope to see you again soon!\n" + line;
                System.out.println(output);
                in.close();
                break;
            }else{
                String output = line + "     " + word + "\n" + line;
                System.out.println(output);
            }
        }

    }
}
