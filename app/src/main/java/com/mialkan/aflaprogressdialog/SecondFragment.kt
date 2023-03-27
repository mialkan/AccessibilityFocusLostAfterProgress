package com.mialkan.aflaprogressdialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
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
                BackButtonScreen()
            }
        }
    }
}

@Composable
fun BackButtonScreen() {
    var showLoading by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = showLoading) {
        if (showLoading) {
            delay(2500)
            showLoading = false
        }
    }
    if (showLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        ) {
            CircularProgressIndicator(color = MaterialTheme.colors.primary)
        }
    } else {
        Box {
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
