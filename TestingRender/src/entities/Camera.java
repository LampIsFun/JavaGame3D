package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

import terrains.Terrain;

public class Camera {

	private Vector3f position = new Vector3f(0, 0, 0);
	private float pitch;
	private float yaw;
	private float roll;
	private float vspeed;
	private boolean escape_down = false;
	private float GROUND = 7f;

	public Camera() {
		Mouse.setGrabbed(true);
	}

	public void move(Terrain terrain) {

		GROUND = terrain.getY(position.x, position.z) + 7.0f;
		
		if (Mouse.isGrabbed()) {
		float arg_yaw = Mouse.getDX();
		yaw += arg_yaw / 10;
		float arg_roll = Mouse.getDY();
		pitch += -(arg_roll / 10);
		}

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
			if (Math.round(position.y) == Math.round(GROUND)) {
				vspeed = 1;
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			position.y -= 0.1f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
			yaw -= 1.0f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
			yaw += 1.0f;
		}

		if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
			if (escape_down == false) {
				escape_down = true;
				Mouse.setGrabbed(!Mouse.isGrabbed());
			}
		} else {
			escape_down = false;
		}

		position.y += vspeed;

		if (position.y > GROUND) {
			vspeed -= 0.05;
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
