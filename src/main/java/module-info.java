module net.me.gestionconsultationbdcc {
    requires javafx.fxml;
    requires java.sql;
    requires static lombok;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.controls;


    opens net.me.gestionconsultationbdcc.controller to javafx.fxml;
    opens net.me.gestionconsultationbdcc.entities to javafx.base;

    exports net.me.gestionconsultationbdcc;
}