package src;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.Label;

public class HelloFX extends Application {
  public static final int BOARD_SIZE = 8;
  public static final int SQUARE_LENGTH = 80;
  public static final int PADDING = 160;
  String blackText = "Black";
  String whiteText = "White";

  public static void main(String[] args) {
    HelloFX.launch(args);
  }

  public void start(Stage primaryStage) {
    primaryStage.setTitle("Playing Chess");

    Text blackPlayer = new Text("Black");
    blackPlayer.setFont(Font.font(16));

    Label blackLabel = new Label(this.blackText);

    HBox blackRow = new HBox(PADDING, blackLabel);

    GridPane board = new GridPane();

    for (int row = 0; row < BOARD_SIZE; row++) {
      for (int col = 0; col < BOARD_SIZE; col++) {
        StackPane square = new StackPane();
        Color tileColour = ((row + col) % 2 == 0) ? Color.web(Colour.WHITE.getColourCode())
            : Color.web(Colour.BLACK.getColourCode());

        Rectangle visualSquare = new Rectangle(SQUARE_LENGTH, SQUARE_LENGTH, tileColour);
        square.getChildren().add(visualSquare);
        board.add(square, col, row);
        board.setAlignment(Pos.CENTER);
      }
    }
    VBox root = new VBox(blackRow, board);
    Scene scene = new Scene(root, 750, 750);
    primaryStage.setScene(scene);
    primaryStage.show();

  }

  public void updateBoard(Board board) {
    String str = "";
    str += board.getPointDiff();
    this.blackText = str;

  }
}
