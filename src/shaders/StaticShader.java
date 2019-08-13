package shaders;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import entities.Camera;
import entities.Light;
import toolbox.Maths;

public class StaticShader extends ShaderProgram{
	
	private static final String VERTEX_FILE = "src/shaders/vertexShader.glsl";
	private static final String FRAGMENT_FILE = "src/shaders/fragmentShader.glsl";
	
	private int transformationMatrix;
	private int projectionMatrix;
	private int viewMatrix;
	private int lightPosition;
	private int lightColor;
	private int shineDamper;
	private int reflectivity;
	private int useFakeLighting;
	private int skyColor;

	public StaticShader() {
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	@Override
	protected void bindAttributes() {
		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoords");
		super.bindAttribute(2, "normal");
	}

	@Override
	protected void getAllUniformLocations() {
		transformationMatrix = super.getUniformLocation("transformationMatrix");
		projectionMatrix = super.getUniformLocation("projectionMatrix");
		viewMatrix = super.getUniformLocation("viewMatrix");
		lightPosition = super.getUniformLocation("lightPosition");
		lightColor = super.getUniformLocation("lightColor");
		shineDamper = super.getUniformLocation("shineDamper");
		reflectivity = super.getUniformLocation("reflectivity");
		useFakeLighting = super.getUniformLocation("useFakeLighting");
		skyColor = super.getUniformLocation("skyColor");
		
	}
	
	public void loadSkyColor(float r, float g, float b) {
		super.load3DVector(skyColor, new Vector3f(r,g,b));
	}
	
	public void loadFakeLightingVariable(boolean useFake) {
		super.loadBoolean(useFakeLighting, useFake);
	}
	
	public void loadShineVariables(float damper, float reflect) {
		super.loadFloat(shineDamper, damper);
		super.loadFloat(reflectivity, reflect);
	}
	
	public void loadLight(Light light) {
		super.load3DVector(lightPosition, light.getPosition());
		super.load3DVector(lightColor, light.getColor());
	}
	
	public void loadViewMatrix(Camera cam) {
		Matrix4f matrix = Maths.createViewMatrix(cam);
		super.loadMatrix(viewMatrix, matrix);
	}
	
	public void loadProjectionMatrix(Matrix4f matrix) {
		super.loadMatrix(projectionMatrix, matrix);
	}
	
	public void loadTransformationMatrix(Matrix4f matrix) {
		super.loadMatrix(transformationMatrix, matrix);
	}
	

}
