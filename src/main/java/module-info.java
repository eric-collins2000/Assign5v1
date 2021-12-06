module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.apache.logging.log4j;
    requires org.apache.httpcomponents.httpclient;
    requires org.apache.httpcomponents.httpcore;
    requires org.json;

    opens Main to javafx.fxml;
    exports Main;
    exports Gateways;
    opens Gateways to javafx.fxml;
    exports DocRobsStuff;
    opens DocRobsStuff to javafx.fxml;
    exports DataModels;
    opens DataModels to javafx.fxml;
    exports Session;
    opens Session to javafx.fxml;
}