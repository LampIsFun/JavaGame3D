package mainPack;

import java.util.Random;

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

		
		RawModel[] model = new RawModel[100];
		TexturedModel[] staticModel = new TexturedModel[100];
		ModelTexture[] texture = new ModelTexture[100];
		Entity[] dragon = new Entity[100];
		
		DisplayManager.createDisplay();

		Loader loader = new Loader();
		
		
		
		for(int i=0;i<100;i++) {
			model[i] = OBJLoader.loadObjModel("dragon", loader);
			staticModel[i] = new TexturedModel(model[i],new ModelTexture(loader.loadTexture("testTexture")));
			texture[i] = staticModel[i].getTexture();
			texture[i].setShineDamper(2);
			texture[i].setReflectivity(1);
			Random n = new Random();
			int rx = n.nextInt(100);
			int ry = n.nextInt(100);
			int rz = n.nextInt(100);
			dragon[i] = new Entity(staticModel[i], new Vector3f(rx,ry,rz),0,0,0,1);
			dragon[i].increaseRotation(rx*n.nextInt(3), ry*n.nextInt(3), rz*n.nextInt(3));
		}


		
		
		RawModel modelLight = OBJLoader.loadObjModel("CoolHeadThing", loader);
		TexturedModel staticModelLight = new TexturedModel(modelLight,new ModelTexture(loader.loadTexture("testTexture")));
		ModelTexture textureLight = staticModelLight.getTexture();
		
		textureLight.setShineDamper(10);
		textureLight.setReflectivity(2);
		
		Entity modelSun = new Entity(staticModelLight, new Vector3f(0,200,200),0,0,0,10);
		Light light = new Light(new Vector3f(0,200,200),new Vector3f(1,1,1));
		
		Camera camera = new Camera();
		camera.setPosition(-2, 5, 10);

		MasterRenderer renderer = new MasterRenderer();
		while (!Display.isCloseRequested()) {
			//dragon.increaseRotation(0.00f, 0.2f, 0.0f);
			camera.move();
			// game logic

			// render
			for(int i=0;i<100;i++) {
				Entity in = dragon[i];
				renderer.processEntity(in);
			}
			//renderer.processEntity(dragon);
			renderer.processEntity(modelSun);
			
			renderer.render(light, camera);
			DisplayManager.updateDisplay();

		}
		
		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}

}
