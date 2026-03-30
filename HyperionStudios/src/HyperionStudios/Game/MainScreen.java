package HyperionStudios.Game;

import HyperionStudios.Core.GameApplication;
import HyperionStudios.Core.GameBackend;
import HyperionStudios.Core.Graphics.g2D.SpriteBatch;
import HyperionStudios.Core.Input.Code;
import HyperionStudios.Core.Input.Input;
import HyperionStudios.Core.Screens.ScreenManager;
import HyperionStudios.Game.Resources.R;
import HyperionStudios.Game.Scenes.MenuScreen;

public class MainScreen extends GameApplication {
	
	public MainScreen(GameBackend backend) {
		super(backend);
		
		this.Start();
	}
	
    @Override
	public void Initialize() {
        System.out.println("MainScreen (Game): Inicializando...");
        
        ScreenManager.setScreen(new MenuScreen());
        
        Input.bindAction("Run", Code.KEY_LSHIFT);
        
        // Carregar TiledMaps
        R.loadTiledMap("Map/Map");
        
        // Carregar Fonts
        R.loadFont("Fonts/Pixel");
	}
	
	@Override
	public void Update(float delta) {
		
	}
	
	@Override
    public void Draw(SpriteBatch batch) {
		
    }
	
	@Override
	public void Dispose() {
		System.out.println("MainScreen (Game): Recursos liberados.");
	}
}