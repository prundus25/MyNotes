module com.dxp {
    requires transitive javafx.controls;
    requires javafx.fxml;

    requires com.fasterxml.jackson.databind;

    opens com.dxp to javafx.fxml, com.fasterxml.jackson.databind;
    exports com.dxp;
}