package com.panfilosoft.contactapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.panfilosoft.contactapp.ui.screens.AddScreen
import com.panfilosoft.contactapp.ui.screens.ContactViewModel
import com.panfilosoft.contactapp.ui.screens.DetailScreen
import com.panfilosoft.contactapp.ui.screens.EditScreen
import com.panfilosoft.contactapp.ui.screens.HomeScreen


@Composable
fun NavManager(
    viewModel: ContactViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.Home.route
    ) {

        composable(Routes.Home.route) {
            HomeScreen(viewModel, navController)
        }
        composable(Routes.Add.route) {
            AddScreen(viewModel, navController)
        }

        composable(
            Routes.Detail.route + "/{id}/{name}/{number}/{email}",
            arguments = listOf(
                navArgument("id") { type = NavType.StringType },
                navArgument("name") { type = NavType.StringType },
                navArgument("number") { type = NavType.StringType },
                navArgument("email") { type = NavType.StringType },
            )
        ) {
            DetailScreen(
                viewModel,
                navController,
                it.arguments?.getString("id")!!,
                it.arguments?.getString("name")!!,
                it.arguments?.getString("number")!!,
                it.arguments?.getString("email")!!,
            )
        }

        composable(
            Routes.Edit.route + "/{id}/{name}/{number}/{email}",
            arguments = listOf(
                navArgument("id") { type = NavType.StringType },
                navArgument("name") { type = NavType.StringType },
                navArgument("number") { type = NavType.StringType },
                navArgument("email") { type = NavType.StringType },
            )
        ) {
            EditScreen(
                viewModel,
                navController,
                it.arguments?.getString("id")!!,
                it.arguments?.getString("name")!!,
                it.arguments?.getString("number")!!,
                it.arguments?.getString("email")!!,
            )
        }
    }
}

sealed class Routes(val route: String) {
    data object Home : Routes("home")
    data object Add : Routes("add")
    data object Edit : Routes("edit")
    data object Detail : Routes("detail")
}
