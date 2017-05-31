package mainPack;

import org.lwjgl.opengl.Display;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.RawModel;
import renderEngine.Renderer;

public class Main {

	
	public static void main(String[] args) {
		
		DisplayManager.createDisplay();
		
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		
		float[] vertices = { 
				//Left bottom triangle
				-0.5f, 0.4f, 0f, 
				-0.5f, -0.5f, 0f, 
				0.5f, -0.1f, 0f, 
				//Right top triangle
				0.5f, -0.5f, 0f, 
				0.5f, 0.5f, 0f, 
				-0.5f, 0.5f, 0f
		};
		
		RawModel model = loader.loadToVAO(vertices);
		
		while(!Display.isCloseRequested()) {
			renderer.prepare();
			//game logic
			
			//render
			renderer.render(model);
			DisplayManager.updateDisplay();
			
		}
	}

}
