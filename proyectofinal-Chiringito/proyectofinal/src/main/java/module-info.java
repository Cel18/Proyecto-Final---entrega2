module co.edu.uniquindio.proyectofinal {
    requires javafx.controls;
    requires javafx.fxml;
    
    opens co.edu.uniquindio.proyectofinal.controllers to javafx.fxml;
    exports co.edu.uniquindio.proyectofinal;
}
