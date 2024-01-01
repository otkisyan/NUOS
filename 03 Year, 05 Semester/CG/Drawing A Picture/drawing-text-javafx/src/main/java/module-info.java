module com.computergraphics.lb2_cg {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.computergraphics.lb2_cg to javafx.fxml;
    exports com.computergraphics.lb2_cg;
}