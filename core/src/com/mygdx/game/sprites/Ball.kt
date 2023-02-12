package com.mygdx.game.sprites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3
import com.mygdx.game.Pong
import kotlin.random.Random

class Ball() : GameObject(
    Vector3((400 - BALL_WIDTH / 2).toFloat(), (230 - BALL_HEIGHT / 2).toFloat(), 0f),
    Vector3(listOf(-7f, -6f, -5f, -4f, 7f, 6f, 5f, 4f).random(), listOf(-7f, -6f, -5f, -4f, 7f, 6f, 5f, 4f).random(), 0f),
    Texture("Untitled.png")
) {
    init {
        bounds = Rectangle(position.x, position.y, BALL_WIDTH.toFloat(), BALL_HEIGHT.toFloat())
    }

    override fun draw(batch: Batch, parentAlpha: Float) {
        batch.draw(sprite, position.x, position.y)
    }

    fun update(deltaTime: Float) {
        if (position.x > Pong.WIDTH - BALL_HEIGHT || position.x < 0) {
            velocity.x *= -1
            position.x = (400 - BALL_WIDTH / 2).toFloat()
            position.y = (Pong.HEIGHT /2 - BALL_HEIGHT / 2).toFloat()
            velocity.x = listOf(-7f, -6f, -5f, 7f, 6f, 5f).random()
            velocity.y = listOf(-7f, -6f, -5f, -4f, 7f, 6f, 5f, 4f).random()
        }
        if (position.y + BALL_WIDTH > Pong.HEIGHT) {
            velocity.y *= -1
        }
        if (position.y < 0) {
            velocity.y = velocity.y * -1
        }
        position.x += velocity.x
        position.y += velocity.y
        sprite.setPosition(position.x, position.y)
        bounds.setPosition(position.x, position.y)
    }

    fun collision() {
        velocity.x *= -1.05f
        velocity.y *= 1.02f
    }


    companion object {
        const val BALL_WIDTH = 30
        const val BALL_HEIGHT = 30
    }
}
