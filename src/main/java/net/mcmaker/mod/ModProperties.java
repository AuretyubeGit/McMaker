package net.mcmaker.mod;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import com.google.common.collect.Maps;

public class ModProperties {
	
	public static final ModProperties MINECRAFT_MOD_PROPERTIES = createMinecraftModProperties();
	public Map<String, String> properties = Maps.newHashMap();
	private Class<?> mainClassObject;
	private File modFile;
	
	public ModProperties(final File modFile) {
		this.modFile = modFile;
	}
	
	public void readPropertiesFile(InputStream propertiesFileInput) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(propertiesFileInput));
		String line;
		while ((line = br.readLine()) != null) {
			if(line.contains("=")) {
				String[] propertie = line.split("=");
				String key = propertie[0];
				String value = propertie[1];
				
				if(key.equals("mainClass")) {
					properties.put("mainClass", value);
				}
				if(key.equals("modId")) {
					properties.put("modId", value);
				}
			}
		}
		br.close();
	}
	
	public void readPropertiesFile(File file) throws IOException {
		FileReader reader = new FileReader(file);
		BufferedReader br = new BufferedReader(reader);
		String line;
		while ((line = br.readLine()) != null) {
			if(line.contains("=")) {
				String[] propertie = line.split("=");
				String key = propertie[0];
				String value = propertie[1];
				
				if(key.equals("mainClass")) {
					properties.put("mainClass", value);
				}
				if(key.equals("modId")) {
					properties.put("modId", value);
				}
			}
		}
		br.close();
		reader.close();
	}
	
	private static ModProperties createMinecraftModProperties() {
		ModProperties mcProps = new ModProperties(null);
		mcProps.properties.put("mainClass", "net.minecraft.client.main.Main");
		mcProps.properties.put("modId", "minecraft");
		return mcProps;
	}

	public String getMainClasspath() {
		return properties.get("mainClass");
	}
	
	public String getModid() {
		return properties.get("modId");
	}
	
	public File getModFile() {
		return modFile;
	}

	public void setMainClassObject(Class<?> modClass) {
		this.mainClassObject = modClass;
	}
	
	public Class<?> getMainClassObject() {
		return mainClassObject;
	}
	
}