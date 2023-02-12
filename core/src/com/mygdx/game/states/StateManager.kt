package com.mygdx.game.states

import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import java.util.Stack


object StateManager {
    private val states: Stack<State> = Stack()

    fun push(state: State?) {
        states.push(state)
    }

    fun pop() {
        states.pop()
    }

    fun set(state: State?) {
        states.pop()
        states.push(state)
    }

    fun update(dt: Float) {
        states.peek().update(dt)
    }

    fun render(sb: SpriteBatch, font: BitmapFont) {
        states.peek().render(sb, font)
    }
}