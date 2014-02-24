import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.newdawn.slick.opengl.Texture;

public class TextureStore {
	private ArrayList<Texture> textures = new ArrayList<Texture>();
	private ArrayList<String> locations = new ArrayList<String>();

	public TextureStore() {
		init();
	}

	private void init() {
		try {
			loadTextures();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadTextures() throws IOException {
		int numberOfIds = 0;
		FileInputStream is = new FileInputStream("res/assets/textureIDs.txt");
		Scanner scan = new Scanner(is);
		scan.findInLine("= ");
		while (scan.hasNextLine()) {
			scan.findInLine("= ");
			locations.add(scan.nextLine());

			numberOfIds++;
		}

		for (String path : locations) {
			System.out.println(path);
			textures.add(TextureLib.loadTexture("textures/" + path + ".png"));
		}

		scan.close();
		is.close();
	}

	public Texture getTexture(int id) {
		return textures.get(id);
	}

	public Texture getTexture(String name) {
		for (int i = 0; i < locations.size(); i++) {
			if (locations.get(i).equalsIgnoreCase(name)) {
				return textures.get(i);
			}
		}
		return null;
	}
}
