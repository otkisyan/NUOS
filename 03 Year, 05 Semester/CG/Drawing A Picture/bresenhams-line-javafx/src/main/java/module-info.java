module com.computergraphics.lb2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.computergraphics.lb2 to javafx.fxml;
    exports com.computergraphics.lb2;
}