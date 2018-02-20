package mainPack;

import java.util.ArrayList;

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
import textures.TerrainTexture;
import textures.TerrainTexturePack;

public class Main {

	static Loader loader = new Loader();
	static double newGem;

	public static void main(String[] args) {
		DisplayManager.createDisplay();

		ArrayList<Entity> gems = new ArrayList<Entity>();
		Terrain myT;

		//TERRAIN PACK
		TerrainTexture backgroundTexture = new TerrainTexture(loader.loadTexture("grass"));
		TerrainTexture rTexture = new TerrainTexture(loader.loadTexture("grass2"));
		TerrainTexture gTexture = new TerrainTexture(loader.loadTexture("mud"));
		TerrainTexture bTexture = new TerrainTexture(loader.loadTexture("path"));

		TerrainTexturePack texturePack = new TerrainTexturePack(backgroundTexture,rTexture,gTexture,bTexture);

		TerrainTexture blendMap = new TerrainTexture(loader.loadTexture("blendMap"));

		//

		final int TREE_COUNT = 200;
		final int GRASS_COUNT = 200;
		Entity[] tree = new Entity[TREE_COUNT];
		Entity[] grass = new Entity[GRASS_COUNT];

		Terrain terrain = new Terrain(-1, -1, loader, texturePack, blendMap, "heightMap2");
		//Terrain terrain2 = new Terrain(0, -1, loader, texturePack, blendMap, "heightMap2");
		//Terrain terrain3 = new Terrain(-1, 0, loader, texturePack, blendMap, "heightMap2");
		//Terrain terrain4 = new Terrain(0, 0, loader, texturePack, blendMap, "heightMap2");

		for (int i = 0; i < TREE_COUNT; i++) {
			float newX = (float) ((Math.random() * 600) - 300);
			float newY = (float) ((Math.random() * 600) - 300);
			tree[i] = createInstance("tree01", "treeTexture01", false, false, 1f, 0f,
					new Vector3f(newX, terrain.getY(newX,newY), newY ), 0,
					(float) ((Math.random() * 360)), 0, 1);
		}

		/*for (int i = 0; i < GRASS_COUNT; i++) {
			float newX = (float) ((Math.random() * 600) - 300);
			float newY = (float) ((Math.random() * 600) - 300);
			grass[i] = createInstance("grassModel", "grassTexture", true, true, 1f, 0f,
					new Vector3f(newX, terrain.getY(newX, newY), newY), 0,
					(float) ((Math.random() * 360)), 0, 4);
		}*/

		RawModel modelLight = OBJLoader.loadObjModel("CoolHeadThing", loader);
		TexturedModel staticModelLight = new TexturedModel(modelLight,
				new ModelTexture(loader.loadTexture("testTexture1")));
		ModelTexture textureLight = staticModelLight.getTexture();

		textureLight.setShineDamper(10);
		textureLight.setReflectivity(2);

		Light light = new Light(new Vector3f(3000, 9000, 2000), new Vector3f(1, 1, 1));



		Camera camera = new Camera();
		camera.setPosition(-2, 5, 10);

		MasterRenderer renderer = new MasterRenderer();
		while (!Display.isCloseRequested()) {
			newGem = (Math.random()*100);
			// game logic
			if (newGem < 2) {
				float newX = (float) ((Math.random() * 600) - 300);
				float newY = (float) ((Math.random() * 600) - 300);
				gems.add(createInstance("diamond","testTexture"+(Math.round(Math.random()*6)+1), false, false, 1f, 0f,
						new Vector3f(newX, terrain.getY(newX,newY), newY ), 0,
						(float) ((Math.random() * 360)), 0, 1));
			}
				//get terrain currently on.
			if (camera.getPosition().getX() >= 0 && camera.getPosition().getZ() > 0) {
				myT = terrain;
			} else if (camera.getPosition().getX() <= 0 && camera.getPosition().getZ() > 0) {
				myT = terrain;
			} else if (camera.getPosition().getX() >= 0 && camera.getPosition().getZ() < 0) {
				myT = terrain;
			} else {
				myT = terrain;
			}
			camera.move(myT);
			// render
			renderer.processTerrain(terrain);
			//renderer.processTerrain(terrain2);
			//renderer.processTerrain(terrain3);
			//renderer.processTerrain(terrain4);

			for (int i = 0; i < TREE_COUNT; i++) {
				renderer.processEntity(tree[i]);
			}
			for(Entity e:gems) {
				e.setRotY(e.getRotY()+1f);
				renderer.processEntity(e);
			}
			/*for (int i = 0; i < GRASS_COUNT; i++) {
				renderer.processEntity(grass[i]);
			}*/

			renderer.render(light, camera);
			DisplayManager.updateDisplay();

		}

		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}

	static Entity createInstance(String objFile, String textureFile, boolean fakeLighting, boolean transparent, float shineDamping,
			float reflectiveness, Vector3f pos, float rotx, float roty, float rotz, float scale) {
		RawModel rm = OBJLoader.loadObjModel(objFile, loader);
		ModelTexture mt = new ModelTexture(loader.loadTexture(textureFile));
		mt.setUseFakeLighting(fakeLighting);
		mt.setHasTransparency(transparent);
		mt.setShineDamper(shineDamping);
		mt.setReflectivity(reflectiveness);
		TexturedModel tm = new TexturedModel(rm, mt);
		Entity e = new Entity(tm, pos, rotx, roty, rotz, scale);
		return e;
	}

}
