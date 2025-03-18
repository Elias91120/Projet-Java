package application;

import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;

public class UserController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField searchField;
    @FXML
    private Button addUserBtn;
    @FXML
    private Button updateUserBtn;
    @FXML
    private Button deleteUserBtn;
    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, Integer> colId;
    @FXML
    private TableColumn<User, String> colName;
    @FXML
    private TableColumn<User, String> colEmail;
    @FXML
    private TableColumn<User, String> createdAtColumn;
    @FXML
    private TableColumn<User, String> updatedAtColumn;
    @FXML
    private Label userCountLabel;

    private UserDAO userDAO;

    public UserController() {
        userDAO = new UserDAO();
    }

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        createdAtColumn.setCellValueFactory(new PropertyValueFactory<>("createdAt"));
        updatedAtColumn.setCellValueFactory(new PropertyValueFactory<>("updatedAt"));

        // ✅ Empêcher la troncature des dates
        createdAtColumn.setCellFactory(tc -> {
            TextFieldTableCell<User, String> cell = new TextFieldTableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            text.textProperty().bind(cell.itemProperty());
            text.wrappingWidthProperty().bind(createdAtColumn.widthProperty().subtract(10));
            return cell;
        });

        updatedAtColumn.setCellFactory(tc -> {
            TextFieldTableCell<User, String> cell = new TextFieldTableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            text.textProperty().bind(cell.itemProperty());
            text.wrappingWidthProperty().bind(updatedAtColumn.widthProperty().subtract(10));
            return cell;
        });

        createdAtColumn.setPrefWidth(200);
        updatedAtColumn.setPrefWidth(200);

        // ✅ Réintégration de la sélection d'utilisateur
        userTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                nameField.setText(newSelection.getName());
                emailField.setText(newSelection.getEmail());
            }
        });

        loadUsers();
    }

    @FXML
    public void addUser() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();

        if (name.isEmpty() || email.isEmpty()) {
            showAlert("Erreur", "Les champs ne peuvent pas être vides !");
            return;
        }

        userDAO.createUser(name, email);
        loadUsers();
        nameField.clear();
        emailField.clear();

        showAlert("Succès", "Utilisateur ajouté avec succès !");
    }

    @FXML
    public void updateUser() {
        User selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            showAlert("Erreur", "Aucun utilisateur sélectionné.");
            return;
        }

        String newName = nameField.getText().trim();
        String newEmail = emailField.getText().trim();

        if (newName.isEmpty() || newEmail.isEmpty()) {
            showAlert("Erreur", "Les champs ne peuvent pas être vides !");
            return;
        }

        userDAO.updateUser(selectedUser.getId(), newName, newEmail);
        loadUsers();
        userTable.refresh();

        showAlert("Succès", "Utilisateur mis à jour avec succès !");
    }

    @FXML
    public void deleteUser() {
        User selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            showAlert("Erreur", "Aucun utilisateur sélectionné.");
            return;
        }

        userDAO.deleteUser(selectedUser.getId());
        loadUsers();
        nameField.clear();
        emailField.clear();

        showAlert("Succès", "Utilisateur supprimé avec succès !");
    }

    @FXML
    public void searchUser() {
        String keyword = searchField.getText().trim();
        if (keyword.isEmpty()) {
            loadUsers();
            return;
        }

        List<User> users = userDAO.searchUsers(keyword);
        userTable.getItems().setAll(users);
        updateUserCount();
    }

    private void loadUsers() {
        List<User> users = userDAO.getAllUsers();
        userTable.getItems().setAll(users);
        updateUserCount();
    }

    @FXML
    public void resetSearch() {
        searchField.clear();
        userTable.getSelectionModel().clearSelection();
        loadUsers();
    }

    private void updateUserCount() {
        int count = userTable.getItems().size();
        userCountLabel.setText("Nombre total d’utilisateurs : " + count);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
