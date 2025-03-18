package application;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/UserView.fxml"));
        VBox root = loader.load();
        Scene scene = new Scene(root, 600, 400);

        // 🔗 Lien vers le fichier CSS
        URL cssFile = getClass().getResource("/views/styles.css");
        if (cssFile != null) {
            scene.getStylesheets().add(cssFile.toExternalForm());
        } else {
            System.out.println("⚠️ Fichier styles.css introuvable !");
        }

        primaryStage.setTitle("Gestion des Utilisateurs");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
