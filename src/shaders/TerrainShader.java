package shaders;
 
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import toolbox.Maths;
import entities.Camera;
import entities.Light;
 
public class TerrainShader extends ShaderProgram{
     
     
    private static final String VERTEX_FILE = "bin/shaders/terrainVertexShader.glsl";
    private static final String FRAGMENT_FILE = "bin/shaders/terrainFragmentShader.glsl";
     
    private int transformationMatrix;
    private int projectionMatrix;
    private int viewMatrix;
    private int lightPosition;
    private int lightColor;
    private int shineDamper;
    private int reflectivity;
    private int skyColor;
 
    public TerrainShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }
 
    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoordinates");
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
        skyColor = super.getUniformLocation("skyColor");
    }
    
    public void loadSkyColor(float r, float g, float b) {
		super.load3DVector(skyColor, new Vector3f(r,g,b));
	}
    
    public void loadShineVariables(float damper,float reflect){
        super.loadFloat(shineDamper, damper);
        super.loadFloat(reflectivity, reflect);
    }
     
    public void loadTransformationMatrix(Matrix4f matrix){
        super.loadMatrix(transformationMatrix, matrix);
    }
     
    public void loadLight(Light light){
        super.load3DVector(lightPosition, light.getPosition());
        super.load3DVector(lightColor, light.getColor());
    }
     
    public void loadViewMatrix(Camera camera){
        Matrix4f viewMatrixx = Maths.createViewMatrix(camera);
        super.loadMatrix(viewMatrix, viewMatrixx);
    }
     
    public void loadProjectionMatrix(Matrix4f projection){
        super.loadMatrix(projectionMatrix, projection);
    }
     
 
}
