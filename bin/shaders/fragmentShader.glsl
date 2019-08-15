#version 400 core

in vec2 pass_textureCoords;
in vec3 surfaceNormal;
in vec3 toLightVector;
in vec3 toCameraVector;
in float visibility;

out vec4 out_Color;

uniform sampler2D textureSampler;
uniform vec3 lightColor;
uniform float shineDamper;
uniform float reflectivity;
uniform vec3 skyColor;

void main(void) {

    vec3 unitNormal = normalize(surfaceNormal);
    vec3 unitLightVector = normalize(toLightVector);

    float nDotl = dot(unitNormal,unitLightVector);
    float brightness = max(nDotl,0.3);
    vec3 diffuse = brightness * lightColor;

    vec3 unitVectorToCamera = normalize(toCameraVector);
    vec3 reflectedDir = normalize(-reflect(unitVectorToCamera, unitNormal));
    float specularFactor = pow(max(dot(reflectedDir,unitLightVector),0.),shineDamper);
    vec3 finalSpecular = clamp(specularFactor * reflectivity * lightColor, 0., 1.);

    vec4 textureColor = texture(textureSampler,pass_textureCoords);
    if (textureColor.a<0.5) {
        discard;
    }

    out_Color = vec4(diffuse,1.0)* textureColor + vec4(finalSpecular,0.0);
    out_Color = mix(vec4(skyColor,1.0),out_Color, visibility);

    //out_Color.xyz = vec3(abs(finalSpecular));

}
