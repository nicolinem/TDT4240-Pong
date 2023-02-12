package com.mygdx.game.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.ScreenUtils

class MenuState(gsm: StateManager?): State(gsm) {
    private var l = 0


    override fun update(dt: Float) {
        l ++
        if (l  > 3) {
            if (Gdx.input.justTouched()) {
                    gsm!!.set(GameState(gsm))
                    return
            }
        }

    }

    override fun render(sb: SpriteBatch, font: BitmapFont) {
        ScreenUtils.clear(0f, 0f, 0.2f, 1f)
        cam.update()
        sb.setProjectionMatrix(cam.combined)

        sb.begin()
        font.draw(sb, "Welcome to Pong!", 100f, 270f)
        font.draw(sb, "Tap anywhere to play", 100f, 170f)

        sb.end()
    }

    override fun dispose() {
    }

}