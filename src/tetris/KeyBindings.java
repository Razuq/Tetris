package tetris;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class KeyBindings
{

    private TetrisComponent tc;
    private TetrisGame tg;

    public KeyBindings(TetrisComponent tc, TetrisGame game) {
	this.tc = tc;
	this.tg = game;
	leftArrow();
	rightArrow();
	upArrow();
	space();

    }

    private void leftArrow() {
	Action moveLeft = new AbstractAction()
	{
	    public void actionPerformed(ActionEvent e) {
		tg.movePolyLeft();
	    }
	};

	tc.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "moveLeft");
	tc.getActionMap().put("moveLeft", moveLeft);
    }

    private void rightArrow() {
	Action moveRight = new AbstractAction()
	{
	    public void actionPerformed(ActionEvent e) {
		tg.movePolyRight();
	    }
	};

	tc.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "moveRight");
	tc.getActionMap().put("moveRight", moveRight);
    }

    private void upArrow() {
	Action rotate = new AbstractAction()
	{
	    public void actionPerformed(ActionEvent e) {
		tg.rotatePoly();
	    }
	};

	tc.getInputMap().put(KeyStroke.getKeyStroke("UP"), "rotate");
	tc.getActionMap().put("rotate", rotate);
    }

    private void space() {
	Action floorPoly = new AbstractAction()
	{
	    @Override public void actionPerformed(final ActionEvent e) {
		tg.hardDrop();
	    }
	};

	tc.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "floor");
	tc.getActionMap().put("floor", floorPoly);
    }
}
