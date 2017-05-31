package mainPack;

import org.lwjgl.opengl.Display;

import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.RawModel;
import renderEngine.Renderer;
import shaders.StaticShader;

public class Main {

	public static void main(String[] args) {

		DisplayManager.createDisplay();

		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		StaticShader shader = new StaticShader();

		float[] vertices = {
				-0.7f, 0.2f, 0.3f, 
				-0.8f, -0.8f, 0f, 
				0.5f, -0.2f, 0f, 
				0.1f, 0.7f, 0f 
				};
		int[] indices =  {
				0,1,3,3,1,2
		};
		
		float[] vertices1 = {
				-0.1f, 0.4f, 0.3f, 
				-0.1f, -0.3f, 0f, 
				0.5f, -0.3f, 0f, 
				0.4f, 0.3f, 0f 
				};
		int[] indices1 =  {
				0,1,3,3,1,2
		};

		RawModel model = loader.loadToVAO(vertices, indices);
		RawModel model2 = loader.loadToVAO(vertices1, indices1);

		while (!Display.isCloseRequested()) {
			renderer.prepare();
			// game logic

			// render
			shader.start();
			renderer.render(model);
			renderer.render(model2);
			shader.stop();
			DisplayManager.updateDisplay();

		}
		
		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}

}
