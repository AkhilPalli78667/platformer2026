package platformer.code.gameengine.hitbox;

import java.awt.Graphics;

import javax.swing.plaf.TreeUI;

/**
 * 
 * @author Paul
 *
 */
public abstract class Hitbox {
	
	public static final boolean SHOW_HITBOXES = true; //used for debugging

	public abstract void update();
	
	public abstract void draw(Graphics graphics);
}
