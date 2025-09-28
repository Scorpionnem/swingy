package org.mbatty.controller;

/*

    The controller has access to both, it takes inputs and sends them to the model and tells the view to redraw

*/

abstract public class Controller {
    abstract public void processInput();
    abstract public void processStartGame();
}
