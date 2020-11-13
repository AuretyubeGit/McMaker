package net.mcmaker.runner;

import java.net.Proxy;

import com.mojang.authlib.Agent;
import com.mojang.authlib.UserAuthentication;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;

import net.mcmaker.mod.ModLoader;
import net.minecraft.client.main.Main;

public class GameRunner {

	public static void main(String... args) {
		String assets = System.getenv().containsKey("assetDirectory") ? System.getenv("assetDirectory") : "assets";
		String modPropsPath = System.getenv().containsKey("modPropertiesPath") ? System.getenv("modPropertiesPath") : "";
		ModLoader.get().addWorkingModPath(modPropsPath);
        ArgumentList list = ArgumentList.from(args);
        list.putLazy("assetsDir", assets);
        list.put("version", "mcp");
        list.put("assetIndex", "1.16");
        list.put("userProperties", "{}");
        if(list.hasValue("email") && list.hasValue("password")) {
        	try {
				UserAuthentication auth = tryLogin(list.get("email"), list.get("password"));
				list.put("username", auth.getSelectedProfile().getName());
				list.put("uuid", auth.getSelectedProfile().getId().toString().replace("-", ""));
				list.put("accessToken", auth.getAuthenticatedToken());
		        list.put("userType", "mojang");
				list.remove("email");
				list.remove("password");
				Main.main(list.getArguments());
			} catch (AuthenticationException e) {
				e.printStackTrace();
			}
        } else {
        	Main.main(list.getArguments());
        }
	}
	
	private static UserAuthentication tryLogin(String email, String password) throws AuthenticationException {
		UserAuthentication auth = new YggdrasilUserAuthentication(new YggdrasilAuthenticationService(Proxy.NO_PROXY, "1"), Agent.MINECRAFT);
		auth.setUsername(email);
		auth.setPassword(password);
		auth.logIn();
		if(auth.isLoggedIn()) {
			return auth;
		}
		return null;
	}

}