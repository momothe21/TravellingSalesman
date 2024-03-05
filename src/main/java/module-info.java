module gui.travellingsalesman {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires com.almasb.fxgl.all;

    opens gui.travellingsalesman to javafx.fxml;
    exports gui.travellingsalesman;
}