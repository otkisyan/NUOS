module L {

    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires org.mariadb.jdbc;
    requires io.github.cdimascio.dotenv.java;

    opens gui to javafx.fxml;
    exports gui;
    exports models;
}