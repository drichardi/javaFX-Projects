import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.util.Map;
import java.util.HashMap;

import javafx.beans.binding.Bindings;
import javafx.beans.property.*;

public class CalcFX extends Application
{
  //Create an array of arrays (logically by calculator row)
  //unicode character's used for mathematical symbols
  //Note: final means that the values cannot be changed once initialized
  private static final String[][] calcButtons = {
    { "Rad", "Deg", "x!", "(", ")", "%", "CE" },
    { "Inv", "sin", "ln", "7", "8", "9", "\u00F7" },
    { "\u03C0", "cos", "log", "4", "5", "6", "\u00D7" },
    { "\u0065", "tan", "\u221A", "1", "2", "3", "\u2212" },
    { "Ans", "EXP", "Xy", "0", ".", "=", "+" }
  };

  //Using a Map to quickly get a button with a key usingthe button's text string
  private final Map<String, Button> commands = new HashMap<>();

  //Properties allow get/set methods to encapsulate numbers as we operate
  private DoubleProperty stackValue = new SimpleDoubleProperty();
  private DoubleProperty value = new SimpleDoubleProperty();

  //Enum to help readability
  private enum Operation { NOOPERATION, ADD, SUBTRACT, MULTIPLY, DIVIDE,
                           INV, SIN, LN, PI, COS, LOG, E, TAN, SQUAREROOT,
                           ANS, EXP, XY, RADIANS, DEGREES, FACTORIAL,
                           LPAREN, RPAREN, PERCENTAGE }

  private Operation currentOp = Operation.NOOPERATION;
  private Operation stackOp = Operation.NOOPERATION;



  @Override
  public void start(Stage theStage)
  {
    final TextField screen = createScreen();
    final TilePane buttons = addButtons();

    theStage.setTitle("Do you even Math, bro?");
    //creates solid white background with minimal stage decoration
    theStage.initStyle(StageStyle.UTILITY);
    theStage.setResizable(false);
    theStage.setScene(new Scene(createLayout(screen, buttons)));
    theStage.show();
  }

  private VBox createLayout(TextField screen, TilePane buttons) {
    //Vbox(int spacing)
    final VBox layout = new VBox(4);
    layout.setAlignment(Pos.CENTER);
    layout.setStyle("-fx-background-color: #fff;"
                  + "-fx-font-size: 14px;");
    //adding buttons and screen to layout
    layout.getChildren().setAll(screen, buttons);
    handleCommands(layout);
    screen.maxWidthProperty().bind(buttons.widthProperty());

    return layout;

  }

  private void handleCommands(VBox layout) {
    layout.addEventFilter(KeyEvent.KEY_PRESSED, e ->
    {
      //TODO CAN BE EXPANDED TO INCLUDE KEYBOARD SHORTCUTS
      // e.g. "C" = COSINE / "S" = SINE / ETC..
      Button activated = commands.get(e.getText());
      if (activated != null) {
        //Press button programmatically
        activated.fire();
      }
    });
  }

  private TextField createScreen() {
    final TextField screen = new TextField();
    screen.setStyle("-fx-background-color: #eee;");
    screen.setAlignment(Pos.CENTER_RIGHT);
    screen.setEditable(false);
    screen.textProperty().bind(Bindings.format("%.00f", value));

    return screen;

  }

  private TilePane addButtons() {
    TilePane buttons = new TilePane();
    buttons.setVgap(4);
    buttons.setHgap(4);
    buttons.setPrefWidth(450);
    buttons.setMaxWidth(450);
    buttons.setPrefColumns(calcButtons[0].length);
    for (String[] rows : calcButtons) {
      for (String label : rows) {
        buttons.getChildren().add(createButton(label));
      }
    }
    return buttons;
  }

  private Button createButton(final String label) {
    Button button = makeStandardButton(label);

    if(label.matches("[.0-9]")) {
      makeNumberPadButton(label, button);
    } else {
      final ObjectProperty<Operation> triggerOp = determineOperand(label);
      if (triggerOp.get() != Operation.NOOPERATION) {
        makeOperandButton(button, triggerOp);
      } else if ("CE".equals(label)) {
        makeClearButton(button);
      } else if ("=".equals(label)) {
        makeEqualsButton(button);
      }
    }
    return button;
  }

  private ObjectProperty<Operation> determineOperand(String label) {
    final ObjectProperty<Operation> triggerOp =
      new SimpleObjectProperty<>(Operation.NOOPERATION);

      switch(label) {
        case "+":       triggerOp.set(Operation.ADD);         break;
        case "\u2212":  triggerOp.set(Operation.SUBTRACT);    break;
        case "\u00D7":  triggerOp.set(Operation.MULTIPLY);    break;
        case "\u00F7":  triggerOp.set(Operation.DIVIDE);      break;
        case "Inv":     triggerOp.set(Operation.INV);         break;
        case "sin":     triggerOp.set(Operation.SIN);         break;
        case "ln":      triggerOp.set(Operation.LN);          break;
        case "\u03C0":  triggerOp.set(Operation.PI);          break;
        case "cos":     triggerOp.set(Operation.COS);         break;
        case "log":     triggerOp.set(Operation.LOG);         break;
        case "\u0065":  triggerOp.set(Operation.E);           break;
        case "tan":     triggerOp.set(Operation.TAN);         break;
        case "\u221A":  triggerOp.set(Operation.SQUAREROOT);  break;
        case "Ans":     triggerOp.set(Operation.ANS);         break;
        case "EXP":     triggerOp.set(Operation.EXP);         break;
        case "Xy":      triggerOp.set(Operation.XY);          break;
        case "Rad":     triggerOp.set(Operation.RADIANS);     break;
        case "Deg":     triggerOp.set(Operation.DEGREES);     break;
        case "x!":      triggerOp.set(Operation.FACTORIAL);   break;
        case "(":       triggerOp.set(Operation.LPAREN);      break;
        case ")":       triggerOp.set(Operation.RPAREN);      break;
        case "%":       triggerOp.set(Operation.PERCENTAGE);  break;
      }
      return triggerOp;
  }

  private void makeOperandButton(
    Button button, final ObjectProperty<Operation> triggerOp) {

    button.setStyle("-fx-min-width: 60px;"
                  + "-fx-min-height: 40px;"
                  + "-fx-background-color: #ccc;");
    button.setOnAction(event -> {
      currentOp = triggerOp.get();
    });
  }

  private Button makeStandardButton(String label) {
    Button button = new Button(label);
    commands.put(label, button);
    button.setMaxSize(60, 40);
    return button;
  }

  private void makeNumberPadButton(final String label, Button button) {
    button.setStyle("-fx-min-width: 60px;"
                  + "-fx-min-height: 40px;"
                  + "-fx-background-color: #eee;");
    button.setOnAction(event -> {
      if(currentOp == Operation.NOOPERATION) {
        value.set(value.get() * 10 + Integer.parseInt(label));
      } else {
        stackValue.set(value.get());
        value.set(Integer.parseInt(label));

        //save operator state and open up current Operation for a new command
        stackOp = currentOp;
        currentOp = Operation.NOOPERATION;
      }
    });
  }
  private void makeClearButton(Button button) {
    button.setStyle("-fx-min-width: 60px;"
                  + "-fx-min-height: 40px;"
                  + "-fx-background-color: #ccc;");
    button.setOnAction(event -> {
      //set stored value property to 0
      value.set(0);
    });
  }
  private void makeEqualsButton(Button button) {
    button.setStyle("-fx-min-width: 60px;"
                  + "-fx-min-height: 40px;"
                  + "-fx-background-color: #298edb;");
    button.setOnAction(event -> {
      switch (stackOp) {
        case ADD:         value.set(stackValue.get() + value.get());  break;
        case SUBTRACT:    value.set(stackValue.get() - value.get());  break;
        case MULTIPLY:    value.set(stackValue.get() * value.get());  break;
        case DIVIDE:      value.set(stackValue.get() / value.get());  break;

        //TODO IMPLEMENT THE REST OF THE OPERATORS
        //TODO PI IS CONSIDERED AN OPERAND CURRENTLY BUT IT SHOULD BE A NUMBER
      }
    });
  }
  public static void main(String[] args) {
      launch(args);
    }

}
