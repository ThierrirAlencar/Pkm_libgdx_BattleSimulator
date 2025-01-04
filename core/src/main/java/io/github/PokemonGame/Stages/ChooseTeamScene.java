package io.github.PokemonGame.Stages;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import io.github.PokemonGame.Actors.Pokemon;
import io.github.PokemonGame.Classes.PokedexController;


public class ChooseTeamScene extends ApplicationAdapter {
    //Sprite Batch
    private SpriteBatch batch;

    //Texturas
    private Texture BackgroundTexture = new Texture("Ui/PkmChooseMenu/PokemonChooseBg.png");

    //Actors and Classes
    private PokedexController dexClass = new PokedexController();

    //Skins
    public Skin textSkin = new Skin(Gdx.files.internal("Ui/BattleCore/Buttons/CustomUITextButton.json"));
    public BitmapFont font; //Font Texture
    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;

    @Override
    public void create() {

        //Configurações de Fonte
        generator = new FreeTypeFontGenerator(Gdx.files.internal("Ui/monogram.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.color = Color.BLACK;
        parameter.size = 40;
        font = generator.generateFont(parameter);

        batch = new SpriteBatch();
    }

    @Override
    public void render() {
        logic();
        draw();

    }
    public void logic(){
        int dexSize = dexClass.getDex().size();
        if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN) && dexClass.index < dexSize-1){
            dexClass.setIndex(dexClass.index+1);
            Gdx.app.log("Update Index", "- " + dexClass.getIndex());
        };
        if(Gdx.input.isKeyJustPressed(Input.Keys.UP) && dexClass.index>0){
            dexClass.setIndex(dexClass.index-1);
            Gdx.app.log("Update Index", "- " + dexClass.getIndex());
        };
    }
    public void draw(){
        batch.begin();
        batch.draw(BackgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        for (int i = 0;i<dexClass.getSelection().size();i++){
            Pokemon p = dexClass.getSelection().get(i);
            if(i != 1){
                batch.setColor(1,1,1,.5f);
            }
            batch.draw(p.getFrontTexture(),75+((i%3)*10),550-(i*150),150,150);
            batch.setColor(1,1,1,1);
        }
        font.draw(batch,"Use Arrow Keys to move and space to select",400,80,5,1,false);
        batch.end();
    }
    @Override
    public void dispose() {
        batch.dispose();
        BackgroundTexture.dispose();
    }
}
