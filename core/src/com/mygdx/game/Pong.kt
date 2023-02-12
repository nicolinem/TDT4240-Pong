package com.mygdx.game

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.mygdx.game.states.GameState
import com.mygdx.game.states.MenuState
import com.mygdx.game.states.StateManager


class Pong() : ApplicationAdapter() {

    private lateinit var spriteBatch: SpriteBatch
    private lateinit var gameStateManager: StateManager
    private lateinit var font: BitmapFont

    override fun create() {
        spriteBatch = SpriteBatch()
        gameStateManager = StateManager
        font = BitmapFont()
        gameStateManager.push(MenuState(gameStateManager))
        Gdx.gl.glClearColor(1f, 0f, 0f, 1f)
    }

    override fun render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        gameStateManager.update(Gdx.graphics.deltaTime)
        gameStateManager.render(spriteBatch, font)
    }


    override fun dispose() {
/*        spriteBatch!!.dispose()
        font!!.dispose()*/
    }

    companion object {
        const val WIDTH = 800
        const val HEIGHT = 480
    }
}