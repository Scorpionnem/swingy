package org.mbatty.view;

/*

    The view is only used to draw infos on screen/terminal, it has access to the Model to take infos.

*/

import org.mbatty.model.GameState;
import org.mbatty.model.Model;

abstract public class View {
    protected Model model;

    abstract public void    render(GameState state);
}
