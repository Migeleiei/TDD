module se233.chapter5_tdd {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;

    opens se233.chapter5_tdd to javafx.fxml;
    exports se233.chapter5_tdd;
}