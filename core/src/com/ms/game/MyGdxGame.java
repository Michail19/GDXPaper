package com.ms.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyGdxGame extends AbstractScreen {
	/**
	 * This is a main class of game
	 * Game screen
	 * <p>
	 * Created by MS
	 */

	public Texture img, img1, paperTexture, paperTexture1, gr, t1, t2, t3, t4;
	public TextureRegion playerImg, top, bottom, textureRegionProgress1, textureRegionProgress2, textureRegionProgress3;
	private TextureAtlas textureAtlas, textureA, textureB, textureAtlasProgress;
	public boolean isGameOver = false, isTouched = false, isPaused = false, n = false, pr1 = false, pr2 = false, pr3 = false, isCollectedPaper = false, isRun = false, isE1 = false, isE2 = false, isE3 = false;
	private Player player;
	private Ground ground, ground1; //, ground2, ground3;
	private Paper paper;
	private Prefs prefs;
	private ChallengePrefs challengePrefs;
	private Camera camera;
	private Background background;
	private ScrollHandler2 scroller;
	private BitmapFont font24;
	private ChallengeScreen challengeScreen;
	private Stage stage, pause;
	private int c = 0, end = 0, lastPaper, level = 0, timer = 0; //* c -, end -, lastPaper - for last paper position Y, level - game level, timer - crutch for timer
	private double progress1 = 1, progress2 = 1, progress3 = 1;
	private float lastCamX, lastCamY;
	private String[] chosenLanguage = language[getLang()];
	private final int Width = Gdx.graphics.getWidth(), Height = Gdx.graphics.getHeight();
	private String timeEnter;

    /*
    Plan:
    1) Multi-level platforms +
    2) Donates +
    3) Platforms +
    4) Screens +
    5) Shop +
    6) Player animation +
    7) Counting trees +
    8) Paper collecting +
    9) Paper on multi-level platforms +
    10) Icon and name +
    11) Moving camera +
    12) Jumping +
    13) Detailed player +
    14) Description of necessity paper recycling +
    15) Challenges +
    16) Global variables +
    17) Record +
    18) Coins +
    19) Busters and different cloth +
    20) Animations +
    21) Loading screen +
    *	  22) Adaptation for ios -
     */

//	private ShapeRenderer shapeRenderer;

	/**
	 * Main class constructor, initialization the batch resource
	 */
	public MyGdxGame() {}

	/**
	 * Initialization method
	 */
	@Override
	public void show() {
		player = new Player(200, 320, 1024, 1024);
		challengePrefs = new ChallengePrefs();
		challengeScreen = new ChallengeScreen();
		t1 = new Texture(Gdx.files.internal("magnet.png"));
		t2 = new Texture(Gdx.files.internal("x2bonus.png"));
		t3 = new Texture(Gdx.files.internal("acceleration.png"));
		t4 = new Texture(Gdx.files.internal("Coin.png"));
		textureAtlasProgress = new TextureAtlas("Loading.pack");
		textureRegionProgress1 = textureAtlasProgress.findRegion("Load1");
		textureRegionProgress2 = textureAtlasProgress.findRegion("Load1");
		textureRegionProgress3 = textureAtlasProgress.findRegion("Load1");
		paperTexture = new Texture(Gdx.files.internal("CrumplesPaperMin.png"));
		paperTexture1 = new Texture(Gdx.files.internal("CrumplesPaperMin1.png"));
		textureAtlas = new TextureAtlas("AnimationPlayer.pack");
		playerImg = textureAtlas.findRegion("Player");
		camera = new OrthographicCamera(Width, Height);
		camera.position.set(Width>>1, Height>>1, 0);
		scroller = new ScrollHandler2();
		background = scroller.getBackground();
		paper = scroller.getPaper();
		ground = scroller.getGround();
		ground1 = scroller.getGround();
//		ground2 = scroller.getGround();
//		ground3 = scroller.getGround();
		ground.setLength(5); //* Make size of 5 blocks
		font24 = new BitmapFont(Gdx.files.internal("font32S.fnt"));
		font24.getData().setScale(2f);
		lastCamX = camera.position.x;
		lastCamY = camera.position.y;
		stage = new Stage();
		prefs = new Prefs();
		chosenLanguage = language[prefs.getLang()];
		if (prefs.getBg()==0) {
			img = new Texture(Gdx.files.internal("Landscape.png"));
			img1 = new Texture(Gdx.files.internal("Landscape1.png"));
			gr = new Texture(Gdx.files.internal("Grass.png"));
		}
		else {
			img = new Texture(Gdx.files.internal("City.png"));
			img1 = new Texture(Gdx.files.internal("City1.png"));
			gr = new Texture(Gdx.files.internal("Road.png"));
		}
		stage.getCamera().viewportWidth = Width;
		stage.getCamera().viewportHeight = Height;

		if (prefs.getTop() == 1) textureA = new TextureAtlas("BlueTShirt.pack");
		else if (prefs.getTop() == 2) textureA = new TextureAtlas("Vest.pack");
		else if (prefs.getTop() == 3) textureA = new TextureAtlas("Jamper.pack");
		else if (prefs.getTop() == 4) textureA = new TextureAtlas("PinkTS.pack");
		else if (prefs.getTop() == 5) textureA = new TextureAtlas("WBTS.pack");
		else if (prefs.getTop() == 6) textureA = new TextureAtlas("BJamper.pack");
		else if (prefs.getTop() == 7) textureA = new TextureAtlas("PJamper.pack");
		else if (prefs.getTop() == 8) textureA = new TextureAtlas("Final.pack");

		if (prefs.getDown() == 1) textureB = new TextureAtlas("Shorts.pack");
		else if (prefs.getDown() == 2) textureB = new TextureAtlas("Trousers.pack");
		else if (prefs.getDown() == 3) textureB = new TextureAtlas("Jeans.pack");
		else if (prefs.getDown() == 4) textureB = new TextureAtlas("JeansE.pack");

		if (prefs.getTop() != 0) top = textureA.findRegion("T1");
		if (prefs.getDown() != 0) bottom = textureB.findRegion("S1");

		initButtons();
		Gdx.input.setInputProcessor(stage);
		stage.getRoot().getColor().a = 0;
		stage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(1.5f)));

		Date currentDate = new Date();
		DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
		timeEnter = timeFormat.format(currentDate);

		// in create method:
//		shapeRenderer = new ShapeRenderer();
//		shapeRenderer.setAutoShapeType(true);
	}

	/**
	 * Function for pause and initialization buttons
	 */
	private void initButtons() {
		Texture img = new Texture(Gdx.files.internal("pause.png"));
		final Skin skin = new Skin();
		skin.add("simpleButton", img);
		img = new Texture(Gdx.files.internal("ButtonDesign2.png"));
		skin.add("button2", img);

		ImageButton.ImageButtonStyle textButtonStyle = new ImageButton.ImageButtonStyle();
		textButtonStyle.up = skin.getDrawable("simpleButton");
		Group group = new Group();
		final ImageButton imgBtn = new ImageButton(textButtonStyle);
		imgBtn.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				isPaused = true;
				n = true;
				try {
					challengeScreen.checkConditions();
				}
				catch (Exception ignore) {}
				String[] iLanguage = challengeLang[prefs.getLang()];

				pause = new Stage();
				Group group1 = new Group();
				Texture texture = new Texture(Gdx.files.internal("ButtonDesign1.png"));
				Skin skin1 = new Skin();
				skin1.add("simple1Button", texture);
				TextButton.TextButtonStyle textButtonStyle1 = new TextButton.TextButtonStyle();
				textButtonStyle1.up = skin1.getDrawable("simple1Button");
				textButtonStyle1.font = font24;
				textButtonStyle1.fontColor = Color.FOREST;
				TextButton txtBtn1 = new TextButton(chosenLanguage[4], textButtonStyle1);
				TextButton txtBtn11 = new TextButton(chosenLanguage[5], textButtonStyle1);
				txtBtn1.addListener(new ClickListener() {
					@Override
					public void clicked(InputEvent event, float x, float y) {
						stage.getRoot().clearActions();
						isPaused = false;
						stage.getRoot().getColor().a = 0;
						stage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(1f)));
						Gdx.input.setInputProcessor(stage);
						stage.getActors().removeIndex(2);
					}
				});
				txtBtn11.addListener(new ClickListener() {
					@Override
					public void clicked(InputEvent event, float x, float y) {
						stage.getRoot().clearActions();
						player.jumpForce = 0;
						camera.position.set(Width >> 1, Height >> 1, 0);
						pause.getActors().clear();
						ScreenManager.getInstance().setScreen(ScreenManager.ScreenType.GAME_OVER, player);
					}
				});
				txtBtn1.setPosition(-(Width >> 1) - (Width >> 3), -(Height >> 2) - (Height >> 2) * 0.4f);
				txtBtn11.setPosition(-(Width >> 1) - (Width >> 3) + 25, -(Height >> 2) - (Height >> 2) * 0.85f);
				group1.addActor(txtBtn1);
				group1.addActor(txtBtn11);
				group1.setPosition(Width - (Width >> 2), Height - (Height >> 3));
				pause.addActor(group1);
				Gdx.input.setInputProcessor(pause);
				stage.getRoot().getColor().a = 1f;
				stage.addAction(Actions.sequence(Actions.fadeOut(0.5f)));

				Label.LabelStyle labelStyle = new Label.LabelStyle();
				labelStyle.background = skin.getDrawable("button2");
				labelStyle.font = font24;
				labelStyle.fontColor = Color.WHITE;
				Label label1, label2, label3;
				if (!challengePrefs.isM1()) label1 = new Label((iLanguage[0] + prefs.getCollectedPaper() + iLanguage[1] + iLanguage[3] + iLanguage[2]), labelStyle);
				else if (!challengePrefs.isM2()) label1 = new Label((iLanguage[0] + prefs.getCollectedPaper() + iLanguage[1] + iLanguage[4] + iLanguage[2]), labelStyle);
				else if (!challengePrefs.isM3()) label1 = new Label((iLanguage[0] + prefs.getCollectedPaper() + iLanguage[1] + iLanguage[5] + iLanguage[2]), labelStyle);
				else if (!challengePrefs.isM4()) label1 = new Label((iLanguage[0] + prefs.getCollectedPaper() + iLanguage[1] + iLanguage[6] + iLanguage[2]), labelStyle);
				else if (!challengePrefs.isM5()) label1 = new Label((iLanguage[0] + prefs.getCollectedPaper() + iLanguage[1] + iLanguage[7] + iLanguage[2]), labelStyle);
				else if (!challengePrefs.isM6()) label1 = new Label((iLanguage[0] + prefs.getCollectedPaper() + iLanguage[1] + iLanguage[8] + iLanguage[2]), labelStyle);
				else label1 = new Label(chosenLanguage[19], labelStyle);

				if (!challengePrefs.isM7()) label2 = new Label((iLanguage[9] + prefs.getUsedBusters() + iLanguage[1] + iLanguage[11] + iLanguage[10]), labelStyle);
				else if (!challengePrefs.isM8()) label2 = new Label((iLanguage[9] + prefs.getUsedBusters() + iLanguage[1] + iLanguage[12] + iLanguage[10]), labelStyle);
				else if (!challengePrefs.isM9()) label2 = new Label((iLanguage[9] + prefs.getUsedBusters() + iLanguage[1] + iLanguage[13] + iLanguage[10]), labelStyle);
				else if (!challengePrefs.isM10()) label2 = new Label((iLanguage[9] + prefs.getUsedBusters() + iLanguage[1] + iLanguage[14] + iLanguage[10]), labelStyle);
				else if (!challengePrefs.isM11()) label2 = new Label((iLanguage[9] + prefs.getUsedBusters() + iLanguage[1] + iLanguage[15] + iLanguage[10]), labelStyle);
				else if (!challengePrefs.isM12()) label2 = new Label((iLanguage[9] + prefs.getUsedBusters() + iLanguage[1] + iLanguage[16] + iLanguage[10]), labelStyle);
				else label2 = new Label(chosenLanguage[19], labelStyle);

				if (!challengePrefs.isM13()) label3 = new Label((iLanguage[17] + prefs.getJumps() + iLanguage[1] + iLanguage[3] + iLanguage[18]), labelStyle);
				else if (!challengePrefs.isM14()) label3 = new Label((iLanguage[17] + prefs.getJumps() + iLanguage[1] + iLanguage[4] + iLanguage[18]), labelStyle);
				else if (!challengePrefs.isM15()) label3 = new Label((iLanguage[17] + prefs.getJumps() + iLanguage[1] + iLanguage[5] + iLanguage[18]), labelStyle);
				else if (!challengePrefs.isM16()) label3 = new Label((iLanguage[17] + prefs.getJumps() + iLanguage[1] + iLanguage[6] + iLanguage[18]), labelStyle);
				else if (!challengePrefs.isM17()) label3 = new Label((iLanguage[17] + prefs.getJumps() + iLanguage[1] + iLanguage[7] + iLanguage[18]), labelStyle);
				else if (!challengePrefs.isM18()) label3 = new Label((iLanguage[17] + prefs.getJumps() + iLanguage[1] + iLanguage[8] + iLanguage[18]), labelStyle);
				else label3 = new Label(chosenLanguage[19], labelStyle);

				label1.setAlignment(Align.center);
				label2.setAlignment(Align.center);
				label3.setAlignment(Align.center);

				label1.setWidth(Width-700);
				label2.setWidth(Width-700);
				label3.setWidth(Width-700);

				label1.setPosition(680, Height-400);
				label2.setPosition(680, Height-510);
				label3.setPosition(680, Height-620);

				pause.addActor(label1);
				pause.addActor(label2);
				pause.addActor(label3);

				stage.addActor(pause.getRoot());
			}
		}); //* Event listener
		imgBtn.setPosition(-100, -50);
		group.addActor(imgBtn);
		group.setPosition(Width - (Width >> 25), Height - (Height >> 5));
		stage.addActor(group);
		stage.getRoot().getColor().a = 0;
		useBuster();
	}

	/**
	 * Function for using and initialization busters
	 */
	private void useBuster() {
		final Group group1 = new Group();
		Skin skin = new Skin();
		Texture img = new Texture(Gdx.files.internal("accelerationR.png"));
		skin.add("acceleration", img);
		img = new Texture(Gdx.files.internal("x2bonusR.png"));
		skin.add("x2bonus", img);
		img = new Texture(Gdx.files.internal("magnetR.png"));
		skin.add("magnet", img);
		if (prefs.getAmountSpeedBooster() > 0) {
			TextButton.TextButtonStyle n1 = new TextButton.TextButtonStyle();
			n1.up = skin.getDrawable("acceleration");
			n1.font = font24;
			n1.fontColor = Color.WHITE;
			final TextButton speed = new TextButton(String.valueOf(prefs.getAmountSpeedBooster()), n1);
			speed.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					prefs.setUsedBusters(1);
					n = true;
					if (!pr1) {
						prefs.setAmountSpeedBooster(-1);
						pr1 = true;
						if (level > 3) scroller.setScrollSpeed(9);
						else scroller.setScrollSpeed(25);
						speed.setText(String.valueOf(prefs.getAmountSpeedBooster()));

						Thread thread = new Thread() {
							@Override
							public void run() {
								try {
									Thread.sleep(5000);
									pr1 = false;
									isE1 = true;
									if (level == 0) scroller.setScrollSpeed(9);
									else if (level == 1) scroller.setScrollSpeed(12);
									else if (level == 2) scroller.setScrollSpeed(15);
									else if (level == 3) scroller.setScrollSpeed(20);
									else if (level == 4) scroller.setScrollSpeed(25);
									else if (level == 5) scroller.setScrollSpeed(35);
									else if (level == 6) scroller.setScrollSpeed(40);
									else if (level == 7) scroller.setScrollSpeed(50);
								} catch (InterruptedException ignored) {}
							}
						};
						thread.start();
					}
				}
			});
			speed.setPosition(Width - 138, 10);
			group1.addActor(speed);
		}

		if (prefs.getAmountIncreaseWeightBooster() > 0) {
			TextButton.TextButtonStyle n2 = new TextButton.TextButtonStyle();
			n2.up = skin.getDrawable("x2bonus");
			n2.font = font24;
			n2.fontColor = Color.WHITE;
			final TextButton bonus = new TextButton(String.valueOf(prefs.getAmountIncreaseWeightBooster()), n2);
			bonus.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					prefs.setUsedBusters(1);
					n = true;
					if (!pr2) {
						prefs.setAmountIncreaseWeightBooster(-1);
						pr2 = true;
						bonus.setText(String.valueOf(prefs.getAmountIncreaseWeightBooster()));

						Thread thread = new Thread() {
							@Override
							public void run() {
								try {
									Thread.sleep(5000);
									pr2 = false;
									isE2 = true;
								} catch (InterruptedException ignored) {}
							}
						};
						thread.start();
					}
				}
			});
			if (prefs.getAmountSpeedBooster() == 0) bonus.setPosition(Width - 138, 10);
			else bonus.setPosition(Width - 276, 10);
			group1.addActor(bonus);
		}

		if (prefs.getAmountMagnetBooster() > 0) {
			TextButton.TextButtonStyle n3 = new TextButton.TextButtonStyle();
			n3.up = skin.getDrawable("magnet");
			n3.font = font24;
			n3.fontColor = Color.WHITE;
			final TextButton magnet = new TextButton(String.valueOf(prefs.getAmountMagnetBooster()), n3);
			magnet.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					prefs.setUsedBusters(1);
					n = true;
					if (!pr3) {
						prefs.setAmountMagnetBooster(-1);
						pr3 = true;
						magnet.setText(String.valueOf(prefs.getAmountMagnetBooster()));

						Thread thread = new Thread() {
							@Override
							public void run() {
								try {
									Thread.sleep(5000);
									pr3 = false;
									isE3 = true;
								} catch (InterruptedException ignored) {}
							}
						};
						thread.start();
					}
				}
			});
			if (prefs.getAmountSpeedBooster() == 0 && prefs.getAmountIncreaseWeightBooster() == 0) magnet.setPosition(Width - 138, 10);
			else if (prefs.getAmountSpeedBooster() == 0 || prefs.getAmountIncreaseWeightBooster() == 0) magnet.setPosition(Width - 276, 10);
			else magnet.setPosition(Width - 414, 10);
			group1.addActor(magnet);
		}

		stage.addActor(group1);
	}

	/**
	 * Render method, works with update function
	 *
	 * @param dt - delta time
	 */
	@Override
	public void render(float dt) {
		Gdx.gl.glClearColor(0.8f, 0.8f, 0.8f, 0.5f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); //* Other part of screen painted in green colour
		if (!isPaused) {
			update(dt);

			stage.getBatch().begin(); //* Function for starting batch painting

			if ((player.getRectangle().overlaps(ground.getRectangle()) || player.getRectangle().overlaps(ground1.getRectangle()))
					// || player.getRectangle().overlaps(ground2.getRectangle()) || player.getRectangle().overlaps(ground3.getRectangle()))
					&& !player.isGrounded) {
				scroller.stop();
				if (end != -50) {
					end -= 1;
				}
				c = 0;
				player.jumpForce -= 10;
			} //* Moratorium for intersection ground by body

			if (player.isGrounded() && Gdx.input.isTouched()) {
				isTouched = true;
				prefs.setJumps(1);
				player.isGrounded = false;
				player.startM = player.getJumpForce();
			} //* Checking the separation from the ground
			else {
				if (!n) {
					if (player.getJumpForce() >= player.startM + 700)
						isTouched = false; //* Checking the separation from the ground

					if (player.getJumpForce() < player.startM + 700 && isTouched) {
						if (pr1) {
							if (level <= 3) player.jumpForce += 30;
							else player.jumpForce += 10;
						}
						else {
							if (level == 0) player.jumpForce += 10;
							else if (level == 1) player.jumpForce += 13;
							else if (level == 2) player.jumpForce += 17;
							else if (level == 3) player.jumpForce += 23;
							else if (level == 4) player.jumpForce += 30;
							else if (level == 5) player.jumpForce += 40;
							else if (level == 6) player.jumpForce += 50;
							else if (level == 7) player.jumpForce += 60;
						}
					}  //* Up
					else {
						if (!player.isGrounded && player.getPosition().y + player.getJumpForce() > -1200) {
							if (pr1) {
								if (level <= 3) player.jumpForce -= 30;
								else player.jumpForce -= 10;
							}
							else {
								if (level == 0) player.jumpForce -= 10;
								else if (level == 1) player.jumpForce -= 13;
								else if (level == 2) player.jumpForce -= 17;
								else if (level == 3) player.jumpForce -= 23;
								else if (level == 4) player.jumpForce -= 30;
								else if (level == 5) player.jumpForce -= 40;
								else if (level == 6) player.jumpForce -= 50;
								else if (level == 7) player.jumpForce -= 60;
							}
						}
					} //* Down
				}
				else {
					n = false;
					isTouched = false;
					player.isGrounded = true;
				}
			} //* Function of jumping, jump force = 700

			ground.getRectangle().setPosition(ground.getX(), ground.getY());
			ground1.getRectangle().setPosition(ground1.getX(), ground1.getY());
//			ground2.getRectangle().setPosition(ground2.getX(), ground2.getY());
//			ground3.getRectangle().setPosition(ground3.getX(), ground3.getY()); //* Updating rectangle position of grounds

			player.getRectangle().setPosition(player.getRectangle().getX(), player.getY() + player.getJumpForce());
			player.getRectangleL().setPosition(player.getRectangleL().getX(), player.getY() + player.getJumpForce());
			player.getRectangleM().setPosition(player.getRectangleM().getX(), 0); //* Updating rectangle position of player

			if (player.getPosition().y + player.getJumpForce() - (Height>>1) + 400 < 0 || player.getPosition().y + player.getJumpForce() + (Height>>1) + 400 > 4167) {
				camera.position.set(player.getPosition().x + 600, 540, 0);

				if (player.getPosition().y + player.getJumpForce() <= -1200) {
					isGameOver = true;
				}
			} else {
				camera.position.set(player.getPosition().x + 600, player.getPosition().y + player.getJumpForce() + 400, 0);
			}
			camera.update();
			stage.getBatch().setProjectionMatrix(camera.combined); //* Updating camera position

			if (isGameOver) {
				Date currentDate = new Date();
				DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
				String timeExit = timeFormat.format(currentDate), s = challengePrefs.getTimeInGame();
				int h1 = 0, h2 = 0, h3 = 0, m1 = 0, m2 = 0, m3 = 0, s1 = 0, s2 = 0, s3 = 0, r = 0, l = 0;
				for (int i = 0; i < s.length(); i++) {
					if (s.charAt(i) == ':' && r == 0) {
						h1 = Integer.parseInt(s.substring(0, i));
						l = i + 1;
						r++;
					}
					else if (s.charAt(i) == ':' && r == 1) {
						m1 = Integer.parseInt(s.substring(l, i));
						l = i + 1;
						r++;
					}
					else if (i + 1 == s.length() && r == 2) {
						s1 = Integer.parseInt(s.substring(l));
						r++;
					}
				}
				r = 0;
				l = 0;
				for (int i = 0; i < timeEnter.length(); i++) {
					if (timeEnter.charAt(i) == ':' && r == 0) {
						h2 = Integer.parseInt(timeEnter.substring(0, i));
						l = i + 1;
						r++;
					}
					else if (timeEnter.charAt(i) == ':' && r == 1) {
						m2 = Integer.parseInt(timeEnter.substring(l, i));
						l = i + 1;
						r++;
					}
					else if (i + 1 == timeEnter.length() && r == 2) {
						s2 = Integer.parseInt(timeEnter.substring(l));
						r++;
					}
				}
				r = 0;
				l = 0;
				for (int i = 0; i < timeExit.length(); i++) {
					if (timeExit.charAt(i) == ':' && r == 0) {
						h3 = Integer.parseInt(timeExit.substring(0, i));
						l = i + 1;
						r++;
					}
					else if (timeExit.charAt(i) == ':' && r == 1) {
						m3 = Integer.parseInt(timeExit.substring(l, i));
						l = i + 1;
						r++;
					}
					else if (i + 1 == timeExit.length() && r == 2) {
						s3 = Integer.parseInt(timeExit.substring(l));
						r++;
					}
				}
				challengePrefs.setTimeInGame(((h1 + (h3-h2)) + ":" + (m1 + (m3-m2)) + ":" + (s1 + (s3-s2))));
				prefs.setForRecycled(player.weight);

				player.jumpForce = 0;
				isGameOver = false;
				camera.position.set(Width >> 1, Height >> 1, 0);
				ScreenManager.getInstance().setScreen(ScreenManager.ScreenType.GAME_OVER, player);
			} //* Game over function, which is destroying process and change screen

			drawBackground(); //* Drawing BG
			stage.getBatch().draw(playerImg, player.getPosition().x - 200 + end, player.getPosition().y + player.getJumpForce() - 20); //* Player texture batching
			if (prefs.getDown() != 0) stage.getBatch().draw(bottom, player.getPosition().x + end + 80, player.getPosition().y + player.getJumpForce() + 40); //* Bottom cloth texture batching
			if (prefs.getTop() != 0 && prefs.getTop() != 8) stage.getBatch().draw(top, player.getPosition().x + end + 75, player.getPosition().y + player.getJumpForce() + 230);
			else if (prefs.getTop() == 8) stage.getBatch().draw(top, player.getPosition().x + end - 290, player.getPosition().y + player.getJumpForce() - 30); //* Top cloth texture batching

			if (paper.getWeight() < 6)
				stage.getBatch().draw(paperTexture, paper.getX(), paper.getY(), paper.getWidth(), paper.getHeight());
			else
				stage.getBatch().draw(paperTexture1, paper.getX(), paper.getY(), paper.getWidth(), paper.getHeight()); //* Paper texture batching

			font24.getData().setScale(1.5f);
			if (camera.position.y - (Height >> 1) > 0 && camera.position.y + (Height >> 1) < 4167) {
				font24.draw(stage.getBatch(), chosenLanguage[0] + (int) player.getWeight(), camera.position.x - (Width >> 1) + 20, camera.position.y + (Height >> 1) - 25);
				if (isCollectedPaper) {
					font24.draw(stage.getBatch(), "+ " + lastPaper, camera.position.x - (Width >> 1) + 340, camera.position.y + (Height >> 1) - 25);
					if (!isRun) {
						Thread thread = new Thread() {
							@Override
							public void run() {
								try {
									isRun = true;
									while (timer < 3) {
										timer++;
										Thread.sleep(1000);
									}
									//Thread.sleep(1500);
									isCollectedPaper = false;
									timer = 0;
									isRun = false;
								} catch (InterruptedException ignored) {}
							}
						};
						thread.start();
					}
				}
				lastCamX = camera.position.x;
				lastCamY = camera.position.y;
			} else {
				font24.draw(stage.getBatch(), chosenLanguage[0] + (int) player.getWeight(), lastCamX - (Width >> 1) + 20, lastCamY + (Height >> 1) - 25);
				if (isCollectedPaper) {
					font24.draw(stage.getBatch(), "+ " + lastPaper, lastCamX - (Width >> 1) + 340, lastCamY + (Height >> 1) - 25);
					if (!isRun) {
						Thread thread = new Thread() {
							@Override
							public void run() {
								try {
									isRun = true;
									while (timer < 3) {
										timer++;
										Thread.sleep(1000);
									}
									//Thread.sleep(1500);
									isCollectedPaper = false;
									timer = 0;
									isRun = false;
								} catch (InterruptedException ignored) {
								}
							}
						};
						thread.start();
					}
				}
			} //* Text texture batching

			for (int i = 0; i < ground.getLength(); i++)
				stage.getBatch().draw(gr, ground.getX() + ground.getWidth() * i, ground.getY());
			for (int i = 0; i < ground1.getLength(); i++)
				stage.getBatch().draw(gr, ground1.getX() + ground1.getWidth() * i, ground1.getY());
//			for (int i = 0; i < ground2.getLength(); i++)
//				stage.getBatch().draw(gr, ground2.getX() + ground2.getWidth() * i, ground2.getY());
//			for (int i = 0; i < ground3.getLength(); i++)
//				stage.getBatch().draw(gr, ground3.getX() + ground3.getWidth() * i, ground3.getY()); //* Ground texture batching

			// in render method:
//			shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
//			shapeRenderer.setColor(Color.BLACK);
//			shapeRenderer.rect(player.getRectangleM().x, player.getRectangleM().y, player.getRectangleM().width, player.getRectangleM().height);
//			shapeRenderer.end();
		}
		else {
			stage.act(dt);
			stage.getBatch().begin();

			if (player.getPosition().y + player.getJumpForce() - (Height>>1) + 400 < 0 || player.getPosition().y + player.getJumpForce() + (Height>>1) + 400 > 4167) {
				camera.position.set(player.getPosition().x + 600, 540, 0);
			} else {
				camera.position.set(player.getPosition().x + 600, player.getPosition().y + player.getJumpForce() + 400, 0);
			}
			camera.update();
			stage.getBatch().setProjectionMatrix(camera.combined); //* Updating camera position

			drawBackground(); //* Drawing BG
			stage.getBatch().draw(playerImg, player.getPosition().x - 200 + end, player.getPosition().y + player.getJumpForce() - 20); //* Player texture batching
			if (prefs.getDown() != 0) stage.getBatch().draw(bottom, player.getPosition().x + end + 80, player.getPosition().y + player.getJumpForce() + 40); //* Bottom cloth texture batching
			if (prefs.getTop() != 0 && prefs.getTop() != 8) stage.getBatch().draw(top, player.getPosition().x + end + 75, player.getPosition().y + player.getJumpForce() + 230);
			else if (prefs.getTop() == 8) stage.getBatch().draw(top, player.getPosition().x + end - 290, player.getPosition().y + player.getJumpForce() - 30); //* Top cloth texture batching

			for (int i = 0; i < ground.getLength(); i++)
				stage.getBatch().draw(gr, ground.getX() + ground.getWidth() * i, ground.getY());
			for (int i = 0; i < ground1.getLength(); i++)
				stage.getBatch().draw(gr, ground1.getX() + ground1.getWidth() * i, ground1.getY());
//			for (int i = 0; i < ground2.getLength(); i++)
//				stage.getBatch().draw(gr, ground2.getX() + ground2.getWidth() * i, ground2.getY());
//			for (int i = 0; i < ground3.getLength(); i++)
//				stage.getBatch().draw(gr, ground3.getX() + ground3.getWidth() * i, ground3.getY()); //* Ground texture batching

			stage.getBatch().draw(paperTexture, -100, -100); //* Extra batching for correct work all batching source

			pause.draw();

			font24.setColor(Color.CYAN);
			font24.getData().setScale(5f);
			font24.draw(stage.getBatch(), chosenLanguage[3], (Width >> 1) - 3 * (Width >> 3), (Height >> 1) + 1.25f * (Height >> 2));
		}
		stage.getBatch().end(); //* Function for ending batch painting
		stage.draw();

		stage.getBatch().begin();
		if (progress1 < 13 && pr1) {
			textureRegionProgress1 = textureAtlasProgress.findRegion("Load" + (int) progress1);
			progress1 += 0.04;
			stage.getBatch().draw(textureRegionProgress1, Width - 138, 10);
		}
		else {
			progress1 = 1;
		}

		if (progress2 < 13 && pr2) {
			textureRegionProgress2 = textureAtlasProgress.findRegion("Load" + (int) progress2);
			progress2 += 0.04;
			if (prefs.getAmountSpeedBooster() == 0) stage.getBatch().draw(textureRegionProgress2, Width - 138, 10);
			else stage.getBatch().draw(textureRegionProgress2, Width - 276, 10);
		}
		else {
			progress2 = 1;
		}

		if (progress3 < 13 && pr3) {
			textureRegionProgress3 = textureAtlasProgress.findRegion("Load" + (int) progress3);
			progress3 += 0.04;
			if (prefs.getAmountSpeedBooster() == 0 && prefs.getAmountIncreaseWeightBooster() == 0) stage.getBatch().draw(textureRegionProgress3, Width - 138, 10);
			else if (prefs.getAmountSpeedBooster() == 0 || prefs.getAmountIncreaseWeightBooster() == 0) stage.getBatch().draw(textureRegionProgress3, Width - 276, 10);
			else stage.getBatch().draw(textureRegionProgress3, Width - 414, 10);
		}
		else {
			progress3 = 1;
		}
		stage.getBatch().end();
	}

	/**
	 * Background batching
	 */
	private void drawBackground() {
		if (!scroller.getBackground().isRight) {
			stage.getBatch().draw(img, background.getX(), background.getY(), background.getWidth(), background.getHeight());
			stage.getBatch().draw(img1, background.getX() - 4167, background.getY(), background.getWidth(), background.getHeight());
		} else {
			stage.getBatch().draw(img1, background.getX(), background.getY(), background.getWidth(), background.getHeight());
			stage.getBatch().draw(img, background.getX() - 4167, background.getY(), background.getWidth(), background.getHeight());
		} //* Checking right-left side of texture and batching
	}

	/**
	 * Updating method
	 *
	 * @param dt - delta time
	 */
	public void update(float dt) {
		scroller.update(dt); //* Scroller updating, sending dt to ScrollHandler class
		stage.act(dt);

		if (!pr1) {
			if (player.getWeight() >= 50 && player.getWeight() < 100) level = 1;
			else if (player.getWeight() >= 100 && player.getWeight() < 250) level = 2;
			else if (player.getWeight() >= 250 && player.getWeight() < 400) level = 3;
			else if (player.getWeight() >= 400 && player.getWeight() < 650) level = 4;
			else if (player.getWeight() >= 650 && player.getWeight() < 800) level = 5;
			else if (player.getWeight() >= 800 && player.getWeight() < 1000) level = 6;
			else if (player.getWeight() >= 1000) level = 7;

			if (level == 1) scroller.setScrollSpeed(12);
			else if (level == 2) scroller.setScrollSpeed(15);
			else if (level == 3) scroller.setScrollSpeed(20);
			else if (level == 4) scroller.setScrollSpeed(25);
			else if (level == 5) scroller.setScrollSpeed(35);
			else if (level == 6) scroller.setScrollSpeed(40);
			else if (level == 7) scroller.setScrollSpeed(50);
		}

		paper.getRectangle().setPosition(paper.getX(), paper.getY()); //* Updating rectangle position of paper

		player.isGrounded = player.getRectangleL().overlaps(ground.getRectangle()) ||
				player.getRectangleL().overlaps(ground1.getRectangle());
//				player.getRectangleL().overlaps(ground2.getRectangle()) ||
//				player.getRectangleL().overlaps(ground3.getRectangle()); //* Checking the landing

		c++; //* Variable of player animation
		if (c == 2) {
			playerImg = textureAtlas.findRegion("Player");
			if (prefs.getTop() != 0) top = textureA.findRegion("T1");
			if (prefs.getDown() != 0) bottom = textureB.findRegion("S1");
		}
		else if (c == 4) {
			playerImg = textureAtlas.findRegion("Player1");
			if (prefs.getTop() != 0) top = textureA.findRegion("T2");
			if (prefs.getDown() != 0) bottom = textureB.findRegion("S2");
		}
		else if (c == 6) {
			playerImg = textureAtlas.findRegion("Player2");
			if (prefs.getTop() != 0) top = textureA.findRegion("T3");
			if (prefs.getDown() != 0) bottom = textureB.findRegion("S3");
		}
		else if (c == 8) {
			playerImg = textureAtlas.findRegion("Player3");
			if (prefs.getTop() != 0) top = textureA.findRegion("T4");
			if (prefs.getDown() != 0) bottom = textureB.findRegion("S4");
		}
		else if (c == 10) {
			playerImg = textureAtlas.findRegion("Player2");
			if (prefs.getTop() != 0) top = textureA.findRegion("T3");
			if (prefs.getDown() != 0) bottom = textureB.findRegion("S3");
		}
		else if (c == 12) {
			playerImg = textureAtlas.findRegion("Player1");
			if (prefs.getTop() != 0) top = textureA.findRegion("T2");
			if (prefs.getDown() != 0) bottom = textureB.findRegion("S2");
		}
		else if (c == 14) {
			playerImg = textureAtlas.findRegion("Player");
			if (prefs.getTop() != 0) top = textureA.findRegion("T1");
			if (prefs.getDown() != 0) bottom = textureB.findRegion("S1");
			c = 0;
		} //* Player animation

		if (isE1) {
			if (prefs.getAmountSpeedBooster() == 0) {
				stage.getActors().removeIndex(1);
				useBuster();
			}
			isE1 = false;
		}
		if (isE2) {
			if (prefs.getAmountIncreaseWeightBooster() == 0) {
				stage.getActors().removeIndex(1);
				useBuster();
			}
			isE2 = false;
		}
		if (isE3) {
			if (prefs.getAmountMagnetBooster() == 0) {
				stage.getActors().removeIndex(1);
				useBuster();
			}
			isE3 = false;
		}

		if (!pr3) {
			if (player.getRectangle().overlaps(paper.getRectangle()) || player.getRectangleL().overlaps(paper.getRectangle())) {
				if (pr2) {
					player.weight += 2*paper.getWeight();
					lastPaper = 2*paper.getWeight();
				}
				else {
					player.weight += paper.getWeight();
					lastPaper = paper.getWeight();
				}
				isCollectedPaper = true;
				scroller.resetPaper();
				timer = 0;
			}
		}
		else {
			if (player.getRectangleM().overlaps(paper.getRectangle())) {
				if (pr2) {
					player.weight += 2*paper.getWeight();
					lastPaper = 2*paper.getWeight();
				}
				else {
					player.weight += paper.getWeight();
					lastPaper = paper.getWeight();
				}
				isCollectedPaper = true;
				scroller.resetPaper();
				timer = 0;
			}
		} //* Collecting paper
	}

	/**
	 * Dispose method
	 */
	@Override
	public void dispose() {
		try {
			textureAtlas.dispose();
			img.dispose();
			img1.dispose();
			font24.dispose();
			paperTexture.dispose();
			paperTexture1.dispose();
			gr.dispose();
			stage.dispose();
			playerImg.getTexture().dispose();
			textureA.dispose();
			textureB.dispose();
		} catch (Exception ignore) {}
	}
}
