package com.compose.myapplication.navigation

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.compose.myapplication.MainViewModel
import com.compose.myapplication.detail.Game
import com.compose.myapplication.detail.InGame
import com.compose.myapplication.detail.InGameScreen
import com.compose.myapplication.detail.InGameViewModel
import com.compose.myapplication.detail.Match
import com.compose.myapplication.detail.MatchScreen
import com.compose.myapplication.detail.MatchViewModel
import com.compose.myapplication.detail.ResultWinnerScreen
import com.compose.myapplication.detail.ResultsWinner
import com.compose.myapplication.enumType
import com.compose.myapplication.first.FirstScreen
import com.compose.myapplication.first.FirstViewModel
import com.compose.myapplication.first.navigation.First
import com.compose.myapplication.first.navigation.Main
import com.compose.myapplication.second.SecondScreen
import com.compose.myapplication.second.SecondViewModel
import com.compose.myapplication.second.navigation.Book
import com.compose.myapplication.second.navigation.OwnZonedDateTime
import com.compose.myapplication.second.navigation.Second
import com.compose.myapplication.serializableType
import java.time.ZonedDateTime
import kotlin.reflect.typeOf

@SuppressLint("RestrictedApi")
@Composable
fun TypeSafetyNavigation(
    modifier: Modifier = Modifier,
    onRecreate: () -> Unit = {},
) {
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
                println("This is the back stack entry's savedStateHandle")
                println(backStackEntry)
                println("=================================================")
                val vm = hiltViewModel<FirstViewModel>(backStackEntry)
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
            typeMap = mapOf(
                typeOf<Book>() to enumType<Book>(),
                typeOf<OwnZonedDateTime>() to serializableType<OwnZonedDateTime>(),
            ),
        ) { backStackEntry ->
            val args = backStackEntry.toRoute<Second>()
            SecondScreen(
                modifier = modifier,
                title = "${args.book.title} ==== ${args.ownTime.value}",
                onBookClick = { navController.navigate(Game) },
            )
        }

        navigation<Game>(
            startDestination = Match,
        ) {
            composable<Match> { backStackEntry ->
                val parentEntry = remember(backStackEntry) { navController.getBackStackEntry(Game) }
                val mainVM = hiltViewModel<MainViewModel>(parentEntry)
                val vm = hiltViewModel<MatchViewModel>()
                val secondViewModel = hiltViewModel<SecondViewModel>()
                MatchScreen(
                    modifier = Modifier,
                    onClick = { navController.navigate(InGame) },
                )
            }

            composable<InGame> { backStackEntry ->
                val parentEntry = remember(backStackEntry) { navController.getBackStackEntry(Game) }
                val mainVM = hiltViewModel<MainViewModel>(parentEntry)
                val vm = hiltViewModel<InGameViewModel>()
                InGameScreen(
                    modifier = Modifier,
                    onClick = { navController.navigate(ResultsWinner) },
                    onChangeClick = {
                        vm.changeConfiguration()
                        onRecreate()
                    },
                )
            }

            composable<ResultsWinner> { backStackEntry ->
                val parentEntry = remember(backStackEntry) { navController.getBackStackEntry(Game) }
                val mainVM = hiltViewModel<MainViewModel>(parentEntry)
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
