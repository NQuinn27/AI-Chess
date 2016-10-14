package com.niallquinn;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.*;
import javax.swing.*;

/*
This class can be used as a starting point for creating your Chess game project. The only piece that
has been coded is a white pawn...a lot done, more to do!
*/

public class ChessProject extends JFrame implements MouseListener, MouseMotionListener {

    private static final String PIECE_FILE_PATH =
            "/Users/niall/Developer/NCI/Artificial Intelligence/Project1/src/com/niallquinn/";

    JLayeredPane layeredPane;
    JPanel chessBoard;
    JLabel chessPiece;
    private int xAdjustment;
    private int yAdjustment;
    private int startX;
    private int startY;
    private int initialX;
    private int initialY;

    private int landingX;
    private int landingY;
    private int xMovement;
    private int yMovement;

    private boolean validMove;
    private boolean success;

    MouseEvent currentEvent;

    JPanel panels;
    JLabel pieces;


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
            if (row == 0)
                square.setBackground(i % 2 == 0 ? Color.white : Color.gray);
            else
                square.setBackground(i % 2 == 0 ? Color.gray : Color.white);
        }

        // Setting up the Initial Chess board.
        for (int i = 8; i < 16; i++) {
            pieces = new JLabel(new ImageIcon(Main.class.getResource("WhitePawn.png")));
            panels = (JPanel) chessBoard.getComponent(i);
            panels.add(pieces);
        }
        pieces = new JLabel(new ImageIcon(Main.class.getResource("WhiteRook.png")));
        panels = (JPanel) chessBoard.getComponent(0);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon(Main.class.getResource("WhiteKnight.png")));
        panels = (JPanel) chessBoard.getComponent(1);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon(Main.class.getResource("WhiteKnight.png")));
        panels = (JPanel) chessBoard.getComponent(6);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon(Main.class.getResource("WhiteBishup.png")));
        panels = (JPanel) chessBoard.getComponent(2);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon(Main.class.getResource( "WhiteBishup.png")));
        panels = (JPanel) chessBoard.getComponent(5);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon(Main.class.getResource("WhiteKing.png")));
        panels = (JPanel) chessBoard.getComponent(3);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon(Main.class.getResource("WhiteQueen.png")));
        panels = (JPanel) chessBoard.getComponent(4);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon(Main.class.getResource("WhiteRook.png")));
        panels = (JPanel) chessBoard.getComponent(7);
        panels.add(pieces);
        for (int i = 48; i < 56; i++) {
            pieces = new JLabel(new ImageIcon(Main.class.getResource("BlackPawn.png")));
            panels = (JPanel) chessBoard.getComponent(i);
            panels.add(pieces);
        }
        pieces = new JLabel(new ImageIcon(Main.class.getResource("BlackRook.png")));
        panels = (JPanel) chessBoard.getComponent(56);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon(Main.class.getResource("BlackKnight.png")));
        panels = (JPanel) chessBoard.getComponent(57);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon(Main.class.getResource("BlackKnight.png")));
        panels = (JPanel) chessBoard.getComponent(62);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon(Main.class.getResource("BlackBishup.png")));
        panels = (JPanel) chessBoard.getComponent(58);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon(Main.class.getResource("BlackBishup.png")));
        panels = (JPanel) chessBoard.getComponent(61);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon(Main.class.getResource("BlackKing.png")));
        panels = (JPanel) chessBoard.getComponent(59);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon(Main.class.getResource("BlackQueen.png")));
        panels = (JPanel) chessBoard.getComponent(60);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon(Main.class.getResource("BlackRook.png")));
        panels = (JPanel) chessBoard.getComponent(63);
        panels.add(pieces);
    }

    /*
    This method checks if there is a piece present on a particular square.
    */
    private Boolean piecePresent(int x, int y) {
        Component c = chessBoard.findComponentAt(x, y);
        if (c instanceof JPanel) {
            return false;
        } else {
            return true;
        }
    }

    private Boolean piecePresent() {
        Component c = chessBoard.findComponentAt(currentEvent.getX(), currentEvent.getY());
        if (c instanceof JPanel) {
            return false;
        } else {
            return true;
        }
    }

    /*
    This is a method to check if a piece is a Black piece.
    */
    private Boolean checkWhiteOponent(int newX, int newY) {
        return checkOpponentIs("Black", newX, newY);
    }

    private Boolean checkBlackOponent(int newX, int newY) {
        return checkOpponentIs("White", newX, newY);
    }

    private boolean checkOpponentIs(String colour, int newX, int newY) {
        Boolean oponent;
        Component c1 = chessBoard.findComponentAt(newX, newY);
        JLabel awaitingPiece = (JLabel) c1;
        String tmp1 = awaitingPiece.getIcon().toString();
        if (((tmp1.contains(colour)))) {
            oponent = true;
        } else {
            oponent = false;
        }
        return oponent;
    }

    /*
    This method is called when we press the Mouse. So we need to find out what piece we have
    selected. We may also not have selected a piece!
    */
    public void mousePressed(MouseEvent e) {
        chessPiece = null;
        Component c = chessBoard.findComponentAt(e.getX(), e.getY());
        if (c instanceof JPanel)
            return;

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
        if (chessPiece == null) return;
        chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
    }

    /*
    This method is used when the Mouse is released...we need to make sure the move was valid before
    putting the piece back on the board.
    */
    public void mouseReleased(MouseEvent e) {
        currentEvent = e;
        if (chessPiece == null) return;

        chessPiece.setVisible(false);
        success = false;
        Component c = chessBoard.findComponentAt(e.getX(), e.getY());
        String tmp = chessPiece.getIcon().toString();
        String pieceName = new File(tmp).getName();
        pieceName = pieceName.substring(0, (pieceName.length() - 4));
        validMove = false;

        System.out.println("Raw: (" + e.getX() + "," + e.getY() + ").");
        landingX = (e.getX()/75);
        landingY  = (e.getY()/75);
        xMovement = Math.abs((e.getX()/75)-startX);
        yMovement = Math.abs((e.getY()/75)-startY);

        System.out.println("----------------------------------------------");
        System.out.println("The piece that is being moved is : "+pieceName);
        System.out.println("The starting coordinates are : "+"( "+startX+","+startY+")");
        System.out.println("The xMovement is : "+xMovement);
        System.out.println("The yMovement is : "+yMovement);
        System.out.println("The landing coordinates are : "+"( "+landingX+","+landingY+")");
        System.out.println("----------------------------------------------");
    /*
    The only piece that has been enabled to move is a White Pawn...but we should really have this is a
    separate method somewhere...how would this work.

    So a Pawn is able to move two squares forward one its first go but only one square after that.
    The Pawn is the only piece that cannot move backwards in chess...so be careful when committing
    a pawn forward. A Pawn is able to take any of the opponent’s pieces but they have to be one
    square forward and one square over, i.e. in a diagonal direction from the Pawns original position.
    If a Pawn makes it to the top of the other side, the Pawn can turn into any other piece, for
    demonstration purposes the Pawn here turns into a Queen.
    */
        switch (pieceName) {
            case "WhitePawn":
                moveWhitePawn();
                break;
            case "BlackPawn":
                moveBlackPawn();
                break;
            case "BlackQueen":
                moveBlackQueen();
                break;
            case "WhiteQueen":
                moveWhiteQueen();
                break;
            default:
                validMove = false;
                break;
        }

        if (!validMove) {
            int location = 0;
            if (startY == 0) {
                location = startX;
            } else {
                location = (startY * 8) + startX;
            }
            pieces = new JLabel(new ImageIcon(Main.class.getResource(pieceName + ".png")));
            panels = (JPanel) chessBoard.getComponent(location);
            panels.add(pieces);
        } else {
            if (success) {
                int location = 56 + (e.getX() / 75);
                if (c instanceof JLabel) {
                    Container parent = c.getParent();
                    parent.remove(0);
                    pieces = new JLabel(new ImageIcon(Main.class.getResource("WhiteQueen.png")));
                    parent = (JPanel) chessBoard.getComponent(location);
                    parent.add(pieces);
                } else {
                    Container parent = (Container) c;
                    pieces = new JLabel(new ImageIcon(Main.class.getResource("WhiteQueen.png")));
                    parent = (JPanel) chessBoard.getComponent(location);
                    parent.add(pieces);
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

    /********************************************************************
     *
     * Black Queen
     *
     ********************************************************************/

    private void moveBlackQueen() {
        validMove = true;
    }

    private void moveWhiteQueen() {
        validMove = true;
    }

    /********************************************************************
     *
     * White Pawn
     *
     ********************************************************************/

    private void moveWhitePawn() {
        if (landingY < startY) {
            validMove = false;
            return;
        }
        if (startY == 1) {
            if ((yMovement == 1 || yMovement == 2) && (startY < landingY) && xMovement == 0) {
                if (yMovement == 2) {
                    if ((!piecePresent(currentEvent.getX(), (currentEvent.getY()))) && (!piecePresent(currentEvent.getX(), (currentEvent.getY() + 75)))) {
                        validMove = true;
                    } else {
                        validMove = false;
                    }
                } else {
                    if ((!piecePresent(currentEvent.getX(), (currentEvent.getY())))) {
                        validMove = true;
                    } else {
                        validMove = false;
                    }
                }
            } else {
                validMove = false;
            }
        } else {
            if ((startX - 1 >= 0) || (startX + 1 <= 7)) {
                if (piecePresent(currentEvent.getX(), currentEvent.getY()) && (xMovement == 1) && (yMovement == 1)) {
                    if (checkWhiteOponent(currentEvent.getX(), currentEvent.getY())) {
                        validMove = true;
                        if (startY == 6) {
                            success = true;
                        }
                    } else {
                        validMove = false;
                    }
                } else {
                    if (!piecePresent(currentEvent.getX(), (currentEvent.getY()))) {
                        if (xMovement == 0 && yMovement == 1) {
                            if (startY == 6) {
                                success = true;
                            }
                            validMove = true;
                        } else {
                            validMove = false;
                        }
                    } else {
                        validMove = false;
                    }
                }
            } else {
                validMove = false;
            }
        }
    }

    /********************************************************************
     *
     * Black Pawn
     *
     ********************************************************************/

    private void moveBlackPawn() {
        if (landingY > startY) {
            validMove = false;
            return;
        }
        if (startY == 6) {
            if ((yMovement == 1 || yMovement == 2) && (startY > landingY) && xMovement == 0) {
                if (yMovement == 2) {
                    if (!piecePresent(currentEvent.getX(), currentEvent.getY()) && !piecePresent(currentEvent.getX(), (currentEvent.getY() + 75))) {
                        validMove = true;
                    } else {
                        validMove = false;
                    }
                } else {
                    if ((!piecePresent(currentEvent.getX(), (currentEvent.getY())))) {
                        validMove = true;
                    } else {
                        validMove = false;
                    }
                }
            } else {
                validMove = false;
            }
        } else {
            if ((startX <= 7) || (startX - 1 == 0)) {
                if (piecePresent(currentEvent.getX(), currentEvent.getY()) && (xMovement == 1) && (yMovement == 1)) {
                    if (checkBlackOponent(currentEvent.getX(), currentEvent.getY())) {
                        validMove = true;
                        if (startY == 1) {
                            success = true;
                        }
                    } else {
                        validMove = false;
                    }
                } else {
                    if (!piecePresent(currentEvent.getX(), (currentEvent.getY()))) {
                        if (xMovement == 0 && yMovement == 1) {
                            if (startY == 1) {
                                success = true;
                            }
                            validMove = true;
                        } else {
                            validMove = false;
                        }
                    } else {
                        validMove = false;
                    }
                }
            } else {
                validMove = false;
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

}

