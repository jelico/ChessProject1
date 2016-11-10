
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class ChessProject extends JFrame implements MouseListener, MouseMotionListener {

    JLayeredPane layeredPane;
    JPanel chessBoard;
    JLabel chessPiece;
    int xAdjustment;
    int yAdjustment;
    int startX;
    int startY;
    int initialX;
    int initialY;
    JPanel panels;
    JLabel pieces;
 Boolean validMove;
 Boolean isWhiteTurn = true; //check the color turns


    public ChessProject() {
        Dimension boardSize = new Dimension(600, 600);

        //  Use a Layered Pane for this application
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        //Add a chess board to the Layered Pane
        chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout(new GridLayout(8, 8));
        chessBoard.setPreferredSize(boardSize);
        chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

        for (int i = 0; i < 64; i++) {
            JPanel square = new JPanel(new BorderLayout());
            chessBoard.add(square);

            int row = (i / 8) % 2;
            if (row == 0) {
                square.setBackground(i % 2 == 0 ? Color.white : Color.gray);
            } else {
                square.setBackground(i % 2 == 0 ? Color.gray : Color.white);
            }
        }

        // Setting up the Initial Chess board.
        for (int i = 8; i < 16; i++) {
            pieces = new JLabel(new ImageIcon("WhitePawn.png"));
            panels = (JPanel) chessBoard.getComponent(i);
            panels.add(pieces);
        }
        pieces = new JLabel(new ImageIcon("WhiteRook.png"));
        panels = (JPanel) chessBoard.getComponent(0);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteKnight.png"));
        panels = (JPanel) chessBoard.getComponent(1);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteKnight.png"));
        panels = (JPanel) chessBoard.getComponent(6);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteBishup.png"));
        panels = (JPanel) chessBoard.getComponent(2);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteBishup.png"));
        panels = (JPanel) chessBoard.getComponent(5);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteKing.png"));
        panels = (JPanel) chessBoard.getComponent(3);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteQueen.png"));
        panels = (JPanel) chessBoard.getComponent(4);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteRook.png"));
        panels = (JPanel) chessBoard.getComponent(7);
        panels.add(pieces);
        for (int i = 48; i < 56; i++) {
            pieces = new JLabel(new ImageIcon("BlackPawn.png"));
            panels = (JPanel) chessBoard.getComponent(i);
            panels.add(pieces);
        }
        pieces = new JLabel(new ImageIcon("BlackRook.png"));
        panels = (JPanel) chessBoard.getComponent(56);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackKnight.png"));
        panels = (JPanel) chessBoard.getComponent(57);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackKnight.png"));
        panels = (JPanel) chessBoard.getComponent(62);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackBishup.png"));
        panels = (JPanel) chessBoard.getComponent(58);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackBishup.png"));
        panels = (JPanel) chessBoard.getComponent(61);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackKing.png"));
        panels = (JPanel) chessBoard.getComponent(59);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackQueen.png"));
        panels = (JPanel) chessBoard.getComponent(60);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackRook.png"));
        panels = (JPanel) chessBoard.getComponent(63);
        panels.add(pieces);
    }

    private Boolean piecePresent(int x, int y) {
        Component c = chessBoard.findComponentAt(x, y);
        if (c instanceof JPanel) {
            return false;
        } else {
            return true;
        }
    }

    private void switchTurns(){
        if(validMove){
            isWhiteTurn = !isWhiteTurn;
        }
    }

    //Check if a piece is a Black piece.
     private Boolean checkWhiteOponent(int newX, int newY) { //used to check of piece and given location contains "White" in name.
        Boolean oponent;
        Component c1 = chessBoard.findComponentAt(newX, newY);
        JLabel awaitingPiece = (JLabel) c1;
        String tmp1 = awaitingPiece.getIcon().toString();
        if (((tmp1.contains("Black")) && (!tmp1.contains("King"))))  {

            oponent = true;


        } else if (((tmp1.contains("King")))) { //identifying if we've taken a King and won the game.
            isWinner("White");
            oponent = true;
        } else {
            oponent = false;
        }
        return oponent;
    }

    //Check if a piece is a White piece.
 private Boolean checkBlackOponent(int newX, int newY) { //used to check of piece and given location contains "White" in name.
        Boolean oponent;
        Component c1 = chessBoard.findComponentAt(newX, newY);
        JLabel awaitingPiece = (JLabel) c1;
        String tmp1 = awaitingPiece.getIcon().toString();
        if (((tmp1.contains("White")) && (!tmp1.contains("King")))) {

            oponent = true;


        } else if (((tmp1.contains("King")))) { //identifying if we've taken a King and won the game.
            isWinner("Black");
            oponent = true;
        } else {
            oponent = false;
        }
        return oponent;
    }
    private void isWinner(String player) { //this is to take the colour of the piece that won


        String tmp = player;
        JOptionPane.showMessageDialog(null, tmp + " is the winner " );
        NewGame();

    }

    private boolean isKingNear(int newX, int newY){
   if ((piecePresent(newX, newY + 75) && getPieceName(newX, newY + 75).contains("King")) || (piecePresent(newX, newY - 75) && getPieceName(newX, newY - 75).contains("King"))) {
      //Y Close
      return true;
   } else if ((piecePresent(newX + 75, newY) && getPieceName(newX + 75, newY).contains("King")) || (piecePresent(newX - 75, newY) && getPieceName(newX - 75, newY).contains("King"))) {
      //X Close
      return true;
   } else if ((piecePresent(newX - 75, newY + 75) && getPieceName(newX - 75, newY + 75).contains("King")) || (piecePresent(newX + 75, newY - 75) && getPieceName(newX + 75, newY - 75).contains("King"))) {
      //Square + to - X get the X and Y new movements  + to minus X direction
      return true;
   }  else if ((piecePresent(newX - 75, newY - 75) && getPieceName(newX - 75, newY - 75).contains("King")) || (piecePresent(newX + 75, newY + 75) && getPieceName(newX + 75, newY + 75).contains("King"))) {
      //Square - to + X get the new X and Y movements - to plus X direction
      return true;
   }

   return false;
}

//gets the piece name, used for the King Adjacent method

    private String getPieceName(int newX, int newY){
      Component c1 = chessBoard.findComponentAt(newX, newY);
	  if(c1 instanceof JLabel){
		JLabel awaitingPiece = (JLabel)c1;
		String tmp1 = awaitingPiece.getIcon().toString();
		return tmp1;
	  }else{
		return "";
	  }
	}

	// after user black/white has won this method will be called to start a new game

    private void NewGame(){

     JFrame frame = new ChessProject();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);



    }


    public void mousePressed(MouseEvent e) {
        chessPiece = null;
        Component c = chessBoard.findComponentAt(e.getX(), e.getY());
        if (c instanceof JPanel) {
            return;
        }

        Point parentLocation = c.getParent().getLocation();
        xAdjustment = parentLocation.x - e.getX();
        yAdjustment = parentLocation.y - e.getY();
        chessPiece = (JLabel) c;
        initialX = e.getX();
        initialY = e.getY();
        startX = (e.getX() / 75);
        startY = (e.getY() / 75);
        chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
        chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
        layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
    }

    public void mouseDragged(MouseEvent me) {
        if (chessPiece == null) {
            return;
        }
        chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
    }

    public void mouseReleased(MouseEvent e) {
        if (chessPiece == null) {
            return;
        }

        chessPiece.setVisible(false);

        Boolean success = false;

        Component c = chessBoard.findComponentAt(e.getX(), e.getY());
        String tmp = chessPiece.getIcon().toString();
        String pieceName = tmp.substring(0, (tmp.length() - 4));
        validMove = false;


        int landingX = (e.getX() / 75);
        int landingY = (e.getY() / 75);
        int xMovement = Math.abs((e.getX() / 75) - startX);
        int yMovement = Math.abs((e.getY() / 75) - startY);
        System.out.println("----------------------------------------------");
        System.out.println("The piece that is being moved is : " + pieceName);
        System.out.println("The starting coordinates are : " + "( " + startX + "," + startY + ")");
        System.out.println("The xMovement is : " + xMovement);
        System.out.println("The yMovement is : " + yMovement);
        System.out.println("The landing coordinates are : " + "( " + landingX + "," + landingY + ")");
        System.out.println("----------------------------------------------");

//Pawn Moves
//White Pawn
        if (pieceName.equals("WhitePawn")&&isWhiteTurn) {

            if (startY == 1) {

                if (((yMovement == 1) || (yMovement == 2)) && (landingY > startY) && (xMovement == 0)) {
                    if (yMovement == 2) {
                        if ((!piecePresent(e.getX(), e.getY())) && (!piecePresent(e.getX(), e.getY() - 75))) {
                            validMove = true;

                        }
                    } else {
                        if (!piecePresent(e.getX(), e.getY())) {
                            validMove = true;
                        }
                    }

                } else if ((yMovement == 1) && (landingY > startY) && (xMovement == 1)) {

                    if (piecePresent(e.getX(), e.getY())) {

                        if (checkWhiteOponent(e.getX(), e.getY())) {

                            validMove = true;
                            if (startY == 6) {

                                success = true;

                            }

                        }

                    }
                }
            } else {
                if (((yMovement == 1)) && (landingY > startY) && (xMovement == 0)) {
                    if (!piecePresent(e.getX(), e.getY())) {
                        validMove = true;
                    }
                } else if ((yMovement == 1) && (landingY > startY) && (xMovement == 1)) {

                    if (piecePresent(e.getX(), e.getY())) {

                        if (checkWhiteOponent(e.getX(), e.getY())) {

                            validMove = true;
                            if (startY == 6) {

                                success = true;

                            }

                        }

                    }
                }

            }
            switchTurns();

        } //Black Pawn
        else if (pieceName.equals("BlackPawn")&&!isWhiteTurn) {

            if (startY == 6) {

                if (((yMovement == 1) || (yMovement == 2)) && (startY > landingY) && (xMovement == 0)) {
                    if (yMovement == 2) {
                        if ((!piecePresent(e.getX(), e.getY())) && (!piecePresent(e.getX(), e.getY() + 75))) {

                            validMove = true;

//if Valid Move  is true allow pawn to move one or 2 spaces

                        }
                    } else {
                        if (!piecePresent(e.getX(), e.getY())) {
                            validMove = true;           // if there isnt a piece present do move
                        }
                    }

                } else if ((yMovement == 1) && (startY > landingY) && (xMovement == 1)) {

                    if (piecePresent(e.getX(), e.getY())) {
                        if (checkBlackOponent(e.getX(), e.getY())) {
                            validMove = true;
                            if (startY == 1) {

                                success = true;
                            }
                        }
                    }

                }
            } else {
                if (((yMovement == 1)) && (startY > landingY) && (xMovement == 0)) {
                    if (!piecePresent(e.getX(), e.getY())) {
                        validMove = true;
                    }
                } else if ((yMovement == 1) && (startY > landingY) && (xMovement == 1)) {

                    if (piecePresent(e.getX(), e.getY())) {
                        if (checkBlackOponent(e.getX(), e.getY())) {
                            validMove = true;
                            if (startY == 1) {

                                success = true;
                            }
                        }
                    }

                }

            }

            switchTurns();
        }

        //End of Pawn Moves
        //Knight Moves
        //White Knight Code
        if (pieceName.contains("WhiteKnight")&&isWhiteTurn) {

            // next we need to get the new coordinates for where the piece is being dropped.
            int newY = e.getY() / 75;
            int newX = e.getX() / 75;

            // Ensures the pieces is being put back on the board!

          //Check board
            if (((newX < 0) || (newX > 7)) || ((newY < 0) || (newY > 7))) {
                validMove = false;
            } else {

                if (((newX == startX + 1) && (newY == startY + 2)) || ((newX == startX - 1) && (newY == startY + 2)) || ((newX == startX + 2) && (newY == startY + 1)) || ((newX == startX - 2) && (newY == startY + 1)) || ((newX == startX + 1) && (newY == startY - 2)) || ((newX == startX - 1) && (newY == startY - 2)) || ((newX == startX + 2) && (newY == startY - 1)) || ((newX == startX - 2) && (newY == startY - 1))) {
                    validMove = true;

                    if (piecePresent(e.getX(), (e.getY()))) {
                        if (pieceName.contains("White")) {
                            if (checkWhiteOponent(e.getX(), e.getY())) {
                                validMove = true;
                            } else {
                                validMove = false;
                            }
                        } else {
                            if (checkBlackOponent(e.getX(), e.getY())) {
                                validMove = true;
                            } else {
                                validMove = false;
                            }
                        }
                    }
                } else {
                    validMove = false;
                }
            }
            switchTurns();
        } //Black Knight Code
        else if (pieceName.contains("BlackKnight")&&!isWhiteTurn) {

            // next we need to get the new coordinates for where the piece is being dropped.
            int newY = e.getY() / 75;
            int newX = e.getX() / 75;


            if (((newX < 0) || (newX > 7)) || ((newY < 0) || (newY > 7))) {
                validMove = false;
            } else {
                if (((newX == startX + 1) && (newY == startY + 2)) || ((newX == startX - 1) && (newY == startY + 2)) || ((newX == startX + 2) && (newY == startY + 1)) || ((newX == startX - 2) && (newY == startY + 1)) || ((newX == startX + 1) && (newY == startY - 2)) || ((newX == startX - 1) && (newY == startY - 2)) || ((newX == startX + 2) && (newY == startY - 1)) || ((newX == startX - 2) && (newY == startY - 1))) {
                    validMove = true;

                    if (piecePresent(e.getX(), (e.getY()))) {
                        if (pieceName.contains("Black")) {
                            if (checkBlackOponent(e.getX(), e.getY())) {
                                validMove = true;
                            } else {
                                validMove = false;
                            }
                        } else {
                            if (checkWhiteOponent(e.getX(), e.getY())) {
                                validMove = true;
                            } else {
                                validMove = false;
                            }
                        }
                    }
                } else {
                    validMove = false;
                }
            }
            switchTurns();
        }
        //End of Knight Code
        //Bishop Code
        //White Bishup

        if (pieceName.contains("WhiteBishup")&&isWhiteTurn) {
            int newY = e.getY() / 75;
            int newX = e.getX() / 75;
            boolean inTheWay = false;
            int distance = Math.abs(startX - newX);
            if (((newX < 0) || (newX > 7)) || ((newY < 0) || (newY > 7))) {
                validMove = false;
            } else {
                validMove = true;
                if (Math.abs(startX - newX) == Math.abs(startY - newY)) {
                    if ((startX - newX < 0) && (startY - newY < 0)) {
                        for (int i = 0; i < distance; i++) {
                            if (piecePresent((initialX + (i * 75)), (initialY + (i * 75)))) {
                                inTheWay = true;

                                //there is a piece present move wont be allowed
                            }
                        }
                    } else if ((startX - newX < 0) && (startY - newY > 0)) {
                        for (int i = 0; i < distance; i++) {
                            if (piecePresent((initialX + (i * 75)), (initialY - (i * 75)))) {
                                inTheWay = true;
                            }
                        }
                    } else if ((startX - newX > 0) && (startY - newY > 0)) {
                        for (int i = 0; i < distance; i++) {
                            if (piecePresent((initialX - (i * 75)), (initialY - (i * 75)))) {
                                inTheWay = true;
                            }
                        }
                    } else if ((startX - newX > 0) && (startY - newY < 0)) {
                        for (int i = 0; i < distance; i++) {
                            if (piecePresent((initialX - (i * 75)), (initialY + (i * 75)))) {
                                inTheWay = true;
                            }
                        }
                    }
                    if (inTheWay) {
                        validMove = false;
                    } else {
                        if (piecePresent(e.getX(), (e.getY()))) {
                            if (pieceName.contains("White")) {
                                if (checkWhiteOponent(e.getX(), e.getY())) {
                                    validMove = true;
                                } else {
                                    validMove = false;
                                }
                            } else {
                                if (checkBlackOponent(e.getX(), e.getY())) {
                                    validMove = true;
                                } else {
                                    validMove = false;
                                }
                            }
                        } else {
                            validMove = true;
                        }
                    }
                } else { // the move that is being tried is not a diagonal move...
                    validMove = false;
                }
            }
            switchTurns();
        } //Black Bishup
        else if (pieceName.contains("BlackBishup")&&!isWhiteTurn) {
            int newY = e.getY() / 75;
            int newX = e.getX() / 75;
            boolean inTheWay = false;
            int distance = Math.abs(startX - newX);
            if (((newX < 0) || (newX > 7)) || ((newY < 0) || (newY > 7))) {
                validMove = false;
            } else {
                validMove = true;
                if (Math.abs(startX - newX) == Math.abs(startY - newY)) {
                    if ((startX - newX < 0) && (startY - newY < 0)) {
                        for (int i = 0; i < distance; i++) {
                            if (piecePresent((initialX + (i * 75)), (initialY + (i * 75)))) {
                                inTheWay = true;
                            }
                        }
                    } else if ((startX - newX < 0) && (startY - newY > 0)) {
                        for (int i = 0; i < distance; i++) {
                            if (piecePresent((initialX + (i * 75)), (initialY - (i * 75)))) {
                                inTheWay = true;
                            }
                        }
                    } else if ((startX - newX > 0) && (startY - newY > 0)) {
                        for (int i = 0; i < distance; i++) {
                            if (piecePresent((initialX - (i * 75)), (initialY - (i * 75)))) {
                                inTheWay = true;
                            }
                        }
                    } else if ((startX - newX > 0) && (startY - newY < 0)) {
                        for (int i = 0; i < distance; i++) {
                            if (piecePresent((initialX - (i * 75)), (initialY + (i * 75)))) {
                                inTheWay = true;
                            }
                        }
                    }
                    if (inTheWay) {
                        validMove = false;
                    } else {
                        if (piecePresent(e.getX(), (e.getY()))) {
                            if (pieceName.contains("Black")) {
                                if (checkBlackOponent(e.getX(), e.getY())) {
                                    validMove = true;
                                } else {
                                    validMove = false;
                                }
                            } else {
                                if (checkWhiteOponent(e.getX(), e.getY())) {
                                    validMove = true;
                                } else {
                                    validMove = false;
                                }
                            }
                        } else {
                            validMove = true;
                        }
                    }
                } else { // the move that is being tried is not a diagonal move...
                    validMove = false;
                }
            }
            switchTurns();
        }
        //End of Bishup Code

        //Start of Rook code
        if (pieceName.contains("WhiteRook")&&(isWhiteTurn) || pieceName.contains("BlackRook")&&(!isWhiteTurn)) {
            Boolean intheway = false;
            if (((landingX < 0) || (landingX > 7)) || ((landingY < 0) || (landingY > 7))) {
                validMove = false;
            } else {
                if (((Math.abs(startX - landingX) != 0) && (Math.abs(startY - landingY) == 0)) || ((Math.abs(startX - landingX) == 0) && (Math.abs(landingY - startY) != 0))) {
                    if (Math.abs(startX - landingX) != 0) {
                        if (startX - landingX > 0) {
                            for (int i = 0; i < xMovement; i++) {
                                if (piecePresent(initialX - (i * 75), e.getY())) {
                                    intheway = true;
                                    break;
                                } else {
                                    intheway = false;
                                }
                            }
                        } else {
                            for (int i = 0; i < xMovement; i++) {
                                if (piecePresent(initialX + (i * 75), e.getY())) {
                                    intheway = true;
                                    break;
                                } else {
                                    intheway = false;
                                }
                            }
                        }
                    } else {
                        if (startY - landingY > 0) {
                            for (int i = 0; i < yMovement; i++) {
                                if (piecePresent(e.getX(), initialY - (i * 75))) {
                                    intheway = true;
                                    break;
                                } else {
                                    intheway = false;
                                }
                            }
                        } else {
                            for (int i = 0; i < yMovement; i++) {
                                if (piecePresent(e.getX(), initialY + (i * 75))) {
                                    intheway = true;
                                    break;
                                } else {
                                    intheway = false;
                                }
                            }
                        }
                    }
                    if (intheway) {
                        validMove = false;
                    } else {
                        if (piecePresent(e.getX(), (e.getY()))) {
                            if (pieceName.contains("White")) {
                                if (checkWhiteOponent(e.getX(), e.getY())) {
                                    validMove = true;
                                } else {
                                    validMove = false;
                                }
                            } else {
                                if (checkBlackOponent(e.getX(), e.getY())) {
                                    validMove = true;
                                } else {
                                    validMove = false;
                                }
                            }
                        } else {
                            validMove = true;
                        }
                    }
                } else {
                    validMove = false;

                }
            }
            switchTurns();
        }

        //Black Queen
        if (pieceName.contains("BlackQueen")&&!isWhiteTurn) {
            int newY = e.getY() / 75;
            int newX = e.getX() / 75;
            boolean inTheWay = false;
            int distance = Math.abs(startX - newX);

            if (((newX < 0) || (newX > 7)) || ((newY < 0) || (newY > 7))) {
                validMove = false;
            } else {

                validMove = true;

                //Check if the move is a diagonal move
                if (Math.abs(startX - newX) == Math.abs(startY - newY)) {
                    // If there are any pieces along the diagonal in the way the move cannot be made.
                    if ((startX - newX < 0) && (startY - newY < 0)) {
                        for (int i = 0; i < distance; i++) {
                            if (piecePresent((initialX + (i * 75)), (initialY + (i * 75)))) {
                                inTheWay = true;
                            }
                        }
                    } else if ((startX - newX < 0) && (startY - newY > 0)) {
                        for (int i = 0; i < distance; i++) {
                            if (piecePresent((initialX + (i * 75)), (initialY - (i * 75)))) {
                                inTheWay = true;
                            }
                        }
                    } else if ((startX - newX > 0) && (startY - newY > 0)) {
                        for (int i = 0; i < distance; i++) {
                            if (piecePresent((initialX - (i * 75)), (initialY - (i * 75)))) {
                                inTheWay = true;
                            }
                        }
                    } else if ((startX - newX > 0) && (startY - newY < 0)) {
                        for (int i = 0; i < distance; i++) {
                            if (piecePresent((initialX - (i * 75)), (initialY + (i * 75)))) {
                                inTheWay = true;
                            }
                        }
                    }
                    if (inTheWay) {
                        validMove = false;
                    } else {
                        if (piecePresent(e.getX(), (e.getY()))) {
                            if (pieceName.contains("White")) {
                                if (checkWhiteOponent(e.getX(), e.getY())) {
                                    validMove = true;
                                } else {
                                    validMove = false;
                                }
                            } else {
                                if (checkBlackOponent(e.getX(), e.getY())) {
                                    validMove = true;
                                } else {
                                    validMove = false;
                                }
                            }
                        } else {
                            validMove = true;
                        }
                    }
                } else if (((Math.abs(startX - newX) != 0) && (Math.abs(startY - newY) == 0)) || ((Math.abs(startX - newX) == 0) && (Math.abs(newY - startY) != 0))) {
                    if (Math.abs(startX - newX) != 0) {
                        //we have movement along the x axis

                        if (startX - newX > 0) {
                            //movement in the left direction....
                            for (int i = 0; i < xMovement; i++) {
                                if (piecePresent(initialX - (i * 75), e.getY())) {
                                    inTheWay = true;
                                    break;
                                } else {
                                    inTheWay = false;
                                }
                            }
                        } else {
                            for (int i = 0; i < xMovement; i++) {
                                if (piecePresent(initialX + (i * 75), e.getY())) {
                                    inTheWay = true;
                                    break;
                                } else {
                                    inTheWay = false;
                                }
                            }
                        }
                    } else {
                        //we have movement along the y axis

                        if (startY - newY > 0) {
                            //movement in the left direction....
                            for (int i = 0; i < yMovement; i++) {
                                if (piecePresent(e.getX(), initialY - (i * 75))) {
                                    inTheWay = true;
                                    break;
                                } else {
                                    inTheWay = false;
                                }
                            }
                        } else {
                            for (int i = 0; i < yMovement; i++) {
                                if (piecePresent(e.getX(), initialY + (i * 75))) {
                                    inTheWay = true;
                                    break;
                                } else {
                                    inTheWay = false;
                                }
                            }
                        }
                    }
                    if (inTheWay) {
                        validMove = false;
                    } else {
                        if (piecePresent(e.getX(), (e.getY()))) {
                            if (pieceName.contains("White")) {
                                if (checkWhiteOponent(e.getX(), e.getY())) {
                                    validMove = true;
                                } else {
                                    validMove = false;
                                }
                            } else {
                                if (checkBlackOponent(e.getX(), e.getY())) {
                                    validMove = true;
                                } else {
                                    validMove = false;
                                }
                            }
                        } else {
                            validMove = true;
                        }
                    }
                } else { // the move that is being tried is not a diagonal move...
                    validMove = false;
                }
            }
            switchTurns();

        } //End of Black Queen Code
        //White Queen
        else if (pieceName.contains("WhiteQueen")&&isWhiteTurn) {
            int newY = e.getY() / 75;
            int newX = e.getX() / 75;
            boolean inTheWay = false;
            int distance = Math.abs(startX - newX);

            if (((newX < 0) || (newX > 7)) || ((newY < 0) || (newY > 7))) {
                validMove = false;
            } else {

                validMove = true;

                //Check if the move is a diagonal move
                if (Math.abs(startX - newX) == Math.abs(startY - newY)) {
                    // If there are any pieces along the diagonal in the way the move cannot be made.
                    if ((startX - newX < 0) && (startY - newY < 0)) {
                        for (int i = 0; i < distance; i++) {
                            if (piecePresent((initialX + (i * 75)), (initialY + (i * 75)))) {
                                inTheWay = true;
                            }
                        }
                    } else if ((startX - newX < 0) && (startY - newY > 0)) {
                        for (int i = 0; i < distance; i++) {
                            if (piecePresent((initialX + (i * 75)), (initialY - (i * 75)))) {
                                inTheWay = true;
                            }
                        }
                    } else if ((startX - newX > 0) && (startY - newY > 0)) {
                        for (int i = 0; i < distance; i++) {
                            if (piecePresent((initialX - (i * 75)), (initialY - (i * 75)))) {
                                inTheWay = true;
                            }
                        }
                    } else if ((startX - newX > 0) && (startY - newY < 0)) {
                        for (int i = 0; i < distance; i++) {
                            if (piecePresent((initialX - (i * 75)), (initialY + (i * 75)))) {
                                inTheWay = true;
                            }
                        }
                    }
                    if (inTheWay) {
                        validMove = false;
                    } else {
                        if (piecePresent(e.getX(), (e.getY()))) {
                            if (pieceName.contains("Black")) {
                                if (checkBlackOponent(e.getX(), e.getY())) {
                                    validMove = true;
                                } else {
                                    validMove = false;
                                }
                            } else {
                                if (checkWhiteOponent(e.getX(), e.getY())) {
                                    validMove = true;
                                } else {
                                    validMove = false;
                                }
                            }
                        } else {
                            validMove = true;
                        }
                    }
                } else if (((Math.abs(startX - newX) != 0) && (Math.abs(startY - newY) == 0)) || ((Math.abs(startX - newX) == 0) && (Math.abs(newY - startY) != 0))) {
                    if (Math.abs(startX - newX) != 0) {
                        //we have movement along the x axis

                        if (startX - newX > 0) {
                            //movement in the left direction....
                            for (int i = 0; i < xMovement; i++) {
                                if (piecePresent(initialX - (i * 75), e.getY())) {
                                    inTheWay = true;
                                    break;
                                } else {
                                    inTheWay = false;
                                }
                            }
                        } else {
                            for (int i = 0; i < xMovement; i++) {
                                if (piecePresent(initialX + (i * 75), e.getY())) {
                                    inTheWay = true;
                                    break;
                                } else {
                                    inTheWay = false;
                                }
                            }
                        }
                    } else {
                        //we have movement along the y axis

                        if (startY - newY > 0) {
                            //movement in the left direction....
                            for (int i = 0; i < yMovement; i++) {
                                if (piecePresent(e.getX(), initialY - (i * 75))) {
                                    inTheWay = true;
                                    break;
                                } else {
                                    inTheWay = false;
                                }
                            }
                        } else {
                            for (int i = 0; i < yMovement; i++) {
                                if (piecePresent(e.getX(), initialY + (i * 75))) {
                                    inTheWay = true;
                                    break;
                                } else {
                                    inTheWay = false;
                                }
                            }
                        }
                    }
                    if (inTheWay) {
                        validMove = false;
                    } else {
                        if (piecePresent(e.getX(), (e.getY()))) {
                            if (pieceName.contains("White")) {
                                if (checkWhiteOponent(e.getX(), e.getY())) {
                                    validMove = true;
                                } else {
                                    validMove = false;
                                }
                            } else {
                                if (checkBlackOponent(e.getX(), e.getY())) {
                                    validMove = true;
                                } else {
                                    validMove = false;
                                }
                            }
                        } else {
                            validMove = true;
                        }
                    }
                } else { // the move that is being tried is not a diagonal move...
                    validMove = false;
                }
            }
            switchTurns();
        }

           //end of White Queen Code
        //White King
        if (pieceName.contains("WhiteKing")&&isWhiteTurn) {
            int newY = e.getY() / 75;
            int newX = e.getX() / 75;
            boolean inTheWay = false;
            int distance = Math.abs(startX - newX);

            if (((newX < 0) || (newX > 7)) || ((newY < 0) || (newY > 7)) || isKingNear(e.getX(), e.getY())) {
                validMove = false;
            } else {

                validMove = true;

                //Check if the move is a diagonal move
                if (((xMovement == 1) && (yMovement == 1))) {
                    // If there are any pieces along the diagonal in the way the move cannot be made.
                    if ((startX - newX < 0) && (startY - newY < 0)) {
                        for (int i = 0; i < distance; i++) {
                            if (piecePresent((initialX + (i * 75)), (initialY + (i * 75)))) {
                                inTheWay = true;
                            }
                        }
                    } else if ((startX - newX < 0) && (startY - newY > 0)) {
                        for (int i = 0; i < distance; i++) {
                            if (piecePresent((initialX + (i * 75)), (initialY - (i * 75)))) {
                                inTheWay = true;
                            }
                        }
                    } else if ((startX - newX > 0) && (startY - newY > 0)) {
                        for (int i = 0; i < distance; i++) {
                            if (piecePresent((initialX - (i * 75)), (initialY - (i * 75)))) {
                                inTheWay = true;
                            }
                        }
                    } else if ((startX - newX > 0) && (startY - newY < 0)) {
                        for (int i = 0; i < distance; i++) {
                            if (piecePresent((initialX - (i * 75)), (initialY + (i * 75)))) {
                                inTheWay = true;
                            }
                        }
                    }
                    if (inTheWay) {
                        validMove = false;
                    } else {
                        if (piecePresent(e.getX(), (e.getY()))) {
                            if (pieceName.contains("White")) {
                                if (checkWhiteOponent(e.getX(), e.getY())) {
                                    validMove = true;
                                } else {
                                    validMove = false;
                                }
                            } else {
                                if (checkWhiteOponent(e.getX(), e.getY())) {
                                    validMove = true;
                                } else {
                                    validMove = false;
                                }
                            }
                        } else {
                            validMove = true;
                        }
                    }
                } else if (((Math.abs(startX - newX) != 1) && (Math.abs(startY - newY) == 1)) || ((Math.abs(startX - newX) == 1) && (Math.abs(newY - startY) != 1))) {
                    if (Math.abs(startX - newX) != 1) {
                        //we have movement along the x axis

                        if (startX - newX > 0) {
                            //movement in the left direction....
                            for (int i = 0; i < xMovement; i++) {
                                if (piecePresent(initialX - (i * 75), e.getY())) {
                                    inTheWay = true;
                                    break;
                                } else {
                                    inTheWay = false;
                                }
                            }
                        } else {
                            for (int i = 0; i < xMovement; i++) {
                                if (piecePresent(initialX + (i * 75), e.getY())) {
                                    inTheWay = true;
                                    break;
                                } else {
                                    inTheWay = false;
                                }
                            }
                        }
                    } else {
                        //we have movement along the y axis

                        if (Math.abs(startY - newY) != 1) {
                            //movement in the left direction....
                            for (int i = 0; i < yMovement; i++) {
                                if (piecePresent(e.getX(), initialY - (i * 75))) {
                                    inTheWay = true;
                                    break;
                                } else {
                                    inTheWay = false;
                                }
                            }
                        } else {
                            for (int i = 0; i < yMovement; i++) {
                                if (piecePresent(e.getX(), initialY + (i * 75))) {
                                    inTheWay = true;
                                    break;
                                } else {
                                    inTheWay = false;
                                }
                            }
                        }
                    }
                    if (inTheWay) {
                        validMove = false;
                    } else {
                        if (piecePresent(e.getX(), (e.getY()))) {
                            if (pieceName.contains("White")) {
                                if (checkWhiteOponent(e.getX(), e.getY())) {
                                    validMove = true;
                                } else {
                                    validMove = false;
                                }
                            } else {
                                if (checkBlackOponent(e.getX(), e.getY())) {
                                    validMove = true;
                                } else {
                                    validMove = false;
                                }
                            }
                        } else {
                            validMove = true;
                        }
                    }
                } else { // the move that is being tried is not a diagonal move...
                    validMove = false;
                }
            }
switchTurns();
        } //end of White King Code
        //Black King
        else if (pieceName.contains("BlackKing")&&!isWhiteTurn) {
            int newY = e.getY() / 75;
            int newX = e.getX() / 75;
            boolean inTheWay = false;
            int distance = Math.abs(startX - newX);

            if (((newX == 0) || (newX > 7)) || ((newY < 0) || (newY > 7)) || isKingNear(e.getX(), e.getY())) {
                validMove = false;
            } else {

                validMove = true;

                //Check if the move is a diagonal move
                if (((xMovement == 1) && (yMovement == 1))) {
                    // If there are any pieces along the diagonal in the way the move cannot be made.
                    if ((startX - newX < 0) && (startY - newY < 0)) {
                        for (int i = 0; i < distance; i++) {
                            if (piecePresent((initialX + (i * 75)), (initialY + (i * 75)))) {
                                inTheWay = true;
                            }
                        }
                    } else if ((startX - newX < 0) && (startY - newY > 0)) {
                        for (int i = 0; i < distance; i++) {
                            if (piecePresent((initialX + (i * 75)), (initialY - (i * 75)))) {
                                inTheWay = true;
                            }
                        }
                    } else if ((startX - newX > 0) && (startY - newY > 0)) {
                        for (int i = 0; i < distance; i++) {
                            if (piecePresent((initialX - (i * 75)), (initialY - (i * 75)))) {
                                inTheWay = true;
                            }
                        }
                    } else if ((startX - newX > 0) && (startY - newY < 0)) {
                        for (int i = 0; i < distance; i++) {
                            if (piecePresent((initialX - (i * 75)), (initialY + (i * 75)))) {
                                inTheWay = true;
                            }
                        }
                    }
                    if (inTheWay) {
                        validMove = false;
                    } else {
                        if (piecePresent(e.getX(), (e.getY()))) {
                            if (pieceName.contains("Black")) {
                                if (checkBlackOponent(e.getX(), e.getY())) {
                                    validMove = true;
                                } else {
                                    validMove = false;
                                }
                            } else {
                                if (checkBlackOponent(e.getX(), e.getY())) {
                                    validMove = true;
                                } else {
                                    validMove = false;
                                }
                            }
                        } else {
                            validMove = true;
                        }
                    }
                } else if (((Math.abs(startX - newX) != 1) && (Math.abs(startY - newY) == 1)) || ((Math.abs(startX - newX) == 1) && (Math.abs(newY - startY) != 1))) {
                    if (Math.abs(startX - newX) != 1) {
                        //we have movement along the x axis

                        if (Math.abs(startY - newY) != 1) {
                            //movement in the left direction....
                            for (int i = 0; i < xMovement; i++) {
                                if (piecePresent(initialX - (i * 75), e.getY())) {
                                    inTheWay = true;
                                    break;
                                } else {
                                    inTheWay = false;
                                }
                            }
                        } else {
                            for (int i = 0; i < xMovement; i++) {
                                if (piecePresent(initialX + (i * 75), e.getY())) {
                                    inTheWay = true;
                                    break;
                                } else {
                                    inTheWay = false;
                                }
                            }
                        }
                    } else {
                        //we have movement along the y axis

                        if (startY - newY > 0) {
                            //movement in the left direction....
                            for (int i = 0; i < yMovement; i++) {
                                if (piecePresent(e.getX(), initialY - (i * 75))) {
                                    inTheWay = true;
                                    break;
                                } else {
                                    inTheWay = false;
                                }
                            }
                        } else {
                            for (int i = 0; i < yMovement; i++) {
                                if (piecePresent(e.getX(), initialY + (i * 75))) {
                                    inTheWay = true;
                                    break;
                                } else {
                                    inTheWay = false;
                                }
                            }
                        }
                    }
                    if (inTheWay) {
                        validMove = false;
                    } else {
                        if (piecePresent(e.getX(), (e.getY()))) {
                            if (pieceName.contains("Black")) {
                                if (checkBlackOponent(e.getX(), e.getY())) {
                                    validMove = true;
                                } else {
                                    validMove = false;
                                }
                            } else {
                                if (checkBlackOponent(e.getX(), e.getY())) {
                                    validMove = true;
                                } else {
                                    validMove = false;
                                }
                            }
                        } else {
                            validMove = true;
                        }
                    }
                } else { // the move that is being tried is not a diagonal move...
                    validMove = false;
                }
            }
switchTurns();
        }

           //end of Black King Code
//Changes to new pawn Piece and Validates Move

















        if (!validMove) {






            int location = 0;
            if (startY == 0) {


                location = startX;
            } else {
                location = (startY * 8) + startX;
            }
            String pieceLocation = pieceName + ".png";
            pieces = new JLabel(new ImageIcon(pieceLocation));
            panels = (JPanel) chessBoard.getComponent(location);
            panels.add(pieces);
        } else {
            if (success) {

                if (c instanceof JLabel) {
                    Container parent = c.getParent();
                    parent.remove(0);

                    String promoteTo;
                    do {
                        promoteTo = "Queen";

                    } while (promoteTo == null);
                    String newPiece = null;
                    int location = 0;
                    if (pieceName.contains("White")) {
                        location = 56 + (e.getX() / 75);
                        newPiece = "White" + promoteTo;
                    } else {
                        location = (e.getX() / 75);
                        newPiece = "Black" + promoteTo;
                    }

                    pieces = new JLabel(new ImageIcon(newPiece + ".png"));
                    parent = (JPanel) chessBoard.getComponent(location);
                    parent.add(pieces);
                    validate();
                    repaint();
                }
            } else {
                if (c instanceof JLabel) {
                    Container parent = c.getParent();
                    parent.remove(0);
                    parent.add(chessPiece);
                } else {
                    Container parent = (Container) c;
                    parent.add(chessPiece);
                }
                chessPiece.setVisible(true);
            }
        }
    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public static void main(String[] args) {
        JFrame frame = new ChessProject();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        JOptionPane.showMessageDialog(null, "Welcome to Jelico's chess game! \n White Starts the Game ");
    }
}
