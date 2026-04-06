package UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

import Game.Main;
import Network.FirebaseTest;

public class MainMenuUi implements Screen {
    private Texture backTexture;
    private SpriteBatch batch;
    private Stage stage;
    private Main game;
    private Table mainTable;
    private FirebaseTest test;
    private Table statsTable;
    private Texture statsBgTexture;

    public MainMenuUi(Main game, Stage stage, Skin skin) {
        this.stage = stage;
        this.test = new FirebaseTest();
        this.game = game;
        this.batch = new SpriteBatch();

        stage.clear();

        backTexture = new Texture(Gdx.files.internal("menu_items/background.jpg"));

        mainTable = new Table();
        mainTable.setFillParent(true);
        this.stage.addActor(mainTable);

        showMainMenu();
        showStats();
    }

    public void showMainMenu() {
        mainTable.clear();
        mainTable.center();

        TextButton playGameButton = new TextButton("Play Game", game.skin);
        TextButton settingButton = new TextButton("Settings", game.skin);
        TextButton networkTestButton = new TextButton("Network Test", game.skin);
        TextButton logOutButton = new TextButton("Log-out", game.skin);
        TextButton quitButton = new TextButton("Quit", game.skin);

        mainTable.add(playGameButton).width(250f).height(60f).padBottom(20f).row();
        mainTable.add(settingButton).width(250f).height(60f).padBottom(20f).row();
        mainTable.add(networkTestButton).width(250f).height(60f).padBottom(20f).row();
        mainTable.add(logOutButton).width(250f).height(60f).padBottom(20f).row();
        mainTable.add(quitButton).width(250f).height(60f);

        playGameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent e, float x, float y) {
                game.setScreen(new InviteUi(game, stage, game.skin));
            }
        });

        settingButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent e, float x, float y) {
                game.setScreen(new SettingsUi(game, stage, game.skin));
            }
        });

        networkTestButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent e, float x, float y) {
                game.setScreen(new NetworkTestUi(game, stage, game.skin));
            }
        });

        logOutButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent e, float x, float y) {
                game.setScreen(new InitialUi(game));
            }
        });

        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent e, float x, float y) {
                Gdx.app.exit();
            }
        });
    }

    public void showStats() {
        statsTable = new Table();
        statsTable.setFillParent(true);
        statsTable.top().right();

        // Dark, semi-transparent reddish-brown background for visibility
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(new Color(0.2f, 0.1f, 0.05f, 0.85f));
        pixmap.fill();
        statsBgTexture = new Texture(pixmap);
        pixmap.dispose();

        Table innerStatsBox = new Table();
        innerStatsBox.setBackground(new TextureRegionDrawable(statsBgTexture));
        innerStatsBox.pad(20f);

        Label userLabel = new Label("Username: " + game.username, game.skin);
        userLabel.setFontScale(1.5f);
        Label gameLabel = new Label("Played games: " + game.games, game.skin);
        gameLabel.setFontScale(1.5f);
        Label winLabel = new Label("Wins: " + game.wins, game.skin);
        winLabel.setFontScale(1.5f);

        innerStatsBox.add(userLabel).right().row();
        innerStatsBox.add(gameLabel).padTop(10f).right().row();
        innerStatsBox.add(winLabel).padTop(10f).right();

        statsTable.add(innerStatsBox).padTop(30f).padRight(30f);
        stage.addActor(statsTable);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Render background to absolute window size
        batch.getProjectionMatrix().setToOrtho2D(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.begin();
        batch.draw(backTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void dispose() {
        stage.dispose();
        backTexture.dispose();
        batch.dispose();
        if (statsBgTexture != null) statsBgTexture.dispose();
    }

    @Override
    public void show() {
        stage.setViewport(new FitViewport(1920, 1080));
        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

        Gdx.input.setInputProcessor(stage);

        mainTable.setFillParent(true);
        stage.addActor(mainTable);
    }

    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
}
