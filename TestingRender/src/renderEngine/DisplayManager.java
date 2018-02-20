package renderEngine;

import java.awt.Dimension;
import java.awt.Toolkit;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

public class DisplayManager {
	
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static double width = screenSize.getWidth();
	static double height = screenSize.getHeight();
	private static final int WIDTH = 900;
	private static final int HEIGHT = 900;
	private static final int FPS_CAP = 120;

	public static void createDisplay() {

		ContextAttribs attribs = new ContextAttribs(3, 2)
		.withForwardCompatible(true)
		.withProfileCore(true);

		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.create(new PixelFormat(), attribs);
			Display.setTitle("Work in progress...");
			
			//glfwWindowHint(GLFW_DECORATED, GLFW_FALSE);

		} catch (LWJGLException e) {
			e.printStackTrace();
		}

		GL11.glViewport(0, 0, WIDTH, HEIGHT);
	}

	public static void updateDisplay() {
		
		Display.sync(FPS_CAP);
		Display.update();
	}

	public static void closeDisplay() {

		Display.destroy();
	}

}
