package mainPack;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Entity;
import models.RawModel;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;

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
		
		float[] textureCoords = {
				0,0,
				0,1,
				1,1,
				1,0
		};

		RawModel model = loader.loadToVAO(vertices, textureCoords, indices);
		
		TexturedModel staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("testTexture")));
		
		Entity entity = new Entity(staticModel, new Vector3f(0,0,0),0,0,0,1);

		while (!Display.isCloseRequested()) {
			entity.increaseRotation(0.02f, 0.003f, 0.1f);
			renderer.prepare();
			// game logic

			// render
			shader.start();
			renderer.render(entity,shader);
			shader.stop();
			DisplayManager.updateDisplay();

		}
		
		shader.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}

}
