package org.d3if0107.assesment2.navigation

import org.d3if0107.assesment2.ui.ui.Screen.KEY_ID_PESANAN

sealed class Screen(val route: String) {
    data object Home: Screen("mainScreen")
    data object FormBaru: Screen("detailScreen")
    data object FormUbah: Screen("detailScreen/{$KEY_ID_PESANAN}"){
        fun withId(id: Long) = "detailScreen/$id"
    }
}