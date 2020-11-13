package net.mcmaker.resources;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.function.Consumer;
import java.util.jar.JarFile;

import net.mcmaker.utils.resource.JarFilePack;
import net.minecraft.resources.IPackFinder;
import net.minecraft.resources.IPackNameDecorator;
import net.minecraft.resources.ResourcePackInfo;
import net.minecraft.resources.ResourcePackInfo.Priority;

public class ModPackFinder implements IPackFinder {
	private static final FileFilter FILE_FILTER = (resourceFile) -> {
		boolean flag1 = resourceFile.isFile() && resourceFile.getName().endsWith(".jar");
		boolean flag2 = resourceFile.isDirectory() && (new File(resourceFile, "pack.mcmeta")).isFile();
		return flag1 || flag2;
	};
	private final File folder;
	private final IPackNameDecorator field_232610_c_;

	public ModPackFinder(File gameDir, IPackNameDecorator p_i231420_2_) {
		this.folder = new File(gameDir, "mods");
		this.field_232610_c_ = p_i231420_2_;
	}

	public void find(Consumer<ResourcePackInfo> consumer, ResourcePackInfo.IFactory factory) {
		if (!this.folder.isDirectory()) {
			this.folder.mkdirs();
		}

		File[] afile = this.folder.listFiles(FILE_FILTER);
		if (afile != null) {
			for (File file : afile) {
//				System.out.println("Mod File Name: " + file.getName());
				if (file.getAbsolutePath().contains(".jar")) {
					try(JarFile jarFile = new JarFile(file)) {
						if (jarFile.getEntry("pack.mcmeta") != null) {
							ResourcePackInfo resourcePackInfo = ResourcePackInfo.createResourcePack("mod_resources", true, () -> new JarFilePack(file), factory, Priority.TOP, this.field_232610_c_);
							if (resourcePackInfo != null) {
								consumer.accept(resourcePackInfo);
								System.out.println("ResourcePack from file " + file.getName() + " is not null");
							} else {
								System.out.println("ResourcePack from file " + file.getName() + " is  null");
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}