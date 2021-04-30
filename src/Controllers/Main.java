package Controllers;

import com.firebase.client.Firebase;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;

public class Main extends Application {
    public static Stage loginstage, mainStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.loginstage = primaryStage;
        LoginWindow();

    }

    public void LoginWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML Files/LogIn.fxml"));
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            loginstage.setScene(scene);
            loginstage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void MainMenuWindow() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML Files/MainMenu.fxml"));
                    AnchorPane pane = loader.load();
                    Scene scene = new Scene(pane);
                    mainStage = new Stage();
                    mainStage.setScene(scene);
                    mainStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void DoctorsLoginWindow() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML Files/DoctorLogIn.fxml"));
                    AnchorPane pane = loader.load();
                    Scene scene = new Scene(pane);
                    mainStage = new Stage();
                    mainStage.setScene(scene);
                    mainStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void DoctorsRegisterWindow() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML Files/DoctorRegister.fxml"));
                    AnchorPane pane = loader.load();
                    Scene scene = new Scene(pane);
                    mainStage = new Stage();
                    mainStage.setScene(scene);
                    mainStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void DoctorMainMenu() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML Files/DoctorMainMenu.fxml"));
                    AnchorPane pane = loader.load();
                    Scene scene = new Scene(pane);
                    mainStage = new Stage();
                    mainStage.setScene(scene);
                    mainStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void DoctorAppointment() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML Files/DoctorAppointments.fxml"));
                    AnchorPane pane = loader.load();
                    Scene scene = new Scene(pane);
                    mainStage = new Stage();
                    mainStage.setScene(scene);
                    mainStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void DoctorPatients() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML Files/DoctorPatients.fxml"));
                    AnchorPane pane = loader.load();
                    Scene scene = new Scene(pane);
                    mainStage = new Stage();
                    mainStage.setScene(scene);
                    mainStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void DoctorDetails() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML Files/DoctorDetails.fxml"));
                    AnchorPane pane = loader.load();
                    Scene scene = new Scene(pane);
                    mainStage = new Stage();
                    mainStage.setScene(scene);
                    mainStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void UserAppointmentsWindow() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML Files/UserAppointments.fxml"));
                    AnchorPane pane = loader.load();
                    Scene scene = new Scene(pane);
                    mainStage = new Stage();
                    mainStage.setScene(scene);
                    mainStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void UserDetailsWindow() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML Files/UserDetails.fxml"));
                    AnchorPane pane = loader.load();
                    Scene scene = new Scene(pane);
                    mainStage = new Stage();
                    mainStage.setScene(scene);
                    mainStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void PreQuesWindow() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML Files/PreQues.fxml"));
                    AnchorPane pane = loader.load();
                    Scene scene = new Scene(pane);
                    mainStage = new Stage();
                    mainStage.setScene(scene);
                    mainStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void PostQuesWindow() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML Files/PostQues.fxml"));
                    AnchorPane pane = loader.load();
                    Scene scene = new Scene(pane);
                    mainStage = new Stage();
                    mainStage.setScene(scene);
                    mainStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void UserPaymentsWindow() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML Files/UserPayments.fxml"));
                    AnchorPane pane = loader.load();
                    Scene scene = new Scene(pane);
                    mainStage = new Stage();
                    mainStage.setScene(scene);
                    mainStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void DoctorsAppointmentScreen() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML Files/DoctorAppointmentScreen.fxml"));
                    AnchorPane pane = loader.load();
                    Scene scene = new Scene(pane);
                    mainStage = new Stage();
                    mainStage.setScene(scene);
                    mainStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void UserAppointmentScreen() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML Files/UserAppointmentScreen.fxml"));
                    AnchorPane pane = loader.load();
                    Scene scene = new Scene(pane);
                    mainStage = new Stage();
                    mainStage.setScene(scene);
                    mainStage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void CloseButton(Stage stage) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                stage.close();
            }
        });
    }

    public static void main (String[]args){
        launch(args);
    }

}