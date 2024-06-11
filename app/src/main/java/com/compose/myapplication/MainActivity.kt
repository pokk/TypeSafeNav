package com.compose.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.compose.myapplication.detail.Game
import com.compose.myapplication.detail.InGame
import com.compose.myapplication.detail.InGameScreen
import com.compose.myapplication.detail.Match
import com.compose.myapplication.detail.MatchScreen
import com.compose.myapplication.detail.ResultsWinner
import com.compose.myapplication.first.SecondScreen
import com.compose.myapplication.first.navigation.First
import com.compose.myapplication.first.navigation.Main
import com.compose.myapplication.second.FirstScreen
import com.compose.myapplication.second.navigation.Book
import com.compose.myapplication.second.navigation.Second
import com.compose.myapplication.second.navigation.bookType
import com.compose.myapplication.second.navigation.zonedDateTimeType
import com.compose.myapplication.ui.theme.MyApplicationTheme
import java.time.ZonedDateTime
import kotlin.reflect.typeOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TypeSafetyNavigation(
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}

@Composable
fun TypeSafetyNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Main) {
        navigation<Main>(
            startDestination = First,
        ) {
            composable<First> {
                FirstScreen(
                    modifier = modifier,
                    onClicked = {
                        val args =
                            Second(
                                book = Book.B,
                                time = ZonedDateTime.now(),
                            )
                        navController.navigate(args)
                    },
                )
            }
        }

        composable<Second>(
            typeMap =
                mapOf(
                    typeOf<Book>() to bookType,
                    typeOf<ZonedDateTime>() to zonedDateTimeType,
                ),
        ) { backStackEntry ->
            println("Start Second Screen and fetch args")
            val args = backStackEntry.toRoute<Second>()
            SecondScreen(
                modifier = modifier,
                title = args.book.name,
            )
        }

        navigation<Game>(
            startDestination = Match,
        ) {
            composable<Match> {
                MatchScreen(
                    modifier = Modifier,
                    onClick = { navController.navigate(InGame) },
                )
            }

            composable<InGame> {
                InGameScreen(
                    modifier = Modifier,
                    onClick = { navController.navigate(ResultsWinner) },
                )
            }
        }
    }
}
