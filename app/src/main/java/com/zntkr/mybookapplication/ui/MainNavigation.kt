package com.zntkr.mybookapplication.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.zntkr.mybookapplication.feature.bookdetail.ui.BookDetailScreen
import com.zntkr.mybookapplication.feature.booklist.ui.BookListScreen

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            BookListScreen(modifier = Modifier, navController, it)
        }
        composable(
            route = "detail/{id}",
            arguments = listOf(
                navArgument(name = "id") { NavType.StringType },
            )
        ) { BookDetailScreen(navController, it.arguments?.getString("id")) }
    }
}