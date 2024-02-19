import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializer {
    public static void serialize(Library library) {
        write(library);
        read();
    }

    public static void write(Library library) {
        try (FileOutputStream fOutStream = new FileOutputStream("file.txt");
             ObjectOutputStream oOutStream = new ObjectOutputStream(fOutStream)) {
            oOutStream.writeObject(library);
            oOutStream.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void read() {
        Library serializedLib;
        try (FileInputStream fInStream = new FileInputStream("file.txt");
             ObjectInputStream oInStream = new ObjectInputStream(fInStream)) {
            serializedLib = (Library) oInStream.readObject();
            System.out.println(serializedLib.getAllBooks());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
