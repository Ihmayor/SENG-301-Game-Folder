package inputRelated;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.state.StateBasedGame;
 
//Source :https://evilzone.org/java/%28java-fames-tut%29-slick2d-buttons-buttons-buttons/

public class BuildButton extends BasicButton {
 
    private static List<BuildButton> buttons = new ArrayList<BuildButton>();
 
    public BuildButton(GUIContext guic, Animation animation, int x, int y, StateBasedGame sbg, int stateID, Image inactive, Image active)
            throws SlickException {
        super(guic, x, y, sbg, stateID, inactive, active);
        buttons.add(this);
    }
 
    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        if (!isMouseOver()) {
            // activate one button at a time
            for (BuildButton b : buttons) {
                if (b.isMouseOver()) {
                    setActivated(false);
                    break;
                }
            }
        }
        super.mouseClicked(button, x, y, clickCount);
    }
 
}