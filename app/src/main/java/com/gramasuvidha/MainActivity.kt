package com.gramasuvidha

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.gramasuvidha.datastore.SessionManager
import com.gramasuvidha.navigation.RootNavigator
import com.gramasuvidha.ui.theme.GramaSuvidhaTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

import android.content.res.Configuration
import java.util.Locale
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalConfiguration

import com.gramasuvidha.data.repository.AuthRepository
import com.gramasuvidha.data.repository.ProjectRepository

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var sessionManager: SessionManager

    @Inject
    lateinit var projectRepository: ProjectRepository

    @Inject
    lateinit var authRepository: AuthRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val language by sessionManager.language.collectAsState(initial = "en")
            
            val locale = Locale(language)
            Locale.setDefault(locale)
            val config = Configuration(LocalConfiguration.current)
            config.setLocale(locale)
            
            // Update the activity's resources to match the selected language.
            // We avoid overriding LocalContext via CompositionLocalProvider because Hilt 
            // requires the LocalContext to be the actual Activity to create ViewModels.
            @Suppress("DEPRECATION")
            resources.updateConfiguration(config, resources.displayMetrics)
            
            CompositionLocalProvider(
                LocalConfiguration provides config
            ) {
                GramaSuvidhaTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        RootNavigator(
                            sessionManager = sessionManager,
                            projectRepository = projectRepository,
                            authRepository = authRepository
                        )
                    }
                }
            }
        }
    }
}
