package net.mcmaker.mod;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.minecraft.client.Minecraft;

public class ModLoader {

	private static final ModLoader INSTANCE = new ModLoader();
	private Map<String, ModProperties> mods = Maps.newHashMap();
	public String workingModPropertiesPath;
	public static final Logger LOGGER = LogManager.getLogger("ModManager");

	public void init(Minecraft mc) {
		mods.put("minecraft", ModProperties.MINECRAFT_MOD_PROPERTIES);
		listMods(mc);
	}

	public void construcMods() {
		LOGGER.info("Initialize Mods");
		mods.forEach((modId, properties) -> {
			if (modId.equals("minecraft"))
				return;
			try {
				Class<?> modClass = properties.getMainClassObject();
				modClass.newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	private void listMods(Minecraft mc) {
		File foalder = new File(mc.gameDir, "mods");
		if (!foalder.exists()) {
			foalder.mkdir();
		}

		try (Stream<Path> walk = Files.walk(Paths.get(foalder.getAbsolutePath()))) {
			List<String> paths = walk.map(x -> x.toString()).filter(f -> f.endsWith(".jar"))
					.collect(Collectors.toList());
			
			if(workingModPropertiesPath != null) {
				File file = new File(workingModPropertiesPath);
				if (file.exists()) {
					ModProperties properties = new ModProperties(file);
					properties.readPropertiesFile(file);
					Class<?> c;
					try {
						c = Class.forName(properties.getMainClasspath());
						properties.setMainClassObject(c);
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					mods.put(properties.getModid(), properties);
				}
			}
			paths.forEach((path) -> {
				try (JarFile jarFile = new JarFile(path)) {
					ModProperties properties = new ModProperties(new File(path));
					properties.readPropertiesFile(jarFile.getInputStream(jarFile.getEntry("mod.properties")));
					Class<?> modClass = findModClass(path, jarFile, properties);
					if (!modClass.isAnnotationPresent(Mod.class)) {
						System.err.println("Mod " + jarFile.getName() + "'s main class " + modClass.getName()
								+ " not valid: Mod annotation not present");
						System.exit(0);
					} else {
						properties.setMainClassObject(modClass);
						mods.put(properties.getModid(), properties);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			LOGGER.info("////////////// Mods (" + mods.size() + ") //////////////");
			LOGGER.info("McMaker Found " + mods.size() + " Mods:");
			mods.forEach((modId, modProperties) -> LOGGER.info(modId + " Properties: {mainClass="
					+ modProperties.getMainClasspath() + ",modId=" + modProperties.getModid() + "}"));
			LOGGER.info("////////////////////////////////////");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Class<?> findModClass(String path, JarFile jarFile, ModProperties properties)
			throws MalformedURLException, ClassNotFoundException {
		Enumeration<JarEntry> e = jarFile.entries();

		URL[] urls = { new URL("jar:file:" + new File(path) + "!/") };
		URLClassLoader cl = URLClassLoader.newInstance(urls);

		while (e.hasMoreElements()) {
			JarEntry je = e.nextElement();
			if (je.isDirectory() || !je.getName().endsWith(".class")) {
				continue;
			}
			// -6 because of .class
			String className = je.getName().substring(0, je.getName().length() - 6);
			className = className.replace('/', '.');
			Class<?> c = cl.loadClass(className);
			if (properties.getMainClasspath().equals(c.getName())) {
				return c;
			}
		}
		return null;
	}

	public static ModLoader get() {
		return INSTANCE;
	}

	public Map<String, ModProperties> getLoadedMods() {
		return mods;
	}

	public String[] getModids() {
		List<String> namespaces = Lists.newArrayList();
		namespaces.add("minecraft");
		namespaces.add("realms");
		mods.forEach((modid, properties) -> {
			if (!modid.equals("minecraft") || !modid.equals("realms")) {
				namespaces.add(modid);
			}
		});
		String[] array = new String[namespaces.size()];
		return namespaces.toArray(array);
	}

	public void addWorkingModPath(String modPropertiesPath) {
		if (modPropertiesPath == null || modPropertiesPath.equalsIgnoreCase(""))
			return;
		this.workingModPropertiesPath = modPropertiesPath;
	}
}