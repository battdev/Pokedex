package com.battagliandrea.pokedex.ui.items.type

import com.battagliandrea.pokedex.ui.items.base.ListItem


data class TypeItem(
        override val id: Int = 0,
        val name: String = ""
): ListItem()
