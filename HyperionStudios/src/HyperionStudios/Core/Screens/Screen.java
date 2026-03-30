package HyperionStudios.Core.Screens;

import HyperionStudios.Core.GameApplication;
import HyperionStudios.Core.Graphics.g2D.SpriteBatch;

public interface Screen {

    void update(float delta);

    void render(SpriteBatch batch);

    void onEnter(GameApplication game);
    
    void onPause();

    void onExit();

}