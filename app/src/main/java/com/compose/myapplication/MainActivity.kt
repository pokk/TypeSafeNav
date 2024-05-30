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
import androidx.navigation.toRoute
import com.compose.myapplication.first.SecondScreen
import com.compose.myapplication.first.navigation.First
import com.compose.myapplication.second.FirstScreen
import com.compose.myapplication.second.navigation.Book
import com.compose.myapplication.second.navigation.Second
import com.compose.myapplication.second.navigation.bookType
import com.compose.myapplication.ui.theme.MyApplicationTheme
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

    NavHost(navController, startDestination = First) {
        composable<First> {
            FirstScreen(
                modifier = modifier,
                onClicked = {
                    val args =
                        Second(
                            book = Book.B,
                        )
                    navController.navigate(args)
                },
            )
        }

        composable<Second>(
            typeMap =
                mapOf(
                    typeOf<Book>() to bookType,
                ),
        ) { backStackEntry ->
            println("Start Second Screen and fetch args")
            val args = backStackEntry.toRoute<Book>()
            println("=================================================")
            println(args.name)
            println("=================================================")
            SecondScreen(
                modifier = modifier,
                title = args.name,
            )
        }
    }
}
