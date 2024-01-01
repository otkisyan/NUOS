module com.computergraphics.lb3_cg {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.computergraphics.lb3_cg to javafx.fxml;
    exports com.computergraphics.lb3_cg;
}