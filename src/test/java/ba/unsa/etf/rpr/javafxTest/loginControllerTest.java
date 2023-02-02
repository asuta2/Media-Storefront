package ba.unsa.etf.rpr.javafxTest;

import ba.unsa.etf.rpr.business.MediaManager;
import ba.unsa.etf.rpr.mn.Media;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(ApplicationExtension.class)
public class loginControllerTest {
    @Start
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
        stage.setTitle("Login");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        stage.toFront();
    }
    @Test
    void loginTest(FxRobot robot){
        robot.clickOn("#UsernameField");
        robot.write("admin@admin.com");
        robot.clickOn("#PasswordField");
        robot.write("admin");
        robot.clickOn("#loginButton");
        //test if main window opens
        robot.lookup("#mainWindow").tryQuery().isPresent();
        BorderPane mainWindow = robot.lookup("#mainWindow").queryAs(BorderPane.class);
        assertNotNull(mainWindow);
    }
    @Test
    void testRegister(FxRobot robot){
        robot.clickOn("#RegisterBtn");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        robot.lookup("#usernameField").tryQuery().isPresent();
        TextField usernameField = robot.lookup("#usernameField").queryAs(TextField.class);
        assertNotNull(usernameField);
    }
    @Test
    void testMainWindowListSort(FxRobot robot) {
        robot.clickOn("#UsernameField");
        robot.write("test@test.com");
        robot.clickOn("#PasswordField");
        robot.write("testic123");
        robot.clickOn("#loginButton");
        //test if main window opens
        robot.lookup("#myLibraryButton").tryQuery().isPresent();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ChoiceBox sortChoiceBox = robot.lookup("#orderByTypeBox").queryAs(ChoiceBox.class);
        assertNotNull(sortChoiceBox);
        robot.clickOn("#orderByTypeBox");
        robot.clickOn("Video Games");
        assertEquals("Video Games", sortChoiceBox.getValue());
        ChoiceBox typeChoiceBox = robot.lookup("#orderByBox").queryAs(ChoiceBox.class);
        assertNotNull(typeChoiceBox);
        robot.clickOn("#orderByBox");
        robot.clickOn("On Sale");
        assertEquals("On Sale", typeChoiceBox.getValue());
        ListView listView = robot.lookup("#mediaList").queryAs(ListView.class);
        List<Media> mediaList = listView.getItems();
        MediaManager mediaManager = new MediaManager();
        assertNotNull(mediaList);
        List<Media> check = mediaManager.getMediaOnSale();
        //filter check so that only video games are left
        check.removeIf(media -> !(media.getTypeId() == 1));
        //check if list is sorted correctly
        new Thread(() -> {
            for (int i = 0; i < mediaList.size(); i++) {
                assertEquals(check.get(i).getMediaName(), mediaList.get(i).getMediaName());
            }
        }).start();
    }
}
