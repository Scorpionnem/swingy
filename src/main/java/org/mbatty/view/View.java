package org.mbatty.view;

/*

    The view is only used to draw infos on screen/terminal, it has access to the Model to take infos.

*/

import org.mbatty.model.GameState;
import org.mbatty.model.Map;
import org.mbatty.model.Model;
import org.mbatty.model.entities.Entity;

import javax.swing.*;

abstract public class View {
    protected Model model;
    protected JFrame window;

    abstract public void    renderInfo(String str);
    abstract public void    renderMap(Map map);
    abstract public void    renderPlayerStats(Entity player);

    public JFrame getWindow() {
        return (window);
    }
    public void closeWindow() {
        if (window != null)
            window.setVisible(false);
    }
}
