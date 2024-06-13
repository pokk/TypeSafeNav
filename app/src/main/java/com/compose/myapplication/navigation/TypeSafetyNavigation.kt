package com.compose.myapplication.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.compose.myapplication.detail.Game
import com.compose.myapplication.detail.InGame
import com.compose.myapplication.detail.InGameScreen
import com.compose.myapplication.detail.Match
import com.compose.myapplication.detail.MatchScreen
import com.compose.myapplication.detail.ResultWinnerScreen
import com.compose.myapplication.detail.ResultsWinner
import com.compose.myapplication.enumType
import com.compose.myapplication.first.FirstScreen
import com.compose.myapplication.first.navigation.First
import com.compose.myapplication.first.navigation.Main
import com.compose.myapplication.second.SecondScreen
import com.compose.myapplication.second.navigation.Book
import com.compose.myapplication.second.navigation.OwnZonedDateTime
import com.compose.myapplication.second.navigation.Second
import com.compose.myapplication.serializableType
import java.time.ZonedDateTime
import kotlin.reflect.typeOf

@SuppressLint("RestrictedApi")
@Composable
fun TypeSafetyNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    LaunchedEffect(Unit) {
        navController.currentBackStack.collect {
            println("=================================================")
            it.forEach(::println)
            println("=================================================")
        }
    }

    NavHost(navController, startDestination = Main) {
        navigation<Main>(
            startDestination = First,
        ) {
            composable<First> { backStackEntry ->
                FirstScreen(
                    modifier = modifier,
                    onClicked = {
                        val args =
                            Second(
                                book = Book.B,
                                ownTime = OwnZonedDateTime(ZonedDateTime.now()),
                            )
                        navController.navigate(args)
                    },
                )
            }
        }

        composable<Second>(
            typeMap =
                mapOf(
                    typeOf<Book>() to enumType<Book>(),
                    typeOf<OwnZonedDateTime>() to serializableType<OwnZonedDateTime>(),
                ),
        ) { backStackEntry ->
            val args = backStackEntry.toRoute<Second>()
            SecondScreen(
                modifier = modifier,
                title = "${args.book.title} ==== ${args.ownTime.value}",
                onBookClick = { navController.navigate(InGame) },
            )
        }

        navigation<Game>(
            startDestination = Match,
        ) {
            composable<Match> { backStackEntry ->
                MatchScreen(
                    modifier = Modifier,
                    onClick = { navController.navigate(InGame) },
                )
            }

            composable<InGame> { backStackEntry ->
                InGameScreen(
                    modifier = Modifier,
                    onClick = { navController.navigate(ResultsWinner) },
                )
            }

            composable<ResultsWinner> { backStackEntry ->
                ResultWinnerScreen(
                    modifier = Modifier,
                    onClick = {
//                        navController.navigate(
//                            Second(
//                                book = Book.C,
//                                ownTime =
//                                    OwnZonedDateTime(
//                                        value = ZonedDateTime.now(),
//                                    ),
//                            ),
//                        )
                        navController.navigate(First)
                    },
                )
            }
        }
    }
}
