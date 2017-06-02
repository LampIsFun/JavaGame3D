package mainPack;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Entity;
import entities.Light;
import models.RawModel;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import textures.ModelTexture;

public class Main {

	public static void main(String[] args) {

		DisplayManager.createDisplay();

		Loader loader = new Loader();


		RawModel model = OBJLoader.loadObjModel("dragon", loader);
		TexturedModel staticModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("testTexture")));
		ModelTexture texture = staticModel.getTexture();
		texture.setShineDamper(10);
		texture.setReflectivity(1);
		Entity dragon = new Entity(staticModel, new Vector3f(0,0,-25),0,0,0,1);
		Entity entity2 = new Entity(staticModel, new Vector3f(-10,0,-30),0,0,0,1);
		Light light = new Light(new Vector3f(200,200,100),new Vector3f(1,1,1));
		
		Camera camera = new Camera();
		camera.setPosition(-2, 5, 10);

		MasterRenderer renderer = new MasterRenderer();
		while (!Display.isCloseRequested()) {
			dragon.increaseRotation(0.00f, 0.5f, 0.0f);
			entity2.increaseRotation(0.00f, 0.2f, 0.0f);
			camera.move();
			// game logic

			// render
			renderer.processEntity(dragon);
			renderer.processEntity(entity2);
			
			renderer.render(light, camera);
			DisplayManager.updateDisplay();

		}
		
		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}

}
