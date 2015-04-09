package tetris;

import java.awt.event.ActionEvent;

import java.awt.Color;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.ActionListener;

/**
 * TetrisFrame creates the window for the game to run in.
 * It also binds the keys that ae used.
 */
public class TetrisFrame extends JFrame {
    private Board board;
    private JTextArea textBoard;
    public TetrisComponent tetrisComponent;

    private final static int EXTRA_WIDTH = 15;
    private final static int EXTRA_HEIGHT = 60;

    public TetrisFrame(Board board) {
        super("Tetris");
        this.board = board;

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new MigLayout("fill, gap 0, insets 0"));
        this.getContentPane().setBackground(Color.decode("#333333"));
        tetrisComponent = new TetrisComponent(board);
        board.addBoardListener(tetrisComponent);
        this.setPreferredSize(new Dimension(tetrisComponent.getPreferredSize().width + EXTRA_WIDTH,
                                            tetrisComponent.getPreferredSize().height + EXTRA_HEIGHT));

        keyBindings(tetrisComponent);
        this.add(tetrisComponent, "w  " + tetrisComponent.getPreferredSize().getWidth() + "!, h " + tetrisComponent.getPreferredSize().getHeight() + "!, al center top");

        createMenu();

        this.pack();
        this.setVisible(true);
    }


    /**
     * Creates a text based representation of the game.
     * Not used anymore, but it was in the assignment so I left it
     */
    private void createTextBoard() {
        textBoard = new JTextArea(board.getHeight(), board.getWidth()); // sets the size to that of the board
        textBoard.setText(BoardToTextConverter.convertToText(board));
        this.add(textBoard, "al center top");
    }

    /*public void createNewGame() {
        this.board = new Board(board.getWidth(), board.getHeight());
        tetrisComponent = new TetrisComponent(board);
        board.getGame().setState(TetrisGame.States.RUN);
    }*/

    /**
     * Creates the top menu bar with the option to exit the program.
     */
    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem exitOption = new JMenuItem("Exit", 'x');

        fileMenu.add(exitOption);
        menuBar.add(fileMenu);

        this.setJMenuBar(menuBar);

        exitOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                String[] options = {"Yes", "No"};
                int answere = JOptionPane.showOptionDialog(new JFrame(), "Are you sure you want to exit?", "Exit", 0, JOptionPane.QUESTION_MESSAGE, null, options, "No");
                if (answere == 0) {
                    System.exit(0);
                }
            }
        });
    }

    /**
     * This method is used to create a new game after the player has lost.
     */
    public void newGame() {
        this.remove(tetrisComponent);
        board.removeBoardListener(tetrisComponent);
        this.board = new Board(board.getWidth(), board.getHeight());


        tetrisComponent = new TetrisComponent(board);
        keyBindings(tetrisComponent);
        tetrisComponent.setFocusable(true);
        board.addBoardListener(tetrisComponent);
        this.add(tetrisComponent);


        this.revalidate();
        tetrisComponent.requestFocusInWindow();
    }

    public Board getBoard() {
        return board;
    }


    /**
     * Binds keys for the current tetrisComponent.
     * The keys that are used are: SPACE, ENTER, LEFT, RIGHT, UP
     *
     * @param tetrisComponent the component to which to bind the keys
     */
    private void keyBindings(TetrisComponent tetrisComponent) {

        Action hardDrop = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (board.getGame().getState() == TetrisGame.States.RUN) {
                    board.getGame().hardDrop();
                }
            }
        };
        tetrisComponent.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "hardDrop");
        tetrisComponent.getActionMap().put("hardDrop", hardDrop);

        Action newGame = new AbstractAction() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (board.getGame().getState() == TetrisGame.States.GAME_OVER) {
                    newGame();
                }
            }
        };
        tetrisComponent.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "newGame");
        tetrisComponent.getActionMap().put("newGame", newGame);

        Action moveLeft = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (board.getGame().getState() == TetrisGame.States.RUN) {
                    board.getGame().movePolyLeft();
                }
            }
        };
        tetrisComponent.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "moveLeft");
        tetrisComponent.getActionMap().put("moveLeft", moveLeft);

        Action moveRight = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (board.getGame().getState() == TetrisGame.States.RUN) {
                    board.getGame().movePolyRight();
                }
            }
        };
        tetrisComponent.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "moveRight");
        tetrisComponent.getActionMap().put("moveRight", moveRight);

        Action rotate = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                if (board.getGame().getState() == TetrisGame.States.RUN)
                board.getGame().rotatePoly();
            }
        };
        tetrisComponent.getInputMap().put(KeyStroke.getKeyStroke("UP"), "rotateRight");
        tetrisComponent.getActionMap().put("rotateRight", rotate);
    }
}


