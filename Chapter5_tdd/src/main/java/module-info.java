module se233.chapter5_tdd {
    requires javafx.controls;
    requires javafx.fxml;
    requires junit;


    opens se233.chapter5_tdd to javafx.fxml;
    exports se233.chapter5_tdd;
}