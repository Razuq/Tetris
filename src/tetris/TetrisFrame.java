package tetris;

import java.awt.event.ActionEvent;

import java.awt.Color;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.ActionListener;

/**
 * Creates the window for the game to run in
 */
public class TetrisFrame extends JFrame{
    private Board board;
    private JTextArea textBoard;
    public TetrisComponent tetrisComponent;

    private final static int EXTRA_WIDTH = 100;
    private final static int EXTRA_HEIGHT = 100;

    public TetrisFrame(Board board) {
	super("Tetris");
	this.board = board;


	this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	this.setLayout(new MigLayout("fill"));


	tetrisComponent = new TetrisComponent(board);
	Score scoreLabel = new Score(board);
	board.addBoardListener(tetrisComponent);
	board.addBoardListener(scoreLabel);

	this.setPreferredSize(new Dimension(tetrisComponent.getPreferredSize().width + EXTRA_WIDTH, tetrisComponent.getPreferredSize().height + EXTRA_HEIGHT));
	KeyBindings kb = new KeyBindings(tetrisComponent, board.getGame());

	this.add(tetrisComponent, "w  "+tetrisComponent.getPreferredSize().getWidth()+"!, h "+tetrisComponent.getPreferredSize().getHeight()+"!, al center top");
	this.add(scoreLabel, "TOP");
	//createTextBoard();
	createMenu();

	this.pack();
	this.setVisible(true);
    }


    /**
     * Not used anymore, but it was in the assignment so I left it
     */
    private void createTextBoard() {
	textBoard = new JTextArea(board.getHeight(), board.getWidth()); // sets the size to that of the board
	textBoard.setText(BoardToTextConverter.convertToText(board));
	this.add(textBoard, "al center top");
    }

    public void createNewGame() {
	this.board = new Board(board.getWidth(), board.getHeight());
	tetrisComponent = new TetrisComponent(board);
	board.getGame().setState(TetrisGame.States.RUN);
    }

    private void createMenu() {
	JMenuBar menuBar = new JMenuBar();
	JMenu fileMenu = new JMenu("File");
	JMenuItem exitOption = new JMenuItem("Exit", 'x');

	fileMenu.add(exitOption);
	menuBar.add(fileMenu);

	this.setJMenuBar(menuBar);

	exitOption.addActionListener(new ActionListener()
	{
	    @Override public void actionPerformed(final ActionEvent e) {
		String[] options = {"Yes", "No"};
		int answere = JOptionPane.showOptionDialog(new JFrame(), "Are you sure you want to exit?", "Exit", 0, JOptionPane.QUESTION_MESSAGE, null, options, "No");
		if (answere == 0) {
		    System.exit(0);
		}
	    }
	});
    }


}


