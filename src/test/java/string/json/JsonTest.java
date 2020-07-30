package string.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;
import org.junit.Test;

public class JsonTest {

    @Test
    public void test() {
        Node node = new Node();
        node.setValue(1);
        node.setChild(node);

        Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(node));
    }

    @Data
    private static class Node {
        private int value;
        private Node child;
    }
}
