package viewmodel;

import model.Model;
import model.ModelManger;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class TransferViewModelTest {
    private static Model model;
    private static ViewState viewState;

    @BeforeAll
    static void beforeAll() throws SQLException, IOException {
        model = new ModelManger();
        viewState = new ViewState();
    }


}