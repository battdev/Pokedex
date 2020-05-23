package com.battagliandrea.pokedex.ui.items.title

import com.battagliandrea.pokedex.ui.items.base.ListItem


data class TitleItem(
        override val id: Int = 0,
        val text: String = ""
): ListItem()
