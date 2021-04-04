import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;
import model.ModelManger;
import view.ViewHandler;
import viewmodel.ViewModelFactory;

import java.io.IOException;

public class ApplicationPlaceOrder extends Application {
    @Override public void start(Stage primaryStage) throws IOException
    {
        Model model = new ModelManger();
        ViewModelFactory viewModelFactory = new ViewModelFactory(model);
        ViewHandler view = new ViewHandler(viewModelFactory);

        view.start(primaryStage);
    }

    @Override public void stop() throws IOException {
    }
}
