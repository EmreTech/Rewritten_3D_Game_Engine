package engineTester;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Keyboard;
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

public class MainGameLoop {
	
	static Loader loader;
	static MasterRenderer renderer;
	public static void main(String[] args) {
		DisplayManager.createDisplay();
		
		
		loader = new Loader();
		
		RawModel model = OBJLoader.loadObjModel("tree", loader);
		TexturedModel texturedModel = new TexturedModel(model,new ModelTexture(loader.loadTexture("tree")));
		texturedModel.getTexture().setReflectivity(0);
		texturedModel.getTexture().setShineDamper(0);
		RawModel grassModel = OBJLoader.loadObjModel("grassModel", loader);
		TexturedModel texturedGrassModel = new TexturedModel(grassModel,new ModelTexture(loader.loadTexture("grassTexture")));
		texturedGrassModel.getTexture().setTransparent(true);
		RawModel bunnyModel = OBJLoader.loadObjModel("bunny", loader);
		TexturedModel texturedBunnyModel = new TexturedModel(bunnyModel,new ModelTexture(loader.loadTexture("white")));
		texturedBunnyModel.getTexture().setReflectivity(5);
		texturedBunnyModel.getTexture().setShineDamper(5);
		
		
		List<Entity> bunnies = new ArrayList<Entity>();
		Random random = new Random();
	 	float x = random.nextFloat() * 300;
		float y = 0;
		float z = random.nextFloat() * 300;
		bunnies.add(new Entity(texturedBunnyModel, new Vector3f(x,y,z),0,0,0,1));
		
		List<Entity> trees = new ArrayList<Entity>();
		for (int i = 0; i < 500; i++) {
			x = random.nextFloat() * 800;
			y = 0;
			z = random.nextFloat() * 800;
			trees.add(new Entity(texturedModel, new Vector3f(x,y,z),0,0,0,3));
		}
		
		List<Entity> grasses = new ArrayList<Entity>();
		for (int i = 0; i < 300; i++) {
			x = random.nextFloat() * 800;
			y = 0;
			z = random.nextFloat() * 800;
			grasses.add(new Entity(texturedGrassModel, new Vector3f(x,y,z), 0,0,0,1));
		}
		Light light = new Light(new Vector3f(400,300,400), new Vector3f(1,1,1));
		
		Terrain terrain = new Terrain(0,0,loader,new ModelTexture(loader.loadTexture("grass")));
		
		Camera camera = new Camera(new Vector3f(400,5,400));
		
		renderer = new MasterRenderer();
		while (!Display.isCloseRequested()) {
			camera.move();
			renderer.processTerrain(terrain);
			for (Entity bunny:bunnies) {
				bunny.increaseRotation(0, 1, 0);
				renderer.processEntity(bunny);
			}
			for (Entity tree:trees) {
				renderer.processEntity(tree);
			}
			for (Entity grass:grasses) {
				renderer.processEntity(grass);
			}
			renderer.render(light, camera);
			System.out.println(camera.getPosition() + " " + camera.getPitch() + " " + camera.getYaw());
			DisplayManager.updateDisplay();
			
			if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
				close();
			}
		}
		close();
	}
	
	public static void close() {
		renderer.cleanUp();
		loader.cleanUp();
		DisplayManager.closeDisplay();
	}
}
