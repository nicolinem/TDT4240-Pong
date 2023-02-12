package com.mygdx.game.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputProcessor
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector3
import com.mygdx.game.Pong


abstract class State(protected var gsm: StateManager?) {

    protected var cam: OrthographicCamera = OrthographicCamera()

    init {
        cam.setToOrtho(false, Pong.WIDTH.toFloat(), Pong.HEIGHT.toFloat());

    }

    abstract fun update(dt: Float)
    abstract fun render(sb: SpriteBatch, font: BitmapFont)
    abstract fun dispose()

}