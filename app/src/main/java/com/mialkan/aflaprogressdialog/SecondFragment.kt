package com.mialkan.aflaprogressdialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.semantics.*
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.delay

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                BackButtonScreen(findNavController())
            }
        }
    }
}

@Composable
fun BackButtonScreen(navController: NavController) {
    var showLoading by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = showLoading) {
        if (showLoading) {
            delay(1500)
            showLoading = false
        }
    }
    AFLAProgressDialogTheme {
        Scaffold(
            topBar = {
                TopAppBar(title = {
                    Text(
                        text = "Focus Progress",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.semantics { heading() }
                    )
                }, navigationIcon = {
                        IconButton(
                            onClick = { navController.popBackStack() }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Navigate Up"
                            )
                        }
                    })
            }
        ) { paddingValues ->
            if (showLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                ) {
                    CircularProgressIndicator(color = MaterialTheme.colors.primary, modifier = Modifier.semantics { liveRegion = LiveRegionMode.Polite })
                }
            } else {
                Box(
                    modifier = Modifier.padding(paddingValues)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                            .verticalScroll(rememberScrollState())
                    ) {
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                            text = "How long did you sleep?",
                            style = MaterialTheme.typography.h6
                        )
                    }
                }
            }
        }
    }
}
