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
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import io.github.PokemonGame.Actors.Pokemon;
import io.github.PokemonGame.Classes.PokedexController;
import io.github.PokemonGame.Classes.TeamController;
import io.github.PokemonGame.Main;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class ChooseTeamStage extends ApplicationAdapter {
    //Sprite Batch
    private SpriteBatch batch;

    //Texturas
    private Texture BackgroundTexture = new Texture("Ui/PkmChooseMenu/PokemonChooseBg.png");

    //Actors and Classes
    private PokedexController dexClass;
    private TeamController team;
    //Skins
    public Skin textSkin = new Skin(Gdx.files.internal("Ui/BattleCore/Buttons/CustomUITextButton.json"));
    public BitmapFont font; //Font Texture
    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;


    //Parent
    private Main parent;

    public ChooseTeamStage(Main parent){
        this.parent = parent;
        this.batch = parent.batch;
        this.dexClass = parent.pokedex;
        this.team = parent.team;
    }
    @Override
    public void create() {

        //Configurações de Fonte
        generator = new FreeTypeFontGenerator(Gdx.files.internal("Ui/monogram.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.color = Color.BLACK;
        parameter.size = 40;
        font = generator.generateFont(parameter);

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
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            if(team.getTeam().size()>0){
                this.parent.setScene(new WorldRenderStage(parent,parent.batch));
            }
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)){
            Pokemon p = dexClass.getPkm(dexClass.index+1);
            team.AddToTeam(p);
            Gdx.app.log("Pokemon Added to Team", "- " + p.getName());
        };
    }
    public void draw(){
        batch.begin();

        //Dsesenha o background
        batch.draw(BackgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        //Desenha os pokemons na tela sendo eles o pokemon selecionado o anterior e o proximo
        for (int i = 0;i<dexClass.getSelection().size();i++){
            Pokemon p = dexClass.getSelection().get(i);
            //Lista tem o tamanho total de 3 começando de zero, logo 1 = metade da seleção ou seja o pokemon selecionado
            if(i != 1){
                font.setColor(1,1,1,.5f);
                batch.setColor(1,1,1,.5f);
            }
            font.draw(batch,p.getName(),200,640-(i*150));
            font.draw(batch,"Pokedex: "+p.getcIndex()+" HP:"+p.getLife(),200,600-(i*150));
            font.draw(batch,"Type " + p.getType(),200,550-(i*150));
            batch.draw(p.getFrontTexture(),30+((i%2)*30),550-(i*150),150,150);
            batch.setColor(1,1,1,1);
            font.setColor(1,1,1,1);

        }
        drawSelectedTeam();
        font.draw(batch,"Use Arrow Keys to move and space to select and enter to submit",400,80,3,1,false);
        batch.end();
    }

    public void drawSelectedTeam(){
        for (int i = 0; i<this.team.getTeam().size();i++){
            Pokemon p = this.team.getTeam().get(i);
            int x = 300;
            int y = 300+(i*80);

            switch (i){
                case 0:
                    x = 520;
                    y = 510; break;
                case 1:
                    x = 520;
                    y = 365; break;
                case 2:
                    x = 520;
                    y = 235; break;
                case 3:
                    x = 775;
                    y = 530; break;
                case 4:
                    x = 775;
                    y = 400; break;
                case 5:
                    x = 775;
                    y = 250; break;
            }

            batch.draw(p.getFrontTexture(),x,y,100,100);
            font.draw(batch,p.getName(),x+50,y+0,2,1,false);
        }
    }
    @Override
    public void dispose() {
        BackgroundTexture.dispose();
    }
}
