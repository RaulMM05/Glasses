package gestion.fct.sceneControllers;

import org.apache.commons.codec.digest.DigestUtils;

public class test {
	public static void main(String[] args) {
		String passwordActual = DigestUtils.sha256Hex("1234");
		System.out.println(passwordActual);
	}
}
