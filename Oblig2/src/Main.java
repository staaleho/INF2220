import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by staleh on 28.09.2016.
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("buildhouse1.txt");
        Project newproject = new Project();

        try {
            newproject.newProjectFromFile(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
