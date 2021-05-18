package viewmodel;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Model;
import model.ModelManger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class AccountViewModelTest {
    private AccountViewModel AVM;
    private static Model model;
    private static ViewState viewState;

    @BeforeAll
    static void beforeAll() throws SQLException, IOException {
        model = new ModelManger();
        viewState = new ViewState();
    }

    @BeforeEach
    void setUp() {
        AVM = new AccountViewModel(model,viewState);
    }

    @Test
    public void setWithdraw(){
        AVM.setWithdraw();
        assertTrue(viewState.isWithdraw());
    }

    @Test
    public void setAdd(){
        AVM.setAdd();
        assertFalse(viewState.isWithdraw());
    }
}