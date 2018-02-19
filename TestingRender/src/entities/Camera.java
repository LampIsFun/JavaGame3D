package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class Camera {

	private Vector3f position = new Vector3f(0, 0, 0);
	private float pitch;
	private float yaw;
	private float roll;
	private float vspeed;
	private static final float GROUND = 7f;

	public Camera() {

	}

	public void move() {
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			position.z -= Math.sin(Math.toRadians(yaw) + (Math.PI / 2)) * 0.6;
			position.x -= Math.cos(Math.toRadians(yaw) + (Math.PI / 2)) * 0.6;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			position.z += Math.sin(Math.toRadians(yaw) + (Math.PI / 2)) * 0.6;
			position.x += Math.cos(Math.toRadians(yaw) + (Math.PI / 2)) * 0.6;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			position.z += Math.sin(Math.toRadians(yaw)) * 0.6;
			position.x += Math.cos(Math.toRadians(yaw)) * 0.6;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			position.z -= Math.sin(Math.toRadians(yaw)) * 0.6;
			position.x -= Math.cos(Math.toRadians(yaw)) * 0.6;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			if (position.y == GROUND) {
				vspeed = 3;
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			position.y -= 0.1f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
			yaw -= 0.7f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
			yaw += 0.7f;
		}

		position.y += vspeed;

		if (position.y > GROUND) {
			vspeed -= 0.1;
		}
		
		if (position.y < GROUND) {
			position.y = GROUND;
		}
	}

	public Vector3f getPosition() {
		return position;
	}

	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}

	public void setPosition(float x, float y, float z) {
		position.x = x;
		position.y = y;
		position.z = z;
	}

}
