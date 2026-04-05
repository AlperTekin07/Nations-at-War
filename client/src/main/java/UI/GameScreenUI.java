package UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import Game.Main;
import Game.Player;
import UI.TroopManagement.TroopManager;


public class GameScreenUI implements Screen{
    private Table popupMenu;
    private String lastTroop = ""; 
    private Texture backTexture;
    private Image backImage;
    private Main game;
    private Stage stage;
    private Table mainTable;
    private UI.TroopManagement.TroopManager troopManage;
    private Game.Player player;

    public GameScreenUI(Main game) {
        this.player = new Player(1);
        this.game = game;
        this.stage = new Stage();
        this.troopManage = new TroopManager();

        backTexture = new Texture(Gdx.files.internal("menu_items/background.jpg"));
        backImage = new Image(backTexture);

        backImage.setFillParent(true);
        stage.addActor(backImage);

        mainTable = new Table(); 
        popupMenu = new Table();
        popupMenu.setVisible(false);
    }

    public void popupMenu(TakeVert vert) {
        popupMenu.clear();

        TextButton one = new TextButton("1", game.skin);
        TextButton two =new TextButton("2", game.skin);
        TextButton third = new TextButton("3", game.skin);

        popupMenu.addActor(one);
        popupMenu.addActor(two);
        popupMenu.addActor(third);

        one.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent e, float x, float y) {
                if (player.getId() == 1) {
                    troopManage.spawn(lastTroop, player.getId(), 20f, 600f);
                } else {
                    troopManage.spawn(lastTroop, player.getId(), 1200f, 600f);
                }
            }
        });

        two.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent e, float x, float y) {
                if (player.getId() == 1) {
                    troopManage.spawn(lastTroop, player.getId(), x, 360f);
                } else {
                    troopManage.spawn(lastTroop, player.getId(), 1200f, 600f);
                }
            }
        });

        third.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent e, float x, float y) {
                if (player.getId() == 1) {
                    troopManage.spawn(lastTroop, player.getId(), x, 120f);
                } else {
                    troopManage.spawn(lastTroop, player.getId(), 1200f, 600f);
                }
               
            }
        });
    }

    public void showTroops() {
        Table troopTable = new Table();

        ImageButton archerButton = new ImageButton(game.skin, "archer");
        ImageButton swordsmanButton = new ImageButton(game.skin, "swordsman");
        ImageButton mageButton = new ImageButton(game.skin, "mage");
        ImageButton knighButton = new ImageButton(game.skin, "knight");
        ImageButton dragonButton = new ImageButton(game.skin, "dragon");

        troopTable.add(dragonButton).padRight(10f);
        troopTable.add(archerButton).padRight(10f);
        troopTable.add(swordsmanButton).padRight(10f);
        troopTable.add(knighButton).padRight(10f);
        troopTable.add(mageButton).padRight(10f);

        archerButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent e, float x, float y) {
                troopManage.spawn("archer", player.getId(), x, y);
                lastTroop = "archer";
            }
        });

        swordsmanButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent e, float x, float y) {
                troopManage.spawn("swordsman", player.getId(), x, y);
                lastTroop = "swordsman";
            }
        });

        knighButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent e, float x, float y) {
                troopManage.spawn("knight", player.getId(), x, y);
                lastTroop = "knight";
            }
        });

        mageButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent e, float x, float y) {
                troopManage.spawn("mage", player.getId(), x, y);
                lastTroop = "mage";
            }
        });

        dragonButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent e, float x, float y) {
                troopManage.spawn("dragon", player.getId(), x, y);
                lastTroop = "dragon";
            }
        });

        mainTable.add(troopTable).top().right();
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);

        mainTable.setFillParent(true);
        stage.addActor(mainTable);
    }

    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
}
