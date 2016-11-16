import javafx.application.Application;
import javafx.event.ActionEvent;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import javafx.stage.Stage;

public class CalcFX extends Application
{
  Button btnAdd, btnSub, btnDiv, btnMul, btnAC, btnRad, btnInv, btnPi, btnE, btnAns, btnSin, btnCos, btnTan, btnEXP, btnDeg, btnFactorial, btnLn, btnLog, btnSqRoot, btnXy, btnLParen, btnRParen, btnPercent, btnNum0, btnNum1, btnNum2, btnNum3, btnNum4, btnNum5, btnNum6, btnNum7, btnNum8, btnNum9, btnDecimalPoint, btnEquals;
  Label lblAnswer, lblInput;



  @Override
  public void start(Stage theStage)
  {
    //make controls
    lblAnswer = new Label("");
    lblInput = new Label("");
    btnRad = new Button("Rad");
    btnInv = new Button("Inv");
    btnPi = new Button("Pi");
    btnE = new Button("e");
    btnAns = new Button("Ans");
    btnDeg = new Button("Deg");
    btnSin = new Button("sin");
    btnCos = new Button("cos");
    btnTan = new Button("tan");
    btnEXP = new Button("EXP");
    btnFactorial = new Button("x!");
    btnLn = new Button("ln");
    btnLog = new Button("log");
    btnSqRoot = new Button("\u221A");
    btnXy = new Button("xY");
    btnLParen = new Button("(");
    btnRParen = new Button(")");
    btnAdd = new Button("+");
    btnSub = new Button("-");
    btnMul = new Button("x");
    btnDiv = new Button("/");
    btnAC = new Button("AC");
    btnPercent = new Button("%");
    btnEquals = new Button("=");
    btnDecimalPoint = new Button(".");
    btnNum0 = new Button("0");
    btnNum1 = new Button("1");
    btnNum2 = new Button("2");
    btnNum3 = new Button("3");
    btnNum4 = new Button("4");
    btnNum5 = new Button("5");
    btnNum6 = new Button("6");
    btnNum7 = new Button("7");
    btnNum8 = new Button("8");
    btnNum9 = new Button("9");


    //right align text in Label
    //lblAnswer.setAlignment(Pos.RIGHT);
    //apply css-like style to Label

  //  lblanswer.setStyle("-fx-border-color: #000; -fx-padding: 5px;");

    //make container for app

    GridPane root = new GridPane();
    //put container in middle of scene
    root.setAlignment(Pos.CENTER);
    //set spacing between controls
    root.setHgap(4);
    root.setVgap(4);
    //add to grid, cell by cell
    //answer
    root.add(lblAnswer, 0, 0, 7, 1);
    //input
    root.add(lblInput, 0, 1, 7, 1);
    //row 1
    root.add(btnRad, 0,2);
    root.add(btnDeg, 1,2);
    root.add(btnFactorial, 2,2);
    root.add(btnLParen, 3,2);
    root.add(btnRParen, 4,2);
    root.add(btnPercent, 5,2);
    root.add(btnAC, 6, 2);
    //row 2
    root.add(btnInv, 0,3);
    root.add(btnSin, 1, 3);
    root.add(btnLn, 2, 3);
    root.add(btnNum7, 3, 3);
    root.add(btnNum8, 4, 3);
    root.add(btnNum9, 5, 3);
    root.add(btnDiv, 6, 3);
    //row 3
    root.add(btnPi, 0, 4);
    root.add(btnCos, 1, 4);
    root.add(btnLog, 2, 4);
    root.add(btnNum4, 3, 4);
    root.add(btnNum5, 4, 4);
    root.add(btnNum6, 5, 4);
    root.add(btnMul, 6, 4);
    //row 4
    root.add(btnE, 0, 5);
    root.add(btnTan, 1, 5);
    root.add(btnSqRoot, 2, 5);
    root.add(btnNum1, 3, 5);
    root.add(btnNum2, 4, 5);
    root.add(btnNum3, 5, 5);
    root.add(btnSub, 6, 5);
    //row 5
    root.add(btnAns, 0, 6);
    root.add(btnEXP, 1, 6);
    root.add(btnXy, 2, 6);
    root.add(btnNum0, 3, 6);
    root.add(btnDecimalPoint, 4, 6);
    root.add(btnEquals, 5, 6);
    root.add(btnAdd, 6, 6);


    //last 2 rows span across 2 columns
    //col, row, colspan, rowspan
    //root.add(lblanswer, 0, 3, 2, 1);
    //root.add(btnclear, 0, 4, 2, 1);
    //set widths of all controls in method
    root.getChildren().forEach(node ->
      node.setStyle("-fx-min-width: 60px; -fx-min-height: 40px; -fx-background-color: #ccc;")
    );
    lblInput.setStyle("-fx-min-width: 445px; -fx-padding: 10px; -fx-min-height: 60px; -fx-background-color: #000;");
    lblAnswer.setStyle("-fx-min-width: 445px; -fx-height: 20px; -fx-min-height: 20px; -fx-padding: 5px; -fx-background-color: #888;");
    btnEquals.setStyle("-fx-min-width: 60px; -fx-min-height: 40px; -fx-background-color: #298edb;");
    {
      String styleNumPad = btnNum0.getStyle();
      styleNumPad += "-fx-background-color: #ddd;";
      btnNum0.setStyle(styleNumPad);
      btnNum1.setStyle(styleNumPad);
      btnNum2.setStyle(styleNumPad);
      btnNum3.setStyle(styleNumPad);
      btnNum4.setStyle(styleNumPad);
      btnNum5.setStyle(styleNumPad);
      btnNum6.setStyle(styleNumPad);
      btnNum7.setStyle(styleNumPad);
      btnNum8.setStyle(styleNumPad);
      btnNum9.setStyle(styleNumPad);
      btnDecimalPoint.setStyle(styleNumPad);
    }



    /*
    setWidths();
    //attach buttons to code in method
    linkButtonFunctions();
    //usual stuff for apps
    */

    Scene scene = new Scene(root, 600, 400);
    theStage.setTitle("Mathemagic 1.0");
    theStage.setScene(scene);
    theStage.show();
  }

  /*
    public void setWidths()
    {
      //set widths of all controls
      txtnum1.setPrefWidth(70);
      txtnum2.setPrefWidth(70);
      btnadd.setPrefWidth(70);
      btnsub.setPrefWidth(70);
      btnmul.setPrefWidth(70);
      btndiv.setPrefWidth(70);
      btnclear.setPrefWidth(150);
      lblanswer.setPrefWidth(150);
    }
    public void linkButtonFunctions()
    {
      //have each button run buttonFunctions when clicked
      btnadd.setOnAction(e -> buttonFunctions(e));
      btnsub.setOnAction(e -> buttonFunctions(e));
      btnmul.setOnAction(e -> buttonFunctions(e));
      btndiv.setOnAction(e -> buttonFunctions(e));
      btnclear.setOnAction(e -> buttonFunctions(e));
    }

    public void buttonFunctions(ActionEvent e)
    {
      int num1, num2, answer;
      char symbol;
      //e tells us which button was clicked "Event"
      if(e.getSource() == btnclear)
      {
        txtnum1.setText("");
        txtnum2.setText("");
        lblanswer.setText("?");
        txtnum1.requestFocus();
        return;
      }
      //read numbers in from textfields
      num1 = Integer.parseInt(txtnum1.getText());
      num2 = Integer.parseInt(txtnum2.getText());
      if(e.getSource() == btnadd)
      {
        symbol = '+';
        answer = num1 + num2;
      }
      else if(e.getSource() == btnsub)
      {
        symbol = '-';
        answer = num1 - num2;
      }
      else if(e.getSource() == btnmul)
      {
        symbol = 'x';
        answer = num1 * num2;
      }
      else
      {
        symbol = '/';
        answer = num1 / num2;
      }
      //display answer
      lblanswer.setText("" + num1 + symbol + num2 + " = " + answer);

    }
    */
    public static void main(String[] args) {
      launch(args);
    }

}
