package net.mcmaker.utils.resource;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.function.Predicate;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import org.apache.commons.io.IOUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import net.minecraft.resources.FilePack;
import net.minecraft.resources.ResourcePack;
import net.minecraft.resources.ResourcePackFileNotFoundException;
import net.minecraft.resources.ResourcePackType;
import net.minecraft.util.ResourceLocation;

public class JarFilePack extends ResourcePack {

	private JarFile jarFile;

	public JarFilePack(File fileIn) {
		super(fileIn);
	}

	private JarFile getResourcePackJarFile() throws IOException {
		if (this.jarFile == null) {
			this.jarFile = new JarFile(this.file);
		}

		return this.jarFile;
	}

	protected InputStream getInputStream(String resourcePath) throws IOException {
		JarFile jarFile = this.getResourcePackJarFile();
		ZipEntry zipentry = jarFile.getEntry(resourcePath);
		if (zipentry == null) {
			throw new ResourcePackFileNotFoundException(this.file, resourcePath);
		} else {
			return jarFile.getInputStream(zipentry);
		}
	}

	public boolean resourceExists(String resourcePath) {
		try {
			return this.getResourcePackJarFile().getEntry(resourcePath) != null;
		} catch (IOException ioexception) {
			return false;
		}
	}

	public Set<String> getResourceNamespaces(ResourcePackType type) {
		JarFile jarFile;
		try {
			jarFile = this.getResourcePackJarFile();
		} catch (IOException ioexception) {
			return Collections.emptySet();
		}

		Enumeration<? extends ZipEntry> enumeration = jarFile.entries();
		Set<String> set = Sets.newHashSet();

		while (enumeration.hasMoreElements()) {
			ZipEntry zipentry = enumeration.nextElement();
			String s = zipentry.getName();
			if (s.startsWith(type.getDirectoryName() + "/")) {
				List<String> list = Lists.newArrayList(FilePack.PATH_SPLITTER.split(s));
				if (list.size() > 1) {
					String s1 = list.get(1);
					if (s1.equals(s1.toLowerCase(Locale.ROOT))) {
						set.add(s1);
					} else {
						this.onIgnoreNonLowercaseNamespace(s1);
					}
				}
			}
		}

		return set;
	}

	protected void finalize() throws Throwable {
		this.close();
		super.finalize();
	}

	public void close() {
		if (this.jarFile != null) {
			IOUtils.closeQuietly((Closeable) this.jarFile);
			this.jarFile = null;
		}

	}

	public Collection<ResourceLocation> getAllResourceLocations(ResourcePackType type, String namespaceIn,
			String pathIn, int maxDepthIn, Predicate<String> filterIn) {
		JarFile jarFile;
		try {
			jarFile = this.getResourcePackJarFile();
		} catch (IOException ioexception) {
			return Collections.emptySet();
		}

		Enumeration<? extends ZipEntry> enumeration = jarFile.entries();
		List<ResourceLocation> list = Lists.newArrayList();
		String s = type.getDirectoryName() + "/" + namespaceIn + "/";
		String s1 = s + pathIn + "/";

		while (enumeration.hasMoreElements()) {
			ZipEntry zipentry = enumeration.nextElement();
			if (!zipentry.isDirectory()) {
				String s2 = zipentry.getName();
				if (!s2.endsWith(".mcmeta") && s2.startsWith(s1)) {
					String s3 = s2.substring(s.length());
					String[] astring = s3.split("/");
					if (astring.length >= maxDepthIn + 1 && filterIn.test(astring[astring.length - 1])) {
						list.add(new ResourceLocation(namespaceIn, s3));
					}
				}
			}
		}

		return list;
	}
}