package org.example.employeemanagementsystem;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.util.Callback;

public class HelloController {
    @FXML
    private TableView<Employee> employeeTable;
    @FXML
    private TableColumn<Employee, String> nameColumn;
    @FXML
    private TableColumn<Employee, String> typeColumn;
    @FXML
    private TableColumn<Employee, Double> salaryColumn;
    @FXML
    private TableColumn<Employee, Button> removeColumn;

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField salaryTextField;
    @FXML
    private TextField hoursWorkedTextField;
    @FXML
    private TextField hourlyRateTextField;
    @FXML
    private TextField maxHoursTextField;

    @FXML
    private RadioButton fullTimeRadioButton;
    @FXML
    private RadioButton partTimeRadioButton;
    @FXML
    private RadioButton contractorRadioButton;

    @FXML
    private Button addEmployeeButton;

    private final ObservableList<Employee> employeeList = FXCollections.observableArrayList();
    private ToggleGroup employeeTypeGroup;

    @FXML
    public void initialize() {
        // Initialize table columns
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getType()));
        salaryColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().calculateSalary()).asObject());

        // Set up remove column with a button
        removeColumn.setCellFactory(new Callback<TableColumn<Employee, Button>, TableCell<Employee, Button>>() {
            @Override
            public TableCell<Employee, Button> call(TableColumn<Employee, Button> param) {
                return new TableCell<Employee, Button>() {
                    private final Button removeButton = new Button("Remove");

                    {
                        removeButton.setOnAction(event -> {
                            Employee employee = getTableView().getItems().get(getIndex());
                            removeEmployee(employee);
                        });
                    }

                    @Override
                    protected void updateItem(Button item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(removeButton);
                        }
                    }
                };
            }
        });

        // Add items to the employee table
        employeeTable.setItems(employeeList);

        // Configure the radio buttons to work as a group
        employeeTypeGroup = new ToggleGroup();
        fullTimeRadioButton.setToggleGroup(employeeTypeGroup);
        partTimeRadioButton.setToggleGroup(employeeTypeGroup);
        contractorRadioButton.setToggleGroup(employeeTypeGroup);

        // Configure the Add Employee button
        addEmployeeButton.setOnAction(event -> addEmployee());
    }

    // Add the employee to the table with validation
    public void addEmployee() {
        String name = nameTextField.getText();
        double salary = 0;
        int hoursWorked = 0;
        double hourlyRate = 0;
        int maxHours = 0;

        // Determine selected employee type
        RadioButton selectedType = (RadioButton) employeeTypeGroup.getSelectedToggle();
        if (selectedType == null) {
            showAlert("Error", "Please select an employee type");
            return;
        }
        String type = selectedType.getText();

        // Validate inputs based on employee type
        if (type.equals("Full")) {
            try {
                salary = Double.parseDouble(salaryTextField.getText());
                if (salary <= 0) {
                    showAlert("Error", "Salary must be a positive number");
                    return;
                }
            } catch (NumberFormatException e) {
                showAlert("Error", "Invalid salary entered");
                return;
            }
        } else if (type.equals("Part Time")) {
            try {
                hoursWorked = Integer.parseInt(hoursWorkedTextField.getText());
                hourlyRate = Double.parseDouble(hourlyRateTextField.getText());
                if (hoursWorked < 0 || hourlyRate <= 0) {
                    showAlert("Error", "Hours worked and hourly rate must be non-negative and positive, respectively");
                    return;
                }
            } catch (NumberFormatException e) {
                showAlert("Error", "Invalid input for hours worked or hourly rate");
                return;
            }
        } else if (type.equals("Contract")) {
            try {
                maxHours = Integer.parseInt(maxHoursTextField.getText());
                hourlyRate = Double.parseDouble(hourlyRateTextField.getText());
                if (maxHours < 0 || hourlyRate <= 0) {
                    showAlert("Error", "Max hours and hourly rate must be non-negative and positive, respectively");
                    return;
                }
            } catch (NumberFormatException e) {
                showAlert("Error", "Invalid input for max hours or hourly rate");
                return;
            }
        }

        // If all inputs are valid, create the employee and add to the table
        Employee newEmployee = createEmployee(type, name, salary, hoursWorked, hourlyRate, maxHours);
        employeeList.add(newEmployee);
    }

    // Create the employee based on type
    private Employee createEmployee(String type, String name, double salary, int hoursWorked, double hourlyRate, int maxHours) {
        switch (type) {
            case "Full":
                return new FullTimeEmployee(name, salary);
            case "Part Time":
                return new PartTimeEmployee(name, hourlyRate, hoursWorked);
            case "Contract":
                return new Contractor(name, hourlyRate, maxHours);
            default:
                return null;
        }
    }

    // Show an alert dialog with the given message
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Remove employee from the table
    public void removeEmployee(Employee employee) {
        employeeList.remove(employee);
    }
}
