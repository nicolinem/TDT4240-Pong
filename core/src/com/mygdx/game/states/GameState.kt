package com.mygdx.game.states

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.InputAdapter
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.utils.ScreenUtils
import com.mygdx.game.sprites.Ball
import com.mygdx.game.sprites.Paddle
import java.util.*


class GameState(gsm: StateManager?) : State(gsm) {
    private val ball: Ball = Ball()
    private val leftPaddle: Paddle = Paddle(Vector3(30f, 200f, 0f), Vector3(0f, 0f, 0f))
    private val rightPaddle: Paddle = Paddle(Vector3((770 - Paddle.PADDLE_WIDTH).toFloat(), 200f, 0f), Vector3(0f, 0f, 0f))
    private var player1Score: Int = 0
    private var player2Score: Int = 0
    private var gameWinner = ""
    private var gameEnd: Boolean = false


    init {
        player1Score = 0
        player2Score = 0

        Gdx.input.inputProcessor = object : InputAdapter() {
            override fun keyDown(keycode: Int): Boolean {
                when (keycode) {
                    Keys.UP -> rightPaddle.up()
                    Keys.DOWN -> rightPaddle.down()
                    Keys.W -> leftPaddle.up()
                    Keys.S -> leftPaddle.down()
                }
                return true
            }
            override fun touchDown(x: Int, y: Int, pointer: Int, button: Int): Boolean {
                if (gameEnd) {
                    gameEnd = false
                    gsm!!.set(MenuState(gsm))
                }
                return true
            }
            }
        }






    override fun update(dt: Float) {

        var deltaTime = dt
        if (deltaTime > 0.1f) deltaTime = 0.1f

        if (!gameEnd) {
            checkCollision()
            checkScore()
            updateBall(deltaTime)
            updatePaddles(deltaTime)
        }

    }

    override fun dispose() {
        ball.dispose()
        leftPaddle.dispose()
        rightPaddle.dispose()
    }



    private fun updateBall(deltaTime: Float) {
        ball.update(deltaTime)
    }

    private fun updatePaddles(deltaTime: Float) {
        leftPaddle.update(deltaTime)
        rightPaddle.update(deltaTime)
    }

    private fun checkCollision() {
        if (ball.bounds.overlaps(leftPaddle.bounds)) ball.collision()
        if (ball.bounds.overlaps(rightPaddle.bounds)) ball.collision()
    }

    private fun checkScore() {
        if (ball.position.x < 0) {
            player2Score++
            print(player2Score)
            ball.setPosition(240f, 400f)
            if (player2Score > 20) {
                finishGame("Player 2")
            }
        }
        if (ball.position.x > 800 - Ball.BALL_WIDTH) {
            player1Score++
            print(player1Score)
            ball.setPosition(240f, 400f)
            if (player1Score > 20) {
                finishGame("Player 1")
            }
        }
    }

    private fun finishGame(player: String) {
        gameEnd = true
        gameWinner = player
    }


     override fun render(sb: SpriteBatch, font: BitmapFont) {
        draw(sb, font)
    }

    private fun draw(sb: SpriteBatch, font: BitmapFont) {
        cam.update()
        sb.setProjectionMatrix(cam.combined)
        Gdx.gl.glClearColor(0f, 0f, 0.2f, 1f)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
        sb.begin()
        if (gameEnd) {
            ScreenUtils.clear(0f, 0f, 0.2f, 1f)
            font.draw(sb, "$gameWinner is the winner", 100f, 150f)
            font.draw(sb, "Tap anywhere to go to main screen", 100f, 100f)
        } else {
            font.draw(sb, "Player 1: ${player1Score.toString()}", 0f, 470f)
            font.draw(sb, "Player 2: ${player2Score.toString()}", 0f, 450f)
            ball.draw(sb, 1f)
            leftPaddle.draw(sb, 1f)
            rightPaddle.draw(sb, 1f)
        }
        sb.end()
    }

}