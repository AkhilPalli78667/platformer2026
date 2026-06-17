package platformer.code.gamelogic.player;

import java.awt.Color;
import java.awt.Graphics;

import platformer.code.gameengine.PhysicsObject;
import platformer.code.gameengine.graphics.MyGraphics;
import platformer.code.gameengine.hitbox.RectHitbox;
import platformer.code.gamelogic.Main;
import platformer.code.gamelogic.level.Level;
import platformer.code.gamelogic.tiles.SolidTile;
import platformer.code.gamelogic.tiles.Tile;

public class Player extends PhysicsObject {
	public float walkSpeed = 400;
	public float jumpPower = 1350;

	private boolean isJumping = false;

	/////////////////
	///
	/// ADDED
	private boolean canDoubleJump = false;
	private boolean jumpReleased = true;
	private boolean isFlying = false;

	public Player(float x, float y, Level level) {

		super(x, y, level.getLevelData().getTileSize(), level.getLevelData().getTileSize(), level);
		int offset = (int) (level.getLevelData().getTileSize() * 0.1); // hitbox is offset by 10% of the player size.
		this.hitbox = new RectHitbox(this, offset, offset, width - offset, height - offset);
	}

	public void changeSpeed(float n) {
		float old = 400;
		walkSpeed = n;
	}

	@Override
	public void update(float tslf) {
		super.update(tslf);

		movementVector.x = 0;
		if (PlayerInput.isLeftKeyDown()) {
			movementVector.x = -walkSpeed;
		}
		if (PlayerInput.isRightKeyDown()) {
			movementVector.x = +walkSpeed;
		}
		if (PlayerInput.isJumpKeyDown()) {

			if (!isJumping && !isFlying) {
				movementVector.y = -jumpPower;
				isJumping = true;
				jumpReleased = false;
			} 
			else if (canDoubleJump  && jumpReleased && !isFlying) {

				//System.out.println(canDoubleJump + " " + hasDoubleJumped + " " + jumpReleased + isFlying);


				//System.out.println("isJumping  "+ " =  " + isJumping);
				
				
					//System.out.println("double jumped");
				
				
System.out.println("double jumping");

				movementVector.y -= jumpPower+500;
				canDoubleJump = false;
				jumpReleased=false;
			}                                                                                                        

		} else {
			jumpReleased = true;
		}
		System.out.println(canDoubleJump +  " " + jumpReleased + " "+!isFlying);

		if(collisionMatrix[BOT] instanceof SolidTile){

			isJumping = false;
		}
	}
	///

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.YELLOW);
		MyGraphics.fillRectWithOutline(g, (int) getX(), (int) getY(), width, height);

		if (Main.DEBUGGING) {
			for (int i = 0; i < closestMatrix.length; i++) {
				Tile t = closestMatrix[i];
				if (t != null) {
					g.setColor(Color.RED);
					g.drawRect((int) t.getX(), (int) t.getY(), t.getSize(), t.getSize());
				}
			}
		}

		hitbox.draw(g);
	}

	// added
	// gas flhy
	public void fly() {
		movementVector.y = -1350;
		isFlying = true;
	}

	public void stopfly() {
		isFlying = false;
	}

	// doible jump

	public void enableDoubleJump() {
		canDoubleJump = true;
		// System.out.println("double jump ennaaabled");
	}

	public void resetDoubleJump() {
		canDoubleJump= false;
	}


}
