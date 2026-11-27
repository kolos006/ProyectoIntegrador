package com.example.prestakey.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.prestakey.ui.screens.auth.AuthScreen
import com.example.prestakey.ui.screens.incidents.IncidentsScreen
import com.example.prestakey.ui.screens.keys.KeysScreen
import com.example.prestakey.ui.screens.loans.LoansScreen
import com.example.prestakey.ui.screens.nfc.NfcScreen
import com.example.prestakey.ui.screens.nfc.NfcViewModel

/**
 * Rutas de navegación de la aplicación PrestaKey.
 */
object AppRoutes {
    const val AUTH = "auth"
    const val KEYS = "keys"
    const val LOANS = "loans"
    const val NFC = "nfc"
    const val INCIDENTS = "incidents"

    /**
     * Mapea el identificador numérico de cada sección (1 a 4) a su ruta correspondiente.
     */
    fun routeForSection(section: Int): String = when (section) {
        1 -> KEYS
        2 -> LOANS
        3 -> NFC
        4 -> INCIDENTS
        else -> KEYS
    }
}

/**
 * Grafo de navegación principal con NavHost y Scaffold.
 */
@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
    nfcViewModel: NfcViewModel? = null
) {
    val navigateToSection: (Int) -> Unit = { section ->
        val route = AppRoutes.routeForSection(section)
        navController.navigate(route) {
            popUpTo(AppRoutes.KEYS) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    val logout: () -> Unit = {
        navController.navigate(AppRoutes.AUTH) {
            popUpTo(0) {
                inclusive = true
            }
        }
    }

    NavHost(
        navController = navController,
        startDestination = AppRoutes.AUTH
    ) {
        composable(AppRoutes.AUTH) {
            AuthScreen(
                onLoginSuccess = {
                    navController.navigate(AppRoutes.KEYS) {
                        popUpTo(AppRoutes.AUTH) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(AppRoutes.KEYS) {
            KeysScreen(
                onNavigate = navigateToSection,
                onLogout = logout
            )
        }
        composable(AppRoutes.LOANS) {
            LoansScreen(
                onNavigate = navigateToSection,
                onLogout = logout
            )
        }
        composable(AppRoutes.NFC) {
            if (nfcViewModel != null) {
                NfcScreen(
                    viewModel = nfcViewModel,
                    onNavigate = navigateToSection,
                    onLogout = logout
                )
            } else {
                NfcScreen(
                    onNavigate = navigateToSection,
                    onLogout = logout
                )
            }
        }
        composable(AppRoutes.INCIDENTS) {
            IncidentsScreen(
                onNavigate = navigateToSection,
                onLogout = logout
            )
        }
    }
}
