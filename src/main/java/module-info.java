module com.philip.cmu.advanceprogcasestudy {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.philip.cmu.chapter1 to javafx.fxml;
    exports com.philip.cmu.chapter1;
}