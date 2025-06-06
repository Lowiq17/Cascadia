package game.material;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


/**
 * Class which represents a tile of the game
 * @author Christophe TARATIBU
 */
public class Tile {
  private String places = new String();
  private final List<String> listAnimals = new ArrayList<>();
	private final static List<List<PeerTileToken>> startTiles = new ArrayList<>();

	/**
	 * Adds animals to the tile
	 * @param animals the animals to add
	 * @return the tile with the animals
	 */
	public Tile addAnimals(String... animals) {
    this.listAnimals.addAll(Arrays.asList(animals));
    return this;
  }

	/**
	 * Sets the place of the tile
	 * @param place	the place of the tile
	 * @return the tile with the place
	 */
	public Tile setPlace(String place) {
		this.places = place;
		return this;
  }

	/**
	 * Gets the place
	 * @return place
	 */
	public String getPlace(){
		return places;
	}

	/**
	 * Gets the list of animals
	 * @return List of animals
	 */
	public List<String> getListAnimals(){
		return listAnimals;
	}

	/**
	 * Adds the first starting tile
	 * @return the first starting tile
	 */
	public static List<PeerTileToken> addStart1(){
		List<PeerTileToken> tilesTokens1 = new ArrayList<>();
		tilesTokens1.add(new PeerTileToken(new Tile().setPlace("Ma").addAnimals("Bu","Re"), null));
		tilesTokens1.add(new PeerTileToken(new Tile().setPlace("Ri").addAnimals("Sa", "Bu"), null));
		tilesTokens1.add(new PeerTileToken(new Tile().setPlace("Pr").addAnimals("Ou", "Sa"), null));
		return tilesTokens1;
	}

	 /**
     * Adds the second starting tile
     * @return the second starting tile
     */
  public static List<PeerTileToken> addStart2() {
    List<PeerTileToken> tilesTokens2 = new ArrayList<>();
    tilesTokens2.add(new PeerTileToken(new Tile().setPlace("Mo").addAnimals("Ou", "Bu"), null));
    tilesTokens2.add(new PeerTileToken(new Tile().setPlace("Ma").addAnimals("Sa", "Ou"), null));
    tilesTokens2.add(new PeerTileToken(new Tile().setPlace("Fo").addAnimals("Wa", "Re"), null));
    return tilesTokens2;
  }


	/**
	 * Adds the third starting tile
	 * @return the third starting tile
	 */
	public static List<PeerTileToken> addStart3(){
		List<PeerTileToken> tilesTokens3 = new ArrayList<>();
		tilesTokens3.add(new PeerTileToken(new Tile().setPlace("Fo").addAnimals("Wa","Bu"),null));
		tilesTokens3.add(new PeerTileToken(new Tile().setPlace("Mo").addAnimals("Bu", "Ou"),null));
		tilesTokens3.add(new PeerTileToken(new Tile().setPlace( "Ma").addAnimals("Re", "Sa"),null));
		return tilesTokens3;

	}

	/**
	 * Adds the fourth starting tile
	 * @return the fourth starting tile
	 */
	public static List<PeerTileToken> addStart4(){
		// Quatrième tuile de départ
		List<PeerTileToken> tilesTokens4 = new ArrayList<>();
		tilesTokens4.add(new PeerTileToken(new Tile().setPlace("Ri").addAnimals("Sa","Bu"),null));
		tilesTokens4.add(new PeerTileToken(new Tile().setPlace("Fo").addAnimals( "Wa", "Ou"),null));
		tilesTokens4.add(new PeerTileToken(new Tile().setPlace("Mo").addAnimals("Re", "Bu"),null));
		return tilesTokens4;
	}


	/**
	 * Adds the fifth starting tile
	 * @return the fifth starting tile
	 */
	public static List<PeerTileToken> addStart5(){
		//Cinquième tuile de départ
		List<PeerTileToken> tilesTokens5 = new ArrayList<>();
		tilesTokens5.add(new PeerTileToken(new Tile().setPlace("Pr").addAnimals("Re","Bu"),null));
		tilesTokens5.add(new PeerTileToken(new Tile().setPlace("Ri").addAnimals("Sa", "Bu"),null));
		tilesTokens5.add(new PeerTileToken(new Tile().setPlace("Mo").addAnimals("Ou", "Wa"),null));
		return tilesTokens5;
	}


	/**
	 * Adds the starting tiles
	 * @return the list of starting tiles
	 */
	public static List<List<PeerTileToken>> startTiles(){
    startTiles.add(addStart1());
		startTiles.add(addStart2());
		startTiles.add(addStart3());
		startTiles.add(addStart4());
		startTiles.add(addStart5());
	  Collections.shuffle(startTiles);
		return startTiles;
	}	

	/**
	 * Gets the starting tiles (3 tiles)
	 * @return the starting tiles
	 */
	public static List<PeerTileToken> getStartiles(){
		var chosenTokenTile = startTiles.remove(0);
		return chosenTokenTile;
	}


	/**
	 * Exploits the csv file which contains the tiles
	 * @return the list of tiles
	 * @throws IOException if the file is not found
	 */
	public static List<Tile> ExploitCsv() throws IOException {
		List<Tile> tiles = new ArrayList<>();
		try {
		    Path path = Path.of("data/Tuiles.csv"); // Jar file
		//	Path path = Path.of("Game/data/Tuiles.csv"); // IDE
      //System.out.println(Files.exists(path) + " fichier existe");
			var dataList = Files.readAllLines(path);
			for(var line : dataList){
				String[] parts = line.split(";");
				if (parts.length >= 2) {
          Tile tile = new Tile();
					var place = parts[0].substring(0, 2);
					if (Biome.contains(Places.class, place)){
						tile.setPlace(parts[0].substring(0, 2));
					} else {
						throw new IllegalArgumentException("Place inconnue");
					}
					var animals = parts[1].split(",");
					var animal1 = animals[0].substring(0, 2);
					var animal2 = animals[1].substring(0, 2);
					if (Biome.contains(Animal.class,animal1) && Biome.contains(Animal.class,animal2)){
						tile.addAnimals(animal1);
						tile.addAnimals(animal2);
          	tiles.add(tile);
					} else {
						  throw new IllegalArgumentException("Animaux inconnus");
					} 
        }
			}	
		} catch(IOException e){
			  throw new IOException("file not found");
		}
		return tiles;
	}

	/**
	 * Shuffle the tiles
	 * @param tiles the list of tiles
	 */
	public static void shuffTiles(List<Tile> tiles){
		Objects.requireNonNull(tiles);
		Collections.shuffle(tiles);
	}
  /**
	 * Creates a bag 
	 * @param tiles List of tiles
	 * @param nbPlayers Number of players
	 * @return List of tiles
	 */
	public static ArrayList<Tile> tileBag(List<Tile> tiles, int nbPlayers){
		Objects.requireNonNull(tiles);
		var tilePool  = new ArrayList<Tile>();
		var nbTiles = 20 * nbPlayers + 3;
		var iterator = tiles.iterator();
		int count = 0;
		while (iterator.hasNext() && count < nbTiles) {
			tilePool.add(iterator.next());
			iterator.remove();
			count++;
	  }  
    return tilePool;
	}
		
}
