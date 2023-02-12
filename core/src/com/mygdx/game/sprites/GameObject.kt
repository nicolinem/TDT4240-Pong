package com.mygdx.game.sprites

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.scenes.scene2d.Actor

abstract class GameObject protected constructor(
    var position: Vector3,
    protected var velocity: Vector3,
    private var texture: Texture
) : Actor() {
    protected var sprite: Sprite = Sprite(texture)
    var bounds: Rectangle

    init {
        height = texture.height.toFloat()
        width = texture.width.toFloat()
        bounds =
            Rectangle(position.x, position.y, texture.width.toFloat(), texture.height.toFloat())
        setBounds(position.x, position.y, texture.width.toFloat(), texture.height.toFloat())
    }

    fun dispose() {
        this.sprite.texture.dispose()
    }
}