package com.compose.myapplication.detail

import kotlinx.serialization.Serializable

// Route for nested graph
@Serializable
data object Game

// Routes inside nested graph
@Serializable
data object Match

@Serializable
data object InGame

@Serializable
data object ResultsWinner

@Serializable
data object GameOver
