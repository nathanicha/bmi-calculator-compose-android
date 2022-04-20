package com.natlwd.compose.bmicalculator.main

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.natlwd.compose.bmicalculator.R

enum class MainIcon(val imageVector: ImageVector, @StringRes val contentDescription: Int) {
    Square(Icons.Default.CropSquare, R.string.cd_crop_square),
    Done(Icons.Default.Done, R.string.cd_done),
    Event(Icons.Default.Event, R.string.cd_event),
    Privacy(Icons.Default.PrivacyTip, R.string.cd_privacy),
    Trash(Icons.Default.RestoreFromTrash, R.string.cd_restore);

    companion object {
        val Default = Square
    }
}