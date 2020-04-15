package file;

import java.io.File;

public class FindFile {

    public boolean exists(String fileAbsolutePath) {
        File file = new File(fileAbsolutePath);
        return file.exists();
    }
}
