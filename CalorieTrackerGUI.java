import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.time.LocalDate;

public class CalorieTrackerGUI extends Application {
    private CalorieTracker tracker;

    private TextField nameField;
    private TextField caloriesField;
    private TextField dateField;
    private Button addButton;
    private Button viewCaloriesButton;
    private Label totalCaloriesLabel;

    public CalorieTrackerGUI() {
        tracker = new CalorieTracker();
    }

    @Override
    public void start(Stage primaryStage) {
        nameField = new TextField();
        nameField.setPromptText("Meal Name");
        caloriesField = new TextField();
        caloriesField.setPromptText("Calories");
        dateField = new TextField();
        dateField.setPromptText("Date (yyyy-MM-dd)");
        addButton = new Button("Add Meal");
        addButton.setOnAction(e -> addMealFromGUI());

        viewCaloriesButton = new Button("View Total Calories");
        viewCaloriesButton.setOnAction(e -> displayTotalCalories());
        totalCaloriesLabel = new Label();

        VBox layout = new VBox(10);
        layout.getChildren().addAll(
                new Label("Add Meal"),
                nameField,
                caloriesField,
                dateField,
                addButton,
                viewCaloriesButton,
                totalCaloriesLabel
        );

        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Calorie Tracker");
        primaryStage.show();
    }

    private void addMealFromGUI() {
        String name = nameField.getText();
        int calories = Integer.parseInt(caloriesField.getText());
        LocalDate date = LocalDate.parse(dateField.getText()); // Assuming date is entered in "yyyy-MM-dd" format
        Meal meal = new Meal(name, calories, date);
        tracker.addMeal(meal);
        nameField.clear();
        caloriesField.clear();
        dateField.clear();
    }

    private void displayTotalCalories() {
        int totalCalories = 0;
        LocalDate currentDate = LocalDate.now();
        for (Meal meal : tracker.getMeals()) {
            if (isSameDate(meal.getDate(), currentDate)) {
                totalCalories += meal.getCalories();
            }
        }
        totalCaloriesLabel.setText("Total Calories for Today: " + totalCalories);
    }

    private boolean isSameDate(LocalDate date1, LocalDate date2) {
        return date1.equals(date2);
    }

    public static void main(String[] args) {
        launch(args);
    }
}



