package dirOne;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;

public class ViewController {
    private BorderPane root;
    public ViewController() {
        root = new BorderPane();
    }

    public BorderPane getRoot() {
        return root;
    }

    public void addComponentToTop(HBox hbox) {
        root.setTop(hbox);
    }

    public void addComponentToBottom(Node node) {
        root.setBottom(node);
    }

    public void addComponentToLeft(HBox hbox) {
        root.setLeft(hbox);
    }

    public void addComponentToRight(HBox hbox) {
        root.setRight(hbox);
    }

    public void addComponentToCenter(Node node) {
        root.setCenter(node);
    }
}
