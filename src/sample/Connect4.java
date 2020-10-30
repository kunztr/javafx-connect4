package sample;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

/**2 Player Connect4
 * @author Tyler Kunz
 */
public class Connect4 extends Application {

    /*
    Handles what the user interacts with
     */
    public void start(Stage stage){
        Game game = new Game();
        FlowPane fp = new FlowPane();
        GridPane gp = new GridPane();
        gp.setGridLinesVisible(true);
        fp.getChildren().add(gp);

        //Initializes 7 buttons, 1 for each column.
        //I wasn't sure how to add buttons & events via for loop so I had to hard code them.
        Image image = new Image("https://image.flaticon.com/icons/png/512/16/16748.png");
        // Image image = new Image("https://i.giphy.com/media/9PekRRQ0WlrRVwynAk/giphy.webp");
        //Image image = new Image("https://media.wired.com/photos/598e35fb99d76447c4eb1f28/master/pass/phonepicutres-TA.jpg");
        ImageView iv0 = new ImageView(image);
        iv0.setFitHeight(50);
        iv0.setFitWidth(30);
        ImageView iv1 = new ImageView(image);
        iv1.setFitHeight(50);
        iv1.setFitWidth(30);
        ImageView iv2 =  new ImageView(image);
        iv2.setFitHeight(50);
        iv2.setFitWidth(30);
        ImageView iv3 =  new ImageView(image);
        iv3.setFitHeight(50);
        iv3.setFitWidth(30);
        ImageView iv4 =  new ImageView(image);
        iv4.setFitHeight(50);
        iv4.setFitWidth(30);
        ImageView iv5 =  new ImageView(image);
        iv5.setFitHeight(50);
        iv5.setFitWidth(30);
        ImageView iv6 =  new ImageView(image);
        iv6.setFitHeight(50);
        iv6.setFitWidth(30);

        Button b0 = new Button();
        b0.setGraphic(iv0);
        //b0.setText("Insert");
        //b0.setAlignment(Pos.CENTER);
        b0.setPrefSize(100,50);
        gp.add(b0, 0, 0);
        //Adds chip to given column when clicked
        b0.setOnMouseClicked(event -> {
            Chip c = game.addChip(0);
            updateGrid(game, gp, fp, c);
        });
        Button b1 = new Button();
        b1.setGraphic(iv1);
        b1.setPrefSize(100,50);

        gp.add(b1, 1, 0);
        b1.setOnMouseClicked(event -> {
            Chip c = game.addChip(1);
            updateGrid(game, gp, fp, c);
        });
        Button b2 = new Button();
        b2.setGraphic(iv2);
        b2.setPrefSize(100,50);

        gp.add(b2, 2, 0);
        b2.setOnMouseClicked(event -> {
            Chip c = game.addChip(2);
            updateGrid(game, gp, fp, c);
        });
        Button b3 = new Button();
        b3.setGraphic(iv3);
        b3.setPrefSize(100,50);

        gp.add(b3, 3, 0);
        b3.setOnMouseClicked(event -> {
            Chip c = game.addChip(3);
            updateGrid(game, gp, fp, c);
        });
        Button b4 = new Button();
        b4.setGraphic(iv4);
        b4.setPrefSize(100,50);

        gp.add(b4, 4, 0);
        b4.setOnMouseClicked(event -> {
            Chip c = game.addChip(4);
            updateGrid(game, gp, fp, c);
        });
        Button b5 = new Button();
        b5.setGraphic(iv5);
        b5.setPrefSize(100,50);

        gp.add(b5, 5, 0);
        b5.setOnMouseClicked(event -> {
            Chip c = game.addChip(5);
            updateGrid(game, gp, fp, c);
        });
        Button b6 = new Button();
        b6.setGraphic(iv6);
        b6.setPrefSize(100,50);

        gp.add(b6, 6, 0);
        b6.setOnMouseClicked(event -> {
            Chip c = game.addChip(6);
            updateGrid(game, gp, fp, c);
        });

        //Creates a 6x7 connect4 board
        for(int i = 1; i < 7; i++){
            for(int j = 0; j < 7; j++) {
                Pane p = new Pane();
                p.setMinSize(100, 100);
                Rectangle r = new Rectangle(100,100);
                r.setFill(Color.BLUE);
                Circle c1 = new Circle(50, 50, 42, Color.LIGHTGRAY);
                Circle c = new Circle(50, 50, 40, Color.TRANSPARENT);
                p.getChildren().addAll(r, c, c1);
                gp.add(p, j, i);
            }
        }
        Scene scene = new Scene(fp, 700,730);
        stage.setTitle("Connect4 - Tyler Kunz");
        stage.setScene(scene);
        stage.show();
    }

    //Updates physical aspects of board
    public void updateGrid(Game game, GridPane gp, FlowPane fp, Chip chip){
        //Creates a completely new pane rather than editing the previous one, which doesn't seem to be possible in a grid pane
        Pane p = new Pane();
        p.setMinSize(100, 100);
        Rectangle r = new Rectangle(100, 100);
        r.setFill(Color.BLUE);
        //Makes chip with correct color
        Circle c = new Circle(50, 50, 40, chip.color);
        Circle c0 = new Circle(50, 50, 42, Color.LIGHTGRAY);
        p.getChildren().addAll(r, c0, c);
        gp.add(p, chip.xCoor, chip.yCoor + 1);
        Text t = new Text(); //Winner/tie message
        t.setFont(new Font(50));
        //Handles flashing animation if someone wins
        if (game.winners.size() >= 4) {
                for (Chip chipW : game.winners) {
                    //New circle is created
                    Pane pW = new Pane();
                    p.setMinSize(100, 100);
                    Rectangle rW = new Rectangle(100, 100);
                    rW.setFill(Color.BLUE);
                    Circle c0W = new Circle(50, 50, 42, Color.LIGHTGRAY);
                    Circle cW = new Circle(50, 50, 40, chip.color);
                    //Animation
                    FadeTransition ft = new FadeTransition(Duration.millis(1000), cW);
                    ft.setFromValue(.5);
                    ft.setToValue(10);
                    ft.setCycleCount(4);
                    pW.getChildren().addAll(rW, c0W, cW);
                    gp.add(pW, chipW.xCoor, chipW.yCoor + 1);
                    ft.play();
                }
                //Winner messages
                if(chip.color == Color.YELLOW){
                    t.setText("Yellow wins!");
                }
                if(chip.color == Color.RED){
                    t.setText("Red wins!");
                }
            }
        //Tie message
        if(game.isFull){
            t.setText("The board is full. It's a draw!");
        }
        fp.getChildren().add(t);

    }

    /**
     * Keeps track of values on connect4 board
     */
    private class Game{
        Chip[][] grid; //Grid of chips
        Chip lastChip; //The previous chip, for determining when to switch colors
        ArrayList<Chip> winners = new ArrayList<>(); //Chips that will flash once someone wins
        boolean isFull; //Is the game board full?

        public Game(){
            grid = new Chip[6][7];
            //Sets default chip values
            for(int y = 0; y < grid.length; y++){
                for(int x = 0; x < grid[y].length; x++){
                    Color c = Color.TRANSPARENT;
                    grid[y][x] = new Chip(x, y, 0, c);
                }
                System.out.println();
            }
            System.out.println();
        }

        //Adds a chip when given a column index
        public Chip addChip(int xCoor){
                for (int y = grid.length - 1; y >= 0; y--) {
                    if (grid[y][xCoor].playerVal == 0) {
                        //Changes between red & yellow chips
                        if (lastChip == null || lastChip.playerVal == 2) {
                            lastChip = grid[y][xCoor];
                            grid[y][xCoor].playerVal = 1;
                            grid[y][xCoor].color = Color.RED;
                        } else if (lastChip.playerVal == 1) {
                            lastChip = grid[y][xCoor];
                            grid[y][xCoor].playerVal = 2;
                            grid[y][xCoor].color = Color.YELLOW;
                        }
                        checkForWin(grid[y][xCoor]);
                        checkIfFull();
                        return grid[y][xCoor];
                    }
                }
            return null;
        }

        //Checks for 4 in a row
        public void checkForWin(Chip c){
            //Vertical
            int chipsInARow = 0;
            for(int y = 0; y < grid.length; y++){
                if(grid[y][c.xCoor].playerVal == c.playerVal){
                    chipsInARow++;
                    winners.add(grid[y][c.xCoor]);
                }
                else{
                    chipsInARow = 0;
                    winners.clear();
                }
                if(chipsInARow == 4){
                    return;
                }
            }

            //Horizontal
            chipsInARow = 0;
            winners.clear();
            for(int x = 0; x < grid[c.yCoor].length - 1; x++){
                if(grid[c.yCoor][x].playerVal == c.playerVal){
                    chipsInARow++;
                    winners.add(grid[c.yCoor][x]);
                }
                else{
                    chipsInARow = 0;
                    winners.clear();
                }
                if(chipsInARow == 4){
                    return;
                }
            }

            //Diagonal Upper Left to Lower Right
            int diagX = c.xCoor; //Starting x point coordinate search
            int diagY = c.yCoor; //Starting y point coordinate search
            chipsInARow = 0;
            winners.clear();
            //Calculates starting point
            while(diagX >= 0 && diagY >= 0){
                diagX--;
                diagY--;
            }
            while(diagX < 6 && diagY < 5){
                diagX++;
                diagY++;
                if(grid[diagY][diagX].playerVal == c.playerVal){
                    chipsInARow++;
                    winners.add(grid[diagY][diagX]);
                }
                else{
                    chipsInARow = 0;
                    winners.clear();
                }
                if(chipsInARow == 4){
                    return;
                }
            }

            //Diagonal Upper Right to Lower Left
            diagX = c.xCoor;
            diagY = c.yCoor;
            chipsInARow = 0;
            winners.clear();
            while(diagX >= 0 && diagY <= 5){
                diagX--;
                diagY++;
            }
            while(diagX < 6 && diagY > 0){
                diagX++;
                diagY--;
                if(grid[diagY][diagX].playerVal == c.playerVal){
                    chipsInARow++;
                    winners.add(grid[diagY][diagX]);
                }
                else{
                    chipsInARow = 0;
                    winners.clear();
                }
                if(chipsInARow == 4){
                    return;
                }
            }
            winners.clear();
        }

        //Scans top of board to see if there are still available spaces to play
        public void checkIfFull(){
            isFull = true;
            for(int x = 0; x < grid[0].length; x++){
                if(grid[0][x].playerVal == 0){
                    isFull = false;
                }
            }
        }
    }

    /**
     * Keeps track of the every chip's properties
     */
    private class Chip{
        int xCoor;
        int yCoor;
        int playerVal; //0=empty, 1=player1, 2=player2
        Color color;

        public Chip(int x, int y, int p, Color c){
            xCoor = x;
            yCoor = y;
            playerVal = p;
            color = c;
        }
    }
}
