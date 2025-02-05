package io.github.PokemonGame.Stages;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import io.github.PokemonGame.Actors.GymChief;
import io.github.PokemonGame.Actors.Player;
import io.github.PokemonGame.Main;

public class WorldRenderStage extends ApplicationAdapter {
    // SpriteBatch
    private SpriteBatch batch;

    // Camera
    private OrthographicCamera camera;

    // Viewport
    private StretchViewport viewport;

    //actors
    private Player player;
    private GymChief captain;

    // Stage
    private Stage stage;

    // Map render
    private TiledMap map;
    private OrthogonalTiledMapRenderer tmxRender;

    //Box 2d World Collision Detection
    private World world;
    private Box2DDebugRenderer debugRenderer;

    // Constants
    private static final int TILE_SIZE = 32;
    private static final int VISIBLE_TILES_X = 25;
    private static final int VISIBLE_TILES_Y = 18;

    //Animation
    private float elapsedTime = 0;

    private Main parent;

    public WorldRenderStage(Main parent, SpriteBatch batch) {
        this.parent = parent;
        this.batch = batch;
    }

    @Override
    public void create() {
        Gdx.app.log("Stage called", "render world demonstration");

        //Calculos muito fodas ae
        float viewportWidth = TILE_SIZE * VISIBLE_TILES_X;
        float viewportHeight = TILE_SIZE * VISIBLE_TILES_Y;


        camera = new OrthographicCamera();
        camera.setToOrtho(false, viewportWidth, viewportHeight); // Set camera size
        camera.position.set(0, 0, 0); // Center camera
        camera.update();


        //Carregar Mapa TMX
        map = new TmxMapLoader().load("Map/teste.tmx");
        tmxRender = new OrthogonalTiledMapRenderer(map);

        //Colisões e mundo BOX2d
        world = new World(new Vector2(0, 0), true);
        debugRenderer = new Box2DDebugRenderer();

        // Criando colisões a partir da layer de objetos
        createCollisionLayer("collision");

        viewport = new StretchViewport(viewportWidth, viewportHeight, camera);

        stage = new Stage(viewport, batch);

        //Atores do estágio
        player = new Player(new Texture("player/playerFront.png"),12*TILE_SIZE,30*TILE_SIZE,world);
        captain = new GymChief(880,431);

        Gdx.input.setInputProcessor(stage);
    }

    private void createCollisionLayer(String layerName){
        /*
            Um Aviso (02/01/2025)

            Não me pergunte o que isso faz (eu sei que funciona para pegar as colisões do Tiled e converter pra BOX2D)
            Deve-se entender que escrevi esse código as 3:00 da madrugada ouvindo música triste
            e pensando em seres cujo a descrição nao vem ao caso

            Compreenda por tanto thierrir do futuro que a porra desse código nao vai ser compreendida
            por você nem por ninguem da sua equipe. ChatGPT ou Deus talvez ajudem mas sem isso amigo, você se fudeu

            Deve-se entender tambem que meus esforços de documentar isso aqui de um modo decente se provaram falhos
            pois na verdade escrevi saporra tem uns 30 minutos e já não faço a menor ideia do que isso faz

            Tenha um bom dia se isso aqui der pau 😘😘

            Ps: nao questione pq karalhos tem dois FORs dentro de um while (Otimização de cu é rola)
            Ps(de uma semana depois): Removi a porra do while pq tava uma merda o fps
        */

        for (MapObject object : map.getLayers().get(layerName).getObjects()) {
            if (object instanceof RectangleMapObject) {
                Rectangle rect = ((RectangleMapObject) object).getRectangle();

                // Criando o corpo estático para colisão (talvez)
                BodyDef bodyDef = new BodyDef();
                bodyDef.type = BodyDef.BodyType.StaticBody;
                bodyDef.position.set(rect.x + rect.width / 2, rect.y + rect.height / 2);

                Body body = world.createBody(bodyDef); // Objeto Body

                PolygonShape shape = new PolygonShape();
                shape.setAsBox(rect.width / 2, rect.height / 2);

                FixtureDef fixtureDef = new FixtureDef();
                fixtureDef.shape = shape;
                fixtureDef.density = 0f;     // O objeto NÃO pode ser movido
                fixtureDef.friction = 0.8f;  // Evita deslizes ao tocar nas bordas ( ou tenta)
                fixtureDef.restitution = 0f;
                body.createFixture(fixtureDef);
                shape.dispose();
            }
        }
    }

    @Override
    public void render() {
        input();  // Primeiro processa a entrada do jogador
        world.step(1 / 60f, 6, 2); // Depois avança a física (assim as colisões são calculadas corretamente)
        elapsedTime += Gdx.graphics.getDeltaTime();
        draw();
    }
    public void draw(){
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        tmxRender.setView(camera);

        tmxRender.render();


        batch.begin();
        //Desenhar ator (captão)
        batch.draw(captain.currentTexture,captain.x,captain.y);


        //Desenhar jogador nas posições do body no mapa
        Vector2 playerPosition = player.getPlayerBody().getPosition();
        if(player.isRunning){
            batch.draw((TextureRegion) player.currentAnimation.getKeyFrame(elapsedTime, true),
                playerPosition.x, playerPosition.y);
        }else {
            batch.draw(player.currentTexture,playerPosition.x, playerPosition.y);
        }

        batch.end();
    }

//recebe comandos do teclado
public void input(){
    player.move();
    Vector2 playerPosition = player.getPlayerBody().getPosition();

    //Um monte de variaveis para calcular os limites da camera
    float mapWidth = map.getProperties().get("width", Integer.class) * TILE_SIZE;
    float mapHeight = map.getProperties().get("height", Integer.class) * TILE_SIZE;
    float cameraHalfWidth = camera.viewportWidth / 2;
    float cameraHalfHeight = camera.viewportHeight / 2;
    float cameraX = Math.max(cameraHalfWidth, Math.min(playerPosition.x, mapWidth - cameraHalfWidth));
    float cameraY = Math.max(cameraHalfHeight, Math.min(playerPosition.y, mapHeight - cameraHalfHeight));

    camera.position.set(cameraX, cameraY, 0);

    if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
        Gdx.app.postRunnable(() -> this.parent.setScene(new CombatStage(parent)));
    }
}

@Override
    public void dispose() {
        if (world != null) {
            // Remover todos os corpos antes de descartar o mundo
            world.dispose();
            world = null;
        }

        if (player.currentTexture != null) {
            player.currentTexture.dispose();
        }

        if (map != null) {
            map.dispose();
        }

        if (tmxRender != null) {
            tmxRender.dispose();
        }

        if (debugRenderer != null) {
            debugRenderer.dispose();
        }
    }
}
