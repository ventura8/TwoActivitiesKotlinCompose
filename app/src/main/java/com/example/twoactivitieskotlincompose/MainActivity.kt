package com.example.twoactivitieskotlincompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.twoactivitieskotlincompose.ui.theme.TwoActivitiesKotlinComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavScreen() {
    TwoActivitiesKotlinComposeTheme {
        val navController = rememberNavController()
        Scaffold(topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) },
                colors =
                    topAppBarColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
            )
        }) { padding ->
            NavHost(
                navController,
                modifier = Modifier.padding(padding),
                startDestination = "${nav_routes.main}/{${nav_arguments.reply}}",
            ) {
                composable("${nav_routes.main}/{${nav_arguments.reply}}") {
                    MainScreen(
                        it.arguments?.getString(nav_arguments.reply),
                    ) { message -> navController.navigate("${nav_routes.second}/$message") }
                }
                composable(
                    "${nav_routes.second}/{${nav_arguments.message}}",
                    arguments =
                        listOf(navArgument(nav_arguments.message) { type = NavType.StringType }),
                ) {
                    SecondScreen(
                        it.arguments?.getString(nav_arguments.message),
                    ) { reply -> navController.navigate("${nav_routes.main}/$reply") }
                }
            }
        }
    }
}

@Preview
@Composable
fun NavScreenPreview() {
    NavScreen()
}

object nav_routes {
    const val main = "mainScreen"
    const val second = "secondScreen"
}

object nav_arguments {
    const val message = "message"
    const val reply = "reply"
}
