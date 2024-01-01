module com.computergraphics.lb1 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.computergraphics.lb1 to javafx.fxml;
    exports com.computergraphics.lb1;
}