package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class Camera {

	private Vector3f position = new Vector3f(0, 0, 0);
	private float pitch;
	private float yaw;
	private float roll;
	private float vspeed;

	public Camera() {

	}

	public void move() {
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			position.z -= Math.sin(Math.toRadians(yaw) + (Math.PI / 2)) * 0.3;
			position.x -= Math.cos(Math.toRadians(yaw) + (Math.PI / 2)) * 0.3;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			position.z += Math.sin(Math.toRadians(yaw) + (Math.PI / 2)) * 0.3;
			position.x += Math.cos(Math.toRadians(yaw) + (Math.PI / 2)) * 0.3;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			position.z += Math.sin(Math.toRadians(yaw)) * 0.3;
			position.x += Math.cos(Math.toRadians(yaw)) * 0.3;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			position.z -= Math.sin(Math.toRadians(yaw)) * 0.3;
			position.x -= Math.cos(Math.toRadians(yaw)) * 0.3;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			if (position.y == 0) {
				vspeed = 3;
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			position.y -= 0.1f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
			yaw -= 0.3f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
			yaw += 0.3f;
		}

		position.y += vspeed;

		if (position.y > 0) {
			vspeed -= 0.1;
		}
		
		if (position.y < 0) {
			position.y = 0;
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
