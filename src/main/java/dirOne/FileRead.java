package dirOne;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileRead {
    private static final Map<Task, String> userMap = new HashMap<>();
    public static void saveToFile(TableView<Task> tableView, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            for (int i = 0; i < tableView.getColumns().size(); i++) {
                writer.write(tableView.getColumns().get(i).getText());
                if (i < tableView.getColumns().size() - 1) {
                    writer.write(";");
                }
            }
            writer.newLine();

            for (Task task : tableView.getItems()) {
                for (int i = 0; i < tableView.getColumns().size(); i++) {
                    Object cellValue = tableView.getColumns().get(i).getCellData(task);
                    if (cellValue != null) {
                        writer.write(cellValue.toString());
                    }
                    if (i < tableView.getColumns().size() - 1) {
                        writer.write(";");
                    }
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void loadFromFile(TableView<Task> tableView, File file) {
        ObservableList<Task> data = tableView.getItems();
        data.clear();
        userMap.clear();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                reader.readLine(); // Skip header line if there is one
                while ((line = reader.readLine()) != null) {
                    String[] values = line.split(";");
                    if (values.length >= 3) {
                        String nr = values[0];
                        boolean check = Boolean.parseBoolean(values[1]);
                        String notes = values[2];
                        String user = values.length > 3 ? values[3] : "";

                        Task task = new Task(nr, check, notes);
                        task.setUser(user);
                        data.add(task);
                        userMap.put(task, user);
                    }
                }

                tableView.setEditable(true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public static String getUserForTask(Task task) { return userMap.getOrDefault(task,"");} //useless for now
    }

