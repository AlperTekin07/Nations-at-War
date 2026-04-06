package UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;

import Game.Main;

public class SettingsUi implements Screen {
    private Main game;
    private Stage stage;
    private Skin skin;
    private Table mainTable;
    private Texture backTexture;
    private SpriteBatch batch;
    private Preferences prefs;

    public SettingsUi(Main game, Stage stage, Skin skin) {
        this.game = game;
        this.stage = stage;
        this.skin = game.skin;
        this.batch = new SpriteBatch();

        stage.clear();
        backTexture = new Texture(Gdx.files.internal("menu_items/background.jpg"));

        mainTable = new Table();
        mainTable.setFillParent(true);
        mainTable.center();
        stage.addActor(mainTable);

        prefs = Gdx.app.getPreferences("NationsAtWarSettings");
        buildUi();
    }

    private void buildUi() {
        mainTable.clear();
        Label titleLabel = new Label("Settings menu", skin);
        titleLabel.setFontScale(1.5f);
        mainTable.add(titleLabel).colspan(2).padBottom(50f).row();

        float savedVolume = prefs.getFloat("volume", 1.0f);
        String savedFps = prefs.getString("fps", "60");
        String savedWindowMode = prefs.getString("windowMode", "windowed");

        Label volumeLabel = new Label("Volume bar", skin);
        volumeLabel.setAlignment(Align.left);
        final Slider volumeSlider = new Slider(0f, 1f, 0.05f, false, skin);
        volumeSlider.setValue(savedVolume);

        mainTable.add(volumeLabel).width(150f).padBottom(30f);
        mainTable.add(volumeSlider).width(300f).padBottom(30f).row();

        Label fpsLabel = new Label("FPS Limiter", skin);
        fpsLabel.setAlignment(Align.left);
        final SelectBox<String> fpsBox = new SelectBox<>(skin);
        fpsBox.setItems("240", "144", "120", "60", "unlimited");
        fpsBox.setSelected(savedFps);

        mainTable.add(fpsLabel).width(150f).padBottom(30f);
        mainTable.add(fpsBox).width(300f).padBottom(30f).row();

        Label windowLabel = new Label("Window Mode", skin);
        windowLabel.setAlignment(Align.left);
        final SelectBox<String> windowBox = new SelectBox<>(skin);
        windowBox.setItems("windowed", "windowed borderless", "fullscreen");
        windowBox.setSelected(savedWindowMode);

        mainTable.add(windowLabel).width(150f).padBottom(50f);
        mainTable.add(windowBox).width(300f).padBottom(50f).row();

        Table buttonTable = new Table();
        TextButton applyButton = new TextButton("Apply", skin);
        TextButton exitButton = new TextButton("Exit", skin);

        buttonTable.add(applyButton).width(140f).padRight(20f);
        buttonTable.add(exitButton).width(140f);
        mainTable.add(buttonTable).colspan(2).padBottom(100f);

        applyButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                prefs.putFloat("volume", volumeSlider.getValue());
                prefs.putString("fps", fpsBox.getSelected());
                prefs.putString("windowMode", windowBox.getSelected());
                prefs.flush();

                String selectedFps = fpsBox.getSelected();
                switch (selectedFps) {
                    case "240": Gdx.graphics.setForegroundFPS(240); break;
                    case "144": Gdx.graphics.setForegroundFPS(144); break;
                    case "120": Gdx.graphics.setForegroundFPS(120); break;
                    case "60": Gdx.graphics.setForegroundFPS(60); break;
                    case "unlimited": Gdx.graphics.setForegroundFPS(0); break;
                }

                String selectedWindow = windowBox.getSelected();
                switch (selectedWindow) {
                    case "windowed":
                        Gdx.graphics.setWindowedMode(1280, 720);
                        break;
                    case "windowed borderless":
                        Gdx.graphics.setUndecorated(true);
                        Gdx.graphics.setWindowedMode(Gdx.graphics.getDisplayMode().width, Gdx.graphics.getDisplayMode().height);
                        break;
                    case "fullscreen":
                        Gdx.graphics.setUndecorated(false);
                        Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
                        break;
                }
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuUi(game, stage, skin));
            }
        });
    }

    @Override
    public void show() {
        stage.setViewport(new FitViewport(1920, 1080));
        stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        Gdx.input.setInputProcessor(stage);
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
        backTexture.dispose();
        batch.dispose();
    }

    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
}
