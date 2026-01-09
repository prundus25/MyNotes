module com.dspdev {
    requires transitive javafx.controls;
    requires javafx.fxml;

    requires com.fasterxml.jackson.databind;

    opens com.dspdev to javafx.fxml, com.fasterxml.jackson.databind;
    exports com.dspdev;
}