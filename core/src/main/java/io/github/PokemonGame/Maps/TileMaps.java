package io.github.PokemonGame.Maps;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.PokemonGame.Types.TERRAIN;

import static io.github.PokemonGame.Types.TERRAIN.grass1;

public class TileMaps {
    //Array bidimensional que armazena a lista de Tiles de um mapa
    private Tile[][] tiles;
    private int w, h; //largura e altura respectivamente

    //texturas
    private Texture grass1Texture = new Texture("tiles/grass1.png");
    private Texture grass2Texture = new Texture("tiles/grass2.png");

    public TileMaps(int width, int height){
        w = width;h = height;

        //inicializa esse array bidimensional com o tamanho definido
        tiles = new Tile[w][h];


        //Inicializa todos os titles com a terra 1 ou 2 aleatoriamente
        for(int i =0;i<w;i++){
            for(int j =0;j<h;j++){
                if(Math.random()>0.5){
                    tiles[i][j] = new Tile(grass1);
                }else {
                    tiles[i][j] = new Tile(TERRAIN.grass2);
                }
            }
        }
    }
    //Retorna um tile em uma posição especifica
    public Tile getTile(int x, int y){
        return tiles[x][y];
    }

    public int getW() {
        return w;
    }

    public int getH() {
        return h;
    }

    public void drawMap(SpriteBatch batch){
        batch.begin();
        for(int x = 0;x<this.getW();x++){
            for(int y = 0;y<this.getH();y++){
                switch (this.getTile(x,y).getTerrain()){
                    case grass1:
                        batch.draw(grass1Texture,x*32,y*32,32,32);
                        break;
                    case grass2:
                        batch.draw(grass2Texture,x*32,y*32,32,32);
                        break;
                }
            }
        }
        batch.end();
    }
}
