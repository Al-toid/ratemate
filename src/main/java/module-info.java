module ratemate {
    requires transitive javafx.controls;
    requires javafx.fxml;

    opens ratemate to javafx.fxml;
    exports ratemate;
}
