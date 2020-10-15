package com.example.intimesimple.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.onCommit
import androidx.compose.ui.Modifier
import com.example.intimesimple.data.local.Workout
import timber.log.Timber

@ExperimentalMaterialApi
@Composable
fun WorkoutListContent(
        modifier: Modifier = Modifier,
        innerPadding: PaddingValues,
        items: List<Workout>,
        onSwipe: (Workout) -> Unit,
        onClick: (Workout) -> Unit
) {
    LazyColumnFor(
            modifier = modifier.padding(innerPadding),
            items = items,
    ) { item ->

        val dismissState = rememberDismissState()
        Timber.d("DismissState: ${dismissState.value}")
        onCommit(dismissState.value){
            if(dismissState.value == DismissValue.DismissedToEnd ){
                Timber.d("onSwipe() - Dismissing WorkoutID: ${item.id}")
                onSwipe(item)
            }
        }
        SwipeToDismiss(
            modifier = modifier,
            state = dismissState,
            directions = setOf(DismissDirection.StartToEnd),
            background = {
                WorkoutItemDismissBackground()
            },
            dismissContent = {
                WorkoutItem(
                    workout = item,
                    onClick = onClick)
            }
        )
    }
}