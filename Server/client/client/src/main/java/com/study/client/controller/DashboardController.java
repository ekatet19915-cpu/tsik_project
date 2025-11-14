package com.study.client.controller;

import com.study.client.model.Group;
import com.study.client.model.User;
import com.study.client.service.GroupService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.util.List;

public class DashboardController {

    @FXML
    private ListView<Group> groupsListView;

    @FXML
    private Button createGroupButton;

    @FXML
    private Label welcomeLabel;

    private User currentUser;
    private final GroupService groupService = new GroupService();

    public void setCurrentUser(User user) {
        this.currentUser = user;
        welcomeLabel.setText("Добро пожаловать, " + user.getUsername());
        loadGroups();
    }

    private void loadGroups() {
        new Thread(() -> {
            try {
                List<Group> groups = groupService.getGroupsForUser(currentUser.getId());
                Platform.runLater(() -> groupsListView.setItems(FXCollections.observableArrayList(groups)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    @FXML
    private void onCreateGroupClicked(MouseEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Создать группу");
        dialog.setHeaderText("Введите название группы:");
        dialog.showAndWait().ifPresent(name -> {
            new Thread(() -> {
                try {
                    groupService.createGroup(currentUser.getId(), name);
                    loadGroups(); // обновляем список
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        });
    }

    @FXML
    private void onGroupSelected(MouseEvent event) {
        Group selectedGroup = groupsListView.getSelectionModel().getSelectedItem();
        if(selectedGroup != null) {
            // можно открыть group.fxml или другую сцену
            System.out.println("Выбрана группа: " + selectedGroup.getName());
        }
    }
}
