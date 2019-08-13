package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

public class Camera {
	
	private Vector3f position;
	private float pitch;
	private float yaw;
	private float roll;
	
	public Camera(Vector3f position) {
		this.position = position;
	}
	
	public void move() {
		if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
			position.z-=0.35f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			position.x+=0.35f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
			position.x-=0.35f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			position.z+=0.35f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			pitch-=0.35f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
			pitch+=0.35;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			yaw-=0.35f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			yaw+=0.35f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			position.y+=0.35f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			position.y-=0.35f;
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
}
