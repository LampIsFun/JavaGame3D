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
import terrains.Terrain;
import textures.ModelTexture;

public class Main {

	
	
	public static void main(String[] args) {

		
		RawModel[] model = new RawModel[100];
		TexturedModel[] staticModel = new TexturedModel[100];
		ModelTexture[] texture = new ModelTexture[100];
		Entity[] dragon = new Entity[100];
		float[] rotSpd = new float[100];
		
		DisplayManager.createDisplay();

		Loader loader = new Loader();
		
		
		
		for(int i=0;i<100;i++) {
			model[i] = OBJLoader.loadObjModel("diamond", loader);
			Random n = new Random();
			int choice = n.nextInt(6);
			switch(choice) {
			case 0: {
				staticModel[i] = new TexturedModel(model[i],new ModelTexture(loader.loadTexture("testTexture")));
				break;
			}
			case 1: {
				staticModel[i] = new TexturedModel(model[i],new ModelTexture(loader.loadTexture("testTexture2")));
				break;
			}
			case 2: {
				staticModel[i] = new TexturedModel(model[i],new ModelTexture(loader.loadTexture("testTexture3")));
				break;
			}
			case 3: {
				staticModel[i] = new TexturedModel(model[i],new ModelTexture(loader.loadTexture("testTexture4")));
				break;
			}
			case 4: {
				staticModel[i] = new TexturedModel(model[i],new ModelTexture(loader.loadTexture("testTexture5")));
				break;
			}
			case 5: {
				staticModel[i] = new TexturedModel(model[i],new ModelTexture(loader.loadTexture("testTexture6")));
				break;
			}
			case 6: {
				staticModel[i] = new TexturedModel(model[i],new ModelTexture(loader.loadTexture("testTexture6")));
				break;
			}
			
			}
			
			
			texture[i] = staticModel[i].getTexture();
			texture[i].setShineDamper(2);
			texture[i].setReflectivity(1);
			
			int rx = n.nextInt(200) - 100;
			int ry = n.nextInt(100);
			int rz = n.nextInt(200) - 250;
			rotSpd[i] = (float) n.nextInt(100) / 100;
			dragon[i] = new Entity(staticModel[i], new Vector3f(rx,ry,rz),0,0,0,7);
			dragon[i].increaseRotation(rx*n.nextInt(3), ry*n.nextInt(3), rz*n.nextInt(3));
		}


		
		
		RawModel modelLight = OBJLoader.loadObjModel("CoolHeadThing", loader);
		TexturedModel staticModelLight = new TexturedModel(modelLight,new ModelTexture(loader.loadTexture("testTexture")));
		ModelTexture textureLight = staticModelLight.getTexture();
		
		textureLight.setShineDamper(10);
		textureLight.setReflectivity(2);
		
		Entity modelSun = new Entity(staticModelLight, new Vector3f(0,0,-25),0,0,0,10);
		Light light = new Light(new Vector3f(3000,2000,2000),new Vector3f(1,1,1));
		
		Terrain terrain = new Terrain(-1,-1,loader,new ModelTexture(loader.loadTexture("grass")));
		Terrain terrain2 = new Terrain(0,-1,loader,new ModelTexture(loader.loadTexture("grass")));
		
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
				dragon[i].increaseRotation(0, rotSpd[i], rotSpd[i]);
				renderer.processEntity(in);
			}
			renderer.processTerrain(terrain);
			renderer.processTerrain(terrain2);
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
