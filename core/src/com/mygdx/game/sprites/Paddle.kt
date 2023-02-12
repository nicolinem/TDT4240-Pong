package com.mygdx.game.sprites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3
import com.mygdx.game.Pong

class Paddle constructor(position: Vector3, velocity: Vector3?) :
    GameObject(position, velocity!!, Texture("paddle.png")) {
    init {
        bounds = Rectangle(position.x, position.y, PADDLE_WIDTH.toFloat(), PADDLE_HEIGHT.toFloat())
    }

    override fun draw(batch: Batch, parentAlpha: Float) {
        batch.draw(sprite, position.x, position.y)
    }

    fun update(deltaTime: Float) {

        //Paddle is about to leave the top of the screen
        if (position.y - PADDLE_HEIGHT > Pong.HEIGHT) {
            velocity.y = 0f
            println("TOP")
        }
        //Paddle is about to leave the bottom of the screen
        if (position.y < 0) {
            velocity.y = 0f
        }
        position.y += velocity.y
        sprite.setPosition(position.x, position.y)
        bounds.setPosition(position.x, position.y)
    }

    fun up() {
        position.y += 40f
        sprite.setPosition(position.x, position.y)
        bounds.setPosition(position.x, position.y)
    }

    fun down() {
        position.y -= 40f
        sprite.setPosition(position.x, position.y)
        bounds.setPosition(position.x, position.y)
    }

    companion object {
        const val PADDLE_WIDTH = 30
        const val PADDLE_HEIGHT = 130
    }
}